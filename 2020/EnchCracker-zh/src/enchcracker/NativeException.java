package enchcracker;

public class NativeException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public NativeException(final String desc) {
        super(desc);
    }
}
