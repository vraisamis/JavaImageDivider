package vraisamis.jviewer;

import java.awt.Dimension;

import java.io.File;

import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() {
        super();
        // ��ԊO���̐ݒ�
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // �O���̃t���[���̍쐬
        JSplitPane jsp = new JSplitPane();
        jsp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jsp.setPreferredSize(new Dimension(800, 600));
        jsp.setResizeWeight(0.5);
        // ����̍쐬
        files = new DefaultTableModel(fileFields, 0);
        tfiles = new JTable(files);
        JScrollPane sp1 = new JScrollPane(tfiles);
        sp1.setPreferredSize(new Dimension(400, 300));
        // �����̍쐬
        actions = new DefaultTableModel(actionFields, 0);
        tactions = new JTable(actions);
        JScrollPane sp2 = new JScrollPane(tactions);
        // �����̍쐬
        JSplitPane lPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp1, sp2);
        lPane.setPreferredSize(new Dimension(400, 600));
        lPane.setResizeWeight(0.7);
        jsp.setLeftComponent(lPane);
        // �E���̍쐬
        graphic = new JPanel();
        
        JScrollPane spGraphic = new JScrollPane(graphic, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setRightComponent(spGraphic);
        jsp.setPreferredSize(new Dimension(800, 600));
        jsp.setResizeWeight(0.5);
        this.getContentPane().add(jsp);
        this.setSize(800, 600);
        // ���j���[�̍쐬�ƃZ�b�g
        menuBar = this.createMenuBar();
        this.setJMenuBar(menuBar);
   }

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
    private JPanel graphic;
    private JMenuBar menuBar;
    private MenuActionListener mActionListener;

    public static void main(String[] args) {
        ApplicationFrame appFrame = new ApplicationFrame();
        appFrame.setTitle("Test��");
        appFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuItem jmi;
        mActionListener = new MenuActionListener(this);
        // ���j���[�o�[�̍쐬
        JMenuBar mb = new JMenuBar();
        // �t�@�C��
        JMenu mFile = new JMenu(MenuActionListener.MENU_FILE);
        // �@�t�H���_�I��
        jmi = new JMenuItem(MenuActionListener.ITEM_FOLDERSELECT);
        jmi.addActionListener(mActionListener);
        mFile.add(jmi);
        // �@�I��
        jmi = new JMenuItem(MenuActionListener.ITEM_EXIT);
        mFile.add(jmi);
        mb.add(mFile);
        // �I�v�V����
        JMenu mOption = new JMenu(MenuActionListener.MENU_OPTION);
        // �@�ݒ�
        jmi = new JMenuItem(MenuActionListener.ITEM_SETTING);
        mOption.add(jmi);
        mb.add(mOption);
        return mb;
    }
    
    public void SetImageList(File[] images) {
        LinkedList<Object[]> li = new LinkedList<Object[]>();
        for (File f: images) {
            Object[] os = new Object[3];
            os[0] = "";
            os[1] = f.getAbsolutePath();
            os[2] = f.length();
            li.add(os);
        }
        Object[][] oo = new Object[li.size()][];
        for (Object[] os : li) files.addRow(os);
    }
}
