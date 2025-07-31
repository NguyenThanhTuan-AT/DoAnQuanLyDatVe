package Test;

import javax.swing.*;
import view.ChuyenBayPanel;
import view.HangHangKhongPanel;
import view.HanhKhachPanel;
import view.VeMayBayPanel;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Quản lý đặt vé máy bay");
        setSize(900, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Vé máy bay", new VeMayBayPanel());
        tabbedPane.addTab("Hành khách", new HanhKhachPanel());
        tabbedPane.addTab("Chuyến bay", new ChuyenBayPanel());
        tabbedPane.addTab("Hãng hàng không", new HangHangKhongPanel());

        add(tabbedPane);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
