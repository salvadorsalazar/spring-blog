package com.codeup.springblog.controllers;


import com.codeup.springblog.controllers.models.Coffee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CoffeeController {

    @GetMapping("/coffee")
    public String coffeeInfo(){
        return "views-lec/coffee";
    }

    @GetMapping("/coffee/{roast}")
    public String roastSelection(@PathVariable String roast, Model model){
//        model.addAttribute("roast",roast);
       Coffee selection = new Coffee();
       selection.setRoast(roast);
       if(roast.equals("dark")){
           selection.setOrigin("Columbia");
       }else if (roast.equals("medium")) {
           selection.setOrigin("new Guinea");
       }else{
           selection.setOrigin("kenya");
       }
       model.addAttribute("selection",selection);
        return "views-lec/coffee";

    }

}
