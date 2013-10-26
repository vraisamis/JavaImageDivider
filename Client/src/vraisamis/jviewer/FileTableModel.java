package vraisamis.jviewer;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class FileTableModel extends DefaultTableModel {
    public FileTableModel(Object[][] object, Object[] object1) {
        super(object, object1);
    }

    public FileTableModel(Vector vector, Vector vector1) {
        super(vector, vector1);
    }

    public FileTableModel(Object[] object, int i) {
        super(object, i);
    }

    public FileTableModel(Vector vector, int i) {
        super(vector, i);
    }

    public FileTableModel(int i, int i1) {
        super(i, i1);
    }

    public FileTableModel() {
        super();
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
