package vraisamis.jviewer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;

import javax.swing.KeyStroke;

public class Command {
    public Command(int key, File dir) {
        
    }
    public Command(char key, String keyword, File dir) {
        this.key = key;
        this.stroke = KeyStroke.getKeyStroke(key);
        this.keyword = keyword;
        this.dir = dir;
    }
    
    private KeyStroke stroke;
    private char key;
    private String keyword;
    private File dir;

    public KeyStroke getKeyStroke() {
        return stroke;
    }
    
    public String getKeyString() {
        return new Character(key).toString();
    }

    public String getKeyword() {
        return keyword;
    }

    public File getDirectory() {
        return dir;
    }
    
    public String[] toStrings() {
        return new String[]{getKeyString(),getKeyword(),getDirectory().toString()};
    }
    
    @Override
    public String toString() {
        return getKeyString();
    }
}
