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
    private String type;
    private String pattern;
    private Long commentsCnt;

    @Builder
    public PostsUpdateRequestDto(String title, String content, Long fileId, String filename,String type, String pattern,Long commentsCnt) {
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.filename = filename;
        this.type = type;
        this.pattern = pattern;
        this.commentsCnt = commentsCnt;
    }
}