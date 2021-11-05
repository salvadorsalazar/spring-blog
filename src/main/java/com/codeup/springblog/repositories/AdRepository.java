package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// By extending JpaRepository, our AdRepository is able to have access to a TON of baked-in methods
public interface AdRepository extends JpaRepository<Ad, Long> { // Ad - which entity is the repo related to, Long - what is Ad's Primary Key type?


List<Ad> findByTitle(String title);//select * from ads where title = :title limit =1


Ad findByDescription(String title);
@Query(nativeQuery = true,value="select * from ads where title like :term%")
List<Ad> findByTitleLike(@Param("t")String term);


}