package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.ProductSale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceProductSale implements DAO<ProductSale, Integer> {
    SessionFactory factory;

    public ServiceProductSale(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(ProductSale productSale) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(productSale);
            session.getTransaction().commit();
        }
    }

    @Override
    public ProductSale read(Integer id) {
        try(Session session = factory.openSession()){
            ProductSale productSale = session.get(ProductSale.class, id);
            return productSale;
        }
    }

    @Override
    public List<ProductSale> readAll() {
        try(Session session = factory.openSession()){
            Query<ProductSale> query = session.createQuery("from ProductSale");
            return query.list();
        }
    }

    @Override
    public void update(ProductSale productSale) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(productSale);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ProductSale productSale) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(productSale);
            session.getTransaction().commit();
        }
    }
}
