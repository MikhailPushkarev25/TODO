package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Hiber implements Store {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final Store INST = new Hiber();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch(final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Item item) {
        if (item.getId() == 0) {
            create(item);
        } else {
            item = findById(item.getId());
            item.setDone(true);
            update(item);
        }
    }

    @Override
    public void addNewItem(Item item, String[] cat) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            for (String id : cat) {
                Category category = session.find(Category.class, Integer.parseInt(id));
                item.addCategories(category);
            }
            session.save(item);

            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
    }

    @Override
    public Item create(Item item) {
        return this.tx(session -> {
             session.save(item);
             return item;
        });
    }

    @Override
    public void update(Item item) {
       this.tx(session -> {
           session.update(item);
           return item;
       });
    }

    @Override
    public void delete(Integer id) {
        this.tx(session -> {
            Item item = new Item(null);
            item.setId(id);
            session.delete(item);
            return item;
        });
    }


    @Override
    public List<Item> findAll() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.model.Item").list()
        );
    }

    @Override
    public List<Category> findAllCategory() {
        List<Category> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();

            rsl = session.createQuery("select c from Category c", Category.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            sf.getCurrentSession().getTransaction().rollback();
        }
        return rsl;
    }

    @Override
    public Item findById(Integer id) {
        return this.tx(session -> {
            return session.get(Item.class, id);
        });
    }

    @Override
    public User findByEmailUser(String email) {
        User rsl = null;
        rsl = (User) tx(session -> session
                .createQuery("from ru.job4j.model.User as users where users.email=:email")
                .setParameter("email", email).uniqueResult());
        return rsl;
    }

    @Override
    public User create(User user) {
        return this.tx(session -> {
            session.save(user);
            return user;
        });
    }

    @Override
    public void update(User user) {
        this.tx(session -> {
            session.update(user);
            return user;
        });
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            create(user);
        } else {
            update(user);
        }
    }

    public static void main(String[] args) {
        Hiber hiber = new Hiber();
        User user = new User("2@email", "111");
        System.out.println(hiber.create(user));
        System.out.println(hiber.findByEmailUser("123@emaill"));
    }

}
