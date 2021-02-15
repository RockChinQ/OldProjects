package enchcracker;

import java.text.*;
import java.io.*;
import java.util.logging.*;

public class Log
{
    private static FileHandler fileHandler;
    private static final Logger LOGGER;
    
    static {
        Log.fileHandler = null;
        try {
            Log.fileHandler = new FileHandler("enchcracker.log");
        }
        catch (Exception e) {
            System.err.println("Exception creating log file");
            e.printStackTrace();
        }
        LOGGER = getLogger();
    }
    
    private Log() {
    }
    
    public static void cleanupLogging() {
        Log.fileHandler.close();
    }
    
    private static Logger getLogger() {
        final Logger logger = Logger.getLogger("");
        logger.setUseParentHandlers(false);
        logger.addHandler(Log.fileHandler);
        final SimpleFormatter formatter = new SimpleFormatter() {
            private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            @Override
            public String format(final LogRecord record) {
                String thrown;
                if (record.getThrown() == null) {
                    thrown = "";
                }
                else {
                    final StringWriter sw = new StringWriter();
                    final PrintWriter pw = new PrintWriter(sw);
                    pw.println();
                    record.getThrown().printStackTrace(pw);
                    thrown = sw.toString();
                }
                return String.format("[%s] [%s/%s]: %s%s%n", this.dateFormat.format(record.getMillis()), record.getLoggerName(), record.getLevel(), record.getMessage(), thrown);
            }
        };
        Handler[] handlers;
        for (int length = (handlers = logger.getHandlers()).length, i = 0; i < length; ++i) {
            final Handler handler = handlers[i];
            handler.setFormatter(formatter);
        }
        return logger;
    }
    
    public static void info(final String message) {
        Log.LOGGER.info(message);
    }
    
    public static void warn(final String message) {
        Log.LOGGER.log(Level.WARNING, message);
    }
    
    public static void warn(final String message, final Throwable thrown) {
        Log.LOGGER.log(Level.WARNING, message, thrown);
    }
    
    public static void fatal(final String message) {
        Log.LOGGER.log(Level.SEVERE, message);
    }
    
    public static void fatal(final String message, final Throwable thrown) {
        Log.LOGGER.log(Level.SEVERE, message, thrown);
    }
}
