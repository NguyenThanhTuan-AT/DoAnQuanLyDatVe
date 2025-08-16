package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import view.LocalDateTimeAdapter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

public class QuanLyChung {

    private List<ChuyenBay> danhSachChuyenBay;
    private List<VeMayBay> danhSachVe;
    private List<HanhKhach> danhSachHanhKhach;
    private List<HangHangKhong> danhSachHang;

    public QuanLyChung() {
        danhSachChuyenBay = new ArrayList<>();
        danhSachVe = new ArrayList<>();
        danhSachHanhKhach = new ArrayList<>();
        danhSachHang = new ArrayList<>();
    }

// === Quản lý chuyến bay ===
    
    public void themChuyenBay(ChuyenBay cb) {
        // Kiểm tra xem chuyến bay có hợp lệ hoặc chưa tồn tại trước khi thêm
        if (cb == null || cb.getSoHieuChuyenBay() == null || timChuyenBay(cb.getSoHieuChuyenBay()) != null) {
            throw new IllegalArgumentException("Chuyến bay không hợp lệ hoặc đã tồn tại!");
        }
        danhSachChuyenBay.add(cb);
        luuChuyenBayVaoFile("data/chuyen_bay.json");// Lưu dữ liệu vào file JSON sau khi thêm
    }

    public void xoaChuyenBay(String soHieu) {
        danhSachChuyenBay.removeIf(cb -> cb.getSoHieuChuyenBay().equals(soHieu));
        luuChuyenBayVaoFile("data/chuyen_bay.json"); // Lưu dữ liệu vào file JSON sau khi xóa
    }

    public ChuyenBay timChuyenBay(String soHieu) {
        for (ChuyenBay cb : danhSachChuyenBay) {
            if (cb.getSoHieuChuyenBay().equals(soHieu)) {
                return cb;
            }
        }
        return null;
    }

    // === Quản lý vé ===
    public void themVe(VeMayBay ve) {
        // Kiểm tra chuyến bay tồn tại và còn vé trước khi thêm
        ChuyenBay cb = timChuyenBay(ve.getSoHieuChuyenBay());
        if (cb == null) {
            throw new IllegalArgumentException("Chuyến bay không tồn tại!");
        }
        if (cb.getSoVeDaBan() >= cb.getSoThuongGia() + cb.getSoPhoThong()) {
            throw new IllegalArgumentException("Hết vé cho chuyến bay này!");
        }
        if (ve == null || ve.getMaVe() == null || timVe(ve.getMaVe()) != null) {
            throw new IllegalArgumentException("Vé không hợp lệ hoặc đã tồn tại!");
        }
        danhSachVe.add(ve);
        cb.setSoVeDaBan(cb.getSoVeDaBan() + 1); // Tăng số vé đã bán
        luuVeVaoFile("data/ve.json"); // Lưu dữ liệu vào file JSON
    }

    public void xoaVe(String maVe) {
        // Xóa vé và giảm số vé đã bán của chuyến bay tương ứng
        VeMayBay ve = timVe(maVe);
        if (ve != null) {
            ChuyenBay cb = timChuyenBay(ve.getSoHieuChuyenBay());
            if (cb != null) {
                cb.setSoVeDaBan(cb.getSoVeDaBan() - 1);
            }
            danhSachVe.removeIf(v -> v.getMaVe().equals(maVe));
            luuVeVaoFile("data/ve.json"); // Lưu dữ liệu vào file JSON
        }  
    }

    public VeMayBay timVe(String maVe) {
        for (VeMayBay ve : danhSachVe) {
            if (ve.getMaVe().equals(maVe)) {
                return ve;
            }
        }
        return null;
    }

    // === Quản lý hành khách ===
    public void themHanhKhach(HanhKhach hk) {
        // Kiểm tra mã vé tồn tại và hành khách chưa tồn tại trước khi thêm
        if (hk == null || hk.getCccd() == null || timHanhKhach(hk.getCccd()) != null) {
            throw new IllegalArgumentException("Hành khách không hợp lệ hoặc đã tồn tại!");
        }
        if (timVe(hk.getMaVe()) == null) {
            throw new IllegalArgumentException("Mã vé không tồn tại!");
        }
        danhSachHanhKhach.add(hk);
        luuHanhKhachVaoFile("data/hanh_khach.json"); // Lưu dữ liệu vào file JSON
    }

    public void xoaHanhKhach(String cccd) {
        danhSachHanhKhach.removeIf(hk -> hk.getCccd().equals(cccd));// Thêm phương thức xóa hành khách
        luuHanhKhachVaoFile("data/hanh_khach.json"); // Lưu dữ liệu vào file JSON
    }
    
    public HanhKhach timHanhKhach(String cccd) {
        for (HanhKhach hk : danhSachHanhKhach) {
            if (hk.getCccd().equals(cccd)) {
                return hk;
            }
        }
        return null;
    }

    // === Quản lý hãng hàng không ===
    public void themHang(HangHangKhong hhk) {
        // Kiểm tra hãng hàng không hợp lệ và chưa tồn tại
        if (hhk == null || hhk.getMaHang() == null || timHang(hhk.getMaHang()) != null) {
            throw new IllegalArgumentException("Hãng hàng không không hợp lệ hoặc đã tồn tại!");
        }
        danhSachHang.add(hhk);
        luuHangHangKhongVaoFile("data/hang_hang_khong.json"); // Lưu dữ liệu vào file JSON
    }

    public void xoaHang(String maHang) {
        danhSachHang.removeIf(hhk -> hhk.getMaHang().equals(maHang));// Thêm phương thức xóa hãng hàng không
        luuHangHangKhongVaoFile("data/hang_hang_khong.json"); // Lưu dữ liệu vào file JSON
    }
    
    public HangHangKhong timHang(String maHang) {
        for (HangHangKhong hhk : danhSachHang) {
            if (hhk.getMaHang().equals(maHang)) {
                return hhk;
            }
        }
        return null;
    }

    // === Lưu dữ liệu vào file JSON ===
    // Lưu danh sách chuyến bay vào file JSON
    private void luuChuyenBayVaoFile(String filePath) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();
            FileWriter writer = new FileWriter(filePath);
            gson.toJson(danhSachChuyenBay, writer);
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu file JSON chuyến bay: " + e.getMessage());
        }
    }
    // Lưu danh sách vé vào file JSON
    private void luuVeVaoFile(String filePath) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .setPrettyPrinting()
                    .create();
            FileWriter writer = new FileWriter(filePath);
            gson.toJson(danhSachVe, writer);
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu file JSON vé: " + e.getMessage());
        }
    }
    // Lưu danh sách hành khách vào file JSON
    private void luuHanhKhachVaoFile(String filePath) {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            FileWriter writer = new FileWriter(filePath);
            gson.toJson(danhSachHanhKhach, writer);
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu file JSON hành khách: " + e.getMessage());
        }
    }
    // Lưu danh sách hãng hàng không vào file JSON
    private void luuHangHangKhongVaoFile(String filePath) {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            FileWriter writer = new FileWriter(filePath);
            gson.toJson(danhSachHang, writer);
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu file JSON hãng hàng không: " + e.getMessage());
        }
    }

    // === Hiển thị toàn bộ hệ thống ===
    @Override
    public String toString() {
        return "QuanLyChung{" // Đổi tên
                + "\\nChuyenBay=" + danhSachChuyenBay
                + ",\\nVe=" + danhSachVe
                + ",\\nHanhKhach=" + danhSachHanhKhach
                + ",\\nHangHangKhong=" + danhSachHang
                + '}';
    }

}
