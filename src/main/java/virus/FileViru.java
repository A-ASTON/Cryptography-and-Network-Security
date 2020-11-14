package virus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileViru {
    public File createFile(String path, String content) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
            FileOutputStream output = new FileOutputStream(file);
            
        }
        return file;
    }
}
