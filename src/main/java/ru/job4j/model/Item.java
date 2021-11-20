package ru.job4j.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Timestamp created = new Timestamp(System.currentTimeMillis());
    private Boolean done;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "users_id")
    private User user;

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
        return Objects.equals(id, item.id) &&
                Objects.equals(description, item.description) &&
                Objects.equals(created, item.created) &&
                Objects.equals(done, item.done) &&
                Objects.equals(user, item.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, done, user);
    }
}
