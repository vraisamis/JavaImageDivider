package vraisamis.jviewer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;

public class Command {
    public Command(int key, File dir) {
        
    }
    public Command(int key, String keyword, File dir) {
        this.key = key;
        this.keyword = keyword;
        this.dir = dir;
    }
    
    private int key;
    private String keyword;
    private File dir;

    public String getKeyString() {
        return new Character((char)key).toString();
    }

    public String getKeyWord() {
        return keyword;
    }

    public File getDirectory() {
        return dir;
    }
    
    public String[] toStrings() {
        return new String[]{getKeyString(),getKeyWord(),getDirectory().toString()};
    }
}
