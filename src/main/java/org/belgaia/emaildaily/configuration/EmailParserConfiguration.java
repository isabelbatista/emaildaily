package org.belgaia.emaildaily.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Isabel Batista on 21.05.16.
 */
@Configuration
public class EmailParserConfiguration {

    @Value("${email.directory}")
    private String emailDirectory;

    @Value("${email.application.emails.directory}")
    private String emailApplicationEmailDirectory;

    @Bean
    public static EmailParserConfiguration emailParserConfiguration() {
        return new EmailParserConfiguration();
    }

    public String getEmailDirectoryPath() {
        return emailDirectory;
    }

    public String getEmailApplicationEmailDirectory() {
        return emailApplicationEmailDirectory;
    }
}
