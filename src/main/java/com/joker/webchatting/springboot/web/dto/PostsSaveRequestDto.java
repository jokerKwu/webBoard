package com.joker.webchatting.springboot.web.dto;

import com.joker.webchatting.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private String filename;
    private Long fileId;
    private String type;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String filename , Long fileId,String type) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.fileId = fileId;
        this.filename = filename;
        this.type = type;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .fileId(fileId)
                .filename(filename)
                .type(type)
                .build();
    }

}