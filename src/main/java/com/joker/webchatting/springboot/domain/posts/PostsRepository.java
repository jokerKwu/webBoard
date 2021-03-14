package com.joker.webchatting.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id desc ")
    List<Posts> findAllDesc();

    //제목
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and p.title like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByTitleDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword);

    //내용
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and p.content like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByContentDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword);

    //작성자
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and p.author like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByAuthorDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword);

    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and (p.title like concat('%', :keyword, '%') or p.content like concat('%', :keyword, '%'))  order by p.id desc",nativeQuery = true)
    List<Posts> findByTitleOrByContentDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword);


    //List<Posts> findByTitleContainingIgnoreCase(String keyword);          //제목
    //List<Posts> findByContentContainingIgnoreCase(String keyword);        //내용
    //List<Posts> findByAuthorContainingIgnoreCase(String keyword);          //작성자
    //List<Posts> findAllByContentContainingIgnoreCaseOrTitleContainingIgnoreCase(String keyword1,String keyword2); //제목 + 내용
}
