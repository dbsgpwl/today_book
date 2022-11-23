package com.example.book_list.service;


import com.example.book_list.model.Post;
import com.example.book_list.model.entity.PostEntity;
import com.example.book_list.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void create(String title, String review) {
        postRepository.save(PostEntity.of(title, review));
    }

    @Transactional
    public Post modify(String title, String review, Integer postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> {
            return new IllegalArgumentException(String.format("%s가 존재하지 않습니다.", postId));
        });

        post.setTitle(title);
        post.setReview(review);

        return Post.fromEntity(postRepository.saveAndFlush(post)); //수정 시 saveAndFlush

    }
    @Transactional
    public Post getPost(Integer postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> {
            return new IllegalArgumentException(String.format("%s가 존재하지 않습니다.", postId));
        });

        return Post.fromEntity(post);
    }
    @Transactional
    public void delete(Integer postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> {
            return new IllegalArgumentException(String.format("%s가 존재하지 않습니다.", postId));
        });

        postRepository.delete(post);
    }

    @Transactional
    public Page<Post> list(Pageable pageable) {
        return postRepository.findAll(pageable).map(Post::fromEntity);
    }





}
