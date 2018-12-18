/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.projectJPA;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entities.Auteur;
import be.ugent.iii.entities.Bibliotheek;
import be.ugent.iii.entities.Boek;
import be.ugent.iii.entities.Collectie;
import be.ugent.iii.factory.BibliotheekFactory;
import java.util.List;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Arthur
 */
public class BibliotheekGUI extends javax.swing.JFrame {

    /**
     * Creates new form BibliotheekGUI
     */
    private static BibliotheekDao dao;

    public BibliotheekGUI() {
        this.setTitle("Bibliotheek");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ZoekButtonGroup = new javax.swing.ButtonGroup();
        Library = new javax.swing.JTabbedPane();
        LoginPanel = new javax.swing.JPanel();
        LidID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        SearchPanel = new javax.swing.JPanel();
        AlleRadioButton = new javax.swing.JRadioButton();
        IDRadioButton = new javax.swing.JRadioButton();
        NaamRadioButton = new javax.swing.JRadioButton();
        KeuzeComboBox = new javax.swing.JComboBox<>();
        IDTextField = new javax.swing.JTextField();
        ZoekButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResultTextArea = new javax.swing.JTextArea();
        NaamTextField = new javax.swing.JTextField();
        LoanPanel = new javax.swing.JPanel();

        ZoekButtonGroup.add(AlleRadioButton);
        ZoekButtonGroup.add(IDRadioButton);
        ZoekButtonGroup.add(NaamRadioButton);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Lid ID:");

        LoginButton.setText("Log in");

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LoginButton)
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LidID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(382, Short.MAX_VALUE))
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LidID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LoginButton)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        Library.addTab("Inloggen", LoginPanel);

        AlleRadioButton.setText("Alle");
        AlleRadioButton.setMnemonic(1);
        AlleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlleRadioButtonActionPerformed(evt);
            }
        });

        IDRadioButton.setText("ID");
        IDRadioButton.setMnemonic(2);
        IDRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDRadioButtonActionPerformed(evt);
            }
        });

        NaamRadioButton.setText("Naam");
        NaamRadioButton.setMnemonic(3);
        NaamRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NaamRadioButtonActionPerformed(evt);
            }
        });

        KeuzeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bibliotheken", "Auteurs", "Collecties", "Boeken" }));
        KeuzeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeuzeComboBoxActionPerformed(evt);
            }
        });

        IDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDTextFieldActionPerformed(evt);
            }
        });

        ZoekButton.setText("Zoek");
        ZoekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoekButtonActionPerformed(evt);
            }
        });

        ResultTextArea.setColumns(20);
        ResultTextArea.setRows(5);
        jScrollPane1.setViewportView(ResultTextArea);

        NaamTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NaamTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AlleRadioButton)
                    .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ZoekButton)
                        .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SearchPanelLayout.createSequentialGroup()
                                .addComponent(IDRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(KeuzeComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(SearchPanelLayout.createSequentialGroup()
                        .addComponent(NaamRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NaamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        SearchPanelLayout.setVerticalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(KeuzeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(AlleRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDRadioButton)
                    .addComponent(IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NaamRadioButton)
                    .addComponent(NaamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ZoekButton)
                .addContainerGap(158, Short.MAX_VALUE))
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        Library.addTab("Opzoeken", SearchPanel);

        javax.swing.GroupLayout LoanPanelLayout = new javax.swing.GroupLayout(LoanPanel);
        LoanPanel.setLayout(LoanPanelLayout);
        LoanPanelLayout.setHorizontalGroup(
            LoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );
        LoanPanelLayout.setVerticalGroup(
            LoanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        Library.addTab("Uitlenen", LoanPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Library, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Library, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AlleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlleRadioButtonActionPerformed
        // TODO add your handling code here:
        IDTextField.setEnabled(false);
        NaamTextField.setEnabled(false);
    }//GEN-LAST:event_AlleRadioButtonActionPerformed

    private void KeuzeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeuzeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeuzeComboBoxActionPerformed

    private void IDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDTextFieldActionPerformed

    private void IDRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDRadioButtonActionPerformed
        // TODO add your handling code here:
        IDTextField.setEnabled(true);
        NaamTextField.setEnabled(false);
    }//GEN-LAST:event_IDRadioButtonActionPerformed

    private void NaamRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NaamRadioButtonActionPerformed
        // TODO add your handling code here:
        IDTextField.setEnabled(false);
        NaamTextField.setEnabled(true);
    }//GEN-LAST:event_NaamRadioButtonActionPerformed

    private void NaamTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NaamTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NaamTextFieldActionPerformed

    private void ZoekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZoekButtonActionPerformed
        // TODO add your handling code here:
        StringBuilder s = new StringBuilder();
        int keuze = ZoekButtonGroup.getSelection().getMnemonic();

        try {
            comboboxHulpMethode(s, keuze);
            ResultTextArea.setText(s.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Het ingevoerde ID kan enkel uit cijfers bestaan");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Het gevraagde item bestaat niet");
        }
    }//GEN-LAST:event_ZoekButtonActionPerformed
    private void comboboxHulpMethode(StringBuilder b, int keuze) throws Exception {
        String comboBoxSelectie = KeuzeComboBox.getSelectedItem().toString();

        switch (comboBoxSelectie) {
            case "Bibliotheken":
                switch (keuze) {
                    //ALLE
                    case 1:
                        List<Bibliotheek> lijst = dao.getBibliotheken();
                        for (Bibliotheek bib : lijst) {
                            b.append(bib.toString()).append("\n");
                        }
                        return;
                    //ID
                    case 2:
                        return;
                    //NAAM
                    case 3:
                        b.append(dao.getBibliotheek(NaamTextField.getText()).toString());
                        return;
                }
            case "Auteurs":
                switch (keuze) {
                    //ALLE
                    case 1:
                        List<Auteur> lijst = dao.getAuteurs();
                        for (Auteur a : lijst) {
                            b.append(a.toString()).append("\n");
                        }
                        return;
                    //ID
                    case 2:
                        return;
                    //NAAM
                    case 3:
                        return;
                }
            case "Collecties":
                switch (keuze) {
                    //ALLE
                    case 1:
                        List<Collectie> lijst = dao.getCollecties();
                        for (Collectie c : lijst) {
                            b.append(c.toString()).append("\n");
                        }
                        return;
                    //ID
                    case 2:
                        return;
                    //NAAM
                    case 3:
                        return;
                }
            case "Boeken":
                switch (keuze) {
                    //ALLE
                    case 1:
                        List<Boek> lijst = dao.getBoeken();
                        for (Boek a : lijst) {
                            b.append(a.toString()).append("\n");
                        }
                        return;
                    //ID
                    case 2:
                        Boek s = dao.getBoek(Integer.parseInt(IDTextField.getText()));
                        if (s == null) {
                            throw new Exception();
                        } else {
                            b.append(s);
                        }
                        return;
                    //NAAM
                    case 3:
                        return;
                }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BibliotheekGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BibliotheekGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BibliotheekGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BibliotheekGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Maak bibliotheek
                BibliotheekFactory factory = new BibliotheekFactory();
                dao = new BibliotheekDao();
                Bibliotheek bib = factory.maakBibliotheekMetCollecties();
                dao.addBibliotheek(bib);
                //start GUI
                new BibliotheekGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AlleRadioButton;
    private javax.swing.JRadioButton IDRadioButton;
    private javax.swing.JTextField IDTextField;
    private javax.swing.JComboBox<String> KeuzeComboBox;
    private javax.swing.JTabbedPane Library;
    private javax.swing.JTextField LidID;
    private javax.swing.JPanel LoanPanel;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JRadioButton NaamRadioButton;
    private javax.swing.JTextField NaamTextField;
    private javax.swing.JTextArea ResultTextArea;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JButton ZoekButton;
    private javax.swing.ButtonGroup ZoekButtonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
