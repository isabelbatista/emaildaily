package org.belgaia.emaildaily.EmailConnector;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Isabel Batista on 21.05.16.
 */
@Service
public class EmailDailyService {

    private static final String THUNDERBIRD_EMAIL_DIR = "/Users/Isa/Library/Thunderbird/Profiles/htfufzvv.default/Mail/pop.gmx.net";
    private static final String THUNDERBIRD_EMAIL_FILE = "Inbox";
    private static final Charset FILE_INPUT_CHARSET = Charset.forName("ISO-8859-1");

    public String getEmailContent() throws IOException {
        return getInboxContent();
    }

    private String getInboxContent() throws IOException {
        List<File> files = Arrays.asList(getFilesFromEmailDirectory());

        StringBuilder emailContent = new StringBuilder("Content of Emails:\n");
        for(File emailDirFile : files) {
            if(emailDirFile.getName().equals(THUNDERBIRD_EMAIL_FILE)) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(emailDirFile), FILE_INPUT_CHARSET));

                emailContent = new StringBuilder();

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    emailContent.append(line);
                }
            }
        }
        return emailContent.toString();
    }

    private File[] getFilesFromEmailDirectory() {
        File directory = new File(THUNDERBIRD_EMAIL_DIR);
        return directory.listFiles();
    }
}
