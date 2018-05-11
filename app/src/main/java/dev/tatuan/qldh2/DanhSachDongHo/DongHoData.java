package dev.tatuan.qldh2.DanhSachDongHo;

/**
 * Created by tatuan on 26/04/2018.
 */

public class DongHoData {
    String giaDH, id, linkHA, loaiDH, matKinh, nangLuong, tenDH, xuatXu;

    public DongHoData() {
    }

    public String getGiaDH() {
        return giaDH;
    }

    public void setGiaDH(String giaDH) {
        this.giaDH = giaDH;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinkHA() {
        return linkHA;
    }

    public void setLinkHA(String linkHA) {
        this.linkHA = linkHA;
    }

    public String getLoaiDH() {
        return loaiDH;
    }

    public void setLoaiDH(String loaiDH) {
        this.loaiDH = loaiDH;
    }

    public String getMatKinh() {
        return matKinh;
    }

    public void setMatKinh(String matKinh) {
        this.matKinh = matKinh;
    }

    public String getNangLuong() {
        return nangLuong;
    }

    public void setNangLuong(String nangLuong) {
        this.nangLuong = nangLuong;
    }

    public String getTenDH() {
        return tenDH;
    }

    public void setTenDH(String tenDH) {
        this.tenDH = tenDH;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public DongHoData(String giaDH, String id, String linkHA, String loaiDH, String matKinh, String nangLuong, String tenDH, String xuatXu) {
        this.giaDH = giaDH;
        this.id = id;
        this.linkHA = linkHA;
        this.loaiDH = loaiDH;
        this.matKinh = matKinh;
        this.nangLuong = nangLuong;
        this.tenDH = tenDH;
        this.xuatXu = xuatXu;
    }
}
