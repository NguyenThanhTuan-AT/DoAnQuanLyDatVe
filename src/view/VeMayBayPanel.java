package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.QuanLyChung;
import model.VeMayBay;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VeMayBayPanel extends JPanel {

    private QuanLyChung quanLyChung; // Thêm biến để tích hợp với QuanLyChung
    private JTable table;
    private DefaultTableModel tableModel;

    public VeMayBayPanel(QuanLyChung quanLyChung) {
        // Nhận instance QuanLyChung qua constructor
        this.quanLyChung = quanLyChung;
        setLayout(new BorderLayout());

        String[] columnNames = {"Mã vé", "Chuyến bay", "Điểm đi", "Điểm đến", "Thời gian đi", "Thời gian đến", "Hạng vé", "Giá"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnLoad = new JButton("Đọc file JSON");
        btnLoad.addActionListener(e -> loadDataFromJson("data/ve.json"));
        buttonPanel.add(btnLoad);

        // Thêm nút để thêm vé
        JButton btnAdd = new JButton("Thêm vé");
        btnAdd.addActionListener(e -> themVe());
        buttonPanel.add(btnAdd);

        // Thêm nút để xóa vé
        JButton btnDelete = new JButton("Xóa vé");
        btnDelete.addActionListener(e -> xoaVe());
        buttonPanel.add(btnDelete);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadDataFromJson(String filePath) {
        try {
            // Kiểm tra file tồn tại trước khi đọc
            File file = new File(filePath);
            if (!file.exists()) {
                JOptionPane.showMessageDialog(this, "File JSON không tồn tại: " + filePath);
                return;
            }
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            FileReader reader = new FileReader(filePath);
            List<VeMayBay> dataList = gson.fromJson(reader, new TypeToken<List<VeMayBay>>() {
            }.getType());
            reader.close();

            // Thêm dữ liệu vào QuanLyChung
            for (VeMayBay ve : dataList) {
                quanLyChung.themVe(ve);
            }

            tableModel.setRowCount(0);
            for (VeMayBay item : dataList) {
                Object[] row = {
                    item.getMaVe(), item.getSoHieuChuyenBay(), item.getDiemDi(), item.getDiemDen(),
                    item.getThoiGianDi().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    item.getThoiGianDen().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    item.getHangVe(), item.getGiaVe()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file JSON: " + e.getMessage());
        }
    }

    private void themVe() {
        // Tạo form nhập liệu để thêm vé
        JTextField maVeField = new JTextField();
        JTextField soHieuChuyenBayField = new JTextField();
        JTextField diemDiField = new JTextField();
        JTextField diemDenField = new JTextField();
        JTextField thoiGianDiField = new JTextField();
        JTextField thoiGianDenField = new JTextField();
        JTextField hangVeField = new JTextField();
        JTextField giaVeField = new JTextField();

        Object[] fields = {
            "Mã vé:", maVeField,
            "Số hiệu chuyến bay:", soHieuChuyenBayField,
            "Điểm đi:", diemDiField,
            "Điểm đến:", diemDenField,
            "Thời gian đi (yyyy-MM-dd HH:mm):", thoiGianDiField,
            "Thời gian đến (yyyy-MM-dd HH:mm):", thoiGianDenField,
            "Hạng vé (Phổ thông/Thương gia):", hangVeField,
            "Giá vé:", giaVeField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Thêm vé", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                VeMayBay ve = new VeMayBay();
                ve.setMaVe(maVeField.getText());
                ve.setSoHieuChuyenBay(soHieuChuyenBayField.getText());
                ve.setDiemDi(diemDiField.getText());
                ve.setDiemDen(diemDenField.getText());
                ve.setThoiGianDi(LocalDateTime.parse(thoiGianDiField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                ve.setThoiGianDen(LocalDateTime.parse(thoiGianDenField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                ve.setHangVe(hangVeField.getText());
                ve.setGiaVe(Double.parseDouble(giaVeField.getText()));

                quanLyChung.themVe(ve);
                // Cập nhật bảng sau khi thêm
                Object[] row = {
                    ve.getMaVe(), ve.getSoHieuChuyenBay(), ve.getDiemDi(), ve.getDiemDen(),
                    ve.getThoiGianDi().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    ve.getThoiGianDen().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    ve.getHangVe(), ve.getGiaVe()
                };
                tableModel.addRow(row);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm vé: " + e.getMessage());
            }
        }
    }

    private void xoaVe() {
        // Xóa vé được chọn từ bảng và QuanLyChung
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String maVe = (String) tableModel.getValueAt(selectedRow, 0);
            quanLyChung.xoaVe(maVe);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một vé để xóa!");
        }
    }
}