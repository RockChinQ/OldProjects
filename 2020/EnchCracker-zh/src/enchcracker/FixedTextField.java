package enchcracker;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class FixedTextField extends JTextField
{
    private static final long serialVersionUID = 1L;
    
    public FixedTextField() {
    }
    
    public FixedTextField(final String text) {
        super(text);
    }
    
    @Deprecated
    @Override
    public Rectangle modelToView(final int pos) throws BadLocationException {
        try {
            this.getLocationOnScreen();
        }
        catch (IllegalComponentStateException e) {
            throw new BadLocationException(null, 0);
        }
        return super.modelToView(pos);
    }
}
