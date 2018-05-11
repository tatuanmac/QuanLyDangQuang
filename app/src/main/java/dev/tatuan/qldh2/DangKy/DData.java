package dev.tatuan.qldh2.DangKy;

/**
 * Created by tatuan on 17/04/2018.
 */

public class DData {
    String sodienthoai, matkhau, ten;

    public DData(String sodienthoai, String matkhau, String ten) {
        this.sodienthoai = sodienthoai;
        this.matkhau = matkhau;
        this.ten = ten;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public DData() {

    }

}
