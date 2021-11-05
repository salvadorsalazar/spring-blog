package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Ad;
import com.codeup.springblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdController {
private final AdRepository adRepository;
public AdController(AdRepository adRepository){
    this.adRepository = adRepository;
}
    @GetMapping("/ads")
    @ResponseBody
    public String showAds() {
        return "Showing all the ads";
    }

//    @GetMapping("/ads/{id}")
//    @ResponseBody
//    public String showSingleAd(@PathVariable long id) {
//        return "Showing ad: " + id;
//    }

//    @GetMapping("/ads/{title}")
//    @ResponseBody
//    public List<Ad> getByTitle(@PathVariable String title){
//    return adRepository.findByTitle(title);
//
//    }


    @GetMapping("/ads/{descr}")
    @ResponseBody
    public Ad getByTitle(@PathVariable String descr){
        return adRepository.findByDescription(descr);

    }

//
//    @PostMapping("/ads")
//    @ResponseBody
//    public String createAd() {
//        return "Creating an ad";
//    }

    @PostMapping("/ads")
    @ResponseBody
    public String createAd(@RequestBody Ad newAd) {
        adRepository.save(newAd);
        return String.format("Ad created wit an id of : %s", newAd.getId());
    }

}
