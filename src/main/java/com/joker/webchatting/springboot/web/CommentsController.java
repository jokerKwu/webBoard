package com.joker.webchatting.springboot.web;

import com.joker.webchatting.springboot.service.posts.CommentsService;
import com.joker.webchatting.springboot.web.dto.CommentsListResponseDto;
import com.joker.webchatting.springboot.web.dto.CommentsRequestDto;
import com.joker.webchatting.springboot.web.dto.CommentsSaveRequestDto;
import com.joker.webchatting.springboot.web.dto.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentsController {

    private final CommentsService commentService;

    //댓글 목록 가져오기
    @GetMapping("/api/v1/comments/list")
    public List<CommentsListResponseDto> findAll(){
        return commentService.findAllDesc();
    }

    //댓글 등록하기
    @PostMapping("/api/v1/comments")
    public Long save(@RequestParam("postId")Long postId, @RequestParam("author")String author, @RequestParam("commentsContent")String content){
        CommentsSaveRequestDto requestDto = new CommentsSaveRequestDto();
        requestDto.setAuthor(author);
        requestDto.setContent(content);
        requestDto.setPostId(postId);
        return commentService.save(requestDto);
    }

    //댓글 수정하기
    @PostMapping("/api/v1/comments/{id}")
    public Long update(@PathVariable Long id, @RequestParam("commentsId")Long commentsId,@RequestParam("update_content")String update_content ,@RequestParam("author")String author){
        CommentsUpdateRequestDto requestDto = new CommentsUpdateRequestDto();
        requestDto.setContent(update_content);
        requestDto.setPostId(commentsId);
        requestDto.setAuthor(author);
        return commentService.update(id, requestDto);
    }

    //댓글 삭제하기
    @DeleteMapping("/api/v1/comments/{id}")
    public Long delete(@PathVariable Long id){
        commentService.delete(id);
        return id;
    }
}
