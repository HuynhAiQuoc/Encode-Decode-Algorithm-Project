
package com.raven.form;

import com.raven.digital.signature.DigitalSignature;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;


public class SignatureForm extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    public SignatureForm() {
        initComponents();

        jbtnPrivateKeyFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//                FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("", "txt");
//                fileChooser.setFileFilter(fileFilter);
//                fileChooser.setMultiSelectionEnabled(false);

                int x = fileChooser.showDialog(SignatureForm.this, "Chọn file");
                if (x == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    jtfPrivateKeyFile.setText(f.getAbsolutePath());
                } else {
                    jtfPrivateKeyFile.setText("No path is selected");
                }
            }
        });
        
        jbtnSignatureFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//                FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("", "txt");
//                fileChooser.setFileFilter(fileFilter);
//                fileChooser.setMultiSelectionEnabled(false);

                int x = fileChooser.showDialog(SignatureForm.this, "Chọn file");
                if (x == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    jtfSignature.setText(f.getAbsolutePath());
                } else {
                    jtfSignature.setText("No path is selected");
                }
            }
        });
        
        jbtnDestinationFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("", "txt");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setMultiSelectionEnabled(false);

                int x = fileChooser.showDialog(SignatureForm.this, "Chọn file");
                if (x == JFileChooser.APPROVE_OPTION) {
                    File f = fileChooser.getSelectedFile();
                    jtfDestinationFile.setText(f.getAbsolutePath());
                } else {
                    jtfDestinationFile.setText("No is path selected");
                }
            }
        });

        jbtnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourceFile = jtfSignature.getText();
                String destFile = jtfDestinationFile.getText();
                if(sourceFile.equalsIgnoreCase("")
                        || sourceFile.equalsIgnoreCase("No source file is selected")
                        || destFile.equalsIgnoreCase("")
                        || destFile.equalsIgnoreCase("No destination file is selected")){
                    JOptionPane.showMessageDialog(jPanel3, "Something missing");
                } else {
                    String keyFilePath = jtfPrivateKeyFile.getText();
                    
                    if(keyFilePath.equalsIgnoreCase("") || keyFilePath.equalsIgnoreCase("No file is selected")){
                        JOptionPane.showMessageDialog(jPanel3, "Please select a key file");
                    } else {
                        try {
                            PrivateKey priv = DigitalSignature.readPrivateKey(keyFilePath);
                            destFile += ("\\signature_for_" + FilenameUtils.getBaseName(sourceFile));
                            DigitalSignature.createSignature(priv, sourceFile, destFile);
                            
                            JOptionPane.showMessageDialog(jPanel3, "Creating Successfully");
                        } catch(HeadlessException | IOException | NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e1){
                            JOptionPane.showMessageDialog(jPanel3, "Failed. Check your input and try again");
                        }
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

        jPanel3 = new javax.swing.JPanel();
        jlbBrowseFile = new javax.swing.JLabel();
        jlbPrivateKeyFile = new javax.swing.JLabel();
        jlbSignature = new javax.swing.JLabel();
        jlbDestinationFile = new javax.swing.JLabel();
        jtfPrivateKeyFile = new javax.swing.JTextField();
        jtfSignature = new javax.swing.JTextField();
        jtfDestinationFile = new javax.swing.JTextField();
        jbtnPrivateKeyFile = new javax.swing.JButton();
        jbtnSignatureFile = new javax.swing.JButton();
        jbtnDestinationFile = new javax.swing.JButton();
        jbtnStart = new javax.swing.JButton();
        jlbImage = new javax.swing.JLabel();
        jlbTitle = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jlbBrowseFile.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jlbBrowseFile.setText("Browse File");

        jlbPrivateKeyFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbPrivateKeyFile.setText("Private Key File:");

        jlbSignature.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbSignature.setText("File for creating signature:");

        jlbDestinationFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbDestinationFile.setText("Destination File:");

        jbtnPrivateKeyFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnPrivateKeyFile.setText("Browse");

        jbtnSignatureFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnSignatureFile.setText("Browse");

        jbtnDestinationFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jbtnDestinationFile.setText("Browse");

        jbtnStart.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jbtnStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/power-button.png"))); // NOI18N
        jbtnStart.setText("Start");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfSignature)
                                    .addComponent(jtfPrivateKeyFile))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbtnPrivateKeyFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnSignatureFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbPrivateKeyFile)
                                    .addComponent(jlbSignature, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbDestinationFile, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jtfDestinationFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtnDestinationFile, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlbBrowseFile))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jbtnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbBrowseFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jlbPrivateKeyFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPrivateKeyFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnPrivateKeyFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbSignature)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfSignature, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSignatureFile, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbDestinationFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnDestinationFile, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jtfDestinationFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jlbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/digital-signature.png"))); // NOI18N

        jlbTitle.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jlbTitle.setForeground(new java.awt.Color(0, 102, 153));
        jlbTitle.setText("Digital Signature Creator");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jlbTitle)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jbtnDestinationFile;
    private javax.swing.JButton jbtnPrivateKeyFile;
    private javax.swing.JButton jbtnSignatureFile;
    private javax.swing.JButton jbtnStart;
    private javax.swing.JLabel jlbBrowseFile;
    private javax.swing.JLabel jlbDestinationFile;
    private javax.swing.JLabel jlbImage;
    private javax.swing.JLabel jlbPrivateKeyFile;
    private javax.swing.JLabel jlbSignature;
    private javax.swing.JLabel jlbTitle;
    private javax.swing.JTextField jtfDestinationFile;
    private javax.swing.JTextField jtfPrivateKeyFile;
    private javax.swing.JTextField jtfSignature;
    // End of variables declaration//GEN-END:variables
}
