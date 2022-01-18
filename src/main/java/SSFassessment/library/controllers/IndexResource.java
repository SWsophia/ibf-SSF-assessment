package SSFassessment.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = { "/", "/index.html" })
public class IndexResource {

    
    @GetMapping
    public String index() {
        return "index";
    }

    
}
