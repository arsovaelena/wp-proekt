package mk.ukim.finki.wpproekt.service.impl;

import mk.ukim.finki.wpproekt.exceptions.UserNotFoundException;
import mk.ukim.finki.wpproekt.model.CommentItem;
import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.repository.CommentRepository;
import mk.ukim.finki.wpproekt.repository.ItemRespository;
import mk.ukim.finki.wpproekt.repository.UserRepository;
import mk.ukim.finki.wpproekt.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final ItemRespository itemRespository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(UserRepository userRepository, ItemRespository itemRespository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.itemRespository = itemRespository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<CommentItem> create(String username, Long itemId, String comment) {
        User user = this.userRepository.findByUsername(username).get();
        Item item = this.itemRespository.findById(itemId).get();
        CommentItem commentItem = new CommentItem(user, item, comment);
        return Optional.of(this.commentRepository.save(commentItem));
    }

    @Override
    public Optional<CommentItem> update(Long id, String comment) {
        CommentItem commentItem = this.findById(id).get().orElseThrow(()-> new UserNotFoundException("Comment not found"));
        commentItem.setComment(comment);
        return Optional.of(this.commentRepository.save(commentItem));
    }

    @Override
    public Optional<CommentItem> delete(Long commentId) {
        CommentItem commentItem = this.findById(commentId).get().orElseThrow(()-> new UserNotFoundException("Comment not found"));
        this.commentRepository.delete(commentItem);
        return Optional.of(commentItem);
    }

    @Override
    public Optional<Optional<CommentItem>> findById(Long id) {
        return Optional.of(this.commentRepository.findById(id));
    }

    @Override
    public List<CommentItem> findAllByItemId(Long id) {
        return this.commentRepository.findAllByItem_Id(id);
    }
}
