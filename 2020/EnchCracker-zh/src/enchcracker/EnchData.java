package enchcracker;

public class EnchData
{
    private int shelves;
    private int s1;
    private int s2;
    private int s3;
    
    public EnchData(final int shelves, final int s1, final int s2, final int s3) {
        this.shelves = -1;
        this.s1 = -1;
        this.s2 = -1;
        this.s3 = -1;
        this.shelves = shelves;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }
    
    @Override
    public String toString() {
        return String.format("%2d   bookshelves:     %2d     %2d     %2d%n", this.shelves, this.s1, this.s2, this.s3);
    }
}
