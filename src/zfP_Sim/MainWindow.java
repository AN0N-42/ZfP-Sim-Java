package zfP_Sim;

import control.Body;
import control.Scan;
import control.Sender;
import enums.VerificationType;
import eventListeners.DocumentVerificationListener;
import eventListeners.DragDropListener;
import geometry.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import properties.FieldVerifier;
import shapesBase.ShapeBase;
import structures.StructFieldType;

public class MainWindow extends BodyWindow {
    
    public double[][] senderPositions;
    public int index;
    public int rotationSpeed;
    public FieldVerifier fv;
        
    public MainWindow() {
        initComponents();
        body = new Body(); body.exampleLongBar();
        getSenderPositions();
        simPanel.main = this;
        scanPanel.main = this;
        simPanel.drawBody(senderPositions[index]);
        rotationSpeed = 1;
        fv = new FieldVerifier(simStartButton);
        
        StructFieldType sx = new StructFieldType(senderXField, VerificationType.NON_NEG_DOUBLE);
        StructFieldType sy = new StructFieldType(senderYField, VerificationType.NON_NEG_DOUBLE);
        StructFieldType rx = new StructFieldType(rayXField, VerificationType.DOUBLE);
        StructFieldType ry = new StructFieldType(rayYField, VerificationType.DOUBLE);
        StructFieldType nr = new StructFieldType(numRayField, VerificationType.POS_INTEGER);
        StructFieldType a = new StructFieldType(angleField, VerificationType.POS_DOUBLE);
        StructFieldType rf = new StructFieldType(refField, VerificationType.POS_INTEGER);
        StructFieldType vl = new StructFieldType(velocityField, VerificationType.POS_DOUBLE);
        StructFieldType rn = new StructFieldType(rangeField, VerificationType.POS_DOUBLE);
        fv.addField(sx);
        fv.addField(sy);
        fv.addField(rx);
        fv.addField(ry);
        fv.addField(nr);
        fv.addField(a);
        fv.addField(rf);
        fv.addField(vl);
        fv.addField(rn);
        senderXField.getDocument().addDocumentListener(new DocumentVerificationListener(sx, fv) );
        senderYField.getDocument().addDocumentListener(new DocumentVerificationListener(sy, fv) );
        rayXField.getDocument().addDocumentListener(new DocumentVerificationListener(rx, fv) );
        rayYField.getDocument().addDocumentListener(new DocumentVerificationListener(ry, fv) );
        numRayField.getDocument().addDocumentListener(new DocumentVerificationListener(nr, fv) );
        angleField.getDocument().addDocumentListener(new DocumentVerificationListener(a, fv) );
        refField.getDocument().addDocumentListener(new DocumentVerificationListener(rf, fv) );
        velocityField.getDocument().addDocumentListener(new DocumentVerificationListener(vl, fv) );
        rangeField.getDocument().addDocumentListener(new DocumentVerificationListener(rn, fv) );
        
        //KeyEventListener
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            
            @Override
            public boolean dispatchKeyEvent(KeyEvent evt) {
                if(simPanel.main.isFocused()){
                    int type = evt.getID();
                    if(type == KeyEvent.KEY_PRESSED){
                        int keyCode = evt.getKeyCode();
                        switch(keyCode){
                            case KeyEvent.VK_RIGHT:
                                MouseListener[] listenersR = simPanel.getMouseListeners();
                                if(listenersR.length == 1 && lit != null){
                                    lit.rotate(getRotationSpeed());
                                    body.refreshDragPoints();
                                    if( body.outline.contains(lit) ){
                                        outlineChanged();
                                    }
                                    simPanel.drawBody_Edit();
                                }
                                else if(listenersR.length == 0){
                                    prevIndex();
                                    simPanel.drawBody(senderPositions[index]);
                                }
                                return false;
                            case KeyEvent.VK_LEFT:
                                MouseListener[] listenersL = simPanel.getMouseListeners();
                                if(listenersL.length == 1 && lit != null){
                                    lit.rotate(-getRotationSpeed());
                                    body.refreshDragPoints();
                                    if( body.outline.contains(lit) ){
                                        outlineChanged();
                                    }
                                    simPanel.drawBody_Edit();
                                }
                                else if(listenersL.length == 0){
                                    nextIndex();
                                    simPanel.drawBody(senderPositions[index]);
                                }
                                return false;
                            case KeyEvent.VK_ENTER:
                                simStartButtonActionPerformed(null);
                                return false;
                            case KeyEvent.VK_SHIFT:
                                bodyEditButtonActionPerformed(null);
                                return false;
                            default:
                                return false;
                        }
                    }
                    else if(type == KeyEvent.KEY_RELEASED){
                        rotationSpeed = 1;
                        return false;
                    }
                }
                return false;
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        simToolBar = new javax.swing.JToolBar();
        senderXLabel = new javax.swing.JLabel();
        senderXField = new javax.swing.JTextField();
        senderYLabel = new javax.swing.JLabel();
        senderYField = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        rayXLabel = new javax.swing.JLabel();
        rayXField = new javax.swing.JTextField();
        rayYLabel = new javax.swing.JLabel();
        rayYField = new javax.swing.JTextField();
        numRayLabel = new javax.swing.JLabel();
        numRayField = new javax.swing.JTextField();
        angleLabel = new javax.swing.JLabel();
        angleField = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        refLabel = new javax.swing.JLabel();
        refField = new javax.swing.JTextField();
        velocityLabel = new javax.swing.JLabel();
        velocityField = new javax.swing.JTextField();
        rangeLabel = new javax.swing.JLabel();
        rangeField = new javax.swing.JTextField();
        simStartButton = new javax.swing.JButton();
        bodyEditButton = new javax.swing.JButton();
        jSplitPane2 = new javax.swing.JSplitPane();
        simPanel = new drawing.DrawPanel();
        scanPanel = new zfP_Sim.ScanPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenu = new javax.swing.JMenu();
        new2DMenuItem = new javax.swing.JMenuItem();
        newPregenMenuItem = new javax.swing.JMenuItem();
        editMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        openMenuItem = new javax.swing.JMenuItem();
        closeMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exportMenu = new javax.swing.JMenu();
        exportPDFMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        propertiesMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jSplitPane1.setDividerLocation(140);
        jSplitPane1.setDividerSize(0);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jSplitPane1.setPreferredSize(new java.awt.Dimension(1920, 1040));

        simToolBar.setBorder(null);
        simToolBar.setFloatable(false);
        simToolBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        simToolBar.setRollover(true);

        senderXLabel.setText("Sender x");
        simToolBar.add(senderXLabel);

        senderXField.setText("360");
        senderXField.setMinimumSize(new java.awt.Dimension(48, 26));
        senderXField.setPreferredSize(new java.awt.Dimension(48, 26));
        simToolBar.add(senderXField);

        senderYLabel.setText("Sender y");
        simToolBar.add(senderYLabel);

        senderYField.setText("30");
        senderYField.setMinimumSize(new java.awt.Dimension(48, 26));
        senderYField.setPreferredSize(new java.awt.Dimension(48, 26));
        simToolBar.add(senderYField);
        simToolBar.add(jSeparator6);

        rayXLabel.setText("Vektor x");
        simToolBar.add(rayXLabel);

        rayXField.setText("0");
        rayXField.setMinimumSize(new java.awt.Dimension(48, 26));
        rayXField.setPreferredSize(new java.awt.Dimension(48, 26));
        simToolBar.add(rayXField);

        rayYLabel.setText("Vektor y");
        simToolBar.add(rayYLabel);

        rayYField.setText("5");
        rayYField.setMinimumSize(new java.awt.Dimension(48, 26));
        rayYField.setPreferredSize(new java.awt.Dimension(48, 26));
        simToolBar.add(rayYField);

        numRayLabel.setText("Anzahl der Strahlen");
        simToolBar.add(numRayLabel);

        numRayField.setText("3");
        numRayField.setMinimumSize(new java.awt.Dimension(48, 26));
        numRayField.setPreferredSize(new java.awt.Dimension(48, 26));
        simToolBar.add(numRayField);

        angleLabel.setText("Streuungswinkel in °");
        simToolBar.add(angleLabel);

        angleField.setText("5");
        angleField.setMinimumSize(new java.awt.Dimension(48, 26));
        angleField.setPreferredSize(new java.awt.Dimension(48, 26));
        simToolBar.add(angleField);
        simToolBar.add(jSeparator7);

        refLabel.setText("Reflexionen");
        simToolBar.add(refLabel);

        refField.setText("5");
        refField.setMinimumSize(new java.awt.Dimension(48, 26));
        refField.setPreferredSize(new java.awt.Dimension(48, 26));
        simToolBar.add(refField);

        velocityLabel.setText("Geschwindigkeit");
        simToolBar.add(velocityLabel);

        velocityField.setText("1000");
        velocityField.setMinimumSize(new java.awt.Dimension(48, 26));
        velocityField.setPreferredSize(new java.awt.Dimension(48, 26));
        simToolBar.add(velocityField);

        rangeLabel.setText("Reichweite");
        simToolBar.add(rangeLabel);

        rangeField.setText("20");
        rangeField.setMinimumSize(new java.awt.Dimension(48, 26));
        rangeField.setPreferredSize(new java.awt.Dimension(48, 26));
        simToolBar.add(rangeField);

        simStartButton.setText("Simulation starten");
        simStartButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        simStartButton.setFocusable(false);
        simStartButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        simStartButton.setMaximumSize(new java.awt.Dimension(120, 26));
        simStartButton.setMinimumSize(new java.awt.Dimension(120, 26));
        simStartButton.setPreferredSize(new java.awt.Dimension(120, 26));
        simStartButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        simStartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simStartButtonActionPerformed(evt);
            }
        });
        simToolBar.add(simStartButton);

        bodyEditButton.setText("Prüfkörper editieren");
        bodyEditButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bodyEditButton.setFocusable(false);
        bodyEditButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bodyEditButton.setMaximumSize(new java.awt.Dimension(120, 26));
        bodyEditButton.setMinimumSize(new java.awt.Dimension(120, 26));
        bodyEditButton.setPreferredSize(new java.awt.Dimension(120, 26));
        bodyEditButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bodyEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bodyEditButtonActionPerformed(evt);
            }
        });
        simToolBar.add(bodyEditButton);

        jSplitPane1.setLeftComponent(simToolBar);

        jSplitPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSplitPane2.setDividerLocation(660);
        jSplitPane2.setDividerSize(0);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setToolTipText("");
        jSplitPane2.setEnabled(false);
        jSplitPane2.setMinimumSize(new java.awt.Dimension(0, 0));
        jSplitPane2.setPreferredSize(new java.awt.Dimension(1780, 1040));
        jSplitPane2.setRequestFocusEnabled(false);

        simPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        simPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        simPanel.setPreferredSize(new java.awt.Dimension(1780, 660));

        javax.swing.GroupLayout simPanelLayout = new javax.swing.GroupLayout(simPanel);
        simPanel.setLayout(simPanelLayout);
        simPanelLayout.setHorizontalGroup(
            simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1770, Short.MAX_VALUE)
        );
        simPanelLayout.setVerticalGroup(
            simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        jSplitPane2.setLeftComponent(simPanel);

        scanPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scanPanel.setMaximumSize(new java.awt.Dimension(1780, 380));
        scanPanel.setMinimumSize(new java.awt.Dimension(1780, 300));
        scanPanel.setPreferredSize(new java.awt.Dimension(1780, 380));

        javax.swing.GroupLayout scanPanelLayout = new javax.swing.GroupLayout(scanPanel);
        scanPanel.setLayout(scanPanelLayout);
        scanPanelLayout.setHorizontalGroup(
            scanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1776, Short.MAX_VALUE)
        );
        scanPanelLayout.setVerticalGroup(
            scanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(scanPanel);

        jSplitPane1.setRightComponent(jSplitPane2);

        fileMenu.setText("Datei");

        newMenu.setText("Neu");

        new2DMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        new2DMenuItem.setText("2D");
        new2DMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new2DMenuItemActionPerformed(evt);
            }
        });
        newMenu.add(new2DMenuItem);

        newPregenMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        newPregenMenuItem.setText("Beispiele");
        newPregenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPregenMenuItemActionPerformed(evt);
            }
        });
        newMenu.add(newPregenMenuItem);

        fileMenu.add(newMenu);

        editMenuItem.setText("Bearbeiten");
        editMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(editMenuItem);
        fileMenu.add(jSeparator1);

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setText("Öffnen");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        closeMenuItem.setText("Schließen");
        closeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(closeMenuItem);
        fileMenu.add(jSeparator2);

        exportMenu.setText("Exportieren");

        exportPDFMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        exportPDFMenuItem.setText("als PDF");
        exportPDFMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPDFMenuItemActionPerformed(evt);
            }
        });
        exportMenu.add(exportPDFMenuItem);

        fileMenu.add(exportMenu);
        fileMenu.add(jSeparator3);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Speichern");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setText("Speichern als");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(jSeparator5);

        propertiesMenuItem.setText("Eigenschaften");
        fileMenu.add(propertiesMenuItem);
        fileMenu.add(jSeparator4);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText("Verlassen");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText("Hilfe");

        jMenuItem3.setText("T.B.D.");
        helpMenu.add(jMenuItem3);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter f = new FileNameExtensionFilter("Serialisierte Java-Objekte", "ser");
        jfc.setFileFilter(f);
        if(jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();
            try{
                FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(body);
                out.close();
         fileOut.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeMenuItemActionPerformed
        body = new Body();
        getSenderPositions();
        simPanel.drawClear();
    }//GEN-LAST:event_closeMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void simStartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simStartButtonActionPerformed
        if(!body.outline.isEmpty()){
            MouseListener[] listeners = simPanel.getMouseListeners();
            if(listeners.length == 1){
                simPanel.removeMouseListener(listeners[0]);
            }
            Sender sender = new Sender(new Ray( new Point(Double.parseDouble(senderXField.getText()), Double.parseDouble(senderYField.getText())), new Vector(Double.parseDouble(rayXField.getText()), Double.parseDouble(rayYField.getText()))), Double.parseDouble(rangeField.getText()));
            Scan scan = new Scan(body, sender, Integer.parseInt(refField.getText()), Double.parseDouble(velocityField.getText()), 0);
            if(Integer.parseInt(numRayField.getText()) == 1){
                simPanel.simulate(scan.reflections());
                scanPanel.setScores(scan.scan_A());
            }
            else{
                simPanel.simulate(scan.MultiReflections(Integer.parseInt(numRayField.getText()) , Double.parseDouble(angleField.getText())));
                //scanPanel.setScores(scan.MultiScan_A(Integer.parseInt(numRayField.getText()) , Double.parseDouble(angleField.getText())));
                //scanPanel.setScores(scan.processScan_A(scan.MultiScan_A(Integer.parseInt(numRayField.getText()) , Double.parseDouble(angleField.getText())), 0.5));
                scanPanel.setScores(scan.processScan_A3(scan.MultiScan_A(Integer.parseInt(numRayField.getText()) , Double.parseDouble(angleField.getText()))));
            }
        }
    }//GEN-LAST:event_simStartButtonActionPerformed

    private void newPregenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPregenMenuItemActionPerformed
        JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter f = new FileNameExtensionFilter("Serialisierte Java-Objekte", "ser");
        jfc.setFileFilter(f);
        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();
            try{
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                body = (Body) in.readObject();
                in.close();
                getSenderPositions();
                simPanel.drawBody(senderPositions[index]);
         fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Body class not found");
                c.printStackTrace();
                return;
            }
        }
    }//GEN-LAST:event_newPregenMenuItemActionPerformed

    private void bodyEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bodyEditButtonActionPerformed
        MouseListener[] listeners = simPanel.getMouseListeners();
        if(listeners.length == 1){
            simPanel.removeMouseListener(listeners[0]);
            simPanel.drawBody(senderPositions[index]);
        }
        else{
            simPanel.addMouseListener(new DragDropListener(simPanel, this));
            body.refreshDragPoints();
            simPanel.drawBody_Edit();
        }
    }//GEN-LAST:event_bodyEditButtonActionPerformed

    private void new2DMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new2DMenuItemActionPerformed
        this.setVisible(false);
        EditorWindow ew = new EditorWindow(this);
        ew.setVisible(true);
    }//GEN-LAST:event_new2DMenuItemActionPerformed

    private void exportPDFMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportPDFMenuItemActionPerformed
        PrinterJob j = PrinterJob.getPrinterJob();
        //j.setJobName("Out"); 
        j.setPrintable(new Printable(){
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) return Printable.NO_SUCH_PAGE;
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
                g2d.scale(0.32, 0.32);
                jSplitPane2.paint(g2d);
                return Printable.PAGE_EXISTS;
            }
        });
        if (j.printDialog()){
            try{
                j.print();
            }catch(PrinterException e){
                e.printStackTrace();
            }
        }
        
        
    }//GEN-LAST:event_exportPDFMenuItemActionPerformed

    private void editMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMenuItemActionPerformed
        this.setVisible(false);
        EditorWindow ew = new EditorWindow(this);
        ew.body = body;
        ew.setVisible(true);
    }//GEN-LAST:event_editMenuItemActionPerformed
        
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField angleField;
    private javax.swing.JLabel angleLabel;
    private javax.swing.JButton bodyEditButton;
    private javax.swing.JMenuItem closeMenuItem;
    private javax.swing.JMenuItem editMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu exportMenu;
    private javax.swing.JMenuItem exportPDFMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem new2DMenuItem;
    private javax.swing.JMenu newMenu;
    private javax.swing.JMenuItem newPregenMenuItem;
    private javax.swing.JTextField numRayField;
    private javax.swing.JLabel numRayLabel;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem propertiesMenuItem;
    private javax.swing.JTextField rangeField;
    private javax.swing.JLabel rangeLabel;
    private javax.swing.JTextField rayXField;
    private javax.swing.JLabel rayXLabel;
    private javax.swing.JTextField rayYField;
    private javax.swing.JLabel rayYLabel;
    private javax.swing.JTextField refField;
    private javax.swing.JLabel refLabel;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private zfP_Sim.ScanPanel scanPanel;
    private javax.swing.JTextField senderXField;
    private javax.swing.JLabel senderXLabel;
    private javax.swing.JTextField senderYField;
    private javax.swing.JLabel senderYLabel;
    private drawing.DrawPanel simPanel;
    private javax.swing.JButton simStartButton;
    private javax.swing.JToolBar simToolBar;
    private javax.swing.JTextField velocityField;
    private javax.swing.JLabel velocityLabel;
    // End of variables declaration//GEN-END:variables

    public void getSenderPositions(){
        if(! body.outline.isEmpty()){
            java.util.ArrayList<double[]> pointsList = control.SenderPositions.getPathPoints(body);
            double[][] points = new double[pointsList.size()][];
            for(int i = 0; i<points.length; i++){
                points[i] = new double[2];
                points[i][0] = pointsList.get(i)[0];
                points[i][1] = pointsList.get(i)[1];
            }
            senderPositions = points;
        }
        else senderPositions = new double[][] { new double[]{30,30} };
        setIndex(0);
    }
    
    public void setIndex(int newIndex){
        if(newIndex < senderPositions.length){
            index = newIndex;
            senderXField.setText(Double.toString(Math.rint(senderPositions[index][0]*10.0)/10.0));
            senderYField.setText(Double.toString(Math.rint(senderPositions[index][1]*10.0)/10.0));
        }
        else{
            index = 0;
            senderXField.setText(Double.toString(Math.rint(senderPositions[index][0]*10.0)/10.0));
            senderYField.setText(Double.toString(Math.rint(senderPositions[index][1]*10.0)/10.0));
        }
    }
    
    public void nextIndex(){
        if(index < senderPositions.length-1){
            index++;
            senderXField.setText(Double.toString(Math.rint(senderPositions[index][0]*10.0)/10.0));
            senderYField.setText(Double.toString(Math.rint(senderPositions[index][1]*10.0)/10.0));
        }
        else{
            index = 0;
            senderXField.setText(Double.toString(Math.rint(senderPositions[index][0]*10.0)/10.0));
            senderYField.setText(Double.toString(Math.rint(senderPositions[index][1]*10.0)/10.0));
        }
    }
    
    public void prevIndex(){
        if(index > 0){
            index--;
            senderXField.setText(Double.toString(Math.rint(senderPositions[index][0]*10.0)/10.0));
            senderYField.setText(Double.toString(Math.rint(senderPositions[index][1]*10.0)/10.0));
        }
        else{
            index = senderPositions.length-1;
            senderXField.setText(Double.toString(Math.rint(senderPositions[index][0]*10.0)/10.0));
            senderYField.setText(Double.toString(Math.rint(senderPositions[index][1]*10.0)/10.0));
        }
    }

    public int getRotationSpeed() {
        if(rotationSpeed < 15){
            return ++rotationSpeed;
        }
        else{return rotationSpeed;}
    }
    
    @Override
    public void setLit(ShapeBase shape) {
        lit = shape;
    }

    @Override
    public void outlineChanged() {
        getSenderPositions();
    }
}
