package com.example.book_list.controller;


import com.example.book_list.controller.request.PostRequest;
import com.example.book_list.controller.response.PostResponse;
import com.example.book_list.controller.response.Response;
import com.example.book_list.model.Post;
import com.example.book_list.model.entity.PostEntity;
import com.example.book_list.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public Response<Void> create(@RequestBody PostRequest request){
        postService.create(request.getTitle(), request.getReview());

        return Response.success();
    }

    @PutMapping("/{postId}")
    public Response<PostResponse> modify(@PathVariable Integer postId,
                                         @RequestBody PostRequest request){
        Post post = postService.modify(request.getTitle(), request.getReview(), postId);
        return Response.success(PostResponse.fromPost(post));
    }

    @DeleteMapping("{postId}")
    public Response<Void> delete(@PathVariable Integer postId){
        postService.delete(postId);
        return Response.success();
    }

    @GetMapping
    public Response<Page<PostResponse>> list(Pageable pageable){
        return Response.success(postService.list(pageable).map(PostResponse::fromPost));
    }

    @GetMapping("/{postId}")
    public Response<PostResponse> getPost(@PathVariable Integer postId){
        Post post = postService.getPost(postId);
        return Response.success(PostResponse.fromPost(post));
    }

}
