package com.joker.webchatting.springboot.web;


import com.joker.webchatting.springboot.service.posts.FileService;
import com.joker.webchatting.springboot.service.posts.PostsService;
import com.joker.webchatting.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final FileService fileService;

    @PostMapping("/api/v1/fileUpload")
    public void uploadAjaxPost(@RequestParam("uploadFile") MultipartFile[] files, @RequestParam("title")String title, @RequestParam("author")String author, @RequestParam("content")String content ) {
        String uploadFolder = "C:\\upload";
        PostsSaveRequestDto requestDto = new PostsSaveRequestDto();
        requestDto.setTitle(title);
        requestDto.setAuthor(author);
        requestDto.setContent(content);
        for(MultipartFile multipartFile : files) {
            System.out.println("---------------------------------");
            System.out.println("Upload File Name :"+multipartFile.getOriginalFilename());
            System.out.println("Upload File Size : " + multipartFile.getSize());

            String uploadFileName = multipartFile.getOriginalFilename();

            //IE has file path
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
            System.out.println("only file name : " + uploadFileName);

            File saveFile = new File(uploadFolder, uploadFileName);

            try {
                multipartFile.transferTo(saveFile);

            }catch (Exception e) {
                System.out.println(e.getMessage());
            }//end catch
        }//end for
        postsService.save(requestDto);

    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts/list")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAllDesc();
    }
}