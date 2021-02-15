package mk.ukim.finki.wpproekt.repository;

import mk.ukim.finki.wpproekt.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByItem_Id(Long id);
    List<Grade> findAllByGradeAndItem_Id(Character grade, Long id);
}
