package dev.tatuan.qldh2.DonHang;

/**
 * Created by tatuan on 25/04/2018.
 */

public class UserData1 {

    String sodienthoai ,diachi, ten;
    public UserData1() {
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public UserData1(String sodienthoai, String diachi, String ten) {
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.ten = ten;
    }
}
