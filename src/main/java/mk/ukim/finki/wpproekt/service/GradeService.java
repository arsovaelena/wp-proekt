package mk.ukim.finki.wpproekt.service;

import mk.ukim.finki.wpproekt.model.Grade;

import java.util.Optional;

public interface GradeService {

    Optional<Grade> create(String  username, Long itemId, Character grade);
    Optional<Grade> update(Long id, Character grade);
    Optional<Grade> delete(Long id);
    Optional<Optional<Grade>> findById(Long id);

}
