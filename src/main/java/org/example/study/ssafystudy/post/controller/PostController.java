package org.example.study.ssafystudy.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.study.ssafystudy.post.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;



}
