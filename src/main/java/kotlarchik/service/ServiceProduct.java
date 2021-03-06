package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceProduct implements DAO<Product, Integer> {
    SessionFactory factory;

    public ServiceProduct(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Product product) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public Product read(Integer id) {
        try(Session session = factory.openSession()){
            Product product = session.get(Product.class, id);
            return product;
        }
    }

    @Override
    public List<Product> readAll() {
        try(Session session = factory.openSession()){
            Query<Product> query = session.createQuery("from Product");
            return query.list();
        }
    }

    @Override
    public void update(Product product) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Product product) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        }
    }
}
