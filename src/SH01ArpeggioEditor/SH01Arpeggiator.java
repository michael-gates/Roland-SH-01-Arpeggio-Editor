/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SH01ArpeggioEditor;

import javax.sound.midi.*;
import static SH01ArpeggioEditor.SH01Util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michael
 */
public class SH01Arpeggiator {
    private static final Logger LOGGER = Logger.getLogger(SH01ArpeggioEditor.class.getName());
    private MidiDevice device;


    
    public SH01Arpeggiator(MidiDevice dev){
        setMidiDevice(dev);
    }
    
    
    public void setGrid(int grid) {
        arpSetGrid(grid, device);
    }
    
    public void setDuration(int duration) {
        arpSetDuration(duration, device);
    }
    
    public void setMotif(int motif) {
        arpSetMotif(motif, device);
    }
    
    public void setOctaveRange(int octaveRange) {
        arpSetOctaveRange(octaveRange, device);
    }
    
    public void setAccentRate(int accentRate) {
        arpSetAccentRate(accentRate, device);
    }
    
    public void setVelocity(int velocity) {
        arpSetVelocity(velocity, device);
    }
    
    public void setEndStep(int endStep) {
        arpSetEndStep(endStep, device);
    }
    
    public void setPatternStep(int pat, int step, int value) {
        arpSetPatternStep(pat, step, value, device);
    }
    
    public void setPatternOriginalNote(int pat, int value) {
        arpSetPatternOriginalNote(pat, value, device);
    }
    
    public final void setMidiDevice(MidiDevice dev) {
        device = dev;
    }
    
}
