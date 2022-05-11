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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zLittleMasterz
 */
public class GIAOVIEN {
    
    private String tenLogin;
    private String matKhau;
    private String coSo;
    private String maGV;
    private String role;
    private String hoTen;
    private String ho;
    private String ten;
    private String hocVi;
    private String maKhoa;

    public GIAOVIEN() {
    }

    public GIAOVIEN(String maGV,String ho, String ten, String hocVi, String maKhoa,String coSo) {
        this.coSo = coSo;
        this.maGV = maGV;
        this.ho = ho;
        this.ten = ten;
        this.hocVi = hocVi;
        this.maKhoa = maKhoa;
    }

    
    public GIAOVIEN(String tenUser, String password, String tenServer) throws SQLException {
        Connection login = JDBCCONNECT.getConnection(tenUser, password, tenServer);
        String sql = "EXEC SP_LAY_TT_DANGNHAP_GV '"+tenUser+"'";
        try {
            PreparedStatement statement = login.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                this.maGV = rs.getString(1);
                this.role = rs.getString(2);
                this.ho = rs.getString(3);
                this.ten = rs.getString(4);
                this.hocVi = rs.getString(5);
                this.maKhoa = rs.getString(6);
                this.hoTen = this.ho+" "+this.ten;
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: lỗi kết nối tới cơ sở dữ liệu GIAOVIEN.<init>()");
        }
        this.tenLogin = tenUser;
        this.matKhau = password;
        this.coSo = tenServer;
        login.close();
    }

    public void layTtDangNhapGv() {
        try {
            Connection login = JDBCCONNECT.getConnection(tenLogin, matKhau, coSo);
            String sql = "EXEC SP_LAY_TT_DANGNHAP_GV '" + this.tenLogin + "'";
            PreparedStatement statement = login.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                this.maGV = rs.getString(1);
                this.role = rs.getString(2);
                this.ho = rs.getString(3);
                this.ten = rs.getString(4);
                this.hocVi = rs.getString(5);
                this.maKhoa = rs.getString(6);
                this.hoTen = this.ho + " " + this.ten;
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: lỗi kết nối tới cơ sở dữ liệu GIAOVIEN.<init>()");
        }
    }
    
    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTenLogin() {
        return tenLogin;
    }

    public void setTenLogin(String tenLogin) {
        this.tenLogin = tenLogin;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getCoSo() {
        return coSo;
    }

    public void setCoSo(String coSo) {
        this.coSo = coSo;
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
    
}
