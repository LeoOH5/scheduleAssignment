package com.example.schedule.service.commentService;

import com.example.schedule.dto.commentDto.CreateCommentRequest;
import com.example.schedule.dto.commentDto.CreateCommentResponse;
import com.example.schedule.entity.Comment;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.commentRepository.CommentRepository;
import com.example.schedule.repository.scheduleRepository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    private static final int MAX_COMMENTS_PER_SCHEDULE = 10;

    // 댓글 생성
    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request) {
        // 스케줄 존재 여부 확인
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스케줄입니다. ID: " + scheduleId));

        // 댓글 10개 제한 확인
        int currentCommentCount = schedule.getComments().size();
        if (currentCommentCount >= MAX_COMMENTS_PER_SCHEDULE) {
            throw new IllegalStateException("댓글은 최대 " + MAX_COMMENTS_PER_SCHEDULE + "개까지만 작성할 수 있습니다.");
        }

        // 댓글 생성
        Comment comment = Comment.of(
                schedule,
                request.getContent(),
                request.getWriter(),
                request.getPassword()
        );

        Comment result = commentRepository.save(comment);

        // 응답 생성
        return new CreateCommentResponse(
                result.getId(),
                result.getSchedule().getId(),
                result.getContent(),
                result.getWriter(),
                result.getCreatedAt(),
                result.getModifiedAt()
        );
    }
}
