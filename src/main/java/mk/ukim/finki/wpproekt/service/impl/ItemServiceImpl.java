package mk.ukim.finki.wpproekt.service.impl;

import mk.ukim.finki.wpproekt.enumerations.TypeEnumeration;
import mk.ukim.finki.wpproekt.exceptions.ItemNotFoundException;
import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.repository.ItemRespository;
import mk.ukim.finki.wpproekt.service.ItemService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private  final ItemRespository itemRespository;

    public ItemServiceImpl(ItemRespository itemRespository) {
        this.itemRespository = itemRespository;
    }

    @Override
    public List<Item> findAll() {
        return this.itemRespository.findAll();
    }

    @Override
    public List<Item> findAllByType(TypeEnumeration typeEnumeration) {
        return this.itemRespository.findAllByType(typeEnumeration);
    }

    @Override
    public Optional<Item> edit(Long id, String name, String description, TypeEnumeration type, int price, String image) {
        Item product = this.itemRespository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);
        product.setType(type);
        return Optional.of(this.itemRespository.save(product));

    }

    @Override
   public Optional<Item> save(String name, String description, TypeEnumeration type, int price, String image){
        this.itemRespository.deleteByName(name);
        return Optional.of(this.itemRespository.save(new Item(name, description, type, price, image)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.itemRespository.deleteById(id);
    }

    public Optional<Item> findById(Long id) {
        return this.itemRespository.findById(id);
    }
}
