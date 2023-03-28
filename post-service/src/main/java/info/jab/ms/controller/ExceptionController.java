package info.jab.ms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/katakroker")
@Slf4j
public class ExceptionController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String forceException() {
        RuntimeException e = new RuntimeException("Katakroker");
        log.error(e.getMessage(), e);
        return "Something goes wrong";
    }
}
