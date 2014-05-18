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
        // TODO ‚±‚Ìƒƒ\ƒbƒh‚ðŽÀ‘•
        if (e.getValueIsAdjusting()) return;
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (lsm.isSelectionEmpty()) {
        } else {
            int row = lsm.getMinSelectionIndex();
            System.out.println("row is " + row);
            if (row >= 0 && row < parent.getFilesTableModel().getRowCount()) parent.setImageFromRow(row);
        }
    }
}
