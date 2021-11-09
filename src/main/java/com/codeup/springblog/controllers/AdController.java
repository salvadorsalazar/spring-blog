package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.AdRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class AdController {

    private final AdRepository adRepository;
    private final UserRepository userRepository;

    private final EmailService emailService;

    public AdController(AdRepository adRepository, EmailService emailService,UserRepository userRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;

        this.emailService = emailService;
    }

    @GetMapping("/ads")
//    @ResponseBody
    public String showAds(Model model) {
        model.addAttribute("ads",adRepository.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public Ad showSingleAd(@PathVariable long id) {
        return adRepository.findById(id).get();
    }

    @GetMapping("/ads/byTitle")
    @ResponseBody
    public List<Ad> getByTitle(@RequestParam(name="title") String title){
        return adRepository.findByTitle(title);
    }

    @GetMapping("/ads/byTitlePart")
    @ResponseBody
    public List<Ad> getByTitlePart(@RequestParam(name="titlePart") String titlePart){
        return adRepository.findByTitleLike(titlePart);
    }

    @GetMapping("/ads/byDescription")
    @ResponseBody
    public Ad getByDescription(@RequestParam(name="description") String description){
        return adRepository.findByDescription(description);
    }

    @PostMapping("/ads")
    @ResponseBody
    public String createAd(@RequestBody Ad newAd) {
        adRepository.save(newAd);
        return String.format("Ad created with an ID of: %s", newAd.getId());
    }



    @GetMapping("/ads/create")
    public String showCreateAdsForm(Model model){
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAdWithForm(@ModelAttribute Ad ad){
        User user = userRepository.getById(1L);
        ad.setOwner(user);
        adRepository.save(ad);
        emailService.prepareAndSend(ad,"you created " + ad.getTitle(),
                ad.getDescription());
        return "redirect:/ads";

    }

// To test the creation of an ad without the concern of the view, try using a fetch!
// After that works, binding your data to a view will require much less troubleshooting
// because you KNOW the repo, entity, and controller methods are fine!

/*
    fetch("/ads",
    {
        method: "POST",
        headers: {"content-type": "application/json"},
        body: JSON.stringify({
            title: "intellisense is awful",
            description: "for sale: one awful predictive text ai. price negotiable"
            })
    })
    .then(res => res.json())
    .then(data => console.log(data))
* */


}










//@Controller
//public class AdController {
//private final AdRepository adRepository;
//public AdController(AdRepository adRepository){
//    this.adRepository = adRepository;
//}
//    @GetMapping("/ads")
//    @ResponseBody
//    public String showAds() {
//        return "Showing all the ads";
//    }
//
////    @GetMapping("/ads/{id}")
////    @ResponseBody
////    public String showSingleAd(@PathVariable long id) {
////        return "Showing ad: " + id;
////    }
//
////    @GetMapping("/ads/{title}")
////    @ResponseBody
////    public List<Ad> getByTitle(@PathVariable String title){
////    return adRepository.findByTitle(title);
////
////    }
//
//
//    @GetMapping("/ads/{descr}")
//    @ResponseBody
//    public Ad getByTitle(@PathVariable String descr){
//        return adRepository.findByDescription(descr);
//
//    }
//
////
////    @PostMapping("/ads")
////    @ResponseBody
////    public String createAd() {
////        return "Creating an ad";
////    }
//
//    @PostMapping("/ads")
//    @ResponseBody
//    public String createAd(@RequestBody Ad newAd) {
//        adRepository.save(newAd);
//        return String.format("Ad created wit an id of : %s", newAd.getId());
//    }
//
//}
