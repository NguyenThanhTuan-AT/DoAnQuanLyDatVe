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
import model.HanhKhach;

public class HanhKhachPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public HanhKhachPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"CCCD", "Họ tên", "Mã vé"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnLoad = new JButton("Đọc file JSON");
        btnLoad.addActionListener(e -> loadDataFromJson("data/hanh_khach.json"));
        add(btnLoad, BorderLayout.SOUTH);
    }

    private void loadDataFromJson(String filePath) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            FileReader reader = new FileReader(filePath);
            List<HanhKhach> dataList = gson.fromJson(reader, new TypeToken<List<HanhKhach>>() {
            }.getType());
            reader.close();

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
}
