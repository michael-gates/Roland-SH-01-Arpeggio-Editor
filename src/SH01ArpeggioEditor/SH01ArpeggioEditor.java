/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SH01ArpeggioEditor;

import static SH01ArpeggioEditor.SH01Util.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author michael
 */
public class SH01ArpeggioEditor extends javax.swing.JFrame {
    private static final Logger logger = Logger.getLogger(SH01ArpeggioEditor.class.getName());
    private final MidiDevice.Info[] midiDeviceInfos = MidiSystem.getMidiDeviceInfo();
    private MidiDevice selectedDevice = null;
    
    private SH01Arpeggiator arp;
    

    /**
     * Creates new form SH01ArpeggioEditor
     */
    public SH01ArpeggioEditor() {
        try {
            this.arp = new SH01Arpeggiator(MidiSystem.getMidiDevice(midiDeviceInfos[0]));
        } catch (MidiUnavailableException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        initMidi();
        initLabels();
        initPatternTabs();
    }
    
    private void initMidi() {
        for(MidiDevice.Info info : midiDeviceInfos) {
            outputDeviceComboBox.addItem(info.getName());
        }
        
        try {
            selectedDevice = MidiSystem.getMidiDevice(midiDeviceInfos[0]);
            arp.setMidiDevice(selectedDevice);
        } catch (MidiUnavailableException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    private void initLabels() {
        Hashtable octaveRangeLabelTable = new Hashtable();
        for(int i = -3; i <= 3; i++) {
            octaveRangeLabelTable.put(i+64, new JLabel(Integer.toString(i)));
        }
        sliderOctaveRange.setLabelTable(octaveRangeLabelTable);
        
        Hashtable endStepLabelTable = new Hashtable() {
            {
                put(1, new JLabel("1"));
                put(8, new JLabel("8"));
                put(16, new JLabel("16"));
                put(24, new JLabel("24"));
                put(32, new JLabel("32"));
            }
        };
        sliderEndStep.setLabelTable(endStepLabelTable);
        
        Hashtable velocityLabelTable = new Hashtable() {
            {
                put(0, new JLabel("0"));
                put(127, new JLabel("127"));
            }  
        };
        sliderVelocity.setLabelTable(velocityLabelTable);
        
    }
    
    
    private static Component getComponentByName(Component root, String name) {
        for(Component c : ((Container)root).getComponents()) {
            Component c2 = getComponentByName(c, name);
            if(c2 != null)
                return c2;
            
            if(c.getName() == name)
                return c;
        }
        
        return null;
    }

    private void initPatternTabs() {
        int tabIndex = 1;

        
        for(Component p : arpeggioPatternTabbedPane.getComponents()) {
            
            // Get the top panel for this tab.
            JPanel curTopPanel = (JPanel) ((Container)p).getComponent(0);
            JTextField inputField = (JTextField) getComponentByName(curTopPanel, "originalNoteInputField");
            JButton button = (JButton) getComponentByName(curTopPanel, "setOriginalNoteButton");
            
            final int curTabIndex = tabIndex;
            
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
//                    logger.log(Level.INFO, "testing...");
                    arp.setPatternOriginalNote(curTabIndex, Integer.parseInt(inputField.getText()));
                }
            });
            
            
            
            // Get the step editing panel for this tab, i.e. the panel to which we want to put our sliders
            JPanel curStepPanel = (JPanel) ((Container)p).getComponent(1);
            
            // Create sliders with change listeners and add them to the current step editing panel
            for(int stepIndex = 1; stepIndex <= 32; stepIndex++) {
                JSlider slider = new JSlider();
                slider.setOrientation(JSlider.VERTICAL);
                slider.setMaximum(128);
                slider.setValue(1);
             
                JPanel stepPanel = new JPanel();
                stepPanel.setLayout(new BoxLayout(stepPanel, BoxLayout.Y_AXIS));
                stepPanel.add(new JLabel("S" + Integer.toString(stepIndex), JLabel.CENTER));
                stepPanel.add(slider);
                
                JLabel valueIndicator = new JLabel("1");
                stepPanel.add(valueIndicator); 

                final int curPatIndex = tabIndex;
                final int curStepIndex = stepIndex;
                
                // Do the appropriate changes when slider is moved
                slider.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        valueIndicator.setText(Integer.toString(slider.getValue()));
                        arp.setPatternStep(curPatIndex, curStepIndex, slider.getValue());
                    }
                });
                
                curStepPanel.add(stepPanel);
            }
            
            tabIndex++;
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        arpeggioCommonPanel = new javax.swing.JPanel();
        sliderGrid = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        sliderDuration = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sliderMotif = new javax.swing.JSlider();
        sliderOctaveRange = new javax.swing.JSlider();
        jLabel5 = new javax.swing.JLabel();
        sliderAccentRate = new javax.swing.JSlider();
        jLabel6 = new javax.swing.JLabel();
        sliderVelocity = new javax.swing.JSlider();
        jLabel7 = new javax.swing.JLabel();
        sliderEndStep = new javax.swing.JSlider();
        jLabel8 = new javax.swing.JLabel();
        infoLabel = new javax.swing.JLabel();
        arpeggioPatternPanel = new javax.swing.JPanel();
        arpeggioPatternTabbedPane = new javax.swing.JTabbedPane();
        patternPanel1 = new javax.swing.JPanel();
        topPanel1 = new javax.swing.JPanel();
        originalNoteInputField1 = new javax.swing.JTextField();
        originalNoteInputButton1 = new javax.swing.JButton();
        originalNoteLabel1 = new javax.swing.JLabel();
        stepPanel1 = new javax.swing.JPanel();
        patternPanel2 = new javax.swing.JPanel();
        topPanel2 = new javax.swing.JPanel();
        originalNoteInputField2 = new javax.swing.JTextField();
        originalNoteInputButton2 = new javax.swing.JButton();
        originalNoteLabel2 = new javax.swing.JLabel();
        stepPanel2 = new javax.swing.JPanel();
        patternPanel3 = new javax.swing.JPanel();
        topPanel3 = new javax.swing.JPanel();
        originalNoteInputField3 = new javax.swing.JTextField();
        originalNoteInputButton3 = new javax.swing.JButton();
        originalNoteLabel3 = new javax.swing.JLabel();
        stepPanel3 = new javax.swing.JPanel();
        patternPanel4 = new javax.swing.JPanel();
        topPanel4 = new javax.swing.JPanel();
        originalNoteInputField4 = new javax.swing.JTextField();
        originalNoteInputButton4 = new javax.swing.JButton();
        originalNoteLabel4 = new javax.swing.JLabel();
        stepPanel4 = new javax.swing.JPanel();
        patternPanel5 = new javax.swing.JPanel();
        topPanel5 = new javax.swing.JPanel();
        originalNoteInputField5 = new javax.swing.JTextField();
        originalNoteInputButton5 = new javax.swing.JButton();
        originalNoteLabel5 = new javax.swing.JLabel();
        stepPanel5 = new javax.swing.JPanel();
        patternPanel6 = new javax.swing.JPanel();
        topPanel6 = new javax.swing.JPanel();
        originalNoteInputField6 = new javax.swing.JTextField();
        originalNoteInputButton6 = new javax.swing.JButton();
        originalNoteLabel6 = new javax.swing.JLabel();
        stepPanel6 = new javax.swing.JPanel();
        patternPanel7 = new javax.swing.JPanel();
        topPanel7 = new javax.swing.JPanel();
        originalNoteInputField7 = new javax.swing.JTextField();
        originalNoteInputButton7 = new javax.swing.JButton();
        originalNoteLabel7 = new javax.swing.JLabel();
        stepPanel7 = new javax.swing.JPanel();
        patternPanel8 = new javax.swing.JPanel();
        topPanel8 = new javax.swing.JPanel();
        originalNoteInputField8 = new javax.swing.JTextField();
        originalNoteInputButton8 = new javax.swing.JButton();
        originalNoteLabel8 = new javax.swing.JLabel();
        stepPanel8 = new javax.swing.JPanel();
        patternPanel9 = new javax.swing.JPanel();
        topPanel9 = new javax.swing.JPanel();
        originalNoteInputField9 = new javax.swing.JTextField();
        originalNoteInputButton9 = new javax.swing.JButton();
        originalNoteLabel9 = new javax.swing.JLabel();
        stepPanel9 = new javax.swing.JPanel();
        patternPanel10 = new javax.swing.JPanel();
        topPanel10 = new javax.swing.JPanel();
        originalNoteInputField10 = new javax.swing.JTextField();
        originalNoteInputButton10 = new javax.swing.JButton();
        originalNoteLabel10 = new javax.swing.JLabel();
        stepPanel10 = new javax.swing.JPanel();
        patternPanel11 = new javax.swing.JPanel();
        topPanel11 = new javax.swing.JPanel();
        originalNoteInputField11 = new javax.swing.JTextField();
        originalNoteInputButton11 = new javax.swing.JButton();
        originalNoteLabel11 = new javax.swing.JLabel();
        stepPanel11 = new javax.swing.JPanel();
        patternPanel12 = new javax.swing.JPanel();
        topPanel12 = new javax.swing.JPanel();
        originalNoteInputField12 = new javax.swing.JTextField();
        originalNoteInputButton12 = new javax.swing.JButton();
        originalNoteLabel12 = new javax.swing.JLabel();
        stepPanel12 = new javax.swing.JPanel();
        patternPanel13 = new javax.swing.JPanel();
        topPanel13 = new javax.swing.JPanel();
        originalNoteInputField13 = new javax.swing.JTextField();
        originalNoteInputButton13 = new javax.swing.JButton();
        originalNoteLabel13 = new javax.swing.JLabel();
        stepPanel13 = new javax.swing.JPanel();
        patternPanel14 = new javax.swing.JPanel();
        topPanel14 = new javax.swing.JPanel();
        originalNoteInputField14 = new javax.swing.JTextField();
        originalNoteInputButton14 = new javax.swing.JButton();
        originalNoteLabel14 = new javax.swing.JLabel();
        stepPanel14 = new javax.swing.JPanel();
        patternPanel15 = new javax.swing.JPanel();
        topPanel15 = new javax.swing.JPanel();
        originalNoteInputField15 = new javax.swing.JTextField();
        originalNoteInputButton15 = new javax.swing.JButton();
        originalNoteLabel15 = new javax.swing.JLabel();
        stepPanel15 = new javax.swing.JPanel();
        patternPanel16 = new javax.swing.JPanel();
        topPanel16 = new javax.swing.JPanel();
        originalNoteInputField16 = new javax.swing.JTextField();
        originalNoteInputButton16 = new javax.swing.JButton();
        originalNoteLabel16 = new javax.swing.JLabel();
        stepPanel16 = new javax.swing.JPanel();
        devicePanel = new javax.swing.JPanel();
        outputDeviceComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SH01 Arpeggio Editor");

        sliderGrid.setMajorTickSpacing(1);
        sliderGrid.setMaximum(8);
        sliderGrid.setPaintLabels(true);
        sliderGrid.setPaintTicks(true);
        sliderGrid.setSnapToTicks(true);
        sliderGrid.setToolTipText("");
        sliderGrid.setValue(0);
        sliderGrid.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sliderGrid.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderGridStateChanged(evt);
            }
        });

        jLabel2.setText("Grid");

        sliderDuration.setMajorTickSpacing(1);
        sliderDuration.setMaximum(9);
        sliderDuration.setPaintLabels(true);
        sliderDuration.setPaintTicks(true);
        sliderDuration.setSnapToTicks(true);
        sliderDuration.setToolTipText("");
        sliderDuration.setValue(0);
        sliderDuration.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sliderDuration.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderDurationStateChanged(evt);
            }
        });

        jLabel3.setText("Duration");

        jLabel4.setText("Motif");

        sliderMotif.setMajorTickSpacing(1);
        sliderMotif.setMaximum(11);
        sliderMotif.setPaintLabels(true);
        sliderMotif.setPaintTicks(true);
        sliderMotif.setSnapToTicks(true);
        sliderMotif.setToolTipText("");
        sliderMotif.setValue(0);
        sliderMotif.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sliderMotif.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderMotifStateChanged(evt);
            }
        });

        sliderOctaveRange.setMajorTickSpacing(1);
        sliderOctaveRange.setMaximum(67);
        sliderOctaveRange.setMinimum(61);
        sliderOctaveRange.setPaintLabels(true);
        sliderOctaveRange.setPaintTicks(true);
        sliderOctaveRange.setSnapToTicks(true);
        sliderOctaveRange.setToolTipText("");
        sliderOctaveRange.setValue(64);
        sliderOctaveRange.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sliderOctaveRange.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderOctaveRangeStateChanged(evt);
            }
        });

        jLabel5.setText("Octave Range");

        sliderAccentRate.setMajorTickSpacing(10);
        sliderAccentRate.setPaintLabels(true);
        sliderAccentRate.setPaintTicks(true);
        sliderAccentRate.setToolTipText("");
        sliderAccentRate.setValue(0);
        sliderAccentRate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sliderAccentRate.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderAccentRateStateChanged(evt);
            }
        });

        jLabel6.setText("Accent Rate");

        sliderVelocity.setMajorTickSpacing(1);
        sliderVelocity.setMaximum(127);
        sliderVelocity.setPaintLabels(true);
        sliderVelocity.setPaintTicks(true);
        sliderVelocity.setSnapToTicks(true);
        sliderVelocity.setToolTipText("");
        sliderVelocity.setValue(0);
        sliderVelocity.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sliderVelocity.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderVelocityStateChanged(evt);
            }
        });

        jLabel7.setText("Velocity");

        sliderEndStep.setMajorTickSpacing(1);
        sliderEndStep.setMaximum(32);
        sliderEndStep.setMinimum(1);
        sliderEndStep.setPaintLabels(true);
        sliderEndStep.setPaintTicks(true);
        sliderEndStep.setSnapToTicks(true);
        sliderEndStep.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sliderEndStep.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderEndStepStateChanged(evt);
            }
        });

        jLabel8.setText("End Step");

        javax.swing.GroupLayout arpeggioCommonPanelLayout = new javax.swing.GroupLayout(arpeggioCommonPanel);
        arpeggioCommonPanel.setLayout(arpeggioCommonPanelLayout);
        arpeggioCommonPanelLayout.setHorizontalGroup(
            arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arpeggioCommonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(arpeggioCommonPanelLayout.createSequentialGroup()
                        .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sliderVelocity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderAccentRate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderOctaveRange, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderEndStep, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(arpeggioCommonPanelLayout.createSequentialGroup()
                        .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(sliderGrid, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addComponent(sliderDuration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sliderMotif, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 407, Short.MAX_VALUE)
                        .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(arpeggioCommonPanelLayout.createSequentialGroup()
                        .addComponent(infoLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        arpeggioCommonPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {sliderAccentRate, sliderDuration, sliderEndStep, sliderGrid, sliderMotif, sliderOctaveRange, sliderVelocity});

        arpeggioCommonPanelLayout.setVerticalGroup(
            arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arpeggioCommonPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sliderGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sliderMotif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(arpeggioCommonPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, arpeggioCommonPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sliderOctaveRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(arpeggioCommonPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sliderAccentRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, arpeggioCommonPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sliderVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(arpeggioCommonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sliderEndStep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(infoLabel)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Patch Arpeggio Common", arpeggioCommonPanel);

        topPanel1.setName("topPanel"); // NOI18N

        originalNoteInputField1.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton1.setText("Set");
        originalNoteInputButton1.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel1.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel1Layout = new javax.swing.GroupLayout(topPanel1);
        topPanel1.setLayout(topPanel1Layout);
        topPanel1Layout.setHorizontalGroup(
            topPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel1Layout.setVerticalGroup(
            topPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton1)
                    .addComponent(originalNoteLabel1))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        topPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {originalNoteInputButton1, originalNoteInputField1, originalNoteLabel1});

        stepPanel1.setName("stepPanel"); // NOI18N
        stepPanel1.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel1Layout = new javax.swing.GroupLayout(patternPanel1);
        patternPanel1.setLayout(patternPanel1Layout);
        patternPanel1Layout.setHorizontalGroup(
            patternPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel1Layout.setVerticalGroup(
            patternPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("1", patternPanel1);

        topPanel2.setName("topPanel"); // NOI18N

        originalNoteInputField2.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton2.setText("Set");
        originalNoteInputButton2.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel2.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel2Layout = new javax.swing.GroupLayout(topPanel2);
        topPanel2.setLayout(topPanel2Layout);
        topPanel2Layout.setHorizontalGroup(
            topPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel2Layout.setVerticalGroup(
            topPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton2)
                    .addComponent(originalNoteLabel2))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel2.setName("stepPanel"); // NOI18N
        stepPanel2.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel2Layout = new javax.swing.GroupLayout(patternPanel2);
        patternPanel2.setLayout(patternPanel2Layout);
        patternPanel2Layout.setHorizontalGroup(
            patternPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel2Layout.setVerticalGroup(
            patternPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("2", patternPanel2);

        topPanel3.setName("topPanel"); // NOI18N

        originalNoteInputField3.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton3.setText("Set");
        originalNoteInputButton3.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel3.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel3Layout = new javax.swing.GroupLayout(topPanel3);
        topPanel3.setLayout(topPanel3Layout);
        topPanel3Layout.setHorizontalGroup(
            topPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel3Layout.setVerticalGroup(
            topPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton3)
                    .addComponent(originalNoteLabel3))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel3.setName("stepPanel"); // NOI18N
        stepPanel3.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel3Layout = new javax.swing.GroupLayout(patternPanel3);
        patternPanel3.setLayout(patternPanel3Layout);
        patternPanel3Layout.setHorizontalGroup(
            patternPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel3Layout.setVerticalGroup(
            patternPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("3", patternPanel3);

        topPanel4.setName("topPanel"); // NOI18N

        originalNoteInputField4.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton4.setText("Set");
        originalNoteInputButton4.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel4.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel4Layout = new javax.swing.GroupLayout(topPanel4);
        topPanel4.setLayout(topPanel4Layout);
        topPanel4Layout.setHorizontalGroup(
            topPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel4Layout.setVerticalGroup(
            topPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton4)
                    .addComponent(originalNoteLabel4))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel4.setName("stepPanel"); // NOI18N
        stepPanel4.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel4Layout = new javax.swing.GroupLayout(patternPanel4);
        patternPanel4.setLayout(patternPanel4Layout);
        patternPanel4Layout.setHorizontalGroup(
            patternPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel4Layout.setVerticalGroup(
            patternPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("4", patternPanel4);

        topPanel5.setName("topPanel"); // NOI18N

        originalNoteInputField5.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton5.setText("Set");
        originalNoteInputButton5.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel5.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel5Layout = new javax.swing.GroupLayout(topPanel5);
        topPanel5.setLayout(topPanel5Layout);
        topPanel5Layout.setHorizontalGroup(
            topPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel5Layout.setVerticalGroup(
            topPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton5)
                    .addComponent(originalNoteLabel5))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel5.setName("stepPanel"); // NOI18N
        stepPanel5.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel5Layout = new javax.swing.GroupLayout(patternPanel5);
        patternPanel5.setLayout(patternPanel5Layout);
        patternPanel5Layout.setHorizontalGroup(
            patternPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel5Layout.setVerticalGroup(
            patternPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("5", patternPanel5);

        topPanel6.setName("topPanel"); // NOI18N

        originalNoteInputField6.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton6.setText("Set");
        originalNoteInputButton6.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel6.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel6Layout = new javax.swing.GroupLayout(topPanel6);
        topPanel6.setLayout(topPanel6Layout);
        topPanel6Layout.setHorizontalGroup(
            topPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel6Layout.setVerticalGroup(
            topPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton6)
                    .addComponent(originalNoteLabel6))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel6.setName("stepPanel"); // NOI18N
        stepPanel6.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel6Layout = new javax.swing.GroupLayout(patternPanel6);
        patternPanel6.setLayout(patternPanel6Layout);
        patternPanel6Layout.setHorizontalGroup(
            patternPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel6Layout.setVerticalGroup(
            patternPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("6", patternPanel6);

        topPanel7.setName("topPanel"); // NOI18N

        originalNoteInputField7.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton7.setText("Set");
        originalNoteInputButton7.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel7.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel7Layout = new javax.swing.GroupLayout(topPanel7);
        topPanel7.setLayout(topPanel7Layout);
        topPanel7Layout.setHorizontalGroup(
            topPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel7Layout.setVerticalGroup(
            topPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton7)
                    .addComponent(originalNoteLabel7))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel7.setName("stepPanel"); // NOI18N
        stepPanel7.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel7Layout = new javax.swing.GroupLayout(patternPanel7);
        patternPanel7.setLayout(patternPanel7Layout);
        patternPanel7Layout.setHorizontalGroup(
            patternPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel7Layout.setVerticalGroup(
            patternPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("7", patternPanel7);

        topPanel8.setName("topPanel"); // NOI18N

        originalNoteInputField8.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton8.setText("Set");
        originalNoteInputButton8.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel8.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel8Layout = new javax.swing.GroupLayout(topPanel8);
        topPanel8.setLayout(topPanel8Layout);
        topPanel8Layout.setHorizontalGroup(
            topPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel8Layout.setVerticalGroup(
            topPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton8)
                    .addComponent(originalNoteLabel8))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel8.setName("stepPanel"); // NOI18N
        stepPanel8.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel8Layout = new javax.swing.GroupLayout(patternPanel8);
        patternPanel8.setLayout(patternPanel8Layout);
        patternPanel8Layout.setHorizontalGroup(
            patternPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel8Layout.setVerticalGroup(
            patternPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("8", patternPanel8);

        topPanel9.setName("topPanel"); // NOI18N

        originalNoteInputField9.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton9.setText("Set");
        originalNoteInputButton9.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel9.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel9Layout = new javax.swing.GroupLayout(topPanel9);
        topPanel9.setLayout(topPanel9Layout);
        topPanel9Layout.setHorizontalGroup(
            topPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel9Layout.setVerticalGroup(
            topPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton9)
                    .addComponent(originalNoteLabel9))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel9.setName("stepPanel"); // NOI18N
        stepPanel9.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel9Layout = new javax.swing.GroupLayout(patternPanel9);
        patternPanel9.setLayout(patternPanel9Layout);
        patternPanel9Layout.setHorizontalGroup(
            patternPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel9Layout.setVerticalGroup(
            patternPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("9", patternPanel9);

        topPanel10.setName("topPanel"); // NOI18N

        originalNoteInputField10.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton10.setText("Set");
        originalNoteInputButton10.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel10.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel10Layout = new javax.swing.GroupLayout(topPanel10);
        topPanel10.setLayout(topPanel10Layout);
        topPanel10Layout.setHorizontalGroup(
            topPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel10Layout.setVerticalGroup(
            topPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton10)
                    .addComponent(originalNoteLabel10))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel10.setName("stepPanel"); // NOI18N
        stepPanel10.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel10Layout = new javax.swing.GroupLayout(patternPanel10);
        patternPanel10.setLayout(patternPanel10Layout);
        patternPanel10Layout.setHorizontalGroup(
            patternPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel10Layout.setVerticalGroup(
            patternPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("10", patternPanel10);

        topPanel11.setName("topPanel"); // NOI18N

        originalNoteInputField11.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton11.setText("Set");
        originalNoteInputButton11.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel11.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel11Layout = new javax.swing.GroupLayout(topPanel11);
        topPanel11.setLayout(topPanel11Layout);
        topPanel11Layout.setHorizontalGroup(
            topPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel11Layout.setVerticalGroup(
            topPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton11)
                    .addComponent(originalNoteLabel11))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel11.setName("stepPanel"); // NOI18N
        stepPanel11.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel11Layout = new javax.swing.GroupLayout(patternPanel11);
        patternPanel11.setLayout(patternPanel11Layout);
        patternPanel11Layout.setHorizontalGroup(
            patternPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel11Layout.setVerticalGroup(
            patternPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("11", patternPanel11);

        topPanel12.setName("topPanel"); // NOI18N

        originalNoteInputField12.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton12.setText("Set");
        originalNoteInputButton12.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel12.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel12Layout = new javax.swing.GroupLayout(topPanel12);
        topPanel12.setLayout(topPanel12Layout);
        topPanel12Layout.setHorizontalGroup(
            topPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField12, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel12Layout.setVerticalGroup(
            topPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton12)
                    .addComponent(originalNoteLabel12))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel12.setName("stepPanel"); // NOI18N
        stepPanel12.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel12Layout = new javax.swing.GroupLayout(patternPanel12);
        patternPanel12.setLayout(patternPanel12Layout);
        patternPanel12Layout.setHorizontalGroup(
            patternPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel12Layout.setVerticalGroup(
            patternPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("12", patternPanel12);

        topPanel13.setName("topPanel"); // NOI18N

        originalNoteInputField13.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton13.setText("Set");
        originalNoteInputButton13.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel13.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel13Layout = new javax.swing.GroupLayout(topPanel13);
        topPanel13.setLayout(topPanel13Layout);
        topPanel13Layout.setHorizontalGroup(
            topPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel13Layout.setVerticalGroup(
            topPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton13)
                    .addComponent(originalNoteLabel13))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel13.setName("stepPanel"); // NOI18N
        stepPanel13.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel13Layout = new javax.swing.GroupLayout(patternPanel13);
        patternPanel13.setLayout(patternPanel13Layout);
        patternPanel13Layout.setHorizontalGroup(
            patternPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel13Layout.setVerticalGroup(
            patternPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("13", patternPanel13);

        topPanel14.setName("topPanel"); // NOI18N

        originalNoteInputField14.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton14.setText("Set");
        originalNoteInputButton14.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel14.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel14Layout = new javax.swing.GroupLayout(topPanel14);
        topPanel14.setLayout(topPanel14Layout);
        topPanel14Layout.setHorizontalGroup(
            topPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel14Layout.setVerticalGroup(
            topPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton14)
                    .addComponent(originalNoteLabel14))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel14.setName("stepPanel"); // NOI18N
        stepPanel14.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel14Layout = new javax.swing.GroupLayout(patternPanel14);
        patternPanel14.setLayout(patternPanel14Layout);
        patternPanel14Layout.setHorizontalGroup(
            patternPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel14Layout.setVerticalGroup(
            patternPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("14", patternPanel14);

        topPanel15.setName("topPanel"); // NOI18N

        originalNoteInputField15.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton15.setText("Set");
        originalNoteInputButton15.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel15.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel15Layout = new javax.swing.GroupLayout(topPanel15);
        topPanel15.setLayout(topPanel15Layout);
        topPanel15Layout.setHorizontalGroup(
            topPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField15, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel15Layout.setVerticalGroup(
            topPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton15)
                    .addComponent(originalNoteLabel15))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel15.setName("stepPanel"); // NOI18N
        stepPanel15.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel15Layout = new javax.swing.GroupLayout(patternPanel15);
        patternPanel15.setLayout(patternPanel15Layout);
        patternPanel15Layout.setHorizontalGroup(
            patternPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel15Layout.setVerticalGroup(
            patternPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("15", patternPanel15);

        topPanel16.setName("topPanel"); // NOI18N

        originalNoteInputField16.setName("originalNoteInputField"); // NOI18N

        originalNoteInputButton16.setText("Set");
        originalNoteInputButton16.setName("setOriginalNoteButton"); // NOI18N

        originalNoteLabel16.setText("Original note (0 - 128)");

        javax.swing.GroupLayout topPanel16Layout = new javax.swing.GroupLayout(topPanel16);
        topPanel16.setLayout(topPanel16Layout);
        topPanel16Layout.setHorizontalGroup(
            topPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(originalNoteLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputField16, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(originalNoteInputButton16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanel16Layout.setVerticalGroup(
            topPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originalNoteInputField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originalNoteInputButton16)
                    .addComponent(originalNoteLabel16))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stepPanel16.setName("stepPanel"); // NOI18N
        stepPanel16.setLayout(new java.awt.GridLayout(2, 16, 0, 32));

        javax.swing.GroupLayout patternPanel16Layout = new javax.swing.GroupLayout(patternPanel16);
        patternPanel16.setLayout(patternPanel16Layout);
        patternPanel16Layout.setHorizontalGroup(
            patternPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patternPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patternPanel16Layout.setVerticalGroup(
            patternPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patternPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        arpeggioPatternTabbedPane.addTab("16", patternPanel16);

        javax.swing.GroupLayout arpeggioPatternPanelLayout = new javax.swing.GroupLayout(arpeggioPatternPanel);
        arpeggioPatternPanel.setLayout(arpeggioPatternPanelLayout);
        arpeggioPatternPanelLayout.setHorizontalGroup(
            arpeggioPatternPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arpeggioPatternPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(arpeggioPatternTabbedPane)
                .addContainerGap())
        );
        arpeggioPatternPanelLayout.setVerticalGroup(
            arpeggioPatternPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arpeggioPatternPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(arpeggioPatternTabbedPane)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Patch Arpeggio Pattern", arpeggioPatternPanel);

        outputDeviceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputDeviceComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Output device");

        javax.swing.GroupLayout devicePanelLayout = new javax.swing.GroupLayout(devicePanel);
        devicePanel.setLayout(devicePanelLayout);
        devicePanelLayout.setHorizontalGroup(
            devicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(devicePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outputDeviceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(498, Short.MAX_VALUE))
        );
        devicePanelLayout.setVerticalGroup(
            devicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(devicePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(devicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputDeviceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(524, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Device", devicePanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(342, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sliderGridStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderGridStateChanged
        arp.setGrid(sliderGrid.getValue());
    }//GEN-LAST:event_sliderGridStateChanged

    private void outputDeviceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputDeviceComboBoxActionPerformed
        try {
            selectedDevice = MidiSystem.getMidiDevice(midiDeviceInfos[outputDeviceComboBox.getSelectedIndex()]);
            arp.setMidiDevice(selectedDevice);
        } catch (MidiUnavailableException ex) {
            logger.log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_outputDeviceComboBoxActionPerformed

    private void sliderDurationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderDurationStateChanged
        arp.setDuration(sliderDuration.getValue());
    }//GEN-LAST:event_sliderDurationStateChanged

    private void sliderMotifStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderMotifStateChanged
        arp.setMotif(sliderMotif.getValue());
    }//GEN-LAST:event_sliderMotifStateChanged

    private void sliderOctaveRangeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderOctaveRangeStateChanged
        arp.setOctaveRange(sliderOctaveRange.getValue());
    }//GEN-LAST:event_sliderOctaveRangeStateChanged

    private void sliderAccentRateStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderAccentRateStateChanged
        arp.setAccentRate(sliderAccentRate.getValue());
    }//GEN-LAST:event_sliderAccentRateStateChanged

    private void sliderVelocityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderVelocityStateChanged
        arp.setVelocity(sliderVelocity.getValue());
        infoLabel.setText("Velocity: " + Integer.toString(sliderVelocity.getValue()));
    }//GEN-LAST:event_sliderVelocityStateChanged

    private void sliderEndStepStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderEndStepStateChanged
        arp.setEndStep(sliderEndStep.getValue());
        infoLabel.setText("End Step: " + Integer.toString(sliderEndStep.getValue()));
    }//GEN-LAST:event_sliderEndStepStateChanged

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
            java.util.logging.Logger.getLogger(SH01ArpeggioEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SH01ArpeggioEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SH01ArpeggioEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SH01ArpeggioEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SH01ArpeggioEditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel arpeggioCommonPanel;
    private javax.swing.JPanel arpeggioPatternPanel;
    private javax.swing.JTabbedPane arpeggioPatternTabbedPane;
    private javax.swing.JPanel devicePanel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton originalNoteInputButton1;
    private javax.swing.JButton originalNoteInputButton10;
    private javax.swing.JButton originalNoteInputButton11;
    private javax.swing.JButton originalNoteInputButton12;
    private javax.swing.JButton originalNoteInputButton13;
    private javax.swing.JButton originalNoteInputButton14;
    private javax.swing.JButton originalNoteInputButton15;
    private javax.swing.JButton originalNoteInputButton16;
    private javax.swing.JButton originalNoteInputButton2;
    private javax.swing.JButton originalNoteInputButton3;
    private javax.swing.JButton originalNoteInputButton4;
    private javax.swing.JButton originalNoteInputButton5;
    private javax.swing.JButton originalNoteInputButton6;
    private javax.swing.JButton originalNoteInputButton7;
    private javax.swing.JButton originalNoteInputButton8;
    private javax.swing.JButton originalNoteInputButton9;
    private javax.swing.JTextField originalNoteInputField1;
    private javax.swing.JTextField originalNoteInputField10;
    private javax.swing.JTextField originalNoteInputField11;
    private javax.swing.JTextField originalNoteInputField12;
    private javax.swing.JTextField originalNoteInputField13;
    private javax.swing.JTextField originalNoteInputField14;
    private javax.swing.JTextField originalNoteInputField15;
    private javax.swing.JTextField originalNoteInputField16;
    private javax.swing.JTextField originalNoteInputField2;
    private javax.swing.JTextField originalNoteInputField3;
    private javax.swing.JTextField originalNoteInputField4;
    private javax.swing.JTextField originalNoteInputField5;
    private javax.swing.JTextField originalNoteInputField6;
    private javax.swing.JTextField originalNoteInputField7;
    private javax.swing.JTextField originalNoteInputField8;
    private javax.swing.JTextField originalNoteInputField9;
    private javax.swing.JLabel originalNoteLabel1;
    private javax.swing.JLabel originalNoteLabel10;
    private javax.swing.JLabel originalNoteLabel11;
    private javax.swing.JLabel originalNoteLabel12;
    private javax.swing.JLabel originalNoteLabel13;
    private javax.swing.JLabel originalNoteLabel14;
    private javax.swing.JLabel originalNoteLabel15;
    private javax.swing.JLabel originalNoteLabel16;
    private javax.swing.JLabel originalNoteLabel2;
    private javax.swing.JLabel originalNoteLabel3;
    private javax.swing.JLabel originalNoteLabel4;
    private javax.swing.JLabel originalNoteLabel5;
    private javax.swing.JLabel originalNoteLabel6;
    private javax.swing.JLabel originalNoteLabel7;
    private javax.swing.JLabel originalNoteLabel8;
    private javax.swing.JLabel originalNoteLabel9;
    private javax.swing.JComboBox<String> outputDeviceComboBox;
    private javax.swing.JPanel patternPanel1;
    private javax.swing.JPanel patternPanel10;
    private javax.swing.JPanel patternPanel11;
    private javax.swing.JPanel patternPanel12;
    private javax.swing.JPanel patternPanel13;
    private javax.swing.JPanel patternPanel14;
    private javax.swing.JPanel patternPanel15;
    private javax.swing.JPanel patternPanel16;
    private javax.swing.JPanel patternPanel2;
    private javax.swing.JPanel patternPanel3;
    private javax.swing.JPanel patternPanel4;
    private javax.swing.JPanel patternPanel5;
    private javax.swing.JPanel patternPanel6;
    private javax.swing.JPanel patternPanel7;
    private javax.swing.JPanel patternPanel8;
    private javax.swing.JPanel patternPanel9;
    private javax.swing.JSlider sliderAccentRate;
    private javax.swing.JSlider sliderDuration;
    private javax.swing.JSlider sliderEndStep;
    private javax.swing.JSlider sliderGrid;
    private javax.swing.JSlider sliderMotif;
    private javax.swing.JSlider sliderOctaveRange;
    private javax.swing.JSlider sliderVelocity;
    private javax.swing.JPanel stepPanel1;
    private javax.swing.JPanel stepPanel10;
    private javax.swing.JPanel stepPanel11;
    private javax.swing.JPanel stepPanel12;
    private javax.swing.JPanel stepPanel13;
    private javax.swing.JPanel stepPanel14;
    private javax.swing.JPanel stepPanel15;
    private javax.swing.JPanel stepPanel16;
    private javax.swing.JPanel stepPanel2;
    private javax.swing.JPanel stepPanel3;
    private javax.swing.JPanel stepPanel4;
    private javax.swing.JPanel stepPanel5;
    private javax.swing.JPanel stepPanel6;
    private javax.swing.JPanel stepPanel7;
    private javax.swing.JPanel stepPanel8;
    private javax.swing.JPanel stepPanel9;
    private javax.swing.JPanel topPanel1;
    private javax.swing.JPanel topPanel10;
    private javax.swing.JPanel topPanel11;
    private javax.swing.JPanel topPanel12;
    private javax.swing.JPanel topPanel13;
    private javax.swing.JPanel topPanel14;
    private javax.swing.JPanel topPanel15;
    private javax.swing.JPanel topPanel16;
    private javax.swing.JPanel topPanel2;
    private javax.swing.JPanel topPanel3;
    private javax.swing.JPanel topPanel4;
    private javax.swing.JPanel topPanel5;
    private javax.swing.JPanel topPanel6;
    private javax.swing.JPanel topPanel7;
    private javax.swing.JPanel topPanel8;
    private javax.swing.JPanel topPanel9;
    // End of variables declaration//GEN-END:variables
}
