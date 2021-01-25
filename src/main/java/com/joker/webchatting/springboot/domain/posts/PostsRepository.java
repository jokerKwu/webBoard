package com.joker.webchatting.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id desc ")
    List<Posts> findAllDesc();

    List<Posts> findByTitleContaining(String keyword);          //제목
    List<Posts> findByContentContaining(String keyword);        //내용
    List<Posts> findByAuthorContaining(String keyword);          //작성자
    List<Posts> findAllByContentContainingOrTitleContaining(String keyword1,String keyword2); //제목 + 내용
}
