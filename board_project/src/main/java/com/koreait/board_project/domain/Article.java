package com.koreait.board_project.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
public class Article {

    private Long id;
    private String title;
    private String content;
    private String hashtag;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

}