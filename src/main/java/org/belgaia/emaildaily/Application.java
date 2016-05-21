package org.belgaia.emaildaily;

import org.belgaia.emaildaily.EmailConnector.EmailDailyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * Created by Isabel Batista on 21.05.16.
 */
@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private EmailDailyService service;

    public static void main (String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public boolean initializeApp() {
        LOG.info("Initialize app by generating separated email files from Thunderbird Inbox file.");
        try {
            service.generateSeparateEmailFilesFromInboxDirectory();
            //service.generateFolderForSeparatedEmails();
            return Boolean.TRUE;
        } catch (IOException e) {
            LOG.error("Failed to generate separated email files: " + e.getMessage());
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
