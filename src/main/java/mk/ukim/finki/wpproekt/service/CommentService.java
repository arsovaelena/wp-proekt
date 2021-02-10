package mk.ukim.finki.wpproekt.service;

import mk.ukim.finki.wpproekt.model.CommentItem;

import java.util.Optional;

public interface CommentService {
    Optional<CommentItem> create(String  username, Long itemId, String comment);

    Optional<CommentItem> update(Long id, String comment);

   Optional<CommentItem> delete(Long commentId);

    Optional<Optional<CommentItem>> findById(Long id);
}
