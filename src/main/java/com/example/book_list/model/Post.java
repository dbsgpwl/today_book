package com.example.book_list.model;

import com.example.book_list.model.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Post {

    private Integer id;

    private String title;

    private String review;

    private Timestamp createAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public static Post fromEntity(PostEntity entity) {
        return new Post(
                entity.getId(),
                entity.getTitle(),
                entity.getReview(),
                entity.getCreateAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt()
        );
    }
}
