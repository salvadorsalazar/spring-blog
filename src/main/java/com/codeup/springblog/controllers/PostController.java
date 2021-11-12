

package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostImage;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    private PostRepository postsDao;
    private UserRepository usersDao;
    private EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String index(Model viewModel) {
        List<Post> posts = postsDao.findAll();
        viewModel.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id) {
        return "Here is the post " + id;
    }

    @GetMapping("/posts/create")
    public String create(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String insert(@ModelAttribute Post post) {
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setUser(author);
        postsDao.save(post);
        emailService.prepareAndSend(post, "You submitted: " + post.getTitle(), post.getBody());
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String returnEditView(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postsDao.getById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post post) {
        Post editedPost = postsDao.getById(post.getId());
        editedPost.setTitle(post.getTitle());
        editedPost.setBody(post.getBody());
        postsDao.save(editedPost);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postsDao.deleteById(id);
        return "redirect:/posts";
    }
//    @GetMapping("/posts/{id}/show")
//    public String showPost(@PathVariable long id, Model model){
//        Post post = postsDao.getById(id);
//
//        model.addAttribute("post",post);
//        return "posts/show";
//    }

}



//ORIGINAL CODE TO BE PUT BACK JUST IN CASE DRAFT:
//@Controller
//public class PostController {
//
//    // injecting a dependency...
//
//    private PostRepository postsDao;
//    private final UserRepository userDao;
//    private final EmailService emailService;
//
//    public PostController(PostRepository postsDao, UserRepository userDao, EmailService emailService) {
//        this.postsDao = postsDao;
//        this.userDao = userDao;
//        this.emailService = emailService;
//    }
//
//    @GetMapping("/posts")
//    public String index(Model viewModel) {
//        // fetch all posts with postsDao
//        List<Post> posts = postsDao.findAll();
//        // send list of post objects to index view
//        // add list of posts to
//        viewModel.addAttribute("posts", posts);
//        return "posts/index";
//    }
//
//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String show(@PathVariable long id) {
//        return "Here is the post " + id;
//    }
//
//    @GetMapping("/posts/create")
//    public String create(Model model ) {
//        model.addAttribute("post", new Post());
//        return "posts/create";
//    }
//
//    @PostMapping("/posts/create")
//    public String insert(@ModelAttribute Post post, @RequestParam List<String> urls) {
//        List<PostImage> images = new ArrayList<>();
//        User author = userDao.getById(1L);
////        Post post = new Post(title, body,author);
//
//        // create list of post image objects to pass to the new post constructor
//        for (String url : urls) {
//            PostImage postImage = new PostImage(url);
//            postImage.setPost(post);
//            images.add(postImage);
//        }
//
//        post.setImages(images);
//
//        // save a post object with images
// post.setUser(author);
//
//        postsDao.save(post);
//        emailService.prepareAndSend(post,"you subniite" + post.getTitle(),post.getBody());
//
//        // modify the post index view to display post images
//        return "redirect:/posts";
//    }
//
//    // ================ EDIT
//    // add an endpoint (GET "/posts/{id}/edit) to send the user an edit post form / view
//    // create an edit post form
//    // create another endpoint (POST "/posts/{id}/edit") to handle the post request of editing a post
//    // add controller logic to edit the fields of the post and save the changes and redirect to the index view
//
//    @GetMapping("/posts/{id}/edit")
//    public String returnEditView(@PathVariable long id, Model viewModel) {
//        // send a edit form
//        viewModel.addAttribute("post", postsDao.getById(id));
//        return "posts/edit";
//    }
//
//    @PostMapping("/posts/{id}/edit")
//    public String updatePost(@ModelAttribute Post post) {
//        // use the new form inputs to update the existing post in the DB
//        // pull the existing post object from the database
//        Post editedPost = postsDao.getById(post.getId());
//        // set the title and body to the request param values
//        editedPost.setTitle(post.getTitle());
//        editedPost.setBody(post.getBody());
//        // persist the change in the db with the postsDao
//        postsDao.save(editedPost); // works to both update existing posts and insert new posts
//        return "redirect:/posts";
//    }
//
//
//
//    // ================ DELETE
//    // add another endpoint (POST "/posts/{id}/delete") to delete a post
//    // add needed controller logic to delete the correct post
//
//    @PostMapping("/posts/{id}/delete")
//    public String deletePost(@PathVariable long id) {
//        postsDao.deleteById(id);
//        return "redirect:/posts";
//    }
//
//
//}
////
////@Controller
////public class PostController {
//////dependancy injection code below
////    private final PostRepository postDao;
////    private final UserRepository userDao;
////
////    public PostController(PostRepository postDao, UserRepository userDao) {
////        this.postDao = postDao;
////        this.userDao = userDao;
////    }
////
////@GetMapping("/posts")
////    @ResponseBody
////    public String index(){
////        return " here are all the alds";
////    }
////    seed posts in the DB
////    fetch posts with postsDao
////
//
//
//    @GetMapping("/posts")
//    @ResponseBody
//    public String showPosts() {
//        return "Showing all posts:";
//    }
//
//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String showSinglePost(@PathVariable long id) {
//        return "Showing all posts: " + id;
//    }
//
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String postForm() {
//        return "Form to create a post: ";
//    }
//
//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String createPost() {
//        return "Creating a new post page";
//    }
//
////@GetMapping("/posts/delete/{id}")
////    public String deletePost(@PathVariable long id){
////postDao.deleteById(id);
////
////    return null;
////}
//
//
//
//
//
////    add an endpoint to sen the ser an edit post form
////    create an edit post form
////    creat anothoer endpoint to hanled the post rrequest of editing a post
//}