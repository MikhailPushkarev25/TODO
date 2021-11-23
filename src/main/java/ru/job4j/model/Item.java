package ru.job4j.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private Boolean done;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Category> categories = new ArrayList<>();

    public static Item of(String description) {
        Item item = new Item();
        item.description = description;
        return item;
    }

    public Item() {
    }

    public Item(String description) {
        this.description = description;
    }

    public Item(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Item(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Item(String description, User user, List<Category> categories) {
        this.description = description;
        this.user = user;
        this.categories = categories;
    }

    public Item(Timestamp created, Boolean done, User user) {
        this.created = created;
        this.done = done;
        this.user = user;
    }

    public Item(String description, Timestamp created, Boolean done, User user) {
        this.description = description;
        this.created = created;
        this.done = done;
        this.user = user;
    }

    public Item(Integer id, String description, Timestamp created, Boolean done, User user) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
        this.user = user;
    }

    public Item(Integer id, String description, Timestamp created, Boolean done, User user, List<Category> categories) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
        this.user = user;
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategories(Category category) {
        this.categories.add(category);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
