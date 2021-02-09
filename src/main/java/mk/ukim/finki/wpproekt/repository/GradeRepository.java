package mk.ukim.finki.wpproekt.repository;

import mk.ukim.finki.wpproekt.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
