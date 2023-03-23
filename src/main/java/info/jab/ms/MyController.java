package info.jab.ms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    @GetMapping("api/v1/message")
    public String getMessage(){
        logger.info("GET Retrieving getMessage");
        return "Hello World";
    }

}
