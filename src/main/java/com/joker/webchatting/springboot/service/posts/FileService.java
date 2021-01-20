package com.joker.webchatting.springboot.service.posts;

import javax.transaction.Transactional;

import com.joker.webchatting.springboot.domain.posts.File;
import com.joker.webchatting.springboot.domain.posts.FileRepository;
import com.joker.webchatting.springboot.web.dto.FileDto;
import org.springframework.stereotype.Service;
@Service
public class FileService {
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public Long saveFile(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public FileDto getFile(Long id) {
        File file = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
        return fileDto;
    }
}