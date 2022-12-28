package com.koreait.board_project.domain;

import java.util.List;
import com.koreait.board_project.config.JpaConfig;
import com.koreait.board_project.repository.ArticleCommentRepository;
import com.koreait.board_project.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private  final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ){
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void select(){
        List<Article> articles = articleRepository.findAll();
        assertThat(articles).isNotNull().hasSize(1000);
    }

    @DisplayName("insert 테스트")
    @Test
    void insert(){
        long prevCount = articleRepository.count();
        Article saveArticle = articleRepository.save(Article.of("새로운 글", "새로운 글을 등록합니다.","#new"));
        assertThat(articleRepository.count()).isEqualTo(prevCount+1);
    }

    @DisplayName("update 테스트")
    @Test
    void update(){
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashTag = "#Spring";
        article.setHashtag(updateHashTag);

        Article savedArticle = articleRepository.saveAndFlush(article);
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHashTag);
    }

    @DisplayName("delete 테스트")
    @Test
    void delete(){
        Article article = articleRepository.findById(1L).orElseThrow();
        long preArticleCount = articleRepository.count();
        long preArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();

        articleRepository.delete(article);

        assertThat(articleRepository.count()).isEqualTo(preArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(preArticleCommentCount - deletedCommentsSize);
    }
}