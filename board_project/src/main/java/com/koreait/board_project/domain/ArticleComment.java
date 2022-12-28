package com.koreait.board_project.domain;

import java.time.LocalDateTime;

public class ArticleComment {
    private Long id;
    private Article article;     // 게시글 아이디
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}