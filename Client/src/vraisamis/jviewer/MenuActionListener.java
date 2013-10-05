package vraisamis.jviewer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JFileChooser;

public class MenuActionListener implements ActionListener {
    public MenuActionListener(ApplicationFrame parent) {
        super();
        this.parent = parent;
    }
    public static final String MENU_FILE = "File";
    public static final String ITEM_EXIT = "Exit";
    public static final String ITEM_FOLDERSELECT = "Select Folder";
    public static final String MENU_OPTION = "Option";
    public static final String ITEM_SETTING = "Settings";
    
    private ApplicationFrame parent;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Ç±ÇÃÉÅÉ\ÉbÉhÇé¿ëï
        if (e.getActionCommand().equals(ITEM_FOLDERSELECT)) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int selop = fc.showDialog(parent, "Use Folder");
            if (selop == JFileChooser.APPROVE_OPTION) parent.SetImageList(fc.getSelectedFile().listFiles(new ImageFileFilter()));
            return;
        }
    }
}
