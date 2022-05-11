/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

/**
 *
 * @author zLittleMasterz
 */
class JDateChooserEditor extends DefaultCellEditor {

    JDateChooser date;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public JDateChooserEditor(JCheckBox jLabel, JDateChooser date) {
        super(jLabel);
        this.date = date;
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (!String.valueOf(value).isEmpty()) {
            String dateStr = String.valueOf(value);
            try {
                date.setDate(dateFormat.parse(dateStr));
            } catch (ParseException ex) {
                System.out.println("ERROR: " + ex.getMessage() + " JDateChooserEditor.getTableCellEditorComponent()");
            }
            return date;
        }
        return date;
    }

    public Object getCellEditorValue() {
        return new String(((JTextField) date.getDateEditor().getUiComponent()).getText());
    }

    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}

class JSpinnerDateEditor extends DefaultCellEditor {

    JSpinner spinner;
    SimpleDateFormat gioThiFormat = new SimpleDateFormat("HH:mm a");

    public JSpinnerDateEditor(JCheckBox checkBox) {
        super(checkBox);
        spinner = new JSpinner();
        spinner.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        Date gioThiDate = new Date();
        Date gioThiMax = new Date();
        Date gioThiMin = new Date();
        try {
            gioThiDate = gioThiFormat.parse("07:00 AM");
            gioThiMin = gioThiFormat.parse("07:00 AM");
            gioThiMax = gioThiFormat.parse("16:30 PM");
        } catch (Exception ex) {
            System.out.println("Lỗi hiển thị spnGioThi");
        }
        spinner.setModel(new SpinnerDateModel(gioThiDate, gioThiMin, gioThiMax, Calendar.HOUR_OF_DAY));
        JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "HH:mm a");
        spinner.setEditor(de);
        spinner.setPreferredSize(new java.awt.Dimension(30, 35));
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        try {
            System.out.println(String.valueOf(value));
            spinner.getModel().setValue(gioThiFormat.parse(String.valueOf(value)));
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage() + " JSpinnerDateEditor.getTableCellEditorComponent()");
        }
        return spinner;
    }

    public Object getCellEditorValue() {
        return (new SimpleDateFormat("HH:mm a")).format((Date) spinner.getValue());
    }

    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}

class JSpinnerEditor extends DefaultCellEditor {

    JSpinner spinner;
    String donVi;

    public JSpinnerEditor(JCheckBox checkBox, String donVi) {
        super(checkBox);
        this.donVi = donVi;
        spinner = new javax.swing.JSpinner();

        spinner.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        spinner.setModel(new javax.swing.SpinnerNumberModel(30, 1, null, 1));

        spinner.setPreferredSize(new java.awt.Dimension(30, 35));
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        try {
            int val = Integer.parseInt(String.valueOf(value).replaceAll(donVi, "").trim());
            System.out.println(val);
            spinner.setValue(val);
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage() + " JSpinnerEditor.getTableCellEditorComponent()");;
        }
        return spinner;
    }

    public Object getCellEditorValue() {
        return String.valueOf(spinner.getValue()) + donVi;
    }

    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}

class IconRenderer implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        try {
            return (Component) value;
        } catch (Exception e) {
        }
        return (new JLabel());
    }
}

public class MAINFORM extends javax.swing.JFrame {

    /**
     * Creates new form MAINFORM
     */
    private Color mainBttColor = new Color(80, 130, 217);
    private GIAOVIEN gvLogin;
    private SINHVIEN selectedSv = new SINHVIEN();
    private COSO coSo = new COSO();
    private ArrayList<MONHOC> dsMonHoc;
    private ArrayList<LOP> dsLop;
    private ArrayList<KYTHI> dsKyThi;
    private ArrayList<LOP> dsLopTheoKhoa;
    private ArrayList<KHOA> dsKhoa;
    private ArrayList<GIAOVIEN> dsGiaovien;
    private ArrayList<DIEMTHI> bangDiem;
    private ArrayList<CAUHOI> boDe;
    private SimpleDateFormat ngayFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat gioFormat = new SimpleDateFormat("HH:mm a");

    private DefaultTableModel dftblDsKyThi = new DefaultTableModel();
    private DefaultTableModel dftblDsCauHoi = new DefaultTableModel();
    private DefaultTableModel dftblDsGiaoVien = new DefaultTableModel();
    private DefaultTableModel dftblBangDiem = new DefaultTableModel();
    private DefaultTableModel dftblSinhVienLop = new DefaultTableModel();
    private DefaultTableModel dftblDsLop = new DefaultTableModel();
    private DefaultTableModel dftblMonHoc = new DefaultTableModel();
    String tiengViet = "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ\\s]";
    ArrayList<CAUHOICARD> dsCauHoi;
    THOIGIAN dongHo;
    private ArrayList<Integer> editDataRows = new ArrayList<>();
    private int mode = 0;
    private Stack dataTblStack = new Stack();
    private String GetMaLop;
    private String GetMaKhoa;
    private String link1;

    public MAINFORM(GIAOVIEN giaovien, ArrayList<COSO> dsCoso) {
        initComponents();

        setStartPanelsColor();
        this.gvLogin = giaovien;
        for (COSO coso : dsCoso) {
            cbxCoSoHienTai.addItem(coso);
            if (coso.getTenServer().compareToIgnoreCase(gvLogin.getCoSo()) == 0) {
                this.coSo = coso;
            }
        }
        cbxCoSoHienTai.setSelectedItem(this.coSo);

        if (giaovien.getRole().equals("GIANGVIEN")) {
            disableFunc(pnlHieuChinhSinhVien);
            disableFunc(pnlHieuChinhKyThi);
            disableFunc(pnlHieuChinhKhoa);
            disableFunc(pnlHieuChinhGV);
            disableFunc(pnlDoiCoSo);
            disableFunc(pnlThemGV);
            disableFunc(pnlHieuChinhMonHoc);

            tbDSKhoa.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Mã Khoa", "Tên Khoa", "Mã Cơ Sở"
                    }
            ) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            });

            tblDSLop.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        ".", "Mã Lớp", "Tên Lớp", "Mã Khoa"
                    }
            ) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            });

            bttReportBaiThi.setVisible(false);
            bttTaoLogin.setVisible(false);
            bttBaoCaoDsKyThi.setVisible(false);
        } else if (giaovien.getRole().equals("COSO")) {
            cbxCoSoHienTai.setEnabled(false);
            disableFunc(pnlDoiCoSo);
        } else if (giaovien.getRole().equals("TRUONG")) {
            bttThiThu.setVisible(false);
            disableFunc(pnlHieuChinhSinhVien);
            disableFunc(pnlHieuChinhKyThi);
            disableFunc(pnlHieuChinhKhoa);
            disableFunc(pnlBoDeFunc);
            disableFunc(pnlHieuChinhMonHoc);
            tbDSKhoa.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Mã Khoa", "Tên Khoa", "Mã Cơ Sở"
                    }
            ) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            });

            tblDSLop.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        ".", "Mã Lớp", "Tên Lớp", "Mã Khoa"
                    }
            ) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            });

            tblDsCauHoi.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        ".", "Câu Hỏi", "Mã MH", "Trình độ", "Nội dung", "A", "B", "C", "D", "ĐÁP ÁN", "MÃ GV"
                    }
            ) {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            });
            bttXoaGV.setVisible(false);
        }
        txtUserName.setText(this.gvLogin.getHoTen());

        dsMonHoc = IODATA.getDsMonHoc(giaovien.getTenLogin(), giaovien.getMatKhau(), giaovien.getCoSo());
        dsLop = IODATA.getDsLop(giaovien.getTenLogin(), giaovien.getMatKhau(), giaovien.getCoSo(), "");
        dsKhoa = IODATA.getDSKhoa(giaovien.getTenLogin(), giaovien.getMatKhau(), giaovien.getCoSo(), "");

        refreshEditorModels();

        dftblDsKyThi = (DefaultTableModel) tblDsKyThi.getModel();
        dftblDsCauHoi = (DefaultTableModel) tblDsCauHoi.getModel();
        dftblDsGiaoVien = (DefaultTableModel) tblDsGiaoVien.getModel();
        dftblBangDiem = (DefaultTableModel) tblBangDiem.getModel();
        dftblSinhVienLop = (DefaultTableModel) tblSinhVienLop.getModel();
        dftblDsLop = (DefaultTableModel) tblDSLop.getModel();
        dftblMonHoc = (DefaultTableModel) tblMonHoc.getModel();
        loadMonHoc();
        link1 = "";
        pnlBode.setEnabled(false);
        settingTable();
        loadCBMaLop_SINHVIEN();
        refreshpnlHoSoGiaoVien();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField6 = new javax.swing.JTextField();
        pnlDoiCoSo = new javax.swing.JPanel();
        pnlDoiCoSo.setVisible(false);
        jLabel57 = new javax.swing.JLabel();
        cbxCoSoHienTai = new javax.swing.JComboBox<>();
        pnlGD_LamViec = new javax.swing.JPanel();
        MainBtt = new javax.swing.JPanel();
        bttUser = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JLabel();
        bttKhoaLop = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        unlKhoaLop = new javax.swing.JPanel();
        bttGiaoVien = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        unlGiaoVien = new javax.swing.JPanel();
        bttBoDe = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        unlBoDe = new javax.swing.JPanel();
        bttKyThi = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        unlKyThi = new javax.swing.JPanel();
        bttSinhVien = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        unlSinhVien = new javax.swing.JPanel();
        bttDoiCoSo = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        bttMonHoc = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        unlMonHoc = new javax.swing.JPanel();
        pnlBode = new javax.swing.JPanel();
        pnlBode.setVisible(false);
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDsCauHoi = new javax.swing.JTable();
        pnlBoDeFunc = new javax.swing.JPanel();

        bttThemBD = new javax.swing.JButton();
        bttLuuBD = new javax.swing.JButton();
        bttHuyThayDoiBD = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        bttXoaBD = new javax.swing.JButton();
        bttPhucHoiBD = new javax.swing.JButton();
        txtTimKiemCH = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        pnlChiTietCauHoi = new javax.swing.JPanel();
        lblA = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();
        lblC = new javax.swing.JLabel();
        lblD = new javax.swing.JLabel();
        lblCauSo = new javax.swing.JLabel();
        lblNoiDung = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        pnlKhoaLop = new javax.swing.JPanel();
        pnlKhoaLop.setVisible(false);
        jPanel5 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDSLop = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbDSKhoa = new javax.swing.JTable();
        pnlHieuChinhKhoa = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        txtMaKh = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtTenKh = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        BtnDelete = new javax.swing.JButton();
        BtnUpdate = new javax.swing.JButton();
        lblMaKhoa = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        pnlGiaoVien = new javax.swing.JPanel();
        pnlGiaoVien.setVisible(false);
        pnlGiaoVienFunc = new javax.swing.JPanel();

        pnlHieuChinhGV = new javax.swing.JPanel();
        bttXoaGV = new javax.swing.JButton();
        bttThemGV = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        bttTaoLogin = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblDsGiaoVien = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        lblTenLoginGV = new javax.swing.JLabel();
        lblQuyenGV = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        pnlThemGV = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txtMaGv_GiaoVien = new javax.swing.JTextField();
        txtHoGv_GiaoVien = new javax.swing.JTextField();
        txtTenGv_GiaoVien = new javax.swing.JTextField();
        txtHocVi_GiaoVien = new javax.swing.JTextField();
        cbxKhoa_GiaoVien = new javax.swing.JComboBox<>();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        bttXacNhanThem = new javax.swing.JButton();
        pnlSinhVien = new javax.swing.JPanel();
        pnlSinhVien.setVisible(false);
        pnlHieuChinhSinhVien = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtMaSV_SINHVIEN = new javax.swing.JTextField();
        txtTen_SINHVIEN = new javax.swing.JTextField();
        txtHo_SINHNVIEN = new javax.swing.JTextField();
        txtDiaChi_SINHVIEN = new javax.swing.JTextField();
        btn_ThemSinhVien = new javax.swing.JButton();
        btn_SuaSinhVien = new javax.swing.JButton();
        btn_XoaSinhVien = new javax.swing.JButton();
        btn_LamMoiSinhVien = new javax.swing.JButton();
        txtMauKhau_SINHVIEN = new javax.swing.JPasswordField();
        txtNgaySinh_SINHVIEN = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBangDiem = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSinhVienLop = new javax.swing.JTable();
        jLabel58 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        cbxLop_SINHVIEN = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        bttReportBaiThi = new javax.swing.JButton();
        pnlKyThi = new javax.swing.JPanel();
        pnlKyThi.setVisible(false);
        pnlKyThiFunc = new javax.swing.JPanel();

        bttThiThu = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        bttBaoCaoDsKyThi = new javax.swing.JButton();
        pnlHieuChinhKyThi = new javax.swing.JPanel();
        bttThemKT = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        bttLuuKT = new javax.swing.JButton();
        bttSuaKT = new javax.swing.JButton();
        bttHuyThayDoiKT = new javax.swing.JButton();
        bttBaoCaoBangDiemKt = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDsKyThi = new javax.swing.JTable();
        jLabel67 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        pnlHoSoGiaoVien = new javax.swing.JPanel();
        pnlHoSoGiaoVien.setVisible(true);
        jPanel2 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txtHoGv_HoSo = new javax.swing.JTextField();
        txtHocVi_HoSo = new javax.swing.JTextField();
        lblMaGv_HoSo = new javax.swing.JLabel();
        bttLuuHoSo = new javax.swing.JButton();
        bttSuaHoSo = new javax.swing.JButton();
        lblKhoa_HoSo = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        lblCoSo_HoSo = new javax.swing.JLabel();
        lblTenLogin_HoSo = new javax.swing.JLabel();
        lblRole_HoSo = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtTenGv_HoSo = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        pnlMonHoc = new javax.swing.JPanel();
        pnlMonHoc.setVisible(false);
        pnlHieuChinhMonHoc = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        btnThem_MonHoc = new javax.swing.JButton();
        btnSua_MonHoc = new javax.swing.JButton();
        btnXoa_MonHoc = new javax.swing.JButton();
        btnLamMoi_MonHoc = new javax.swing.JButton();
        txtMaMH_MonHoc = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txtTenMH_MonHoc = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblMonHoc = new javax.swing.JTable();
        pnlThiTracNghiem = new javax.swing.JPanel();
        pnlThiTracNghiem.setVisible(false);
        pnlThongTinThiSinh = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        lblMonThi = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblThinhDoBaiThi = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblHoTenThiSinh = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblLopThiSinh = new javax.swing.JLabel();
        pnlThoiGian = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        lblTgConLai = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlBaiThi = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        bttNopBai = new javax.swing.JButton();
        pnlLuaChon = new javax.swing.JPanel();
        pnlEditorModel = new javax.swing.JPanel();
        pnlThemSuaKyThi = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxLopThi = new javax.swing.JComboBox<>();
        cbxDoKho = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbxMaMonThi = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        spnLanThi = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        spnSoCauThi = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        spnThoiGianThi = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        spnGioThi = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        dcNgayThi = new com.toedter.calendar.JDateChooser();
        cbxLan = new javax.swing.JComboBox<>();
        cbxTrinhDo = new javax.swing.JComboBox<>();
        cbxTenMonThi = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtCauHoi_BODE = new javax.swing.JTextField();
        txtMaGV_BODE = new javax.swing.JTextField();
        cbxMaMH_BODE = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txtCauHoi = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtA_BODE = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtB_BODE = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtC_BODE = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtD_BODE = new javax.swing.JTextField();
        cbxTrinhDo_BODE = new javax.swing.JComboBox<>();
        cbxDapAn_BODE = new javax.swing.JComboBox<>();
        cbxMaKhoa = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();

        jTextField6.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1600, 900));
        setResizable(false);

        pnlDoiCoSo.setBackground(new java.awt.Color(237, 237, 237));
        pnlDoiCoSo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));

        jLabel57.setBackground(new java.awt.Color(252, 252, 252));
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 153));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Cơ sở:");

        cbxCoSoHienTai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxCoSoHienTai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCoSoHienTaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDoiCoSoLayout = new javax.swing.GroupLayout(pnlDoiCoSo);
        pnlDoiCoSo.setLayout(pnlDoiCoSoLayout);
        pnlDoiCoSoLayout.setHorizontalGroup(
            pnlDoiCoSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoiCoSoLayout.createSequentialGroup()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxCoSoHienTai, 0, 148, Short.MAX_VALUE))
        );
        pnlDoiCoSoLayout.setVerticalGroup(
            pnlDoiCoSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cbxCoSoHienTai, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlGD_LamViec.setBackground(new java.awt.Color(255, 255, 255));
        pnlGD_LamViec.setPreferredSize(new java.awt.Dimension(1600, 900));
        pnlGD_LamViec.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MainBtt.setBackground(new java.awt.Color(80, 130, 217));
        MainBtt.setPreferredSize(new java.awt.Dimension(1600, 132));

        bttUser.setBackground(new java.awt.Color(80, 130, 217));
        bttUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        bttUser.setForeground(new java.awt.Color(0, 0, 102));
        bttUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bttUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bttUserMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttUserMousePressed(evt);
            }
        });
        bttUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/userIcon.png"))); // NOI18N
        bttUser.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 90));

        txtUserName.setBackground(new java.awt.Color(253, 253, 253));
        txtUserName.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(249, 249, 249));
        txtUserName.setText("Nguyễn Nguyên Nguyên Nguyen ");
        bttUser.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, 34));

        bttKhoaLop.setBackground(new java.awt.Color(37, 74, 131));
        bttKhoaLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bttKhoaLopMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bttKhoaLopMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttKhoaLopMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Khoa,lớp");

        unlKhoaLop.setBackground(new java.awt.Color(254, 240, 229));

        javax.swing.GroupLayout unlKhoaLopLayout = new javax.swing.GroupLayout(unlKhoaLop);
        unlKhoaLop.setLayout(unlKhoaLopLayout);
        unlKhoaLopLayout.setHorizontalGroup(
            unlKhoaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        unlKhoaLopLayout.setVerticalGroup(
            unlKhoaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bttKhoaLopLayout = new javax.swing.GroupLayout(bttKhoaLop);
        bttKhoaLop.setLayout(bttKhoaLopLayout);
        bttKhoaLopLayout.setHorizontalGroup(
            bttKhoaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(unlKhoaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        bttKhoaLopLayout.setVerticalGroup(
            bttKhoaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bttKhoaLopLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(unlKhoaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bttGiaoVien.setBackground(new java.awt.Color(37, 74, 131));
        bttGiaoVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bttGiaoVienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bttGiaoVienMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttGiaoVienMousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Giáo viên");

        unlGiaoVien.setBackground(new java.awt.Color(254, 240, 229));
        unlGiaoVien.setPreferredSize(new java.awt.Dimension(0, 10));

        javax.swing.GroupLayout unlGiaoVienLayout = new javax.swing.GroupLayout(unlGiaoVien);
        unlGiaoVien.setLayout(unlGiaoVienLayout);
        unlGiaoVienLayout.setHorizontalGroup(
            unlGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        unlGiaoVienLayout.setVerticalGroup(
            unlGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bttGiaoVienLayout = new javax.swing.GroupLayout(bttGiaoVien);
        bttGiaoVien.setLayout(bttGiaoVienLayout);
        bttGiaoVienLayout.setHorizontalGroup(
            bttGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(unlGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        bttGiaoVienLayout.setVerticalGroup(
            bttGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bttGiaoVienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(unlGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        bttBoDe.setBackground(new java.awt.Color(37, 74, 131));
        bttBoDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bttBoDeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bttBoDeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttBoDeMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bộ đề");

        unlBoDe.setBackground(new java.awt.Color(254, 240, 229));
        unlBoDe.setPreferredSize(new java.awt.Dimension(0, 10));

        javax.swing.GroupLayout unlBoDeLayout = new javax.swing.GroupLayout(unlBoDe);
        unlBoDe.setLayout(unlBoDeLayout);
        unlBoDeLayout.setHorizontalGroup(
            unlBoDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
        unlBoDeLayout.setVerticalGroup(
            unlBoDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bttBoDeLayout = new javax.swing.GroupLayout(bttBoDe);
        bttBoDe.setLayout(bttBoDeLayout);
        bttBoDeLayout.setHorizontalGroup(
            bttBoDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(unlBoDe, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bttBoDeLayout.setVerticalGroup(
            bttBoDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bttBoDeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(unlBoDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        bttKyThi.setBackground(new java.awt.Color(37, 74, 131));
        bttKyThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bttKyThiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bttKyThiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttKyThiMousePressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Kỳ thi");

        unlKyThi.setBackground(new java.awt.Color(254, 240, 229));
        unlKyThi.setPreferredSize(new java.awt.Dimension(0, 10));

        javax.swing.GroupLayout unlKyThiLayout = new javax.swing.GroupLayout(unlKyThi);
        unlKyThi.setLayout(unlKyThiLayout);
        unlKyThiLayout.setHorizontalGroup(
            unlKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        unlKyThiLayout.setVerticalGroup(
            unlKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bttKyThiLayout = new javax.swing.GroupLayout(bttKyThi);
        bttKyThi.setLayout(bttKyThiLayout);
        bttKyThiLayout.setHorizontalGroup(
            bttKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(unlKyThi, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        bttKyThiLayout.setVerticalGroup(
            bttKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bttKyThiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(unlKyThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        bttSinhVien.setBackground(new java.awt.Color(37, 74, 131));
        bttSinhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bttSinhVienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bttSinhVienMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttSinhVienMousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Sinh viên");

        unlSinhVien.setBackground(new java.awt.Color(254, 240, 229));
        unlSinhVien.setPreferredSize(new java.awt.Dimension(0, 10));

        javax.swing.GroupLayout unlSinhVienLayout = new javax.swing.GroupLayout(unlSinhVien);
        unlSinhVien.setLayout(unlSinhVienLayout);
        unlSinhVienLayout.setHorizontalGroup(
            unlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        unlSinhVienLayout.setVerticalGroup(
            unlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bttSinhVienLayout = new javax.swing.GroupLayout(bttSinhVien);
        bttSinhVien.setLayout(bttSinhVienLayout);
        bttSinhVienLayout.setHorizontalGroup(
            bttSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(unlSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        bttSinhVienLayout.setVerticalGroup(
            bttSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bttSinhVienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(unlSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bttDoiCoSo.setBackground(new java.awt.Color(37, 74, 131));
        bttDoiCoSo.setPreferredSize(new java.awt.Dimension(200, 100));
        bttDoiCoSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bttDoiCoSoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bttDoiCoSoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttDoiCoSoMousePressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/icons8_menu_64px.png"))); // NOI18N

        javax.swing.GroupLayout bttDoiCoSoLayout = new javax.swing.GroupLayout(bttDoiCoSo);
        bttDoiCoSo.setLayout(bttDoiCoSoLayout);
        bttDoiCoSoLayout.setHorizontalGroup(
            bttDoiCoSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bttDoiCoSoLayout.setVerticalGroup(
            bttDoiCoSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bttMonHoc.setBackground(new java.awt.Color(37, 74, 131));
        bttMonHoc.setPreferredSize(new java.awt.Dimension(192, 90));
        bttMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bttMonHocMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bttMonHocMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttMonHocMousePressed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Môn Học");

        unlMonHoc.setBackground(new java.awt.Color(254, 240, 229));

        javax.swing.GroupLayout unlMonHocLayout = new javax.swing.GroupLayout(unlMonHoc);
        unlMonHoc.setLayout(unlMonHocLayout);
        unlMonHocLayout.setHorizontalGroup(
            unlMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
        unlMonHocLayout.setVerticalGroup(
            unlMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bttMonHocLayout = new javax.swing.GroupLayout(bttMonHoc);
        bttMonHoc.setLayout(bttMonHocLayout);
        bttMonHocLayout.setHorizontalGroup(
            bttMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(unlMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bttMonHocLayout.setVerticalGroup(
            bttMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bttMonHocLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(unlMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout MainBttLayout = new javax.swing.GroupLayout(MainBtt);
        MainBtt.setLayout(MainBttLayout);
        MainBttLayout.setHorizontalGroup(
            MainBttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainBttLayout.createSequentialGroup()
                .addComponent(bttUser, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bttKhoaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bttGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bttBoDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bttKyThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bttSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bttMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bttDoiCoSo, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
        );
        MainBttLayout.setVerticalGroup(
            MainBttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bttBoDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bttGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bttKhoaLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bttUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bttKyThi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bttSinhVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bttDoiCoSo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainBttLayout.createSequentialGroup()
                .addComponent(bttMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlGD_LamViec.add(MainBtt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 90));

        pnlBode.setPreferredSize(new java.awt.Dimension(1600, 740));

        tblDsCauHoi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        tblDsCauHoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ".","Câu Hỏi", "Mã MH", "Trình độ", "Nội dung", "A", "B", "C", "D", "ĐÁP ÁN", "MÃ GV"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true,false, true, true, true,true, true, true, true,true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (String.valueOf(tblDsCauHoi.getValueAt(rowIndex, 10)).compareToIgnoreCase(gvLogin.getMaGV()) != 0) {
                    return false;
                }
                if (columnIndex == 2) {
                    String maMh = String.valueOf(tblDsCauHoi.getValueAt(rowIndex, columnIndex));
                    if (!IODATA.ktDoiMonCauHoi(gvLogin, maMh)) {
                        JOptionPane.showMessageDialog(rootPane, "Môn học " + maMh + " hiện có trong danh sách chuẩn bị thi nên "
                            + "không thể sửa môn câu hỏi hoặc xóa câu hỏi môn này", "Không thể sửa hoặc xóa câu hỏi môn " + maMh, JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                return canEdit[columnIndex];
            }
        });
        tblDsCauHoi.setRowHeight(35);
        tblDsCauHoi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDsCauHoi.getTableHeader().setReorderingAllowed(false);
        tblDsCauHoi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblDsCauHoiFocusGained(evt);
            }
        });
        tblDsCauHoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDsCauHoiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDsCauHoi);

        pnlBoDeFunc.setBackground(new java.awt.Color(254, 254, 254));
        pnlBoDeFunc.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(254, 240, 229)));

        bttThemBD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttThemBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/newIcon.png"))); // NOI18N
        bttThemBD.setText("Thêm");
        bttThemBD.setBorderPainted(false);
        bttThemBD.setPreferredSize(new java.awt.Dimension(115, 39));
        bttThemBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttThemBDActionPerformed(evt);
            }
        });

        bttLuuBD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttLuuBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/saveIcon.png"))); // NOI18N
        bttLuuBD.setText("Lưu");
        bttLuuBD.setBorderPainted(false);
        bttLuuBD.setPreferredSize(new java.awt.Dimension(115, 37));
        bttLuuBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttLuuBDActionPerformed(evt);
            }
        });

        bttHuyThayDoiBD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttHuyThayDoiBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/cancelIcon.png"))); // NOI18N
        bttHuyThayDoiBD.setText("Hủy bỏ");
        bttHuyThayDoiBD.setBorderPainted(false);
        bttHuyThayDoiBD.setPreferredSize(new java.awt.Dimension(115, 39));
        bttHuyThayDoiBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttHuyThayDoiBDActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Hiệu chỉnh:");

        bttXoaBD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttXoaBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/deleteIcon.png"))); // NOI18N
        bttXoaBD.setText("Xóa");
        bttXoaBD.setBorderPainted(false);
        bttXoaBD.setPreferredSize(new java.awt.Dimension(115, 39));
        bttXoaBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttXoaBDActionPerformed(evt);
            }
        });

        bttPhucHoiBD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttPhucHoiBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/backIcon.png"))); // NOI18N
        bttPhucHoiBD.setText("Back");
        bttPhucHoiBD.setBorderPainted(false);
        bttPhucHoiBD.setPreferredSize(new java.awt.Dimension(115, 39));
        bttPhucHoiBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttPhucHoiBDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBoDeFuncLayout = new javax.swing.GroupLayout(pnlBoDeFunc);
        pnlBoDeFunc.setLayout(pnlBoDeFuncLayout);
        pnlBoDeFuncLayout.setHorizontalGroup(
            pnlBoDeFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoDeFuncLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bttPhucHoiBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttThemBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttXoaBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(bttHuyThayDoiBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttLuuBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(818, Short.MAX_VALUE))
        );
        pnlBoDeFuncLayout.setVerticalGroup(
            pnlBoDeFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoDeFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBoDeFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBoDeFuncLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBoDeFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bttLuuBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttThemBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bttHuyThayDoiBD, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(bttXoaBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bttPhucHoiBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        txtTimKiemCH.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTimKiemCH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemCHKeyTyped(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("Tìm kiếm: ");

        pnlChiTietCauHoi.setBackground(new java.awt.Color(255, 255, 255));
        pnlChiTietCauHoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));

        lblA.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblA.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblA.setPreferredSize(new java.awt.Dimension(1422, 45));

        lblB.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblB.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblB.setPreferredSize(new java.awt.Dimension(1422, 45));

        lblC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblC.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblC.setPreferredSize(new java.awt.Dimension(1422, 45));

        lblD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblD.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblD.setPreferredSize(new java.awt.Dimension(1422, 45));

        lblCauSo.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblCauSo.setForeground(new java.awt.Color(0, 0, 153));
        lblCauSo.setText("Câu  1000:");
        lblCauSo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblNoiDung.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        lblNoiDung.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel45.setText("A) ");
        jLabel45.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel62.setText("B) ");
        jLabel62.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel63.setText("C) ");
        jLabel63.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel64.setText("D) ");
        jLabel64.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlChiTietCauHoiLayout = new javax.swing.GroupLayout(pnlChiTietCauHoi);
        pnlChiTietCauHoi.setLayout(pnlChiTietCauHoiLayout);
        pnlChiTietCauHoiLayout.setHorizontalGroup(
            pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietCauHoiLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlChiTietCauHoiLayout.createSequentialGroup()
                .addGroup(pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChiTietCauHoiLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblCauSo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlChiTietCauHoiLayout.createSequentialGroup()
                        .addGroup(pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlChiTietCauHoiLayout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlChiTietCauHoiLayout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addGroup(pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel64)
                                    .addComponent(jLabel63))
                                .addGap(7, 7, 7)
                                .addGroup(pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        pnlChiTietCauHoiLayout.setVerticalGroup(
            pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChiTietCauHoiLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCauSo, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(lblNoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChiTietCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout pnlBodeLayout = new javax.swing.GroupLayout(pnlBode);
        pnlBode.setLayout(pnlBodeLayout);
        pnlBodeLayout.setHorizontalGroup(
            pnlBodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBoDeFunc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlBodeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChiTietCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(pnlBodeLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemCH, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlBodeLayout.setVerticalGroup(
            pnlBodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodeLayout.createSequentialGroup()
                .addComponent(pnlBoDeFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiemCH)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlChiTietCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        pnlGD_LamViec.add(pnlBode, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, 810));

        jPanel5.setBackground(new java.awt.Color(244, 244, 244));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 153));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("Danh sách lớp của khoa ");

        tblDSLop.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblDSLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ".", "Mã Lớp", "Tên Lớp", "Mã Khoa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(columnIndex==0) return false;
                if(!editDataRows.isEmpty()){
                    if(editDataRows.contains(rowIndex)&&columnIndex!=3){
                        return true;
                    }else return false;
                }else{
                    if(columnIndex!=1) return true;
                }
                return false;
            }
        });
        tblDSLop.setRowHeight(35);
        tblDSLop.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDSLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSLopMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblDSLop);
        if (tblDSLop.getColumnModel().getColumnCount() > 0) {
            tblDSLop.getColumnModel().getColumn(0).setResizable(false);
            tblDSLop.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblDSLop.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblDSLop.getColumnModel().getColumn(2).setPreferredWidth(300);
            tblDSLop.getColumnModel().getColumn(3).setPreferredWidth(150);
        }

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 153));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Khoa");

        tbDSKhoa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbDSKhoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khoa", "Tên Khoa", "Mã Cơ Sở"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDSKhoa.setRowHeight(35);
        tbDSKhoa.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbDSKhoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDSKhoaMouseClicked(evt);
            }
        });
        tbDSKhoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDSKhoaKeyPressed(evt);
            }
        });
        jScrollPane7.setViewportView(tbDSKhoa);
        if (tbDSKhoa.getColumnModel().getColumnCount() > 0) {
            tbDSKhoa.getColumnModel().getColumn(0).setPreferredWidth(70);
            tbDSKhoa.getColumnModel().getColumn(1).setPreferredWidth(200);
            tbDSKhoa.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        pnlHieuChinhKhoa.setBackground(new java.awt.Color(244, 244, 244));
        pnlHieuChinhKhoa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Hiệu chỉnh khoa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel46.setText("Mã Khoa         :");

        txtMaKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKhActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel47.setText("Tên khoa        :");

        txtTenKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.setActionCommand("");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        BtnDelete.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        BtnDelete.setText("Xóa");
        BtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeleteActionPerformed(evt);
            }
        });

        BtnUpdate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        BtnUpdate.setText("Sửa");
        BtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHieuChinhKhoaLayout = new javax.swing.GroupLayout(pnlHieuChinhKhoa);
        pnlHieuChinhKhoa.setLayout(pnlHieuChinhKhoaLayout);
        pnlHieuChinhKhoaLayout.setHorizontalGroup(
            pnlHieuChinhKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHieuChinhKhoaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHieuChinhKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHieuChinhKhoaLayout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addComponent(BtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(BtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(pnlHieuChinhKhoaLayout.createSequentialGroup()
                        .addGroup(pnlHieuChinhKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlHieuChinhKhoaLayout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenKh))
                            .addGroup(pnlHieuChinhKhoaLayout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaKh, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlHieuChinhKhoaLayout.setVerticalGroup(
            pnlHieuChinhKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHieuChinhKhoaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlHieuChinhKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(pnlHieuChinhKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(pnlHieuChinhKhoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        lblMaKhoa.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblMaKhoa.setForeground(new java.awt.Color(0, 0, 153));
        lblMaKhoa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jSeparator4.setForeground(new java.awt.Color(0, 0, 153));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7)
                    .addComponent(pnlHieuChinhKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(lblMaKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator5))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44)))
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlHieuChinhKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 230, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout pnlKhoaLopLayout = new javax.swing.GroupLayout(pnlKhoaLop);
        pnlKhoaLop.setLayout(pnlKhoaLopLayout);
        pnlKhoaLopLayout.setHorizontalGroup(
            pnlKhoaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlKhoaLopLayout.setVerticalGroup(
            pnlKhoaLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlGD_LamViec.add(pnlKhoaLop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1600, 810));

        pnlGiaoVien.setBackground(new java.awt.Color(242, 242, 242));

        pnlGiaoVienFunc.setBackground(new java.awt.Color(254, 254, 254));
        pnlGiaoVienFunc.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(254, 240, 229)));

        pnlHieuChinhGV.setBackground(new java.awt.Color(254, 254, 254));

        bttXoaGV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttXoaGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/deleteIcon.png"))); // NOI18N
        bttXoaGV.setText("Xóa");
        bttXoaGV.setBorderPainted(false);
        bttXoaGV.setPreferredSize(new java.awt.Dimension(115, 39));
        bttXoaGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttXoaGVActionPerformed(evt);
            }
        });

        bttThemGV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttThemGV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/newIcon.png"))); // NOI18N
        bttThemGV.setText("Thêm");
        bttThemGV.setBorderPainted(false);
        bttThemGV.setPreferredSize(new java.awt.Dimension(115, 39));
        bttThemGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttThemGVActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Hiệu chỉnh:");

        bttTaoLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttTaoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/icons8_add_user_male_30px.png"))); // NOI18N
        bttTaoLogin.setText("Tạo Login");
        bttTaoLogin.setBorderPainted(false);
        bttTaoLogin.setPreferredSize(new java.awt.Dimension(115, 39));
        bttTaoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttTaoLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHieuChinhGVLayout = new javax.swing.GroupLayout(pnlHieuChinhGV);
        pnlHieuChinhGV.setLayout(pnlHieuChinhGVLayout);
        pnlHieuChinhGVLayout.setHorizontalGroup(
            pnlHieuChinhGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHieuChinhGVLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttThemGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttXoaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttTaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlHieuChinhGVLayout.setVerticalGroup(
            pnlHieuChinhGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHieuChinhGVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHieuChinhGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHieuChinhGVLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlHieuChinhGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bttThemGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bttXoaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bttTaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlGiaoVienFuncLayout = new javax.swing.GroupLayout(pnlGiaoVienFunc);
        pnlGiaoVienFunc.setLayout(pnlGiaoVienFuncLayout);
        pnlGiaoVienFuncLayout.setHorizontalGroup(
            pnlGiaoVienFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiaoVienFuncLayout.createSequentialGroup()
                .addComponent(pnlHieuChinhGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGiaoVienFuncLayout.setVerticalGroup(
            pnlGiaoVienFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHieuChinhGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tblDsGiaoVien.setBorder(new javax.swing.border.MatteBorder(null));
        tblDsGiaoVien.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tblDsGiaoVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ GIÁO VIÊN", "HỌ", "TÊN", "HỌC VỊ", "MÃ KHOA", "CƠ SỞ", "CÓ LOGIN?"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDsGiaoVien.setRowHeight(45);
        tblDsGiaoVien.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblDsGiaoVien.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblDsGiaoVien.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDsGiaoVien.getTableHeader().setReorderingAllowed(false);
        tblDsGiaoVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDsGiaoVienMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblDsGiaoVien);
        tblDsGiaoVien.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblDsGiaoVien.getColumnModel().getColumnCount() > 0) {
            tblDsGiaoVien.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblDsGiaoVien.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblDsGiaoVien.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblDsGiaoVien.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblDsGiaoVien.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblDsGiaoVien.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        jPanel8.setBackground(new java.awt.Color(244, 244, 244));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Phân quyền", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 26), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel50.setText("Tên login: ");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel51.setText("Quyền cấp:");

        lblTenLoginGV.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N

        lblQuyenGV.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenLoginGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblQuyenGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenLoginGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuyenGV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jLabel52.setBackground(new java.awt.Color(0, 0, 153));
        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 0, 153));
        jLabel52.setText("Danh sách giáo viên");

        jSeparator1.setForeground(new java.awt.Color(0, 51, 153));

        jSeparator2.setForeground(new java.awt.Color(0, 51, 153));

        pnlThemGV.setBackground(new java.awt.Color(244, 244, 244));
        pnlThemGV.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Thêm giáo viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 26), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel53.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        jLabel53.setText("Họ      : ");

        jLabel54.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        jLabel54.setText("Tên     :");

        jLabel55.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        jLabel55.setText("Mã GV:");

        jLabel56.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        jLabel56.setText("Học vị :");

        txtMaGv_GiaoVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMaGv_GiaoVien.setPreferredSize(new java.awt.Dimension(200, 35));
        txtMaGv_GiaoVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaGv_GiaoVienMouseClicked(evt);
            }
        });

        txtHoGv_GiaoVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtHoGv_GiaoVien.setPreferredSize(new java.awt.Dimension(200, 35));
        txtHoGv_GiaoVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHoGv_GiaoVienMouseClicked(evt);
            }
        });

        txtTenGv_GiaoVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenGv_GiaoVien.setPreferredSize(new java.awt.Dimension(200, 35));
        txtTenGv_GiaoVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenGv_GiaoVienMouseClicked(evt);
            }
        });

        txtHocVi_GiaoVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtHocVi_GiaoVien.setPreferredSize(new java.awt.Dimension(200, 35));
        txtHocVi_GiaoVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHocVi_GiaoVienMouseClicked(evt);
            }
        });

        cbxKhoa_GiaoVien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxKhoa_GiaoVien.setPreferredSize(new java.awt.Dimension(200, 35));
        AutoCompleteDecorator.decorate(cbxKhoa_GiaoVien,ObjectToStringConverter.DEFAULT_IMPLEMENTATION);

        jLabel59.setFont(new java.awt.Font("Arial", 1, 19)); // NOI18N
        jLabel59.setText("Khoa   :");

        jLabel61.setFont(new java.awt.Font("Tahoma", 2, 21)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 51, 204));
        jLabel61.setText("Thông tin cá nhân:");

        bttXacNhanThem.setBackground(new java.awt.Color(249, 231, 0));
        bttXacNhanThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bttXacNhanThem.setForeground(new java.awt.Color(51, 51, 51));
        bttXacNhanThem.setText("Xác nhận");
        bttXacNhanThem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bttXacNhanThem.setContentAreaFilled(false);
        bttXacNhanThem.setOpaque(true);
        bttXacNhanThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttXacNhanThemActionPerformed(evt);
            }
        });
        bttXacNhanThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bttXacNhanThem.setBackground(new Color(233, 217, 7));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bttXacNhanThem.setBackground(new Color(249, 231, 0));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                bttXacNhanThem.setBackground(new Color(223, 208, 7));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                bttXacNhanThem.setBackground(new Color(249, 231, 0));//To change body of generated methods, choose Tools | Templates.
            }
        });

        javax.swing.GroupLayout pnlThemGVLayout = new javax.swing.GroupLayout(pnlThemGV);
        pnlThemGV.setLayout(pnlThemGVLayout);
        pnlThemGVLayout.setHorizontalGroup(
            pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemGVLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlThemGVLayout.createSequentialGroup()
                        .addGroup(pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel56)
                                .addComponent(jLabel54)
                                .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel59))
                        .addGap(33, 33, 33)
                        .addGroup(pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxKhoa_GiaoVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHoGv_GiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenGv_GiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHocVi_GiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlThemGVLayout.createSequentialGroup()
                                .addComponent(txtMaGv_GiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(bttXacNhanThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlThemGVLayout.setVerticalGroup(
            pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemGVLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel61)
                .addGap(28, 28, 28)
                .addGroup(pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaGv_GiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoGv_GiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenGv_GiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtHocVi_GiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(pnlThemGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxKhoa_GiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(bttXacNhanThem, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout pnlGiaoVienLayout = new javax.swing.GroupLayout(pnlGiaoVien);
        pnlGiaoVien.setLayout(pnlGiaoVienLayout);
        pnlGiaoVienLayout.setHorizontalGroup(
            pnlGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGiaoVienFunc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlGiaoVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGiaoVienLayout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(451, 451, 451))
                    .addGroup(pnlGiaoVienLayout.createSequentialGroup()
                        .addGroup(pnlGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1064, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlGiaoVienLayout.createSequentialGroup()
                                .addGap(636, 636, 636)
                                .addComponent(jSeparator1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlThemGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGiaoVienLayout.setVerticalGroup(
            pnlGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiaoVienLayout.createSequentialGroup()
                .addComponent(pnlGiaoVienFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGiaoVienLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlThemGV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlGiaoVienLayout.createSequentialGroup()
                        .addGroup(pnlGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8)))
                .addContainerGap())
        );

        pnlGD_LamViec.add(pnlGiaoVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1600, 810));

        pnlSinhVien.setBackground(new java.awt.Color(242, 242, 242));
        pnlSinhVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));
        pnlSinhVien.setPreferredSize(new java.awt.Dimension(1600, 810));

        pnlHieuChinhSinhVien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Hiệu chỉnh sinh viên", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 26), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel34.setText("MÃ SINH VIÊN:");
        jLabel34.setPreferredSize(new java.awt.Dimension(114, 35));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel35.setText("HỌ                 :");
        jLabel35.setPreferredSize(new java.awt.Dimension(114, 35));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel38.setText("TÊN               :");
        jLabel38.setPreferredSize(new java.awt.Dimension(114, 35));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel40.setText("NGÀY SINH    :");
        jLabel40.setPreferredSize(new java.awt.Dimension(114, 35));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel41.setText("ĐỊA CHỈ         :");
        jLabel41.setPreferredSize(new java.awt.Dimension(114, 35));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel42.setText("MẬT KHẨU     :");
        jLabel42.setPreferredSize(new java.awt.Dimension(114, 35));

        txtMaSV_SINHVIEN.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        txtTen_SINHVIEN.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        txtHo_SINHNVIEN.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        txtDiaChi_SINHVIEN.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        btn_ThemSinhVien.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btn_ThemSinhVien.setText("THÊM");
        btn_ThemSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSinhVienActionPerformed(evt);
            }
        });

        btn_SuaSinhVien.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btn_SuaSinhVien.setText("SỬA");
        btn_SuaSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaSinhVienActionPerformed(evt);
            }
        });

        btn_XoaSinhVien.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btn_XoaSinhVien.setText("XÓA");
        btn_XoaSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaSinhVienActionPerformed(evt);
            }
        });

        btn_LamMoiSinhVien.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        btn_LamMoiSinhVien.setText("LÀM MỚI");
        btn_LamMoiSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiSinhVienActionPerformed(evt);
            }
        });

        txtMauKhau_SINHVIEN.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        txtNgaySinh_SINHVIEN.setDateFormatString("dd/MM/yyyy");
        txtNgaySinh_SINHVIEN.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        javax.swing.GroupLayout pnlHieuChinhSinhVienLayout = new javax.swing.GroupLayout(pnlHieuChinhSinhVien);
        pnlHieuChinhSinhVien.setLayout(pnlHieuChinhSinhVienLayout);
        pnlHieuChinhSinhVienLayout.setHorizontalGroup(
            pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHieuChinhSinhVienLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlHieuChinhSinhVienLayout.createSequentialGroup()
                        .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlHieuChinhSinhVienLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMauKhau_SINHVIEN, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtTen_SINHVIEN, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtHo_SINHNVIEN, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(txtMaSV_SINHVIEN, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                    .addGroup(pnlHieuChinhSinhVienLayout.createSequentialGroup()
                        .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi_SINHVIEN)
                            .addComponent(txtNgaySinh_SINHVIEN, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_LamMoiSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_XoaSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_SuaSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ThemSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlHieuChinhSinhVienLayout.setVerticalGroup(
            pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHieuChinhSinhVienLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaSV_SINHVIEN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemSinhVien))
                .addGap(18, 18, 18)
                .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_SuaSinhVien)
                    .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHo_SINHNVIEN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_XoaSinhVien)
                    .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTen_SINHVIEN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_LamMoiSinhVien)
                    .addGroup(pnlHieuChinhSinhVienLayout.createSequentialGroup()
                        .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgaySinh_SINHVIEN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi_SINHVIEN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlHieuChinhSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMauKhau_SINHVIEN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Bảng điểm ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 26), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel9.setPreferredSize(new java.awt.Dimension(462, 200));

        tblBangDiem.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        tblBangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ SINH VIEN", "MÃ MÔN HỌC", "TÊN MÔN HỌC", "LẦN THI", "NGÀY THI", "ĐIỂM", "MÃ BÀI THI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangDiem.setRowHeight(35);
        tblBangDiem.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBangDiem.getTableHeader().setReorderingAllowed(false);
        tblBangDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangDiemMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblBangDiem);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        tblSinhVienLop.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        tblSinhVienLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MSV", "HỌ", "TÊN", "NGÀY SINH", "ĐỊA CHỈ", "MÃ LỚP", "MẬT KHẨU"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSinhVienLop.setRowHeight(36);
        tblSinhVienLop.getTableHeader().setReorderingAllowed(false);
        tblSinhVienLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSinhVienLopMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSinhVienLop);
        if (tblSinhVienLop.getColumnModel().getColumnCount() > 0) {
            tblSinhVienLop.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblSinhVienLop.getColumnModel().getColumn(1).setPreferredWidth(180);
            tblSinhVienLop.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblSinhVienLop.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblSinhVienLop.getColumnModel().getColumn(4).setPreferredWidth(270);
            tblSinhVienLop.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblSinhVienLop.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 153));
        jLabel58.setText("Danh sách sinh viên lớp");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 153));

        cbxLop_SINHVIEN.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbxLop_SINHVIEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLop_SINHVIENActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel39.setText("Lớp    :");

        bttReportBaiThi.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        bttReportBaiThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/icons8_business_report_48px.png"))); // NOI18N
        bttReportBaiThi.setText("Xem lại bài thi");
        bttReportBaiThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttReportBaiThiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSinhVienLayout = new javax.swing.GroupLayout(pnlSinhVien);
        pnlSinhVien.setLayout(pnlSinhVienLayout);
        pnlSinhVienLayout.setHorizontalGroup(
            pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSinhVienLayout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxLop_SINHVIEN, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSinhVienLayout.createSequentialGroup()
                        .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSinhVienLayout.createSequentialGroup()
                                .addComponent(jLabel58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlHieuChinhSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSinhVienLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1265, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bttReportBaiThi, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)))
                .addContainerGap())
        );
        pnlSinhVienLayout.setVerticalGroup(
            pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSinhVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxLop_SINHVIEN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlHieuChinhSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSinhVienLayout.createSequentialGroup()
                        .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel58)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGroup(pnlSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSinhVienLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlSinhVienLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(bttReportBaiThi)
                        .addContainerGap(298, Short.MAX_VALUE))))
        );

        pnlGD_LamViec.add(pnlSinhVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1600, 810));

        pnlKyThiFunc.setBackground(new java.awt.Color(254, 254, 254));
        pnlKyThiFunc.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(254, 240, 229)));

        bttThiThu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttThiThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/tracnghiemIcon.png"))); // NOI18N
        bttThiThu.setText("Thi thử");
        bttThiThu.setPreferredSize(new java.awt.Dimension(115, 37));
        bttThiThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttThiThuActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Nâng cao:");

        bttBaoCaoDsKyThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttBaoCaoDsKyThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/icons8_business_report_35px.png"))); // NOI18N
        bttBaoCaoDsKyThi.setText("Xem danh sách kỳ thi");
        bttBaoCaoDsKyThi.setPreferredSize(new java.awt.Dimension(115, 37));
        bttBaoCaoDsKyThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttBaoCaoDsKyThiActionPerformed(evt);
            }
        });

        pnlHieuChinhKyThi.setBackground(new java.awt.Color(254, 254, 254));

        bttThemKT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttThemKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/newIcon.png"))); // NOI18N
        bttThemKT.setText("Thêm");
        bttThemKT.setBorderPainted(false);
        bttThemKT.setPreferredSize(new java.awt.Dimension(115, 39));
        bttThemKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttThemKTActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Hiệu chỉnh:");
        jLabel33.setPreferredSize(new java.awt.Dimension(110, 35));

        bttLuuKT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttLuuKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/saveIcon.png"))); // NOI18N
        bttLuuKT.setText("Lưu");
        bttLuuKT.setBorderPainted(false);
        bttLuuKT.setPreferredSize(new java.awt.Dimension(115, 37));
        bttLuuKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttLuuKTActionPerformed(evt);
            }
        });

        bttSuaKT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttSuaKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/editIcon.png"))); // NOI18N
        bttSuaKT.setText("Sửa");
        bttSuaKT.setBorderPainted(false);
        bttSuaKT.setPreferredSize(new java.awt.Dimension(115, 39));
        bttSuaKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttSuaKTActionPerformed(evt);
            }
        });

        bttHuyThayDoiKT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttHuyThayDoiKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/cancelIcon.png"))); // NOI18N
        bttHuyThayDoiKT.setText("Hủy bỏ");
        bttHuyThayDoiKT.setBorderPainted(false);
        bttHuyThayDoiKT.setPreferredSize(new java.awt.Dimension(115, 39));
        bttHuyThayDoiKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttHuyThayDoiKTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHieuChinhKyThiLayout = new javax.swing.GroupLayout(pnlHieuChinhKyThi);
        pnlHieuChinhKyThi.setLayout(pnlHieuChinhKyThiLayout);
        pnlHieuChinhKyThiLayout.setHorizontalGroup(
            pnlHieuChinhKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHieuChinhKyThiLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttThemKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttSuaKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bttHuyThayDoiKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttLuuKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlHieuChinhKyThiLayout.setVerticalGroup(
            pnlHieuChinhKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHieuChinhKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bttLuuKT, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addComponent(bttHuyThayDoiKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bttSuaKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bttThemKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bttBaoCaoBangDiemKt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttBaoCaoBangDiemKt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/icons8_business_report_35px.png"))); // NOI18N
        bttBaoCaoBangDiemKt.setText("Xem bảng điểm kỳ thi");
        bttBaoCaoBangDiemKt.setPreferredSize(new java.awt.Dimension(115, 37));
        bttBaoCaoBangDiemKt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttBaoCaoBangDiemKtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKyThiFuncLayout = new javax.swing.GroupLayout(pnlKyThiFunc);
        pnlKyThiFunc.setLayout(pnlKyThiFuncLayout);
        pnlKyThiFuncLayout.setHorizontalGroup(
            pnlKyThiFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKyThiFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHieuChinhKyThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttThiThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttBaoCaoDsKyThi, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttBaoCaoBangDiemKt, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        pnlKyThiFuncLayout.setVerticalGroup(
            pnlKyThiFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKyThiFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKyThiFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlKyThiFuncLayout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(pnlHieuChinhKyThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bttThiThu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlKyThiFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bttBaoCaoDsKyThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttBaoCaoBangDiemKt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblDsKyThi.setBorder(new javax.swing.border.MatteBorder(null));
        tblDsKyThi.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        tblDsKyThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ".","MÃ MÔN THI","TÊN MÔN THI", "LỚP", "LẦN", "GVĐK", "TRÌNH ĐỘ", "NGÀY THI", "GIỜ BẮT ĐẦU", "SỐ CÂU ", "THỜI GIAN", "TRẠNG THÁI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                System.out.print(rowIndex);
                if(mode ==1){
                    if(editDataRows.contains(rowIndex)&&columnIndex!=0
                        &&columnIndex!=5&&columnIndex!=11){
                        System.out.println(" true");
                        return true;
                    }
                }
                if(mode ==2){
                    if(editDataRows.contains(rowIndex)&&columnIndex!=0&&columnIndex!=5&&columnIndex!=11
                        &&columnIndex!=1&&columnIndex!=2&&columnIndex!=3&&columnIndex!=4){
                        System.out.println(" true");
                        return true;
                    }
                }
                System.out.println(" false");
                return false;
            }
        });
        tblDsKyThi.setColumnSelectionAllowed(true);
        tblDsKyThi.setColumnSelectionAllowed(false);
        tblDsKyThi.setRowSelectionAllowed(true);
        tblDsKyThi.setRowHeight(45);
        tblDsKyThi.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblDsKyThi.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblDsKyThi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDsKyThi.getTableHeader().setReorderingAllowed(false);
        tblDsKyThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDsKyThiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDsKyThi);
        tblDsKyThi.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDsKyThi.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblDsKyThi.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblDsKyThi.getColumnModel().getColumn(2).setPreferredWidth(250);
        tblDsKyThi.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblDsKyThi.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblDsKyThi.getColumnModel().getColumn(5).setPreferredWidth(250);
        tblDsKyThi.getColumnModel().getColumn(6).setPreferredWidth(90);
        tblDsKyThi.getColumnModel().getColumn(7).setPreferredWidth(130);
        tblDsKyThi.getColumnModel().getColumn(8).setPreferredWidth(90);
        tblDsKyThi.getColumnModel().getColumn(9).setPreferredWidth(60);
        tblDsKyThi.getColumnModel().getColumn(10).setPreferredWidth(60);
        tblDsKyThi.getColumnModel().getColumn(11).setPreferredWidth(150);
        tblDsKyThi.getTableHeader().setResizingAllowed(false);

        jLabel67.setBackground(new java.awt.Color(0, 0, 153));
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 0, 153));
        jLabel67.setText("Danh sách kỳ thi");

        jSeparator8.setForeground(new java.awt.Color(0, 51, 153));

        javax.swing.GroupLayout pnlKyThiLayout = new javax.swing.GroupLayout(pnlKyThi);
        pnlKyThi.setLayout(pnlKyThiLayout);
        pnlKyThiLayout.setHorizontalGroup(
            pnlKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlKyThiFunc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(pnlKyThiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlKyThiLayout.createSequentialGroup()
                .addComponent(jSeparator8)
                .addContainerGap())
        );
        pnlKyThiLayout.setVerticalGroup(
            pnlKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKyThiLayout.createSequentialGroup()
                .addComponent(pnlKyThiFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel67)
                .addGap(3, 3, 3)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE))
        );

        pnlGD_LamViec.add(pnlKyThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1600, 810));

        pnlHoSoGiaoVien.setBackground(new java.awt.Color(252, 252, 252));
        pnlHoSoGiaoVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Hồ sơ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel65.setText("Họ               :");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel66.setText("Mã giáo viên:");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel68.setText("Thuộc khoa  :");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel69.setText("Login          :");

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel70.setText("Quyền         :");

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel71.setText("Học vị          :");

        jLabel73.setFont(new java.awt.Font("Tahoma", 2, 21)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 51, 204));
        jLabel73.setText("Thông tin cá nhân:");

        jLabel74.setFont(new java.awt.Font("Tahoma", 2, 21)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 51, 204));
        jLabel74.setText("Thông tin tài khoản:");

        txtHoGv_HoSo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtHoGv_HoSo.setPreferredSize(new java.awt.Dimension(200, 35));
        txtHoGv_HoSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHoGv_HoSoMouseClicked(evt);
            }
        });
        txtHoGv_HoSo.setEnabled(false);

        txtHocVi_HoSo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtHocVi_HoSo.setPreferredSize(new java.awt.Dimension(200, 35));
        txtHocVi_HoSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHocVi_HoSoMouseClicked(evt);
            }
        });
        txtHocVi_HoSo.setEnabled(false);

        lblMaGv_HoSo.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        lblMaGv_HoSo.setText("jLabel72");

        bttLuuHoSo.setBackground(new java.awt.Color(249, 231, 0));
        bttLuuHoSo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bttLuuHoSo.setForeground(new java.awt.Color(51, 51, 51));
        bttLuuHoSo.setText("Lưu hồ sơ");
        bttLuuHoSo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bttLuuHoSo.setContentAreaFilled(false);
        bttLuuHoSo.setOpaque(true);
        bttLuuHoSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttLuuHoSoActionPerformed(evt);
            }
        });
        bttLuuHoSo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bttLuuHoSo.setBackground(new Color(233, 217, 7));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bttLuuHoSo.setBackground(new Color(249, 231, 0));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                bttLuuHoSo.setBackground(new Color(223, 208, 7));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                bttLuuHoSo.setBackground(new Color(249, 231, 0));//To change body of generated methods, choose Tools | Templates.
            }
        });

        bttSuaHoSo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bttSuaHoSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/editRowIcon.png"))); // NOI18N
        bttSuaHoSo.setText("Chỉnh sửa hồ sơ");
        bttSuaHoSo.setPreferredSize(new java.awt.Dimension(79, 30));
        bttSuaHoSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttSuaHoSoMouseClicked(evt);
            }
        });

        lblKhoa_HoSo.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        lblKhoa_HoSo.setText("jLabel72");

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel76.setText("Cơ sở           :");

        lblCoSo_HoSo.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        lblCoSo_HoSo.setText("jLabel72");

        lblTenLogin_HoSo.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        lblTenLogin_HoSo.setText("jLabel72");

        lblRole_HoSo.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        lblRole_HoSo.setText("jLabel72");

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        jLabel72.setText("Tên              :");

        txtTenGv_HoSo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenGv_HoSo.setPreferredSize(new java.awt.Dimension(200, 35));
        txtTenGv_HoSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTenGv_HoSoMouseClicked(evt);
            }
        });
        txtTenGv_HoSo.setEnabled(false);

        jSeparator6.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRole_HoSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTenLogin_HoSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCoSo_HoSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHocVi_HoSo, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                            .addComponent(txtHoGv_HoSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblMaGv_HoSo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTenGv_HoSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblKhoa_HoSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(123, 123, 123))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bttSuaHoSo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(bttLuuHoSo, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator6)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(bttSuaHoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaGv_HoSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHoGv_HoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenGv_HoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(txtHocVi_HoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblKhoa_HoSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCoSo_HoSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenLogin_HoSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblRole_HoSo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addGap(33, 33, 33)
                .addComponent(bttLuuHoSo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlHoSoGiaoVienLayout = new javax.swing.GroupLayout(pnlHoSoGiaoVien);
        pnlHoSoGiaoVien.setLayout(pnlHoSoGiaoVienLayout);
        pnlHoSoGiaoVienLayout.setHorizontalGroup(
            pnlHoSoGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoSoGiaoVienLayout.createSequentialGroup()
                .addGap(477, 477, 477)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(452, Short.MAX_VALUE))
        );
        pnlHoSoGiaoVienLayout.setVerticalGroup(
            pnlHoSoGiaoVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoSoGiaoVienLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pnlGD_LamViec.add(pnlHoSoGiaoVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1600, 810));

        pnlMonHoc.setBackground(new java.awt.Color(247, 247, 247));
        pnlMonHoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));

        pnlHieuChinhMonHoc.setBackground(new java.awt.Color(247, 247, 247));
        pnlHieuChinhMonHoc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Hiệu chỉnh môn học", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel77.setText("Tên môn học :");

        btnThem_MonHoc.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnThem_MonHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/newIcon.png"))); // NOI18N
        btnThem_MonHoc.setText("Thêm");
        btnThem_MonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_MonHocActionPerformed(evt);
            }
        });

        btnSua_MonHoc.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnSua_MonHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/editIcon.png"))); // NOI18N
        btnSua_MonHoc.setText("Sửa");
        btnSua_MonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_MonHocActionPerformed(evt);
            }
        });

        btnXoa_MonHoc.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnXoa_MonHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/deleteIcon.png"))); // NOI18N
        btnXoa_MonHoc.setText("Xóa");
        btnXoa_MonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_MonHocActionPerformed(evt);
            }
        });

        btnLamMoi_MonHoc.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        btnLamMoi_MonHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/icons8_refresh_20px.png"))); // NOI18N
        btnLamMoi_MonHoc.setText("Làm mới");
        btnLamMoi_MonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi_MonHocActionPerformed(evt);
            }
        });

        txtMaMH_MonHoc.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtMaMH_MonHoc.setPreferredSize(new java.awt.Dimension(35, 35));

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel75.setText("Mã môn học :");

        txtTenMH_MonHoc.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtTenMH_MonHoc.setPreferredSize(new java.awt.Dimension(35, 35));

        javax.swing.GroupLayout pnlHieuChinhMonHocLayout = new javax.swing.GroupLayout(pnlHieuChinhMonHoc);
        pnlHieuChinhMonHoc.setLayout(pnlHieuChinhMonHocLayout);
        pnlHieuChinhMonHocLayout.setHorizontalGroup(
            pnlHieuChinhMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHieuChinhMonHocLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlHieuChinhMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlHieuChinhMonHocLayout.createSequentialGroup()
                        .addComponent(btnThem_MonHoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua_MonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa_MonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLamMoi_MonHoc))
                    .addGroup(pnlHieuChinhMonHocLayout.createSequentialGroup()
                        .addGroup(pnlHieuChinhMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlHieuChinhMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlHieuChinhMonHocLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtMaMH_MonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlHieuChinhMonHocLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtTenMH_MonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        pnlHieuChinhMonHocLayout.setVerticalGroup(
            pnlHieuChinhMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHieuChinhMonHocLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(pnlHieuChinhMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaMH_MonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pnlHieuChinhMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenMH_MonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pnlHieuChinhMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem_MonHoc)
                    .addComponent(btnSua_MonHoc)
                    .addComponent(btnXoa_MonHoc)
                    .addComponent(btnLamMoi_MonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jPanel6.setBackground(new java.awt.Color(247, 247, 247));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), "Bảng danh sách môn học", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(0, 0, 153))); // NOI18N

        tblMonHoc.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        tblMonHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã môn học", "Tên môn hoc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMonHoc.setRowHeight(35);
        tblMonHoc.getTableHeader().setReorderingAllowed(false);
        tblMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMonHocMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblMonHoc);
        if (tblMonHoc.getColumnModel().getColumnCount() > 0) {
            tblMonHoc.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblMonHoc.getColumnModel().getColumn(1).setPreferredWidth(500);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMonHocLayout = new javax.swing.GroupLayout(pnlMonHoc);
        pnlMonHoc.setLayout(pnlMonHocLayout);
        pnlMonHocLayout.setHorizontalGroup(
            pnlMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonHocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHieuChinhMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        pnlMonHocLayout.setVerticalGroup(
            pnlMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonHocLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(pnlMonHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHieuChinhMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jPanel6.getAccessibleContext().setAccessibleName("");
        jPanel6.getAccessibleContext().setAccessibleDescription("");

        pnlGD_LamViec.add(pnlMonHoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1600, 810));

        pnlThiTracNghiem.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongTinThiSinh.setBackground(new java.awt.Color(255, 213, 132));
        pnlThongTinThiSinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 2));
        pnlThongTinThiSinh.setPreferredSize(new java.awt.Dimension(1598, 140));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 0, 102));
        jLabel18.setText("Môn thi :");

        lblMonThi.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblMonThi.setForeground(new java.awt.Color(51, 0, 102));
        lblMonThi.setText("CSDL");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 0, 102));
        jLabel20.setText("Trình độ:");

        lblThinhDoBaiThi.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        lblThinhDoBaiThi.setForeground(new java.awt.Color(0, 153, 0));
        lblThinhDoBaiThi.setText("A");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 0, 102));
        jLabel22.setText("Họ tên:");

        lblHoTenThiSinh.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        lblHoTenThiSinh.setForeground(new java.awt.Color(51, 0, 102));
        lblHoTenThiSinh.setText("Nguyễn Nguyễn Nguyễn Nguyễn");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 0, 102));
        jLabel24.setText("Lớp    :");

        lblLopThiSinh.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        lblLopThiSinh.setForeground(new java.awt.Color(51, 0, 102));
        lblLopThiSinh.setText("D18CQCN01");

        pnlThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        pnlThoiGian.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(127, 153, 255)));

        jLabel26.setFont(new java.awt.Font("Sitka Subheading", 1, 36)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/timeIcon.png"))); // NOI18N
        jLabel26.setPreferredSize(new java.awt.Dimension(177, 100));

        lblTgConLai.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lblTgConLai.setForeground(new java.awt.Color(255, 0, 0));
        lblTgConLai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTgConLai.setText("30:00");
        lblTgConLai.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblTgConLaiPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout pnlThoiGianLayout = new javax.swing.GroupLayout(pnlThoiGian);
        pnlThoiGian.setLayout(pnlThoiGianLayout);
        pnlThoiGianLayout.setHorizontalGroup(
            pnlThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThoiGianLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblTgConLai, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlThoiGianLayout.setVerticalGroup(
            pnlThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblTgConLai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlThongTinThiSinhLayout = new javax.swing.GroupLayout(pnlThongTinThiSinh);
        pnlThongTinThiSinh.setLayout(pnlThongTinThiSinhLayout);
        pnlThongTinThiSinhLayout.setHorizontalGroup(
            pnlThongTinThiSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinThiSinhLayout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addGroup(pnlThongTinThiSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinThiSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHoTenThiSinh)
                    .addComponent(lblLopThiSinh))
                .addGap(186, 186, 186)
                .addGroup(pnlThongTinThiSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinThiSinhLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblThinhDoBaiThi))
                    .addGroup(pnlThongTinThiSinhLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMonThi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlThongTinThiSinhLayout.setVerticalGroup(
            pnlThongTinThiSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinThiSinhLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlThongTinThiSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblMonThi)
                    .addComponent(jLabel22)
                    .addComponent(lblHoTenThiSinh))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinThiSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinThiSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(lblThinhDoBaiThi))
                    .addGroup(pnlThongTinThiSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(lblLopThiSinh)))
                .addGap(20, 26, Short.MAX_VALUE))
            .addGroup(pnlThongTinThiSinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlThiTracNghiem.add(pnlThongTinThiSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);

        pnlBaiThi.setBackground(new java.awt.Color(255, 255, 193));
        pnlBaiThi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(0, 0, 102)));
        pnlBaiThi.setPreferredSize(new java.awt.Dimension(1220, 345));

        javax.swing.GroupLayout pnlBaiThiLayout = new javax.swing.GroupLayout(pnlBaiThi);
        pnlBaiThi.setLayout(pnlBaiThiLayout);
        pnlBaiThiLayout.setHorizontalGroup(
            pnlBaiThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlBaiThiLayout.setVerticalGroup(
            pnlBaiThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlBaiThi);
        pnlBaiThi.getAccessibleContext().setAccessibleParent(pnlBaiThi);

        pnlThiTracNghiem.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1280, 760));

        jPanel1.setBackground(new java.awt.Color(255, 213, 132));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 2));
        jPanel1.setPreferredSize(new java.awt.Dimension(318, 750));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bttNopBai.setBackground(new java.awt.Color(0, 193, 142));
        bttNopBai.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        bttNopBai.setForeground(new java.awt.Color(255, 255, 255));
        bttNopBai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TN/icon/nopBaiIcon.png"))); // NOI18N
        bttNopBai.setText("TRẢ BÀI");
        bttNopBai.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bttNopBai.setContentAreaFilled(false);
        bttNopBai.setOpaque(true);
        bttNopBai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttNopBaiActionPerformed(evt);
            }
        });
        bttNopBai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bttNopBai.setBackground(new Color(16, 212, 160));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bttNopBai.setBackground(new Color(0, 193, 142));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                bttNopBai.setBackground(new Color(1, 155, 115));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                bttNopBai.setBackground(new Color(0, 193, 142));//To change body of generated methods, choose Tools | Templates.
            }

        });
        jPanel1.add(bttNopBai, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 689, 313, 79));

        pnlLuaChon.setOpaque(false);
        pnlLuaChon.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(pnlLuaChon, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 12, 290, 670));

        pnlThiTracNghiem.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 130, -1, 770));

        pnlEditorModel.setVisible(false);
        pnlEditorModel.setAlignmentX(0.0F);
        pnlEditorModel.setAlignmentY(0.0F);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setText("Lớp       :");

        cbxLopThi.setEditable(true);
        cbxLopThi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbxLopThi.setPreferredSize(new java.awt.Dimension(64, 35));
        AutoCompleteDecorator.decorate(cbxLopThi,ObjectToStringConverter.DEFAULT_IMPLEMENTATION);

        cbxDoKho.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbxDoKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));
        cbxDoKho.setToolTipText("");
        cbxDoKho.setPreferredSize(new java.awt.Dimension(64, 35));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel4.setText("Trình độ:");

        cbxMaMonThi.setEditable(true);
        cbxMaMonThi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbxMaMonThi.setPreferredSize(new java.awt.Dimension(64, 35));
        AutoCompleteDecorator.decorate(cbxMaMonThi,ObjectToStringConverter.DEFAULT_IMPLEMENTATION);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel10.setText("Môn thi :");

        spnLanThi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        spnLanThi.setModel(new javax.swing.SpinnerNumberModel(2, 1, 2, 1));
        spnLanThi.setPreferredSize(new java.awt.Dimension(30, 35));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel11.setText("Lần       :");

        spnSoCauThi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        spnSoCauThi.setModel(new javax.swing.SpinnerNumberModel(30, 1, null, 1));
        spnSoCauThi.setPreferredSize(new java.awt.Dimension(30, 35));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel12.setText("Số câu   :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel13.setText("Ngày thi :");

        spnThoiGianThi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        spnThoiGianThi.setModel(new javax.swing.SpinnerNumberModel(30, 1, null, 1));
        spnThoiGianThi.setPreferredSize(new java.awt.Dimension(30, 35));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel14.setText("Thời gian:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel15.setText("phút");

        spnGioThi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        Date gioThiDate = new Date();
        Date gioThiMax = new Date();
        Date gioThiMin = new Date();
        SimpleDateFormat gioThiFormat = new SimpleDateFormat("HH:mm a");
        try{
            gioThiDate = gioThiFormat.parse("07:00 AM");
            gioThiMin = gioThiFormat.parse("07:00 AM");
            gioThiMax = gioThiFormat.parse("16:30 PM");
        }catch(Exception ex){
            System.out.println("Lỗi hiển thị spnGioThi");
        }
        spnGioThi.setModel(new SpinnerDateModel(gioThiDate, gioThiMin, gioThiMax, Calendar.HOUR_OF_DAY));
        JSpinner.DateEditor de = new JSpinner.DateEditor(spnGioThi, "HH:mm a");
        spnGioThi.setEditor(de);
        spnGioThi.setPreferredSize(new java.awt.Dimension(30, 35));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel16.setText("Giờ thi    :");

        dcNgayThi.setDateFormatString("dd/MM/yyyy");
        dcNgayThi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        dcNgayThi.setMinSelectableDate(new Date());

        cbxLan.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbxLan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

        cbxTrinhDo.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbxTrinhDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));

        cbxTenMonThi.setEditable(true);
        cbxTenMonThi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbxTenMonThi.setPreferredSize(new java.awt.Dimension(64, 35));
        AutoCompleteDecorator.decorate(cbxTenMonThi,ObjectToStringConverter.DEFAULT_IMPLEMENTATION);

        javax.swing.GroupLayout pnlThemSuaKyThiLayout = new javax.swing.GroupLayout(pnlThemSuaKyThi);
        pnlThemSuaKyThi.setLayout(pnlThemSuaKyThiLayout);
        pnlThemSuaKyThiLayout.setHorizontalGroup(
            pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemSuaKyThiLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThemSuaKyThiLayout.createSequentialGroup()
                        .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlThemSuaKyThiLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxLopThi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlThemSuaKyThiLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlThemSuaKyThiLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxMaMonThi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxTenMonThi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(26, 26, 26))
                    .addGroup(pnlThemSuaKyThiLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnThoiGianThi, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlThemSuaKyThiLayout.createSequentialGroup()
                        .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlThemSuaKyThiLayout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnGioThi, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlThemSuaKyThiLayout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbxDoKho, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlThemSuaKyThiLayout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(spnSoCauThi, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlThemSuaKyThiLayout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addGap(65, 65, 65))))
            .addGroup(pnlThemSuaKyThiLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxLan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxTrinhDo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlThemSuaKyThiLayout.setVerticalGroup(
            pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThemSuaKyThiLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(cbxMaMonThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbxTenMonThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxLopThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnLanThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(38, 38, 38)
                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxDoKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnSoCauThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(40, 40, 40)
                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnGioThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThemSuaKyThiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnThoiGianThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addComponent(cbxTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxLan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel19.setText("CÂU HỎI");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel21.setText("MÃ GIẢNG VIÊN");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel23.setText("MÃ MÔN HOC");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel25.setText("TRÌNH ĐỘ");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel27.setText("ĐÁP ÁN");

        txtCauHoi_BODE.setEditable(false);
        txtCauHoi_BODE.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtCauHoi_BODE.setPreferredSize(new java.awt.Dimension(6, 35));

        txtMaGV_BODE.setEditable(false);
        txtMaGV_BODE.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtMaGV_BODE.setPreferredSize(new java.awt.Dimension(6, 35));

        cbxMaMH_BODE.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbxMaMH_BODE.setPreferredSize(new java.awt.Dimension(31, 35));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel28.setText("NỘI DUNG");

        txtCauHoi.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtCauHoi.setPreferredSize(new java.awt.Dimension(1000, 35));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel29.setText("A");

        txtA_BODE.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtA_BODE.setPreferredSize(new java.awt.Dimension(1000, 35));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel30.setText("B");

        txtB_BODE.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtB_BODE.setPreferredSize(new java.awt.Dimension(1000, 35));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel31.setText("C");

        txtC_BODE.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtC_BODE.setPreferredSize(new java.awt.Dimension(1000, 35));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel32.setText("D");

        txtD_BODE.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txtD_BODE.setPreferredSize(new java.awt.Dimension(1000, 35));

        cbxTrinhDo_BODE.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbxTrinhDo_BODE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C" }));
        cbxTrinhDo_BODE.setPreferredSize(new java.awt.Dimension(35, 35));

        cbxDapAn_BODE.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        cbxDapAn_BODE.setForeground(new java.awt.Color(0, 0, 204));
        cbxDapAn_BODE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D" }));
        cbxDapAn_BODE.setPreferredSize(new java.awt.Dimension(35, 35));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCauHoi_BODE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaGV_BODE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxMaMH_BODE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxTrinhDo_BODE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxDapAn_BODE, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtA_BODE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtB_BODE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtC_BODE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtD_BODE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(txtCauHoi_BODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(txtCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(txtMaGV_BODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtA_BODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(cbxMaMH_BODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(cbxTrinhDo_BODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel31)
                                .addComponent(txtC_BODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(cbxDapAn_BODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel32)
                                .addComponent(txtD_BODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtB_BODE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbxMaKhoa.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        cbxMaKhoa.setPreferredSize(new java.awt.Dimension(31, 35));
        AutoCompleteDecorator.decorate(cbxMaKhoa,ObjectToStringConverter.DEFAULT_IMPLEMENTATION);

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel49.setText("Mã khoa:");

        javax.swing.GroupLayout pnlEditorModelLayout = new javax.swing.GroupLayout(pnlEditorModel);
        pnlEditorModel.setLayout(pnlEditorModelLayout);
        pnlEditorModelLayout.setHorizontalGroup(
            pnlEditorModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditorModelLayout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(pnlThemSuaKyThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173)
                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(cbxMaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(727, 727, 727))
            .addGroup(pnlEditorModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlEditorModelLayout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(1, 1, 1)))
        );
        pnlEditorModelLayout.setVerticalGroup(
            pnlEditorModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThemSuaKyThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlEditorModelLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addGroup(pnlEditorModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxMaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlEditorModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlEditorModelLayout.createSequentialGroup()
                    .addGap(319, 319, 319)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(320, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(pnlGD_LamViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlEditorModel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(1380, 1380, 1380)
                .addComponent(pnlDoiCoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(pnlThiTracNghiem, javax.swing.GroupLayout.PREFERRED_SIZE, 1600, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(pnlGD_LamViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlEditorModel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(pnlDoiCoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlThiTracNghiem, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttBoDeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttBoDeMousePressed
        if (canLuuThayDoi()) {
            return;
        }
        setBttColor(bttBoDe, unlBoDe, pnlBode);
        resetMaintabColor(new JPanel[]{bttGiaoVien, bttKhoaLop, bttKyThi, bttSinhVien, bttDoiCoSo, bttMonHoc},
                new JPanel[]{unlGiaoVien, unlKhoaLop, unlKyThi, unlSinhVien, unlMonHoc},
                new JPanel[]{pnlGiaoVien, pnlKhoaLop, pnlKyThi, pnlSinhVien, pnlDoiCoSo, pnlHoSoGiaoVien, pnlMonHoc});
        loadCBMAMH();
        loadBoDe();
    }//GEN-LAST:event_bttBoDeMousePressed

    private void bttKhoaLopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttKhoaLopMousePressed
        if (canLuuThayDoi()) {
            return;
        }
        bttKhoaLop.setOpaque(true);
        setBttColor(bttKhoaLop, unlKhoaLop, pnlKhoaLop);
        resetMaintabColor(new JPanel[]{bttGiaoVien, bttBoDe, bttKyThi, bttSinhVien, bttDoiCoSo, bttMonHoc},
                new JPanel[]{unlGiaoVien, unlBoDe, unlKyThi, unlSinhVien, unlMonHoc},
                new JPanel[]{pnlGiaoVien, pnlBode, pnlKyThi, pnlSinhVien, pnlDoiCoSo, pnlMonHoc, pnlHoSoGiaoVien});
        ManagePnlKhoaLop();
    }//GEN-LAST:event_bttKhoaLopMousePressed

    private void bttGiaoVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttGiaoVienMousePressed
        if (canLuuThayDoi()) {
            return;
        }
        setBttColor(bttGiaoVien, unlGiaoVien, pnlGiaoVien);
        resetMaintabColor(new JPanel[]{bttBoDe, bttKhoaLop, bttKyThi, bttSinhVien, bttDoiCoSo, bttMonHoc},
                new JPanel[]{unlBoDe, unlKhoaLop, unlKyThi, unlSinhVien, unlMonHoc},
                new JPanel[]{pnlBode, pnlKhoaLop, pnlKyThi, pnlSinhVien, pnlDoiCoSo, pnlHoSoGiaoVien, pnlMonHoc});
        refreshPnlGiaoVien();
    }//GEN-LAST:event_bttGiaoVienMousePressed

    private void bttSinhVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSinhVienMousePressed
        if (canLuuThayDoi()) {
            return;
        }
        setBttColor(bttSinhVien, unlSinhVien, pnlSinhVien);
        resetMaintabColor(new JPanel[]{bttGiaoVien, bttKhoaLop, bttKyThi, bttBoDe, bttDoiCoSo, bttMonHoc},
                new JPanel[]{unlGiaoVien, unlKhoaLop, unlKyThi, unlBoDe, unlMonHoc},
                new JPanel[]{pnlGiaoVien, pnlKhoaLop, pnlKyThi, pnlBode, pnlDoiCoSo, pnlHoSoGiaoVien, pnlMonHoc});
        loadCBMaLop_SINHVIEN();

    }//GEN-LAST:event_bttSinhVienMousePressed

    private void bttKyThiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttKyThiMousePressed
        if (canLuuThayDoi()) {
            return;
        }
        setBttColor(bttKyThi, unlKyThi, pnlKyThi);
        resetMaintabColor(new JPanel[]{bttGiaoVien, bttKhoaLop, bttBoDe, bttSinhVien, bttDoiCoSo, bttMonHoc},
                new JPanel[]{unlGiaoVien, unlKhoaLop, unlBoDe, unlSinhVien, unlSinhVien},
                new JPanel[]{pnlGiaoVien, pnlKhoaLop, pnlBode, pnlSinhVien, pnlDoiCoSo, pnlHoSoGiaoVien, pnlMonHoc});
        refreshPnlKyThi();
    }//GEN-LAST:event_bttKyThiMousePressed

    private void bttBoDeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttBoDeMouseEntered
        if (!pnlBode.isVisible()) {
            unlBoDe.setBackground(new Color(41, 83, 147));
            bttBoDe.setBackground(new Color(41, 83, 147));
        }
    }//GEN-LAST:event_bttBoDeMouseEntered

    private void bttKhoaLopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttKhoaLopMouseEntered
        if (!pnlKhoaLop.isVisible()) {
            unlKhoaLop.setBackground(new Color(41, 83, 147));
            bttKhoaLop.setBackground(new Color(41, 83, 147));
        }
    }//GEN-LAST:event_bttKhoaLopMouseEntered

    private void bttGiaoVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttGiaoVienMouseEntered
        if (!pnlGiaoVien.isVisible()) {
            unlGiaoVien.setBackground(new Color(41, 83, 147));
            bttGiaoVien.setBackground(new Color(41, 83, 147));
        }
    }//GEN-LAST:event_bttGiaoVienMouseEntered

    private void bttSinhVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSinhVienMouseEntered
        if (!pnlSinhVien.isVisible()) {
            unlSinhVien.setBackground(new Color(41, 83, 147));
            bttSinhVien.setBackground(new Color(41, 83, 147));
        }
    }//GEN-LAST:event_bttSinhVienMouseEntered

    private void bttKyThiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttKyThiMouseEntered
        if (!pnlKyThi.isVisible()) {
            unlKyThi.setBackground(new Color(41, 83, 147));
            bttKyThi.setBackground(new Color(41, 83, 147));
        }
    }//GEN-LAST:event_bttKyThiMouseEntered

    private void bttBoDeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttBoDeMouseExited
        if (!pnlBode.isVisible()) {
            bttBoDe.setBackground(mainBttColor);
            unlBoDe.setBackground(mainBttColor);
        }
    }//GEN-LAST:event_bttBoDeMouseExited

    private void bttKhoaLopMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttKhoaLopMouseExited
        if (!pnlKhoaLop.isVisible()) {
            bttKhoaLop.setBackground(mainBttColor);
            unlKhoaLop.setBackground(mainBttColor);
        }
    }//GEN-LAST:event_bttKhoaLopMouseExited

    private void bttGiaoVienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttGiaoVienMouseExited
        if (!pnlGiaoVien.isVisible()) {
            bttGiaoVien.setBackground(mainBttColor);
            unlGiaoVien.setBackground(mainBttColor);
        }
    }//GEN-LAST:event_bttGiaoVienMouseExited

    private void bttSinhVienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSinhVienMouseExited
        if (!pnlSinhVien.isVisible()) {
            bttSinhVien.setBackground(mainBttColor);
            unlSinhVien.setBackground(mainBttColor);
        }
    }//GEN-LAST:event_bttSinhVienMouseExited

    private void bttKyThiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttKyThiMouseExited
        if (!pnlKyThi.isVisible()) {
            bttKyThi.setBackground(mainBttColor);
            unlKyThi.setBackground(mainBttColor);
        }
    }//GEN-LAST:event_bttKyThiMouseExited

    private void bttDoiCoSoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttDoiCoSoMouseEntered
        if (!pnlDoiCoSo.isVisible()) {
            bttDoiCoSo.setBackground(new Color(41, 83, 147));
        }
    }//GEN-LAST:event_bttDoiCoSoMouseEntered

    private void bttDoiCoSoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttDoiCoSoMouseExited
        if (!pnlDoiCoSo.isVisible()) {
            bttDoiCoSo.setBackground(mainBttColor);
        }
    }//GEN-LAST:event_bttDoiCoSoMouseExited

    private void bttDoiCoSoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttDoiCoSoMousePressed
        bttDoiCoSo.setBackground(new Color(37, 74, 131));
        pnlDoiCoSo.setVisible(!pnlDoiCoSo.isVisible());
    }//GEN-LAST:event_bttDoiCoSoMousePressed

    private void bttUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttUserMouseEntered
        bttUser.setBackground(new Color(41, 83, 147));
        txtUserName.setForeground(new Color(240, 240, 240));
    }//GEN-LAST:event_bttUserMouseEntered

    private void bttUserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttUserMousePressed
        if (canLuuThayDoi()) {
            return;
        }
        pnlHoSoGiaoVien.setVisible(true);
        resetMaintabColor(new JPanel[]{bttGiaoVien, bttKhoaLop, bttBoDe, bttSinhVien, bttKyThi, bttDoiCoSo, bttMonHoc},
                new JPanel[]{unlGiaoVien, unlKhoaLop, unlBoDe, unlSinhVien, unlKyThi, unlMonHoc},
                new JPanel[]{pnlGiaoVien, pnlKhoaLop, pnlBode, pnlSinhVien, pnlKyThi, pnlDoiCoSo, pnlMonHoc});
    }//GEN-LAST:event_bttUserMousePressed

    private void bttUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttUserMouseExited
        bttUser.setBackground(new Color(80, 130, 217));
        txtUserName.setForeground(Color.white);
    }//GEN-LAST:event_bttUserMouseExited

    private void bttThemKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttThemKTActionPerformed

        disableFunc(new JButton[]{bttThiThu, bttThemKT, bttSuaKT});
        enableFunc(new JButton[]{bttLuuKT, bttHuyThayDoiKT});
        enableFunc(pnlThemSuaKyThi);
        tblDsKyThi.setRowSorter(null);
        mode = 1;

        ImageIcon icon = new ImageIcon(getClass().getResource("/TN/icon/newTblRowIcon.png"));
        JLabel imageLabel = new JLabel(icon);

        dftblDsKyThi.addRow(new Object[]{
            imageLabel, "", "", "", "", gvLogin.getHoTen(), "", "", "", "", "", "Lên lịch"

        });
        editDataRows.add(dftblDsKyThi.getRowCount() - 1);
        tblDsKyThi.setRowSelectionInterval(tblDsKyThi.getRowCount() - 1, tblDsKyThi.getRowCount() - 1);
        tblDsKyThi.scrollRectToVisible(tblDsKyThi.getCellRect(tblDsKyThi.getRowCount() - 1, 0, true));
    }//GEN-LAST:event_bttThemKTActionPerformed

    private void bttLuuKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttLuuKTActionPerformed

        KYTHI ktMoi = null;
        String error = "";
        for (int i : editDataRows) {
            //Lấy data và kiểm tra ngoại lệ
            String maMh = String.valueOf(dftblDsKyThi.getValueAt(i, 1));
            if (maMh.isEmpty()) {
                error += "mã môn học, ";
            }
            String tenMh = String.valueOf(dftblDsKyThi.getValueAt(i, 2));
            if (tenMh.isEmpty()) {
                error += "tên môn học, ";
            }
            String maLop = String.valueOf(dftblDsKyThi.getValueAt(i, 3));
            if (tenMh.isEmpty()) {
                error += "mã lớp, ";
            }
            String trinhDo = String.valueOf(dftblDsKyThi.getValueAt(i, 6));
            if (tenMh.isEmpty()) {
                error += "trình độ, ";
            }
            Date ngayThi = new Date();
            Date gioThi = new Date();
            int lan = 0;
            int soCau = 0;
            int tg = 0;
            try {
                lan = Integer.parseInt(String.valueOf(dftblDsKyThi.getValueAt(i, 4)));
            } catch (Exception e) {
                error += "lần, ";
            }
            try {
                soCau = Integer.parseInt(String.valueOf(dftblDsKyThi.getValueAt(i, 9)));
            } catch (Exception e) {
                error += "số câu, ";
            }
            try {
                tg = Integer.parseInt(String.valueOf(dftblDsKyThi.getValueAt(i, 10)).replaceAll("p", "").trim());
            } catch (Exception e) {
                error += "thời gian thi, ";
            }

            try {
                ngayThi = ngayFormat.parse(String.valueOf(dftblDsKyThi.getValueAt(i, 7)));
                gioThi = gioFormat.parse(String.valueOf(dftblDsKyThi.getValueAt(i, 8)));
                long l = ngayThi.getTime() + gioThi.getTime() + 25200000;
                ngayThi.setTime(l);

            } catch (ParseException ex) {
                error += "ngày giờ thi, ";
            }
            if (!error.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa điền đủ thông tin kỳ thi:\n" + "  " + error + " \n hãy điền đủ các thông tin trên", "Đăng ký kỳ thi thất bại", HEIGHT);
                return;
            }
            ktMoi = new KYTHI(maMh, tenMh, maLop, lan, gvLogin.getMaGV(),
                    gvLogin.getHoTen(), trinhDo, IODATA.formatDate(ngayThi), soCau, tg);
        }

        if (mode == 1) {
            try {

                IODATA.dangKy_kyThi(gvLogin, ktMoi);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Đăng ký kỳ thi thất bại", HEIGHT);
                return;
            }
            JOptionPane.showMessageDialog(rootPane, "Đăng ký kỳ thi thành công!");
        } else if (mode == 2) {
            try {
                IODATA.sua_kyThi(gvLogin, ktMoi);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Không thể sửa kỳ thi", HEIGHT);
                return;
            }
            JOptionPane.showMessageDialog(rootPane, "Đăng ký kỳ thi thành công!");
        }
        refreshPnlKyThi();
    }//GEN-LAST:event_bttLuuKTActionPerformed

    private void bttThiThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttThiThuActionPerformed
        int row = tblDsKyThi.getSelectedRow();
        String maMH = String.valueOf(dftblDsKyThi.getValueAt(row, 1));
        String trinhDo = String.valueOf(dftblDsKyThi.getValueAt(row, 6));
        int soCau = Integer.parseInt(String.valueOf(dftblDsKyThi.getValueAt(row, 9)));
        int soPhut = Integer.parseInt(String.valueOf(dftblDsKyThi.getValueAt(row, 10)).replace("p", "").trim());

        dsCauHoi = IODATA.getDeThi(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), maMH, trinhDo, soCau);
        pnlGD_LamViec.setVisible(false);
        pnlThiTracNghiem.setVisible(true);
        lblHoTenThiSinh.setText(gvLogin.getHoTen());
        lblLopThiSinh.setText(gvLogin.getMaKhoa());
        lblThinhDoBaiThi.setText(trinhDo);
        lblMonThi.setText(maMH);

        pnlBaiThi.setPreferredSize(new Dimension(pnlBaiThi.getPreferredSize().width, (pnlBaiThi.getPreferredSize().height * soCau) + 1));

        for (int i = 0; i < soCau; i++) {
            dsCauHoi.get(i).setSize(dsCauHoi.get(i).getPreferredSize());
            dsCauHoi.get(i).setLocation(0, i * dsCauHoi.get(i).getSize().height);
            pnlBaiThi.add(dsCauHoi.get(i));
            dsCauHoi.get(i).setVisible(true);

            pnlLuaChon.add(dsCauHoi.get(i).getPnlIsChon());
            dsCauHoi.get(i).getPnlIsChon().setVisible(true);
        }
        dongHo = new THOIGIAN(new Date(), soPhut, lblTgConLai);
        dongHo.start();
    }//GEN-LAST:event_bttThiThuActionPerformed

    private void tblDsKyThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDsKyThiMouseClicked
        enableFunc(new JButton[]{bttThiThu});
        int row = tblDsKyThi.getSelectedRow();
        int column = tblDsKyThi.getSelectedColumn();

        //".","MÃ MÔN THI","TÊN MÔN THI", "LỚP", "LẦN", "GVĐK", "TRÌNH ĐỘ", "NGÀY THI", "GIỜ BẮT ĐẦU", "SỐ CÂU ", "THỜI GIAN", "TRẠNG THÁI"
        String trangThai = String.valueOf(tblDsKyThi.getValueAt(row, 11));
        if (trangThai.compareToIgnoreCase("Đã kết thúc") == 0) {
            enableFunc(new JButton[]{bttBaoCaoBangDiemKt});
        } else {
            disableFunc(new JButton[]{bttBaoCaoBangDiemKt});
        }
    }//GEN-LAST:event_tblDsKyThiMouseClicked

    private void bttNopBaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttNopBaiActionPerformed
        nopBai();
    }//GEN-LAST:event_bttNopBaiActionPerformed

    private void lblTgConLaiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblTgConLaiPropertyChange
        if (lblTgConLai.getText().compareToIgnoreCase("00:00") == 0) {
            System.out.println("het gio");
            nopBai();
        }
    }//GEN-LAST:event_lblTgConLaiPropertyChange

    private void tblDsCauHoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDsCauHoiMouseClicked
        int row = tblDsCauHoi.getSelectedRow();
        int column = tblDsCauHoi.getSelectedColumn();
        String maGv = String.valueOf(tblDsCauHoi.getValueAt(row, 10));
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            System.out.println("double clicked");
            if (maGv.compareToIgnoreCase(this.gvLogin.getMaGV()) != 0) {
                JOptionPane.showMessageDialog(rootPane, "Không thể chỉnh sửa bộ đề của giáo viên khác");
                disableFunc(new JButton[]{bttXoaBD});
            } else {
                enableFunc(new JButton[]{bttXoaBD});
            }
        }
        String maMh = String.valueOf(tblDsCauHoi.getValueAt(row, column));

        if (maGv.compareToIgnoreCase(this.gvLogin.getMaGV()) != 0) {
            disableFunc(new JButton[]{bttXoaBD});
        } else {
            enableFunc(new JButton[]{bttXoaBD});
        }
        lblCauSo.setText("Câu " + tblDsCauHoi.getValueAt(row, 1).toString().trim() + ":");
        txtMaGV_BODE.setText(tblDsCauHoi.getValueAt(row, 9).toString());
        cbxTrinhDo_BODE.setSelectedItem(tblDsCauHoi.getModel().getValueAt(tblDsCauHoi.getSelectedRow(), 2) + "");
        lblNoiDung.setText(tblDsCauHoi.getValueAt(row, 4).toString());
        lblA.setText(tblDsCauHoi.getValueAt(row, 5).toString());
        lblB.setText(tblDsCauHoi.getValueAt(row, 6).toString());
        lblC.setText(tblDsCauHoi.getValueAt(row, 7).toString());
        lblD.setText(tblDsCauHoi.getValueAt(row, 8).toString());
        cbxDapAn_BODE.setSelectedItem(tblDsCauHoi.getModel().getValueAt(tblDsCauHoi.getSelectedRow(), 8) + "");
        cbxMaMH_BODE.setSelectedItem(tblDsCauHoi.getModel().getValueAt(tblDsCauHoi.getSelectedRow(), 1) + "");
    }//GEN-LAST:event_tblDsCauHoiMouseClicked

    private void bttSuaKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttSuaKTActionPerformed
        int row = tblDsKyThi.getSelectedRow();
        String maMH = String.valueOf(dftblDsKyThi.getValueAt(row, 1));
        String maLop = String.valueOf(dftblDsKyThi.getValueAt(row, 3));
        int lan = Integer.parseInt(String.valueOf(dftblDsKyThi.getValueAt(row, 4)));
        for (KYTHI kythi : dsKyThi) {
            if (kythi.getMaMH() == maMH && kythi.getMaLop() == maLop && kythi.getLan() == lan) {
                if (kythi.getNgayThi().before(new Date())) {
                    long kt = kythi.getNgayThi().getTime() + (kythi.getTg() * 60 * 1000);
                    Date ketThuc = new Date(kt);
                    if (ketThuc.before(new Date())) {
                        JOptionPane.showMessageDialog(rootPane, "Kỳ thi này đã kết thúc, không thể sửa", "Không thể sửa", HEIGHT);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Kỳ thi này đã bắt đầu, không thể sửa", "Không thể sửa", HEIGHT);
                        return;
                    }
                }
            }
        }
        disableFunc(new JButton[]{bttThemKT, bttSuaKT, bttThiThu});
        enableFunc(new JButton[]{bttLuuKT, bttHuyThayDoiKT});
        mode = 2;
        ImageIcon icon = new ImageIcon(getClass().getResource("/TN/icon/editRowIcon.png"));
        JLabel imageLabel = new JLabel(icon);
        tblDsKyThi.setValueAt(imageLabel, row, 0);
        editDataRows.add(row);
        //".","MÃ MÔN THI","TÊN MÔN THI", "LỚP", "LẦN", "GVĐK", "TRÌNH ĐỘ", "NGÀY THI", "GIỜ BẮT ĐẦU", "SỐ CÂU ", "THỜI GIAN", "TRẠNG THÁI"
    }//GEN-LAST:event_bttSuaKTActionPerformed

    private void bttHuyThayDoiKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttHuyThayDoiKTActionPerformed
        int chon = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn hủy thay đổi? ", "Hủy thay đổi", JOptionPane.YES_NO_OPTION, HEIGHT);
        if (chon == JOptionPane.YES_OPTION) {
            refreshPnlKyThi();
        }
    }//GEN-LAST:event_bttHuyThayDoiKTActionPerformed

    private void bttThemBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttThemBDActionPerformed
        putDataTbl(dftblDsCauHoi);

        txtTimKiemCH.setText("");
        enableFunc(new JButton[]{bttLuuBD, bttHuyThayDoiBD, bttPhucHoiBD});

        ImageIcon icon = new ImageIcon(getClass().getResource("/TN/icon/newTblRowIcon.png"));
        JLabel imageLabel = new JLabel(icon);

        dftblDsCauHoi.addRow(new Object[]{
            imageLabel, "New", "", "", "", "", "", "", "", "", gvLogin.getMaGV()
        // ".","Câu Hỏi", "Mã MH", "Trình độ", "Nội dung", "A", "B", "C", "D", "ĐÁP ÁN", "MÃ GV"

        });
        tblDsCauHoi.setRowSelectionInterval(tblDsCauHoi.getRowCount() - 1, tblDsCauHoi.getRowCount() - 1);
        tblDsCauHoi.scrollRectToVisible(tblDsCauHoi.getCellRect(tblDsCauHoi.getRowCount() - 1, 0, true));
    }//GEN-LAST:event_bttThemBDActionPerformed

    private void bttLuuBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttLuuBDActionPerformed
        ArrayList<CAUHOI> dsCauhoi = new ArrayList<>();
        boolean flag = true;
        boolean flag1 = false;
        int rowError = 0;
        for (int i = 0; i < dftblDsCauHoi.getRowCount(); i++) {
            Object[] cauhoi = ((Vector) dftblDsCauHoi.getDataVector().elementAt(i)).toArray();
            for (int j = 1; j < 11; j++) {
                if (String.valueOf(cauhoi[j]).isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Không thể ghi vì bạn chưa điền đầy đủ thông tin câu hỏi", "Không thể ghi", HEIGHT);
                    tblDsCauHoi.setRowSelectionInterval(i, i);
                    tblDsCauHoi.scrollRectToVisible(tblDsCauHoi.getCellRect(i, 0, true));
                    return;
                }
            }
            rowError = i;
            if (String.valueOf(dftblDsCauHoi.getValueAt(tblDsCauHoi.getRowCount() - 1, 4)).compareToIgnoreCase(String.valueOf(dftblDsCauHoi.getValueAt(i, 4))) == 0) {
                if (i == dftblDsCauHoi.getRowCount() - 1) {
                    flag1 = true;

                    System.out.println("Không bằng");
                } else {
                    flag1 = false;
                    //  System.out.println(dftblDsCauHoi.getDataVector().elementAt(i).get(4)+":=: " + String.valueOf(dftblDsCauHoi.getDataVector().elementAt(dftblDsCauHoi.getRowCount()-1).get(4)));
                    //  System.out.println("Bằng");
                    break;
                }
            }
            if (String.valueOf(cauhoi[5]).equalsIgnoreCase(String.valueOf(cauhoi[6]))) {
                flag = false;
                break;
            } else if (String.valueOf(cauhoi[5]).equalsIgnoreCase(String.valueOf(cauhoi[7]))) {
                flag = false;
                break;
            } else if (String.valueOf(cauhoi[5]).equalsIgnoreCase(String.valueOf(cauhoi[8]))) {
                flag = false;
                break;
            } else if (String.valueOf(cauhoi[6]).equalsIgnoreCase(String.valueOf(cauhoi[7]))) {
                flag = false;
                break;
            } else if (String.valueOf(cauhoi[6]).equalsIgnoreCase(String.valueOf(cauhoi[8]))) {
                flag = false;
                break;
            } else if (String.valueOf(cauhoi[7]).equalsIgnoreCase(String.valueOf(cauhoi[8]))) {
                flag = false;
                break;
            } else {
                //".","Câu Hỏi", "Mã MH", "Trình độ", "Nội dung", "A", "B", "C", "D", "ĐÁP ÁN", "MÃ GV"
                int sttCauHoi = 0;
                if (String.valueOf(cauhoi[1]).trim().compareToIgnoreCase("New") != 0) {
                    sttCauHoi = Integer.parseInt(String.valueOf(cauhoi[1]).trim());
                }
                String maGv = String.valueOf(cauhoi[10]);
                if (maGv.compareToIgnoreCase(gvLogin.getMaGV()) != 0) {
                    continue;
                }
                String maMh = String.valueOf(cauhoi[2]);
                String trinhDo = String.valueOf(cauhoi[3]);
                String noiDung = String.valueOf(cauhoi[4]);
                String A = String.valueOf(cauhoi[5]);
                String B = String.valueOf(cauhoi[6]);
                String C = String.valueOf(cauhoi[7]);
                String D = String.valueOf(cauhoi[8]);
                String dapAn = String.valueOf(cauhoi[9]);

                CAUHOI cauHoi = new CAUHOI(sttCauHoi, maMh, trinhDo, noiDung, A, B, C, D, dapAn, maGv);
                dsCauhoi.add(cauHoi);
            }
        }
        if (flag == false) {
            tblDsCauHoi.setRowSelectionInterval(rowError, rowError);
            tblDsCauHoi.scrollRectToVisible(tblDsCauHoi.getCellRect(rowError, 0, true));
            JOptionPane.showMessageDialog(rootPane, "Các dáp án không được trùng nhau.");
        } else if (flag1 == false) {
            JOptionPane.showMessageDialog(rootPane, "Đã có câu hỏi trong bộ đề.");
        } else {
            IODATA.ghiBoDe(gvLogin, dsCauhoi);
            loadBoDe();
            loadCBMAMH();
        }
    }//GEN-LAST:event_bttLuuBDActionPerformed

    private void bttHuyThayDoiBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttHuyThayDoiBDActionPerformed
        int chon = JOptionPane.showConfirmDialog(rootPane, "Những chỉnh sửa sẽ không được lưu? ", "Hủy thay đổi", JOptionPane.YES_NO_OPTION, HEIGHT);
        if (chon == JOptionPane.YES_OPTION) {
            loadBoDe();
        }
    }//GEN-LAST:event_bttHuyThayDoiBDActionPerformed

    private void bttXoaBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttXoaBDActionPerformed
        int row = tblDsCauHoi.getSelectedRow();

        int choosen = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn xóa câu hỏi này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (choosen == JOptionPane.YES_OPTION) {
            putDataTbl(dftblDsCauHoi);
            enableFunc(new JButton[]{bttPhucHoiBD, bttLuuBD, bttHuyThayDoiBD});
            dftblDsCauHoi.removeRow(row);
        }
    }//GEN-LAST:event_bttXoaBDActionPerformed

    private void bttPhucHoiBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttPhucHoiBDActionPerformed
        phucHoiBD();
    }//GEN-LAST:event_bttPhucHoiBDActionPerformed

    public void phucHoiBD() {
        enableFunc(new JButton[]{bttLuuBD, bttHuyThayDoiBD});

        Vector<Object> dataTable2 = (Vector<Object>) dataTblStack.pop();
        dftblDsCauHoi.setRowCount(0);
        for (Object vector : dataTable2) {
            dftblDsCauHoi.addRow((Vector) vector);
        }

        if (dataTblStack.empty()) {
            disableFunc(new JButton[]{bttPhucHoiBD, bttHuyThayDoiBD, bttLuuBD});
            loadCBMAMH();
            loadBoDe();
        }
    }

    private void tblDsCauHoiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblDsCauHoiFocusGained

    }//GEN-LAST:event_tblDsCauHoiFocusGained

    private void btn_ThemSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSinhVienActionPerformed
        selectedSv.setMaSV(txtMaSV_SINHVIEN.getText());
        selectedSv.setHo(txtHo_SINHNVIEN.getText());
        selectedSv.setTen(txtTen_SINHVIEN.getText());
        selectedSv.setNgaySinh(IODATA.formatDate(txtNgaySinh_SINHVIEN.getDate()));
        selectedSv.setDiaChi(txtDiaChi_SINHVIEN.getText());
        selectedSv.setMatKhauSV(String.valueOf(txtMauKhau_SINHVIEN.getPassword()));
        selectedSv.setMaLop((String) cbxLop_SINHVIEN.getSelectedItem());
        System.out.println(selectedSv.getNgaySinh());
        if (selectedSv.getMaSV().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống Mã sinh viên");
            jLabel33.setForeground(Color.RED);
        } else if (selectedSv.getHo().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống Họ");
            jLabel34.setForeground(Color.RED);
        } else if (selectedSv.getTen().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống Tên");
            jLabel35.setForeground(Color.RED);
        } else if (!selectedSv.getHo().matches(tiengViet + "+")) {
            JOptionPane.showMessageDialog(rootPane, "Nhập họ tiếng việt không nhập kí tự đặc biệt");
        } else if (!selectedSv.getTen().matches(tiengViet + "+")) {
            JOptionPane.showMessageDialog(rootPane, "Nhập tên tiếng việt không nhập kí tự đặc biệt");
        } else if (selectedSv.getDiaChi().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống Địa Chỉ");
            jLabel37.setForeground(Color.RED);
        } else if (selectedSv.getMatKhauSV().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống Mật Khẩu");
            jLabel38.setForeground(Color.RED);
        } else {
            try {
                IODATA.themSinhVien(this.gvLogin.getTenLogin(), this.gvLogin.getMatKhau(), this.gvLogin.getCoSo(), selectedSv);
                JOptionPane.showMessageDialog(rootPane, "Thêm Thành Công!");
                resetSinhVien();
                loadSinhVien(selectedSv.getMaLop());
                jLabel33.setForeground(Color.BLACK);
                jLabel34.setForeground(Color.BLACK);
                jLabel35.setForeground(Color.BLACK);
                jLabel37.setForeground(Color.BLACK);
                jLabel38.setForeground(Color.BLACK);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(MAINFORM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btn_ThemSinhVienActionPerformed

    private void btn_SuaSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaSinhVienActionPerformed
        selectedSv.setMaSV(txtMaSV_SINHVIEN.getText());
        selectedSv.setHo(txtHo_SINHNVIEN.getText());
        selectedSv.setTen(txtTen_SINHVIEN.getText());
        selectedSv.setNgaySinh(IODATA.formatDate(txtNgaySinh_SINHVIEN.getDate()));
        selectedSv.setDiaChi(txtDiaChi_SINHVIEN.getText());
        selectedSv.setMatKhauSV(String.valueOf(txtMauKhau_SINHVIEN.getPassword()));
        selectedSv.setMaLop((String) cbxLop_SINHVIEN.getSelectedItem());
        System.out.println(selectedSv.getNgaySinh());
        if (selectedSv.getMaSV().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "VUI LÒNG CHỌN SINH VIÊN CẦN SỬA!");
            jLabel33.setForeground(Color.RED);
        } else if (selectedSv.getHo().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống Họ");
            jLabel34.setForeground(Color.RED);
        } else if (selectedSv.getTen().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống Tên");
            jLabel35.setForeground(Color.RED);
        } else if (!selectedSv.getHo().matches(tiengViet + "+")) {
            JOptionPane.showMessageDialog(rootPane, "Nhập họ tiếng việt không nhập kí tự đặc biệt");
        } else if (!selectedSv.getTen().matches(tiengViet + "+")) {
            JOptionPane.showMessageDialog(rootPane, "Nhập tên tiếng việt không nhập kí tự đặc biệt");
        } else if (selectedSv.getDiaChi().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống Địa Chỉ");
            jLabel37.setForeground(Color.RED);
        } else if (selectedSv.getMatKhauSV().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống Mật Khẩu");
            jLabel38.setForeground(Color.RED);
        } else {
            try {
                IODATA.suaSinhVien(this.gvLogin.getTenLogin(), this.gvLogin.getMatKhau(), this.gvLogin.getCoSo(), selectedSv);
                JOptionPane.showMessageDialog(rootPane, "Sửa Thành Công!");
                resetSinhVien();
                loadSinhVien(selectedSv.getMaLop());
                jLabel33.setForeground(Color.BLACK);
                jLabel34.setForeground(Color.BLACK);
                jLabel35.setForeground(Color.BLACK);
                jLabel37.setForeground(Color.BLACK);
                jLabel38.setForeground(Color.BLACK);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(MAINFORM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_SuaSinhVienActionPerformed

    private void btn_XoaSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaSinhVienActionPerformed
        selectedSv.setMaSV(txtMaSV_SINHVIEN.getText());
        selectedSv.setMaLop(String.valueOf(cbxLop_SINHVIEN.getSelectedItem()));
        if (selectedSv.getMaSV().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn sinh viên cần xóa !");
        } else {
            String sql = "EXEC SP_XOASINHVIEN ?";
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    Connection conn = JDBCCONNECT.getConnection(this.gvLogin.getTenLogin(), this.gvLogin.getMatKhau(), this.gvLogin.getCoSo());
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, selectedSv.getMaSV());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane, "Xóa thành công!");
                    resetSinhVien();
                    loadSinhVien(selectedSv.getMaLop());

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                } catch (ParseException ex) {
                    Logger.getLogger(MAINFORM.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Xóa thất bại! Vui lòng thử lại.");
            }
        }
    }//GEN-LAST:event_btn_XoaSinhVienActionPerformed

    private void btn_LamMoiSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiSinhVienActionPerformed
        resetSinhVien();
    }//GEN-LAST:event_btn_LamMoiSinhVienActionPerformed

    class MenuLOP extends JPopupMenu {

        private JMenuItem add;
        private JMenuItem delete;
        private JMenuItem save;
        private JMenuItem refresh;

        private ImageIcon addIcon = new ImageIcon(getClass().getResource("/TN/icon/icons8_add_20px.png"));
        private ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/TN/icon/icons8_delete_20px.png"));
        private ImageIcon saveIcon = new ImageIcon(getClass().getResource("/TN/icon/icons8_save_20px.png"));
        private ImageIcon refreshIcon = new ImageIcon(getClass().getResource("/TN/icon/icons8_refresh_20px.png"));

        public MenuLOP() {
            add = new JMenuItem("Thêm lớp", addIcon);
            add(add);
            add.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DefaultTableModel model = (DefaultTableModel) tblDSLop.getModel();
                    ImageIcon icon = new ImageIcon(getClass().getResource("/TN/icon/newTblRowIcon.png"));
                    JLabel imageLabel = new JLabel(icon);

                    dftblDsLop.addRow(new Object[]{
                        imageLabel, "", "", GetMaKhoa

                    });
                    editDataRows.add(dftblDsLop.getRowCount() - 1);
                    tblDSLop.setRowSelectionInterval(tblDSLop.getRowCount() - 1, tblDSLop.getRowCount() - 1);
                    tblDSLop.scrollRectToVisible(tblDSLop.getCellRect(tblDSLop.getRowCount() - 1, 0, true));
                }
            });

            delete = new JMenuItem("Xóa lớp", deleteIcon);
            add(delete);
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    int row = tblDSLop.getSelectedRow();
                    String maLop = String.valueOf(dftblDsLop.getValueAt(row, 1)).trim();
                    String tenLop = String.valueOf(dftblDsLop.getValueAt(row, 2)).trim();
                    try {
                        int choose = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn xóa\n lớp " + maLop + " - " + tenLop + " ?",
                                "Xóa lớp " + maLop + " ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (choose == JOptionPane.YES_OPTION) {
                            xoaLop(GetMaLop);
                            JOptionPane.showMessageDialog(rootPane, "Đã xóa lớp " + maLop, "Xoá lớp thành công", JOptionPane.INFORMATION_MESSAGE);
                            ShowdsLop(GetMaKhoa);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Xóa lớp thất bại", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            save = new JMenuItem("Ghi", saveIcon);
            add(save);
            save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (editDataRows.isEmpty()) {
                        ArrayList<LOP> dslop = new ArrayList<>();
                        for (int i = 0; i < dftblDsLop.getRowCount(); i++) {
                            String maLop = String.valueOf(dftblDsLop.getValueAt(i, 1));
                            String tenLop = String.valueOf(dftblDsLop.getValueAt(i, 2));
                            String maKhoa = String.valueOf(dftblDsLop.getValueAt(i, 3));

                            LOP lop = new LOP(maLop, tenLop, maKhoa);
                            dslop.add(lop);
                        }
                        IODATA.ghiDsLop(gvLogin, dslop);
                        JOptionPane.showMessageDialog(rootPane, "Thông tin lớp đã được cập nhật và lưu lại",
                                "Đã lưu thay đổi", JOptionPane.INFORMATION_MESSAGE);
                        ShowdsLop(GetMaKhoa);
                    } else {
                        int i = editDataRows.get(0);
                        System.out.println(i);
                        String maLop = String.valueOf(dftblDsLop.getValueAt(i, 1)).trim();
                        String tenLop = String.valueOf(dftblDsLop.getValueAt(i, 2)).trim();
                        String maKhoa = String.valueOf(dftblDsLop.getValueAt(i, 3)).trim();

                        if (maLop == "" || tenLop == "" || maKhoa == "") {
                            JOptionPane.showMessageDialog(rootPane, "Bạn chưa điền đầy đủ thông tin lớp muốn thêm", "Không thể thêm lớp", JOptionPane.ERROR_MESSAGE);
                        } else {
                            try {
                                themLop(maLop, tenLop, maKhoa);
                                ShowdsLop(maKhoa);
                                JOptionPane.showMessageDialog(rootPane, "Đã thêm lớp mới: \n"
                                        + "Lớp: " + maLop + " - " + tenLop + "\n"
                                        + "Thuộc khoa: " + maKhoa, "Thêm lớp thành công", JOptionPane.INFORMATION_MESSAGE);
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Thêm lớp thất bại", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            });

            refresh = new JMenuItem("Làm mới", refreshIcon);
            add(refresh);
            refresh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ShowdsLop(GetMaKhoa);
                }
            });
        }

        public void disableAddDeleteItem() {
            this.add.setEnabled(false);
            this.delete.setEnabled(false);
        }

        public void disableSaveItem() {
            this.save.setEnabled(false);
        }
    }

    private void tblDSLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSLopMouseClicked
        int selectedRow = tblDSLop.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblDSLop.getModel();
        String s = model.getValueAt(selectedRow, 1).toString();
        GetMaLop = s;

        //click chuot phai
        if (evt.getModifiers() == InputEvent.BUTTON3_MASK) {
            if (gvLogin.getRole().compareTo("COSO") == 0) {
                MenuLOP menu = new MenuLOP();
                if (!editDataRows.isEmpty()) {
                    menu.disableAddDeleteItem();
                }
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }

        // JOptionPane.showMessageDialog(this, GetMaLop + GetMaKhoa);

    }//GEN-LAST:event_tblDSLopMouseClicked

    private void tbDSKhoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDSKhoaMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbDSKhoa.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tbDSKhoa.getModel();
        String maKhoaChoose = model.getValueAt(selectedRow, 0).toString().trim();
        String tenKhoaChoose = model.getValueAt(selectedRow, 1).toString().trim();
        GetMaKhoa = maKhoaChoose;

        lblMaKhoa.setText(maKhoaChoose + " - " + tenKhoaChoose);

        ShowdsLop(GetMaKhoa);
    }//GEN-LAST:event_tbDSKhoaMouseClicked

    private void tbDSKhoaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDSKhoaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDSKhoaKeyPressed

    private void txtMaKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKhActionPerformed

    private void txtTenKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbDSKhoa.getModel();
        String Makhoa = txtMaKh.getText();
        String Tenkhoa = txtTenKh.getText();
        String MaCS = coSo.getMaCoso();
        if (!txtMaKh.getText().isEmpty() && !txtTenKh.getText().isEmpty()) {

            try {
                themKhoa(Makhoa, Tenkhoa, MaCS);
                dsKhoa = IODATA.getDSKhoa(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), "");
                cbxMaKhoa.removeAllItems();
                for (KHOA khoa : dsKhoa) {
                    cbxMaKhoa.addItem(khoa);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "them khoa that bai");
                Logger.getLogger(MAINFORM.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "không được bỏ trống tt");
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void BtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeleteActionPerformed
        DefaultTableModel model = (DefaultTableModel) tbDSKhoa.getModel();

        try {
            xoaKhoa(GetMaKhoa);

        } catch (SQLException ex) {
            Logger.getLogger(MAINFORM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_BtnDeleteActionPerformed

    private void BtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateActionPerformed
        // TODO add your handling code here:
        int i = tbDSKhoa.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tbDSKhoa.getModel();

        String MaKhoa = model.getValueAt(i, 0).toString();
        String TenKhoa = model.getValueAt(i, 1).toString();
        String MaCS = coSo.getMaCoso();

        try {
            capNhapKhoa(MaKhoa, TenKhoa, MaCS);
        } catch (SQLException ex) {
            Logger.getLogger(MAINFORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnUpdateActionPerformed

    private void bttThemGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttThemGVActionPerformed
        enableFunc(pnlThemGV);
        disableFunc(new JButton[]{bttThemGV, bttXoaGV});

    }//GEN-LAST:event_bttThemGVActionPerformed

    private void bttXoaGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttXoaGVActionPerformed
        int row = tblDsGiaoVien.getSelectedRow();
        String maGv = String.valueOf(tblDsGiaoVien.getValueAt(row, 0));
        String hoTen = String.valueOf(tblDsGiaoVien.getValueAt(row, 1)).trim() + String.valueOf(tblDsGiaoVien.getValueAt(row, 2)).trim();;

        int choose = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn xóa giảng viên \n"
                + maGv + " - " + hoTen, "Xóa giáo viên " + maGv + " ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choose == JOptionPane.YES_OPTION) {
            try {
                IODATA.xoaGiaoVien(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), maGv);
                refreshPnlGiaoVien();
                JOptionPane.showMessageDialog(rootPane, "Đã xóa giáo viên " + maGv + " - " + hoTen, "Xoá giáo viên thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Xoá giáo viên thất bại", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bttXoaGVActionPerformed

    private void tblDsGiaoVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDsGiaoVienMouseClicked
        int row = tblDsGiaoVien.getSelectedRow();
        String magv = String.valueOf(tblDsGiaoVien.getValueAt(row, 0));
        String[] ttgv = IODATA.layTtLogin(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), magv);

        lblTenLoginGV.setText(ttgv[0]);
        lblQuyenGV.setText(ttgv[1]);
        if (ttgv[0] == null && link1.isEmpty()) {
            enableFunc(new JButton[]{bttTaoLogin});
        } else {
            disableFunc(new JButton[]{bttTaoLogin});
        }

        if (gvLogin.getRole().compareTo("COSO") == 0) {
            bttXoaGV.setEnabled(true);
        }
    }//GEN-LAST:event_tblDsGiaoVienMouseClicked

    private void bttXacNhanThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttXacNhanThemActionPerformed

        GIAOVIEN newGv = new GIAOVIEN(txtMaGv_GiaoVien.getText(),
                txtHoGv_GiaoVien.getText(),
                 txtTenGv_GiaoVien.getText(),
                 txtHocVi_GiaoVien.getText(),
                ((KHOA) cbxKhoa_GiaoVien.getSelectedItem()).getMaKhoa(),
                 "");

        boolean kt = true;
        if (newGv.getMaGV().isEmpty()) {
            txtMaGv_GiaoVien.setText("* Mã giáo viên không được bỏ trống");
            txtMaGv_GiaoVien.setForeground(Color.RED);
            kt = false;
        }
        if (newGv.getMaGV().length() > 8) {
            txtMaGv_GiaoVien.setText("* Mã giáo viên không được vượt quá 8 ký tự");
            txtMaGv_GiaoVien.setForeground(Color.RED);
            kt = false;
        }
        if (newGv.getHo().isEmpty()) {
            txtHoGv_GiaoVien.setText("* Họ không được phép trống");
            txtHoGv_GiaoVien.setForeground(Color.RED);
            kt = false;
        }
        if (newGv.getHo().length() > 40) {
            txtHoGv_GiaoVien.setText("* Độ dài họ không được quá 40 ký tự");
            txtHoGv_GiaoVien.setForeground(Color.RED);
            kt = false;
        }
        if (newGv.getTen().isEmpty()) {
            txtTenGv_GiaoVien.setText("* Tên giáo viên không được bỏ trống");
            txtTenGv_GiaoVien.setForeground(Color.RED);
            kt = false;
        }
        if (newGv.getTen().length() > 10) {
            txtTenGv_GiaoVien.setText("* Độ dài tên không được quá 10 ký tự");
            txtTenGv_GiaoVien.setForeground(Color.RED);
            kt = false;
        }

        if (kt) {
            try {
                IODATA.themGiaoVien(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), newGv);
                refreshPnlGiaoVien();
                int choose = JOptionPane.showConfirmDialog(rootPane, "Đã thêm giáo viên mới. \n "
                        + "Bạn có muốn tạo tài khoản login cho giáo viên " + newGv.getMaGV() + " ngay bây giờ không ?", "Thêm giáo viên mới thành công!", JOptionPane.YES_NO_OPTION);
                if (choose == JOptionPane.YES_OPTION) {
                    new TAOTAIKHOAN(this, gvLogin, newGv.getMaGV()).setVisible(true);
                }
            } catch (SQLException ex) {
                System.out.println("ERROR: Lỗi tạo tài khoản gv MAINFORM.bttXacNhanThemActionPerformed()");
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Tạo tài khoản thất bại!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bttXacNhanThemActionPerformed

    private void txtMaGv_GiaoVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaGv_GiaoVienMouseClicked
        if (txtMaGv_GiaoVien.getForeground() == Color.RED) {
            txtMaGv_GiaoVien.setText("");
            txtMaGv_GiaoVien.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtMaGv_GiaoVienMouseClicked

    private void txtHoGv_GiaoVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHoGv_GiaoVienMouseClicked
        if (txtHoGv_GiaoVien.getForeground() == Color.RED) {
            txtHoGv_GiaoVien.setText("");
            txtHoGv_GiaoVien.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtHoGv_GiaoVienMouseClicked

    private void txtTenGv_GiaoVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenGv_GiaoVienMouseClicked
        if (txtTenGv_GiaoVien.getForeground() == Color.RED) {
            txtTenGv_GiaoVien.setText("");
            txtTenGv_GiaoVien.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtTenGv_GiaoVienMouseClicked

    private void txtHocVi_GiaoVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHocVi_GiaoVienMouseClicked
        if (txtHocVi_GiaoVien.getForeground() == Color.RED) {
            txtHocVi_GiaoVien.setText("");
            txtHocVi_GiaoVien.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtHocVi_GiaoVienMouseClicked

    private void bttTaoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttTaoLoginActionPerformed
        int row = tblDsGiaoVien.getSelectedRow();
        String maGv = String.valueOf(dftblDsGiaoVien.getValueAt(row, 0));
        new TAOTAIKHOAN(this, gvLogin, maGv).setVisible(true);
    }//GEN-LAST:event_bttTaoLoginActionPerformed

    private void cbxCoSoHienTaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCoSoHienTaiActionPerformed
        if (canLuuThayDoi()) {
            return;
        }
        COSO choosen = (COSO) cbxCoSoHienTai.getSelectedItem();
        if (choosen == coSo) {
            link1 = "";
            enableFunc(pnlHieuChinhGV);
        } else {
            link1 = "LINK1.";
            disableFunc(pnlHieuChinhGV);
        }
        if (pnlBode.isVisible()) {
            loadCBMAMH();
            loadBoDe();
        } else if (pnlKhoaLop.isVisible()) {
            ManagePnlKhoaLop();
        } else if (pnlGiaoVien.isVisible()) {
            refreshPnlGiaoVien();
        } else if (pnlKyThi.isVisible()) {
            refreshPnlKyThi();
        } else if (pnlSinhVien.isVisible()) {
            loadCBMaLop_SINHVIEN();
        }
    }//GEN-LAST:event_cbxCoSoHienTaiActionPerformed

    private void cbxLop_SINHVIENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLop_SINHVIENActionPerformed
        // TODO add your handling code here:
        String maLop = (String) cbxLop_SINHVIEN.getSelectedItem();
        try {
            loadSinhVien(maLop);
        } catch (ParseException ex) {
            Logger.getLogger(MAINFORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxLop_SINHVIENActionPerformed

    private void tblSinhVienLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSinhVienLopMouseClicked
        int viTriDongVuaBam = tblSinhVienLop.getSelectedRow();
        txtMaSV_SINHVIEN.setText(tblSinhVienLop.getValueAt(viTriDongVuaBam, 0).toString());
        txtHo_SINHNVIEN.setText(tblSinhVienLop.getValueAt(viTriDongVuaBam, 1).toString());
        txtTen_SINHVIEN.setText(tblSinhVienLop.getValueAt(viTriDongVuaBam, 2).toString());
        txtDiaChi_SINHVIEN.setText(tblSinhVienLop.getValueAt(viTriDongVuaBam, 4).toString());
        txtMauKhau_SINHVIEN.setText(String.valueOf(tblSinhVienLop.getValueAt(viTriDongVuaBam, 6)));
        try {
            txtNgaySinh_SINHVIEN.setDate(ngayFormat.parse(tblSinhVienLop.getValueAt(viTriDongVuaBam, 3).toString()));
        } catch (ParseException ex) {
            System.out.println("ERROR: " + ex.getMessage() + " MAINFORM.tbl_SinhVienMouseClicked()");
        }
        txtMaSV_SINHVIEN.setEnabled(false);

        selectedSv.setMaSV(txtMaSV_SINHVIEN.getText());
        selectedSv.setHo(txtHo_SINHNVIEN.getText());
        selectedSv.setTen(txtTen_SINHVIEN.getText());
        selectedSv.setNgaySinh(IODATA.formatDate(txtNgaySinh_SINHVIEN.getDate()));
        selectedSv.setDiaChi(txtDiaChi_SINHVIEN.getText());
        selectedSv.setMatKhauSV(String.valueOf(txtMauKhau_SINHVIEN.getPassword()));
        selectedSv.setMaLop((String) cbxLop_SINHVIEN.getSelectedItem());
        try {
            loadBangDiem(txtMaSV_SINHVIEN.getText());
        } catch (ParseException ex) {
            Logger.getLogger(MAINFORM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblSinhVienLopMouseClicked

    private void bttReportBaiThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttReportBaiThiActionPerformed

        DefaultTableModel dftbl = (DefaultTableModel) tblBangDiem.getModel();
        int row = tblBangDiem.getSelectedRow();
        int maBt = Integer.valueOf(String.valueOf(dftbl.getValueAt(row, 6)).trim());
        String monThi = String.valueOf(dftbl.getValueAt(row, 1)).trim();
        String ngayThi = String.valueOf(dftbl.getValueAt(row, 4)).trim();
        double diem = Double.parseDouble(String.valueOf(dftbl.getValueAt(row, 5)).trim());
        int lan = Integer.valueOf(String.valueOf(dftbl.getValueAt(row, 3)).trim());
        String hoTen = selectedSv.getHo() + selectedSv.getTen();
        String maLop = selectedSv.getMaLop();

        try {
            IODATA.xemReportBaiThi(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), maBt, maLop, hoTen, monThi, ngayThi, diem, lan, link1);
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage() + " MAINFORM.bttReportBaiThiActionPerformed()");
        }
    }//GEN-LAST:event_bttReportBaiThiActionPerformed

    private void tblBangDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangDiemMouseClicked
        int row = tblBangDiem.getSelectedRow();
        if (row >= 0) {
            bttReportBaiThi.setEnabled(true);
        }
    }//GEN-LAST:event_tblBangDiemMouseClicked

    private void bttBaoCaoDsKyThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttBaoCaoDsKyThiActionPerformed
        String coso = ((COSO) cbxCoSoHienTai.getSelectedItem()).getTenCoso();
        new RPDSKYTHIFORM(gvLogin, link1, coso).setVisible(true);
    }//GEN-LAST:event_bttBaoCaoDsKyThiActionPerformed

    private void txtTimKiemCHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemCHKeyTyped

        String find = txtTimKiemCH.getText();

        char type = evt.getKeyChar();
        if ((type >= 65 && type <= 90) || (type >= 97 && type <= 122) || type == 32
                || (type >= 30 && type <= 39)) {
            find = find + type;
        }
        if (find.trim().isEmpty()) {
            System.out.println("Trống");
            loadCBMAMH();
            loadBoDe();
            return;
        } else {
            System.out.println(find.isEmpty() + ", " + find);
        }
        disableFunc(pnlBoDeFunc);
        dftblDsCauHoi.setRowCount(0);
        for (CAUHOI ch : boDe) {
            if (ch.getTimKiem().toLowerCase().contains(find.toLowerCase())) {
                dftblDsCauHoi.addRow(new Object[]{
                    "",
                    ch.getSttCauHoi(),
                    ch.getMaMh(),
                    ch.getTrinhDo(),
                    ch.getNoiDung(),
                    ch.getTxtA(),
                    ch.getTxtB(),
                    ch.getTxtC(),
                    ch.getTxtD(),
                    ch.getDapAn(),
                    ch.getMaGv()
                });
            }
        }
    }//GEN-LAST:event_txtTimKiemCHKeyTyped

    private void txtHoGv_HoSoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHoGv_HoSoMouseClicked
        if (txtHoGv_HoSo.getForeground() == Color.RED) {
            txtHoGv_HoSo.setText("");
            txtHoGv_HoSo.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtHoGv_HoSoMouseClicked

    private void txtHocVi_HoSoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHocVi_HoSoMouseClicked
        if (txtHocVi_HoSo.getForeground() == Color.RED) {
            txtHocVi_HoSo.setText("");
            txtHocVi_HoSo.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtHocVi_HoSoMouseClicked

    private void bttLuuHoSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttLuuHoSoActionPerformed

        boolean kt = true;
        String ho = txtHoGv_HoSo.getText();
        String ten = txtTenGv_HoSo.getText();
        String hocVi = txtHocVi_HoSo.getText();
        if (ho.isEmpty()) {
            txtHoGv_HoSo.setText("* Họ không được phép trống");
            txtHoGv_HoSo.setForeground(Color.RED);
            kt = false;
        }
        if (ho.length() > 40) {
            txtHoGv_HoSo.setText("* Độ dài họ không được quá 40 ký tự");
            txtHoGv_HoSo.setForeground(Color.RED);
            kt = false;
        }
        if (ten.isEmpty()) {
            txtTenGv_HoSo.setText("* Tên giáo viên không được bỏ trống");
            txtTenGv_HoSo.setForeground(Color.RED);
            kt = false;
        }
        if (ten.length() > 10) {
            txtTenGv_HoSo.setText("* Độ dài tên không được quá 10 ký tự");
            txtTenGv_HoSo.setForeground(Color.RED);
            kt = false;
        }
        if (kt) {
            try {
                IODATA.suaGv(gvLogin, ho, ten, hocVi);
                gvLogin.layTtDangNhapGv();
                refreshpnlHoSoGiaoVien();
                JOptionPane.showMessageDialog(rootPane, "Hồ sơ của bạn đã được cập nhật", "Sửa hồ sơ thành công", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Sửa hồ sơ thất bại", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bttLuuHoSoActionPerformed

    private void txtTenGv_HoSoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenGv_HoSoMouseClicked
        if (txtTenGv_HoSo.getForeground() == Color.RED) {
            txtTenGv_HoSo.setText("");
            txtTenGv_HoSo.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtTenGv_HoSoMouseClicked

    private void bttSuaHoSoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSuaHoSoMouseClicked
        txtHoGv_HoSo.setEnabled(true);
        txtTenGv_HoSo.setEnabled(true);
        txtHocVi_HoSo.setEnabled(true);
        bttLuuHoSo.setVisible(true);
        bttSuaHoSo.setEnabled(false);
    }//GEN-LAST:event_bttSuaHoSoMouseClicked

    private void tblMonHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMonHocMouseClicked
        int viTriDongVuaBam = tblMonHoc.getSelectedRow();
        txtMaMH_MonHoc.setText(tblMonHoc.getValueAt(viTriDongVuaBam, 0).toString());
        txtTenMH_MonHoc.setText(tblMonHoc.getValueAt(viTriDongVuaBam, 1).toString());
        txtMaMH_MonHoc.setEnabled(false);
    }//GEN-LAST:event_tblMonHocMouseClicked

    private void btnThem_MonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_MonHocActionPerformed
        MONHOC mh = new MONHOC();
        mh.setMaMh(txtMaMH_MonHoc.getText());
        mh.setTenMh(txtTenMH_MonHoc.getText());
        if (mh.getMaMh().equalsIgnoreCase("") || mh.getTenMh().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống mã môn học và tên môn học");
        } else {
            try {
                IODATA.themMonHoc(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), mh);
                JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
                txtMaMH_MonHoc.setText("");
                txtTenMH_MonHoc.setText("");
                dftblMonHoc.setRowCount(0);
                loadMonHoc();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnThem_MonHocActionPerformed

    private void btnSua_MonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_MonHocActionPerformed
        MONHOC mh = new MONHOC();
        mh.setMaMh(txtMaMH_MonHoc.getText());
        mh.setTenMh(txtTenMH_MonHoc.getText());
        if (mh.getMaMh().equalsIgnoreCase("") || mh.getTenMh().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống mã môn học và tên môn học");
        } else {
            try {
                IODATA.suaMonHoc(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), mh);
                JOptionPane.showMessageDialog(rootPane, "Sửa thành công!");
                txtMaMH_MonHoc.setText("");
                txtTenMH_MonHoc.setText("");
                dftblMonHoc.setRowCount(0);
                txtMaMH_MonHoc.setEnabled(true);
                loadMonHoc();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSua_MonHocActionPerformed

    private void btnXoa_MonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_MonHocActionPerformed
        MONHOC mh = new MONHOC();
        mh.setMaMh(txtMaMH_MonHoc.getText());
        mh.setTenMh(txtTenMH_MonHoc.getText());
        if (mh.getMaMh().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn môn học cần xóa");
        } else {
            String sql = "EXEC SP_XOAMONHOC ?";
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Cảnh báo",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    Connection conn = JDBCCONNECT.getConnection(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo());
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, mh.getMaMh());
                    System.out.println(mh.getMaMh());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane, "Xóa thành công!");
                    txtMaMH_MonHoc.setText("");
                    txtTenMH_MonHoc.setText("");
                    txtMaMH_MonHoc.setEnabled(true);
                    dftblMonHoc.setRowCount(0);
                    loadMonHoc();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnXoa_MonHocActionPerformed

    private void btnLamMoi_MonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi_MonHocActionPerformed
        txtMaMH_MonHoc.setText("");
        txtTenMH_MonHoc.setText("");
        txtMaMH_MonHoc.setEnabled(true);
    }//GEN-LAST:event_btnLamMoi_MonHocActionPerformed

    private void bttMonHocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttMonHocMouseEntered
        if (!pnlMonHoc.isVisible()) {
            unlMonHoc.setBackground(new Color(41, 83, 147));
            bttMonHoc.setBackground(new Color(41, 83, 147));
        }
    }//GEN-LAST:event_bttMonHocMouseEntered

    private void bttMonHocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttMonHocMouseExited
        if (!pnlMonHoc.isVisible()) {
            bttMonHoc.setBackground(mainBttColor);
            unlMonHoc.setBackground(mainBttColor);
        }
    }//GEN-LAST:event_bttMonHocMouseExited

    private void bttMonHocMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttMonHocMousePressed
        setBttColor(bttMonHoc, unlMonHoc, pnlMonHoc);
        resetMaintabColor(new JPanel[]{bttGiaoVien, bttKhoaLop, bttBoDe, bttSinhVien, bttKyThi},
                new JPanel[]{unlGiaoVien, unlKhoaLop, unlBoDe, unlSinhVien, unlKyThi},
                new JPanel[]{pnlGiaoVien, pnlKhoaLop, pnlBode, pnlSinhVien, pnlKyThi, pnlHoSoGiaoVien});

    }//GEN-LAST:event_bttMonHocMousePressed

    private void bttBaoCaoBangDiemKtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttBaoCaoBangDiemKtActionPerformed
        int row = tblDsKyThi.getSelectedRow();
        String maMh = String.valueOf(tblDsKyThi.getValueAt(row, 1));
        String tenMh = String.valueOf(tblDsKyThi.getValueAt(row, 2));
        String maLop = String.valueOf(tblDsKyThi.getValueAt(row, 3));
        int lan = Integer.parseInt(String.valueOf(tblDsKyThi.getValueAt(row, 4)));

        try {
            IODATA.xemReportBangDiemKt(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), maLop, maMh, lan, tenMh, link1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi");
        }
    }//GEN-LAST:event_bttBaoCaoBangDiemKtActionPerformed

    /**
     * @param args the command line arguments
     */
    public void setBttColor(JPanel bttPanel, JPanel unlPanel, JPanel pnlJPanel) {
        bttPanel.setBackground(new Color(37, 74, 131));
        bttPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.black));
        unlPanel.setBackground(new Color(254, 240, 229));
        pnlJPanel.setVisible(true);
    }

    public void resetMaintabColor(JPanel[] bttPanel, JPanel[] unlPanel, JPanel[] pnlPanel) {
        disableFunc(new JButton[]{bttLuuKT, bttThiThu});
        for (JPanel jPanel : bttPanel) {
            jPanel.setBackground(mainBttColor);
            jPanel.setBorder(BorderFactory.createEmptyBorder());
        }
        for (JPanel jPanel : unlPanel) {
            jPanel.setBackground(mainBttColor);
        }
        for (JPanel jPanel : pnlPanel) {
            jPanel.setVisible(false);
        }
    }

    public void setStartPanelsColor() {
        bttBoDe.setBackground(mainBttColor);
        bttKhoaLop.setBackground(mainBttColor);
        bttGiaoVien.setBackground(mainBttColor);
        bttSinhVien.setBackground(mainBttColor);
        bttKyThi.setBackground(mainBttColor);
        bttDoiCoSo.setBackground(mainBttColor);

        unlBoDe.setBackground(mainBttColor);
        unlKhoaLop.setBackground(mainBttColor);
        unlGiaoVien.setBackground(mainBttColor);
        unlSinhVien.setBackground(mainBttColor);
        unlKyThi.setBackground(mainBttColor);
    }

    public void disableFunc(JPanel pnlFunc) {
        for (Component cpn : pnlFunc.getComponents()) {
            cpn.setEnabled(false);
        }
    }

    public void disableFunc(JButton[] bttFunc) {
        for (JButton jButton : bttFunc) {
            jButton.setEnabled(false);
        }
    }

    public void enableFunc(JPanel pnlFunc) {
        for (Component cpn : pnlFunc.getComponents()) {
            cpn.setEnabled(true);
        }
    }

    public void enableFunc(JButton[] bttFunc) {
        for (JButton jButton : bttFunc) {
            jButton.setEnabled(true);
        }
    }

    public MONHOC getMonhoc(String Mh) {
        for (MONHOC monhoc : dsMonHoc) {
            if (monhoc.getMaMh().compareToIgnoreCase(Mh) == 0) {
                return monhoc;
            }
            if (monhoc.getTenMh().compareToIgnoreCase(Mh) == 0) {
                return monhoc;
            }
        }
        return null;
    }

    public void nopBai() {
        dongHo.stop(true);
        int tongSoCau = dsCauHoi.size();
        int soCauDung = 0;
        for (CAUHOICARD cauhoi : dsCauHoi) {
            if (cauhoi.isDung()) {
                soCauDung++;
            }
        }
        double diem = (double) Math.round(((double) soCauDung / tongSoCau) * 100) / 10;
        JOptionPane.showMessageDialog(rootPane, new HOANTHANHBAITHI(soCauDung, tongSoCau, diem), "HOÀN THÀNH BÀI THI", JOptionPane.PLAIN_MESSAGE);

        pnlBaiThi.removeAll();
        pnlBaiThi.setPreferredSize(new Dimension(pnlBaiThi.getPreferredSize().width, 345));
        pnlBaiThi.revalidate();
        pnlBaiThi.repaint();
        pnlLuaChon.removeAll();
        dsCauHoi.clear();
        pnlThiTracNghiem.setVisible(false);
        pnlGD_LamViec.setVisible(true);
    }
    //SETTING TABLE

    public void settingTable() {

        if (gvLogin.getRole().compareTo("TRUONG") == 0) {
            return;
        }
        tblDsKyThi.getColumn(".").setCellRenderer(new IconRenderer());
        tblDsKyThi.getColumnModel().getColumn(1).setCellEditor(new ComboBoxCellEditor(cbxMaMonThi));
        tblDsKyThi.getColumnModel().getColumn(2).setCellEditor(new ComboBoxCellEditor(cbxTenMonThi));
        tblDsKyThi.getColumnModel().getColumn(3).setCellEditor(new ComboBoxCellEditor(cbxLopThi));
        tblDsKyThi.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbxLan));
        tblDsKyThi.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cbxTrinhDo));
        tblDsKyThi.getColumnModel().getColumn(7).setCellEditor(new JDateChooserEditor(new JCheckBox(), dcNgayThi));
        tblDsKyThi.getColumnModel().getColumn(8).setCellEditor(new JSpinnerDateEditor(new JCheckBox()));
        tblDsKyThi.getColumnModel().getColumn(9).setCellEditor(new JSpinnerEditor(new JCheckBox(), ""));
        tblDsKyThi.getColumnModel().getColumn(10).setCellEditor(new JSpinnerEditor(new JCheckBox(), "p"));

        dftblDsKyThi.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                try {
                    if (e.getColumn() == 1) {
                        String maMh = String.valueOf(dftblDsKyThi.getValueAt(e.getFirstRow(), 1));
                        String tenMh = String.valueOf(dftblDsKyThi.getValueAt(e.getFirstRow(), 2));
                        MONHOC monhoc = getMonhoc(maMh);
                        if (monhoc.getTenMh().compareToIgnoreCase(tenMh) != 0) {
                            dftblDsKyThi.setValueAt(monhoc.getTenMh(), e.getFirstRow(), 2);
                        }
                    } else if (e.getColumn() == 2) {
                        String maMh = String.valueOf(dftblDsKyThi.getValueAt(e.getFirstRow(), 1));
                        String tenMh = String.valueOf(dftblDsKyThi.getValueAt(e.getFirstRow(), 2));
                        MONHOC monhoc = getMonhoc(tenMh);
                        if (monhoc.getMaMh().compareToIgnoreCase(maMh) != 0) {
                            dftblDsKyThi.setValueAt(monhoc.getMaMh(), e.getFirstRow(), 1);
                        }
                    }
                } catch (Exception exc) {
                }
            }
        });

        tblDsCauHoi.getColumn(".").setCellRenderer(new IconRenderer());
        tblDsCauHoi.getColumnModel().getColumn(2).setCellEditor(new ComboBoxCellEditor(cbxMaMonThi));
        tblDsCauHoi.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbxTrinhDo));
        tblDsCauHoi.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(txtCauHoi));
        tblDsCauHoi.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(txtCauHoi));
        tblDsCauHoi.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(txtCauHoi));
        tblDsCauHoi.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(txtCauHoi));
        tblDsCauHoi.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(txtCauHoi));
        tblDsCauHoi.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(cbxDapAn_BODE));
        dftblDsCauHoi.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (tblDsCauHoi.isEditing()) {
                    putDataTbl(dftblDsCauHoi);
                    System.out.println(".tableChanged");
                    enableFunc(new JButton[]{bttPhucHoiBD, bttLuuBD, bttHuyThayDoiBD});
                }
            }
        });
        //"Câu Hỏi", "Mã MH", "Trình độ", "Nội dung", "A", "B", "C", "D", "ĐÁP ÁN", "MÃ GV"

        tblDsCauHoi.putClientProperty("terminateEditOnFocusLost", true);

        tblDsCauHoi.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblDsCauHoi.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblDsCauHoi.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblDsCauHoi.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblDsCauHoi.getColumnModel().getColumn(4).setPreferredWidth(250);
        tblDsCauHoi.getColumnModel().getColumn(5).setPreferredWidth(240);
        tblDsCauHoi.getColumnModel().getColumn(6).setPreferredWidth(240);
        tblDsCauHoi.getColumnModel().getColumn(7).setPreferredWidth(240);
        tblDsCauHoi.getColumnModel().getColumn(8).setPreferredWidth(240);
        tblDsCauHoi.getColumnModel().getColumn(9).setPreferredWidth(60);
        tblDsCauHoi.getColumnModel().getColumn(10).setPreferredWidth(70);
        tblDsCauHoi.getTableHeader().setResizingAllowed(true);

        tblDSLop.getColumn(".").setCellRenderer(new IconRenderer());
        tblDSLop.getColumnModel().getColumn(3).setCellEditor(new ComboBoxCellEditor(cbxMaKhoa));
    }

    //REFRESH
    public void ManagePnlKhoaLop() {
        ShowKhoa();
        ShowdsLop(GetMaKhoa);
    }

    public void refreshpnlHoSoGiaoVien() {
        bttSuaHoSo.setEnabled(true);
        txtHoGv_HoSo.setText(gvLogin.getHo());
        txtTenGv_HoSo.setText(gvLogin.getTen());
        txtHocVi_HoSo.setText(gvLogin.getHocVi());
        lblMaGv_HoSo.setText(gvLogin.getMaGV());

        txtHoGv_HoSo.setEnabled(false);
        txtTenGv_HoSo.setEnabled(false);;
        txtHocVi_HoSo.setEnabled(false);
        bttLuuHoSo.setVisible(false);

        for (KHOA khoa : dsKhoa) {
            if (khoa.getMaKhoa().trim().compareToIgnoreCase(gvLogin.getMaKhoa().trim()) == 0) {
                lblKhoa_HoSo.setText(khoa.getMaKhoa() + " - " + khoa.getTenKhoa());
            }
        }
        lblCoSo_HoSo.setText(coSo.getMaCoso() + " - " + coSo.getTenCoso());

        lblTenLogin_HoSo.setText(gvLogin.getTenLogin());
        lblRole_HoSo.setText(gvLogin.getRole());
    }

    public void refreshPnlKyThi() {
        editDataRows.clear();
        mode = 0;
        disableFunc(new JButton[]{bttBaoCaoBangDiemKt});
        if (gvLogin.getRole().compareTo("COSO") == 0) {
            disableFunc(new JButton[]{bttLuuKT, bttHuyThayDoiKT, bttThiThu});
            enableFunc(new JButton[]{bttThemKT, bttSuaKT});
        } else {
            disableFunc(pnlHieuChinhKyThi);
        }

        if (gvLogin.getRole().compareTo("GIANGVIEN") == 0) {
            disableFunc(new JButton[]{bttBaoCaoDsKyThi});
        }
        refreshEditorModels();

        dftblDsKyThi.setRowCount(0);
        dsKyThi = IODATA.getDsKythi(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), link1);
        for (KYTHI kythi : dsKyThi) {
            String tt;
            long kt = kythi.getNgayThi().getTime() + (kythi.getTg() * 60 * 1000);
            Date ketThuc = new Date();
            ketThuc.setTime(kt);
            if (kythi.getNgayThi().after(new Date())) {
                tt = "Lên lịch";
            } else if (ketThuc.before(new Date())) {
                tt = "Đã kết thúc";
            } else {
                tt = "Đang diễn ra";
            }

            dftblDsKyThi.addRow(new Object[]{
                "",
                kythi.getMaMH(),
                kythi.getTenMH(),
                kythi.getMaLop(),
                kythi.getLan(),
                kythi.getGvdk(),
                kythi.getTrinhDo(),
                ngayFormat.format(kythi.getNgayThi()),
                gioFormat.format(kythi.getNgayThi()),
                kythi.getSoCau(),
                kythi.getTg() + "p",
                tt
            });
        }

    }

    public void refreshEditorModels() {
        cbxTenMonThi.removeAllItems();
        cbxMaMonThi.removeAllItems();
        for (MONHOC monhoc : dsMonHoc) {
            cbxMaMonThi.addItem(monhoc);
            cbxTenMonThi.addItem(monhoc.getTenMh());
        }

        cbxLopThi.removeAllItems();
        for (LOP lop : dsLop) {
            cbxLopThi.addItem(lop);
        }

        cbxMaKhoa.removeAllItems();
        for (KHOA khoa : dsKhoa) {
            cbxMaKhoa.addItem(khoa);
        }
    }

    public void refreshPnlGiaoVien() {
        dftblDsGiaoVien.setRowCount(0);
        dsGiaovien = IODATA.getDsGiaoVien(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), link1);
        lblQuyenGV.setText("");
        lblTenLoginGV.setText("");

        for (Component component : pnlThemGV.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
            if (component instanceof JPasswordField) {
                ((JPasswordField) component).setText("");
            }
        }

        if (gvLogin.getRole().compareToIgnoreCase(QUYEN.GIANGVIEN.getValue()) == 0) {
            disableFunc(pnlHieuChinhGV);
        } else if (gvLogin.getRole().compareToIgnoreCase(QUYEN.TRUONG.getValue()) == 0) {
            disableFunc(new JButton[]{bttXoaGV});
            bttThemGV.setEnabled(true);
        }

        if (link1.compareTo("LINK1.") == 0) {
            disableFunc(pnlHieuChinhGV);
        }
        disableFunc(pnlThemGV);
        bttTaoLogin.setEnabled(false);
        bttXoaGV.setEnabled(false);

        for (GIAOVIEN gv : dsGiaovien) {
            String[] kt = IODATA.layTtLogin(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), gv.getMaGV());
            String loginExist = "Có";
            if (kt[0] == null) {
                loginExist = "Không";
            }
            dftblDsGiaoVien.addRow(new Object[]{
                gv.getMaGV(),
                gv.getHo(),
                gv.getTen(),
                gv.getHocVi(),
                gv.getMaKhoa(),
                gv.getCoSo(),
                loginExist
            });
        }

        dsKhoa = IODATA.getDSKhoa(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), "");
        cbxKhoa_GiaoVien.removeAllItems();
        for (KHOA khoa : dsKhoa) {
            cbxKhoa_GiaoVien.addItem(khoa);
        }
    }

    public void resetBoDe() {
        txtCauHoi_BODE.setText("");
        cbxTrinhDo_BODE.setSelectedIndex(0);
        cbxDapAn_BODE.setSelectedIndex(0);
        txtA_BODE.setText("");
        txtB_BODE.setText("");
        txtC_BODE.setText("");
        txtD_BODE.setText("");
        txtCauHoi.setText("");
        cbxMaMH_BODE.setSelectedIndex(0);
    }

    public void resetSinhVien() {
        txtMaSV_SINHVIEN.setText("");
        txtHo_SINHNVIEN.setText("");
        txtTen_SINHVIEN.setText("");
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        txtNgaySinh_SINHVIEN.setDate(date);
        txtDiaChi_SINHVIEN.setText("");
        txtMauKhau_SINHVIEN.setText("");
        txtMaSV_SINHVIEN.setEnabled(true);

    }

    public void loadCBMaLop_SINHVIEN() {
        dsLop = IODATA.getDsLop(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), link1);

        cbxLop_SINHVIEN.removeAllItems();
        cbxLop_SINHVIEN.addItem("Chọn lớp... ");
        for (LOP lop : dsLop) {
            cbxLop_SINHVIEN.addItem(lop.getMaLop());
        }

        bttReportBaiThi.setEnabled(false);

        dftblBangDiem.setRowCount(0);
        resetSinhVien();
    }

    public void loadBangDiem(String maSV) throws ParseException {

        SINHVIEN sv = new SINHVIEN();
        sv.setMaSV(maSV);
        sv.setCoSo(((COSO) cbxCoSoHienTai.getSelectedItem()).getTenServer());
        bangDiem = IODATA.getBangDiemSv(sv);
        if (bangDiem == null) {
            return;
        }
        bttReportBaiThi.setEnabled(false);

        dftblBangDiem.setRowCount(0);
        for (DIEMTHI diemthi : bangDiem) {
            dftblBangDiem.addRow(new Object[]{
                diemthi.getMaSv(),
                diemthi.getMaMh(),
                diemthi.getTenMh(),
                diemthi.getLan(),
                diemthi.getNgayThi(),
                diemthi.getDiem(),
                diemthi.getMaBt()
            });
        }
    }

    public void loadBoDe() {
        if (gvLogin.getRole().compareTo("GIANGVIEN") == 0
                || gvLogin.getRole().compareTo("COSO") == 0) {
            enableFunc(pnlBoDeFunc);
        }
        dftblDsCauHoi.setRowCount(0);
        disableFunc(new JButton[]{bttPhucHoiBD, bttHuyThayDoiBD, bttLuuBD});
        dataTblStack.clear();
        editDataRows.clear();
        String sql = "";
        boDe = new ArrayList<>();
        /*if (gvLogin.getRole().compareTo("TRUONG")==0) {
            sql = "EXEC "+link1+"TN_CSDLPT.DBO.SP_LAYBODE_COSO";
        } else {
            sql = "EXEC SP_LAYBODE_GV " + "'" + this.gvLogin.getMaGV() + "'    ";
        }*/
        sql = "EXEC " + link1 + "TN_CSDLPT.DBO.SP_LAYBODE_COSO";
        try {
            Connection conn = JDBCCONNECT.getConnection(this.gvLogin.getTenLogin(), this.gvLogin.getMatKhau(), this.gvLogin.getCoSo());
            PreparedStatement stament = conn.prepareStatement(sql);
            ResultSet rs = stament.executeQuery();
            while (rs.next()) {
                CAUHOI cauHoi = new CAUHOI(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                boDe.add(cauHoi);

                dftblDsCauHoi.addRow(new Object[]{
                    "",
                    cauHoi.getSttCauHoi(),
                    cauHoi.getMaMh(),
                    cauHoi.getTrinhDo(),
                    cauHoi.getNoiDung(),
                    cauHoi.getTxtA(),
                    cauHoi.getTxtB(),
                    cauHoi.getTxtC(),
                    cauHoi.getTxtD(),
                    cauHoi.getDapAn(),
                    cauHoi.getMaGv()
                });
            }

        } catch (SQLException ex) {
            Logger.getLogger(IODATA.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi");
        }
        System.out.println(dftblDsCauHoi.getRowCount());
    }

    public void loadSinhVien(String maLop) throws ParseException {
        dftblSinhVienLop.setRowCount(0);
        ArrayList<SINHVIEN> dsSvLop = IODATA.getDsSvTheoLop(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), maLop, link1);
        for (SINHVIEN svl : dsSvLop) {
            dftblSinhVienLop.addRow(new Object[]{
                svl.getMaSV(),
                svl.getHo(),
                svl.getTen(),
                svl.getNgaySinh(),
                svl.getDiaChi(),
                svl.getMaLop(),
                svl.getMatKhauSV()
            });
        }
    }

    public void loadCBMAMH() {
        cbxMaMH_BODE.removeAllItems();
        for (MONHOC monhoc : dsMonHoc) {
            cbxMaMH_BODE.addItem(monhoc.getMaMh());
        }
    }

    public void loadMonHoc() {
        dsMonHoc = IODATA.getDsMonHoc(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo());

        for (MONHOC mh1 : dsMonHoc) {
            dftblMonHoc.addRow(new Object[]{
                mh1.getMaMh(),
                mh1.getTenMh()

            });
        }
    }

    public void themLop(String MaLop, String TenLop, String MaKhoa) throws SQLException {

        Connection conn = JDBCCONNECT.getConnection(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo());
        String sql = "exec SP_THEMLOP ?,?,?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, MaLop);
        statement.setString(2, TenLop);
        statement.setString(3, MaKhoa);
        statement.executeUpdate();
    }

    public void xoaLop(String maLop) throws SQLException {
        Connection conn = JDBCCONNECT.getConnection(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo());
        String sql = "exec SP_XOALOP ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, maLop);
        statement.executeUpdate();
        DefaultTableModel model = (DefaultTableModel) tblDSLop.getModel();
        model.setRowCount(0);
    }

    public void themKhoa(String MaKhoa, String TenKhoa, String MaCS) throws SQLException {

        Connection conn = JDBCCONNECT.getConnection(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo());
        String sql = "exec SP_THEMKHOA ?,?,?";
        try {

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, MaKhoa);
            statement.setString(2, TenKhoa);
            statement.setString(3, MaCS);
            statement.executeUpdate();

            txtMaKh.setText("");
            txtTenKh.setText("");
            ShowKhoa();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "them khoa that bai " + ex);

        }
    }

    public void xoaKhoa(String maKhoa) throws SQLException {
        Connection conn = JDBCCONNECT.getConnection(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo());
        String sql = "exec SP_XOAKHOA ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, maKhoa);
            statement.executeUpdate();
//            DefaultTableModel model =(DefaultTableModel)tbDSKhoa.getModel();
//            model.setRowCount(0);
            ShowKhoa();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "xóa khoa thất bại" + ex);
        }
    }

    public void capNhapKhoa(String MaKhoa, String TenKhoa, String MaCS) throws SQLException {
        Connection conn = JDBCCONNECT.getConnection(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo());
        String sql = "exec SP_SUAKHOA ?, ?, ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, MaKhoa);
            ps.setString(2, TenKhoa);
            ps.setString(3, MaCS);
            ps.executeUpdate();

            ShowKhoa();
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin khoa thành công!", "Cập nhật thành công", HEIGHT);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "cap nhap khoa that bai " + ex);
            ShowKhoa();
        }
    }

    public void ShowdsLop(String maKhoa) {
        if (tblDSLop.isEditing()) {
            tblDSLop.getCellEditor().stopCellEditing();
        }
        dsLopTheoKhoa = IODATA.getDSLopTheoKhoa(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), maKhoa, link1);
        dftblDsLop.setRowCount(0);
        for (LOP lopTheoKhoa : dsLopTheoKhoa) {
            dftblDsLop.addRow(new Object[]{
                "",
                lopTheoKhoa.getMaLop(),
                lopTheoKhoa.getTenLop(),
                lopTheoKhoa.getMaKhoa()
            });
            tblDSLop.setModel(dftblDsLop);
        }
        if (dftblDsLop.getRowCount() == 0) {
            dftblDsLop.addRow(new Object[]{"", "", ""});
        }
        editDataRows.clear();
    }

    public void ShowKhoa() {
        dsKhoa = IODATA.getDSKhoa(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo(), link1);
        DefaultTableModel dtm = (DefaultTableModel) tbDSKhoa.getModel();
        dtm.setNumRows(0);
        for (KHOA khoa : dsKhoa) {
            dtm.addRow(new Object[]{
                khoa.getMaKhoa(),
                khoa.getTenKhoa(),
                khoa.getMaCS()
            });
            tbDSKhoa.setModel(dtm);
        }
        boolean a = false;
        a = tbDSKhoa.isCellEditable(1, 1);
    }

    public void capNhapLop(String maLop, String tenLop, String maKhoa) throws SQLException {
        Connection conn = JDBCCONNECT.getConnection(gvLogin.getTenLogin(), gvLogin.getMatKhau(), gvLogin.getCoSo());
        String sql = "exec SP_SUALOP ?, ?, ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maLop);
            ps.setString(2, tenLop);
            ps.setString(3, maKhoa);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "cap nhap thanh cong" + maLop + "  " + maKhoa + " " + tenLop);
            ShowdsLop(maKhoa);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "cap nhap lop that bai " + ex);
            ShowdsLop(maKhoa);
        }
    }

    //BACKUP DATA TABLE
    public void putDataTbl(DefaultTableModel table) {
        Vector<Object> dataTable = new Vector<>();
        for (int i = 0; i < dftblDsCauHoi.getRowCount(); i++) {
            Vector column = (Vector) ((Vector) dftblDsCauHoi.getDataVector().elementAt(i)).clone();
            dataTable.add(column);
        }
        dataTblStack.push(dataTable);
    }

    public boolean canLuuThayDoi() {
        if (dataTblStack.isEmpty() && editDataRows.isEmpty()) {
            return false;
        } else {
            int choose = JOptionPane.showConfirmDialog(rootPane, "Nếu qua tab mới bây giờ, phần công việc \n"
                    + "đang làm dở của bạn sẽ mất. Bạn vẫn muốn tiếp tục ?", "Bạn vẫn chưa lưu thay đổi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choose == JOptionPane.YES_OPTION) {
                dataTblStack.clear();
                editDataRows.clear();
                return false;
            }
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnDelete;
    private javax.swing.JButton BtnUpdate;
    private javax.swing.JPanel MainBtt;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnLamMoi_MonHoc;
    private javax.swing.JButton btnSua_MonHoc;
    private javax.swing.JButton btnThem_MonHoc;
    private javax.swing.JButton btnXoa_MonHoc;
    private javax.swing.JButton btn_LamMoiSinhVien;
    private javax.swing.JButton btn_SuaSinhVien;
    private javax.swing.JButton btn_ThemSinhVien;
    private javax.swing.JButton btn_XoaSinhVien;
    private javax.swing.JButton bttBaoCaoBangDiemKt;
    private javax.swing.JButton bttBaoCaoDsKyThi;
    private javax.swing.JPanel bttBoDe;
    private javax.swing.JPanel bttDoiCoSo;
    private javax.swing.JPanel bttGiaoVien;
    private javax.swing.JButton bttHuyThayDoiBD;
    private javax.swing.JButton bttHuyThayDoiKT;
    private javax.swing.JPanel bttKhoaLop;
    private javax.swing.JPanel bttKyThi;
    private javax.swing.JButton bttLuuBD;
    private javax.swing.JButton bttLuuHoSo;
    private javax.swing.JButton bttLuuKT;
    private javax.swing.JPanel bttMonHoc;
    private javax.swing.JButton bttNopBai;
    private javax.swing.JButton bttPhucHoiBD;
    private javax.swing.JButton bttReportBaiThi;
    private javax.swing.JPanel bttSinhVien;
    private javax.swing.JButton bttSuaHoSo;
    private javax.swing.JButton bttSuaKT;
    private javax.swing.JButton bttTaoLogin;
    private javax.swing.JButton bttThemBD;
    private javax.swing.JButton bttThemGV;
    private javax.swing.JButton bttThemKT;
    private javax.swing.JButton bttThiThu;
    private javax.swing.JPanel bttUser;
    private javax.swing.JButton bttXacNhanThem;
    private javax.swing.JButton bttXoaBD;
    private javax.swing.JButton bttXoaGV;
    private javax.swing.JComboBox<COSO> cbxCoSoHienTai;
    private javax.swing.JComboBox<String> cbxDapAn_BODE;
    private javax.swing.JComboBox<String> cbxDoKho;
    private javax.swing.JComboBox<KHOA> cbxKhoa_GiaoVien;
    private javax.swing.JComboBox<String> cbxLan;
    private javax.swing.JComboBox<LOP> cbxLopThi;
    private javax.swing.JComboBox<String> cbxLop_SINHVIEN;
    private javax.swing.JComboBox<KHOA> cbxMaKhoa;
    private javax.swing.JComboBox<String> cbxMaMH_BODE;
    private javax.swing.JComboBox<MONHOC> cbxMaMonThi;
    private javax.swing.JComboBox<String> cbxTenMonThi;
    private javax.swing.JComboBox<String> cbxTrinhDo;
    private javax.swing.JComboBox<String> cbxTrinhDo_BODE;
    private com.toedter.calendar.JDateChooser dcNgayThi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblC;
    private javax.swing.JLabel lblCauSo;
    private javax.swing.JLabel lblCoSo_HoSo;
    private javax.swing.JLabel lblD;
    private javax.swing.JLabel lblHoTenThiSinh;
    private javax.swing.JLabel lblKhoa_HoSo;
    private javax.swing.JLabel lblLopThiSinh;
    private javax.swing.JLabel lblMaGv_HoSo;
    private javax.swing.JLabel lblMaKhoa;
    private javax.swing.JLabel lblMonThi;
    private javax.swing.JLabel lblNoiDung;
    private javax.swing.JLabel lblQuyenGV;
    private javax.swing.JLabel lblRole_HoSo;
    private javax.swing.JLabel lblTenLoginGV;
    private javax.swing.JLabel lblTenLogin_HoSo;
    private javax.swing.JLabel lblTgConLai;
    private javax.swing.JLabel lblThinhDoBaiThi;
    private javax.swing.JPanel pnlBaiThi;
    private javax.swing.JPanel pnlBoDeFunc;
    private javax.swing.JPanel pnlBode;
    private javax.swing.JPanel pnlChiTietCauHoi;
    private javax.swing.JPanel pnlDoiCoSo;
    private javax.swing.JPanel pnlEditorModel;
    private javax.swing.JPanel pnlGD_LamViec;
    private javax.swing.JPanel pnlGiaoVien;
    private javax.swing.JPanel pnlGiaoVienFunc;
    private javax.swing.JPanel pnlHieuChinhGV;
    private javax.swing.JPanel pnlHieuChinhKhoa;
    private javax.swing.JPanel pnlHieuChinhKyThi;
    private javax.swing.JPanel pnlHieuChinhMonHoc;
    private javax.swing.JPanel pnlHieuChinhSinhVien;
    private javax.swing.JPanel pnlHoSoGiaoVien;
    private javax.swing.JPanel pnlKhoaLop;
    private javax.swing.JPanel pnlKyThi;
    private javax.swing.JPanel pnlKyThiFunc;
    private javax.swing.JPanel pnlLuaChon;
    private javax.swing.JPanel pnlMonHoc;
    private javax.swing.JPanel pnlSinhVien;
    private javax.swing.JPanel pnlThemGV;
    private javax.swing.JPanel pnlThemSuaKyThi;
    private javax.swing.JPanel pnlThiTracNghiem;
    private javax.swing.JPanel pnlThoiGian;
    private javax.swing.JPanel pnlThongTinThiSinh;
    private javax.swing.JSpinner spnGioThi;
    private javax.swing.JSpinner spnLanThi;
    private javax.swing.JSpinner spnSoCauThi;
    private javax.swing.JSpinner spnThoiGianThi;
    private javax.swing.JTable tbDSKhoa;
    private javax.swing.JTable tblBangDiem;
    private javax.swing.JTable tblDSLop;
    private javax.swing.JTable tblDsCauHoi;
    private javax.swing.JTable tblDsGiaoVien;
    private javax.swing.JTable tblDsKyThi;
    private javax.swing.JTable tblMonHoc;
    private javax.swing.JTable tblSinhVienLop;
    private javax.swing.JTextField txtA_BODE;
    private javax.swing.JTextField txtB_BODE;
    private javax.swing.JTextField txtC_BODE;
    private javax.swing.JTextField txtCauHoi;
    private javax.swing.JTextField txtCauHoi_BODE;
    private javax.swing.JTextField txtD_BODE;
    private javax.swing.JTextField txtDiaChi_SINHVIEN;
    private javax.swing.JTextField txtHoGv_GiaoVien;
    private javax.swing.JTextField txtHoGv_HoSo;
    private javax.swing.JTextField txtHo_SINHNVIEN;
    private javax.swing.JTextField txtHocVi_GiaoVien;
    private javax.swing.JTextField txtHocVi_HoSo;
    private javax.swing.JTextField txtMaGV_BODE;
    private javax.swing.JTextField txtMaGv_GiaoVien;
    private javax.swing.JTextField txtMaKh;
    private javax.swing.JTextField txtMaMH_MonHoc;
    private javax.swing.JTextField txtMaSV_SINHVIEN;
    private javax.swing.JPasswordField txtMauKhau_SINHVIEN;
    private com.toedter.calendar.JDateChooser txtNgaySinh_SINHVIEN;
    private javax.swing.JTextField txtTenGv_GiaoVien;
    private javax.swing.JTextField txtTenGv_HoSo;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtTenMH_MonHoc;
    private javax.swing.JTextField txtTen_SINHVIEN;
    private javax.swing.JTextField txtTimKiemCH;
    private javax.swing.JLabel txtUserName;
    private javax.swing.JPanel unlBoDe;
    private javax.swing.JPanel unlGiaoVien;
    private javax.swing.JPanel unlKhoaLop;
    private javax.swing.JPanel unlKyThi;
    private javax.swing.JPanel unlMonHoc;
    private javax.swing.JPanel unlSinhVien;
    // End of variables declaration//GEN-END:variables
}
