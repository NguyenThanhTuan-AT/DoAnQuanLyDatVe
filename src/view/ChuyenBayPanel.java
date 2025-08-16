package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.ChuyenBay;
import model.QuanLyChung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ChuyenBayPanel extends JPanel {

    private QuanLyChung quanLyChung; // Thêm biến để tích hợp với QuanLyChung
    private JTable table;
    private DefaultTableModel tableModel;

    public ChuyenBayPanel(QuanLyChung quanLyChung) {
        // Nhận instance QuanLyChung qua constructor
        this.quanLyChung = quanLyChung;
        setLayout(new BorderLayout());

        String[] columnNames = {"Số hiệu chuyến bay", "Số hiệu máy bay", "Điểm đi", "Điểm đến", "Thời gian đi", "Thời gian đến", "Ghế thương gia", "Ghế phổ thông", "Vé đã bán"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnLoad = new JButton("Đọc file JSON");
        btnLoad.addActionListener(e -> loadDataFromJson("data/chuyen_bay.json"));
        buttonPanel.add(btnLoad);

        // Thêm nút để thêm chuyến bay
        JButton btnAdd = new JButton("Thêm chuyến bay");
        btnAdd.addActionListener(e -> themChuyenBay());
        buttonPanel.add(btnAdd);

        // Thêm nút để xóa chuyến bay
        JButton btnDelete = new JButton("Xóa chuyến bay");
        btnDelete.addActionListener(e -> xoaChuyenBay());
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
            List<ChuyenBay> dataList = gson.fromJson(reader, new TypeToken<List<ChuyenBay>>() {
            }.getType());
            reader.close();

            // Thêm dữ liệu vào QuanLyChung
            for (ChuyenBay cb : dataList) {
                quanLyChung.themChuyenBay(cb);
            }

            tableModel.setRowCount(0);
            for (ChuyenBay item : dataList) {
                // Sửa lỗi thiếu cột Điểm đi và Điểm đến
                Object[] row = {
                    item.getSoHieuChuyenBay(), item.getSoHieuMayBay(), item.getDiemDi(), item.getDiemDen(),
                    item.getThoiGianDi().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    item.getThoiGianDen().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    item.getSoThuongGia(), item.getSoPhoThong(), item.getSoVeDaBan()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file JSON: " + e.getMessage());
        }
    }

    private void themChuyenBay() {
        // Tạo form nhập liệu để thêm chuyến bay
        JTextField soHieuChuyenBayField = new JTextField();
        JTextField soHieuMayBayField = new JTextField();
        JTextField diemDiField = new JTextField();
        JTextField diemDenField = new JTextField();
        JTextField thoiGianDiField = new JTextField();
        JTextField thoiGianDenField = new JTextField();
        JTextField soThuongGiaField = new JTextField();
        JTextField soPhoThongField = new JTextField();

        Object[] fields = {
            "Số hiệu chuyến bay:", soHieuChuyenBayField,
            "Số hiệu máy bay:", soHieuMayBayField,
            "Điểm đi:", diemDiField,
            "Điểm đến:", diemDenField,
            "Thời gian đi (yyyy-MM-dd HH:mm):", thoiGianDiField,
            "Thời gian đến (yyyy-MM-dd HH:mm):", thoiGianDenField,
            "Số ghế thương gia:", soThuongGiaField,
            "Số ghế phổ thông:", soPhoThongField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Thêm chuyến bay", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                ChuyenBay cb = new ChuyenBay();
                cb.setSoHieuChuyenBay(soHieuChuyenBayField.getText());
                cb.setSoHieuMayBay(soHieuMayBayField.getText());
                cb.setDiemDi(diemDiField.getText());
                cb.setDiemDen(diemDenField.getText());
                cb.setThoiGianDi(LocalDateTime.parse(thoiGianDiField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                cb.setThoiGianDen(LocalDateTime.parse(thoiGianDenField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                cb.setSoThuongGia(Integer.parseInt(soThuongGiaField.getText()));
                cb.setSoPhoThong(Integer.parseInt(soPhoThongField.getText()));
                cb.setSoVeDaBan(0);

                quanLyChung.themChuyenBay(cb);
                // Cập nhật bảng sau khi thêm
                Object[] row = {
                    cb.getSoHieuChuyenBay(), cb.getSoHieuMayBay(), cb.getDiemDi(), cb.getDiemDen(),
                    cb.getThoiGianDi().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    cb.getThoiGianDen().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    cb.getSoThuongGia(), cb.getSoPhoThong(), cb.getSoVeDaBan()
                };
                tableModel.addRow(row);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm chuyến bay: " + e.getMessage());
            }
        }
    }

    private void xoaChuyenBay() {
        // Xóa chuyến bay được chọn từ bảng và QuanLyChung
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String soHieuChuyenBay = (String) tableModel.getValueAt(selectedRow, 0);
            quanLyChung.xoaChuyenBay(soHieuChuyenBay);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một chuyến bay để xóa!");
        }
    }
}