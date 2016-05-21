package org.belgaia.emaildaily.EmailConnector;

import org.belgaia.emaildaily.configuration.EmailParserConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Isabel Batista on 21.05.16.
 */
@Service
public class EmailDailyService {

    private static final String THUNDERBIRD_EMAIL_FILE = "Inbox";
    private static final Charset FILE_INPUT_CHARSET = Charset.forName("ISO-8859-1");

    @Autowired
    private EmailParser emailParser;

    @Autowired
    private EmailParserConfiguration configuration;

    public String getEmailContent() throws IOException {
        return getInboxContent();
    }

    public String getEmailContent(String emailName) throws IOException {
        File emailFile = new File(configuration.getEmailDirectoryPath() + "/" + emailName);
        StringBuilder emailContent = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(emailFile), FILE_INPUT_CHARSET));
        String line = "";
        while((line = bufferedReader.readLine()) != null) {
            emailContent.append(line);
        }
        return emailContent.toString();
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
        File directory = new File(configuration.getEmailApplicationEmailDirectory());
        return directory.listFiles();
    }

    public void generateSeparateEmailFilesFromInboxDirectory() throws IOException {
        generateFolderForSeparatedEmails();
        emailParser.separateEmailsFromInboxFile(getInboxFile());
    }

    private Optional<File> getInboxFile() {
        List<File> files = Arrays.asList(getFilesFromEmailDirectory());
        Optional<File> inboxFile = Optional.empty();
        for(File emailDirFile : files) {
            if (emailDirFile.getName().equals(THUNDERBIRD_EMAIL_FILE)) {
                inboxFile = Optional.of(emailDirFile);
            }
        }
        return inboxFile;
    }

    public void generateFolderForSeparatedEmails() throws IOException {
        Path path = Paths.get(configuration.getEmailDirectoryPath());
        Files.createDirectories(path);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
