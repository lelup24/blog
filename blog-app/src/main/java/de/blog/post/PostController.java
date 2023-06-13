package de.blog.post;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {


    @GetMapping("/posts")
    public String posts(HttpServletRequest request, HttpServletResponse response) {
        return "posts";
    }

}
