package vraisamis.jviewer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class FilesTableListener implements KeyListener {
    public FilesTableListener(ApplicationFrame parent, DefaultTableModel dtm, ListSelectionModel lsm) {
        super();
        this.parent = parent;
        this.dtm = dtm;
        this.lsm = lsm;
    }

    private ApplicationFrame parent;
    private DefaultTableModel dtm;
    private ListSelectionModel lsm;
    
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        // TODO Ç±ÇÃÉÅÉ\ÉbÉhÇé¿ëï
        Command[] cc = parent.getActions();
        StringBuilder sb = new StringBuilder();
        sb.append(dtm.getValueAt(lsm.getMinSelectionIndex(), 0));
        for (Command c: cc) {
            if (c.getKeyString().equals(new Character(e.getKeyChar()))) {
                sb.append(c.getKeyWord()).append(",");
            }
        }
        dtm.setValueAt(sb.toString(), lsm.getMinSelectionIndex(), 0);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
