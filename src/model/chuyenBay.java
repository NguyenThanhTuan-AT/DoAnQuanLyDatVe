package model;

import java.time.LocalDateTime;

public class ChuyenBay {

    private String soHieuChuyenBay;
    private String soHieuMayBay;
    private String diemDi;
    private String diemDen;
    private LocalDateTime thoiGianDi;
    private LocalDateTime thoiGianDen;
    private int soThuongGia;
    private int soPhoThong;
    private int soVeDaBan;

    public ChuyenBay() {
    }

    public ChuyenBay(String soHieuChuyenBay, String soHieuMayBay, String diemDi, String diemDen, LocalDateTime thoiGianDi, LocalDateTime thoiGianDen, int soThuongGia, int soPhoThong, int soVeDaBan) {
        this.soHieuChuyenBay = soHieuChuyenBay;
        this.soHieuMayBay = soHieuMayBay;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.thoiGianDi = thoiGianDi;
        this.thoiGianDen = thoiGianDen;
        this.soThuongGia = soThuongGia;
        this.soPhoThong = soPhoThong;
        this.soVeDaBan = soVeDaBan;
    }

    public String getSoHieuChuyenBay() {
        return soHieuChuyenBay;
    }

    public void setSoHieuChuyenBay(String soHieuChuyenBay) {
        this.soHieuChuyenBay = soHieuChuyenBay;
    }

    public String getSoHieuMayBay() {
        return soHieuMayBay;
    }

    public void setSoHieuMayBay(String soHieuMayBay) {
        this.soHieuMayBay = soHieuMayBay;
    }

    public String getDiemDi() {
        return diemDi;
    }

    public void setDiemDi(String diemDi) {
        this.diemDi = diemDi;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }

    public LocalDateTime getThoiGianDi() {
        return thoiGianDi;
    }

    public void setThoiGianDi(LocalDateTime thoiGianDi) {
        this.thoiGianDi = thoiGianDi;
    }

    public LocalDateTime getThoiGianDen() {
        return thoiGianDen;
    }

    public void setThoiGianDen(LocalDateTime thoiGianDen) {
        this.thoiGianDen = thoiGianDen;
    }

    public int getSoThuongGia() {
        return soThuongGia;
    }

    public void setSoThuongGia(int soThuongGia) {
        this.soThuongGia = soThuongGia;
    }

    public int getSoPhoThong() {
        return soPhoThong;
    }

    public void setSoPhoThong(int soPhoThong) {
        this.soPhoThong = soPhoThong;
    }

    public int getSoVeDaBan() {
        return soVeDaBan;
    }

    public void setSoVeDaBan(int soVeDaBan) {
        this.soVeDaBan = soVeDaBan;
    }

    @Override
    public String toString() {
        return "chuyenBay{" + "soHieuChuyenBay: " + soHieuChuyenBay + ", soHieuMayBay: " + soHieuMayBay + ", diemDi: " + diemDi + ", diemDen: " + diemDen + ", thoiGianDi: " + thoiGianDi + ", thoiGianen: " + thoiGianDen + ", soThuongGia: " + soThuongGia + ", soPhoThong: " + soPhoThong + ", soVeDaBan: " + soVeDaBan + '}';
    }

}
