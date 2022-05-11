/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

import com.microsoft.sqlserver.jdbc.ISQLServerPreparedStatement;
import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author zLittleMasterz
 */
public class IODATA {

    public IODATA() {
    }
    
    public ArrayList<COSO> getDsCoso(){
        ArrayList<COSO> dsCoso = new ArrayList<>();
        Connection conn = JDBCCONNECT.getConnection();
        String sql = "SELECT * FROM V_DS_COSO";
   
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                COSO coSo= new COSO(rs.getString(1), rs.getString(2),rs.getString(3));
                dsCoso.add(coSo);
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: IODATA.getDsCoso()");
        }   
        return dsCoso;
    }
    
    public static ArrayList<MONHOC> getDsMonHoc(String userName,String passWord, String tenServer){
        try {
            ArrayList<MONHOC> dsMonhoc = new ArrayList<MONHOC>();
            Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
            String statement = "SELECT * FROM MONHOC";
            PreparedStatement ps = conn.prepareStatement(statement);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MONHOC monhoc = new MONHOC(rs.getString(1), rs.getString(2));
                dsMonhoc.add(monhoc);
            }
            
            return dsMonhoc;
        } catch (SQLException ex) {
            System.out.println("ERROR: IODATA.getDsMonHoc()");
        }
        return null;
    }
    
    public static ArrayList<LOP> getDsLop(String userName,String passWord, String tenServer,String link1){
        try {
            ArrayList<LOP> dsLop = new ArrayList<LOP>();
            Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
            String statement = "SELECT * FROM "+link1+"TN_CSDLPT.DBO.LOP";
            PreparedStatement ps = conn.prepareStatement(statement);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LOP lop = new LOP(rs.getString(1), rs.getString(2),rs.getString(3));
                dsLop.add(lop);
            }
            conn.close();
            return dsLop;
        } catch (SQLException ex) {
            System.out.println("ERROR: IODATA.getDsLop()");
        }
        return null;
    }
    
    public static ArrayList<KYTHI> getDsKythi(String userName,String passWord, String tenServer,String link1){
        try {
            ArrayList<KYTHI> dsKythi = new ArrayList<KYTHI>();
            Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
            String statement = "EXEC "+link1+"TN_CSDLPT.DBO.SP_LAY_DS_GIAOVIENDANGKY";
            PreparedStatement ps = conn.prepareStatement(statement);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KYTHI kyThi = new KYTHI(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getInt(10));
                dsKythi.add(kyThi);
            } 
            conn.close();
            return dsKythi;
        } catch (SQLException ex) {
            System.out.println("ERROR: IODATA.getDsKythi()");
        }
        return null;
    }
    
    public static ArrayList<KYTHI> getDsKythiDangDienRa(String tenServer,String maSv){
        try {
            ArrayList<KYTHI> dsKythi = new ArrayList<KYTHI>();
            Connection conn = JDBCCONNECT.getConnection(SINHVIEN.getTenUser(), SINHVIEN.getMatKhau(), tenServer);
            String statement = "EXEC SP_LAY_DS_KYTHI_SV ?";
            PreparedStatement ps = conn.prepareStatement(statement);
            ps.setString(1, maSv);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KYTHI kyThi = new KYTHI(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        "",
                        "",
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8));
                dsKythi.add(kyThi);
            } 
            conn.close();
            return dsKythi;
        } catch (SQLException ex) {
            System.out.println("ERROR: IODATA.getDsKythi()");
        }
        return null;
    }
    
    public static ArrayList<KYTHI> getDsKythiSapToi(String tenServer,String malop){
        try {
            ArrayList<KYTHI> dsKythi = new ArrayList<KYTHI>();
            Connection conn = JDBCCONNECT.getConnection(SINHVIEN.getTenUser(), SINHVIEN.getMatKhau(), tenServer);
            String statement = "EXEC SP_LAYDSKYTHI_SAPTOI ?";
            PreparedStatement ps = conn.prepareStatement(statement);
            ps.setString(1, malop);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KYTHI kyThi = new KYTHI(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        "",
                        "",
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8));
                dsKythi.add(kyThi);
            } 
            conn.close();
            return dsKythi;
        } catch (SQLException ex) {
            System.out.println("ERROR: IODATA.getDsKythiSapToi()");
        }
        return null;
    }
    
    public static ArrayList<SINHVIEN> getDsSvTheoLop(String username, String password, String tenServer, String maLop,String link1){
        try {
            ArrayList<SINHVIEN> dsSvTheoLop = new ArrayList<SINHVIEN>();
            Connection conn = JDBCCONNECT.getConnection(username, password, tenServer);
            String sql = "EXEC "+link1+"TN_CSDLPT.DBO.SP_LAYSINHVIEN " + "'" + maLop + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SINHVIEN sv = new SINHVIEN();
                sv.setMaSV(rs.getString(1));
                sv.setHo(rs.getString(2));
                sv.setTen(rs.getString(3));
                sv.setNgaySinh(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate(4)));
                sv.setDiaChi(rs.getString(5));
                sv.setMaLop(rs.getString(6));
                sv.setMatKhauSV(rs.getString(7));
                
                dsSvTheoLop.add(sv);
            }
            conn.close();
            return dsSvTheoLop;
        } catch (SQLException ex) {
            System.out.println("ERROR: lỗi IODATA.getDsSvTheoLop()");
            System.out.println(ex);
        }
        return null;
    }
    
    public static ArrayList<LOP> getDSLopTheoKhoa(String username, String password, String tenServer, String maKhoa,String link1){
        try {
            ArrayList<LOP> dsLopTheoKhoa = new ArrayList<LOP>();
            Connection conn = JDBCCONNECT.getConnection(username, password, tenServer);
            String sql = "exec "+link1+"TN_CSDLPT.DBO.SP_LAYDSLOPTHEOKHOA '"+maKhoa+"'";;
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LOP lopTheoKhoa = new LOP(rs.getString(1), rs.getString(2),rs.getString(3));
                dsLopTheoKhoa.add(lopTheoKhoa);
            }
            conn.close();
            return  dsLopTheoKhoa;
        } catch (SQLException ex) {
            System.out.println("ERROR: IODATA.getDsLoptheo khoa() from iodata");
            System.out.println(ex);
        }
        return null;
    }
    
    public static ArrayList<KHOA> getDSKhoa(String userName,String passWord, String tenServer, String link1) {
       try {
            ArrayList<KHOA> dsKhoa = new ArrayList<KHOA>();
            Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
            String statement = "SELECT * FROM "+link1+"TN_CSDLPT.DBO.KHOA";
            PreparedStatement ps = conn.prepareStatement(statement);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KHOA khoa = new KHOA(rs.getString(1), rs.getString(2),rs.getString(3));
                System.out.println(rs.getString(1));
                dsKhoa.add(khoa);
            }
            conn.close();
            return dsKhoa;
        } catch (SQLException ex) {
            System.out.println("ERROR: IODATA.getDsKhoa() from iodata");
                        System.out.println(ex);

        }
        return null;
    }
    
    public static ArrayList<GIAOVIEN> getDsGiaoVien(String userName,String passWord, String tenServer,String link1){
        try {
            ArrayList<GIAOVIEN> dsGv = new ArrayList<GIAOVIEN>();
            Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
            String statement = "EXEC "+link1+"TN_CSDLPT.DBO.SP_LAY_DS_GIAOVIEN";
            PreparedStatement ps = conn.prepareStatement(statement);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GIAOVIEN gv = new GIAOVIEN(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(7));
                dsGv.add(gv);
            } 
            conn.close();
            return dsGv;
        } catch (SQLException ex) {
            System.out.println("ERROR: IODATA.getDsGiaoVien()");
        }
        return null;
    }
    
    public static ArrayList<CAUHOICARD> getDeThi(String userName,String passWord, String tenServer,String maMH,String trinhDo, int socau){
        try {
            ArrayList<CAUHOICARD> dsCauHoi = new ArrayList<CAUHOICARD>();
            Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
            String statement = "EXEC SP_LAYDETHI ?,?,?";
            PreparedStatement ps = conn.prepareStatement(statement);
            ps.setString(1, maMH);
            ps.setString(2, trinhDo);
            ps.setInt(3, socau);
            int i =1;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CAUHOICARD cauHoi = new CAUHOICARD(rs.getInt(1),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
                cauHoi.setLblCauSo(i++);
                dsCauHoi.add(cauHoi);
            } 
            conn.close();
            return dsCauHoi;
        } catch (SQLException ex) {
            System.out.println("ERROR: Lỗi lấy đề thi! IODATA.getDeThi()");
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public static ArrayList<DIEMTHI> getBangDiemSv(SINHVIEN sinhvien){
        try {
            ArrayList<DIEMTHI> bangDiemSv = new ArrayList<DIEMTHI>();
            Connection conn = JDBCCONNECT.getConnection(SINHVIEN.getTenUser(), SINHVIEN.getMatKhau(), sinhvien.getCoSo());
            String statement = "EXEC SP_LAYBANGDIEM_SV ?";
            PreparedStatement ps = conn.prepareStatement(statement);
            ps.setString(1, sinhvien.getMaSV());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DIEMTHI diemThi = new DIEMTHI(rs.getString(1), 
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getInt(7));
                bangDiemSv.add(diemThi);
            }
            
            return bangDiemSv;
        } catch (SQLException ex) {
            System.out.println("ERROR: IODATA.getDsMonHoc()");
        }
        return null;
    }
    
    public static String[] layTtLogin(String userName, String passWord,String tenServer, String maGv){
        try {
            Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
            String sql = "EXEC SP_TT_LOGIN ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maGv);
            
            ResultSet rs = ps.executeQuery();
            String[] ttgv = new String[2];
            if (rs.next()) {
                ttgv[0] = rs.getString(1);
                ttgv[1] = rs.getString(2);
                return ttgv;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IODATA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void dangKy_kyThi(GIAOVIEN gv, KYTHI kythi) throws SQLException{
        Connection conn = null;
        try {
            conn = JDBCCONNECT.getConnection(gv.getTenLogin(), gv.getMatKhau(),gv.getCoSo());
        } catch (SQLException ex) {
            System.out.println("ERROR: lỗi kết nối csdl " + gv.getCoSo() + " GIAOVIEN.testlayDeThi()");;
        }
        String statement = "EXEC SP_LAYDETHI ?,?,?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(statement);
            ps.setString(1, kythi.getMaMH());
            ps.setString(2, kythi.getTrinhDo());
            ps.setInt(3, kythi.getSoCau());
        } catch (SQLException ex) {
            System.out.println("ERROR: PrepareStatement statement failed GIAOVIEN.testlayDeThi()");;
        }
        ResultSet rs = ps.executeQuery();

        String statement2 = "EXEC SP_DANGKY_KYTHI ?,?,?,?,?,?,?,?";
        PreparedStatement ps2 = null;
        try {
            ps2 = conn.prepareStatement(statement2);
            ps2.setString(1, gv.getMaGV());
            ps2.setString(2, kythi.getMaMH());
            ps2.setString(3, kythi.getMaLop());
            ps2.setString(4, kythi.getTrinhDo());
            ps2.setString(5, formatDate(kythi.getNgayThi()));
            ps2.setInt(6, kythi.getLan());
            ps2.setInt(7, kythi.getSoCau());
            ps2.setInt(8, kythi.getTg());
        } catch (SQLException ex) {
            System.out.println("ERROR: PrepareStatement statement2 failed GIAOVIEN.testlayDeThi()");;
        }
        ps2.execute();
    }

    public static void sua_kyThi(GIAOVIEN gv, KYTHI kythi) throws SQLException{
        Connection conn = null;
        try {
            conn = JDBCCONNECT.getConnection(gv.getTenLogin(), gv.getMatKhau(),gv.getCoSo());
        } catch (SQLException ex) {
            System.out.println("ERROR: lỗi kết nối csdl " + gv.getCoSo() + " GIAOVIEN.testlayDeThi()");;
        }
        String statement = "EXEC SP_LAYDETHI ?,?,?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(statement);
            ps.setString(1, kythi.getMaMH());
            ps.setString(2, kythi.getTrinhDo());
            ps.setInt(3, kythi.getSoCau());
        } catch (SQLException ex) {
            System.out.println("ERROR: PrepareStatement statement failed GIAOVIEN.testlayDeThi()");;
        }
        ResultSet rs = ps.executeQuery();

        String statement2 = "EXEC SP_SUA_KYTHI ?,?,?,?,?,?,?";
        PreparedStatement ps2 = null;
        try {
            ps2 = conn.prepareStatement(statement2);
            ps2.setString(1, kythi.getMaMH());
            ps2.setString(2, kythi.getMaLop());
            ps2.setInt(3, kythi.getLan());
            ps2.setString(4, kythi.getTrinhDo());
            ps2.setString(5, formatDate(kythi.getNgayThi()));
            ps2.setInt(6, kythi.getSoCau());
            ps2.setInt(7, kythi.getTg());
        } catch (SQLException ex) {
            System.out.println("ERROR: PrepareStatement statement2 failed GIAOVIEN.testlayDeThi()");;
        }
        ps2.execute();
    }
    
    public static void suaGv(GIAOVIEN gv, String ho, String ten, String hocVi) throws SQLException{
        Connection conn = null;
        conn = JDBCCONNECT.getConnection(gv.getTenLogin(), gv.getMatKhau(),gv.getCoSo());
        String statement = "EXEC SP_SUAGV ?,?,?,?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(statement);
            ps.setString(1, gv.getMaGV());
            ps.setString(2, ho);
            ps.setString(3, ten);
            ps.setString(4, hocVi);
        } catch (SQLException ex) {
            System.out.println("ERROR: lỗi IODATA.suaGv()");
        }
        int rs = ps.executeUpdate();
    }
    
    public static int nopBaiThi(SINHVIEN svThi, String maMh, int lan, double diem, Date ngayThi, ArrayList<CAUHOICARD> baiThi) {
        //while (true) {
            try {
                Connection conn = JDBCCONNECT.getConnection(SINHVIEN.getTenUser(), SINHVIEN.getMatKhau(), svThi.getCoSo());
                String sql = "EXEC SP_NOPBAI ?,?,?,?,?,?";

                SQLServerDataTable baiThi_type = new SQLServerDataTable();
                baiThi_type.addColumnMetadata("CAUSO", Types.INTEGER);
                baiThi_type.addColumnMetadata("NOIDUNG", Types.LONGNVARCHAR);
                baiThi_type.addColumnMetadata("A", Types.LONGNVARCHAR);
                baiThi_type.addColumnMetadata("B", Types.LONGNVARCHAR);
                baiThi_type.addColumnMetadata("C", Types.LONGNVARCHAR);
                baiThi_type.addColumnMetadata("D", Types.LONGNVARCHAR);
                baiThi_type.addColumnMetadata("DAPAN", Types.NCHAR);
                baiThi_type.addColumnMetadata("DACHON", Types.NCHAR);
                for (CAUHOICARD cauHoi : baiThi) {
                    baiThi_type.addRow(cauHoi.getSttCauHoi(),
                            cauHoi.getNoiDung(),
                            cauHoi.getTxtA(),
                            cauHoi.getTxtB(),
                            cauHoi.getTxtC(),
                            cauHoi.getTxtD(),
                            cauHoi.getDapAn(),
                            cauHoi.getDaChon());
                }

                SQLServerCallableStatement cs = (SQLServerCallableStatement) conn.prepareCall(sql);
                cs.setString(1, svThi.getMaSV());
                cs.setString(2, maMh);
                cs.setInt(3, lan);
                cs.setString(4, formatDate(ngayThi));
                cs.setString(5, String.valueOf((double)Math.round(diem*10)/10));
                cs.setStructured(6, "dbo.BAITHI_TYPE", baiThi_type);

                ResultSet rs = cs.executeQuery();
                int maBt = 0;
                if(rs.next()){
                    maBt = rs.getInt(1);
                }
                conn.close();
                return maBt;
            } catch (SQLException ex) {
                System.out.println("ERRORS: lỗi nộp bài IODATA.nopBaiThi()");
                System.out.println( ex.getMessage());
            }
        return 0;
    }
    
    public  static void themGiaoVien(String userName, String passWord,String tenServer,GIAOVIEN newGv) throws SQLException{
        Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
        String sql = "EXEC SP_THEMGIAOVIEN ?,?,?,?,?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, newGv.getMaGV());
        ps.setString(2, newGv.getHo());
        ps.setString(3, newGv.getTen());
        ps.setString(4, newGv.getHocVi());
        ps.setString(5, newGv.getMaKhoa());

        int rs = ps.executeUpdate();
    }
    
    public static int taoTaiKhoan(String userName, String passWord,String tenServer,
                                  String newLoginName, String newPassword, String newUsername, String newRole) throws SQLException{
        Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
        String sql = "{? = CALL SP_TAOTAIKHOAN(?,?,?,?)}";
        SQLServerCallableStatement ps = (SQLServerCallableStatement) conn.prepareCall(sql);
        ps.registerOutParameter(1, java.sql.Types.INTEGER);
        ps.setString(2, newLoginName);
        ps.setString(3, newPassword);
        ps.setString(4, newUsername);
        ps.setString(5, newRole);

        ps.execute();
        
        int isSuccess = ps.getInt(1);
        System.out.println("SUCCESS : "+isSuccess);
        return isSuccess;
    }
    
    public  static void xoaGiaoVien(String userName, String passWord,String tenServer,String maGv) throws SQLException{
        Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
        String sql = "EXEC SP_XOAGV ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, maGv);

        int rs = ps.executeUpdate();
    }

    public static void themMonHoc(String userName, String passWord, String tenServer, MONHOC mh) throws SQLException {
        Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
        String sql = "EXEC SP_THEMMONHOC ?,?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, mh.getMaMh());
        ps.setString(2, mh.getTenMh());

        ps.executeUpdate();
    }

    public static void suaMonHoc(String userName, String passWord, String tenServer, MONHOC mh) throws SQLException {
        String sql = "EXEC SP_SUAMONHOC ?,?";

        Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, mh.getMaMh());
        ps.setString(2, mh.getTenMh());

        ps.executeUpdate();
    }

    public static int ghiBoDe(GIAOVIEN gv, ArrayList<CAUHOI> boDeGv) {
        try {
            Connection conn = JDBCCONNECT.getConnection(gv.getTenLogin(), gv.getMatKhau(), gv.getCoSo());
            String sql = "EXEC SP_GHIBODE ?";

            SQLServerDataTable boDe_type = new SQLServerDataTable();
            boDe_type.addColumnMetadata("CAUHOI", Types.INTEGER);
            boDe_type.addColumnMetadata("MAMH", Types.NCHAR);
            boDe_type.addColumnMetadata("TRINHDO", Types.NCHAR);
            boDe_type.addColumnMetadata("NOIDUNG", Types.NVARCHAR);
            boDe_type.addColumnMetadata("A", Types.NVARCHAR);
            boDe_type.addColumnMetadata("B", Types.NVARCHAR);
            boDe_type.addColumnMetadata("C", Types.NVARCHAR);
            boDe_type.addColumnMetadata("D", Types.LONGNVARCHAR);
            boDe_type.addColumnMetadata("DAPAN", Types.NCHAR);
            boDe_type.addColumnMetadata("MAGV", Types.NCHAR);
            for (CAUHOI cauHoi : boDeGv) {
                boDe_type.addRow(cauHoi.getSttCauHoi(),
                        cauHoi.getMaMh(),
                        cauHoi.getTrinhDo(),
                        cauHoi.getNoiDung(),
                        cauHoi.getTxtA(),
                        cauHoi.getTxtB(),
                        cauHoi.getTxtC(),
                        cauHoi.getTxtD(),
                        cauHoi.getDapAn(),
                        cauHoi.getMaGv());
            }

            SQLServerCallableStatement cs = (SQLServerCallableStatement) conn.prepareCall(sql);
            cs.setStructured(1, "dbo.BODE_TYPE", boDe_type);

            cs.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERRORS: lỗi ghi bộ đề IODATA.ghiBoDe()");
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public static int ghiDsLop(GIAOVIEN gv, ArrayList<LOP> dsLop) {
        try {
            Connection conn = JDBCCONNECT.getConnection(gv.getTenLogin(), gv.getMatKhau(), gv.getCoSo());
            String sql = "EXEC SP_GHIDSLOP ?";
            
            SQLServerDataTable lop_type = new SQLServerDataTable();
            lop_type.addColumnMetadata("MALOP", Types.NCHAR);
            lop_type.addColumnMetadata("TENLOP", Types.NVARCHAR);
            lop_type.addColumnMetadata("MAKH", Types.NCHAR);
            for (LOP lop : dsLop) {
                lop_type.addRow(lop.getMaLop(),
                        lop.getTenLop(),
                        lop.getMaKhoa());
            }

            SQLServerCallableStatement cs = (SQLServerCallableStatement) conn.prepareCall(sql);
            cs.setStructured(1, "dbo.LOP_TYPE", lop_type);

            cs.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERRORS: lỗi ghi danh sách lớp IODATA.ghiDsLop()");
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public static boolean ktDoiMonCauHoi(GIAOVIEN gv, String maMh) {
        try {
            Connection conn = JDBCCONNECT.getConnection(gv.getTenLogin(), gv.getMatKhau(), gv.getCoSo());
            String sql = "EXEC SP_KT_DOIMONCAUHOI ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maMh);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                return false;
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERRORS: Lỗi kiểm tra đổi môn học câu hỏi IODATA.ktDoiMonCauHoi()");
            System.out.println(ex.getMessage());
        }
        return true;
    }
    
    public static Date formatDate(String dateString){
        SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sqlDateFormat.parse(dateString);
            return date;
        } catch (ParseException ex) {
            System.out.println("ERROR: lỗi đổi ngày giờ sql -> util.Date IODATA.formatDate()");
        }
        return null;
    }
  
    public static String formatDate(Date date){
        SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sqlDate = sqlDateFormat.format(date);
        return sqlDate;
    }
    
    public static void themBoDe(String userName, String passWord, String tenServer, String maMH,
                                String trinhDo, String noiDung, String A, String B, String C, 
                                String D, String dapAn, String maGV) throws SQLException {
        String sql = "EXEC SP_THEMBODE ?,?,?,?,?,?,?,?,?";
        Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maMH);
        ps.setString(2, trinhDo);
        ps.setString(3, noiDung);
        ps.setString(4, A);
        ps.setString(5, B);
        ps.setString(6, C);
        ps.setString(7, D);
        ps.setString(8, dapAn);
        ps.setString(9, maGV);
        ps.executeUpdate();
    }

    public static void suaBoDe(String userName, String passWord, String tenServer, String maMH,
                                String trinhDo, String noiDung, String A, String B, String C, 
                                String D, String dapAn, int cauHoi) throws SQLException {
        String sql = "EXEC SP_SUABODE ?,?,?,?,?,?,?,?,?";

        Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(9, cauHoi);
        ps.setString(1, maMH);
        ps.setString(2, trinhDo);
        ps.setString(3, noiDung);
        ps.setString(4, A);
        ps.setString(5, B);
        ps.setString(6, C);
        ps.setString(7, D);
        ps.setString(8, dapAn);
        ps.executeUpdate();
    }
    
    public static void themSinhVien(String userName, String passWord, String tenServer, SINHVIEN sv) throws SQLException{
        String sql = "{Call  dbo.SP_THEMSINHVIEN (?,?,?,?,?,?,?)}";
        
        CallableStatement ps;
            Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
            ps = conn.prepareCall(sql);
            ps.setString(1, sv.getMaSV());
            ps.setString(2, sv.getHo());
            ps.setString(3, sv.getTen());
            ps.setString(4, sv.getNgaySinh());
            ps.setString(5, sv.getDiaChi());
            ps.setString(6, sv.getMaLop());
            ps.setString(7, sv.getMatKhau());
            ps.executeUpdate();
            
       
    }
    
        public static void suaSinhVien(String userName, String passWord, String tenServer, SINHVIEN sv) throws SQLException{
        String sql = "EXEC SP_SUASINHVIEN ?,?,?,?,?,?,?";
        
        PreparedStatement ps;
            Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
            ps = conn.prepareStatement(sql);
            ps.setString(7, sv.getMaSV());
            ps.setString(1, sv.getHo());
            ps.setString(2, sv.getTen());
            ps.setString(3, sv.getNgaySinh());
            ps.setString(4, sv.getDiaChi());
            ps.setString(5, sv.getMaLop());
            ps.setString(6, sv.getMatKhauSV());
            ps.executeUpdate();
            
       
    }
    
    public static void xemReportBaiThi(String userName, String passWord, String tenServer, int maBt,
                                       String maLop, String hoTenSv, String monThi,String ngayThi,
                                       double diem,int lan,String link1) throws SQLException {
        
        Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
        try {
            JasperDesign rpDesign = JRXmlLoader.load("C:\\Users\\zLittleMasterz\\Documents\\NetBeansProjects\\TN_CSDLPT\\src\\reports\\rpBaiThiSv.jrxml");
            
            /*JRDesignQuery query = new JRDesignQuery();
            query.setText("EXEC SP_BAOCAO_BAITHI '"+maBt+"'");
            rpDesign.setQuery(query);*/
            
            JasperReport rpBaiThisv = JasperCompileManager.compileReport(rpDesign);
            Map<String,Object> parameters = new HashMap<>();
            parameters.put("LOP", maLop);
            parameters.put("HOTEN", hoTenSv);
            parameters.put("MONHOC", monThi);
            parameters.put("NGAYTHI", ngayThi);
            parameters.put("DIEM", diem);
            parameters.put("LAN", lan);
            parameters.put("MABT", maBt);
            parameters.put("LINK1", link1);
            
            JasperPrint rpPrint = JasperFillManager.fillReport(rpBaiThisv, parameters, conn);
            JasperViewer.viewReport(rpPrint, false);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
    public static void xemReportBangDiemKt(String userName, String passWord, String tenServer,
            String maLop, String maMh, int lan,String tenMh,String link1) throws SQLException {
        
        Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
        try {
            JasperDesign rpDesign = JRXmlLoader.load("C:\\Users\\zLittleMasterz\\Documents\\NetBeansProjects\\TN_CSDLPT\\src\\reports\\rpBangDiemKT.jrxml");
            
            /*JRDesignQuery query = new JRDesignQuery();
            query.setText("EXEC SP_BAOCAO_BAITHI '"+maBt+"'");
            rpDesign.setQuery(query);*/
            
            JasperReport rpBaiThisv = JasperCompileManager.compileReport(rpDesign);
            Map<String,Object> parameters = new HashMap<>();
            parameters.put("MAMH", maMh);
            parameters.put("LAN", lan);
            parameters.put("MALOP", maLop);
            parameters.put("TENMH", tenMh);
            parameters.put("LINK1", link1);
            
            JasperPrint rpPrint = JasperFillManager.fillReport(rpBaiThisv, parameters, conn);
            JasperViewer.viewReport(rpPrint, false);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
    public static void xemReportDsKyThiTheoNgay(String userName, String passWord, String tenServer,
                                                Date tuNgay, Date denNgay,String coso, String link1) throws SQLException {
        
        Connection conn = JDBCCONNECT.getConnection(userName, passWord, tenServer);
        System.out.println(tenServer);
        try {
            JasperDesign rpDesign = JRXmlLoader.load("C:\\Users\\zLittleMasterz\\Documents\\NetBeansProjects\\TN_CSDLPT\\src\\reports\\rpDsDangKyThiTN.jrxml");
            
            /*JRDesignQuery query = new JRDesignQuery();
            query.setText("EXEC SP_BAOCAO_BAITHI '"+maBt+"'");
            rpDesign.setQuery(query);*/
            
            JasperReport rpDsKyThi = JasperCompileManager.compileReport(rpDesign);
            Map<String,Object> parameters = new HashMap<>();
            parameters.put("TUNGAY", tuNgay);
            parameters.put("DENNGAY", denNgay);
            parameters.put("COSO", coso);
            parameters.put("LINK1", link1);
            
            JasperPrint rpPrint = JasperFillManager.fillReport(rpDsKyThi, parameters, conn);
            JasperViewer.viewReport(rpPrint, false);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }   
    }
}
