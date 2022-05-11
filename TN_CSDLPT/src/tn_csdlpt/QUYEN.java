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
public enum QUYEN {
    
    GIANGVIEN("Giảng viên","GIANGVIEN"),TRUONG("Trường","TRUONG"),COSO("Cơ sở","COSO");    
    private String label;
    private String value;

    private QUYEN(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label; //To change body of generated methods, choose Tools | Templates.
    }
}
