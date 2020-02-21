package com.craigslist.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/")
    public String listJobs(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String jobForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/add")
    public String processform(@Valid Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "jobform";
        }
        jobRepository.save(job);
        return "redirect:/";
    }

    @RequestMapping("/about")
    public String about() {
   /*     model.addAttribute("jobs", jobRepository.findAll());*/
        return "about";
    }

    /*detail*/
    @RequestMapping("/detail/{id}")
    public String jobDetail(@PathVariable("id") long id, Model model) {
        model.addAttribute("job", jobRepository.findById(id).get());
        return "detail";
    }

    /*update*/
    @RequestMapping("/update/{id}")
    public String jobUpdate(@PathVariable("id") long id, Model model) {
        model.addAttribute("job", jobRepository.findById(id).get());
        return "jobform";
    }

    /*delete*/
    @RequestMapping("/delete/{id}")
    public String jobUpdate(@PathVariable("id") long id) {
        jobRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/test}")
    public String test(Model model){
     model.addAttribute("test","test");
        return "test";
    }
}
