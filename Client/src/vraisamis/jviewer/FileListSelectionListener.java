package vraisamis.jviewer;

import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FileListSelectionListener implements ListSelectionListener {
    public FileListSelectionListener(ApplicationFrame af) {
        super();
        this.parent = af;
    }
    
    private ApplicationFrame parent;


    @Override
    public void valueChanged(ListSelectionEvent e) {
        // TODO Ç±ÇÃÉÅÉ\ÉbÉhÇé¿ëï
        if (e.getValueIsAdjusting()) return;
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (lsm.isSelectionEmpty()) {
        } else {
            int row = lsm.getMinSelectionIndex();
            parent.setImageFromRow(row);
        }
    }
}
