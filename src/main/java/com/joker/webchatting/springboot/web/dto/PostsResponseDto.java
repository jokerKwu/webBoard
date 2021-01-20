package com.joker.webchatting.springboot.web.dto;

import com.joker.webchatting.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long fileId;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.fileId = entity.getFileId();
        this.author = entity.getAuthor();
    }
}