/*
 * emisszió v3.17.0 @ 2017.3.5
 */
package steve._01_stackgas;


import java.awt.CardLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import org.joda.time.LocalTime;

/**
 * A fő ablakkezelő osztály.
 * @author István Ócsai
 */
public class MainFrame extends javax.swing.JFrame {
    
    private final DefaultListModel<String> listModelCalib = new DefaultListModel<>();
    private final DefaultListModel<String> listModelReport = new DefaultListModel<>();
    private FileIO cfDataFile;
    private CFData cfData;
    
    private SortedMap<LocalTime, ArrayList<Double>> oneMinAvg;
    private static SortedMap<LocalTime, ArrayList<Double>> oneMinAvgCalibrated;
    private SortedMap<LocalTime, ArrayList<Double>> oneMinAvgPhysNorm;

    public static SortedMap<LocalTime, ArrayList<Double>> getOneMinAvgCalibrated() {
        return oneMinAvgCalibrated;
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        setLocationRelativeTo(null);
        cfDataList.setModel(listModelCalib);
        cfDataList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cfDataList.setDragEnabled(true);
        calibratedValuesList.setModel(listModelReport);
        calibratedValuesList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        calibratedValuesList.setDragEnabled(true);
        headerLabel.setText(setHeader());
        headerLabel1.setText(setHeader());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calibCard = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        OpenButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        calibrationButton = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        changeCalibConcButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cfDataList = new javax.swing.JList<>();
        headerLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        zeroInitTextPane = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        zeroFinishTextPane = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        spanInitTextPane = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        spanNOInitTextPane = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        spanFinishTextPane = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        spanNOFinishTextPane = new javax.swing.JTextPane();
        reportCard = new javax.swing.JPanel();
        headerLabel1 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        newCalibration = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        fullReportButton = new javax.swing.JButton();
        periodReportButton = new javax.swing.JButton();
        oneMinReportButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jScrollPane8 = new javax.swing.JScrollPane();
        calibratedValuesList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Emisszió v 3.0 Füstgázmodul");
        getContentPane().setLayout(new java.awt.CardLayout());

        calibCard.setBorder(javax.swing.BorderFactory.createTitledBorder("Calibration"));

        jToolBar1.setRollover(true);

        OpenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document-open-2.png"))); // NOI18N
        OpenButton.setToolTipText("Open file");
        OpenButton.setFocusable(false);
        OpenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        OpenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        OpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(OpenButton);
        jToolBar1.add(jSeparator3);

        calibrationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calculator-link.png"))); // NOI18N
        calibrationButton.setToolTipText("Calibrate...");
        calibrationButton.setFocusable(false);
        calibrationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        calibrationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        calibrationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calibrationButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(calibrationButton);
        jToolBar1.add(jSeparator4);

        changeCalibConcButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/table-refresh.png"))); // NOI18N
        changeCalibConcButton.setToolTipText("Change calib conc");
        changeCalibConcButton.setFocusable(false);
        changeCalibConcButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        changeCalibConcButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(changeCalibConcButton);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(256, 128));

        jScrollPane1.setViewportView(cfDataList);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Alapvonalállítás adatai"));

        zeroInitTextPane.setBorder(javax.swing.BorderFactory.createTitledBorder("ZERO mérés előtt"));
        zeroInitTextPane.setText("10:35");
        jScrollPane2.setViewportView(zeroInitTextPane);

        zeroFinishTextPane.setBorder(javax.swing.BorderFactory.createTitledBorder("ZERO mérés után"));
        zeroFinishTextPane.setText("14:05");
        jScrollPane3.setViewportView(zeroFinishTextPane);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Meredékségállítás adatai"));

        spanInitTextPane.setBorder(javax.swing.BorderFactory.createTitledBorder("CO, CO2, O2, SO2 SPAN mérés előtt"));
        spanInitTextPane.setText("10:45");
        jScrollPane4.setViewportView(spanInitTextPane);

        spanNOInitTextPane.setBorder(javax.swing.BorderFactory.createTitledBorder("NO SPAN mérés előtt"));
        spanNOInitTextPane.setText("10:40");
        jScrollPane5.setViewportView(spanNOInitTextPane);

        spanFinishTextPane.setBorder(javax.swing.BorderFactory.createTitledBorder("CO, CO2, O2, SO2 SPAN mérés után"));
        spanFinishTextPane.setText("13:55");
        jScrollPane6.setViewportView(spanFinishTextPane);

        spanNOFinishTextPane.setBorder(javax.swing.BorderFactory.createTitledBorder("NO SPAN mérés után"));
        spanNOFinishTextPane.setText("14:00");
        jScrollPane7.setViewportView(spanNOFinishTextPane);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout calibCardLayout = new javax.swing.GroupLayout(calibCard);
        calibCard.setLayout(calibCardLayout);
        calibCardLayout.setHorizontalGroup(
            calibCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calibCardLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(calibCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(calibCardLayout.createSequentialGroup()
                .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        calibCardLayout.setVerticalGroup(
            calibCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calibCardLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(calibCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(calibCardLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(calibCardLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getContentPane().add(calibCard, "calibCard");

        reportCard.setBorder(javax.swing.BorderFactory.createTitledBorder("Reports"));
        reportCard.setPreferredSize(new java.awt.Dimension(360, 450));

        jToolBar2.setRollover(true);

        newCalibration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/draw-bezier-curves.png"))); // NOI18N
        newCalibration.setToolTipText("New calibration...");
        newCalibration.setFocusable(false);
        newCalibration.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newCalibration.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newCalibration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCalibrationActionPerformed(evt);
            }
        });
        jToolBar2.add(newCalibration);
        jToolBar2.add(jSeparator1);

        fullReportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/report.png"))); // NOI18N
        fullReportButton.setToolTipText("Create full report");
        fullReportButton.setFocusable(false);
        fullReportButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fullReportButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fullReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullReportButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(fullReportButton);

        periodReportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/draw-circle.png"))); // NOI18N
        periodReportButton.setToolTipText("Create period report");
        periodReportButton.setFocusable(false);
        periodReportButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        periodReportButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        periodReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                periodReportButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(periodReportButton);

        oneMinReportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/insert-numbers.png"))); // NOI18N
        oneMinReportButton.setToolTipText("Create one minute report");
        oneMinReportButton.setFocusable(false);
        oneMinReportButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        oneMinReportButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        oneMinReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneMinReportButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(oneMinReportButton);
        jToolBar2.add(jSeparator2);

        jScrollPane8.setPreferredSize(new java.awt.Dimension(256, 128));

        jScrollPane8.setViewportView(calibratedValuesList);

        javax.swing.GroupLayout reportCardLayout = new javax.swing.GroupLayout(reportCard);
        reportCard.setLayout(reportCardLayout);
        reportCardLayout.setHorizontalGroup(
            reportCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportCardLayout.createSequentialGroup()
                .addGroup(reportCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(headerLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 323, Short.MAX_VALUE))
        );
        reportCardLayout.setVerticalGroup(
            reportCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportCardLayout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(headerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(reportCard, "reportCard");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenButtonActionPerformed
        listModelCalib.removeAllElements();
        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            cfDataFile = new FileIO(selectedFile);
            cfData = new CFData(cfDataFile);
            oneMinAvg = cfData.getOneMinuteAverages();
            listOutput(oneMinAvg, listModelCalib);
        }
    }//GEN-LAST:event_OpenButtonActionPerformed

    private void calibrationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calibrationButtonActionPerformed
        listModelReport.removeAllElements();
        ArrayList<String> textPaneEntries = readCalibTimes();
        ArrayList<String> calibTimes = new ArrayList<>();
        Pattern p = Pattern.compile(Constants.REGEX_TIME);
        for (String next : textPaneEntries) {
            Matcher m = p.matcher(next);
            while (m.find()) {
                calibTimes.add(m.group(1) + ":" + m.group(2));
            }
        }
        doCalibration(calibTimes);
        CardLayout cardLO = (CardLayout) getContentPane().getLayout();
        cardLO.show(getContentPane(), "reportCard");
        listOutput(oneMinAvgCalibrated, listModelReport);
    }//GEN-LAST:event_calibrationButtonActionPerformed

    private void newCalibrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCalibrationActionPerformed
        CardLayout cardLO = (CardLayout) getContentPane().getLayout();
        cardLO.show(getContentPane(), "calibCard");
    }//GEN-LAST:event_newCalibrationActionPerformed

    private void fullReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullReportButtonActionPerformed
        FullReportDialog dialog = new FullReportDialog(this, true);
        dialog.setVisible(true);
        if (dialog.isOK()) {
            
        }
    }//GEN-LAST:event_fullReportButtonActionPerformed

    private void periodReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_periodReportButtonActionPerformed
        PeriodReportDialog dialog = new PeriodReportDialog(this, true);
        dialog.setIsOneMin(false);
        dialog.setVisible(true);
        if (dialog.isOK()) {
            
        }
    }//GEN-LAST:event_periodReportButtonActionPerformed

    private void oneMinReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneMinReportButtonActionPerformed
        PeriodReportDialog dialog = new PeriodReportDialog(this, true);
        dialog.setIsOneMin(true);
        dialog.setVisible(true);
        if (dialog.isOK()) {
            
        }
    }//GEN-LAST:event_oneMinReportButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OpenButton;
    private javax.swing.JPanel calibCard;
    private javax.swing.JList<String> calibratedValuesList;
    private javax.swing.JButton calibrationButton;
    private javax.swing.JList<String> cfDataList;
    private javax.swing.JButton changeCalibConcButton;
    private javax.swing.JButton fullReportButton;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel headerLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton newCalibration;
    private javax.swing.JButton oneMinReportButton;
    private javax.swing.JButton periodReportButton;
    private javax.swing.JPanel reportCard;
    private javax.swing.JTextPane spanFinishTextPane;
    private javax.swing.JTextPane spanInitTextPane;
    private javax.swing.JTextPane spanNOFinishTextPane;
    private javax.swing.JTextPane spanNOInitTextPane;
    private javax.swing.JTextPane zeroFinishTextPane;
    private javax.swing.JTextPane zeroInitTextPane;
    // End of variables declaration//GEN-END:variables

    private void listOutput(SortedMap<LocalTime, ArrayList<Double>> oneMinAvg, 
            DefaultListModel<String> listModel) {
        for (Map.Entry<LocalTime, ArrayList<Double>> entry : oneMinAvg.entrySet()) {
            LocalTime key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            String builder = key.toString(Constants.TIME_FORMAT);
            for (Double elem : value) {
                builder += String.format(Constants.NUM_FORMAT, elem);
            }
            listModel.addElement(builder);
        }
    }

    private String setHeader() {
        String header = "Time";
        for (String comp : Constants.COMPONENTS) {
            header += String.format("%11s", comp);
        }
        return header;
    }

    private ArrayList<String> readCalibTimes() {
        ArrayList<String> result = new ArrayList<>();
        result.add(zeroInitTextPane.getText());
        result.add(zeroFinishTextPane.getText());
        result.add(spanInitTextPane.getText());
        result.add(spanFinishTextPane.getText());
        result.add(spanNOInitTextPane.getText());
        result.add(spanNOFinishTextPane.getText());
        return result;
    }

    private void doCalibration(ArrayList<String> calibTimes) {
        Calibration calibrate = new Calibration(oneMinAvg);
        calibrate.setZeroTimeInit(LocalTime.parse(calibTimes.get(0)));
        calibrate.setZeroTimeFinit(LocalTime.parse(calibTimes.get(1)));
        calibrate.setCalibTimeInit(LocalTime.parse(calibTimes.get(2)));
        calibrate.setCalibTimeFinit(LocalTime.parse(calibTimes.get(3)));
        calibrate.setCalibNOTimeInit(LocalTime.parse(calibTimes.get(4)));
        calibrate.setCalibNOTimeFinit(LocalTime.parse(calibTimes.get(5)));
        calibrate.setSpanConcentrations("prop");
        calibrate.setZeroCorrection();
        calibrate.setSpanCorrection();
        oneMinAvgCalibrated = calibrate.getCorrectedAverages();
    }
    
}
