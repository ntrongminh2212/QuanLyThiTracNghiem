/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicCheckBoxUI;
import javax.swing.plaf.basic.BasicRadioButtonUI;

/**
 *
 * @author zLittleMasterz
 */
public class CAUHOICARD extends javax.swing.JPanel {

    private int sttCauHoi;
    private String trinhDo;
    private String noiDung;
    private String txtA;
    private String txtB;
    private String txtC;
    private String txtD;
    private String dapAn;
    private boolean dung;
    private String daChon;
    private PNLISCHON pnlIsChon;
    public CAUHOICARD() {
        initComponents();
    }

    public CAUHOICARD(int sttCauHoi, String trinhDo, String noiDung, String A, String B, String C, String D, String dapAn) {
        initComponents();
        this.sttCauHoi = sttCauHoi;
        this.trinhDo = trinhDo;
        this.noiDung = noiDung;
        this.txtA = A;
        this.txtB = B;
        this.txtC = C;
        this.txtD = D;
        int doDai = 120;
        String temp="";
        try {
            for (int i = doDai; i < noiDung.length(); i++) {
                if (noiDung.charAt(i) == ' ') {
                    temp = noiDung.substring(i);
                    noiDung = noiDung.substring(0,i);
                    break;
                }
            }
            this.lblNoiDung.setText("<html><body>" + noiDung + "<br>" + temp + "</body></html>");
            temp ="";
        } catch (Exception e) {
            this.lblNoiDung.setText(noiDung);
        }
        try {
            for (int i = doDai; i < A.length(); i++) {
                if (A.charAt(i) == ' ') {
                    temp = A.substring(i);
                    A = A.substring(0,i);
                    break;
                }
            }
            this.lblA.setText("<html><body>"+A+"<br>"+temp+"</body></html>");
            temp ="";
        } catch (Exception e) {
            this.lblA.setText(A);
        }
        try {
            for (int i = doDai; i < B.length(); i++) {
                if (B.charAt(i) == ' ') {
                    temp = B.substring(i);
                    B = B.substring(0,i);
                    break;
                }
            }
            this.lblB.setText("<html><body>"+B+"<br>"+temp+"</body></html>");
            temp ="";
        } catch (Exception e) {
            this.lblB.setText(B);
        }
        try {
           for (int i = doDai; i < C.length(); i++) {
                if (C.charAt(i) == ' ') {
                    temp = C.substring(i);
                    C = C.substring(0,i);
                    break;
                }
            }
            this.lblC.setText("<html><body>"+C+"<br>"+temp+"</body></html>");
            temp ="";
        } catch (Exception e) {
            this.lblC.setText(C);
        }
        try {
            for (int i = doDai; i < D.length(); i++) {
                if (D.charAt(i) == ' ') {
                    temp = D.substring(i);
                    D = D.substring(0,i);
                    break;
                }
            }
            this.lblD.setText("<html><body>"+D+"<br>"+temp+"</body></html>");
            temp ="";
        } catch (Exception e) {
            this.lblD.setText(D);
        }
        this.dapAn = dapAn.trim();
        this.dung = false;
        this.daChon ="x";
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bttGroupLuaChon = new javax.swing.ButtonGroup();
        lblCauSo = new javax.swing.JLabel();
        rbttA = new javax.swing.JRadioButton();
        rbttB = new javax.swing.JRadioButton();
        rbttC = new javax.swing.JRadioButton();
        rbttD = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblB = new javax.swing.JLabel();
        lblD = new javax.swing.JLabel();
        lblC = new javax.swing.JLabel();
        lblNoiDung = new javax.swing.JLabel();
        lblA = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 183));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        setForeground(new java.awt.Color(0, 0, 102));
        setPreferredSize(new java.awt.Dimension(1220, 345));

        lblCauSo.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        lblCauSo.setForeground(new java.awt.Color(0, 0, 153));
        lblCauSo.setText("Câu 1:");

        rbttA.setBackground(new java.awt.Color(255, 255, 183));
        bttGroupLuaChon.add(rbttA);
        rbttA.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        rbttA.setText("A)");
        rbttA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbttAMouseClicked(evt);
            }
        });

        rbttB.setBackground(new java.awt.Color(255, 255, 183));
        bttGroupLuaChon.add(rbttB);
        rbttB.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        rbttB.setText("B)");
        rbttB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbttBMouseClicked(evt);
            }
        });

        rbttC.setBackground(new java.awt.Color(255, 255, 183));
        bttGroupLuaChon.add(rbttC);
        rbttC.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        rbttC.setText("C)");
        rbttC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbttCMouseClicked(evt);
            }
        });

        rbttD.setBackground(new java.awt.Color(255, 255, 183));
        bttGroupLuaChon.add(rbttD);
        rbttD.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        rbttD.setText("D)");
        rbttD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbttDMouseClicked(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 153));
        jSeparator1.setPreferredSize(new java.awt.Dimension(75, 10));

        lblB.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        lblB.setText("jLabel4");
        lblB.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBMouseClicked(evt);
            }
        });

        lblD.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        lblD.setText("jLabel5");
        lblD.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDMouseClicked(evt);
            }
        });

        lblC.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        lblC.setText("jLabel5");
        lblC.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCMouseClicked(evt);
            }
        });

        lblNoiDung.setFont(new java.awt.Font("Tahoma", 0, 23)); // NOI18N
        lblNoiDung.setText("DDDDDDDDDDDd");
        lblNoiDung.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblA.setFont(new java.awt.Font("Tahoma", 0, 21)); // NOI18N
        lblA.setText("ddddddddddddddddddddddddddddddddddddddddddddd");
        lblA.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblCauSo))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(lblNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(rbttA)
                .addGap(8, 8, 8)
                .addComponent(lblA, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(rbttB)
                .addGap(8, 8, 8)
                .addComponent(lblB, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(rbttC)
                .addGap(8, 8, 8)
                .addComponent(lblC, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(rbttD)
                .addGap(8, 8, 8)
                .addComponent(lblD, javax.swing.GroupLayout.PREFERRED_SIZE, 1107, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCauSo)
                        .addGap(1, 1, 1)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbttA)
                    .addComponent(lblA, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbttB)
                    .addComponent(lblB, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbttC)
                    .addComponent(lblC, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbttD)
                    .addComponent(lblD, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAMouseClicked
        rbttA.setSelected(true);
        rbttAMouseClicked(evt);
    }//GEN-LAST:event_lblAMouseClicked

    private void lblBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBMouseClicked
         rbttB.setSelected(true);
         rbttBMouseClicked(evt);
    }//GEN-LAST:event_lblBMouseClicked

    private void lblCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCMouseClicked
         rbttC.setSelected(true);
         rbttCMouseClicked(evt);
    }//GEN-LAST:event_lblCMouseClicked

    private void lblDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDMouseClicked
         rbttD.setSelected(true);
         rbttDMouseClicked(evt);
    }//GEN-LAST:event_lblDMouseClicked

    private void rbttAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbttAMouseClicked
        if (this.dapAn.compareToIgnoreCase("A")==0) dung = true;
        else dung = false;
        daChon ="A";
        System.out.println(dung);
        pnlIsChon.chon();
    }//GEN-LAST:event_rbttAMouseClicked

    private void rbttBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbttBMouseClicked
         if (this.dapAn.compareToIgnoreCase("B")==0) dung = true;
        else dung = false;
        daChon ="B";
        System.out.println(dung);
        pnlIsChon.chon();
    }//GEN-LAST:event_rbttBMouseClicked

    private void rbttCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbttCMouseClicked
         if (this.dapAn.compareToIgnoreCase("C")==0) dung = true;
        else dung = false;
        daChon ="C";
        System.out.println(dung);
        pnlIsChon.chon();
    }//GEN-LAST:event_rbttCMouseClicked

    private void rbttDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbttDMouseClicked
          if (this.dapAn.compareToIgnoreCase("D")==0) dung = true;
        else dung = false;
        daChon ="D";
        System.out.println(dung);
        pnlIsChon.chon();
    }//GEN-LAST:event_rbttDMouseClicked

    public PNLISCHON getPnlIsChon() {
        return pnlIsChon;
    }
    
    public void setLblCauSo(int cauSo) {
        this.lblCauSo.setText("Câu "+cauSo+":");
        this.pnlIsChon = new PNLISCHON(cauSo);
    }

    public boolean isDung() {
        return dung;
    }

    public int getSttCauHoi() {
        return sttCauHoi;
    }

    public String getDapAn() {
        return dapAn;
    }

    public String getTxtA() {
        return txtA;
    }

    public String getTxtB() {
        return txtB;
    }

    public String getTxtC() {
        return txtC;
    }

    public String getTxtD() {
        return txtD;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public String getDaChon(){
        return daChon;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bttGroupLuaChon;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblC;
    private javax.swing.JLabel lblCauSo;
    private javax.swing.JLabel lblD;
    private javax.swing.JLabel lblNoiDung;
    private javax.swing.JRadioButton rbttA;
    private javax.swing.JRadioButton rbttB;
    private javax.swing.JRadioButton rbttC;
    private javax.swing.JRadioButton rbttD;
    // End of variables declaration//GEN-END:variables
}
