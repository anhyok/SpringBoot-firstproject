package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
            return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
        //System.out.println(form.toString());

        // 1. DTO -> Entity
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());

        // 2. Repository for DB
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. Get all the data
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. Apply the data to the model
        model.addAttribute("articleList", articleEntityList);

        // 3. Set the view page
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        // 1.
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        // 2.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(target != null) {
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/"+ articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("Request DELETE!!!");
        // 1. Find the target
        Article target = articleRepository.findById(id).orElse(null);
        // 2. Delete the target entity
        if(target != null) {
            log.info(target.toString());
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }
        // 3. Redirect
        return "redirect:/articles";
    }
}
