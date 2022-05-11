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
public class MONHOC {
    private String maMh;
    private String tenMh;

    public MONHOC(){
        
    }
    public MONHOC(String maMh, String tenMh) {
        this.maMh = maMh;
        this.tenMh = tenMh;
    }

    public String getMaMh() {
        return maMh;
    }

    public void setMaMh(String maMh) {
        this.maMh = maMh;
    }

    public String getTenMh() {
        return tenMh;
    }

    public void setTenMh(String tenMh) {
        this.tenMh = tenMh;
    }

    @Override
    public String toString() {
        return this.maMh; //To change body of generated methods, choose Tools | Templates.
    }
}
