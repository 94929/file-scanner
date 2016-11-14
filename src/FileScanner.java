import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by jsh3571 on 06/11/2016.
 */

public class FileScanner {
    private List<String> emails;

    public FileScanner() {
        emails = new ArrayList<>();
    }

    public void scan(String path) {
        traverse(new File(path).listFiles());
    }

    public void traverse(File[] files) {
        for (File file : files) {
            if (file.isDirectory())
                traverse(file.listFiles());
            if (file.getName().substring(file.getName().lastIndexOf(".") + 1).
                    equals("txt"))
                printData(file);
        }
    }

    public void printData(File file) {
        emails = new ArrayList<>();

        try {
            BufferedReader br =
                    new BufferedReader(new FileReader(file.getName()));
            String line;
            while ((line = br.readLine()) != null) evaluate(line);
        } catch (Exception e) {
            e.getStackTrace();
        }

        // filePath, fileName, numOfEmails, emails
        System.out.println(file.getParentFile()+" "+ file.getName()+" "+
                emails.size()+" "+getEmails(emails));
    }

    public String getEmails(List<String> emails) {
        StringBuilder sb = new StringBuilder("");

        for (String email : emails)
            sb.append(email+" ; ");

        return sb.toString();
    }

    /* Should only initialise emails */
    public void evaluate(String line) {
        StringTokenizer st = new StringTokenizer(line);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (isValidEmail(token))
                emails.add(token);
        }
    }

    public boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
                "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\." +
                "[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

        return Pattern.compile(emailPattern).matcher(email).matches();
    }
}
