/*
 * emisszió v3.17.0 @ 2017.3.9
 */
package steve._01_stackgas;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.joda.time.LocalTime;

/**
 * A részreportok elkészítését segítő dialógusablak.
 * @author István Ócsai
 */
public class PeriodReportDialog extends javax.swing.JDialog {
    
    private boolean ok;
    private boolean isOneMin;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private ArrayList<String> periodReport;

    /**
     * Creates new form PeriodReportDialog
     */
    public PeriodReportDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        reportList.setModel(listModel);
    }

    public void setIsOneMin(boolean isOneMin) {
        this.isOneMin = isOneMin;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        calcButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        startTimeTextPane = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        endTimeTextPane = new javax.swing.JTextPane();
        headerCheckBox = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        reportList = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        refO2TextPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setRollover(true);

        calcButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table-edit.png"))); // NOI18N
        calcButton.setToolTipText("Calculate report");
        calcButton.setFocusable(false);
        calcButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        calcButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        calcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(calcButton);

        exportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document-export-4.png"))); // NOI18N
        exportButton.setToolTipText("Export report");
        exportButton.setFocusable(false);
        exportButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(exportButton);

        startTimeTextPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Start Time"));
        jScrollPane1.setViewportView(startTimeTextPane);

        endTimeTextPane.setBorder(javax.swing.BorderFactory.createTitledBorder("End Time"));
        jScrollPane2.setViewportView(endTimeTextPane);

        headerCheckBox.setText("Create with header");

        jScrollPane3.setViewportView(reportList);

        refO2TextPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Reference O2"));
        jScrollPane4.setViewportView(refO2TextPane);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addComponent(headerCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(headerCheckBox))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcButtonActionPerformed
        listModel.removeAllElements();
        ArrayList<String> textPaneEntries = readPaneEntries();
        ArrayList<String> reportTimeLimits = new ArrayList<>();
        Pattern p = Pattern.compile(Constants.REGEX_TIME);
        for (int next = 0; next < 2; next++) {
            Matcher m = p.matcher(textPaneEntries.get(next));
            while (m.find()) {
                reportTimeLimits.add(m.group(1) + ":" + m.group(2));
            }
        }
        StackGasReport report = new StackGasReport(MainFrame.getOneMinAvgCalibrated(), 
                LocalTime.parse(reportTimeLimits.get(0)), LocalTime.parse(reportTimeLimits.get(1)));
        report.setReferenceO2(Double.parseDouble(textPaneEntries.get(2)));
        if (isOneMin) {
            periodReport = report.getOneMinReport(headerCheckBox.isSelected());
        } else {
            periodReport = report.getPeriodReport(headerCheckBox.isSelected());
        }
        for (String elem : periodReport) {
            listModel.addElement(String.format("%11s", elem));
        }
    }//GEN-LAST:event_calcButtonActionPerformed

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        /*ExportDialog dialog = new ExportDialog(this, true);
        dialog.setReport(fullTimeReport);
        dialog.setVisible(true);
        if (dialog.isOK()) {
            
        }*/
        JFileChooser exportFile = new JFileChooser(new File(System.getProperty("user.home")));
        exportFile.setApproveButtonText("Save");
        exportFile.setDialogTitle("Export");
        int result = exportFile.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = exportFile.getSelectedFile();
            FileIO.exportReport(exportFile.getSelectedFile(), periodReport);
            JOptionPane.showMessageDialog(rootPane, "Export successfull!");
        }
    }//GEN-LAST:event_exportButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PeriodReportDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PeriodReportDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PeriodReportDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PeriodReportDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PeriodReportDialog dialog = new PeriodReportDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcButton;
    private javax.swing.JTextPane endTimeTextPane;
    private javax.swing.JButton exportButton;
    private javax.swing.JCheckBox headerCheckBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextPane refO2TextPane;
    private javax.swing.JList<String> reportList;
    private javax.swing.JTextPane startTimeTextPane;
    // End of variables declaration//GEN-END:variables

    public boolean isOK() {
        return ok;
    }
    
    private ArrayList<String> readPaneEntries() {
        ArrayList<String> result = new ArrayList<>();
        result.add(startTimeTextPane.getText());
        result.add(endTimeTextPane.getText());
        result.add(refO2TextPane.getText());
        return result;
    }
}