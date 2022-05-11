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
public class COSO {
    String tenCoso;
    String tenServer;
    String maCoso;

    public COSO() {
    }

    public COSO(String maCoso, String tenCoso, String tenServer) {
        this.tenCoso = tenCoso;
        this.tenServer = tenServer;
        this.maCoso = maCoso;
    }

    public String getTenCoso() {
        return tenCoso;
    }

    public void setTenCoso(String tenCoso) {
        this.tenCoso = tenCoso;
    }

    public String getTenServer() {
        return tenServer;
    }

    public void setTenServer(String tenServer) {
        this.tenServer = tenServer;
    }

    public String getMaCoso() {
        return maCoso;
    }

    public void setMaCoso(String maCoso) {
        this.maCoso = maCoso;
    }

    @Override
    public String toString() {
        return tenCoso; //To change body of generated methods, choose Tools | Templates.
    }
}
