/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn_csdlpt;

import java.awt.Color;

/**
 *
 * @author zLittleMasterz
 */
public class PNLISCHON extends javax.swing.JPanel {

    /**
     * Creates new form PNLISCHON
     */
    public PNLISCHON(int cauSo) {
        initComponents();
        lblCauSo.setText(String.valueOf(cauSo));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCauSo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 86, 86));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        setPreferredSize(new java.awt.Dimension(40, 40));

        lblCauSo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblCauSo.setForeground(new java.awt.Color(255, 255, 255));
        lblCauSo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCauSo.setText("130");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCauSo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCauSo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
   public void chon(){
       this.setBackground(new Color(112,214,0));
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCauSo;
    // End of variables declaration//GEN-END:variables
}