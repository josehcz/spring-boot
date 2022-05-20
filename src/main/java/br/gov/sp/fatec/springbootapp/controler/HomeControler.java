package br.gov.sp.fatec.springbootapp.controler;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeControler {
    
    @GetMapping
    public String welcome(){
        return "Hello World";
    }
}
