package vraisamis.jviewer;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.DefaultListSelectionModel;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class FilesTableListener extends AbstractAction {
    public FilesTableListener(ApplicationFrame parent, DefaultTableModel dtm, ListSelectionModel lsm, ActionMap am, InputMap im) {
        super();
        this.parent = parent;
        this.dtm = dtm;
        this.lsm = lsm;
        this.am = am;
        this.im = im;
    }

    private ApplicationFrame parent;
    private DefaultTableModel dtm;
    private ListSelectionModel lsm;
    private ActionMap am;
    private InputMap im;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Ç±ÇÃÉÅÉ\ÉbÉhÇé¿ëï
        System.out.println(e.getActionCommand() + "::::" + im.get(KeyStroke.getKeyStroke(e.getActionCommand().charAt(0))));
        if (dtm.getRowCount() <= 0) return;
        dtm.setValueAt(im.get(KeyStroke.getKeyStroke(e.getActionCommand().charAt(0))), lsm.getMinSelectionIndex(), 0);
    }
}
