package com.joker.webchatting.springboot.domain.posts;

import com.joker.webchatting.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다. */
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Column
    private Long fileId;

    @Column
    private String filename;


    @Builder
    public Posts(String title, String content, String author, Long fileId, String filename) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.fileId = fileId;
        this.filename = filename;
    }

    public void update(String title, String content,Long fileId, String filename) {
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.filename = filename;

    }
}