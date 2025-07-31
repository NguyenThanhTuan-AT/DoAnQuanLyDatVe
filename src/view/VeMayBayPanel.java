package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.awt.*;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.veMayBay;

public class VeMayBayPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public VeMayBayPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"Mã vé", "Chuyến bay", "Điểm đi", "Điểm đến", "Thời gian đi", "Thời gian đến", "Hạng vé", "Giá"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnLoad = new JButton("Đọc file JSON");
        btnLoad.addActionListener(e -> loadDataFromJson("data/ve.json"));
        add(btnLoad, BorderLayout.SOUTH);
    }

    private void loadDataFromJson(String filePath) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            FileReader reader = new FileReader(filePath);
            List<veMayBay> dataList = gson.fromJson(reader, new TypeToken<List<veMayBay>>() {
            }.getType());
            reader.close();

            tableModel.setRowCount(0);
            for (veMayBay item : dataList) {
                Object[] row = {
                    item.getMaVe(), item.getSoHieuChuyenBay(), item.getDiemDi(), item.getDiemDen(), item.getThoiGianDi().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    item.getThoiGianDen().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), item.getHangVe(), item.getGiaVe()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file JSON: " + e.getMessage());
        }
    }
}
