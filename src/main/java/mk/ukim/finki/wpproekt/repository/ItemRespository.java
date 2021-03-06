package mk.ukim.finki.wpproekt.repository;

import mk.ukim.finki.wpproekt.enumerations.TypeEnumeration;
import mk.ukim.finki.wpproekt.model.Ingredient;
import mk.ukim.finki.wpproekt.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRespository extends JpaRepository<Item,Long> {
    Optional<Item> findByName(String name);
    void deleteByName(String name);
    List<Item> findAllByType(TypeEnumeration type);
    List<Item> findAllByIdIn(java.util.List<Long> ids);

}
