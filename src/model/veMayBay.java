package model;

import java.time.LocalDateTime;

public class VeMayBay {

    private String maVe;
    private String soHieuChuyenBay;
    private String diemDi;
    private String diemDen;
    private LocalDateTime thoiGianDi;
    private LocalDateTime thoiGianDen;
    private String hangVe;      //Pho thong or Thuong gia
    private double giaVe;

    public VeMayBay() {
    }

    public VeMayBay(String maVe, String soHieuChuyenBay, String diemDi, String diemDen, LocalDateTime thoiGianDi, LocalDateTime thoiGianDen, String hangVe, double giaVe) {
        this.maVe = maVe;
        this.soHieuChuyenBay = soHieuChuyenBay;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.thoiGianDi = thoiGianDi;
        this.thoiGianDen = thoiGianDen;
        this.hangVe = hangVe;
        this.giaVe = giaVe;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getSoHieuChuyenBay() {
        return soHieuChuyenBay;
    }

    public void setSoHieuChuyenBay(String soHieuChuyenBay) {
        this.soHieuChuyenBay = soHieuChuyenBay;
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

    public String getHangVe() {
        return hangVe;
    }

    public void setHangVe(String hangVe) {
        this.hangVe = hangVe;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    @Override
    public String toString() {
        return "VeMayBay{" + "maVe: " + maVe + ", soHieuChuyenBay: " + soHieuChuyenBay + ", diemDi: " + diemDi + ", diemDen: " + diemDen + ", thoiGianDi: " + thoiGianDi + ", thoiGianDen: " + thoiGianDen + ", hangVe: " + hangVe + ", giaVe: " + giaVe + '}';
    }

}
