package ws18.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingController {

    @GetMapping("/report")
    public String index() {
        return "Greetings from report endpoint";
    }

    @RequestMapping("/reporting")
    public String greeting() {
        return "Greetings from reporting endpoint";
    }

}
