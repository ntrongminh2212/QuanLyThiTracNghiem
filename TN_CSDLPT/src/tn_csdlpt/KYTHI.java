/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zLittleMasterz
 */
public class KYTHI {
    
    private String maTimKiem;
    private String maMH;
    private String tenMH;
    private String maLop;
    private int lan;
    private String maGv;
    private String gvdk;
    private String trinhDo;
    private Date ngayThi;
    private int soCau;
    private int tg;

    public KYTHI() {
    }

    public KYTHI(String maMH,String tenMH, String maLop, int lan,String maGv,String gvdk, String trinhDo, String ngayThi, int soCau, int tg) {
        this.tenMH = tenMH;
        this.maLop = maLop;
        this.lan = lan;
        this.trinhDo = trinhDo;
        this.ngayThi = IODATA.formatDate(ngayThi);
        this.soCau = soCau;
        this.tg = tg;
        this.gvdk = gvdk;
        this.maMH = maMH;
        this.maGv = maGv;
        this.maTimKiem = maMH+tenMH+maLop+lan+maGv+gvdk+trinhDo+ngayThi;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public int getLan() {
        return lan;
    }

    public void setLan(int lan) {
        this.lan = lan;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public Date getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(Date ngayThi) {
        this.ngayThi = ngayThi;
    }

    public int getSoCau() {
        return soCau;
    }

    public void setSoCau(int soCau) {
        this.soCau = soCau;
    }

    public int getTg() {
        return tg;
    }

    public void setTg(int tg) {
        this.tg = tg;
    }   

    public String getGvdk() {
        return gvdk;
    }

    public void setGvdk(String gvdk) {
        this.gvdk = gvdk;
    }

    public String getMaTimKiem() {
        return maTimKiem;
    }

    public void setMaTimKiem() {
        this.maTimKiem = maMH+tenMH+maLop+lan+maGv+gvdk+trinhDo+ngayThi;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaGv() {
        return maGv;
    }

    public void setMaGv(String maGv) {
        this.maGv = maGv;
    }
    
    public String printOut(){
        return maTimKiem + soCau+tg;
    }
}
