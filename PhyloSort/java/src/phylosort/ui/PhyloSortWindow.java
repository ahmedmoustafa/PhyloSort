/*
 * $Id: PhyloSortWindow.java,v 1.42 2008/06/20 22:24:28 ahmed Exp $
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package phylosort.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import phylosort.PhyloCluster;
import phylosort.PhyloSort;
import phylosort.TreeCluster;
import phylosort.TreeNode;
import phylosort.TreeNodeUtil;
import phylosort.ui.logging.DocumentHandler;
import phylosort.ui.logging.SingleLogger;
import phylosort.util.Commons;
import phylosort.util.Config;
import forester.atv.ATVjframe;
import forester.tree.Tree;
import forester.tree.TreeHelper;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Graphical user inteface for PhyloSort.
 * 
 * @author Ahmed Moustafa
 * @version $Revision: 1.42 $
 */
public class PhyloSortWindow extends javax.swing.JFrame {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 3257844376876364850L;
    /**
     * Window width
     */
    private static final int WINDOW_WIDTH = 800;
    /**
     * Window height
     */
    private static final int WINDOW_HEIGHT = 600;
    /**
     * Logger
     */
    private static final Logger logger = SingleLogger.getLogger();
    private final MutableList sourceList = new MutableList();
    private String configFile = Config.SYSTEM_PROPERTY_PHYLOSORT_CONFIG;
    /**
     * Reference tree frame
     */
    private TreeWindow treeWindow = null;
    private int groupIndex = 0;    // The actions
    private int optionGroupIndex = 0;
    public Action nextFocusAction = new AbstractAction("Move Focus Forwards") {

        /**
         * 
         */
        private static final long serialVersionUID = 3763091972940183858L;

        public void actionPerformed(ActionEvent evt) {
            ((Component) evt.getSource()).transferFocus();
        }
    };
    public Action prevFocusAction = new AbstractAction("Move Focus Backwards") {

        /**
         * 
         */
        private static final long serialVersionUID = 3257844402628997943L;

        public void actionPerformed(ActionEvent evt) {
            ((Component) evt.getSource()).transferFocusBackward();
        }
    };

    /**
     * Constructor
     */
    public PhyloSortWindow() {

        initComponents();

        initComponents2();

        logger.addHandler(new DocumentHandler(jTextPaneConsole));

        try {
            // Set the icon for the frame
            URL url = getClass().getResource("/phylosort/ui/images/phylosort.png");
            if (url != null) {
                Image image = java.awt.Toolkit.getDefaultToolkit().getImage(url);
                setIconImage(image);
            } else {
                logger.warning("Image URL is NULL");
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed setting the frame image: " + e.getMessage(), e);
        }

        treeWindow = new TreeWindow();
        treeWindow.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jCheckBoxReference.setSelected(false);
            }
        });
        treeWindow.addSelectionListener(new SelectionListener() {

            public void selected(List<String> items) {
                sourceList.setSelectedItems(items);
                TabbedList tabbedList = null;
                for (int i = 0, n = jTabbedPaneQueryGroups.getComponentCount(); i < n; i++) {
                    tabbedList = (TabbedList) jTabbedPaneQueryGroups.getComponentAt(i);
                    MutableList mutableList = tabbedList.getList();
                    mutableList.setSelectedItems(items);
                }
            }
        });

        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(null);

        // Set the frame size
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jDialogTreeBrowser = new javax.swing.JDialog();
        jFileChooserTrees = new javax.swing.JFileChooser();
        jDialogTreeCluster = new javax.swing.JDialog();
        jLabelTreeClusterInput = new javax.swing.JLabel();
        jTextFieldTreeClusterInput = new javax.swing.JTextField();
        jButtonTreeClusterInput = new javax.swing.JButton();
        jLabelTreeClusterOutput = new javax.swing.JLabel();
        jTextFieldTreeClusterOutput = new javax.swing.JTextField();
        jButtonTreeClusterOutput = new javax.swing.JButton();
        jLabelTreeClusterMinimum = new javax.swing.JLabel();
        jSpinnerTreeClusterMinimum = new javax.swing.JSpinner();
        jPanelTreeClusterButtons = new javax.swing.JPanel();
        jButtonTreeClusterGo = new javax.swing.JButton();
        jButtonTreeClusterCancel = new javax.swing.JButton();
        buttonGroupTaxaSource = new javax.swing.ButtonGroup();
        buttonGroupType = new javax.swing.ButtonGroup();
        jPanelSettings = new javax.swing.JPanel();
        jPanelMinTaxa = new javax.swing.JPanel();
        jLabelMinTaxa = new javax.swing.JLabel();
        jTextFieldMinTaxa = new javax.swing.JTextField();
        jPanelMaxTaxa = new javax.swing.JPanel();
        jLabelMaxTaxa = new javax.swing.JLabel();
        jTextFieldMaxTaxa = new javax.swing.JTextField();
        jPanelMinBootstrap = new javax.swing.JPanel();
        jLabelMinBootstrap = new javax.swing.JLabel();
        jTextFieldMinBoot = new javax.swing.JTextField();
        jPanelMaxNumGenes = new javax.swing.JPanel();
        jLabelMaximumNumberOfCopies = new javax.swing.JLabel();
        jTextFieldMaximumNumberOfCopies = new javax.swing.JTextField();
        jPanelRootByOutgroup = new javax.swing.JPanel();
        jCheckBoxRoot = new javax.swing.JCheckBox();
        jPanelMode = new javax.swing.JPanel();
        jCheckBoxExclusive = new javax.swing.JCheckBox();
        jPanelOnMatch = new javax.swing.JPanel();
        jLabelOnMatch = new javax.swing.JLabel();
        jComboBoxOnMatch = new javax.swing.JComboBox();
        jPanelTaxaRegexp = new javax.swing.JPanel();
        jLabelRegexp = new javax.swing.JLabel();
        jComboBoxRegexp = new javax.swing.JComboBox();
        jPanelButtonsAcceptReject = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jPanelReferenceTaxonomy = new javax.swing.JPanel();
        jCheckBoxReference = new javax.swing.JCheckBox();
        jPanelBody = new javax.swing.JPanel();
        jPanelControls = new javax.swing.JPanel();
        jPanelInputDirectory = new javax.swing.JPanel();
        jButtonInputDirectory = new javax.swing.JButton();
        jTextFieldInputDirectory = new javax.swing.JTextField();
        jPanelOutputDirectory = new javax.swing.JPanel();
        jButtonOutputDirectory = new javax.swing.JButton();
        jTextFieldOutputDirectory = new javax.swing.JTextField();
        jPanelExecute = new javax.swing.JPanel();
        jButtonExecute = new javax.swing.JButton();
        jPanelCluster = new javax.swing.JPanel();
        jPanelExit = new javax.swing.JPanel();
        jButtonGoPlusPlus = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jSplitPaneTop = new javax.swing.JSplitPane();
        jPanelInputTaxa = new javax.swing.JPanel();
        jLabelInputTaxa = new javax.swing.JLabel();
        jPanelTaxaButtons = new javax.swing.JPanel();
        jButtonTaxaAdd = new javax.swing.JButton();
        jButtonTaxaRemove = new javax.swing.JButton();
        jRadioButtonQuery = new javax.swing.JRadioButton();
        jRadioButtonOptional = new javax.swing.JRadioButton();
        jPanelTaxaSource = new javax.swing.JPanel();
        jPanelTaxaSourceButtons = new javax.swing.JPanel();
        jRadioButtonTree = new javax.swing.JRadioButton();
        jRadioButtonForest = new javax.swing.JRadioButton();
        jRadioButtonPlain = new javax.swing.JRadioButton();
        jButtonTaxaOpen = new javax.swing.JButton();
        jTextFieldTaxaOpen = new javax.swing.JTextField();
        jButtonTaxaReload = new javax.swing.JButton();
        jSplitPaneGroups = new javax.swing.JSplitPane();
        jPanelQueryTaxa = new javax.swing.JPanel();
        jLabelGroups = new javax.swing.JLabel();
        jTabbedPaneQueryGroups = new javax.swing.JTabbedPane();
        jPanelGroupButtons = new javax.swing.JPanel();
        jButtonGroupAdd = new javax.swing.JButton();
        jButtonGroupRemove = new javax.swing.JButton();
        jTextFieldGroupRename = new javax.swing.JTextField();
        jButtonGroupRename = new javax.swing.JButton();
        jPanelOptionalTaxa = new javax.swing.JPanel();
        jLabelOptionalGroups = new javax.swing.JLabel();
        jTabbedPaneOptionalGroups = new javax.swing.JTabbedPane();
        jPanelOptionalGroupButtons = new javax.swing.JPanel();
        jButtonOptionalGroupAdd = new javax.swing.JButton();
        jButtonOptionalGroupRemove = new javax.swing.JButton();
        jTextFieldOptionalGroupRename = new javax.swing.JTextField();
        jButtonOptionalGroupRename = new javax.swing.JButton();
        jPanelConsole = new javax.swing.JPanel();
        jLabelConsole = new javax.swing.JLabel();
        jScrollPaneConsole = new javax.swing.JScrollPane();
        jTextPaneConsole = new javax.swing.JTextPane();
        jPanelConsoleEdit = new javax.swing.JPanel();
        jButtonConsoleClear = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemFileLoadConfig = new javax.swing.JMenuItem();
        jMenuItemFileSaveConfig = new javax.swing.JMenuItem();
        jSeparatorFile = new javax.swing.JSeparator();
        jMenuItemFileExit = new javax.swing.JMenuItem();
        jMenuTools = new javax.swing.JMenu();
        jMenuItemTreeBrowser = new javax.swing.JMenuItem();
        jMenuItemTreeCluster = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();

        jDialogTreeBrowser.setTitle("Tree Browser");

        jFileChooserTrees.setDialogTitle("Trees browser");
        jFileChooserTrees.setMultiSelectionEnabled(true);
        jFileChooserTrees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooserTreesActionPerformed(evt);
            }
        });
        jDialogTreeBrowser.getContentPane().add(jFileChooserTrees, java.awt.BorderLayout.CENTER);

        jDialogTreeCluster.setTitle("Tree Cluster");
        jDialogTreeCluster.getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabelTreeClusterInput.setText("Input trees");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jDialogTreeCluster.getContentPane().add(jLabelTreeClusterInput, gridBagConstraints);

        jTextFieldTreeClusterInput.setColumns(10);
        jTextFieldTreeClusterInput.setEditable(false);
        jTextFieldTreeClusterInput.setToolTipText("Input folder");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jDialogTreeCluster.getContentPane().add(jTextFieldTreeClusterInput, gridBagConstraints);

        jButtonTreeClusterInput.setText("Browse...");
        jButtonTreeClusterInput.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButtonTreeClusterInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreeClusterInputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jDialogTreeCluster.getContentPane().add(jButtonTreeClusterInput, gridBagConstraints);

        jLabelTreeClusterOutput.setText("Output clusters");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jDialogTreeCluster.getContentPane().add(jLabelTreeClusterOutput, gridBagConstraints);

        jTextFieldTreeClusterOutput.setColumns(10);
        jTextFieldTreeClusterOutput.setEditable(false);
        jTextFieldTreeClusterOutput.setToolTipText("Output folder");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jDialogTreeCluster.getContentPane().add(jTextFieldTreeClusterOutput, gridBagConstraints);

        jButtonTreeClusterOutput.setText("Browse...");
        jButtonTreeClusterOutput.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButtonTreeClusterOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreeClusterOutputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jDialogTreeCluster.getContentPane().add(jButtonTreeClusterOutput, gridBagConstraints);

        jLabelTreeClusterMinimum.setText("Minimum number of overlaps");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jDialogTreeCluster.getContentPane().add(jLabelTreeClusterMinimum, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jDialogTreeCluster.getContentPane().add(jSpinnerTreeClusterMinimum, gridBagConstraints);

        jPanelTreeClusterButtons.setLayout(new javax.swing.BoxLayout(jPanelTreeClusterButtons, javax.swing.BoxLayout.LINE_AXIS));

        jButtonTreeClusterGo.setText("Cluster");
        jButtonTreeClusterGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreeClusterGoActionPerformed(evt);
            }
        });
        jPanelTreeClusterButtons.add(jButtonTreeClusterGo);

        jButtonTreeClusterCancel.setText("Cancel");
        jButtonTreeClusterCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTreeClusterCancelActionPerformed(evt);
            }
        });
        jPanelTreeClusterButtons.add(jButtonTreeClusterCancel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jDialogTreeCluster.getContentPane().add(jPanelTreeClusterButtons, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PhyloSort");
        setName("PhyloSortWindow"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanelSettings.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
        jPanelSettings.setLayout(new java.awt.GridLayout(0, 1));

        jPanelMinTaxa.setLayout(new java.awt.BorderLayout());

        jLabelMinTaxa.setText("Min # of taxa");
        jPanelMinTaxa.add(jLabelMinTaxa, java.awt.BorderLayout.NORTH);

        jTextFieldMinTaxa.setColumns(5);
        jTextFieldMinTaxa.setToolTipText("Minimum number of taxa in the tree");
        jPanelMinTaxa.add(jTextFieldMinTaxa, java.awt.BorderLayout.CENTER);

        jPanelSettings.add(jPanelMinTaxa);

        jPanelMaxTaxa.setLayout(new java.awt.BorderLayout());

        jLabelMaxTaxa.setText("Max # of taxa");
        jPanelMaxTaxa.add(jLabelMaxTaxa, java.awt.BorderLayout.NORTH);

        jTextFieldMaxTaxa.setColumns(5);
        jTextFieldMaxTaxa.setToolTipText("Maximum number of taxa in the tree");
        jPanelMaxTaxa.add(jTextFieldMaxTaxa, java.awt.BorderLayout.CENTER);

        jPanelSettings.add(jPanelMaxTaxa);

        jPanelMinBootstrap.setLayout(new java.awt.BorderLayout());

        jLabelMinBootstrap.setText("Min bootstrap");
        jPanelMinBootstrap.add(jLabelMinBootstrap, java.awt.BorderLayout.NORTH);

        jTextFieldMinBoot.setColumns(5);
        jTextFieldMinBoot.setToolTipText("Minimum bootstrap support on the matching monophyletic clade");
        jPanelMinBootstrap.add(jTextFieldMinBoot, java.awt.BorderLayout.CENTER);

        jPanelSettings.add(jPanelMinBootstrap);

        jPanelMaxNumGenes.setLayout(new java.awt.BorderLayout());

        jLabelMaximumNumberOfCopies.setLabelFor(jTextFieldMaximumNumberOfCopies);
        jLabelMaximumNumberOfCopies.setText("Max avg # of genes");
        jPanelMaxNumGenes.add(jLabelMaximumNumberOfCopies, java.awt.BorderLayout.NORTH);

        jTextFieldMaximumNumberOfCopies.setColumns(5);
        jTextFieldMaximumNumberOfCopies.setToolTipText("Maximum average number of genes per taxon in the tree");
        jPanelMaxNumGenes.add(jTextFieldMaximumNumberOfCopies, java.awt.BorderLayout.CENTER);

        jPanelSettings.add(jPanelMaxNumGenes);

        jPanelRootByOutgroup.setLayout(new java.awt.BorderLayout());

        jCheckBoxRoot.setText("Root by outgroup");
        jCheckBoxRoot.setToolTipText("Outgroup will be automatically identified in the tree and the tree will be rerooted on that outgroup");
        jCheckBoxRoot.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanelRootByOutgroup.add(jCheckBoxRoot, java.awt.BorderLayout.CENTER);

        jPanelSettings.add(jPanelRootByOutgroup);

        jPanelMode.setLayout(new java.awt.BorderLayout());

        jCheckBoxExclusive.setText("Exclusive mode");
        jCheckBoxExclusive.setToolTipText("<html>\nMonophyly constraint applies on all instances of monophyletic taxa in the tree\n<br>\n(for example, if one query taxon shows in the tree more than once in two different clades,\n<br>\nthe monophyletic requirement will be applied on each clade that query taxon exists)\n</html>");
        jCheckBoxExclusive.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanelMode.add(jCheckBoxExclusive, java.awt.BorderLayout.CENTER);

        jPanelSettings.add(jPanelMode);

        jPanelOnMatch.setLayout(new java.awt.BorderLayout());

        jLabelOnMatch.setText("On match");
        jPanelOnMatch.add(jLabelOnMatch, java.awt.BorderLayout.NORTH);

        jComboBoxOnMatch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Count", "Copy", "Move" }));
        jComboBoxOnMatch.setToolTipText("Action to be taken when a matching tree is found");
        jPanelOnMatch.add(jComboBoxOnMatch, java.awt.BorderLayout.PAGE_END);

        jPanelSettings.add(jPanelOnMatch);

        jPanelTaxaRegexp.setLayout(new java.awt.BorderLayout());

        jLabelRegexp.setText("Taxa regexp");
        jPanelTaxaRegexp.add(jLabelRegexp, java.awt.BorderLayout.NORTH);

        jComboBoxRegexp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxRegexp.setToolTipText("Regular expression to extract taxon name from node labels (for example, when sequence names include the species name and gene accession number)");
        jPanelTaxaRegexp.add(jComboBoxRegexp, java.awt.BorderLayout.CENTER);

        jPanelSettings.add(jPanelTaxaRegexp);

        jButtonOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/ok.png"))); // NOI18N
        jButtonOK.setToolTipText("Accept settings");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });
        jPanelButtonsAcceptReject.add(jButtonOK);

        jButtonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/cancel.png"))); // NOI18N
        jButtonCancel.setToolTipText("Reject settings");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jPanelButtonsAcceptReject.add(jButtonCancel);

        jPanelSettings.add(jPanelButtonsAcceptReject);

        jPanelReferenceTaxonomy.setLayout(new java.awt.BorderLayout());

        jCheckBoxReference.setText("Reference taxonomy");
        jCheckBoxReference.setToolTipText("Show reference taxonomy tree");
        jCheckBoxReference.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxReference.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxReferenceActionPerformed(evt);
            }
        });
        jPanelReferenceTaxonomy.add(jCheckBoxReference, java.awt.BorderLayout.CENTER);

        jPanelSettings.add(jPanelReferenceTaxonomy);

        getContentPane().add(jPanelSettings, java.awt.BorderLayout.EAST);

        jPanelBody.setLayout(new java.awt.BorderLayout());

        jPanelControls.setLayout(new java.awt.GridBagLayout());

        jButtonInputDirectory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/open.png"))); // NOI18N
        jButtonInputDirectory.setMnemonic('I');
        jButtonInputDirectory.setText("In");
        jButtonInputDirectory.setToolTipText("Select input folder...");
        jButtonInputDirectory.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButtonInputDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInputDirectoryActionPerformed(evt);
            }
        });
        jPanelInputDirectory.add(jButtonInputDirectory);

        jTextFieldInputDirectory.setColumns(8);
        jTextFieldInputDirectory.setEditable(false);
        jTextFieldInputDirectory.setToolTipText("Input folder");
        jPanelInputDirectory.add(jTextFieldInputDirectory);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanelControls.add(jPanelInputDirectory, gridBagConstraints);

        jButtonOutputDirectory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/open.png"))); // NOI18N
        jButtonOutputDirectory.setMnemonic('O');
        jButtonOutputDirectory.setText("Out");
        jButtonOutputDirectory.setToolTipText("Select output folder...");
        jButtonOutputDirectory.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButtonOutputDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOutputDirectoryActionPerformed(evt);
            }
        });
        jPanelOutputDirectory.add(jButtonOutputDirectory);

        jTextFieldOutputDirectory.setColumns(8);
        jTextFieldOutputDirectory.setEditable(false);
        jTextFieldOutputDirectory.setToolTipText("Output folder");
        jPanelOutputDirectory.add(jTextFieldOutputDirectory);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanelControls.add(jPanelOutputDirectory, gridBagConstraints);

        jPanelExecute.setLayout(new java.awt.BorderLayout());

        jButtonExecute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/exec.png"))); // NOI18N
        jButtonExecute.setMnemonic('G');
        jButtonExecute.setText("Go");
        jButtonExecute.setToolTipText("Search for monophyletic clades");
        jButtonExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecuteActionPerformed(evt);
            }
        });
        jPanelExecute.add(jButtonExecute, java.awt.BorderLayout.EAST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanelControls.add(jPanelExecute, gridBagConstraints);

        jPanelCluster.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanelControls.add(jPanelCluster, gridBagConstraints);

        jPanelExit.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanelControls.add(jPanelExit, gridBagConstraints);

        jButtonGoPlusPlus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/exec.png"))); // NOI18N
        jButtonGoPlusPlus.setMnemonic('+');
        jButtonGoPlusPlus.setText("Go++");
        jButtonGoPlusPlus.setToolTipText("\"auto\" Search for monophyletic clades");
        jButtonGoPlusPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGoPlusPlusActionPerformed(evt);
            }
        });
        jPanelControls.add(jButtonGoPlusPlus, new java.awt.GridBagConstraints());

        jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/exit.png"))); // NOI18N
        jButtonExit.setMnemonic('X');
        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });
        jPanelControls.add(jButtonExit, new java.awt.GridBagConstraints());

        jPanelBody.add(jPanelControls, java.awt.BorderLayout.NORTH);

        jSplitPaneTop.setResizeWeight(0.5);
        jSplitPaneTop.setAutoscrolls(true);
        jSplitPaneTop.setOneTouchExpandable(true);

        jPanelInputTaxa.setBorder(new javax.swing.border.LineBorder(java.awt.Color.green, 1, true));
        jPanelInputTaxa.setLayout(new java.awt.BorderLayout());

        jLabelInputTaxa.setText("Pool of taxa");
        jPanelInputTaxa.add(jLabelInputTaxa, java.awt.BorderLayout.NORTH);

        jPanelTaxaButtons.setLayout(new java.awt.GridBagLayout());

        jButtonTaxaAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/forward.png"))); // NOI18N
        jButtonTaxaAdd.setToolTipText("Add to group");
        jButtonTaxaAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTaxaAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanelTaxaButtons.add(jButtonTaxaAdd, gridBagConstraints);

        jButtonTaxaRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/back.png"))); // NOI18N
        jButtonTaxaRemove.setToolTipText("Remove from group");
        jButtonTaxaRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTaxaRemoveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanelTaxaButtons.add(jButtonTaxaRemove, gridBagConstraints);

        buttonGroupType.add(jRadioButtonQuery);
        jRadioButtonQuery.setForeground(java.awt.Color.blue);
        jRadioButtonQuery.setText("Query");
        jRadioButtonQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonQueryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanelTaxaButtons.add(jRadioButtonQuery, gridBagConstraints);

        buttonGroupType.add(jRadioButtonOptional);
        jRadioButtonOptional.setForeground(java.awt.Color.red);
        jRadioButtonOptional.setText("Optional");
        jRadioButtonOptional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOptionalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanelTaxaButtons.add(jRadioButtonOptional, gridBagConstraints);

        jPanelInputTaxa.add(jPanelTaxaButtons, java.awt.BorderLayout.EAST);

        jPanelTaxaSource.setBorder(javax.swing.BorderFactory.createTitledBorder("Taxa source"));
        jPanelTaxaSource.setLayout(new java.awt.BorderLayout());

        jPanelTaxaSourceButtons.setLayout(new java.awt.GridLayout(1, 0));

        buttonGroupTaxaSource.add(jRadioButtonTree);
        jRadioButtonTree.setText("Tree");
        jRadioButtonTree.setToolTipText("Load pool of taxa from a Newick-formatted tree");
        jRadioButtonTree.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanelTaxaSourceButtons.add(jRadioButtonTree);

        buttonGroupTaxaSource.add(jRadioButtonForest);
        jRadioButtonForest.setText("Forest");
        jRadioButtonForest.setToolTipText("Load pool of taxa from a folder with Newick-formatted trees");
        jRadioButtonForest.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanelTaxaSourceButtons.add(jRadioButtonForest);

        buttonGroupTaxaSource.add(jRadioButtonPlain);
        jRadioButtonPlain.setText("Plain");
        jRadioButtonPlain.setToolTipText("Load pool of taxa from a plain text file where taxa are separated with commas, spaces, tabs and/or new lines");
        jRadioButtonPlain.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanelTaxaSourceButtons.add(jRadioButtonPlain);

        jPanelTaxaSource.add(jPanelTaxaSourceButtons, java.awt.BorderLayout.NORTH);

        jButtonTaxaOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/open.png"))); // NOI18N
        jButtonTaxaOpen.setToolTipText("Load taxa");
        jButtonTaxaOpen.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButtonTaxaOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTaxaOpenActionPerformed(evt);
            }
        });
        jPanelTaxaSource.add(jButtonTaxaOpen, java.awt.BorderLayout.WEST);

        jTextFieldTaxaOpen.setColumns(8);
        jTextFieldTaxaOpen.setEditable(false);
        jTextFieldTaxaOpen.setToolTipText("Taxa source (file or directory)");
        jPanelTaxaSource.add(jTextFieldTaxaOpen, java.awt.BorderLayout.CENTER);

        jButtonTaxaReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/reload.png"))); // NOI18N
        jButtonTaxaReload.setToolTipText("Reload taxa");
        jButtonTaxaReload.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButtonTaxaReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTaxaReloadActionPerformed(evt);
            }
        });
        jPanelTaxaSource.add(jButtonTaxaReload, java.awt.BorderLayout.EAST);

        jPanelInputTaxa.add(jPanelTaxaSource, java.awt.BorderLayout.SOUTH);

        jSplitPaneTop.setLeftComponent(jPanelInputTaxa);

        jSplitPaneGroups.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPaneGroups.setOneTouchExpandable(true);

        jPanelQueryTaxa.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));
        jPanelQueryTaxa.setLayout(new java.awt.BorderLayout());

        jLabelGroups.setDisplayedMnemonic('Q');
        jLabelGroups.setLabelFor(jTabbedPaneQueryGroups);
        jLabelGroups.setText("Query groups");
        jPanelQueryTaxa.add(jLabelGroups, java.awt.BorderLayout.NORTH);
        jPanelQueryTaxa.add(jTabbedPaneQueryGroups, java.awt.BorderLayout.CENTER);

        jButtonGroupAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/add.png"))); // NOI18N
        jButtonGroupAdd.setToolTipText("Add group");
        jButtonGroupAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGroupAddActionPerformed(evt);
            }
        });
        jPanelGroupButtons.add(jButtonGroupAdd);

        jButtonGroupRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/remove.png"))); // NOI18N
        jButtonGroupRemove.setToolTipText("Remove group");
        jButtonGroupRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGroupRemoveActionPerformed(evt);
            }
        });
        jPanelGroupButtons.add(jButtonGroupRemove);

        jTextFieldGroupRename.setColumns(10);
        jTextFieldGroupRename.setToolTipText("Enter a new name for the selected group");
        jTextFieldGroupRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGroupRenameActionPerformed(evt);
            }
        });
        jTextFieldGroupRename.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldGroupRenameFocusGained(evt);
            }
        });
        jTextFieldGroupRename.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldGroupRenameKeyReleased(evt);
            }
        });
        jPanelGroupButtons.add(jTextFieldGroupRename);

        jButtonGroupRename.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/edit.png"))); // NOI18N
        jButtonGroupRename.setToolTipText("Rename group");
        jButtonGroupRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGroupRenameActionPerformed(evt);
            }
        });
        jButtonGroupRename.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButtonGroupRenameKeyTyped(evt);
            }
        });
        jPanelGroupButtons.add(jButtonGroupRename);

        jPanelQueryTaxa.add(jPanelGroupButtons, java.awt.BorderLayout.SOUTH);

        jSplitPaneGroups.setTopComponent(jPanelQueryTaxa);

        jPanelOptionalTaxa.setBorder(new javax.swing.border.LineBorder(java.awt.Color.red, 1, true));
        jPanelOptionalTaxa.setLayout(new java.awt.BorderLayout());

        jLabelOptionalGroups.setDisplayedMnemonic('O');
        jLabelOptionalGroups.setLabelFor(jTabbedPaneOptionalGroups);
        jLabelOptionalGroups.setText("Optional groups");
        jPanelOptionalTaxa.add(jLabelOptionalGroups, java.awt.BorderLayout.NORTH);
        jPanelOptionalTaxa.add(jTabbedPaneOptionalGroups, java.awt.BorderLayout.CENTER);

        jButtonOptionalGroupAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/add.png"))); // NOI18N
        jButtonOptionalGroupAdd.setToolTipText("Add group");
        jButtonOptionalGroupAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOptionalGroupAddActionPerformed(evt);
            }
        });
        jPanelOptionalGroupButtons.add(jButtonOptionalGroupAdd);

        jButtonOptionalGroupRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/remove.png"))); // NOI18N
        jButtonOptionalGroupRemove.setToolTipText("Remove group");
        jButtonOptionalGroupRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOptionalGroupRemoveActionPerformed(evt);
            }
        });
        jPanelOptionalGroupButtons.add(jButtonOptionalGroupRemove);

        jTextFieldOptionalGroupRename.setColumns(10);
        jTextFieldOptionalGroupRename.setToolTipText("Enter a new name for the selected group");
        jTextFieldOptionalGroupRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOptionalGroupRenameActionPerformed(evt);
            }
        });
        jTextFieldOptionalGroupRename.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldOptionalGroupRenameFocusGained(evt);
            }
        });
        jTextFieldOptionalGroupRename.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldOptionalGroupRenameKeyReleased(evt);
            }
        });
        jPanelOptionalGroupButtons.add(jTextFieldOptionalGroupRename);

        jButtonOptionalGroupRename.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/edit.png"))); // NOI18N
        jButtonOptionalGroupRename.setToolTipText("Rename group");
        jButtonOptionalGroupRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOptionalGroupRenameActionPerformed(evt);
            }
        });
        jButtonOptionalGroupRename.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jButtonOptionalGroupRenameKeyTyped(evt);
            }
        });
        jPanelOptionalGroupButtons.add(jButtonOptionalGroupRename);

        jPanelOptionalTaxa.add(jPanelOptionalGroupButtons, java.awt.BorderLayout.SOUTH);

        jSplitPaneGroups.setRightComponent(jPanelOptionalTaxa);

        jSplitPaneTop.setRightComponent(jSplitPaneGroups);

        jPanelBody.add(jSplitPaneTop, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelBody, java.awt.BorderLayout.CENTER);

        jPanelConsole.setLayout(new java.awt.BorderLayout());

        jLabelConsole.setText("Console");
        jPanelConsole.add(jLabelConsole, java.awt.BorderLayout.NORTH);

        jScrollPaneConsole.setPreferredSize(new java.awt.Dimension(400, 50));

        jTextPaneConsole.setEditable(false);
        jScrollPaneConsole.setViewportView(jTextPaneConsole);

        jPanelConsole.add(jScrollPaneConsole, java.awt.BorderLayout.CENTER);

        jPanelConsoleEdit.setLayout(new javax.swing.BoxLayout(jPanelConsoleEdit, javax.swing.BoxLayout.Y_AXIS));

        jButtonConsoleClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/clear.png"))); // NOI18N
        jButtonConsoleClear.setToolTipText("Clear console");
        jButtonConsoleClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsoleClearActionPerformed(evt);
            }
        });
        jPanelConsoleEdit.add(jButtonConsoleClear);

        jPanelConsole.add(jPanelConsoleEdit, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanelConsole, java.awt.BorderLayout.SOUTH);

        jMenuFile.setMnemonic('F');
        jMenuFile.setText("File");

        jMenuItemFileLoadConfig.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemFileLoadConfig.setMnemonic('L');
        jMenuItemFileLoadConfig.setText("Load Configuration...");
        jMenuItemFileLoadConfig.setToolTipText("Load PhyloSort configuration file");
        jMenuItemFileLoadConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFileLoadConfigActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemFileLoadConfig);

        jMenuItemFileSaveConfig.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemFileSaveConfig.setMnemonic('S');
        jMenuItemFileSaveConfig.setText("Save Configuration...");
        jMenuItemFileSaveConfig.setToolTipText("Save PhyloSort configuration file");
        jMenuItemFileSaveConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFileSaveConfigActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemFileSaveConfig);
        jMenuFile.add(jSeparatorFile);

        jMenuItemFileExit.setMnemonic('X');
        jMenuItemFileExit.setText("Exit");
        jMenuItemFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFileExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemFileExit);

        jMenuBar.add(jMenuFile);

        jMenuTools.setMnemonic('T');
        jMenuTools.setText("Tools");

        jMenuItemTreeBrowser.setMnemonic('B');
        jMenuItemTreeBrowser.setText("Tree Browser...");
        jMenuItemTreeBrowser.setToolTipText("Browsing and viewing trees using A Tree Viewer (ATV)");
        jMenuItemTreeBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTreeBrowserActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItemTreeBrowser);

        jMenuItemTreeCluster.setMnemonic('C');
        jMenuItemTreeCluster.setText("Tree Cluster...");
        jMenuItemTreeCluster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTreeClusterActionPerformed(evt);
            }
        });
        jMenuTools.add(jMenuItemTreeCluster);

        jMenuBar.add(jMenuTools);

        jMenuHelp.setMnemonic('H');
        jMenuHelp.setText("Help");

        jMenuItemAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItemAbout.setMnemonic('A');
        jMenuItemAbout.setText("About...");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButtonGoPlusPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGoPlusPlusActionPerformed

    logger.info("Go++");

    if (!validateSettings()) {
        return;
    }

    String in = jTextFieldInputDirectory.getText().trim();

    if (in.length() == 0) {
        showErrorMessage("Input directory is not set.");
        return;
    }

    File file;

    file = new File(in);
    if (!file.isDirectory()) {
        showErrorMessage("Invalid input directory: " + in);
    }

    if (!file.canRead()) {
        showErrorMessage("Cannot read input directory: " + in);
    }

    String out = jTextFieldOutputDirectory.getText().trim();

    if (Config.getInstance().getOnMatchAction().equalsIgnoreCase(Config.PROPERTY_ON_MATCH_ACTION_COPY) || Config.getInstance().getOnMatchAction().equalsIgnoreCase(Config.PROPERTY_ON_MATCH_ACTION_MOVE)) {

        if (out.length() == 0) {
            showErrorMessage("Output directory is not set.");
            return;
        }

        file = new File(out);
        if (!file.isDirectory()) {
            showErrorMessage("Invalid output directory: " + out);
            return;
        }

        if (!file.canWrite()) {
            showErrorMessage("Cannot write to output directory: " + out);
            return;
        }

        if (in.equalsIgnoreCase(out)) {
            showErrorMessage("Input and output directories are the same. Please change one of them.");
            return;
        }
    }

    TabbedList tab = null;

    List<List<String>> requiredGroups = new ArrayList<List<String>>();
    List<String> requiredNames = new ArrayList<String>();

    for (int i = 0, n = jTabbedPaneQueryGroups.getComponentCount(); i < n; i++) {
        tab = (TabbedList) jTabbedPaneQueryGroups.getComponentAt(i);
        MutableList list = tab.getList();
        if (list.getCount() > 0) {
            List<String> group = new ArrayList<String>();
            for (int j = 0, m = list.getCount(); j < m; j++) {
                group.add(list.get(j));
            }
            requiredGroups.add(group);
            requiredNames.add(jTabbedPaneQueryGroups.getTitleAt(i));
        }
    }

    List<List<String>> optionalGroups = new ArrayList<List<String>>();
    List<String> optionalNames = new ArrayList<String>();

    for (int i = 0, n = jTabbedPaneOptionalGroups.getComponentCount(); i < n; i++) {
        tab = (TabbedList) jTabbedPaneOptionalGroups.getComponentAt(i);
        MutableList list = tab.getList();
        if (list.getCount() > 0) {
            List<String> group = new ArrayList<String>();
            for (int j = 0, m = list.getCount(); j < m; j++) {
                group.add(list.get(j));
            }
            optionalGroups.add(group);
            optionalNames.add(jTabbedPaneOptionalGroups.getTitleAt(i));
        }
    }

    try {
        setCursor(new Cursor(Cursor.WAIT_CURSOR));

        Map<List<String>, Integer> results = PhyloSort.autoSort(in, out, requiredGroups, requiredNames, optionalGroups, optionalNames);
        List<List<String>> keys = new ArrayList<List<String>>();
        int rows = 0;
        int cols = 0;
        for (List<String> list : results.keySet()) {
            keys.add(list);
            rows++;
            if (list.size() > cols) {
                cols = list.size();
            }
        }
        Collections.sort(keys, new GroupComparator());

        String[] headers = new String[cols + 1];
        for (int i = 0; i < cols; i++) {
            headers[i] = "Group #" + (i + 1);
        }
        headers[cols] = "# of trees";

        final Object[][] data = new Object[rows][cols + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols; j++) {
                data[i][j] = null;
            }
        }

        int i = 0;
        for (List<String> list : keys) {
            int j = 0;
            for (String group : list) {
                data[i][j++] = group;
            }
            data[i++][cols] = results.get(list);
        }

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        //JTable table = new JTable(objects, headers);
        final JTable table = new JTable(new ResultsTableModel(data, headers));
        table.setPreferredScrollableViewportSize(new Dimension(800, 400));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setAutoscrolls(true);
        //table.setFillsViewportHeight(true);

        final JFrame frame = new JFrame("PhyloSort - Result");
        try {
            // Set the icon for the frame
            URL url = getClass().getResource("/phylosort/ui/images/phylosort.png");
            if (url != null) {
                Image image = java.awt.Toolkit.getDefaultToolkit().getImage(url);
                frame.setIconImage(image);
            } else {
                logger.warning("Image URL is NULL");
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed setting the frame image: " + e.getMessage(), e);
        }

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        frame.add(southPanel, BorderLayout.SOUTH);

        JButton printButton = new JButton(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/print.png")));
        printButton.setToolTipText("Print result");
        printButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    if (!table.print()) {
                        String msg = "Printing cancelled";
                        logger.info(msg);
                    }
                } catch (Exception e) {
                    String msg = "Failed printing results: " + e.getMessage();
                    showErrorMessage(msg);
                    logger.log(Level.SEVERE, msg, e);
                }
            }
        });


        JButton closeButton = new JButton(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/exit.png")));
        closeButton.setToolTipText("Close result");
        closeButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frame.setVisible(false);
                frame.dispose();
            }
        });

        JButton saveButton = new JButton(new javax.swing.ImageIcon(getClass().getResource("/phylosort/ui/images/save.png")));
        saveButton.setToolTipText("Save result");
        final int n = rows;
        final int m = cols;
        saveButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                File file = null;
                boolean done = false;
                boolean save = true;

                do {
                    file = saveFile(null);

                    if (file != null) {

                        if (file.exists()) {

                            String msg = "File already exists. Would you like to overwrite it?";

                            int option = JOptionPane.showConfirmDialog(null, msg, "PhyloSort - confirmation", JOptionPane.YES_NO_CANCEL_OPTION);

                            if (option == JOptionPane.CANCEL_OPTION) {
                                save = false;
                                done = true;
                            } else if (option == JOptionPane.YES_OPTION) {
                                save = true;
                                done = true;
                            } else if (option == JOptionPane.NO_OPTION) {
                            }
                        } else {
                            done = true;
                            save = true;
                        }
                    } else {
                        save = false;
                        done = true;
                    }
                } while (!done);

                if (save) {
                    BufferedWriter writer = null;
                    try {
                        StringBuffer buffer = new StringBuffer();
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                if (data[i][j] == null) {
                                    buffer.append("");
                                } else {
                                    buffer.append(data[i][j]);
                                }
                                buffer.append(Commons.TAB);
                            }
                            buffer.append(data[i][m]);
                            buffer.append(Commons.getLineSeparator());
                        }
                        writer = new BufferedWriter(new FileWriter(file));
                        writer.write(buffer.toString());
                    } catch (Exception e) {
                        String msg = "Failed saving PhyloSort result " + file.getName() + ": " + e.getMessage();
                        logger.log(Level.SEVERE, msg, e);
                        showErrorMessage(msg);
                    } finally {
                        if (writer != null) {
                            try {
                                writer.close();
                            } catch (Exception e) {
                                String msg = "Failed closing output file " + file.getName() + ": " + e.getMessage();
                                logger.log(Level.WARNING, msg, e);
                            }
                        }
                    }
                }

            }
        });

        southPanel.add(saveButton);
        southPanel.add(printButton);
        southPanel.add(closeButton);

        frame.pack();
        frame.setVisible(true);

    } catch (Exception e) {
        String msg = "Failed sorting trees: " + e.getMessage();
        logger.log(Level.SEVERE, msg, e);
        showErrorMessage(msg);
    } finally {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }


}//GEN-LAST:event_jButtonGoPlusPlusActionPerformed

private void jButtonGroupRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGroupRenameActionPerformed

    int index = jTabbedPaneQueryGroups.getSelectedIndex();

    if (index == -1) {
        showErrorMessage("No group is selected. Please select an query group first.");
        return;
    }

    String validGroupName = "Please provide a unique (case-insensitive) alpha-numeric word as a group name with a maximum of 20 characters.";

    String newTitle = jTextFieldGroupRename.getText();

    if (newTitle == null || newTitle.length() == 0) {
        showErrorMessage("Name is empty. " + validGroupName);
        return;
    }

    if (!newTitle.matches("^[A-Za-z0-9]+$")) {
        showErrorMessage("Name contains invalid character(s). " + validGroupName);
        return;
    }

    if (newTitle.length() > 20) {
        showErrorMessage("Name is too long. " + validGroupName);
        return;
    }

    for (int i = 0, n = jTabbedPaneQueryGroups.getTabCount(); i < n; i++) {
        if (i != index) {
            String currentTitle = jTabbedPaneQueryGroups.getTitleAt(i);
            if (currentTitle.equalsIgnoreCase(newTitle)) {
                showErrorMessage("Name is already used for query group #" + (i + 1) + ". " + validGroupName);
                return;
            }
        }
    }

    for (int i = 0, n = jTabbedPaneOptionalGroups.getTabCount(); i < n; i++) {
        String currentTitle = jTabbedPaneOptionalGroups.getTitleAt(i);
        if (currentTitle.equalsIgnoreCase(newTitle)) {
            showErrorMessage("Name is already used for optional group #" + (i + 1) + ". " + validGroupName);
            return;
        }
    }


    String oldTitle = jTabbedPaneQueryGroups.getTitleAt(index);
    jTabbedPaneQueryGroups.setTitleAt(index, newTitle);

    logger.info("Query group # " + (index + 1) + " was renamed from " + oldTitle + " to " + newTitle);

}//GEN-LAST:event_jButtonGroupRenameActionPerformed

private void jTextFieldGroupRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGroupRenameActionPerformed

    jButtonGroupRenameActionPerformed(evt);

}//GEN-LAST:event_jTextFieldGroupRenameActionPerformed

private void jButtonGroupRenameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonGroupRenameKeyTyped
}//GEN-LAST:event_jButtonGroupRenameKeyTyped

private void jTextFieldGroupRenameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldGroupRenameKeyReleased
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE || (evt.getModifiersEx() == InputEvent.CTRL_DOWN_MASK && evt.getKeyCode() == KeyEvent.VK_Z)) {
        jTextFieldGroupRename.setText(jTabbedPaneQueryGroups.getTitleAt(jTabbedPaneQueryGroups.getSelectedIndex()));
    }
}//GEN-LAST:event_jTextFieldGroupRenameKeyReleased

private void jTextFieldGroupRenameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldGroupRenameFocusGained
    jTextFieldGroupRename.setSelectionStart(0);
    jTextFieldGroupRename.setSelectionEnd(jTextFieldGroupRename.getText().length());
}//GEN-LAST:event_jTextFieldGroupRenameFocusGained

private void jButtonOptionalGroupAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOptionalGroupAddActionPerformed
    addOptionalGroup();
}//GEN-LAST:event_jButtonOptionalGroupAddActionPerformed

private void jButtonOptionalGroupRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOptionalGroupRemoveActionPerformed
    int selectedIndex = jTabbedPaneOptionalGroups.getSelectedIndex();
    if (selectedIndex != -1) {
        MutableList target = ((TabbedList) jTabbedPaneOptionalGroups.getSelectedComponent()).getList();
        while (target.getCount() > 0) {
            sourceList.add(target.get(0));
            target.remove(0);
        }
        jTabbedPaneOptionalGroups.removeTabAt(selectedIndex);
    }

    selectedIndex = jTabbedPaneOptionalGroups.getSelectedIndex();
    if (selectedIndex != -1) {
        MutableList target = ((TabbedList) jTabbedPaneOptionalGroups.getSelectedComponent()).getList();
        sourceList.setTarget(target);
    } else {
        sourceList.setTarget(null);
    }

}//GEN-LAST:event_jButtonOptionalGroupRemoveActionPerformed

private void jTextFieldOptionalGroupRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOptionalGroupRenameActionPerformed
    jButtonOptionalGroupRenameActionPerformed(evt);
}//GEN-LAST:event_jTextFieldOptionalGroupRenameActionPerformed

private void jTextFieldOptionalGroupRenameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldOptionalGroupRenameFocusGained
    jTextFieldOptionalGroupRename.setSelectionStart(0);
    jTextFieldOptionalGroupRename.setSelectionEnd(jTextFieldOptionalGroupRename.getText().length());
}//GEN-LAST:event_jTextFieldOptionalGroupRenameFocusGained

private void jTextFieldOptionalGroupRenameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldOptionalGroupRenameKeyReleased
    if (evt.getKeyCode() == KeyEvent.VK_ESCAPE || (evt.getModifiersEx() == InputEvent.CTRL_DOWN_MASK && evt.getKeyCode() == KeyEvent.VK_Z)) {
        jTextFieldOptionalGroupRename.setText(jTabbedPaneOptionalGroups.getTitleAt(jTabbedPaneOptionalGroups.getSelectedIndex()));
    }
}//GEN-LAST:event_jTextFieldOptionalGroupRenameKeyReleased

private void jButtonOptionalGroupRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOptionalGroupRenameActionPerformed

    int index = jTabbedPaneOptionalGroups.getSelectedIndex();

    if (index == -1) {
        showErrorMessage("No group is selected. Please select an optional group first.");
        return;
    }

    String validGroupName = "Please provide a unique (case-insensitive) alpha-numeric word as a group name with a maximum of 20 characters.";

    String newTitle = jTextFieldOptionalGroupRename.getText();

    if (newTitle == null || newTitle.length() == 0) {
        showErrorMessage("Name is empty. " + validGroupName);
        return;
    }

    if (!newTitle.matches("^[A-Za-z0-9]+$")) {
        showErrorMessage("Name contains invalid character(s). " + validGroupName);
        return;
    }

    if (newTitle.length() > 20) {
        showErrorMessage("Name is too long. " + validGroupName);
        return;
    }

    for (int i = 0, n = jTabbedPaneOptionalGroups.getTabCount(); i < n; i++) {
        if (i != index) {
            String currentTitle = jTabbedPaneOptionalGroups.getTitleAt(i);
            if (currentTitle.equalsIgnoreCase(newTitle)) {
                showErrorMessage("Name is already used for optional group #" + (i + 1) + ". " + validGroupName);
                return;
            }
        }
    }

    for (int i = 0, n = jTabbedPaneQueryGroups.getTabCount(); i < n; i++) {
        String currentTitle = jTabbedPaneQueryGroups.getTitleAt(i);
        if (currentTitle.equalsIgnoreCase(newTitle)) {
            showErrorMessage("Name is already used for query group #" + (i + 1) + ". " + validGroupName);
            return;
        }
    }


    String oldTitle = jTabbedPaneOptionalGroups.getTitleAt(index);
    jTabbedPaneOptionalGroups.setTitleAt(index, newTitle);

    logger.info("Optional group # " + (index + 1) + " was renamed from " + oldTitle + " to " + newTitle);

}//GEN-LAST:event_jButtonOptionalGroupRenameActionPerformed

private void jButtonOptionalGroupRenameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonOptionalGroupRenameKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_jButtonOptionalGroupRenameKeyTyped

private void jRadioButtonQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonQueryActionPerformed
    switchTargetGroups();
}//GEN-LAST:event_jRadioButtonQueryActionPerformed

private void jRadioButtonOptionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonOptionalActionPerformed
    switchTargetGroups();
}//GEN-LAST:event_jRadioButtonOptionalActionPerformed

private void jButtonTaxaAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxaAddActionPerformed
    int[] locations = sourceList.getSelectedIndices();

    if (locations.length > 0) {

        List<Integer> sortedLocations = new ArrayList<Integer>();
        for (Integer index : locations) {
            sortedLocations.add(index);
        }
        Collections.sort(sortedLocations);

        JTabbedPane tabbedPane = null;
        if (jRadioButtonQuery.isSelected()) {
            tabbedPane = jTabbedPaneQueryGroups;
            if (tabbedPane.getSelectedIndex() == -1) {
                addGroup();
            }
        } else {
            tabbedPane = jTabbedPaneOptionalGroups;
            if (tabbedPane.getSelectedIndex() == -1) {
                addOptionalGroup();
            }
        }

        MutableList target = ((TabbedList) tabbedPane.getSelectedComponent()).getList();

        for (Integer index : sortedLocations) {
            String item = sourceList.get(index);
            target.add(item);
        }

        int removed = 0;
        for (Integer index : sortedLocations) {
            sourceList.remove(index - removed++);
        }
    }
}//GEN-LAST:event_jButtonTaxaAddActionPerformed

private void jButtonTaxaRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaxaRemoveActionPerformed

    JTabbedPane tabbedPane = null;
    if (jRadioButtonQuery.isSelected()) {
        tabbedPane = jTabbedPaneQueryGroups;
    } else {
        tabbedPane = jTabbedPaneOptionalGroups;
    }

    if (tabbedPane.getSelectedIndex() > -1) {

        MutableList target = ((TabbedList) tabbedPane.getSelectedComponent()).getList();
        int[] locations = target.getSelectedIndices();

        List<Integer> sortedLocations = new ArrayList<Integer>();
        for (Integer index : locations) {
            sortedLocations.add(index);
        }
        Collections.sort(sortedLocations);

        for (Integer index : sortedLocations) {
            String item = target.get(index);
            sourceList.add(item);
        }

        int removed = 0;
        for (Integer index : sortedLocations) {
            target.remove(index - removed++);
        }
    }
}//GEN-LAST:event_jButtonTaxaRemoveActionPerformed

private void jButtonGroupRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGroupRemoveActionPerformed
    int selectedIndex = jTabbedPaneQueryGroups.getSelectedIndex();
    if (selectedIndex != -1) {
        MutableList target = ((TabbedList) jTabbedPaneQueryGroups.getSelectedComponent()).getList();
        while (target.getCount() > 0) {
            sourceList.add(target.get(0));
            target.remove(0);
        }
        jTabbedPaneQueryGroups.removeTabAt(selectedIndex);
    }
    selectedIndex = jTabbedPaneQueryGroups.getSelectedIndex();
    if (selectedIndex != -1) {
        MutableList target = ((TabbedList) jTabbedPaneQueryGroups.getSelectedComponent()).getList();
        sourceList.setTarget(target);
    } else {
        sourceList.setTarget(null);
    }

}//GEN-LAST:event_jButtonGroupRemoveActionPerformed

    private void jMenuItemTreeClusterActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemTreeClusterActionPerformed

        jDialogTreeCluster.setVisible(true);

    }// GEN-LAST:event_jMenuItemTreeClusterActionPerformed

    private void jButtonTreeClusterCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonTreeClusterCancelActionPerformed
        jDialogTreeCluster.setVisible(false);
    }// GEN-LAST:event_jButtonTreeClusterCancelActionPerformed

    private void jButtonTreeClusterGoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonTreeClusterGoActionPerformed

        String infolder = jTextFieldTreeClusterInput.getText();
        String outfolder = jTextFieldTreeClusterOutput.getText();

        SpinnerNumberModel spinnerNumberModel = (SpinnerNumberModel) jSpinnerTreeClusterMinimum.getModel();

        int minimumOverlap = spinnerNumberModel.getNumber().intValue();

        try {
            setCursor(new Cursor(Cursor.WAIT_CURSOR));

            List<TreeCluster> clusters = PhyloCluster.cluster(infolder, outfolder, minimumOverlap);

            int trees = 0;

            for (TreeCluster cluster : clusters) {
                trees += cluster.size();
            }

            String msg = "Clustered " + trees + " tree(s) into " + clusters.size() + " cluster(s).";
            showInformationMessage(msg);

            jDialogTreeCluster.requestFocus();

        } catch (Exception exception) {

            String msg = "Failed clustering trees: " + exception.getMessage();
            logger.log(Level.SEVERE, msg, exception);
            showErrorMessage(msg);
        } finally {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

    }// GEN-LAST:event_jButtonTreeClusterGoActionPerformed

    private void jButtonTreeClusterOutputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonTreeClusterOutputActionPerformed

        try {
            File directory = openDirectory(jDialogTreeCluster);
            if (directory != null) {
                logger.info("Selected: " + directory.getPath());
                if (directory.isDirectory() && directory.canRead()) {
                    jTextFieldTreeClusterOutput.setText(directory.getPath());
                } else {
                    showErrorMessage("Invalid or cannot read selected directory: " + directory.getPath());
                }
            } else {
                logger.info("No output directory was selected");
            }

        } catch (Exception e) {
            String msg = "Failed opening output directory: " + e.getMessage();
            logger.log(Level.SEVERE, msg, e);
            showErrorMessage(msg);
        }

    }// GEN-LAST:event_jButtonTreeClusterOutputActionPerformed

    private void jButtonTreeClusterInputActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonTreeClusterInputActionPerformed

        try {
            File directory = openDirectory(jDialogTreeCluster);
            if (directory != null) {
                logger.info("Selected: " + directory.getPath());
                if (directory.isDirectory() && directory.canRead()) {
                    jTextFieldTreeClusterInput.setText(directory.getPath());
                } else {
                    showErrorMessage("Invalid or cannot read selected directory: " + directory.getPath());
                }
            } else {
                logger.info("No input directory was selected");
            }

        } catch (Exception e) {
            String msg = "Failed opening input directory: " + e.getMessage();
            logger.log(Level.SEVERE, msg, e);
            showErrorMessage(msg);
        }

    }// GEN-LAST:event_jButtonTreeClusterInputActionPerformed

    private void jButtonTaxaReloadActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonTaxaReloadActionPerformed

        String source = jTextFieldTaxaOpen.getText().trim();

        if (source.length() == 0) {
            jButtonTaxaOpenActionPerformed(evt);
        } else {

            File file = new File(source);

            if (jRadioButtonTree.isSelected()) {
                loadTaxaFromTree(file);
            } else if (jRadioButtonForest.isSelected()) {
                loadTaxaFromForest(file);
            } else if (jRadioButtonPlain.isSelected()) {
                loadTaxaFromPlain(file);
            }
        }

    }// GEN-LAST:event_jButtonTaxaReloadActionPerformed

    private void jButtonTaxaOpenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonTaxaOpenActionPerformed

        if (jRadioButtonTree.isSelected()) {
            loadTaxaFromTree();
        } else if (jRadioButtonForest.isSelected()) {
            loadTaxaFromForest();
        } else if (jRadioButtonPlain.isSelected()) {
            loadTaxaFromPlain();
        }

    }// GEN-LAST:event_jButtonTaxaOpenActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOKActionPerformed
        validateSettings();
    }// GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonCancelActionPerformed
        populateSettings();
    }// GEN-LAST:event_jButtonCancelActionPerformed

    private void jCheckBoxReferenceActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jCheckBoxReferenceActionPerformed
        treeWindow.setVisible(jCheckBoxReference.isSelected());

    }// GEN-LAST:event_jCheckBoxReferenceActionPerformed

    private void jMenuItemFileSaveConfigActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemFileSaveConfigActionPerformed

        File file = null;
        boolean done = false;
        boolean save = true;

        do {
            file = saveFile(configFile);

            if (file != null) {

                if (file.exists()) {

                    String msg = "File already exists. Would you like to overwrite it?";

                    int option = JOptionPane.showConfirmDialog(this, msg, "PhyloSort - confirmation", JOptionPane.YES_NO_CANCEL_OPTION);

                    if (option == JOptionPane.CANCEL_OPTION) {
                        save = false;
                        done = true;
                    } else if (option == JOptionPane.YES_OPTION) {
                        save = true;
                        done = true;
                    } else if (option == JOptionPane.NO_OPTION) {
                    }
                } else {
                    done = true;
                    save = true;
                }
            } else {
                save = false;
                done = true;
            }
        } while (!done);

        if (save) {
            try {
                Config.save(file);
                configFile = file.getName();
            } catch (Exception e) {
                String msg = "Failed saving config file " + file.getName() + ": " + e.getMessage();
                logger.log(Level.SEVERE, msg, e);
                showErrorMessage(msg);
            }
        }

    }// GEN-LAST:event_jMenuItemFileSaveConfigActionPerformed

    private void jButtonConsoleClearActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonConsoleClearActionPerformed

        jTextPaneConsole.setText("");

    }// GEN-LAST:event_jButtonConsoleClearActionPerformed

    private void jMenuItemFileLoadConfigActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemFileLoadConfigActionPerformed

        File file = openFile(this);

        if (file != null) {

            try {
                Config.load(file.getPath());

                Config config = Config.getInstance();

                configFile = file.getName();

                // Update menu items with the new settings loaded from the
                // config file

                jCheckBoxRoot.setSelected(config.isOutgroup());

                populateSettings();

            } catch (Exception e) {
                String msg = e.getMessage();
                showErrorMessage(msg);
            }

        }

    }// GEN-LAST:event_jMenuItemFileLoadConfigActionPerformed

    private void jMenuItemTreeBrowserActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemTreeViewerActionPerformed

        jFileChooserTrees.setCurrentDirectory(new File(Commons.getUserDirectory()));
        jDialogTreeBrowser.setVisible(true);

    }// GEN-LAST:event_jMenuItemTreeViewerActionPerformed

    private void jFileChooserTreesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jFileChooserTreesActionPerformed

        if (evt.getActionCommand().equalsIgnoreCase("CancelSelection")) {
            jDialogTreeBrowser.setVisible(false);
            return;
        }

        if (jFileChooserTrees.getSelectedFile() == null) {
            return;
        }

        jFileChooserTrees.setCursor(new Cursor(Cursor.WAIT_CURSOR));

        for (File file : jFileChooserTrees.getSelectedFiles()) {

            ATVjframe frame = null;
            try {
                logger.info("Starting ATV to view \"" + file.getPath() + "\"...");

                Tree tree = TreeHelper.readNHtree(file);
                frame = new ATVjframe(tree);
                frame.setLocationRelativeTo(this);
                frame.setTitle("ATV - " + file.getPath());
                frame.setIconImage(this.getIconImage());
                frame.pack();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);

                logger.info("ATV instance started successfully to view \"" + file.getPath() + "\"");
            } catch (Exception e) {
                String msg = "Failed starting ATV to view tree \"" + file.getPath() + "\": " + e.getMessage();
                logger.log(Level.SEVERE, msg, e);
                showErrorMessage(msg);
                if (frame != null) {
                    try {
                        frame.setVisible(false);
                        frame.dispose();
                    } catch (Exception silent) {
                        logger.warning(silent.getMessage());
                    }
                }
            }
        }

        jFileChooserTrees.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

    }// GEN-LAST:event_jFileChooserTreesActionPerformed

    private void jButtonOutputDirectoryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOutputDirectoryActionPerformed
        try {
            File directory = openDirectory(this);
            if (directory != null) {
                logger.info("Selected: " + directory.getPath());
                if (directory.isDirectory() && directory.canWrite()) {
                    jTextFieldOutputDirectory.setText(directory.getPath());
                } else {
                    showErrorMessage("Invalid or cannot read selected directory: " + directory.getPath());
                }
            } else {
                logger.info("No output directory was selected");
            }
        } catch (Exception e) {
            String msg = "Failed opening output directory: " + e.getMessage();
            logger.log(Level.SEVERE, msg, e);
            showErrorMessage(msg);
        }
    }// GEN-LAST:event_jButtonOutputDirectoryActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonExitActionPerformed
        exitForm(null);
    }// GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonGroupAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonGroupAddActionPerformed

        addGroup();

    }// GEN-LAST:event_jButtonGroupAddActionPerformed

    private void jButtonExecuteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed

        if (!validateSettings()) {
            return;
        }

        String in = jTextFieldInputDirectory.getText().trim();

        if (in.length() == 0) {
            showErrorMessage("Input directory is not set.");
            return;
        }

        File file;

        file = new File(in);
        if (!file.isDirectory()) {
            showErrorMessage("Invalid input directory: " + in);
        }

        if (!file.canRead()) {
            showErrorMessage("Cannot read input directory: " + in);
        }

        String out = jTextFieldOutputDirectory.getText().trim();

        if (Config.getInstance().getOnMatchAction().equalsIgnoreCase(Config.PROPERTY_ON_MATCH_ACTION_COPY) || Config.getInstance().getOnMatchAction().equalsIgnoreCase(Config.PROPERTY_ON_MATCH_ACTION_MOVE)) {

            if (out.length() == 0) {
                showErrorMessage("Output directory is not set.");
                return;
            }

            file = new File(out);
            if (!file.isDirectory()) {
                showErrorMessage("Invalid output directory: " + out);
                return;
            }

            if (!file.canWrite()) {
                showErrorMessage("Cannot write to output directory: " + out);
                return;
            }

            if (in.equalsIgnoreCase(out)) {
                showErrorMessage("Input and output directories are the same. Please change one of them.");
                return;
            }
        }

        int count = jTabbedPaneQueryGroups.getTabCount();

        boolean foundEmpty = false;
        boolean foundNonEmpty = false;

        TabbedList tab = null;

        for (int i = 0; i < count; i++) {

            tab = (TabbedList) jTabbedPaneQueryGroups.getComponentAt(i);

            if (tab.getList().getCount() > 0) {

                foundNonEmpty = true;

            } else {

                foundEmpty = true;

            }

        }

        if (!foundNonEmpty) {
            showErrorMessage("All groups are empty. Please add taxa to at least one group.");
            return;
        }

        if (foundEmpty) {

            StringBuffer message = new StringBuffer();
            message.append("There is at least one empty group. Would you like to continue?");
            message.append("\n");
            message.append("If you say Yes, empty groups will be removed.");

            if (JOptionPane.showConfirmDialog(this, message.toString(), "PhyloSort - confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                logger.info("Canceled");
                return;
            }

            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < count; i++) {
                tab = (TabbedList) jTabbedPaneQueryGroups.getComponentAt(i);
                if (tab.getList().getCount() == 0) {
                    list.add(i);
                }
            }

            int removed = 0;
            for (int i = 0, n = list.size(); i < n; i++) {
                jTabbedPaneQueryGroups.remove(list.get(i) - (removed++));
            }

        }

        List<List<String>> groups = new ArrayList<List<String>>();

        for (int i = 0, n = jTabbedPaneQueryGroups.getComponentCount(); i < n; i++) {
            tab = (TabbedList) jTabbedPaneQueryGroups.getComponentAt(i);

            MutableList list = tab.getList();
            List<String> group = new ArrayList<String>();

            for (int j = 0, m = list.getCount(); j < m; j++) {

                group.add(list.get(j));

            }

            groups.add(group);
        }

        try {
            setCursor(new Cursor(Cursor.WAIT_CURSOR));

            int match = PhyloSort.sort(in, out, groups);

            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            if (match == 0) {
                showInformationMessage("Found NO matching trees.");
            } else {
                String msg = null;

                if (match == 1) {
                    msg = "Found 1 matching tree.";
                } else if (match > 1) {
                    msg = "Found " + match + " matching trees.";
                }

                if (Config.getInstance().getOnMatchAction().equalsIgnoreCase(Config.PROPERTY_ON_MATCH_ACTION_COPY) || Config.getInstance().getOnMatchAction().equalsIgnoreCase(Config.PROPERTY_ON_MATCH_ACTION_MOVE)) {

                    msg += "\nWould you like to browse the output folder?";

                    if (JOptionPane.showConfirmDialog(this, msg, "PhyloSort - results", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {

                        jFileChooserTrees.setCurrentDirectory(new File(jTextFieldOutputDirectory.getText()));

                        jDialogTreeBrowser.pack();
                        jDialogTreeBrowser.setVisible(true);
                    }

                } else {

                    showInformationMessage(msg);

                }
            }
        } catch (Exception e) {
            String msg = "Failed sorting trees: " + e.getMessage();
            logger.log(Level.SEVERE, msg, e);
            showErrorMessage(msg);
        } finally {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

    }// GEN-LAST:event_jButton5ActionPerformed

    private void jButtonInputDirectoryActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        try {
            File directory = openDirectory(this);
            if (directory != null) {
                logger.info("Selected: " + directory.getPath());
                if (directory.isDirectory() && directory.canRead()) {
                    jTextFieldInputDirectory.setText(directory.getPath());

                    String msg = "Would you like to load the pool of taxa from trees in the selected folder?";
                    if (JOptionPane.showConfirmDialog(this, msg, "PhyloSort - confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        loadTaxa(directory);
                    }

                } else {
                    showErrorMessage("Invalid or cannot read selected directory: " + directory.getPath());
                }
            } else {
                logger.info("No input directory was selected");
            }
        } catch (Exception e) {
            String msg = "Failed opening input directory: " + e.getMessage();
            logger.log(Level.SEVERE, msg, e);
            showErrorMessage(msg);
        }
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemAboutActionPerformed
        StringBuffer buffer = new StringBuffer();
        buffer.append("PhyloSort - a phylogenetic sorting tool");
        buffer.append(Commons.getLineSeparator());

        String version = Commons.getCurrentVersion();
        if (version != null) {
            buffer.append("Version " + version);
            buffer.append(Commons.getLineSeparator());
        }

        String build = Commons.getBuildTimestamp();
        if (build != null) {
            buffer.append("Build " + build);
            buffer.append(Commons.getLineSeparator());
        }

        buffer.append(Commons.getLineSeparator());
        buffer.append("\u00A9 2007-2012");
        buffer.append(Commons.getLineSeparator());
        buffer.append("Ahmed Moustafa & Debashish Bhattacharya");
        buffer.append(Commons.getLineSeparator());
        buffer.append("Interdisciplinary Genetics Program");
        buffer.append(Commons.getLineSeparator());
        buffer.append("University of Iowa");
        buffer.append(Commons.getLineSeparator());
        buffer.append(Commons.getLineSeparator());
        buffer.append("BMC Evolutionary Biology 2008, 8:6 (PMID: 18194581)");
        buffer.append(Commons.getLineSeparator());
        buffer.append(Commons.getLineSeparator());
        buffer.append("GNU General Public License (GPL)");
        JOptionPane.showMessageDialog(this, buffer.toString(), "About PhyloSort", JOptionPane.INFORMATION_MESSAGE);
    }// GEN-LAST:event_jMenuItemAboutActionPerformed

    private void jMenuItemFileExitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemFileExitActionPerformed
        exitForm(null);
    }// GEN-LAST:event_jMenuItemFileExitActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_exitForm
        logger.info("Quitting...");
        if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit PhyloSort?", "PhyloSort - confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            logger.info("Thank you for using PhyloSort!");
            System.exit(0);
        } else {
            logger.info("Canceled quitting");
        // currentTextComponent.requestFocus();
        }
    }// GEN-LAST:event_exitForm

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTaxaSource;
    private javax.swing.ButtonGroup buttonGroupType;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonConsoleClear;
    private javax.swing.JButton jButtonExecute;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonGoPlusPlus;
    private javax.swing.JButton jButtonGroupAdd;
    private javax.swing.JButton jButtonGroupRemove;
    private javax.swing.JButton jButtonGroupRename;
    private javax.swing.JButton jButtonInputDirectory;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JButton jButtonOptionalGroupAdd;
    private javax.swing.JButton jButtonOptionalGroupRemove;
    private javax.swing.JButton jButtonOptionalGroupRename;
    private javax.swing.JButton jButtonOutputDirectory;
    private javax.swing.JButton jButtonTaxaAdd;
    private javax.swing.JButton jButtonTaxaOpen;
    private javax.swing.JButton jButtonTaxaReload;
    private javax.swing.JButton jButtonTaxaRemove;
    private javax.swing.JButton jButtonTreeClusterCancel;
    private javax.swing.JButton jButtonTreeClusterGo;
    private javax.swing.JButton jButtonTreeClusterInput;
    private javax.swing.JButton jButtonTreeClusterOutput;
    private javax.swing.JCheckBox jCheckBoxExclusive;
    private javax.swing.JCheckBox jCheckBoxReference;
    private javax.swing.JCheckBox jCheckBoxRoot;
    private javax.swing.JComboBox jComboBoxOnMatch;
    private javax.swing.JComboBox jComboBoxRegexp;
    private javax.swing.JDialog jDialogTreeBrowser;
    private javax.swing.JDialog jDialogTreeCluster;
    private javax.swing.JFileChooser jFileChooserTrees;
    private javax.swing.JLabel jLabelConsole;
    private javax.swing.JLabel jLabelGroups;
    private javax.swing.JLabel jLabelInputTaxa;
    private javax.swing.JLabel jLabelMaxTaxa;
    private javax.swing.JLabel jLabelMaximumNumberOfCopies;
    private javax.swing.JLabel jLabelMinBootstrap;
    private javax.swing.JLabel jLabelMinTaxa;
    private javax.swing.JLabel jLabelOnMatch;
    private javax.swing.JLabel jLabelOptionalGroups;
    private javax.swing.JLabel jLabelRegexp;
    private javax.swing.JLabel jLabelTreeClusterInput;
    private javax.swing.JLabel jLabelTreeClusterMinimum;
    private javax.swing.JLabel jLabelTreeClusterOutput;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemFileExit;
    private javax.swing.JMenuItem jMenuItemFileLoadConfig;
    private javax.swing.JMenuItem jMenuItemFileSaveConfig;
    private javax.swing.JMenuItem jMenuItemTreeBrowser;
    private javax.swing.JMenuItem jMenuItemTreeCluster;
    private javax.swing.JMenu jMenuTools;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelButtonsAcceptReject;
    private javax.swing.JPanel jPanelCluster;
    private javax.swing.JPanel jPanelConsole;
    private javax.swing.JPanel jPanelConsoleEdit;
    private javax.swing.JPanel jPanelControls;
    private javax.swing.JPanel jPanelExecute;
    private javax.swing.JPanel jPanelExit;
    private javax.swing.JPanel jPanelGroupButtons;
    private javax.swing.JPanel jPanelInputDirectory;
    private javax.swing.JPanel jPanelInputTaxa;
    private javax.swing.JPanel jPanelMaxNumGenes;
    private javax.swing.JPanel jPanelMaxTaxa;
    private javax.swing.JPanel jPanelMinBootstrap;
    private javax.swing.JPanel jPanelMinTaxa;
    private javax.swing.JPanel jPanelMode;
    private javax.swing.JPanel jPanelOnMatch;
    private javax.swing.JPanel jPanelOptionalGroupButtons;
    private javax.swing.JPanel jPanelOptionalTaxa;
    private javax.swing.JPanel jPanelOutputDirectory;
    private javax.swing.JPanel jPanelQueryTaxa;
    private javax.swing.JPanel jPanelReferenceTaxonomy;
    private javax.swing.JPanel jPanelRootByOutgroup;
    private javax.swing.JPanel jPanelSettings;
    private javax.swing.JPanel jPanelTaxaButtons;
    private javax.swing.JPanel jPanelTaxaRegexp;
    private javax.swing.JPanel jPanelTaxaSource;
    private javax.swing.JPanel jPanelTaxaSourceButtons;
    private javax.swing.JPanel jPanelTreeClusterButtons;
    private javax.swing.JRadioButton jRadioButtonForest;
    private javax.swing.JRadioButton jRadioButtonOptional;
    private javax.swing.JRadioButton jRadioButtonPlain;
    private javax.swing.JRadioButton jRadioButtonQuery;
    private javax.swing.JRadioButton jRadioButtonTree;
    private javax.swing.JScrollPane jScrollPaneConsole;
    private javax.swing.JSeparator jSeparatorFile;
    private javax.swing.JSpinner jSpinnerTreeClusterMinimum;
    private javax.swing.JSplitPane jSplitPaneGroups;
    private javax.swing.JSplitPane jSplitPaneTop;
    private javax.swing.JTabbedPane jTabbedPaneOptionalGroups;
    private javax.swing.JTabbedPane jTabbedPaneQueryGroups;
    private javax.swing.JTextField jTextFieldGroupRename;
    private javax.swing.JTextField jTextFieldInputDirectory;
    private javax.swing.JTextField jTextFieldMaxTaxa;
    private javax.swing.JTextField jTextFieldMaximumNumberOfCopies;
    private javax.swing.JTextField jTextFieldMinBoot;
    private javax.swing.JTextField jTextFieldMinTaxa;
    private javax.swing.JTextField jTextFieldOptionalGroupRename;
    private javax.swing.JTextField jTextFieldOutputDirectory;
    private javax.swing.JTextField jTextFieldTaxaOpen;
    private javax.swing.JTextField jTextFieldTreeClusterInput;
    private javax.swing.JTextField jTextFieldTreeClusterOutput;
    private javax.swing.JTextPane jTextPaneConsole;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JScrollPane taxaScrollPane;

    private void addGroup() {
        String title = "QGroup" + ++groupIndex;
        TabbedList tabbedList = new TabbedList(sourceList);
        jTabbedPaneQueryGroups.addTab(title, tabbedList);
        jTabbedPaneQueryGroups.setSelectedComponent(tabbedList);
        jRadioButtonQuery.setSelected(true);
    }

    private void addOptionalGroup() {
        String title = "OGroup" + ++optionGroupIndex;
        TabbedList tabbedList = new TabbedList(sourceList);
        jTabbedPaneOptionalGroups.addTab(title, tabbedList);
        jTabbedPaneOptionalGroups.setSelectedComponent(tabbedList);
        jRadioButtonOptional.setSelected(true);
    }

    private void initComponents2() {

        jSplitPaneTop.setDividerLocation(0.5);
        jSplitPaneGroups.setDividerLocation(0.75);

        taxaScrollPane = new JScrollPane();
        taxaScrollPane.setViewportView(sourceList);
        jPanelInputTaxa.add(taxaScrollPane, java.awt.BorderLayout.CENTER);

        jTabbedPaneQueryGroups.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent event) {
                JTabbedPane pane = (JTabbedPane) event.getSource();
                TabbedList tabbedList = (TabbedList) pane.getSelectedComponent();
                int index = jTabbedPaneQueryGroups.getSelectedIndex();
                if (index > -1) {
                    String title = jTabbedPaneQueryGroups.getTitleAt(index);
                    jTextFieldGroupRename.setText(title);
                    MutableList targetList = tabbedList.getList();
                    sourceList.setTarget(targetList);
                    jRadioButtonQuery.setSelected(true);
                }
            }
        });

        addGroup();

        jTabbedPaneOptionalGroups.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent event) {
                JTabbedPane pane = (JTabbedPane) event.getSource();
                TabbedList tabbedList = (TabbedList) pane.getSelectedComponent();
                int index = jTabbedPaneOptionalGroups.getSelectedIndex();
                if (index > -1) {
                    String title = jTabbedPaneOptionalGroups.getTitleAt(index);
                    jTextFieldOptionalGroupRename.setText(title);
                    MutableList targetList = tabbedList.getList();
                    sourceList.setTarget(targetList);
                    jRadioButtonOptional.setSelected(true);
                }
            }
        });

        addOptionalGroup();

        sourceList.setTarget(((TabbedList) jTabbedPaneQueryGroups.getSelectedComponent()).getList());
        jRadioButtonQuery.setSelected(true);

        jRadioButtonTree.setSelected(true);

        populateRegexpExamples();
        populateSettings();

        jDialogTreeBrowser.pack();
        jDialogTreeBrowser.setLocationRelativeTo(this);

        jDialogTreeCluster.pack();
        jDialogTreeCluster.setLocationRelativeTo(this);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
        jSpinnerTreeClusterMinimum.setModel(spinnerNumberModel);

        pack();

    }

    /**
     * Displays an error message
     * 
     * @param message
     *            the error message to be displayed
     */
    private void showErrorMessage(String message) {

        StringBuffer buffer = new StringBuffer();

        int max = 80;
        int len = message.length();

        for (int i = 0; i < len;) {

            for (int j = 0; j < max && i < len; j++, i++) {
                buffer.append(message.charAt(i));
            }

            if (i < len) {
                buffer.append("\n");
            }

        }

        JOptionPane.showMessageDialog(this, buffer.toString(), "PhyloSort - Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an information message
     * 
     * @param message
     *            the information message to be displayed
     */
    private void showInformationMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "PhyloSort - Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Returns {@link List} of {@link String} of all taxa in all trees in a
     * directory
     * 
     * @param directory
     *            Input directory
     * @return {@link List} of {@link String} of all taxa in all trees in a
     *         directory
     */
    private List<String> loadTaxa(File directory) {
        setCursor(new Cursor(Cursor.WAIT_CURSOR));

        cleanTaxaLists();

        logger.info("Cleared query taxa");

        logger.info("Loading taxa...");

        Set<String> taxa = new HashSet<String>();

        int i = 0;

        for (String file : directory.list()) {

            String path = directory + Commons.getFileSeparator() + file;

            File in = new File(path);

            if (in.isFile() && in.canRead()) {

                logger.info("Loading taxa # " + i + " from tree file \"" + file + "\"");

                try {
                    taxa.addAll(TreeNodeUtil.getTaxa(in));
                } catch (Exception e) {
                    String msg = "Failed getting taxa from tree file \"" + in.getName() + "\": " + e.getMessage();
                    logger.log(Level.SEVERE, msg, e);
                } catch (Error error) {
                    String msg = "Failed getting taxa from tree file \"" + in.getName() + "\": " + error.getMessage();
                    logger.log(Level.SEVERE, msg, error);
                }

                logger.info("Finished loading taxa from tree file \"" + file + "\"");

                i++;
            }
        }

        List<String> sorted = new ArrayList<String>();

        for (String taxon : taxa) {
            if (taxon != null && taxon.trim().length() != 0) {
                sorted.add(taxon);
            }
        }

        logger.info("Loaded " + sorted.size() + " taxa from " + directory.list().length + " files in " + directory.getPath());

        Collections.sort(sorted);

        for (String taxon : sorted) {
            sourceList.add(taxon);
        }

        logger.info("Finished loading taxa");

        if (sorted.size() == 0) {
            logger.warning("Found no taxa");
            if (directory.list().length > 0) {
                String msg = "Loaded no taxa in " + directory.list().length + " files in \"" + directory.getPath() + "\"";
                msg += "\n";
                msg += "The taxa extraction regular expression may need an adjustment.";
                showInformationMessage(msg);
            }
        }

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        return sorted;
    }

    /**
     * Shows a dialog box to select and open a directory
     * 
     * @param parent
     *            Parent component
     * 
     * @return Selected directory ({@link File})
     */
    private File openDirectory(Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String currentDirectory = Commons.getUserDirectory();
        if (currentDirectory != null && currentDirectory.length() > 0) {
            chooser.setCurrentDirectory(new File(currentDirectory));
        }
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            Commons.setUserDirectory(chooser.getCurrentDirectory().getPath());
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    /**
     * Shows a dialog box to select and open a file
     * 
     * @param parent
     *            Parent component
     * 
     * @return Selected directory ({@link File})
     */
    private static File openFile(Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.setCurrentDirectory(new File(Commons.getUserDirectory()));
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            Commons.setUserDirectory(chooser.getCurrentDirectory().getPath());
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    /**
     * Shows a dialog box to select and open a file
     * 
     * @return Selected directory ({@link File})
     */
    public static File saveFile(String filename) {
        JFileChooser chooser = new JFileChooser();
        if (filename != null && filename.length() > 0) {
            chooser.setSelectedFile(new File(filename));
        }
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        chooser.setCurrentDirectory(new File(Commons.getUserDirectory()));
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            Commons.setUserDirectory(chooser.getCurrentDirectory().getPath());
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    /**
     * 
     * 
     */
    private void cleanTaxaLists() {
        sourceList.removeAll();

        logger.info("Cleared source taxa");

        for (Component component : jTabbedPaneQueryGroups.getComponents()) {
            ((TabbedList) component).getList().removeAll();
        }

        for (Component component : jTabbedPaneOptionalGroups.getComponents()) {
            ((TabbedList) component).getList().removeAll();
        }
    }

    public void loadTaxaFromTree() {

        File file = PhyloSortWindow.openFile(this);

        loadTaxaFromTree(file);

        if (file != null) {

            try {
                TreeNode tree = TreeNodeUtil.load(file);

                treeWindow.populateTree(tree);
                jTextFieldTaxaOpen.setText(file.getPath());

            } catch (Exception exception) {
                String msg = "Failed loading reference tree: " + exception.getMessage();
                logger.log(Level.SEVERE, msg, exception);
            }

        } else {
            logger.info("Canceled loading reference tree");
        }

    }

    /**
     * 
     * 
     */
    public void loadTaxaFromTree(File file) {

        if (file != null) {

            try {
                TreeNode tree = TreeNodeUtil.load(file);

                if (tree != null) {

                    treeWindow.populateTree(tree);

                    treeWindow.setVisible(true);
                    jCheckBoxReference.setSelected(true);

                    cleanTaxaLists();

                    Iterable<TreeNode> iterable = tree.iterator();

                    List<String> list = new ArrayList<String>();

                    for (TreeNode node : iterable) {
                        list.add(node.getTaxon(true));
                    }

                    sourceList.populate(list);
                }

                treeWindow.populateTree(tree);
                jTextFieldTaxaOpen.setText(file.getPath());

            } catch (Exception exception) {
                String msg = "Failed loading reference tree: " + exception.getMessage();
                logger.log(Level.SEVERE, msg, exception);
            }
        }
    }

    public void populateRegexpExamples() {
        jComboBoxRegexp.removeAllItems();

        TaxaRegexpExamples examples = TaxaRegexpExamples.getInstance();

        List<String> list = new ArrayList<String>();

        for (int i = 0, n = examples.size(); i < n; i++) {
            String regexp = examples.getExpression(i);
            list.add(regexp);
        }

        Collections.sort(list);

        for (String item : list) {
            jComboBoxRegexp.addItem(item);
        }

        jComboBoxRegexp.setEditable(true);
    }

    private boolean validateSettings() {

        int minimumNumberOfTaxa;
        int maximumNumberOfTaxa;
        float minimumBootstrapSupport;
        float maximumAverageNumberOfCopies;

        String regex = null;
        String match = null;
        String mode = null;

        try {
            minimumNumberOfTaxa = Integer.parseInt(jTextFieldMinTaxa.getText());
        } catch (Exception e) {
            String msg = "Failed parsing minimum number of taxa: " + e.getMessage();
            logger.log(Level.SEVERE, msg, e);
            showErrorMessage(msg);
            jTextFieldMinTaxa.requestFocus();
            jTextFieldMinTaxa.setSelectionStart(0);
            jTextFieldMinTaxa.setSelectionEnd(jTextFieldMinTaxa.getText().length());
            return false;
        }

        try {
            maximumNumberOfTaxa = Integer.parseInt(jTextFieldMaxTaxa.getText());
        } catch (Exception e) {
            String msg = "Failed parsing maximum number of taxa: " + e.getMessage();
            logger.log(Level.SEVERE, msg, e);
            showErrorMessage(msg);
            jTextFieldMaxTaxa.requestFocus();
            jTextFieldMaxTaxa.setSelectionStart(0);
            jTextFieldMaxTaxa.setSelectionEnd(jTextFieldMaxTaxa.getText().length());
            return false;
        }

        try {
            minimumBootstrapSupport = Float.parseFloat(jTextFieldMinBoot.getText());
        } catch (Exception e) {
            String msg = "Failed parsing minimum bootstrap support: " + e.getMessage();
            logger.log(Level.SEVERE, msg, e);
            showErrorMessage(msg);
            jTextFieldMinBoot.requestFocus();
            jTextFieldMinBoot.setSelectionStart(0);
            jTextFieldMinBoot.setSelectionEnd(jTextFieldMinBoot.getText().length());
            return false;
        }

        try {
            maximumAverageNumberOfCopies = Float.parseFloat(jTextFieldMaximumNumberOfCopies.getText());
        } catch (Exception e) {
            String msg = "Failed parsing maximum average number of copies: " + e.getMessage();
            logger.log(Level.SEVERE, msg, e);
            showErrorMessage(msg);
            jTextFieldMaximumNumberOfCopies.requestFocus();
            jTextFieldMaximumNumberOfCopies.setSelectionStart(0);
            jTextFieldMaximumNumberOfCopies.setSelectionEnd(jTextFieldMaximumNumberOfCopies.getText().length());
            return false;
        }

        regex = (String) jComboBoxRegexp.getSelectedItem();
        try {
            Pattern.compile(regex);
            boolean found = false;
            for (int i = 0, n = jComboBoxRegexp.getItemCount(); i < n && !found; i++) {
                String item = (String) jComboBoxRegexp.getItemAt(i);
                if (item.compareTo(regex) == 0) {
                    jComboBoxRegexp.setSelectedIndex(i);
                    found = true;
                }
            }
            if (!found) {
                jComboBoxRegexp.addItem(regex);
            }
        } catch (PatternSyntaxException patternSyntaxException) {
            String msg = "Failed compiling taxa regular epxerssion: " + patternSyntaxException.getMessage();
            logger.log(Level.SEVERE, msg, patternSyntaxException);
            showErrorMessage(msg);
            jComboBoxRegexp.requestFocus();
            return false;
        }

        try {
            if (Config.PROPERTY_ON_MATCH_ACTION_COPY.equalsIgnoreCase((String) jComboBoxOnMatch.getSelectedItem())) {
                match = Config.PROPERTY_ON_MATCH_ACTION_COPY;
            } else if (Config.PROPERTY_ON_MATCH_ACTION_MOVE.equalsIgnoreCase((String) jComboBoxOnMatch.getSelectedItem())) {
                match = Config.PROPERTY_ON_MATCH_ACTION_MOVE;
            } else if (Config.PROPERTY_ON_MATCH_ACTION_COUNT.equalsIgnoreCase((String) jComboBoxOnMatch.getSelectedItem())) {
                match = Config.PROPERTY_ON_MATCH_ACTION_COUNT;
            }
        } catch (Exception e) {
            String msg = "Failed setting on match action: " + e.getMessage();
            logger.log(Level.WARNING, msg, e);
            showErrorMessage(msg);
            jComboBoxOnMatch.requestFocus();
            return false;
        }

        if (jCheckBoxExclusive.isSelected()) {
            mode = Config.PROPERTY_MODE_EXCLUSIVE;
        } else {
            mode = Config.PROPERTY_MODE_INCLUSIVE;
        }

        try {
            Config config = Config.getInstance();
            config.setMinimumNumberOfTaxa(minimumNumberOfTaxa);
            config.setMaximumNumberOfTaxa(maximumNumberOfTaxa);
            config.setMinimumBootstrapSupport(minimumBootstrapSupport);
            config.setMaximumAverageNumberOfCopies(maximumAverageNumberOfCopies);
            config.setRegexp(regex);
            config.setOutgroup(jCheckBoxRoot.isSelected());
            config.setMode(mode);
            config.setOnMatchAction(match);

            logger.info("Settings are OK.");

            return true;

        } catch (Exception exception) {
            String msg = "Failed updating settings: " + exception.getMessage();
            logger.log(Level.SEVERE, msg, exception);
            showErrorMessage(msg);
            return false;
        }
    }

    private void loadTaxaFromForest() {
        File directory = openDirectory(this);
        loadTaxaFromForest(directory);
    }

    private void loadTaxaFromForest(File directory) {
        try {
            if (directory != null) {
                logger.info("Selected: " + directory.getPath());
                if (directory.isDirectory() && directory.canRead()) {
                    loadTaxa(directory);
                    jTextFieldTaxaOpen.setText(directory.getPath());
                } else {
                    showErrorMessage("Invalid or cannot read selected directory: " + directory.getPath());
                }
            } else {
                logger.info("Canceled loading taxa from forest");
            }
        } catch (Exception e) {
            String msg = "Failed opening input directory: " + e.getMessage();
            logger.log(Level.SEVERE, msg, e);
            showErrorMessage(msg);
        }
    }

    private void loadTaxaFromPlain() {
        File file = openFile(this);
        loadTaxaFromPlain(file);
    }

    private void loadTaxaFromPlain(File file) {

        if ((file = openFile(this)) != null) {

            List<String> list = new ArrayList<String>();

            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new FileReader(file));
                String line = null;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.startsWith(Commons.COMMENT_STARTER)) {
                        StringTokenizer tokenizer = new StringTokenizer(line, Commons.DELIMITERS);
                        while (tokenizer.hasMoreTokens()) {
                            String item = tokenizer.nextToken();
                            list.add(item);
                        }
                    }
                }

                logger.info("Loaded " + list.size() + " item(s) from taxa file " + file.getName());

                sourceList.populate(list);

                jTextFieldTaxaOpen.setText(file.getPath());

            } catch (Exception e) {
                String msg = "Failed loading taxa file: " + e.getMessage();
                logger.log(Level.SEVERE, msg, e);
                showErrorMessage(msg);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        String msg = "Failed closing taxa file " + file.getName() + ": " + e.getMessage();
                        logger.log(Level.WARNING, msg, e);
                    }
                }
            }
        }
    }

    private void switchTargetGroups() {
        JTabbedPane pane = null;

        if (jRadioButtonQuery.isSelected()) {
            pane = jTabbedPaneQueryGroups;
        } else {
            pane = jTabbedPaneOptionalGroups;
        }

        int index = pane.getSelectedIndex();

        if (index > -1) {
            TabbedList tabbedList = (TabbedList) pane.getSelectedComponent();
            MutableList targetList = tabbedList.getList();
            sourceList.setTarget(targetList);
        }

    }

    private void populateSettings() {

        Config config = Config.getInstance();

        jTextFieldMinTaxa.setText(Integer.toString(config.getMinimumNumberOfTaxa()));
        jTextFieldMaxTaxa.setText(Integer.toString(config.getMaximumNumberOfTaxa()));
        jTextFieldMinBoot.setText(Float.toString(config.getMinimumBootstrapSupport()));
        jTextFieldMaximumNumberOfCopies.setText(Float.toString(config.getMaximumAverageNumberOfCopies()));

        jCheckBoxRoot.setSelected(config.isOutgroup());
        jCheckBoxExclusive.setSelected(config.isExclusive());

        boolean found = false;
        for (int i = 0, n = jComboBoxOnMatch.getItemCount(); i < n && !found; i++) {
            String item = (String) jComboBoxOnMatch.getItemAt(i);
            if (item.equalsIgnoreCase(config.getOnMatchAction())) {
                jComboBoxOnMatch.setSelectedIndex(i);
                found = true;
            }
        }

        found = false;
        String regex = config.getRegexp().toString();
        for (int i = 0, n = jComboBoxRegexp.getItemCount(); i < n && !found; i++) {
            String item = (String) jComboBoxRegexp.getItemAt(i);
            if (item.compareTo(regex) == 0) {
                jComboBoxRegexp.setSelectedIndex(i);
                found = true;
            }
        }
        if (!found) {
            jComboBoxRegexp.setSelectedItem(regex);
        }

    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        new PhyloSortWindow().setVisible(true);
    }
}