package org.belgaia.emaildaily.EmailConnector;

import org.belgaia.emaildaily.configuration.EmailParserConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * Created by Isabel Batista on 21.05.16.
 */
@Component
public class EmailParser {

    private static final Logger LOG = LoggerFactory.getLogger(EmailParser.class);

    private static final String SEPARATOR_ID = "Keys";
    private static final Charset FILE_INPUT_CHARSET = Charset.forName("ISO-8859-1");

    @Autowired
    private EmailParserConfiguration configuration;

    public void separateEmailsFromInboxFile(Optional<File> inboxFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inboxFile.get()), FILE_INPUT_CHARSET));
        String line = "";
        int emailCounter = 0;

        BufferedWriter writer = null;
        boolean emailStarterIdFound = Boolean.FALSE;

        while((line = bufferedReader.readLine()) != null) {
            if(line.contains(SEPARATOR_ID)) {
                LOG.info("Found separator id for new email.");
                emailStarterIdFound = Boolean.TRUE;
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(configuration.getEmailDirectoryPath() + "email_" + emailCounter++))));
            } else if (emailStarterIdFound){
                writer.append(line);
            }
        }
        writer.close();
    }

    public void getEmailContentByEmailName(String emailName) {

    }
}
