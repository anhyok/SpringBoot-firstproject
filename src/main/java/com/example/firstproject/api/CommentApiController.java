package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 1. Retrieve comments
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // Request to service
        List<CommentDto> dtos = commentService.comments(articleId);
        // Response the result
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 2. Create a comment
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {
        // Request to service
        CommentDto createdDto = commentService.create(articleId, dto);
        // Response the result
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 3. Update a comment

    // 4. Delete a comment
}