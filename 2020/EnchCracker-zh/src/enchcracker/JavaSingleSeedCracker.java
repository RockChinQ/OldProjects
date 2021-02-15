package enchcracker;

import java.util.concurrent.atomic.*;
import java.util.*;

public class JavaSingleSeedCracker extends AbstractSingleSeedCracker
{
    private ArrayList<Integer> possibleSeeds;
    private ArrayList<Integer> nextPossibleSeeds;
    private AtomicLong seedsSearched;
    private AtomicBoolean abortRequested;
    
    public JavaSingleSeedCracker() {
        this.possibleSeeds = new ArrayList<Integer>();
        this.nextPossibleSeeds = new ArrayList<Integer>();
        this.seedsSearched = new AtomicLong(0L);
        this.abortRequested = new AtomicBoolean(false);
    }
    
    private static int getGenericEnchantability(final Random rand, final int bookshelves) {
        final int first = rand.nextInt(8);
        final int second = rand.nextInt(bookshelves + 1);
        return first + 1 + (bookshelves >> 1) + second;
    }
    
    private static int getLevelsSlot1(final Random rand, final int bookshelves) {
        final int enchantability = getGenericEnchantability(rand, bookshelves) / 3;
        return (enchantability < 1) ? 1 : enchantability;
    }
    
    private static int getLevelsSlot2(final Random rand, final int bookshelves) {
        return getGenericEnchantability(rand, bookshelves) * 2 / 3 + 1;
    }
    
    private static int getLevelsSlot3(final Random rand, final int bookshelves) {
        final int enchantability = getGenericEnchantability(rand, bookshelves);
        final int twiceBookshelves = bookshelves * 2;
        return (enchantability < twiceBookshelves) ? twiceBookshelves : enchantability;
    }
    
    @Override
    public boolean initCracker() {
        this.possibleSeeds.ensureCapacity(134217728);
        return true;
    }
    
    @Override
    public void resetCracker() {
        this.possibleSeeds.clear();
        this.nextPossibleSeeds.clear();
    }
    
    @Override
    public void firstInput(final int bookshelves, final int slot1, final int slot2, final int slot3) {
        final Random rand = new Random();
        this.seedsSearched.set(0L);
        for (long seed = 0L, e = 4294967296L; seed < e; ++seed) {
            if (seed % 1000000L == 0L) {
                this.seedsSearched.set(seed);
                if (this.abortRequested.get()) {
                    this.abortRequested.set(false);
                    break;
                }
            }
            rand.setSeed((int)seed);
            if (getLevelsSlot1(rand, bookshelves) == slot1 && getLevelsSlot2(rand, bookshelves) == slot2 && getLevelsSlot3(rand, bookshelves) == slot3) {
                synchronized (this.possibleSeeds) {
                    this.possibleSeeds.add((int)seed);
                }
                // monitorexit(this.possibleSeeds)
            }
        }
    }
    
    @Override
    public void addInput(final int bookshelves, final int slot1, final int slot2, final int slot3) {
        final Random rand = new Random();
        this.nextPossibleSeeds.clear();
        this.seedsSearched.set(0L);
        for (int i = 0, e = this.possibleSeeds.size(); i < e; ++i) {
            if (i % 1000000 == 0) {
                if (this.abortRequested.get()) {
                    this.abortRequested.set(false);
                    break;
                }
                this.seedsSearched.set(i);
            }
            rand.setSeed(this.possibleSeeds.get(i));
            if (getLevelsSlot1(rand, bookshelves) == slot1 && getLevelsSlot2(rand, bookshelves) == slot2 && getLevelsSlot3(rand, bookshelves) == slot3) {
                synchronized (this.possibleSeeds) {
                    this.nextPossibleSeeds.add(this.possibleSeeds.get(i));
                }
                // monitorexit(this.possibleSeeds)
            }
        }
        this.possibleSeeds.clear();
        this.possibleSeeds.addAll(this.nextPossibleSeeds);
    }
    
    @Override
    public int getPossibleSeeds() {
        synchronized (this.possibleSeeds) {
            // monitorexit(this.possibleSeeds)
            return this.possibleSeeds.size();
        }
    }
    
    @Override
    public int getSeed() {
        return this.possibleSeeds.get(0);
    }
    
    @Override
    public void requestAbort() {
        this.abortRequested.set(true);
    }
    
    @Override
    public boolean isAbortRequested() {
        return this.abortRequested.get();
    }
    
    @Override
    public long getSeedsSearched() {
        return this.seedsSearched.get();
    }
}
