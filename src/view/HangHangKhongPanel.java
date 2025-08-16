package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.HangHangKhong;
import model.QuanLyChung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class HangHangKhongPanel extends JPanel {

    private QuanLyChung quanLyChung; // Thêm biến để tích hợp với QuanLyChung
    private JTable table;
    private DefaultTableModel tableModel;

    public HangHangKhongPanel(QuanLyChung quanLyChung) {
        // Nhận instance QuanLyChung qua constructor
        this.quanLyChung = quanLyChung;
        setLayout(new BorderLayout());

        String[] columnNames = {"Mã hãng", "Tên hãng", "Số lượng máy bay", "Danh sách máy bay"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnLoad = new JButton("Đọc file JSON");
        btnLoad.addActionListener(e -> loadDataFromJson("data/hang_hang_khong.json"));
        buttonPanel.add(btnLoad);

        // Thêm nút để thêm hãng hàng không
        JButton btnAdd = new JButton("Thêm hãng hàng không");
        btnAdd.addActionListener(e -> themHangHangKhong());
        buttonPanel.add(btnAdd);

        // Thêm nút để xóa hãng hàng không
        JButton btnDelete = new JButton("Xóa hãng hàng không");
        btnDelete.addActionListener(e -> xoaHangHangKhong());
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
            List<HangHangKhong> dataList = gson.fromJson(reader, new TypeToken<List<HangHangKhong>>() {
            }.getType());
            reader.close();

            // Thêm dữ liệu vào QuanLyChung
            for (HangHangKhong hhk : dataList) {
                quanLyChung.themHang(hhk);
            }

            tableModel.setRowCount(0);
            for (HangHangKhong item : dataList) {
                Object[] row = {
                    item.getMaHang(), item.getTenHang(), item.getSoLuongMayBay(), item.getDanhSachMayBay()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file JSON: " + e.getMessage());
        }
    }

    private void themHangHangKhong() {
        // Tạo form nhập liệu để thêm hãng hàng không
        JTextField maHangField = new JTextField();
        JTextField tenHangField = new JTextField();
        JTextField soLuongMayBayField = new JTextField();
        JTextField danhSachMayBayField = new JTextField();

        Object[] fields = {
            "Mã hãng:", maHangField,
            "Tên hãng:", tenHangField,
            "Số lượng máy bay:", soLuongMayBayField,
            "Danh sách máy bay (phân cách bởi dấu phẩy):", danhSachMayBayField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Thêm hãng hàng không", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                HangHangKhong hhk = new HangHangKhong();
                hhk.setMaHang(maHangField.getText());
                hhk.setTenHang(tenHangField.getText());
                hhk.setSoLuongMayBay(Integer.parseInt(soLuongMayBayField.getText()));
                hhk.setDanhSachMayBay(Arrays.asList(danhSachMayBayField.getText().split(",")));

                quanLyChung.themHang(hhk);
                // Cập nhật bảng sau khi thêm
                Object[] row = {
                    hhk.getMaHang(), hhk.getTenHang(), hhk.getSoLuongMayBay(), hhk.getDanhSachMayBay()
                };
                tableModel.addRow(row);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm hãng hàng không: " + e.getMessage());
            }
        }
    }

    private void xoaHangHangKhong() {
        // Xóa hãng hàng không được chọn từ bảng và QuanLyChung
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String maHang = (String) tableModel.getValueAt(selectedRow, 0);
            quanLyChung.xoaHang(maHang);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hãng hàng không để xóa!");
        }
    }
}