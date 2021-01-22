package com.joker.webchatting.springboot.web.dto;

import com.joker.webchatting.springboot.domain.posts.Comments;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CommentsRequestDto {
    @NotBlank
    private String content;

    @NotBlank
    private Long postId;

    @NotBlank
    private String created_by;

    public CommentsRequestDto(Comments entity) {
    }
}