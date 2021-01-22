package com.joker.webchatting.springboot.domain.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joker.webchatting.springboot.domain.BaseTimeEntity;
import com.joker.webchatting.springboot.domain.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comments extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long postId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String author;

    @Builder
    public Comments(Long postId, String content, String author) {
        this.postId = postId;
        this.content = content;
        this.author = author;
    }
    public void update(Long postId,String content) {
        this.content = content;
        this.postId = postId;
    }
}