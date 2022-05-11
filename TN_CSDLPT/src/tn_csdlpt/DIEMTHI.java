/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

import java.text.SimpleDateFormat;

/**
 *
 * @author zLittleMasterz
 */
public class DIEMTHI {
    private String maSv;
    private String maMh;
    private String tenMh;
    private int lan;
    private String ngayThi;
    private int maBt;
    private double diem;

    public DIEMTHI() {
    }

    public DIEMTHI(String maSv, String maMh,String tenMh, int lan, String ngayThi, double diem, int maBt) {
        this.maSv = maSv;
        this.maMh = maMh;
        this.lan = lan;
        this.ngayThi = new SimpleDateFormat("dd/MM/yyyy").format(IODATA.formatDate(ngayThi));
        this.maBt = maBt;
        this.diem = ((double)Math.round(diem*10)/10);
        this.tenMh = tenMh;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getMaMh() {
        return maMh;
    }

    public void setMaMh(String maMh) {
        this.maMh = maMh;
    }

    public int getLan() {
        return lan;
    }

    public void setLan(int lan) {
        this.lan = lan;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public int getMaBt() {
        return maBt;
    }

    public void setMaBt(int maBt) {
        this.maBt = maBt;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public String getTenMh() {
        return tenMh;
    }

    public void setTenMh(String tenMh) {
        this.tenMh = tenMh;
    }
    
}
