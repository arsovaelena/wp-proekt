package mk.ukim.finki.wpproekt.repository;

import mk.ukim.finki.wpproekt.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}