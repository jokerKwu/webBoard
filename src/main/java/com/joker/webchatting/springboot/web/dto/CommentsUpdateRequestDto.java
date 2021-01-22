package com.joker.webchatting.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsUpdateRequestDto {
    private String author;
    private Long postId;
    private String content;

    @Builder
    public CommentsUpdateRequestDto(String content, String author, Long postId){
        this.content = content;
        this.author = author;
        this.postId = postId;
    }
}
