package mk.ukim.finki.wpproekt.service;

import mk.ukim.finki.wpproekt.model.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {

    Optional<Grade> create(String  username, Long itemId, String grade);
    Optional<Grade> update(Long id, Character grade);
    Optional<Grade> delete(Long id);
    Optional<Optional<Grade>> findById(Long id);
    List<Grade> findAllByItemId(Long id);
    List<Grade> findAllByGradeAndItem_Id(Character grade, Long id);
}
