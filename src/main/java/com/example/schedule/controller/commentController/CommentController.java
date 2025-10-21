package com.example.schedule.controller.commentController;

import com.example.schedule.dto.commentDto.CreateCommentRequest;
import com.example.schedule.dto.commentDto.CreateCommentResponse;
import com.example.schedule.service.commentService.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/{scheduleId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCommentResponse createComment(
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request) {
        return commentService.createComment(scheduleId, request);
    }
}
