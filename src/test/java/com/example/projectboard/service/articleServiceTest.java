package com.example.projectboard.service;

import com.example.projectboard.domain.Article;
import com.example.projectboard.domain.type.SearchType;
import com.example.projectboard.dto.ArticleDto;
import com.example.projectboard.dto.ArticleUpdateDto;
import com.example.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class articleServiceTest {
    @InjectMocks
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void given_when_then() {
        /// Given

        // When
        List<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");

        // Then
        assertThat(articles).isNotNull();
    }
    @DisplayName("게시글을 검색하면, 게시글을 반환한다.")
    @Test
    void given_when_then1() {
        /// Given

        // When
        ArticleDto articles = sut.searchArticles(1L);

        // Then
        assertThat(articles).isNotNull();
    }
    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void given_when_then3() {
        // Given
        ArticleDto dto = ArticleDto.of(LocalDateTime.now(), "shin", "title", "content", "#java");
        given(articleRepository.save(any(Article.class))).willReturn(null);
        // When
        sut.saveArticle(dto);
        // Then
        BDDMockito.then(articleRepository).should().save(any(Article.class));
    }
    @DisplayName("게시글 ID와 수정 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void given_when_then4() {
        // Given
        given(articleRepository.save(any(Article.class))).willReturn(null);
        // When
        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#java"));
        // Then
        BDDMockito.then(articleRepository).should().save(any(Article.class));
    }
    @DisplayName("게시글 ID와 수정 정보를 입력하면, 게시글을 삭제한다.")
    @Test
    void given_when_then5() {
        // Given
        willDoNothing().given(articleRepository).delete(any(Article.class));
        // When
        sut.deleteArticle(1L);
        // Then
        BDDMockito.then(articleRepository).should().delete(any(Article.class));
    }
}