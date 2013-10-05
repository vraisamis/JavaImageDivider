package vraisamis.jviewer;

import java.io.File;
import java.io.FilenameFilter;

public class ImageFileFilter implements FilenameFilter {
    public ImageFileFilter() {
        super();
    }

    @Override
    public boolean accept(File dir, String name) {
        // TODO ‚±‚Ìƒƒ\ƒbƒh‚ğÀ‘•
        return name.matches(".*[.](jpg|jpeg|png)");
    }
}
