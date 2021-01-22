package com.joker.webchatting.springboot.web.dto;

import com.joker.webchatting.springboot.domain.posts.Comments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsSaveRequestDto {
    private String content;
    private String author;
    private Long postId;

    @Builder
    public CommentsSaveRequestDto(String content, String author, Long postId){
        this.content = content;
        this.author = author;
        this.postId = postId;
    }
    public Comments toEntity(){
        return Comments.builder()
                .content(content)
                .author(author)
                .postId(postId)
                .build();
    }
}
