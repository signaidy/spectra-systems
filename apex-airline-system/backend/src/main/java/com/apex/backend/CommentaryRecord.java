package com.apex.backend;

import java.util.List;

public record CommentaryRecord(int commentId, int parentCommentId, int userId, String content, String creationDate,
        String path, int flightId, String userName, List<CommentaryRecord> children) {

}
