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
    private String filename;
    private String modifiedDate;
    private String type;
    private String pattern;
    private Long commentsCnt;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.fileId = entity.getFileId();
        this.filename = entity.getFilename();
        this.modifiedDate = entity.getModifiedDate();
        this.type = entity.getType();
        this.pattern = entity.getPattern();
        this.commentsCnt = entity.getCommentsCnt();
    }
}