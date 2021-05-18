package kotlarchik.service;

import com.sun.xml.bind.v2.runtime.NameBuilder;
import kotlarchik.dao.DAO;
import kotlarchik.model.Manufacturer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceManufacturer implements DAO<Manufacturer, Integer> {
    SessionFactory factory;

    public ServiceManufacturer(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Manufacturer manufacturer) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public Manufacturer read(Integer id) {
        try(Session session = factory.openSession()){
            Manufacturer manufacturer = session.get(Manufacturer.class, id);
            return manufacturer;
        }
    }

    @Override
    public List<Manufacturer> readAll() {
        try(Session session = factory.openSession()){
            Query<Manufacturer> query = session.createQuery("from Manufacturer");
            return query.list();
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.update(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(manufacturer);
            session.getTransaction().commit();
        }
    }
}
