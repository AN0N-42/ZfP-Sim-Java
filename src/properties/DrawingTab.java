package properties;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import shapesBase.ShapeBase;
import structures.StructDrawingInfo;
import zfP_Sim.EditorWindow;

public class DrawingTab extends PropertiesTab{
    
    public ShapeBase shape;

    public javax.swing.JLabel lineColorLabel; public javax.swing.JButton lineColorButton;
    public javax.swing.JLabel lineColorLitLabel; public javax.swing.JButton lineColorLitButton;
    public javax.swing.JLabel fillLabel; public javax.swing.JCheckBox fillCheckBox;
    public javax.swing.JLabel fillColorLabel; public javax.swing.JButton fillColorButton;
    
    public DrawingTab(ShapeBase shape){
        this.shape = shape;
        
        setLayout(new GridLayout(4, 2));
        
        /*NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Double.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        new JFormattedTextField(formatter);*/
        
        lineColorLabel = new JLabel("Linienfarbe");
        lineColorButton = new JButton("");
        lineColorButton.setBackground(shape.drawingInfo.lineColor);
        lineColorButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            lineColorButtonActionPerformed(evt);
        });
        
        lineColorLitLabel = new JLabel("Linienfarbe [markiert]");
        lineColorLitButton = new JButton("");
        lineColorLitButton.setBackground(shape.drawingInfo.lineColorLit);
        lineColorLitButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            lineColorLitButtonActionPerformed(evt);
        });
        
        fillLabel = new JLabel("Füllen");
        fillCheckBox = new JCheckBox("");
        fillCheckBox.setSelected(shape.drawingInfo.fill);
        
        fillColorLabel = new JLabel("Füllfarbe");
        fillColorButton = new JButton("");
        fillColorButton.setBackground(shape.drawingInfo.fillColor);
        fillColorButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            fillColorButtonActionPerformed(evt);
        });
        
        add(lineColorLabel);
        add(lineColorButton);
        add(lineColorLitLabel);
        add(lineColorLitButton);
        add(fillLabel);
        add(fillCheckBox);
        add(fillColorLabel);
        add(fillColorButton);
    }
    
    private void lineColorButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        lineColorButton.setBackground(JColorChooser.showDialog(
                     this,
                     "Choose Background Color",
                     lineColorButton.getBackground()));
    }
    
    private void lineColorLitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        lineColorLitButton.setBackground(JColorChooser.showDialog(
                     this,
                     "Choose Background Color",
                     lineColorLitButton.getBackground()));
    }
    
    private void fillColorButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        fillColorButton.setBackground(JColorChooser.showDialog(
                     this,
                     "Choose Background Color",
                     fillColorButton.getBackground()));
    }
    
    @Override
    public void commit(){
        shape.drawingInfo.lineColor = lineColorButton.getBackground();
        shape.drawingInfo.lineColorLit = lineColorLitButton.getBackground();
        shape.drawingInfo.fill = fillCheckBox.isSelected();
        shape.drawingInfo.fillColor = fillColorButton.getBackground();
    }
}
