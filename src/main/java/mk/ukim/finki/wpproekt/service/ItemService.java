package mk.ukim.finki.wpproekt.service;

import mk.ukim.finki.wpproekt.enumerations.TypeEnumeration;
import mk.ukim.finki.wpproekt.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> findAll();
    List<Item> findAllByType(TypeEnumeration typeEnumeration);
    Optional<Item> edit(Long id, String name, String description, TypeEnumeration type, int price, String image);
    Optional<Item> save(String name, String description, TypeEnumeration type, int price, String image);
    void deleteById(Long id);
    Optional<Item> findById(Long id);
}
