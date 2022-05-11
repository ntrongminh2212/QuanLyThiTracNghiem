/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zLittleMasterz
 */
public class SINHVIEN {
    private static String tenUser = "usersv";
    private static String matKhau = "123";
    private String coSo;
    private String maSV;
    private String hoTen;
    private String ho;
    private String ten;
    private String ngaySinh;
    private String diaChi;
    private String maLop;
    private String tenLop;
    private KHOA khoa;
    private String matKhauSV;

    public SINHVIEN() {
    }
    
    public SINHVIEN(String maSV, String matkhauSV, String tenServer) throws SQLException {
        Connection conn = null;
        try {
            conn = JDBCCONNECT.getConnection(tenUser, matKhau, tenServer);
        } catch (SQLException ex) {
            System.out.println("ERROR: lỗi kết nối tới cơ sở dữ liệu SINHVIEN.<init>()");
            System.out.println(ex.getMessage());
        }
        String sql = "EXEC SP_LAY_TT_DANGNHAP_SV ?,?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maSV);
        ps.setString(2, matkhauSV);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            this.maSV = rs.getString(1);
            this.ho = rs.getString(2);
            this.ten = rs.getString(3);
            this.ngaySinh = rs.getString(4);
            this.diaChi = rs.getString(5);
            this.maLop = rs.getString(6);
            this.tenLop = rs.getString(7);
            this.khoa = new KHOA();
            this.khoa.setMaKhoa(rs.getString(8));
            this.khoa.setTenKhoa(rs.getString(9));
            this.matKhauSV = rs.getString(10);
            this.hoTen = this.ho+" "+this.ten;
        }
        this.coSo = tenServer;
    }

    public void layTtSinhVien(){
        try {
            Connection conn = null;
            conn = JDBCCONNECT.getConnection(tenUser, matKhau, coSo);
            String sql = "EXEC SP_LAY_TT_DANGNHAP_SV ?,?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSV);
            ps.setString(2, matKhauSV);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.maSV = rs.getString(1);
                this.ho = rs.getString(2);
                this.ten = rs.getString(3);
                this.ngaySinh = rs.getString(4);
                this.diaChi = rs.getString(5);
                this.maLop = rs.getString(6);
                this.tenLop = rs.getString(7);
                this.khoa = new KHOA();
                this.khoa.setMaKhoa(rs.getString(8));
                this.khoa.setTenKhoa(rs.getString(9));
                this.matKhauSV = rs.getString(10);
                this.hoTen = this.ho+" "+this.ten;
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: "+ex.getMessage()+" SINHVIEN.layTtSinhVien()");
        }
    }
    
    public static String getTenUser() {
        return tenUser;
    }

    public static String getMatKhau() {
        return matKhau;
    }

    public String getCoSo() {
        return coSo;
    }

    public void setCoSo(String coSo) {
        this.coSo = coSo;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMatKhauSV() {
        return matKhauSV;
    }

    public void setMatKhauSV(String matKhauSV) {
        this.matKhauSV = matKhauSV;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
    
    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public KHOA getKhoa() {
        return khoa;
    }

    public void setKhoa(KHOA khoa) {
        this.khoa = khoa;
    }
 
    
}
