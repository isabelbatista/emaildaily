package org.belgaia.emaildaily;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Isabel Batista on 21.05.16.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/emaildaily")
public class RestController {

    @RequestMapping(value="/info", method= RequestMethod.GET)
    public String info() {
        return "Welcome to the Emaildaily";
    }
}
