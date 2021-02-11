package mk.ukim.finki.wpproekt.repository;

import mk.ukim.finki.wpproekt.model.CommentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentItem, Long> {
    List<CommentItem> findAllByItem_Id(Long id);
}
