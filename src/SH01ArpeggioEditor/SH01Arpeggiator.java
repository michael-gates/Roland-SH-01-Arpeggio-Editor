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
    private MidiDevice device = null;

    public SH01Arpeggiator() {
    }

    
    public SH01Arpeggiator(MidiDevice dev){
        setMidiDevice(dev);
    }
    
    
    public void setGrid(int grid) {
        if(device != null)
            arpSetGrid(grid, device);
    }
    
    public void setDuration(int duration) {
        if(device != null)
            arpSetDuration(duration, device);
    }
    
    public void setMotif(int motif) {
        if(device != null)
            arpSetMotif(motif, device);
    }
    
    public void setOctaveRange(int octaveRange) {
        if(device != null)
            arpSetOctaveRange(octaveRange, device);
    }
    
    public void setAccentRate(int accentRate) {
        if(device != null)
            arpSetAccentRate(accentRate, device);
    }
    
    public void setVelocity(int velocity) {
        if(device != null)
            arpSetVelocity(velocity, device);
    }
    
    public void setEndStep(int endStep) {
        if(device != null)
            arpSetEndStep(endStep, device);
    }
    
    public void setPatternStep(int pat, int step, int value) {
        if(device != null)
            arpSetPatternStep(pat, step, value, device);
    }
    
    public void setPatternOriginalNote(int pat, int value) {
        if(device != null)
            arpSetPatternOriginalNote(pat, value, device);
    }
    
    public final void setMidiDevice(MidiDevice dev) {
        device = dev;
    }
    
}
