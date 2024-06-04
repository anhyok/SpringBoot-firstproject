package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }


    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // DTO -> Entity
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // Find the target
        Article target = articleRepository.findById(id).orElse(null);
        // Wrong Request
        if(target == null || id != article.getId()) {
            log.info("Wrong request! id: {}, article: {}", id, article.toString());
            return null;
        }
        // Update & response 200
        target.patch(article);
        Article updated = articleRepository.save(target); // ***
        return updated;
    }

    public Article delete(Long id) {
        // Find the target
        Article target = articleRepository.findById(id).orElse(null);
        // Wrong Request
        if (target == null) {
            return null;
        }
        // Update & response 200
        articleRepository.delete(target);
        return target;
    }
}
