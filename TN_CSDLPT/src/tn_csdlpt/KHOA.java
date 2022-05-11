/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

/**
 *
 * @author maima
 */
public class KHOA {
    String MaKhoa;
    String TenKhoa;
    String MaCS;

    public KHOA() {
    }

    public KHOA(String MaKhoa, String TenKhoa, String MaCS) {
        this.MaKhoa = MaKhoa;
        this.TenKhoa = TenKhoa;
        this.MaCS = MaCS;
    }

    public String getMaKhoa() {
        return MaKhoa;
    }

    public void setMaKhoa(String MaKhoa) {
        this.MaKhoa = MaKhoa;
    }

    public String getTenKhoa() {
        return TenKhoa;
    }

    public void setTenKhoa(String TenKhoa) {
        this.TenKhoa = TenKhoa;
    }

    public String getMaCS() {
        return MaCS;
    }

    public void setMaCS(String MaCS) {
        this.MaCS = MaCS;
    }
    
    @Override
    public String toString(){
        return MaKhoa;
    }
}
