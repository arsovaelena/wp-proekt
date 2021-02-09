package mk.ukim.finki.wpproekt.repository;

import mk.ukim.finki.wpproekt.model.CommentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentItem, Long> {
}
