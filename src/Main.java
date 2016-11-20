
/**
 * Created by jsh3571 on 06/11/2016.
 */

public class Main {
    public static void main(String[] args) {
        // Obtaining the root path
        String path = System.getProperty("user.dir");

        // The FileScanner will scan text files only
        FileScanner fs = new FileScanner("txt");

        // Takes an input of the 'root' path
        fs.scan(path);
    }
}
