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
    @Builder
    public PostsSaveRequestDto(String title, String content, String author, String filename , Long fileId) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.fileId = fileId;
        this.filename = filename;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .fileId(fileId)
                .filename(filename)
                .build();
    }

}