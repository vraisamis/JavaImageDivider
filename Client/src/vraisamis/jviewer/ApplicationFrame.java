package vraisamis.jviewer;

import java.awt.Dimension;

import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;

import java.io.File;

import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() {
        super();
        TableColumnModel tcm;
        TableColumn col;
        // 一番外側の設定
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 外側のフレームの作成
        JSplitPane jsp = new JSplitPane();
        jsp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jsp.setPreferredSize(new Dimension(800, 600));
        jsp.setResizeWeight(0.5);
        // 右側の作成
        graphic = new JLabel();
        graphic.setHorizontalAlignment(SwingConstants.LEFT);
        graphic.setVerticalAlignment(SwingConstants.TOP);
        JScrollPane spGraphic = new JScrollPane(graphic, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        // 左上の作成
        files = new FileTableModel(fileFields, 0);
        tfiles = new JTable(files);
        tfiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tfiles.getSelectionModel().addListSelectionListener(new FileListSelectionListener(this));
        tfiles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tcm = tfiles.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(1).setPreferredWidth(200);
        tcm.getColumn(2).setPreferredWidth(70);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tcm.getColumn(2).setCellRenderer(renderer);
        JScrollPane sp1 = new JScrollPane(tfiles);
        sp1.setPreferredSize(new Dimension(400, 300));
        // 左下の作成
        actions = new DefaultTableModel(actionFields, 0);
        tactions = new JTable(actions);
        tactions.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tactions.setEnabled(false);
        tcm = tactions.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(30);
        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(2).setPreferredWidth(240);
        JScrollPane sp2 = new JScrollPane(tactions);
        // 左側の作成
        JSplitPane lPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp1, sp2);
        lPane.setPreferredSize(new Dimension(400, 600));
        lPane.setResizeWeight(0.7);
        jsp.setLeftComponent(lPane);
        jsp.setRightComponent(spGraphic);
        jsp.setPreferredSize(new Dimension(800, 600));
        jsp.setResizeWeight(0.5);
        this.getContentPane().add(jsp);
        this.setSize(800, 600);
        // メニューの作成とセット
        menuBar = this.createMenuBar();
        this.setJMenuBar(menuBar);
        
        this.move = false;
        
        //tactions.addKeyListener(new FilesTableListener(this, files, tfiles.getSelectionModel()));
        keymap = new FilesTableListener(this, files, tfiles.getSelectionModel(),
                                        tfiles.getActionMap(), tfiles.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW));
//        Command c = new Command('c', "aaa", new File("c:/"));
//        tkey = KeyStroke.getKeyStroke('c');
//        tfiles.getActionMap().put(c, new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO このメソッドを実装
//                System.out.println(e.getActionCommand());
//                System.out.println(tfiles.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).get(KeyStroke.getKeyStroke(e.getActionCommand().charAt(0))).getClass());
//            }
//        });
//        tfiles.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(tkey, c);
   }

    private KeyStroke tkey;
    private static String[] fileFields = {
        "dst", "FileName", "FileSize"
    };
    private static String[] actionFields = {
        "Key", "Keyword",  "DstDirectory"
    };
    private String[][] testFiles = {
        {"", "aaa", "12"},
        {"", "aagfer", "325"}
    };
    private String[][] testActions = {
        {"A", "aaaa", "ddd"},
        {"V", "fadr3e", "feayu"},
        {"C", "gfranjio", "bvahuio"}
    };
    
    private JTable tfiles;
    private DefaultTableModel files;
    private JTable tactions;
    private DefaultTableModel actions;
    private JLabel graphic;
    private JMenuBar menuBar;
    private MenuActionListener mActionListener;
    private FilesTableListener keymap;
    
    private Command[] actiondata;
    private boolean move;
    private File[] imageFiles;

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
                                                  IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        ApplicationFrame appFrame = new ApplicationFrame();
        appFrame.setTitle("Testあ");
        appFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuItem jmi;
        mActionListener = new MenuActionListener(this);
        // メニューバーの作成
        JMenuBar mb = new JMenuBar();
        // ファイル
        JMenu mFile = new JMenu(MenuActionListener.MENU_FILE);
        // 　フォルダ選択
        jmi = new JMenuItem(MenuActionListener.ITEM_FOLDERSELECT);
        jmi.addActionListener(mActionListener);
        mFile.add(jmi);
        // 　終了
        jmi = new JMenuItem(MenuActionListener.ITEM_EXIT);
        jmi.addActionListener(mActionListener);
        mFile.add(jmi);
        mb.add(mFile);
        // オプション
        JMenu mOption = new JMenu(MenuActionListener.MENU_OPTION);
        // 　設定
        jmi = new JMenuItem(MenuActionListener.ITEM_SETTING);
        jmi.addActionListener(mActionListener);
        mOption.add(jmi);
        mOption.addSeparator();
        jmi = new JMenuItem(MenuActionListener.ITEM_LOADSETTING);
        jmi.addActionListener(mActionListener);
        mOption.add(jmi);
        jmi = new JMenuItem(MenuActionListener.ITEM_SAVESETTING);
        jmi.addActionListener(mActionListener);
        mOption.add(jmi);
        mb.add(mOption);
        // オペレーション
        JMenu mOperation = new JMenu(MenuActionListener.MENU_OPERATION);
        //  実行
        jmi = new JMenuItem(MenuActionListener.ITEM_EXECUTE);
        jmi.addActionListener(mActionListener);
        mOperation.add(jmi);
        mb.add(mOperation);
        return mb;
    }
    
    public File[] getImageList() {
        return imageFiles;
    }
    
    public void setImageList(File[] images) {
        this.imageFiles = images;
        files.setRowCount(0);
        LinkedList<Object[]> li = new LinkedList<Object[]>();
        for (File f: images) {
            Object[] os = new Object[3];
            os[0] = null;
            os[1] = f.getAbsolutePath();
            os[2] = f.length();
            li.add(os);
        }
        Object[][] oo = new Object[li.size()][];
        for (Object[] os : li) files.addRow(os);
    }
    public void setImageFromRow(int row) {
        Object o = files.getValueAt(row, 1);
        graphic.setIcon(new ImageIcon(o.toString()));
    }
    
    public Command[] getActions() {
        return actiondata;
    }
    
    public void setActions(Command[] cc) {
        ActionMap am = tfiles.getActionMap();
        InputMap im = tfiles.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        am.clear();
        im.clear();
        this.actiondata = cc;
        actions.setRowCount(0);
        Object[] o;// = new Object[cc.length][];
        for(int i = 0; i < cc.length; i++) {
            o = new Object[]{
                cc[i].getKeyString(),
                cc[i].getKeyword(),
                cc[i].getDirectory().toString()
            };
            actions.addRow(o);
            am.put(cc[i], keymap);
            im.put(cc[i].getKeyStroke(), cc[i]);
        }
        am.put(cc[0], keymap);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cc[0]);
        //actions.setDataVector(oo, actionFields);
  }
    
    public JTable getActionsTable() {
        return tactions;
    }
    
    public DefaultTableModel getActionsTableModel() {
        return actions;
    }
    
    public void deleteActionsOperation() {
    }
    
    public DefaultTableModel getFilesTableModel() {
        return files;
    }
    
    public boolean getMoveEnable() {
        return this.move;
    }
    public void setMoveEnable(boolean b) {
        this.move = b;
    }
}
