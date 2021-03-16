package com.joker.webchatting.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id desc ")
    List<Posts> findAllDesc();
    //타입 & 패턴 all 인 경우
    List<Posts> findByTitleContainingIgnoreCaseOrderByIdDesc(String keyword);          //제목
    List<Posts> findByContentContainingIgnoreCaseOrderByIdDesc(String keyword);        //내용
    List<Posts> findByAuthorContainingIgnoreCaseOrderByIdDesc(String keyword);          //작성자
    List<Posts> findAllByContentContainingIgnoreCaseOrTitleContainingIgnoreCaseOrderByIdDesc(String keyword1,String keyword2); //제목 + 내용

    // 타입 & 패턴 선택했을 때
    //제목
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and p.title like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByTitleDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword);

    //내용
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and p.content like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByContentDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword);

    //작성자
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and p.author like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByAuthorDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword);

    //제목 + 내용
    @Query(value = "select * from posts p where p.type = :type and p.pattern = :pattern and (p.title like concat('%', :keyword, '%') or p.content like concat('%', :keyword, '%'))  order by p.id desc",nativeQuery = true)
    List<Posts> findByTitleOrByContentDesc(@Param("type") String type, @Param ("pattern") String pattern, @Param("keyword") String keyword);

    //타입만 all인 경우
    //제목
    @Query(value = "select * from posts p where p.pattern = :pattern and p.title like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByTitleContainingAndByPatternContainingDesc( @Param ("pattern") String pattern, @Param("keyword") String keyword);

    //내용
    @Query(value = "select * from posts p where p.pattern = :pattern and p.content like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByContentContainingAndByPatternContainingDesc( @Param ("pattern") String pattern, @Param("keyword") String keyword);

    //작성자
    @Query(value = "select * from posts p where  p.pattern = :pattern and p.author like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByAuthorContainingAndByPatternContainingDesc( @Param ("pattern") String pattern, @Param("keyword") String keyword);

    //제목 + 내용
    @Query(value = "select * from posts p where  p.pattern = :pattern and (p.title like concat('%', :keyword, '%') or p.content like concat('%', :keyword, '%'))  order by p.id desc",nativeQuery = true)
    List<Posts> findByTitleOrContentContainingAndByPatternContainingDesc( @Param ("pattern") String pattern, @Param("keyword") String keyword);


    //패턴만 all인 경우
    //제목
    @Query(value = "select * from posts p where p.type = :type  and p.title like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByTitleContainingAndByTypeContainingDesc(@Param("type") String type, @Param("keyword") String keyword);

    //내용
    @Query(value = "select * from posts p where p.type = :type and p.content like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByContentContainingAndByTypeContainingDesc(@Param("type") String type,  @Param("keyword") String keyword);

    //작성자
    @Query(value = "select * from posts p where p.type = :type and p.author like concat('%', :keyword, '%')  order by p.id desc",nativeQuery = true)
    List<Posts> findByAuthorContainingAndByTypeContainingDesc(@Param("type") String type,  @Param("keyword") String keyword);

    //제목 + 내용
    @Query(value = "select * from posts p where p.type = :type and (p.title like concat('%', :keyword, '%') or p.content like concat('%', :keyword, '%'))  order by p.id desc",nativeQuery = true)
    List<Posts> findByTitleOrContentContainingAndByTypeContainingDesc(@Param("type") String type, @Param("keyword") String keyword);



}
