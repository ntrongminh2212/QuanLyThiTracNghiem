/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author zLittleMasterz
 */
public class THOIGIAN extends Thread{
    int soPhut;
    Date gioBatDau;
    Date gioKetThuc;
    String ketXuat;
    JLabel dongHo;
    boolean stop = false;
    
    public THOIGIAN(Date gioBatDau,int soPhut, JLabel dongHo) {
        this.soPhut = soPhut;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = new Date();
        this.gioKetThuc.setTime(gioBatDau.getTime()+(soPhut*60*1000));
        this.dongHo = dongHo;
    }

    public void stop(boolean stop) {
        this.stop = stop;
    }
    
    @Override
    public void run() {
        int soGiay = 0;
        long tgconlai;
        while (new Date().before(gioKetThuc)&&stop==false) {    
            
            tgconlai = gioKetThuc.getTime()-new Date().getTime();
            soPhut = (int) tgconlai/60000;
            soGiay = (int) tgconlai%60000;
            
            soGiay = soGiay/1000;
            if (soPhut<10) ketXuat = "0"+soPhut;
            else ketXuat = String.valueOf(soPhut);
            if (soGiay<10) ketXuat += ":0"+soGiay;
            else ketXuat +=":"+soGiay;
            
            dongHo.setText(ketXuat);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("ERROR: Lỗi đồng hồ đếm ngược! THOIGIAN.run()");
            }
        }
    }
}
