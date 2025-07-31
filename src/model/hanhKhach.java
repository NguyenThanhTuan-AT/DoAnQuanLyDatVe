package model;

public class HanhKhach {

    private String cccd;
    private String hoTen;
    private String maVe;

    public HanhKhach() {
    }

    public HanhKhach(String cccd, String hoTen, String maVe) {
        this.cccd = cccd;
        this.hoTen = hoTen;
        this.maVe = maVe;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    @Override
    public String toString() {
        return "hanhKhach{" + "cccd: " + cccd + ", hoTen: " + hoTen + ", maVe: " + maVe + '}';
    }

}
