package ru.job4j.store;

import org.hibernate.Cache;
import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.List;

public interface Store {

    void save(Item item);

    void addNewItem(Item item, String[] cat);

    Item create(Item item);

    void update(Item item);

    void delete(Integer id);

    List<Item> findAll();

    List<Category> findAllCategory();

    Item findById(Integer id);

    User findByEmailUser(String email);

    User create(User user);

    void update(User user);

    void save(User user);


}
