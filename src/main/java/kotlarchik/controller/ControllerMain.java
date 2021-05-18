package kotlarchik.controller;

import com.sun.source.tree.IfTree;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import kotlarchik.dao.DAO;
import kotlarchik.model.Product;
import kotlarchik.service.ServiceProduct;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class ControllerMain {
    @FXML
    private TableView<Product> tableProduct;

    @FXML
    private TableColumn<Product, String> columnName;

    @FXML
    private TableColumn<Product, Double> columnCost;

    @FXML
    private TableColumn<Product, String> columnActive;

    @FXML
    private TextField search;

    @FXML
    private ComboBox<String> comboActive;

    private Product product;

    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private final ObservableList<Product> products = FXCollections.observableArrayList();
    private final ObservableList<String> combo = FXCollections.observableArrayList("Название", "Цена", "Статус");

    @FXML
    void initialize(){
        showInfo();
        selectItem();
        search();
    }

    void showInfo(){
        DAO<Product, Integer> productDAO = new ServiceProduct(factory);
        products.addAll(productDAO.readAll());

        columnName.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTitle()));
        columnCost.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getCost()));
        columnActive.setCellValueFactory(c -> {
            if (c.getValue().getIsActive() == 1) {
                return new SimpleObjectProperty<>("Активен");
            } else return new SimpleObjectProperty<>("Неактивен");
        });

        tableProduct.setItems(products);
    }

    private void search(){
        comboActive.setItems(combo);
        search.textProperty().addListener((observableValue, s, t1) -> {
            FilteredList<Product> filteredList = new FilteredList<>(products, p -> {
                if (t1 == null){
                    return true;
                }
                    if (comboActive.getValue().equals("Название")) {
                        if (p.getTitle().toLowerCase().contains(t1.toLowerCase())) {
                            return true;
                        }
                    }
                return false;
            });
            tableProduct.setItems(filteredList);
        });
    }

    private void selectItem(){
        tableProduct.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            product = newValue;
        });
    }
}
