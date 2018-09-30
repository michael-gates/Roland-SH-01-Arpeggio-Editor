/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SH01ArpeggioEditor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.*;


/**
 *
 * @author michael
 */
public class SH01Util {
    private static final Logger logger = Logger.getLogger(SH01Util.class.getName());
    
    public static void sendSysexMultiple(List<byte[]> sysexList, MidiDevice device) {
        for(byte[] sysex : sysexList) {
            sendSysex(sysex, device);
        }
    }
    
    public static void sendSysex(byte[] sysex, MidiDevice device) {
        try {
            device.open();
            device.getReceiver().send(new SysexMessage(sysex, sysex.length), -1);
            device.close();
            
        } catch (MidiUnavailableException | InvalidMidiDataException ex) {
            logger.log(Level.SEVERE, ex.toString());
        }
        
        logger.log(Level.INFO, "Sent: " + bytesToHexString(sysex));
    }
    
    public static void sendSysexDataSet(byte[] addr, byte[] data, MidiDevice device) {
        sendSysex(buildSysexDataSet(addr, data), device);
    }
    
    
    public static byte[] buildSysexDataSet(byte[] addr, byte[] data) {
        if(addr.length != 4)
                return null;

        byte[] head = {(byte)0xF0, 0x41, 0x10, 0x00, 0x00, 0x41, 0x12};
        byte[] msg = new byte[13 + data.length];

        // calculate checksum
        int sum = 0;
        for(byte b : addr)
                sum += b;
        for(byte b : data)
                sum += b;

        int checksum = 128 - (sum % 128);

        System.arraycopy(head, 0, msg, 0, 7);
        System.arraycopy(addr, 0, msg, 7, addr.length);
        System.arraycopy(data, 0, msg, 7 + addr.length, data.length);
        msg[msg.length-2] = (byte) checksum;
        msg[msg.length-1] = (byte) 0xF7;			// EOX


        return msg;
    }
    
 
    
    
    public static void arpSetGrid(int x, MidiDevice device) {
        if(x < 0 || x > 8)
            logger.log(Level.WARNING, Integer.toString(x) + " is not a valid number for parameter Grid");
        
        sendSysexDataSet(new byte[]{0x10, 0x00, 0x0C, 0x00}, new byte[]{(byte) x}, device);
    }
    
    public static void arpSetDuration(int x, MidiDevice device) {
        if(x < 0 || x > 9)
            logger.log(Level.WARNING, Integer.toString(x) + " is not a valid number for parameter Duration");
        
        sendSysexDataSet(new byte[]{0x10, 0x00, 0x0C, 0x01}, new byte[]{(byte) x}, device);
    }
    
    public static void arpSetMotif(int x, MidiDevice device) {
        if(x < 0 || x > 11)
            logger.log(Level.WARNING, Integer.toString(x) + " is not a valid number for parameter Motif");
        
        sendSysexDataSet(new byte[]{0x10, 0x00, 0x0C, 0x02}, new byte[]{(byte) x}, device);
    }
    
    public static void arpSetOctaveRange(int x, MidiDevice device) {
        if(x < 61 || x > 67)
            logger.log(Level.WARNING, Integer.toString(x) + " is not a valid number for parameter Octave Range");
        
        sendSysexDataSet(new byte[]{0x10, 0x00, 0x0C, 0x03}, new byte[]{(byte) x}, device);
    }
    
    public static void arpSetAccentRate(int x, MidiDevice device) {
        if(x < 0 || x > 100)
            logger.log(Level.WARNING, Integer.toString(x) + " is not a valid number for parameter Accent Rate");
        
        sendSysexDataSet(new byte[]{0x10, 0x00, 0x0C, 0x04}, new byte[]{(byte) x}, device);
    }
    
    public static void arpSetVelocity(int x, MidiDevice device) {
        if(x < 0 || x > 127)
            logger.log(Level.WARNING, Integer.toString(x) + " is not a valid number for parameter Velocity");
        
        sendSysexDataSet(new byte[]{0x10, 0x00, 0x0C, 0x05}, new byte[]{(byte) x}, device);
    }
    
    public static void arpSetEndStep(int value, MidiDevice device) {
        if(value < 1 || value > 32)
            logger.log(Level.WARNING, Integer.toString(value) + " is not a valid number for parameter End Step");
        
        int lhs = (value & 0xf0) >> 4;
        int rhs = value & 0x0f;
        
        sendSysexDataSet(new byte[]{0x10, 0x00, 0x0C, 0x06}, new byte[]{(byte) lhs, (byte) rhs}, device);
    }
    
    
    public static void arpSetPatternStep(int pat, int step, int value, MidiDevice device) {
        if(step < 1 || step > 32)
            logger.log(Level.WARNING, "{0} is not valid for <step>", Integer.toString(step));
        if(pat < 1 || pat > 16)
            logger.log(Level.WARNING, "{0} is not valid for <pat>", Integer.toString(pat));
        if(value < 0 || value > 128)
            logger.log(Level.WARNING, "{0} is not valid for <value>", Integer.toString(value));
        
        
        int lhs = (value & 0xf0) >> 4;
        int rhs = value & 0x0f;
        
        sendSysexDataSet(
                new byte[]{0x10, 0x00, (byte) (0x0D + pat - 1), (byte) (step * 2)},
                new byte[]{(byte) lhs, (byte) rhs},
                device
        );
    }
    
    public static void arpSetPatternOriginalNote(int pat, int value, MidiDevice device) {
        if(pat < 1 || pat > 16)
            logger.log(Level.WARNING, "{0} is not valid for <note>", Integer.toString(pat));
        if(value < 0 || value > 128)
            logger.log(Level.WARNING, "{0} is not valid for <value>", Integer.toString(value));
        
        int lhs = (value & 0xf0) >> 4;
        int rhs = value & 0x0f;
        
        sendSysexDataSet(
                new byte[]{0x10, 0x00, (byte) (0x0D + pat - 1), 0x00},
                new byte[]{(byte) lhs, (byte) rhs},
                device
        );
    }
    
    
    public static String bytesToHexString(byte[] bytes) {
        String result = new String();
        
        for(byte b : bytes) {
            result += String.format("%02X ", b);
        }
        
        return result;
    }
    
    
   
}
