package com.joker.webchatting.springboot.web.dto;


import com.joker.webchatting.springboot.domain.posts.Posts;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private Long fileId;
    private String filename;
    private String modifiedDate;
    private String type;
    private String pattern;
    private Long commentsCnt;

    public Posts toEntity(){
        Posts postEntity = Posts.builder()
                .title(title)
                .author(author)
                .content(content)
                .fileId(fileId)
                .filename(filename)
                .type(type)
                .pattern(pattern)
                .commentsCnt(commentsCnt)
                .build();
        return postEntity;
    }

    @Builder
    public PostDto(Long id, String title,String content, String author, Long fileId, String filename, String modifiedDate,String type, String pattern, Long commentsCnt){
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.modifiedDate = modifiedDate;
        this.filename = filename;
        this.fileId=fileId;
        this.type = type;
        this.pattern = pattern;
        this.commentsCnt = commentsCnt;
    }
}
