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
import model.ChuyenBay;

public class ChuyenBayPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public ChuyenBayPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"Số hiệu chuyến bay", "Số hiệu máy bay", "Điểm đi", "Điểm đến", "Thời gian đi", "Thời gian đến", "Ghế thương gia", "Ghế phổ thông", "Vé đã bán"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnLoad = new JButton("Đọc file JSON");
        btnLoad.addActionListener(e -> loadDataFromJson("data/chuyen_bay.json"));
        add(btnLoad, BorderLayout.SOUTH);
    }

    private void loadDataFromJson(String filePath) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();
            FileReader reader = new FileReader(filePath);
            List<ChuyenBay> dataList = gson.fromJson(reader, new TypeToken<List<ChuyenBay>>() {
            }.getType());
            reader.close();

            tableModel.setRowCount(0);
            for (ChuyenBay item : dataList) {
                Object[] row = {
                    item.getSoHieuChuyenBay(), item.getSoHieuMayBay(), item.getThoiGianDi().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    item.getThoiGianDen().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), item.getSoThuongGia(), item.getSoPhoThong(), item.getSoVeDaBan()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file JSON: " + e.getMessage());
        }
    }
}
