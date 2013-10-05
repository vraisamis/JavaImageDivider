package vraisamis.jviewer;

import java.awt.Dimension;

import javax.swing.*;

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
        files = new JTable(testFiles, fileFields);
        JScrollPane sp1 = new JScrollPane(files);
        sp1.setPreferredSize(new Dimension(400, 300));
        // �����̍쐬
        actions = new JTable(testActions, actionFields);
        JScrollPane sp2 = new JScrollPane(actions);
        // �����̍쐬
        JSplitPane lPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp1, sp2);
        lPane.setPreferredSize(new Dimension(400, 600));
        lPane.setResizeWeight(0.7);
        jsp.setLeftComponent(lPane);
        // �E���̍쐬
        graphic = new JPanel();
        
        JScrollPane spGraphic = new JScrollPane(graphic, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setRightComponent(spGraphic);
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
    
    private JTable files;
    private JTable actions;
    private JPanel graphic;
    private JMenuBar menuBar;

    public static void main(String[] args) {
        ApplicationFrame appFrame = new ApplicationFrame();
        appFrame.setTitle("Test��");
        appFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenu mFile = new JMenu("File");
        JMenuBar jmb = new JMenuBar();
        return null;
    }
}
