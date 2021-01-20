package com.joker.webchatting.springboot.web.dto;
import com.joker.webchatting.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private Long fileId;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.fileId = entity.getFileId();
        this.modifiedDate = entity.getModifiedDate();
    }
}