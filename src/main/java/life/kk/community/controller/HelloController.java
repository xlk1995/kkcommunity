package life.kk.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

<<<<<<< HEAD
    @GetMapping("/")
    public String index(){ return "index"; }
=======
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
>>>>>>> c3dec8f334079d584c621e7c49cba322017097d8
}
