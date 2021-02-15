package mk.ukim.finki.wpproekt.service.impl;

import mk.ukim.finki.wpproekt.exceptions.UserNotFoundException;
import mk.ukim.finki.wpproekt.model.Grade;
import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.repository.GradeRepository;
import mk.ukim.finki.wpproekt.repository.ItemRespository;
import mk.ukim.finki.wpproekt.repository.UserRepository;
import mk.ukim.finki.wpproekt.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final UserRepository userRepository;
    private final ItemRespository itemRespository;

    public GradeServiceImpl(GradeRepository gradeRepository, UserRepository userRepository, ItemRespository itemRespository) {
        this.gradeRepository = gradeRepository;
        this.userRepository = userRepository;
        this.itemRespository = itemRespository;
    }

    @Override
    public Optional<Grade> create(String username, Long itemId, String gradeChar) {
        User user = this.userRepository.findByUsername(username).get();
        Item item = this.itemRespository.findById(itemId).get();
        Grade grade = new Grade(user,item,gradeChar.charAt(0));
        return Optional.of(this.gradeRepository.save(grade));
    }

    @Override
    public Optional<Grade> update(Long id, Character gradeChar) {
        Grade grade = this.findById(id).get().orElseThrow(()-> new UserNotFoundException("Grade not found"));
        grade.setGrade(gradeChar);
        return Optional.of(this.gradeRepository.save(grade));
    }

    @Override
    public Optional<Grade> delete(Long id) {
        Grade grade = this.findById(id).get().orElseThrow(()-> new UserNotFoundException("Grade not found"));
        this.gradeRepository.delete(grade);
        return Optional.of(grade);
    }

    @Override
    public Optional<Optional<Grade>> findById(Long id) {
        return Optional.of(this.gradeRepository.findById(id));
    }

    @Override
    public List<Grade> findAllByItemId(Long id) {
        return this.gradeRepository.findAllByItem_Id(id);
    }

    @Override
    public List<Grade> findAllByGradeAndItem_Id(Character grade, Long id) {
        return this.gradeRepository.findAllByGradeAndItem_Id(grade, id);
    }
}
