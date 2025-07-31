package model;

import java.util.List;

public class HangHangKhong {

    private String maHang;
    private String tenHang;
    private int soLuongMayBay;
    private List<String> danhSachMayBay;   //theo so lieu;

    public HangHangKhong() {
    }

    public HangHangKhong(String maHang, String tenHang, int soLuongMayBay, List<String> danhSachMayBay) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuongMayBay = soLuongMayBay;
        this.danhSachMayBay = danhSachMayBay;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getSoLuongMayBay() {
        return soLuongMayBay;
    }

    public void setSoLuongMayBay(int soLuongMayBay) {
        this.soLuongMayBay = soLuongMayBay;
    }

    public List<String> getDanhSachMayBay() {
        return danhSachMayBay;
    }

    public void setDanhSachMayBay(List<String> danhSachMayBay) {
        this.danhSachMayBay = danhSachMayBay;
    }

    @Override
    public String toString() {
        return "hangHangKhong{" + "maHang: " + maHang + ", tenHang: " + tenHang + ", soLuongMayBay: " + soLuongMayBay + ", danhSachMayBay: " + danhSachMayBay + '}';
    }

}
