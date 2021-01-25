package com.joker.webchatting.springboot.web;


import com.joker.webchatting.springboot.service.posts.FileService;
import com.joker.webchatting.springboot.service.posts.PostsService;
import com.joker.webchatting.springboot.util.MD5Generator;
import com.joker.webchatting.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final FileService fileService;



    @PostMapping("/api/v1/fileUpload")
    public void uploadAjaxPost(@RequestParam("uploadFile") MultipartFile[] files, @RequestParam("title")String title, @RequestParam("author")String author, @RequestParam("content")String content ) {
        //윈도우
        //String savePath = "C:\\upload";//실행되는 위치의 files 폴더에 파일이 저장된다.
        //리눅스
        String savePath = "/home/ec2-user/app/step1/upload";//실행되는 위치의 files 폴더에 파일이 저장된다.

        PostsSaveRequestDto requestDto = new PostsSaveRequestDto();
        requestDto.setTitle(title);
        requestDto.setAuthor(author);
        requestDto.setContent(content);


        for(MultipartFile multipartFile : files) {
            System.out.println("---------------------------------");
            System.out.println("Upload File Name :" + multipartFile.getOriginalFilename());
            System.out.println("Upload File Size : " + multipartFile.getSize());

            String origFilename = multipartFile.getOriginalFilename(); //origFilename
            requestDto.setFilename(origFilename);
            /*
                db에 파일아이디 저장
             */
            try {
                String filename = new MD5Generator(origFilename).toString();
                if (!new File(savePath).exists()) {
                    try {
                        new File(savePath).mkdir();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                //윈도우
                String filePath = savePath + "/" + filename;
                //리눅스
                //String filePath = savePath + "/" + filename;
                multipartFile.transferTo(new File(filePath));
                FileDto fileDto = new FileDto();
                fileDto.setOrigFilename(origFilename);
                fileDto.setFilename(filename);
                fileDto.setFilePath(filePath);

                Long fileId = fileService.saveFile(fileDto);
                requestDto.setFileId(fileId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        postsService.save(requestDto);

    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
        FileDto fileDto = fileService.getFile(fileId);
        Path path = Paths.get(fileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    /*
            기존에 등록되어 있는 파일 삭제하고
            재등록한다.
    */
    @PostMapping("api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestParam("uploadFile") MultipartFile[] files, @RequestParam("title")String title, @RequestParam("author")String author, @RequestParam("content")String content ){

        //파일을 등록한다.
        String savePath = "C:\\upload";//실행되는 위치의 files 폴더에 파일이 저장된다.
        PostsUpdateRequestDto requestDto = new PostsUpdateRequestDto();
        requestDto.setTitle(title);
        requestDto.setContent(content);


        for(MultipartFile multipartFile : files) {
            System.out.println("---------------------------------");
            System.out.println("Upload File Name :" + multipartFile.getOriginalFilename());
            System.out.println("Upload File Size : " + multipartFile.getSize());

            String origFilename = multipartFile.getOriginalFilename(); //origFilename
            requestDto.setFilename(origFilename);
            /*
                db에 파일아이디 저장
             */
            try {
                String filename = new MD5Generator(origFilename).toString();
                if (!new File(savePath).exists()) {
                    try {
                        new File(savePath).mkdir();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                String filePath = savePath + "\\" + filename;
                multipartFile.transferTo(new File(filePath));
                FileDto fileDto = new FileDto();
                fileDto.setOrigFilename(origFilename);
                fileDto.setFilename(filename);
                fileDto.setFilePath(filePath);

                Long fileId = fileService.saveFile(fileDto);
                requestDto.setFileId(fileId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return postsService.update(id, requestDto);
    }
/*
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }
*/
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