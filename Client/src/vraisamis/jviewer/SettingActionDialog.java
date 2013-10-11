package vraisamis.jviewer;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;

import java.io.File;

import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class SettingActionDialog extends JDialog {
    public SettingActionDialog(Window window, String string, Dialog.ModalityType modalityType,
                               GraphicsConfiguration graphicsConfiguration) {
        super(window, string, modalityType, graphicsConfiguration);
    }

    public SettingActionDialog(Window window, String string, Dialog.ModalityType modalityType) {
        super(window, string, modalityType);
    }

    public SettingActionDialog(Window window, String string) {
        super(window, string);
    }

    public SettingActionDialog(Window window, Dialog.ModalityType modalityType) {
        super(window, modalityType);
    }

    public SettingActionDialog(Window window) {
        super(window);
    }

    public SettingActionDialog(Dialog dialog, String string, boolean b, GraphicsConfiguration graphicsConfiguration) {
        super(dialog, string, b, graphicsConfiguration);
    }

    public SettingActionDialog(Dialog dialog, String string, boolean b) {
        super(dialog, string, b);
    }

    public SettingActionDialog(Dialog dialog, String string) {
        super(dialog, string);
    }

    public SettingActionDialog(Dialog dialog, boolean b) {
        super(dialog, b);
    }

    public SettingActionDialog(Dialog dialog) {
        super(dialog);
    }

    public SettingActionDialog(Frame frame, String string, boolean b, GraphicsConfiguration graphicsConfiguration) {
        super(frame, string, b, graphicsConfiguration);
    }

    public SettingActionDialog(Frame frame, String string, boolean b) {
        super(frame, string, b);
    }

    public SettingActionDialog(Frame frame, String string) {
        super(frame, string);
    }

    public SettingActionDialog(Frame frame, boolean b) {
        super(frame, b);
    }

    public SettingActionDialog(Frame frame) {
        super(frame);
    }

    public SettingActionDialog() {
        super();
    }
    
    public SettingActionDialog(ApplicationFrame frame, String string, boolean b) {
        super(frame, string, b);
        this.frame = frame;
    }
    
    private ApplicationFrame frame;
    private Command[] actions;
    private JTable table;
    private DefaultTableModel dtm;
    private static final String[] captions = {
        "Key", "Keyword", "Directory"
    };
    private JButton addButton, deleteButton, exitButton;
    private JTextField keyText, keywordText, dirText;
    private JButton folderBrowseButton;
    private int selected;
    private JDialog selfDlg;
    
    public void init(Command[] commands) {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.selfDlg = this;
        setBounds(0, 0, 400, 300);
        this.selected = -1;
        // create table
        this.dtm = new DefaultTableModel(captions, 0);
        this.table = new JTable(dtm);
        if ((this.actions = commands) != null) {
        LinkedList<Object[]> li = new LinkedList<Object[]>();
            for (Command c: actions) {
                Object[] os = new Object[3];
                os[0] = c.getKeyString();
                os[1] = c.getKeyWord();
                os[2] = c.getDirectory();
                li.add(os);
            }
            for (Object[] o: li) dtm.addRow(o);
        }
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(30);
        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(2).setPreferredWidth(240);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 370, 200);
        this.add(jsp);
        // create inputer
        JPanel panel;
        panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        keyText = new JTextField(2);
        panel.add(keyText);
        keywordText = new JTextField(8);
        panel.add(keywordText);
        dirText = new JTextField(15);
        panel.add(dirText);
        folderBrowseButton = new JButton("Browse...");
        folderBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // TODO このメソッドを実装
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int selop = fc.showDialog(selfDlg, "Select dst folder");
                if (selop == JFileChooser.APPROVE_OPTION) dirText.setText(fc.getSelectedFile().toString());

            }
        });
        panel.add(folderBrowseButton);
        this.add(panel);
        // create buttons
        panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addButton = new JButton("Add");
        panel.add(addButton);
        deleteButton = new JButton("Delete");
        panel.add(deleteButton);
        exitButton = new JButton("Exit");
        panel.add(exitButton);
        this.add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        ListSelectionModel lsm = table.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // TODO このメソッドを実装
                if (e.getValueIsAdjusting()) return;
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                if (lsm.isSelectionEmpty()) {
                    selected = -1;
                    return;
                }
                selected = lsm.getMinSelectionIndex();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // TODO このメソッドを実装
                if (keyText.getText().length() != 1) return;
                if (keywordText.getText().length() == 0) return;
                if (dirText.getText().length() == 0) return;
                String[] strs = {
                    keyText.getText(),
                    keywordText.getText(),
                    dirText.getText()
                };
                keyText.setText("");
                keywordText.setText("");
                dirText.setText("");
                dtm.addRow(strs);
            }
            });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO このメソッドを実装
                if (selected < 0) return;
                dtm.removeRow(selected);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO このメソッドを実装
                LinkedList<Command> l = new LinkedList<Command>();
                DefaultTableModel d = frame.getActionsTableModel();
                for (int i = 0, n = dtm.getRowCount(); i < n; i++) {
                    l.add(new Command(
                        dtm.getValueAt(i, 0).toString().charAt(0),
                        dtm.getValueAt(i, 1).toString(),
                        new File(dtm.getValueAt(i, 2).toString())
                    ));
                }
                Command[] cc = new Command[l.size()];
                for (int i = 0, n = l.size(); i < n; i++) cc[i] = l.pollFirst();
                frame.setActions(cc);
                processWindowEvent(new WindowEvent(selfDlg, WindowEvent.WINDOW_CLOSING));
                //System.out.println(frame.getActionsTable().getRowCount());
            }
        });
        
        frame.deleteActionsOperation();
    }
}
