package com.joker.webchatting.springboot.web.dto;

import com.joker.webchatting.springboot.domain.posts.Comments;
import lombok.Getter;

@Getter
public class CommentsListResponseDto {
    private Long id;
    private String content;
    private String author;
    private Long postId;

    public CommentsListResponseDto(Comments entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.postId = entity.getPostId();
    }
}
