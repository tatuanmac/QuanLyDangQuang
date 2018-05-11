package dev.tatuan.qldh2.UploadNewItem;

/**
 * Created by tatuan on 30/04/2018.
 */

public class ObjectUpload {
    String id;
    String linkHA;
    String tenDH;
    String giaDH;
    String loaiDH;
    String matKinh;
    String xuatXu;
    String nangLuong;

    public String getLinkHA() {
        return linkHA;
    }

    public void setLinkHA(String linkHA) {
        this.linkHA = linkHA;
    }

    public String getTenDH() {
        return tenDH;
    }

    public void setTenDH(String tenDH) {
        this.tenDH = tenDH;
    }

    public String getGiaDH() {
        return giaDH;
    }

    public void setGiaDH(String giaDH) {
        this.giaDH = giaDH;
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

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getNangLuong() {
        return nangLuong;
    }

    public void setNangLuong(String nangLuong) {
        this.nangLuong = nangLuong;
    }

    public ObjectUpload() {
    }

    public ObjectUpload(String id, String linkHA, String tenDH, String giaDH, String loaiDH, String matKinh, String xuatXu, String nangLuong) {

        this.id = id;
        this.linkHA = linkHA;
        this.tenDH = tenDH;
        this.giaDH = giaDH;
        this.loaiDH = loaiDH;
        this.matKinh = matKinh;
        this.xuatXu = xuatXu;
        this.nangLuong = nangLuong;
    }
}
