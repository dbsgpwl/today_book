package com.example.book_list.controller.response;
import com.example.book_list.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class PostResponse {
    private Integer id;

    private String title;

    private String review;

    private Timestamp createAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public static PostResponse fromPost(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getReview(),
                post.getCreateAt(),
                post.getUpdatedAt(),
                post.getDeletedAt()
        );
    }
}
