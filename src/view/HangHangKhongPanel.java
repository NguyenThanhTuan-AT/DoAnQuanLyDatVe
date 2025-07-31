package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.awt.*;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.HangHangKhong;

public class HangHangKhongPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public HangHangKhongPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"Mã hãng", "Tên hãng", "Số lượng máy bay", "Danh sách máy bay"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnLoad = new JButton("Đọc file JSON");
        btnLoad.addActionListener(e -> loadDataFromJson("data/hang_hang_khong.json"));
        add(btnLoad, BorderLayout.SOUTH);
    }

    private void loadDataFromJson(String filePath) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            FileReader reader = new FileReader(filePath);
            List<HangHangKhong> dataList = gson.fromJson(reader, new TypeToken<List<HangHangKhong>>() {
            }.getType());
            reader.close();

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
}
