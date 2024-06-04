package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    @Autowired
//    ArticleRepository articleRepository;
//
//    // GET
//    @GetMapping("/api/articles")
//    public List<Article> index() {
//        return articleService.index();
//        return articleRepository.findAll();
//    }
//
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    // POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//
//    // PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
//        // DTO -> Entity
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//        // Find the target
//        Article target = articleRepository.findById(id).orElse(null);
//        // Wrong Request
//        if(target == null || id != article.getId()) {
//            log.info("Wrong request! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // Update & response 200
//        target.patch(article);
//        Article updated = articleRepository.save(target); // ***
//        return ResponseEntity.status(HttpStatus.OK).body(updated); // ***
//    }
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // Find the target
//        Article target = articleRepository.findById(id).orElse(null);
//        // Wrong Request
//        if(target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // Update & response 200
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//
//    }

}
