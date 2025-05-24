package com.drc.jobapplicationtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobPostController {

    @GetMapping("/posts")
    public String getAllJobPosts() {
        return "post/list";
    }
}
