package enchcracker;

import javax.swing.*;
import java.awt.*;

public class NativeSingleSeedCracker extends AbstractSingleSeedCracker
{
    @Override
    public boolean initCracker() {
        Log.info("System details:");
        Log.info("OS = " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        Log.info("Arch (either OS/Java) = " + System.getProperty("os.arch"));
        Log.info("Java = " + System.getProperty("java.version"));
        if (System.getProperties().containsKey("sun.arch.data.model")) {
            Log.info("Java arch = " + System.getProperty("sun.arch.data.model"));
        }
        try {
            System.loadLibrary("enchcracker");
        }
        catch (UnsatisfiedLinkError e) {
            Log.warn("Failed to load native enchcracker library! Using the Java version instead", e);
            String message = "Failed to load native enchcracker library!\n";
            final String origMessage = e.getMessage();
            message = String.valueOf(message) + "Message: " + origMessage + "\n";
            String probableCause;
            if (origMessage.contains("32") || origMessage.contains("64")) {
                probableCause = "You are using 32-bit Java or a 32-bit system. You need 64-bit.";
            }
            else if (origMessage.contains("java.library.path")) {
                probableCause = "The library file could not be found.\nEnsure you are launching as described in README.txt";
            }
            else {
                probableCause = "Unknown";
            }
            message = String.valueOf(message) + "Probable cause: " + probableCause + "\n";
            message = String.valueOf(message) + "\n";
            message = String.valueOf(message) + "If you are sure this is not the problem, create an issue on GitHub.\n";
            message = String.valueOf(message) + "Make sure to include the contents of the log file (enchcracker.log) in your bug report.\n";
            message = String.valueOf(message) + "\n";
            message = String.valueOf(message) + "Press OK to continue with a slower Java implementation of the XP seed finder";
            JOptionPane.showMessageDialog(null, message, "Library load error", 2);
            return false;
        }
        this.ninitCracker();
        return true;
    }
    
    private native void ninitCracker();
    
    private native void nfinalizeCracker();
    
    @Override
    public void resetCracker() {
        this.nresetCracker();
    }
    
    private native void nresetCracker();
    
    @Override
    public void firstInput(final int bookshelves, final int slot1, final int slot2, final int slot3) {
        this.nfirstInput(bookshelves, slot1, slot2, slot3);
    }
    
    private native void nfirstInput(final int p0, final int p1, final int p2, final int p3);
    
    @Override
    public void addInput(final int bookshelves, final int slot1, final int slot2, final int slot3) {
        this.naddInput(bookshelves, slot1, slot2, slot3);
    }
    
    private native void naddInput(final int p0, final int p1, final int p2, final int p3);
    
    @Override
    public int getPossibleSeeds() {
        return this.ngetPossibleSeeds();
    }
    
    private native int ngetPossibleSeeds();
    
    @Override
    public int getSeed() {
        return this.ngetSeed();
    }
    
    private native int ngetSeed();
    
    @Override
    public void requestAbort() {
        this.nrequestAbort();
    }
    
    private native void nrequestAbort();
    
    @Override
    public boolean isAbortRequested() {
        return this.nisAbortRequested();
    }
    
    private native boolean nisAbortRequested();
    
    @Override
    public long getSeedsSearched() {
        return this.ngetSeedsSearched();
    }
    
    private native long ngetSeedsSearched();
    
    public void finalize() {
        this.nfinalizeCracker();
    }
}
