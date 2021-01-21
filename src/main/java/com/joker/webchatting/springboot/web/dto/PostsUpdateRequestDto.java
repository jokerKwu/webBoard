package com.joker.webchatting.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private Long fileId;
    private String filename;

    @Builder
    public PostsUpdateRequestDto(String title, String content, Long fileId, String filename) {
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.filename = filename;
    }
}