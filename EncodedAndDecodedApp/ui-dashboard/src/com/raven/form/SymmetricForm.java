
package com.raven.form;

import com.raven.symmetric.algorithms.FileSymmetricAlgorithms;
import com.raven.symmetric.algorithms.KeySymmetricAlgorithms;
import com.raven.symmetric.algorithms.StringSymmetricAlgorithms;
import com.raven.file.FileOperation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SymmetricForm extends javax.swing.JPanel {
    String[] algorithms = {"DES", "AES", "DESede", "Blowfish", "RC2"};
    int[] keySizeAes = {128, 192, 256};
    int[] keySizeDes = {56};
    int[] keySizeDesede = {112, 168};
    int[] keySizeBlowfish = {32, 56, 128, 448};
    int[] keySizeRc2 = {40, 128, 448, 1024};
    String[] modes = {"ECB", "CBC", "CTR", "CTS"};
    String[] paddings = {"ISO10126Padding", "PKCS5Padding"};
    String[] noPadding = {"NoPadding"};
    String[] paddingMain;
    String algorithm = "";
    int[] keySizeTemp;

    String mode = "";
    String padding = "";
    String event = "Encrypt";
    String type = "text";
    String inputText = "";
    String outputText = "";
    String key = "";
    byte[] iv16 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    byte[] iv8 = {0, 0, 0, 0, 0, 0, 0, 0};
    byte[] iv;
    /**
     * Creates new form Form_1
     */
    public SymmetricForm() {
        initComponents();
        // add actionListener radioButton
        ActionListener listenerRadio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == radioEncrypt) {
                    event = "Encrypt";
                }
                if (e.getSource() == radioDecrypt) {
                    event = "Decrypt";
                }

            }
        };

        radioEncrypt.addActionListener(listenerRadio);
        radioDecrypt.addActionListener(listenerRadio);

//        ActionListener listenerRadioSecond = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == radioString) {
//                    type = "text";
//                    panel_6.show();
//                    panel_7.hide();
//                }
//                if (e.getSource() == radioFile) {
//                    type = "file";
//                    panel_7.show();
//                    panel_6.hide();
//                }
//
//            }
//        };

//        radioFile.addActionListener(listenerRadioSecond);
//        radioString.addActionListener(listenerRadioSecond);

        comboBoxAlgorithms.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                algorithm = (String) cb.getSelectedItem();
                if (algorithm.equals("DES")) {
                    keySizeTemp = new int[keySizeDes.length];
                    keySizeTemp = keySizeDes;
                    iv = iv8;
                } else {
                    if (algorithm.equals("AES")) {
                        keySizeTemp = new int[keySizeAes.length];
                        keySizeTemp = keySizeAes;
                        iv = iv16;
                    } else {
                        if (algorithm.equals("DESede")) {
                            keySizeTemp = new int[keySizeDesede.length];
                            keySizeTemp = keySizeDesede;
                            iv = iv8;
                        } else {
                            if (algorithm.equals("Blowfish")) {
                                keySizeTemp = new int[keySizeBlowfish.length];
                                keySizeTemp = keySizeBlowfish;
                                iv = iv8;
                            } else {
                                if (algorithm.equals("RC2")) {
                                    keySizeTemp = new int[keySizeRc2.length];
                                    keySizeTemp = keySizeRc2;
                                    iv = iv8;
                                }
                            }
                        }
                    }
                }

                comboBoxKeySize.removeAllItems();
                for (int i = 0; i < keySizeTemp.length; i++) {
                    comboBoxKeySize.addItem(keySizeTemp[i]);
                }
            }
        });

        comboBoxKeySize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        comboBoxMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                mode = (String) cb.getSelectedItem();
                if (mode.equals("CTR") || mode.equals("CTS")) {
                    paddingMain = noPadding;
                } else {
                    paddingMain = paddings;
                }

                comboBoxPadding.removeAllItems();
                for (int i = 0; i < paddingMain.length; i++) {
                    comboBoxPadding.addItem(paddingMain[i]);
                }
            }
        });

        comboBoxPadding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                padding = (String) cb.getSelectedItem();
            }
        });

        btnBrowseKeyFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("", "txt");
                fileChooser.setFileFilter(fileFilter);
                fileChooser.setMultiSelectionEnabled(false);

                int x = fileChooser.showDialog(SymmetricForm.this, "Chọn file");
                if (x == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    textFieldKeyFile.setText(f.getAbsolutePath());

                }
            }
        });
           btnBrowseKey.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("", "txt");
                fileChooser.setFileFilter(fileFilter);
                fileChooser.setMultiSelectionEnabled(false);

                int x = fileChooser.showDialog(SymmetricForm.this, "Chọn file");
                if (x == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    textFieldKey.setText(f.getAbsolutePath());

                }
            }
        });

        btnNewButtonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSymmetricAlgorithms sa = new StringSymmetricAlgorithms();
                KeySymmetricAlgorithms ks = new KeySymmetricAlgorithms();
                inputText = textAreaInput.getText();
                FileOperation fo = new FileOperation();
                try {
                    key = fo.readFile(textFieldKey.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (event.equals("Encrypt")) {
                    if (algorithm != null && padding != null) {
                        if (mode.equals("CBC") || mode.equals("CTR") || mode.equals("CTS")) {
                            try {
                                outputText = sa.encryptUseIV(inputText, ks.convertStringToSecretKey(key, algorithm),
                                        algorithm, mode, padding, iv);
                                textAreaOutput.setText(outputText);
                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        } else {
                            if (mode.equals("ECB")) {
                                try {
                                    outputText = sa.encrypt(inputText, ks.convertStringToSecretKey(key, algorithm),
                                            algorithm, mode, padding);
                                } catch (Exception e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                                textAreaOutput.setText(outputText);
                            }
                        }
                    }

                } else {
                    if (event.equals("Decrypt")) {
                        if (algorithm != null && padding != null) {
                            if (mode.equals("CBC") || mode.equals("CTR") || mode.equals("CTS")) {
                                try {
                                    outputText = sa.decryptUseIV(inputText, ks.convertStringToSecretKey(key, algorithm),
                                            algorithm, mode, padding, iv);
                                    textAreaOutput.setText(outputText);
                                } catch (Exception e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            } else {
                                if (mode.equals("ECB")) {
                                    try {
                                        outputText = sa.decrypt(inputText, ks.convertStringToSecretKey(key, algorithm),
                                                algorithm, mode, padding);
                                    } catch (Exception e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                    textAreaOutput.setText(outputText);
                                }
                            }
                        }

                    }
                }
            }
        });

        // file
        btnBrowseKey.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("", "txt");
                fileChooser.setFileFilter(fileFilter);
                fileChooser.setMultiSelectionEnabled(false);

                int x = fileChooser.showDialog(SymmetricForm.this, "Chọn file");
                if (x == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    textFieldKeyFile.setText(f.getAbsolutePath());

                }

            }
        });

        btnBrowseInputFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
//				FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("", "txt");
//				fileChooser.setFileFilter(fileFilter);
                fileChooser.setMultiSelectionEnabled(false);

                int x = fileChooser.showDialog(SymmetricForm.this, "Chọn file");
                if (x == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    textFieldInputFile.setText(f.getAbsolutePath());

                }

            }
        });

        btnBrowseOutputFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
//				FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("", "txt");
//				fileChooser.setFileFilter(fileFilter);
                fileChooser.setMultiSelectionEnabled(false);

                int x = fileChooser.showDialog(SymmetricForm.this, "Chọn file");
                if (x == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    textFieldOutputFile.setText(f.getAbsolutePath());

                }

            }
        });

        btnStartWithFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                FileSymmetricAlgorithms sf = new FileSymmetricAlgorithms();
                KeySymmetricAlgorithms ks = new KeySymmetricAlgorithms();
                String pathInputFile = textFieldInputFile.getText();
                String pathOutFile = textFieldOutputFile.getText();
                FileOperation fo = new FileOperation();
                try {
                    key = fo.readFile(textFieldKeyFile.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (event.equals("Encrypt")) {
                    if (algorithm != null && padding != null) {
                        if (mode.equals("CBC") || mode.equals("CTR") || mode.equals("CTS")) {
                            try {
                                sf.encryptFileIV(pathInputFile, pathOutFile,
                                        ks.convertStringToSecretKey(key, algorithm), algorithm, mode, padding, iv);
                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        } else {
                            if (mode.equals("ECB")) {
                                try {
                                    sf.encryptFile(pathInputFile, pathOutFile,
                                            ks.convertStringToSecretKey(key, algorithm), algorithm, mode, padding);
                                } catch (Exception e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                                textAreaOutput.setText(outputText);
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(SymmetricForm.this, "Đã mã hóa tệp");

                } else {
                    if (event.equals("Decrypt")) {
                        if (algorithm != null && padding != null) {
                            if (mode.equals("CBC") || mode.equals("CTR") || mode.equals("CTS")) {
                                try {
                                    sf.decryptFileIV(pathInputFile, pathOutFile,
                                            ks.convertStringToSecretKey(key, algorithm), algorithm, mode, padding, iv);

                                } catch (Exception e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            } else {
                                if (mode.equals("ECB")) {
                                    try {
                                        sf.decryptFile(pathInputFile, pathOutFile,
                                                ks.convertStringToSecretKey(key, algorithm), algorithm, mode, padding);
                                    } catch (Exception e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                    textAreaOutput.setText(outputText);
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(SymmetricForm.this, "Đã giải mã tệp");

                    }
                }

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jSelect = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        radioEncrypt = new javax.swing.JRadioButton();
        radioDecrypt = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jProperty = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboBoxAlgorithms = new javax.swing.JComboBox<String>(algorithms);
        comboBoxKeySize = new javax.swing.JComboBox<Integer>();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboBoxMode = new javax.swing.JComboBox<String>(modes);
        comboBoxPadding = new javax.swing.JComboBox<String>(paddings);
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        textFieldKeyFile = new javax.swing.JTextField();
        textFieldInputFile = new javax.swing.JTextField();
        textFieldOutputFile = new javax.swing.JTextField();
        btnBrowseKeyFile = new javax.swing.JButton();
        btnBrowseInputFile = new javax.swing.JButton();
        btnBrowseOutputFile = new javax.swing.JButton();
        btnStartWithFile = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textFieldKey = new javax.swing.JTextField();
        btnBrowseKey = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaInput = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaOutput = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        btnNewButtonStart = new javax.swing.JButton();

        setBackground(new java.awt.Color(242, 242, 242));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jSelect.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSelect.setText("Select");

        jLabel2.setText("Encrypt");

        jLabel3.setText("Decrypt");

        buttonGroup1.add(radioEncrypt);

        buttonGroup1.add(radioDecrypt);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addComponent(radioEncrypt))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(radioDecrypt))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSelect)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(radioDecrypt)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSelect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(radioEncrypt))
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3)))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jSelect.getAccessibleContext().setAccessibleParent(jSelect);

        jPanel2.setBackground(new java.awt.Color(242, 242, 242));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jProperty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jProperty.setText("Property");

        jLabel5.setText("Algorithm");

        jLabel6.setText("Key size");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProperty)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxKeySize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxAlgorithms, 0, 132, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jProperty)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboBoxAlgorithms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboBoxKeySize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(242, 242, 242));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Option");

        jLabel8.setText("Mode");

        jLabel9.setText("Padding");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxPadding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(comboBoxMode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboBoxMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(comboBoxPadding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/security .png"))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(242, 242, 242));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPane1.setName("File"); // NOI18N

        jPanel4.setBackground(new java.awt.Color(242, 242, 242));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setName("File"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Browse File");

        jLabel11.setText("Key File");

        jLabel12.setText("Input File");

        jLabel13.setText("Output File");

        btnBrowseKeyFile.setText("Browse");

        btnBrowseInputFile.setText("Browse");

        btnBrowseOutputFile.setText("Browse");

        btnStartWithFile.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnStartWithFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/power-button.png"))); // NOI18N
        btnStartWithFile.setText("Start");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldInputFile)
                                    .addComponent(textFieldOutputFile))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBrowseInputFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBrowseOutputFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(textFieldKeyFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBrowseKeyFile, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(btnStartWithFile, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldOutputFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBrowseOutputFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldKeyFile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBrowseKeyFile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldInputFile, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBrowseInputFile, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnStartWithFile, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("File", jPanel4);

        jPanel5.setBackground(new java.awt.Color(242, 242, 242));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel4.setText("Key File");

        btnBrowseKey.setText("Browse");

        textAreaInput.setColumns(20);
        textAreaInput.setRows(5);
        jScrollPane1.setViewportView(textAreaInput);

        jLabel14.setText("Enter Input");

        textAreaOutput.setColumns(20);
        textAreaOutput.setRows(5);
        jScrollPane2.setViewportView(textAreaOutput);

        jLabel15.setText("Output");

        btnNewButtonStart.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNewButtonStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/power-button.png"))); // NOI18N
        btnNewButtonStart.setText("Start");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(textFieldKey)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBrowseKey, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(203, Short.MAX_VALUE)
                .addComponent(btnNewButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBrowseKey))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel14))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNewButtonStart, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jTabbedPane1.addTab("String", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("File");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseInputFile;
    private javax.swing.JButton btnBrowseKey;
    private javax.swing.JButton btnBrowseKeyFile;
    private javax.swing.JButton btnBrowseOutputFile;
    private javax.swing.JButton btnNewButtonStart;
    private javax.swing.JButton btnStartWithFile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboBoxAlgorithms;
    private javax.swing.JComboBox<Integer> comboBoxKeySize;
    private javax.swing.JComboBox<String> comboBoxMode;
    private javax.swing.JComboBox<String> comboBoxPadding;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jProperty;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jSelect;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton radioDecrypt;
    private javax.swing.JRadioButton radioEncrypt;
    private javax.swing.JTextArea textAreaInput;
    private javax.swing.JTextArea textAreaOutput;
    private javax.swing.JTextField textFieldInputFile;
    private javax.swing.JTextField textFieldKey;
    private javax.swing.JTextField textFieldKeyFile;
    private javax.swing.JTextField textFieldOutputFile;
    // End of variables declaration//GEN-END:variables
}
