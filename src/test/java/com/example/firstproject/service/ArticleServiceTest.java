package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 1. Guessed data
        Article a= new Article(1L, "aaaaa", "11111");

        Article b= new Article(2L, "bbbbb", "22222");

        Article c= new Article(3L, "ccccc", "33333");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 2. Real data
        List<Article> articles = articleService.index();

        // 3. Compare & verify
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success_w_real_id() {
        // 1. Guessed data
        Long id = 1L;

        Article expected = new Article(1L, "aaaaa", "11111");

        // 2. Real data
        Article article = articleService.show(id);

        // 3. Compare & verify
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_failure_w_fake_id() {
        // 1. Guessed data
        Long id = -1L;

        Article expected = null;

        // 2. Real data
        Article article = articleService.show(id);

        // 3. Compare & verify
        assertEquals(expected, article);
    }

    @Transactional
    @Test
    void create_success_w_title_content() {
        // 1. Guessed data
        String title = "dddd";

        String content = "44444";

        ArticleForm dto = new ArticleForm(null, title, content);

        Article expected = new Article(4L, title, content);

        // 2. Real data
        Article article = articleService.create(dto);

        // 3. Compare & verify
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_failure_w_id() {
        // 1. Guessed data
        Long id  = 4L;

        String title = "dddd";

        String content = "44444";

        ArticleForm dto = new ArticleForm(id, title, content);

        Article expected = null;

        // 2. Real data
        Article article = articleService.create(dto);

        // 3. Compare & verify
        assertEquals(expected, article);
    }


    // 1. Guessed data

    // 2. Real data

    // 3. Compare & verify
}