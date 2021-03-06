/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.gui;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entiteiten.*;
import be.ugent.iii.factory.BibliotheekFactory;
import be.ugent.iii.projectJPA.ProjectJpaBibApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
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
    private static BibliotheekFactory factory;
    private static Lid huidigLid;

    public BibliotheekGUI() {
        this.setTitle("Bibliotheek");
        initComponents();
        Library.setEnabledAt(1, false);

        IDTextField.setEnabled(false);
        NaamTextField.setEnabled(false);
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
        jLabel2 = new javax.swing.JLabel();
        RegistreerVoornaam = new javax.swing.JTextField();
        RegistreerAchternaam = new javax.swing.JTextField();
        RegistreerGeslacht = new javax.swing.JComboBox<>();
        RegistreerButton = new javax.swing.JButton();
        RegistreerResultaat = new javax.swing.JLabel();
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        BeschikbaarLijst = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        LeenButton = new javax.swing.JButton();

        ZoekButtonGroup.add(AlleRadioButton);
        ZoekButtonGroup.add(IDRadioButton);
        ZoekButtonGroup.add(NaamRadioButton);

        AlleRadioButton.setSelected(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Library.setMinimumSize(new java.awt.Dimension(0, 0));
        Library.setPreferredSize(new java.awt.Dimension(1150, 400));

        LoginPanel.setPreferredSize(new java.awt.Dimension(1150, 400));

        jLabel1.setText("Lid ID:");

        LoginButton.setText("Log in");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Nieuwe gebruiker:");

        RegistreerVoornaam.setText("Voornaam");

        RegistreerAchternaam.setText("Achternaam");

        RegistreerGeslacht.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Man", "Vrouw" }));

        RegistreerButton.setText("Registreer");
        RegistreerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistreerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LidID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LoginButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(RegistreerAchternaam, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RegistreerVoornaam, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RegistreerGeslacht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RegistreerButton)))
                .addGap(272, 272, 272))
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(RegistreerResultaat, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addComponent(RegistreerVoornaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RegistreerAchternaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(RegistreerGeslacht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RegistreerButton))
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(LoginPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LidID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LoginButton)))
                        .addGap(63, 63, 63)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RegistreerResultaat, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        Library.addTab("Inloggen", LoginPanel);

        SearchPanel.setPreferredSize(new java.awt.Dimension(1150, 400));

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

        jLabel3.setText("Zoekresultaat:");

        jScrollPane2.setViewportView(BeschikbaarLijst);

        jLabel4.setText("Beschikbare boeken:");

        LeenButton.setText("Leen");
        LeenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeenButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearchPanelLayout.createSequentialGroup()
                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SearchPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(LeenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SearchPanelLayout.createSequentialGroup()
                        .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AlleRadioButton)
                                    .addGroup(SearchPanelLayout.createSequentialGroup()
                                        .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(NaamRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(IDRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(53, 53, 53)
                                        .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(IDTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(NaamTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ZoekButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(KeuzeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                        .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(208, 208, 208))
        );
        SearchPanelLayout.setVerticalGroup(
            SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchPanelLayout.createSequentialGroup()
                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SearchPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(KeuzeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AlleRadioButton)
                        .addGap(18, 18, 18)
                        .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IDRadioButton)
                            .addComponent(IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NaamRadioButton)
                            .addComponent(NaamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(ZoekButton))
                    .addGroup(SearchPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4)
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(LeenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        Library.addTab("Opzoeken en uitlenen", SearchPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Library, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Library, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LeenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeenButtonActionPerformed
        // TODO add your handling code here:
        List<Boek> gekozenBoeken = BeschikbaarLijst.getSelectedValuesList();
        int[] indices = BeschikbaarLijst.getSelectedIndices();

        for (Boek b : gekozenBoeken) {
            Set<Lening> leningen = huidigLid.getLeningen();
            for (Lening le : leningen) {
                if (le.getBoek().equals(b)) {
                    huidigLid.remove(le);
                }
            }
        }

        for (Integer i : indices) {
            BeschikbaarLijst.remove(i);
        }
        try {
            //update beschikbaarlijst
            //hulpmethodeZoekResultaat(new StringBuilder(), ZoekButtonGroup.getSelection().getMnemonic());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Er is iets misgelopen");
        }
    }//GEN-LAST:event_LeenButtonActionPerformed

    private void NaamTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NaamTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NaamTextFieldActionPerformed

    private void ZoekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZoekButtonActionPerformed
        // TODO add your handling code here:
        StringBuilder s = new StringBuilder();
        int keuze = ZoekButtonGroup.getSelection().getMnemonic();

        try {
            hulpmethodeZoekResultaat(s, keuze);
            ResultTextArea.setText(s.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Het ingevoerde ID kan enkel uit cijfers bestaan");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_ZoekButtonActionPerformed

    private void IDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDTextFieldActionPerformed

    private void KeuzeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeuzeComboBoxActionPerformed
        // TODO add your handling code here:
        String keuze = KeuzeComboBox.getSelectedItem().toString();
    }//GEN-LAST:event_KeuzeComboBoxActionPerformed

    private void NaamRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NaamRadioButtonActionPerformed
        // TODO add your handling code here:
        IDTextField.setEnabled(false);
        NaamTextField.setEnabled(true);
    }//GEN-LAST:event_NaamRadioButtonActionPerformed

    private void IDRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDRadioButtonActionPerformed
        // TODO add your handling code here:
        IDTextField.setEnabled(true);
        NaamTextField.setEnabled(false);
    }//GEN-LAST:event_IDRadioButtonActionPerformed

    private void AlleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlleRadioButtonActionPerformed
        // TODO add your handling code here:
        IDTextField.setEnabled(false);
        NaamTextField.setEnabled(false);
    }//GEN-LAST:event_AlleRadioButtonActionPerformed

    private void RegistreerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistreerButtonActionPerformed
        // TODO add your handling code here:
        try {
            String voornaam = RegistreerVoornaam.getText();
            String achternaam = RegistreerAchternaam.getText();
            String geslacht = RegistreerGeslacht.getSelectedItem().toString();
            if (voornaam.isEmpty() || achternaam.isEmpty()) {
                throw new Exception("Er is een veld niet ingevuld");
            } else {
                Lid lid;
                if (geslacht.equalsIgnoreCase("man")) {
                    lid = factory.maakLid(voornaam, achternaam, 'M', null);
                } else {
                    lid = factory.maakLid(voornaam, achternaam, 'V', null);
                }
                dao.addLid(lid);
            }

            RegistreerResultaat.setText("Registratie gelukt, uw ID (onthouden!):" + dao.getLid(voornaam, achternaam).getId());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_RegistreerButtonActionPerformed

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        // TODO add your handling code here:
        try {
            int id = Integer.parseInt(LidID.getText());

            //Als lid bestaat: opzoeken en uitlenen activeren
            Lid lid = dao.getLid(id);
            if (lid != null) {
                Library.setEnabledAt(1, true);
            } else {
                throw new Exception("Lid bestaat niet!");
            }

            RegistreerResultaat.setText("Welkom " + lid.getVoorNaam() + " " + lid.getAchterNaam() + ", u kunt nu opzoeken of uitlenen");
            huidigLid = lid;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Een ID is een geheel getal!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void hulpmethodeZoekResultaat(StringBuilder b, int keuze) throws Exception {
        String comboBoxSelectie = KeuzeComboBox.getSelectedItem().toString();
        System.out.println(comboBoxSelectie);

        switch (comboBoxSelectie) {
            case "Bibliotheken":
                switch (keuze) {
                    //ALLE
                    case 1:
                        List<Bibliotheek> lijst = dao.getBibliotheken();
                        for (Bibliotheek bib : lijst) {
                            b.append(bib.toString()).append("\n");
                        }
                        break;
                    //ID
                    case 2:
                        throw new Exception("Nog niet ondersteund");
                    //break;
                    //NAAM
                    case 3:
                        b.append(dao.getBibliotheek(NaamTextField.getText()).toString());
                        break;
                }
                break;
            case "Auteurs":
                switch (keuze) {
                    //ALLE
                    case 1:
                        List<Auteur> lijst = dao.getAuteurs();
                        for (Auteur a : lijst) {
                            b.append(a.toString()).append("\n");
                        }
                        break;
                    //ID
                    case 2:
                        List<Auteur> result = dao.getAuteurs();
                        int i = Integer.parseInt(IDTextField.getText());
                        for (Auteur a : result) {
                            if (a.getId() == i) {
                                b.append(a.toString());
                            }
                        }
                        break;
                    //NAAM
                    case 3:
                        throw new Exception("Nog niet ondersteund");
                    //break;
                }
                break;
            case "Collecties":
                switch (keuze) {
                    //ALLE
                    case 1:
                        List<Collectie> lijst = dao.getCollecties();
                        for (Collectie c : lijst) {
                            b.append(c.toString()).append("\n");
                        }
                        break;
                    //ID
                    case 2:
                        List<Collectie> result = dao.getCollecties();
                        for (Collectie c : result) {
                            if (c.getId() == Integer.parseInt(IDTextField.getText())) {
                                b.append(c.toString()).append("\n");
                            }
                        }
                        break;
                    //NAAM
                    case 3:
                        throw new Exception("Nog niet ondersteund");
                    //break;
                }
                break;
            case "Boeken":
                switch (keuze) {
                    //ALLE
                    case 1:
                        List<Boek> lijst = dao.getBoeken();
                        List<Boek> beschikbaar = new ArrayList<>();
                        DefaultListModel listmodel = new DefaultListModel();

                        for (Boek a : lijst) {
                            b.append(a.toString()).append("\n");
                            if (a.isBeschikbaar()) {
                                beschikbaar.add(a);
                                listmodel.addElement(a);
                            }
                        }
                        BeschikbaarLijst.setModel(listmodel);
                        break;
                    //ID
                    case 2:
                        Boek s = dao.getBoek(Integer.parseInt(IDTextField.getText()));
                        if (s == null) {
                            throw new Exception();
                        } else {
                            b.append(s);
                        }
                        break;
                    //NAAM
                    case 3:
                        throw new Exception("Nog niet ondersteund");
                    //break;
                }
                break;
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Maak bibliotheek
                factory = new BibliotheekFactory();
                dao = new BibliotheekDao();
                Bibliotheek bib = factory.maakDeKrookVolledig();
                dao.addBibliotheek(bib);

                //Start hibernate
                ProjectJpaBibApplication.main(args);
                //start GUI
                new BibliotheekGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AlleRadioButton;
    private javax.swing.JList<Boek> BeschikbaarLijst;
    private javax.swing.JRadioButton IDRadioButton;
    private javax.swing.JTextField IDTextField;
    private javax.swing.JComboBox<String> KeuzeComboBox;
    private javax.swing.JButton LeenButton;
    private javax.swing.JTabbedPane Library;
    private javax.swing.JTextField LidID;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JRadioButton NaamRadioButton;
    private javax.swing.JTextField NaamTextField;
    private javax.swing.JTextField RegistreerAchternaam;
    private javax.swing.JButton RegistreerButton;
    private javax.swing.JComboBox<String> RegistreerGeslacht;
    private javax.swing.JLabel RegistreerResultaat;
    private javax.swing.JTextField RegistreerVoornaam;
    private javax.swing.JTextArea ResultTextArea;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JButton ZoekButton;
    private javax.swing.ButtonGroup ZoekButtonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
