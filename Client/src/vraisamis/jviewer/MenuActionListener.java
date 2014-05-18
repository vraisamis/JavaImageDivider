package vraisamis.jviewer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;

import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;

import java.util.LinkedList;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

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
    public static final String ITEM_LOADSETTING = "Load Setting File";
    public static final String ITEM_SAVESETTING = "Save Setting File";
    public static final String MENU_OPERATION = "Operations";
    public static final String ITEM_EXECUTE = "Execute SAVE";
    
    private ApplicationFrame parent;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO ���̃��\�b�h������
        if (e.getActionCommand().equals(ITEM_FOLDERSELECT)) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int selop = fc.showDialog(parent, "Use Folder");
            if (selop == JFileChooser.APPROVE_OPTION) parent.setImageList(fc.getSelectedFile().listFiles(new ImageFileFilter()));
            return;
        }
        
        if(e.getActionCommand().equals(ITEM_EXIT)) {
            System.exit(0);
        }
        
        if(e.getActionCommand().equals(ITEM_SETTING)) {
            //LinkedList<Command> l = new LinkedList<Command>();
            SettingActionDialog dlg = new SettingActionDialog(parent, "Settings", true);
            dlg.init(parent.getActions());
            dlg.setVisible(true);
        }
        
        if(e.getActionCommand().equals(ITEM_LOADSETTING)) {
            JsonReader jr = null;
            try {
                jr = new JsonReader(new FileReader("./setting.ini"));
                Command[] cm = new Gson().fromJson(jr, Command[].class);
                parent.setActions(cm);
            } catch (IOException ex) {
            } finally {
                try {jr.close();} catch (IOException ex) { }
            }
            //System.exit(0);
        }
        
        if(e.getActionCommand().equals(ITEM_SAVESETTING)) {
            //GsonBuilder gb = new GsonBuilder();
            Gson gson = new Gson();
            JsonWriter jw = null;
            try {
                jw = new JsonWriter(new FileWriter("./setting.ini"));
                gson.toJson(parent.getActions(), Command[].class, jw);
            } catch (IOException f) {
            } finally {
                try {jw.close();} catch (IOException f) { }
            }
            //System.exit(0);
        }
        
        if (e.getActionCommand().equals(ITEM_EXECUTE)) {
            ImageOperation operation = parent.getMoveEnable() ? new MoveImage() : new CopyImage();
            DefaultTableModel dtm = parent.getFilesTableModel();
            int i, n = dtm.getRowCount();
            for (i = 0; i < n; i++) {
                if (dtm.getValueAt(i, 0) == null) continue;
                Command c = (Command)dtm.getValueAt(i, 0);
                System.out.println(c.getDirectory());
                File f = new File(dtm.getValueAt(i, 1).toString());
                System.out.println(f.getParent()); // folder
                System.out.println(f.getName()); // filename
                operation.operate(c.getDirectory().toString(), f.getParent().toString(), f.getName().toString());
            }
            parent.setImageList(new File(dtm.getValueAt(0, 1).toString()).getParentFile().listFiles(new ImageFileFilter()));
            
        }
    }
}
