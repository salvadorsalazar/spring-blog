

package com.codeup.springblog.controllers;

import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PostController {
//dependancy injection code below
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }


//    seed posts in the DB
//    fetch posts with postsDao
//


    @GetMapping("/posts")
    @ResponseBody
    public String showPosts() {
        return "Showing all posts:";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showSinglePost(@PathVariable long id) {
        return "Showing all posts: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postForm() {
        return "Form to create a post: ";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "Creating a new post page";
    }

//@GetMapping("/posts/delete/{id}")
//    public String deletePost(@PathVariable long id){
//postDao.deleteById(id);
//
//    return null;
//}





//    add an endpoint to sen the ser an edit post form
//    create an edit post form
//    creat anothoer endpoint to hanled the post rrequest of editing a post
}