package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.HanhKhach;
import model.QuanLyChung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;

public class HanhKhachPanel extends JPanel {

    private QuanLyChung quanLyChung; // Thêm biến để tích hợp với QuanLyChung
    private JTable table;
    private DefaultTableModel tableModel;

    public HanhKhachPanel(QuanLyChung quanLyChung) {
        // Nhận instance QuanLyChung qua constructor
        this.quanLyChung = quanLyChung;
        setLayout(new BorderLayout());

        String[] columnNames = {"CCCD", "Họ tên", "Mã vé"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnLoad = new JButton("Đọc file JSON");
        btnLoad.addActionListener(e -> loadDataFromJson("data/hanh_khach.json"));
        buttonPanel.add(btnLoad);

        // Thêm nút để thêm hành khách
        JButton btnAdd = new JButton("Thêm hành khách");
        btnAdd.addActionListener(e -> themHanhKhach());
        buttonPanel.add(btnAdd);

        // Thêm nút để xóa hành khách
        JButton btnDelete = new JButton("Xóa hành khách");
        btnDelete.addActionListener(e -> xoaHanhKhach());
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
            List<HanhKhach> dataList = gson.fromJson(reader, new TypeToken<List<HanhKhach>>() {
            }.getType());
            reader.close();

            // Thêm dữ liệu vào QuanLyChung
            for (HanhKhach hk : dataList) {
                quanLyChung.themHanhKhach(hk);
            }

            tableModel.setRowCount(0);
            for (HanhKhach item : dataList) {
                Object[] row = {
                    item.getCccd(), item.getHoTen(), item.getMaVe()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file JSON: " + e.getMessage());
        }
    }

    private void themHanhKhach() {
        // Tạo form nhập liệu để thêm hành khách
        JTextField cccdField = new JTextField();
        JTextField hoTenField = new JTextField();
        JTextField maVeField = new JTextField();

        Object[] fields = {
            "CCCD:", cccdField,
            "Họ tên:", hoTenField,
            "Mã vé:", maVeField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Thêm hành khách", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                HanhKhach hk = new HanhKhach();
                hk.setCccd(cccdField.getText());
                hk.setHoTen(hoTenField.getText());
                hk.setMaVe(maVeField.getText());

                quanLyChung.themHanhKhach(hk);
                // Cập nhật bảng sau khi thêm
                Object[] row = {
                    hk.getCccd(), hk.getHoTen(), hk.getMaVe()
                };
                tableModel.addRow(row);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm hành khách: " + e.getMessage());
            }
        }
    }

    private void xoaHanhKhach() {
        // Xóa hành khách được chọn từ bảng và QuanLyChung
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String cccd = (String) tableModel.getValueAt(selectedRow, 0);
            quanLyChung.xoaHanhKhach(cccd);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hành khách để xóa!");
        }
    }
}