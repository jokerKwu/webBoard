package com.joker.webchatting.springboot.service.posts;


import com.joker.webchatting.springboot.domain.posts.FileRepository;
import com.joker.webchatting.springboot.domain.posts.Posts;
import com.joker.webchatting.springboot.domain.posts.PostsRepository;
import com.joker.webchatting.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final FileRepository fileRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;       // 한 페이지에 존재하는 게시글 수

    @Transactional
    public PostDto getPost(Long id) {
        Posts post = postsRepository.findById(id).get();

        PostDto postDto = PostDto.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .title(post.getTitle())
                .content(post.getContent())
                .fileId(post.getFileId())
                .type(post.getType())
                .pattern((post.getPattern()))
                .build();
        return postDto;
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent(),requestDto.getFileId(),requestDto.getFilename(),requestDto.getType(),requestDto.getPattern(), requestDto.getCommentsCnt());
        return id;
    }

    @Transactional
    public Long commentsCntUpdate(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        posts.update(posts.getTitle(), posts.getContent(),posts.getFileId(),posts.getFilename(),posts.getType(),posts.getPattern(), posts.getCommentsCnt() + 1);

        return id;
    }


    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional
    public void commentsCntDelete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        posts.update(posts.getTitle(), posts.getContent(),posts.getFileId(),posts.getFilename(),posts.getType(),posts.getPattern(), posts.getCommentsCnt() - 1);

    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        //Posts posts = postsRepository.findById(id).get();
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    //키워드 검색
    @Transactional
    public Page<Posts> searchPosts(HashMap<String,String> requestMap, String keyword,@PageableDefault(size = 15, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Posts> postEntities = null;
        String typeOption = requestMap.get("typeOption");
        String patternOption = requestMap.get("patternOption");
        String searchOption = requestMap.get("searchOption");

        System.out.println(typeOption);
        System.out.println(patternOption);
        System.out.println(searchOption);

        // type &&  pattern 이 all인 경우
        if(typeOption.equals("typeAll") && patternOption.equals("patternAll")){
            if(searchOption.equals("title")) {
                postEntities = postsRepository.findByTitleContainingIgnoreCaseOrderByIdDesc(keyword,pageable);
            }else if(searchOption.equals("content")){
                postEntities = postsRepository.findByContentContainingIgnoreCaseOrderByIdDesc(keyword,pageable);
            }else if(searchOption.equals("author")){
                postEntities = postsRepository.findByAuthorContainingIgnoreCaseOrderByIdDesc(keyword,pageable);
            }else if(searchOption.equals("searchAll")){
                postEntities = postsRepository.findAllByContentContainingIgnoreCaseOrTitleContainingIgnoreCaseOrderByIdDesc(keyword,keyword,pageable);
            }
        }
        //pattern all인 경우
        else if(patternOption.equals("patternAll")){
            if(searchOption.equals("title")) {
                postEntities = postsRepository.findByTitleContainingAndByTypeContainingDesc(typeOption,keyword,pageable);
            }else if(searchOption.equals("content")){
                postEntities = postsRepository.findByContentContainingAndByTypeContainingDesc(typeOption,keyword,pageable);
            }else if(searchOption.equals("author")){
                postEntities = postsRepository.findByAuthorContainingAndByTypeContainingDesc(typeOption,keyword,pageable);
            }else if(searchOption.equals("searchAll")){
                postEntities = postsRepository.findByTitleOrContentContainingAndByTypeContainingDesc(typeOption,keyword,pageable);
            }
        }
        // type all인 경우
        else if(typeOption.equals("typeAll")){
            if(searchOption.equals("title")) {
                postEntities = postsRepository.findByTitleContainingAndByPatternContainingDesc(patternOption,keyword,pageable);
            }else if(searchOption.equals("content")){
                postEntities = postsRepository.findByContentContainingAndByPatternContainingDesc(patternOption,keyword,pageable);
            }else if(searchOption.equals("author")){
                postEntities = postsRepository.findByAuthorContainingAndByPatternContainingDesc(patternOption,keyword,pageable);
            }else if(searchOption.equals("searchAll")){
                postEntities = postsRepository.findByTitleOrContentContainingAndByPatternContainingDesc(patternOption,keyword,pageable);
            }
        }
        // 둘다 아닌 경우
        else{
            if(searchOption.equals("title")) {
                postEntities = postsRepository.findByTitleDesc(typeOption,patternOption,keyword,pageable);
            }else if(searchOption.equals("content")){
                postEntities = postsRepository.findByContentDesc(typeOption,patternOption,keyword,pageable);
            }else if(searchOption.equals("author")){
                postEntities = postsRepository.findByTitleOrByContentDesc(typeOption,patternOption,keyword,pageable);
            }else if(searchOption.equals("searchAll")){
                postEntities = postsRepository.findByAuthorDesc(typeOption,patternOption,keyword,pageable);
            }
        }

        return postEntities;
    }
    private PostDto convertEntityToDto(Posts postEntity){
        return PostDto.builder()
                .author(postEntity.getAuthor())
                .title(postEntity.getTitle())
                .modifiedDate(postEntity.getModifiedDate())
                .id(postEntity.getId())
                .fileId(postEntity.getFileId())
                .type(postEntity.getType())
                .pattern(postEntity.getPattern())
                .build();
    }
    //페이지 번호 추가
    @Transactional
    public List<PostDto> getPostlist(Integer pageNum) {
        Page<Posts> page = postsRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));

        List<Posts> postEntities = page.getContent();
        List<PostDto> postDtoList = new ArrayList<>();

        for (Posts postEntity : postEntities) {
            postDtoList.add(this.convertEntityToDto(postEntity));
        }
        return postDtoList;
    }
    @Transactional
    public Long getPostCount() {
        return postsRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        System.out.println(" PostService : 총게시글 수 " + this.getPostCount());

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getPostCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        System.out.println(" PostService : 마지막 번호 " + totalLastPageNum);

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)? curPageNum + BLOCK_PAGE_NUM_COUNT: totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;
        System.out.println(" PostService : 페이지 시작 번호 조정 " + curPageNum);

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
            System.out.println(" PostService : for문 값체크 " + val+" "+idx+" "+pageList[idx]);
        }

        return pageList;
    }

    public Page<Posts> getPostList(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 15,Sort.Direction.DESC,"id");

        return postsRepository.findAll(pageable);
    }

}