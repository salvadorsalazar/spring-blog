package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

//  List<Post> findAllByTitle(String title);
//
//Post editPost(String title);
//
//Post deletePost(String title);


}
