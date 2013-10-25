package vraisamis.jviewer;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MoveImage implements ImageOperation {
    public MoveImage() {
        super();
    }

    @Override
    public void operate(String dstFolder, String srcFolder, String filename) {
        // TODO Ç±ÇÃÉÅÉ\ÉbÉhÇé¿ëï
        Path src = Paths.get(srcFolder, filename);
        if (Files.notExists(src)) return;
        Path dst = Paths.get(dstFolder, filename);
        if (Files.exists(dst)) {
            int i = 1;
            do {
                StringBuilder sb;
                int dot = filename.lastIndexOf(".");
                sb = new StringBuilder();
                sb.append(filename.substring(0, dot));
                sb.append("(").append(i++).append(")").append(filename.substring(dot));
                dst = Paths.get(dstFolder, sb.toString());
            } while (Files.exists(dst));
        }
        try {
            Files.move(src, dst);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
