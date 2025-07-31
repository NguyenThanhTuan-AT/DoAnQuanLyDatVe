package model;

import java.util.ArrayList;
import java.util.List;

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
        danhSachChuyenBay.add(cb);
    }

    public void xoaChuyenBay(String soHieu) {
        danhSachChuyenBay.removeIf(cb -> cb.getSoHieuChuyenBay().equals(soHieu));
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
        danhSachVe.add(ve);
    }

    public void xoaVe(String maVe) {
        danhSachVe.removeIf(ve -> ve.getMaVe().equals(maVe));
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
        danhSachHanhKhach.add(hk);
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
        danhSachHang.add(hhk);
    }

    public HangHangKhong timHang(String maHang) {
        for (HangHangKhong hhk : danhSachHang) {
            if (hhk.getMaHang().equals(maHang)) {
                return hhk;
            }
        }
        return null;
    }

    // === Hiển thị toàn bộ hệ thống ===
    @Override
    public String toString() {
        return "QuanLyHeThongDatVe{"
                + "\\nChuyenBay=" + danhSachChuyenBay
                + ",\\nVe=" + danhSachVe
                + ",\\nHanhKhach=" + danhSachHanhKhach
                + ",\\nHangHangKhong=" + danhSachHang
                + '}';
    }

}
