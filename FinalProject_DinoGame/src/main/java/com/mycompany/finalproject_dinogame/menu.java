package com.mycompany.finalproject_dinogame;

import java.awt.*;
import javax.swing.*;

/* Name: Darren Drew
 * Date: June 4th, 2023
 * Desc: Dino Game: The main menu
 */

public class menu extends javax.swing.JFrame {

    public menu() {
        initComponents();
        setLocationRelativeTo(null);
        //BUTTON COLOURS
        Color clickColor = new Color(0, 78, 255);
        Color backColor = new Color(245, 245, 245);
        start.setBackground(backColor);
        start.setForeground(clickColor);
        start.setBorder(BorderFactory.createLineBorder(backColor));
        start.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 36));
        exit.setBackground(backColor);
        exit.setForeground(clickColor);
        exit.setBorder(BorderFactory.createLineBorder(backColor));
        exit.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 36));
        controls.setBackground(backColor);
        controls.setForeground(clickColor);
        controls.setBorder(BorderFactory.createLineBorder(backColor));
        controls.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 36));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        start = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        controls = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 800));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        start.setText("Dino Game");
        start.setMaximumSize(new java.awt.Dimension(50, 25));
        start.setMinimumSize(new java.awt.Dimension(50, 25));
        start.setPreferredSize(new java.awt.Dimension(500, 200));
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        getContentPane().add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 550, 180, 60));

        exit.setText("Exit");
        exit.setMaximumSize(new java.awt.Dimension(55, 25));
        exit.setMinimumSize(new java.awt.Dimension(55, 25));
        exit.setPreferredSize(new java.awt.Dimension(55, 25));
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 495, 70, 50));

        controls.setText("control manual");
        controls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlsActionPerformed(evt);
            }
        });
        getContentPane().add(controls, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 435, 250, 50));
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DinoGame_TitleScreen.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        main open = new main();
        dispose();
    }//GEN-LAST:event_startActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void controlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_controlsActionPerformed
        controls open = new controls();
        open.setVisible(true);
        open.setLocationRelativeTo(null);
        dispose();
    }//GEN-LAST:event_controlsActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton controls;
    private javax.swing.JButton exit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton start;
    // End of variables declaration//GEN-END:variables
}
