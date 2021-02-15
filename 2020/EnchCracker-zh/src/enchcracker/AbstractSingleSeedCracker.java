package enchcracker;

import java.util.concurrent.atomic.*;

public abstract class AbstractSingleSeedCracker
{
    private boolean firstTime;
    private AtomicBoolean running;
    
    public AbstractSingleSeedCracker() {
        this.firstTime = true;
        this.running = new AtomicBoolean(false);
    }
    
    public void setRunning(final boolean running) {
        this.running.set(running);
    }
    
    public boolean isRunning() {
        return this.running.get();
    }
    
    public void abortAndThen(final Runnable r) {
        if (this.isRunning()) {
            if (!this.isAbortRequested()) {
                this.requestAbort();
            }
            while (this.isRunning()) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        r.run();
    }
    
    public void setFirstTime(final boolean firstTime) {
        this.firstTime = firstTime;
    }
    
    public boolean isFirstTime() {
        return this.firstTime;
    }
    
    public abstract boolean initCracker();
    
    public abstract void resetCracker();
    
    public abstract void firstInput(final int p0, final int p1, final int p2, final int p3);
    
    public abstract void addInput(final int p0, final int p1, final int p2, final int p3);
    
    public abstract int getPossibleSeeds();
    
    public abstract int getSeed();
    
    public abstract void requestAbort();
    
    public abstract boolean isAbortRequested();
    
    public abstract long getSeedsSearched();
}
