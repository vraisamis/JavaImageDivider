package vraisamis.jviewer;

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

    String getKeyString() {
        return new Integer(key).toString();
    }

    String getKeyWord() {
        return keyword;
    }

    File getDirectory() {
        return dir;
    }
}
