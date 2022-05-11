/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

/**
 *
 * @author zLittleMasterz
 */
public class CAUHOI {
    private int sttCauHoi;
    private String maMh;
    private String trinhDo;
    private String noiDung;
    private String txtA;
    private String txtB;
    private String txtC;
    private String txtD;
    private String dapAn;
    private String maGv;
    private String timKiem;
    
    public CAUHOI() {
    }

    public CAUHOI(int sttCauHoi, String maMh, String trinhDo, String noiDung, String txtA, String txtB, String txtC, String txtD, String dapAn, String maGv) {
        this.sttCauHoi = sttCauHoi;
        this.maMh = maMh;
        this.trinhDo = trinhDo;
        this.noiDung = noiDung;
        this.txtA = txtA;
        this.txtB = txtB;
        this.txtC = txtC;
        this.txtD = txtD;
        this.dapAn = dapAn;
        this.maGv = maGv;
        this.timKiem = String.valueOf(sttCauHoi)+maMh+trinhDo+noiDung+txtA+txtB+txtC+txtD+maGv;
    } 
    
    public int getSttCauHoi() {
        return sttCauHoi;
    }

    public void setSttCauHoi(int sttCauHoi) {
        this.sttCauHoi = sttCauHoi;
    }

    public String getMaMh() {
        return maMh;
    }

    public void setMaMh(String maMh) {
        this.maMh = maMh;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getTxtA() {
        return txtA;
    }

    public void setTxtA(String txtA) {
        this.txtA = txtA;
    }

    public String getTxtB() {
        return txtB;
    }

    public void setTxtB(String txtB) {
        this.txtB = txtB;
    }

    public String getTxtC() {
        return txtC;
    }

    public void setTxtC(String txtC) {
        this.txtC = txtC;
    }

    public String getTxtD() {
        return txtD;
    }

    public void setTxtD(String txtD) {
        this.txtD = txtD;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public String getMaGv() {
        return maGv;
    }

    public void setMaGv(String maGv) {
        this.maGv = maGv;
    }

    public String getTimKiem() {
        return timKiem;
    }
}
