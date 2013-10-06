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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
    }
    
    private Command[] actions;
    private JTable table;
    private DefaultTableModel dtm;
    private static final String[] captions = {
        "Key", "Keyword", "Directory"
    };
    private JButton addButton, deleteButton, exitButton;
    private int selected;
    private JDialog selfDlg;
    
    public void init(Command[] commands) {
        this.selfDlg = this;
        setBounds(0, 0, 400, 300);
        this.selected = -1;
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
        JScrollPane jsp = new JScrollPane(table);
        this.add(table, BorderLayout.CENTER);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addButton = new JButton("Add");
        panel.add(addButton);
        deleteButton = new JButton("Delete");
        panel.add(deleteButton);
        exitButton = new JButton("Exit");
        panel.add(exitButton);
        this.add(panel, BorderLayout.SOUTH);
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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO このメソッドを実装
                if (selected < 0) return;
                dtm.removeRow(selected);
            }
        });
        JDialog parentDlg = this;
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO このメソッドを実装
                processWindowEvent(new WindowEvent(selfDlg, WindowEvent.WINDOW_CLOSING));
            }
        });
    }
}
