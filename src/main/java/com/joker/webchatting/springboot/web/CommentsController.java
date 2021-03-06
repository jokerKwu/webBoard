package com.joker.webchatting.springboot.web;

import com.joker.webchatting.springboot.config.auth.LoginUser;
import com.joker.webchatting.springboot.config.auth.dto.SessionUser;
import com.joker.webchatting.springboot.domain.posts.Comments;
import com.joker.webchatting.springboot.service.posts.CommentsService;
import com.joker.webchatting.springboot.service.posts.PostsService;
import com.joker.webchatting.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentsController {

    private final CommentsService commentService;
    private final PostsService postsService;

    //댓글 목록 가져오기
    @GetMapping("/api/v1/comments/list")
    public List<CommentsListResponseDto> findAll(@RequestParam("postId")Long postId){
//        return commentService.findAllDesc();
        return commentService.findByPostId(postId);
    }

    //댓글 등록하기
    @PostMapping("/api/v1/comments")
    public Long save(@RequestParam("postId")Long postId, @RequestParam("author")String author, @RequestParam("commentsContent")String content, @LoginUser SessionUser user){
        CommentsSaveRequestDto requestDto = new CommentsSaveRequestDto();
        requestDto.setAuthor(user.getName());
        requestDto.setContent(content);
        requestDto.setPostId(postId);

        postsService.commentsCntUpdate(postId);

        return commentService.save(requestDto);
    }

    //댓글 수정하기
    @PostMapping("/api/v1/comments/{id}")
    public Long update(@PathVariable Long id, @LoginUser SessionUser user, @RequestParam("commentsId")Long commentsId,@RequestParam("update_content")String update_content ,@RequestParam("author")String author){
        CommentsUpdateRequestDto requestDto = new CommentsUpdateRequestDto();
        requestDto.setContent(update_content);
        requestDto.setPostId(commentsId);
        requestDto.setAuthor(user.getName());
        return commentService.update(id, requestDto);
    }

    //댓글 삭제하기
    @DeleteMapping("/api/v1/comments/{id}")
    public Long delete(@PathVariable Long id){
        CommentsResponseDto comments = commentService.findById(id);

        postsService.commentsCntDelete(comments.getPostId());
        commentService.delete(id);
        return id;
    }
}
