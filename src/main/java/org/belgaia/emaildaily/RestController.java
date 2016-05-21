package org.belgaia.emaildaily;

import org.belgaia.emaildaily.EmailConnector.EmailDailyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * Created by Isabel Batista on 21.05.16.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/emaildaily")
public class RestController {

    private static final Logger LOG = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private EmailDailyService emailService;

    @RequestMapping(value="/info", method= RequestMethod.GET)
    public String info() {
        return "Welcome to the Emaildaily";
    }

    @RequestMapping(value="/findEmails")
    public String findEmails() {
        try {
            return emailService.getEmailContent();
        } catch (IOException e) {
            LOG.error("Failed to get email content: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}