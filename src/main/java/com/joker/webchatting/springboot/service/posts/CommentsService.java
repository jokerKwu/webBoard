package com.joker.webchatting.springboot.service.posts;

import com.joker.webchatting.springboot.domain.posts.*;
import com.joker.webchatting.springboot.web.dto.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final FileRepository fileRepository;

    @Transactional
    public List<CommentsListResponseDto>  getCommentsList() {
        return commentsRepository.findAllDesc().stream()
                .map(CommentsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(CommentsSaveRequestDto requestDto){
        return commentsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, CommentsUpdateRequestDto requestDto){
        Comments comments = commentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id =" + id));
        comments.update(requestDto.getPostId(),requestDto.getContent());
        return id;
    }
    @Transactional
    public void delete(Long id){
        Comments comments = commentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id = " + id));
        commentsRepository.delete(comments);
    }

    @Transactional(readOnly = true)
    public CommentsResponseDto findById(Long id) {
        Comments entity = commentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new CommentsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<CommentsListResponseDto> findAllDesc() {
        return commentsRepository.findAllDesc().stream()
                .map(CommentsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<CommentsListResponseDto> findByPostId(Long postId) {
        return commentsRepository.findByPostId(postId).stream().map(CommentsListResponseDto::new).collect(Collectors.toList());
    }
}