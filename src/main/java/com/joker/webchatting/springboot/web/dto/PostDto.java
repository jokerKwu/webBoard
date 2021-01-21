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
    private LocalDateTime modifiedDate;

    public Posts toEntity(){
        Posts postEntity = Posts.builder()
                .title(title)
                .author(author)
                .content(content)
                .fileId(fileId)
                .filename(filename)
                .build();
        return postEntity;
    }

    @Builder
    public PostDto(Long id, String title,String content, String author, Long fileId, String filename, LocalDateTime modifiedDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.modifiedDate = modifiedDate;
        this.filename = filename;
        this.fileId=fileId;
    }
}
