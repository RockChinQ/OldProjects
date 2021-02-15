package enchcracker;

import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

public class EnchCrackerWindow extends JFrame
{
    private JPanel contentPane;
    private JTextField bookshelvesTextField;
    private JTextField slot1TextField;
    private JTextField slot2TextField;
    private JTextField slot3TextField;
    private JProgressBar progressBar;
    private JTextField xpSeedOutput;
    private JTextField xpSeed1TextField;
    private JTextField xpSeed2TextField;
    private long playerSeed;
    private JLabel playerSeedOutput;
    private JTextField itemTextField;
    private JLabel manipulateOutput;
    private JTextField enchantmentTextField;
    private int timesNeeded;
    private DefaultListModel<Enchantments.EnchantmentInstance> wantedListModel;
    private DefaultListModel<Enchantments.EnchantmentInstance> unwantedListModel;
    private JTextField forcePlayerSeedTextField;
    private static AbstractSingleSeedCracker singleSeedCracker;
    
    public static void main(final String[] args) {
        //final JPanel panel;
       // final JLabel link;
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            Log.fatal("An unexpected error occurred", e);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, 1));
            panel.add(new JLabel("An unexpected error occurred!"));
            panel.add(new JLabel(e.toString()));
            panel.add(Box.createVerticalStrut(20));
            panel.add(new JLabel("Please report this on the GitHub page at:"));
            JLabel link = new JLabel("<html><a href = \"https://github.com/Earthcomputer/EnchantmentCracker/issues\">https://github.com/Earthcomputer/EnchantmentCracker/issues</a></html>");
            link.setCursor(Cursor.getPredefinedCursor(12));
            link.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    browse("https://github.com/Earthcomputer/EnchantmentCracker/issues");
                }
            });
            panel.add(link);
            panel.add(new JLabel("Please include the log file (enchcracker.log) in your bug report."));
            JOptionPane.showMessageDialog(null, panel, "Enchantment Crasher", 0);
            System.exit(1);
            return;
        });
        Runtime.getRuntime().addShutdownHook(new Thread(Log::cleanupLogging));
        EnchCrackerWindow.singleSeedCracker = new NativeSingleSeedCracker();
        if (!EnchCrackerWindow.singleSeedCracker.initCracker()) {
            EnchCrackerWindow.singleSeedCracker = new JavaSingleSeedCracker();
            if (!EnchCrackerWindow.singleSeedCracker.initCracker()) {
                return;
            }
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final EnchCrackerWindow frame = new EnchCrackerWindow();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    Log.fatal("Exception creating frame", e);
                }
            }
        });
    }
    
    public EnchCrackerWindow() {
        this.timesNeeded = -2;
        this.setTitle("Enchantment Cracker");
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 565, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(new BoxLayout(this.contentPane, 1));
        final JTabbedPane tabbedPane = new JTabbedPane(1);
        this.contentPane.add(tabbedPane);
        final JPanel panel_6 = new JPanel();
        tabbedPane.addTab("XP seed", null, panel_6, null);
        panel_6.setLayout(new BoxLayout(panel_6, 0));
        final JPanel viewPanel = new JPanel();
        tabbedPane.addTab("Loaded Data", null, viewPanel, null);
        viewPanel.setLayout(new BoxLayout(viewPanel, 0));
        final JPanel dataPanel1 = new JPanel();
        dataPanel1.setLayout(new BoxLayout(dataPanel1, 1));
        final JPanel dataPanel2 = new JPanel();
        dataPanel2.setLayout(new BoxLayout(dataPanel2, 1));
        final JSplitPane enchDataPanel = new JSplitPane(1, dataPanel1, dataPanel2);
        viewPanel.add(enchDataPanel);
        enchDataPanel.setDividerLocation(232);
        final JPanel panel_7 = new JPanel();
        panel_6.add(panel_7);
        panel_7.setLayout(new BoxLayout(panel_7, 1));
        final JPanel panel = new JPanel();
        panel_7.add(panel);
        final JLabel lblNumberOfBookshelves = new JLabel("Number of bookshelves:");
        lblNumberOfBookshelves.setToolTipText("The number of bookshelves exposed to the enchanting table");
        panel.add(lblNumberOfBookshelves);
        panel.add(this.bookshelvesTextField = new FixedTextField());
        this.bookshelvesTextField.setColumns(10);
        final JPanel panel_8 = new JPanel();
        panel_7.add(panel_8);
        final JLabel lblSlot = new JLabel("Slot 1:");
        lblSlot.setToolTipText("The number on the right of the top slot");
        panel_8.add(lblSlot);
        panel_8.add(this.slot1TextField = new FixedTextField());
        this.slot1TextField.setColumns(10);
        final JPanel panel_9 = new JPanel();
        panel_7.add(panel_9);
        final JLabel lblSlot_1 = new JLabel("Slot 2:");
        lblSlot_1.setToolTipText("The number on the right of the middle slot");
        panel_9.add(lblSlot_1);
        panel_9.add(this.slot2TextField = new FixedTextField());
        this.slot2TextField.setColumns(10);
        final JPanel panel_10 = new JPanel();
        panel_7.add(panel_10);
        final JLabel lblSlot_2 = new JLabel("Slot 3:");
        lblSlot_2.setToolTipText("The number on the right of the bottom slot");
        panel_10.add(lblSlot_2);
        panel_10.add(this.slot3TextField = new FixedTextField());
        this.slot3TextField.setColumns(10);
        final JPanel panel_11 = new JPanel();
        panel_6.add(panel_11);
        panel_11.setLayout(new BoxLayout(panel_11, 1));
        final JPanel panel_12 = new JPanel();
        panel_11.add(panel_12);
        final JButton btnAddInfo = new JButton("Add Info");
        panel_12.add(btnAddInfo);
        btnAddInfo.setToolTipText("Use this information to narrow down the possible XP seeds");
        final ArrayList<EnchData> enchData = new ArrayList<EnchData>();
        btnAddInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                EnchCrackerWindow.this.bookshelvesTextField.setBackground(Color.white);
                EnchCrackerWindow.this.slot1TextField.setBackground(Color.white);
                EnchCrackerWindow.this.slot2TextField.setBackground(Color.white);
                EnchCrackerWindow.this.slot3TextField.setBackground(Color.white);
                int bookshelves;
                int slot1;
                int slot2;
                int slot3;
                try {
                    bookshelves = Integer.parseInt(EnchCrackerWindow.this.bookshelvesTextField.getText());
                    slot1 = Integer.parseInt(EnchCrackerWindow.this.slot1TextField.getText());
                    slot2 = Integer.parseInt(EnchCrackerWindow.this.slot2TextField.getText());
                    slot3 = Integer.parseInt(EnchCrackerWindow.this.slot3TextField.getText());
                }
                catch (NumberFormatException e) {
                    Log.info("Add info failed, fields had invalid numbers");
                    return;
                }
                if (bookshelves < 0 || bookshelves > 15) {
                    Log.info("Add info failed, bookshelf count invalid");
                    EnchCrackerWindow.this.bookshelvesTextField.setBackground(new Color(1.0f, 0.3f, 0.0f));
                    return;
                }
                if (slot1 < 0 || slot1 > 30) {
                    Log.info("Add info failed, slot 1 count invalid");
                    EnchCrackerWindow.this.slot1TextField.setBackground(new Color(1.0f, 0.3f, 0.0f));
                    return;
                }
                if (slot2 < 0 || slot2 > 30) {
                    Log.info("Add info failed, slot 2 count invalid");
                    EnchCrackerWindow.this.slot2TextField.setBackground(new Color(1.0f, 0.3f, 0.0f));
                    return;
                }
                if (slot3 < 0 || slot3 > 30) {
                    Log.info("Add info failed, slot 3 count invalid");
                    EnchCrackerWindow.this.slot3TextField.setBackground(new Color(1.0f, 0.3f, 0.0f));
                    return;
                }
                Log.info("Added info, b = " + bookshelves + ", s1 = " + slot1 + ", s2 = " + slot2 + ", s3 = " + slot3);
                final EnchData ench = new EnchData(bookshelves, slot1, slot2, slot3);
                enchData.add(ench);
                final JLabel enchString = new JLabel(ench.toString());
                if (dataPanel1.getComponentCount() < 8) {
                    dataPanel1.add(enchString);
                }
                else {
                    dataPanel2.add(enchString);
                }
                
                
                int possibleSeeds;
                int possibleSeeds2;
                long seedsSearched;
                Thread thread2;
                long seedsSearched2;
                EnchCrackerWindow.singleSeedCracker.abortAndThen(() -> {
                	boolean firstTime;
                    firstTime = EnchCrackerWindow.singleSeedCracker.isFirstTime();
                    EnchCrackerWindow.singleSeedCracker.setFirstTime(false);
                    if (firstTime) {
                        Thread thread;
                        thread = new Thread(() -> {
                        	final int n;
                        	final int n2;
                        	final int n3;
                        	final int n4;
                            EnchCrackerWindow.singleSeedCracker.firstInput(n, n2, n3, n4);
                            possibleSeeds = EnchCrackerWindow.singleSeedCracker.getPossibleSeeds();
                            Log.info("Reduced possible seeds to " + possibleSeeds);
                            switch (possibleSeeds) {
                                case 0: {
                                    EnchCrackerWindow.this.xpSeedOutput.setText("No possible seeds");
                                    break;
                                }
                                case 1: {
                                    EnchCrackerWindow.this.xpSeedOutput.setText(String.format("XP seed: %08X", EnchCrackerWindow.singleSeedCracker.getSeed()));
                                    if (EnchCrackerWindow.this.xpSeed1TextField.getText().isEmpty()) {
                                        EnchCrackerWindow.this.xpSeed1TextField.setText(Integer.toHexString(EnchCrackerWindow.singleSeedCracker.getSeed()));
                                        break;
                                    }
                                    else if (EnchCrackerWindow.this.xpSeed2TextField.getText().isEmpty()) {
                                        EnchCrackerWindow.this.xpSeed2TextField.setText(Integer.toHexString(EnchCrackerWindow.singleSeedCracker.getSeed()));
                                        break;
                                    }
                                    else {
                                        break;
                                    }
                                    break;
                                }
                                default: {
                                    EnchCrackerWindow.this.xpSeedOutput.setText("Possible seeds: " + possibleSeeds);
                                    break;
                                }
                            }
                            EnchCrackerWindow.singleSeedCracker.setRunning(false);
                            return;
                        });
                    }
                    else {
                        Thread thread;
                        thread = new Thread(() -> {
                        	final int n;
                        	final int n2;
                        	final int n3;
                        	final int n4;
                            EnchCrackerWindow.singleSeedCracker.addInput(n, n2, n3, n4);
                            possibleSeeds2 = EnchCrackerWindow.singleSeedCracker.getPossibleSeeds();
                            Log.info("Reduced possible seeds to " + possibleSeeds2);
                            switch (possibleSeeds2) {
                                case 0: {
                                    EnchCrackerWindow.this.xpSeedOutput.setText("No possible seeds");
                                    break;
                                }
                                case 1: {
                                    EnchCrackerWindow.this.xpSeedOutput.setText(String.format("XP seed: %08X", EnchCrackerWindow.singleSeedCracker.getSeed()));
                                    if (EnchCrackerWindow.this.xpSeed1TextField.getText().isEmpty()) {
                                        EnchCrackerWindow.this.xpSeed1TextField.setText(Integer.toHexString(EnchCrackerWindow.singleSeedCracker.getSeed()));
                                        break;
                                    }
                                    else if (EnchCrackerWindow.this.xpSeed2TextField.getText().isEmpty()) {
                                        EnchCrackerWindow.this.xpSeed2TextField.setText(Integer.toHexString(EnchCrackerWindow.singleSeedCracker.getSeed()));
                                        break;
                                    }
                                    else {
                                        break;
                                    }
                                    break;
                                }
                                default: {
                                    EnchCrackerWindow.this.xpSeedOutput.setText("Possible seeds: " + possibleSeeds2);
                                    break;
                                }
                            }
                            EnchCrackerWindow.singleSeedCracker.setRunning(false);
                            return;
                        });
                    }
                    thread.setDaemon(true);
                    EnchCrackerWindow.singleSeedCracker.setRunning(true);
                    thread.start();
                    if (firstTime) {
                        EnchCrackerWindow.this.progressBar.setMaximum(65536);
                        EnchCrackerWindow.this.progressBar.setStringPainted(true);
                        thread2 = new Thread(() -> {
                            while (EnchCrackerWindow.singleSeedCracker.isRunning()) {
                                seedsSearched = EnchCrackerWindow.singleSeedCracker.getSeedsSearched();
                                EnchCrackerWindow.this.progressBar.setValue((int)(seedsSearched >>> 16));
                                EnchCrackerWindow.this.progressBar.setString("Seeds searched: " + seedsSearched + " / 4294967296");
                                try {
                                    Thread.sleep(500L);
                                }
                                catch (InterruptedException e2) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                            EnchCrackerWindow.this.progressBar.setStringPainted(false);
                            EnchCrackerWindow.this.progressBar.setValue(0);
                            return;
                        });
                    }
                    else {
                        EnchCrackerWindow.this.progressBar.setValue(0);
                        EnchCrackerWindow.this.progressBar.setMaximum(EnchCrackerWindow.singleSeedCracker.getPossibleSeeds());
                        EnchCrackerWindow.this.progressBar.setStringPainted(true);
                        thread2 = new Thread(() -> {
                            while (EnchCrackerWindow.singleSeedCracker.isRunning()) {
                                seedsSearched2 = EnchCrackerWindow.singleSeedCracker.getSeedsSearched();
                                EnchCrackerWindow.this.progressBar.setValue((int)seedsSearched2);
                                EnchCrackerWindow.this.progressBar.setString("Seeds searched: " + seedsSearched2 + " / " + EnchCrackerWindow.this.progressBar.getMaximum());
                                try {
                                    Thread.sleep(500L);
                                }
                                catch (InterruptedException e3) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                            EnchCrackerWindow.this.progressBar.setStringPainted(false);
                            EnchCrackerWindow.this.progressBar.setValue(0);
                            return;
                        });
                    }
                    thread2.setDaemon(true);
                    thread2.start();
                });
            }
        });
        btnAddInfo.setAlignmentX(0.5f);
        final JPanel view = new JPanel();
        panel_11.add(view);
        final JButton btnViewData = new JButton("View Data");
        view.add(btnViewData);
        btnViewData.setToolTipText("View all currently loaded data");
        btnViewData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                Log.info("View button pressed");
                tabbedPane.setSelectedIndex(1);
            }
        });
        btnViewData.setAlignmentX(0.5f);
        final JPanel panel_13 = new JPanel();
        panel_11.add(panel_13);
        final JButton btnResetCracker = new JButton("Reset Cracker");
        panel_13.add(btnResetCracker);
        btnResetCracker.setToolTipText("Reset the XP seed cracker so a new XP seed can be cracked");
        btnResetCracker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     2: invokestatic    enchcracker/Log.info:(Ljava/lang/String;)V
                //     5: invokestatic    enchcracker/EnchCrackerWindow.access$5:()Lenchcracker/AbstractSingleSeedCracker;
                //     8: aload_0         /* this */
                //     9: aload_0         /* this */
                //    10: getfield        enchcracker/EnchCrackerWindow$5.val$dataPanel1:Ljavax/swing/JPanel;
                //    13: aload_0         /* this */
                //    14: getfield        enchcracker/EnchCrackerWindow$5.val$dataPanel2:Ljavax/swing/JPanel;
                //    17: invokedynamic   BootstrapMethod #0, run:(Lenchcracker/EnchCrackerWindow$5;Ljavax/swing/JPanel;Ljavax/swing/JPanel;)Ljava/lang/Runnable;
                //    22: invokevirtual   enchcracker/AbstractSingleSeedCracker.abortAndThen:(Ljava/lang/Runnable;)V
                //    25: return         
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Could not infer any expression.
                //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
                //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
                //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
                //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
                //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
                //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
                //     at java.lang.Thread.run(Unknown Source)
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
        });
        btnResetCracker.setAlignmentX(0.5f);
        final JPanel panel_14 = new JPanel();
        panel_11.add(panel_14);
        panel_14.setLayout(new FlowLayout(1, 5, 5));
        (this.xpSeedOutput = new FixedTextField("XP seed: unknown")).setFont(new Font("Dialog", 1, 12));
        this.xpSeedOutput.setEditable(false);
        this.xpSeedOutput.setBackground(null);
        this.xpSeedOutput.setBorder(null);
        this.xpSeedOutput.setAlignmentX(0.5f);
        panel_14.add(this.xpSeedOutput);
        final JPanel panel_15 = new JPanel();
        tabbedPane.addTab("Player seed", null, panel_15, null);
        panel_15.setLayout(new BoxLayout(panel_15, 1));
        final JPanel panel_16 = new JPanel();
        panel_15.add(panel_16);
        final JLabel lblXpSeed = new JLabel("XP seed 1:");
        lblXpSeed.setToolTipText("The first consecutive XP seed");
        panel_16.add(lblXpSeed);
        panel_16.add(this.xpSeed1TextField = new FixedTextField());
        this.xpSeed1TextField.setColumns(10);
        final JPanel panel_17 = new JPanel();
        panel_15.add(panel_17);
        final JLabel lblXpSeed_1 = new JLabel("XP seed 2:");
        lblXpSeed_1.setToolTipText("The second consecutive XP seed");
        panel_17.add(lblXpSeed_1);
        panel_17.add(this.xpSeed2TextField = new FixedTextField());
        this.xpSeed2TextField.setColumns(10);
        final JButton btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                EnchCrackerWindow.this.forcePlayerSeedTextField.setBackground(Color.white);
                EnchCrackerWindow.this.xpSeed1TextField.setBackground(Color.white);
                EnchCrackerWindow.this.xpSeed2TextField.setBackground(Color.white);
                boolean found;
                if (!EnchCrackerWindow.this.forcePlayerSeedTextField.getText().isEmpty()) {
                    try {
                        EnchCrackerWindow.access$11(EnchCrackerWindow.this, Long.parseUnsignedLong(EnchCrackerWindow.this.forcePlayerSeedTextField.getText(), 16) & 0xFFFFFFFFFFFFL);
                    }
                    catch (NumberFormatException e) {
                        Log.info("Calculate player seed failed, invalid force player seed");
                        EnchCrackerWindow.this.forcePlayerSeedTextField.setBackground(new Color(1.0f, 0.3f, 0.0f));
                        return;
                    }
                    Log.info("Forced player seed");
                    found = true;
                }
                else {
                    int xpSeed1;
                    try {
                        xpSeed1 = Integer.parseUnsignedInt(EnchCrackerWindow.this.xpSeed1TextField.getText(), 16);
                    }
                    catch (NumberFormatException e2) {
                        Log.info("Calculate player seed failed, XP seed 1 invalid");
                        EnchCrackerWindow.this.xpSeed1TextField.setBackground(new Color(1.0f, 0.3f, 0.0f));
                        return;
                    }
                    int xpSeed2;
                    try {
                        xpSeed2 = Integer.parseUnsignedInt(EnchCrackerWindow.this.xpSeed2TextField.getText(), 16);
                    }
                    catch (NumberFormatException e2) {
                        Log.info("Calculate player seed failed, XP seed 2 invalid");
                        EnchCrackerWindow.this.xpSeed2TextField.setBackground(new Color(1.0f, 0.3f, 0.0f));
                        return;
                    }
                    Log.info("Calculating player seed with " + Integer.toHexString(xpSeed1) + ", " + Integer.toHexString(xpSeed2));
                    final long seed1High = (long)xpSeed1 << 16 & 0xFFFFFFFF0000L;
                    final long seed2High = (long)xpSeed2 << 16 & 0xFFFFFFFF0000L;
                    found = false;
                    for (int seed1Low = 0; seed1Low < 65536; ++seed1Low) {
                        if (((seed1High | (long)seed1Low) * 25214903917L + 11L & 0xFFFFFFFF0000L) == seed2High) {
                            EnchCrackerWindow.access$11(EnchCrackerWindow.this, (seed1High | (long)seed1Low) * 25214903917L + 11L & 0xFFFFFFFFFFFFL);
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    Log.info("Played seed calculated as " + Long.toHexString(EnchCrackerWindow.this.playerSeed));
                    EnchCrackerWindow.this.playerSeedOutput.setText(String.format("Player seed set to %012X", EnchCrackerWindow.this.playerSeed));
                }
                else {
                    Log.info("No player seed found");
                    EnchCrackerWindow.this.playerSeedOutput.setText("Player seed not found");
                }
            }
        });
        final JPanel panel_18 = new JPanel();
        panel_15.add(panel_18);
        final JLabel lblForcePlayerSeed = new JLabel("Force Player Seed (optional):");
        lblForcePlayerSeed.setToolTipText("Only use this if you (for some reason) already know the player seed");
        panel_18.add(lblForcePlayerSeed);
        panel_18.add(this.forcePlayerSeedTextField = new FixedTextField());
        this.forcePlayerSeedTextField.setColumns(10);
        btnCalculate.setAlignmentX(0.5f);
        panel_15.add(btnCalculate);
        (this.playerSeedOutput = new JLabel("No seed calculated")).setAlignmentX(0.5f);
        panel_15.add(this.playerSeedOutput);
        final JPanel panel_19 = new JPanel();
        tabbedPane.addTab("Manipulate", null, panel_19, null);
        panel_19.setLayout(new BoxLayout(panel_19, 1));
        final JPanel panel_20 = new JPanel();
        panel_19.add(panel_20);
        final JLabel lblItem = new JLabel("Item:");
        lblItem.setToolTipText("The item you want to enchant");
        panel_20.add(lblItem);
        panel_20.add(this.itemTextField = new FixedTextField());
        this.itemTextField.setColumns(10);
        final JButton btnCalculate_1 = new JButton("Calculate");
        btnCalculate_1.setToolTipText("<html>Press to calculate how many items you <i>would</i> need to throw to get these enchantments</html>");
        btnCalculate_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                long seed = EnchCrackerWindow.this.playerSeed;
                final String item = EnchCrackerWindow.this.itemTextField.getText();
                Log.info("Calculating items to throw");
                Log.info("Item: " + item);
                Log.info("Wanted list:");
                Enumeration<Enchantments.EnchantmentInstance> e = EnchCrackerWindow.this.wantedListModel.elements();
                while (e.hasMoreElements()) {
                    Log.info("  " + e.nextElement());
                }
                Log.info("Not wanted list:");
                e = EnchCrackerWindow.this.unwantedListModel.elements();
                while (e.hasMoreElements()) {
                    Log.info("  " + e.nextElement());
                }
                if (Items.getEnchantability(item) == 0) {
                    return;
                }
                int timesNeeded = -2;
                int bookshelvesNeeded = 0;
                int slot = 0;
                final int[] enchantLevels = new int[3];
            Label_0476:
                for (int i = -1; i < 10000; ++i) {
                    int xpSeed;
                    if (i == -1) {
                        xpSeed = (int)(seed >>> 16);
                    }
                    else {
                        xpSeed = (int)((seed * 25214903917L + 11L & 0xFFFFFFFFFFFFL) >>> 16);
                    }
                    final Random rand = new Random();
                    for (bookshelvesNeeded = 0; bookshelvesNeeded <= 15; ++bookshelvesNeeded) {
                        rand.setSeed(xpSeed);
                        for (slot = 0; slot < 3; ++slot) {
                            int level = Enchantments.calcEnchantmentTableLevel(rand, slot, bookshelvesNeeded, item);
                            if (level < slot + 1) {
                                level = 0;
                            }
                            enchantLevels[slot] = level;
                        }
                        slot = 0;
                    Label_0414:
                        while (slot < 3) {
                            final List<Enchantments.EnchantmentInstance> enchantments = Enchantments.getEnchantmentsInTable(rand, xpSeed, item, slot, enchantLevels[slot]);
                            Enumeration<Enchantments.EnchantmentInstance> e2 = EnchCrackerWindow.this.wantedListModel.elements();
                        Label_0411:
                            while (true) {
                                while (e2.hasMoreElements()) {
                                    if (!enchantments.contains(e2.nextElement())) {
                                        ++slot;
                                        continue Label_0414;
                                    }
                                }
                                e2 = EnchCrackerWindow.this.unwantedListModel.elements();
                                while (e2.hasMoreElements()) {
                                    if (enchantments.contains(e2.nextElement())) {
                                        continue Label_0411;
                                    }
                                }
                                break;
                            }
                            timesNeeded = i;
                            break Label_0476;
                        }
                    }
                    if (i != -1) {
                        for (int j = 0; j < 4; ++j) {
                            seed = (seed * 25214903917L + 11L & 0xFFFFFFFFFFFFL);
                        }
                    }
                }
                EnchCrackerWindow.access$17(EnchCrackerWindow.this, timesNeeded);
                if (timesNeeded == -2) {
                    Log.info("Impossible combination");
                    EnchCrackerWindow.this.manipulateOutput.setText("Impossible");
                }
                else if (timesNeeded == -1) {
                    Log.info("No dummy, b = " + bookshelvesNeeded + ", s = " + (slot + 1));
                    EnchCrackerWindow.this.manipulateOutput.setText("No dummy; b: " + bookshelvesNeeded + ", s: " + (slot + 1));
                }
                else {
                    Log.info("Throw " + timesNeeded + " items, b = " + bookshelvesNeeded + ", s = " + (slot + 1));
                    EnchCrackerWindow.this.manipulateOutput.setText("Throw " + timesNeeded + " (" + timesNeeded / 64 + ":" + timesNeeded % 64 + ") items; b: " + bookshelvesNeeded + ", s: " + (slot + 1));
                }
            }
        });
        panel_20.add(btnCalculate_1);
        panel_20.add(this.manipulateOutput = new JLabel("Not calculated"));
        final JButton btnDone = new JButton("Done");
        btnDone.setToolTipText("Press to let the Cracker know that you have gone for these enchantments");
        btnDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                Log.info("Enchanted and applied changes");
                if (EnchCrackerWindow.this.timesNeeded == -2) {
                    return;
                }
                if (EnchCrackerWindow.this.timesNeeded != -1) {
                    for (int i = 0; i < EnchCrackerWindow.this.timesNeeded; ++i) {
                        for (int j = 0; j < 4; ++j) {
                            EnchCrackerWindow.access$11(EnchCrackerWindow.this, EnchCrackerWindow.this.playerSeed * 25214903917L + 11L & 0xFFFFFFFFFFFFL);
                        }
                    }
                    EnchCrackerWindow.access$11(EnchCrackerWindow.this, EnchCrackerWindow.this.playerSeed * 25214903917L + 11L & 0xFFFFFFFFFFFFL);
                }
                EnchCrackerWindow.access$11(EnchCrackerWindow.this, EnchCrackerWindow.this.playerSeed * 25214903917L + 11L & 0xFFFFFFFFFFFFL);
                EnchCrackerWindow.access$17(EnchCrackerWindow.this, -2);
                EnchCrackerWindow.this.manipulateOutput.setText("Not calculated");
            }
        });
        panel_20.add(btnDone);
        final JPanel panel_21 = new JPanel();
        panel_19.add(panel_21);
        final JLabel lblEnchantment = new JLabel("Enchantment:");
        lblEnchantment.setToolTipText("The enchantment ID (see the wiki) followed optionally by the level.");
        panel_21.add(lblEnchantment);
        (this.enchantmentTextField = new FixedTextField()).setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_21.add(this.enchantmentTextField);
        this.enchantmentTextField.setColumns(10);
        final Component horizontalStrut = Box.createHorizontalStrut(30);
        panel_21.add(horizontalStrut);
        final JPanel panel_22 = new JPanel();
        panel_21.add(panel_22);
        panel_22.setLayout(new GridLayout(0, 2, 0, 0));
        final JButton btnWanted = new JButton("Wanted");
        btnWanted.setToolTipText("This enchantment is wanted in the list of enchantments on the item");
        panel_22.add(btnWanted);
        final JButton btnNotWanted = new JButton("Not Wanted");
        btnNotWanted.setToolTipText("This enchantment is not wanted in the list of enchantments on the item");
        panel_22.add(btnNotWanted);
        final JButton btnDontCare = new JButton("Don't Care");
        btnDontCare.setToolTipText("It doesn't matter whether this enchantment is in the list of enchantments on the item");
        panel_22.add(btnDontCare);
        final JButton btnClear = new JButton("Clear");
        btnClear.setToolTipText("Remove all wanted and unwanted enchantments");
        panel_22.add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                EnchCrackerWindow.this.wantedListModel.removeAllElements();
                EnchCrackerWindow.this.unwantedListModel.removeAllElements();
            }
        });
        btnDontCare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final List<Enchantments.EnchantmentInstance> enchantments = Enchantments.parseEnchantmentInstance(EnchCrackerWindow.this.itemTextField.getText(), EnchCrackerWindow.this.enchantmentTextField.getText(), false);
                if (!enchantments.isEmpty()) {
                    EnchCrackerWindow.this.enchantmentTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    EnchCrackerWindow.this.enchantmentTextField.setText("");
                    for (final Enchantments.EnchantmentInstance enchantment : enchantments) {
                        EnchCrackerWindow.this.wantedListModel.removeElement(enchantment);
                        EnchCrackerWindow.this.unwantedListModel.removeElement(enchantment);
                    }
                }
                else {
                    EnchCrackerWindow.this.enchantmentTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });
        btnNotWanted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final List<Enchantments.EnchantmentInstance> enchantments = Enchantments.parseEnchantmentInstance(EnchCrackerWindow.this.itemTextField.getText(), EnchCrackerWindow.this.enchantmentTextField.getText(), false);
                if (!enchantments.isEmpty()) {
                    EnchCrackerWindow.this.enchantmentTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    EnchCrackerWindow.this.enchantmentTextField.setText("");
                    for (final Enchantments.EnchantmentInstance enchantment : enchantments) {
                        EnchCrackerWindow.this.wantedListModel.removeElement(enchantment);
                        if (!EnchCrackerWindow.this.unwantedListModel.contains(enchantment)) {
                            EnchCrackerWindow.this.unwantedListModel.addElement(enchantment);
                        }
                    }
                }
                else {
                    EnchCrackerWindow.this.enchantmentTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });
        btnWanted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final List<Enchantments.EnchantmentInstance> unwantedEnchantments = Enchantments.parseEnchantmentInstance(EnchCrackerWindow.this.itemTextField.getText(), EnchCrackerWindow.this.enchantmentTextField.getText(), false);
                final List<Enchantments.EnchantmentInstance> wantedEnchantments = Enchantments.parseEnchantmentInstance(EnchCrackerWindow.this.itemTextField.getText(), EnchCrackerWindow.this.enchantmentTextField.getText(), true);
                if (!wantedEnchantments.isEmpty()) {
                    EnchCrackerWindow.this.enchantmentTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    EnchCrackerWindow.this.enchantmentTextField.setText("");
                    for (final Enchantments.EnchantmentInstance enchantment : unwantedEnchantments) {
                        EnchCrackerWindow.this.unwantedListModel.removeElement(enchantment);
                    }
                    for (final Enchantments.EnchantmentInstance enchantment : wantedEnchantments) {
                        if (!EnchCrackerWindow.this.wantedListModel.contains(enchantment)) {
                            EnchCrackerWindow.this.wantedListModel.addElement(enchantment);
                        }
                    }
                }
                else {
                    EnchCrackerWindow.this.enchantmentTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });
        final JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.5);
        splitPane.setAlignmentX(0.5f);
        panel_19.add(splitPane);
        final JPanel panel_23 = new JPanel();
        splitPane.setLeftComponent(panel_23);
        panel_23.setLayout(new BoxLayout(panel_23, 1));
        final JLabel lblWanted = new JLabel("Wanted:");
        panel_23.add(lblWanted);
        final JList list = createWantedEnchantmentList();
        this.wantedListModel = (DefaultListModel<Enchantments.EnchantmentInstance>)(DefaultListModel)list.getModel();
        panel_23.add(list);
        final JPanel panel_24 = new JPanel();
        splitPane.setRightComponent(panel_24);
        panel_24.setLayout(new BoxLayout(panel_24, 1));
        final JLabel lblNotWanted = new JLabel("Not Wanted:");
        panel_24.add(lblNotWanted);
        final JList list_1 = createUnwantedEnchantmentList();
        this.unwantedListModel = (DefaultListModel<Enchantments.EnchantmentInstance>)(DefaultListModel)list_1.getModel();
        panel_24.add(list_1);
        final JPanel panel_25 = new JPanel();
        tabbedPane.addTab("About", null, panel_25, null);
        panel_25.setLayout(new BoxLayout(panel_25, 1));
        final JPanel panel_26 = new JPanel();
        panel_26.setAlignmentX(0.0f);
        panel_25.add(panel_26);
        panel_26.setLayout(new BoxLayout(panel_26, 0));
        final JLabel lblEnchantmentCrackerWritten = new JLabel("Enchantment Cracker, written by Earthcomputer");
        panel_26.add(lblEnchantmentCrackerWritten);
        final JPanel panel_27 = new JPanel();
        panel_27.setAlignmentX(0.0f);
        panel_25.add(panel_27);
        panel_27.setLayout(new BoxLayout(panel_27, 0));
        final JLabel lblTutorialAndExplanation = new JLabel("Tutorial and Explanation: ");
        panel_27.add(lblTutorialAndExplanation);
        final JLabel lblNewLabel = new JLabel("<html><a href=\"https://youtu.be/hfiTZF0hlzw\">Minecraft, Vanilla Survival: Cracking the Enchantment Seed</a></html>");
        lblNewLabel.setToolTipText("https://youtu.be/hfiTZF0hlzw");
        panel_27.add(lblNewLabel);
        lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent arg0) {
                browse("https://youtu.be/hfiTZF0hlzw");
            }
        });
        lblNewLabel.setCursor(Cursor.getPredefinedCursor(12));
        final Component verticalStrut = Box.createVerticalStrut(20);
        panel_25.add(verticalStrut);
        final JPanel panel_28 = new JPanel();
        panel_28.setAlignmentX(0.0f);
        panel_25.add(panel_28);
        panel_28.setLayout(new BoxLayout(panel_28, 0));
        final JLabel lblGithubPage = new JLabel("GitHub page: ");
        panel_28.add(lblGithubPage);
        final JLabel label = new JLabel("<html><a href = \"https://github.com/Earthcomputer/EnchantmentCracker\">Earthcomputer/EnchantmentCracker</a></html>");
        label.setToolTipText("https://github.com/Earthcomputer/EnchantmentCracker");
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent arg0) {
                browse("https://github.com/Earthcomputer/EnchantmentCracker");
            }
        });
        label.setCursor(Cursor.getPredefinedCursor(12));
        panel_28.add(label);
        final JPanel panel_29 = new JPanel();
        panel_29.setAlignmentX(0.0f);
        panel_25.add(panel_29);
        panel_29.setLayout(new BoxLayout(panel_29, 0));
        final JLabel lblPleaseReportAny = new JLabel("Please report any bugs you find on the issue tracker on the GitHub page.");
        panel_29.add(lblPleaseReportAny);
        final JPanel panel_30 = new JPanel();
        panel_30.setAlignmentX(0.0f);
        panel_25.add(panel_30);
        panel_30.setLayout(new BoxLayout(panel_30, 0));
        final JLabel lblMakeSureTo = new JLabel("Make sure to include the log file (enchcracker.log) in the bug report.");
        panel_30.add(lblMakeSureTo);
        this.progressBar = new JProgressBar();
        this.contentPane.add(this.progressBar);
    }
    
    public static JList createWantedEnchantmentList() {
        final JList list = new JList();
        list.setModel(new DefaultListModel());
        return list;
    }
    
    public static JList createUnwantedEnchantmentList() {
        final JList list = new JList();
        list.setModel(new DefaultListModel());
        return list;
    }
    
    private static void browse(final String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        }
        catch (Exception e) {
            Log.warn("Error browsing to " + url, e);
        }
    }
    
    static /* synthetic */ void access$11(final EnchCrackerWindow enchCrackerWindow, final long playerSeed) {
        enchCrackerWindow.playerSeed = playerSeed;
    }
    
    static /* synthetic */ void access$17(final EnchCrackerWindow enchCrackerWindow, final int timesNeeded) {
        enchCrackerWindow.timesNeeded = timesNeeded;
    }
}
