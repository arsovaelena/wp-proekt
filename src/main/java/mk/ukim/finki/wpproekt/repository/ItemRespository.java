package mk.ukim.finki.wpproekt.repository;

import mk.ukim.finki.wpproekt.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRespository extends JpaRepository<Item,Long> {
    Optional<Item> findByName(String name);
    void deleteByName(String name);
}
