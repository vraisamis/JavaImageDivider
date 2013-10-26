package vraisamis.jviewer;

import java.nio.file.Path;

public interface ImageOperation {
    public void operate(String dstFolder, String srcFolder, String filename);
}
