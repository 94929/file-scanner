
/**
 * Created by jsh3571 on 06/11/2016.
 */

public class Main {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        FileScanner fs = new FileScanner("txt");

        // Takes an input of the 'root' path
        fs.scan(path);
    }
}
