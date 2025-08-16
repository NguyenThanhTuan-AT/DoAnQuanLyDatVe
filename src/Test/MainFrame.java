package Test;

import javax.swing.*;
import view.ChuyenBayPanel;
import view.HangHangKhongPanel;
import view.HanhKhachPanel;
import view.VeMayBayPanel;
import model.QuanLyChung;

public class MainFrame extends JFrame {

    private QuanLyChung quanLyChung; // Thêm biến QuanLyChung để truyền vào các panel
    
    public MainFrame() {
        // Khởi tạo QuanLyChung và truyền vào các panel
        this.quanLyChung = new QuanLyChung();
        setTitle("Quản lý đặt vé máy bay");
        setSize(900, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Vé máy bay", new VeMayBayPanel(quanLyChung));
        tabbedPane.addTab("Hành khách", new HanhKhachPanel(quanLyChung));
        tabbedPane.addTab("Chuyến bay", new ChuyenBayPanel(quanLyChung));
        tabbedPane.addTab("Hãng hàng không", new HangHangKhongPanel(quanLyChung));

        add(tabbedPane);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
