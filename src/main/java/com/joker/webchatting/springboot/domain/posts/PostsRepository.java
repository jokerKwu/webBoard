package com.joker.webchatting.springboot.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id desc ")
    List<Posts> findAllDesc();
    //타입 & 패턴 all 인 경우
    Page<Posts> findByTitleContainingIgnoreCaseOrderByIdDesc(String keyword, Pageable pageable);          //제목
    Page<Posts> findByContentContainingIgnoreCaseOrderByIdDesc(String keyword, Pageable pageable);        //내용
    Page<Posts> findByAuthorContainingIgnoreCaseOrderByIdDesc(String keyword, Pageable pageable);          //작성자
    Page<Posts> findAllByContentContainingIgnoreCaseOrTitleContainingIgnoreCaseOrderByIdDesc(String keyword1,String keyword2, Pageable pageable); //제목 + 내용

    // 타입 & 패턴 선택했을 때
    //제목
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and p.title like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    Page<Posts> findByTitleDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword, Pageable pageable);

    //내용
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and p.content like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    Page<Posts> findByContentDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword, Pageable pageable);

    //작성자
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and p.author like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    Page<Posts> findByAuthorDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword, Pageable pageable);

    //제목 + 내용
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and (p.title like concat('%', :keyword, '%') or p.content like concat('%', :keyword, '%'))  order by p.id desc",nativeQuery = true)
    Page<Posts> findByTitleOrByContentDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword, Pageable pageable);

    //타입만 all인 경우
    //제목
    @Query(value = "select * from posts p where p.pattern = :pattern and p.title like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    Page<Posts> findByTitleContainingAndByPatternContainingDesc( @Param ("pattern") String pattern, @Param("keyword") String keyword, Pageable pageable);

    //내용
    @Query(value = "select * from posts p where p.pattern = :pattern and p.content like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    Page<Posts> findByContentContainingAndByPatternContainingDesc( @Param ("pattern") String pattern, @Param("keyword") String keyword, Pageable pageable);

    //작성자
    @Query(value = "select * from posts p where  p.pattern = :pattern and p.author like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    Page<Posts> findByAuthorContainingAndByPatternContainingDesc( @Param ("pattern") String pattern, @Param("keyword") String keyword, Pageable pageable);

    //제목 + 내용
    @Query(value = "select * from posts p where  p.pattern = :pattern and (p.title like concat('%', :keyword, '%') or p.content like concat('%', :keyword, '%'))  order by p.id desc",nativeQuery = true)
    Page<Posts> findByTitleOrContentContainingAndByPatternContainingDesc( @Param ("pattern") String pattern, @Param("keyword") String keyword, Pageable pageable);


    //패턴만 all인 경우
    //제목
    @Query(value = "select * from posts p where p.type = :type  and p.title like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    Page<Posts> findByTitleContainingAndByTypeContainingDesc(@Param("type") String type, @Param("keyword") String keyword, Pageable pageable);

    //내용
    @Query(value = "select * from posts p where p.type = :type and p.content like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    Page<Posts> findByContentContainingAndByTypeContainingDesc(@Param("type") String type,  @Param("keyword") String keyword, Pageable pageable);

    //작성자
    @Query(value = "select * from posts p where p.type = :type and p.author like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    Page<Posts> findByAuthorContainingAndByTypeContainingDesc(@Param("type") String type,  @Param("keyword") String keyword, Pageable pageable);

    //제목 + 내용
    @Query(value = "select * from posts p where p.type = :type and (p.title like concat('%', :keyword, '%') or p.content like concat('%', :keyword, '%'))  order by p.id desc",nativeQuery = true)
    Page<Posts> findByTitleOrContentContainingAndByTypeContainingDesc(@Param("type") String type, @Param("keyword") String keyword, Pageable pageable);



}
