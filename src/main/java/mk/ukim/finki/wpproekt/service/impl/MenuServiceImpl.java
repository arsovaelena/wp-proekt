package mk.ukim.finki.wpproekt.service.impl;

import mk.ukim.finki.wpproekt.model.Item;
import mk.ukim.finki.wpproekt.model.Menu;
import mk.ukim.finki.wpproekt.model.User;
import mk.ukim.finki.wpproekt.repository.ItemRespository;
import mk.ukim.finki.wpproekt.repository.MenuRepository;
import mk.ukim.finki.wpproekt.repository.UserRepository;
import mk.ukim.finki.wpproekt.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private final UserRepository userRepository;
    private final ItemRespository itemRespository;
    private final MenuRepository menuRepository;

    public MenuServiceImpl(UserRepository userRepository, ItemRespository itemRespository, MenuRepository menuRepository) {
        this.userRepository = userRepository;
        this.itemRespository = itemRespository;
        this.menuRepository = menuRepository;
    }

    @Override
    public Optional<Menu> create(String username, Long itemId) {
        User user = this.userRepository.findByUsername(username).get();
        Item item = this.itemRespository.findById(itemId).get();
        Menu menu = new Menu(user,item);
        return Optional.of(this.menuRepository.save(menu));
    }

    @Override
    public Optional<Menu> delete(Long id) {
        Menu menu = this.findById(id).get();
        this.menuRepository.delete(menu);
        return Optional.of(menu);
    }

    @Override
    public Optional<Menu> findById(Long id) {
        return Optional.of(this.menuRepository.findById(id).get());
    }

    @Override
    public List<Menu> findAllByUsername(String username) {
        return this.menuRepository.findAllByUser_Username(username);
    }
}
