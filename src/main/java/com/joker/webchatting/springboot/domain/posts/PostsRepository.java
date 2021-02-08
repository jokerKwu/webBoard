package com.joker.webchatting.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id desc ")
    List<Posts> findAllDesc();

    List<Posts> findByTitleContainingIgnoreCase(String keyword);          //제목
    List<Posts> findByContentContainingIgnoreCase(String keyword);        //내용
    List<Posts> findByAuthorContainingIgnoreCase(String keyword);          //작성자
    List<Posts> findByTypeContaining(String keyword);
    List<Posts> findAllByContentContainingIgnoreCaseOrTitleContainingIgnoreCase(String keyword1,String keyword2); //제목 + 내용
}
