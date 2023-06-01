package controller;

import util.HibernateUtil;
import model.EntityCategory;
import model.EntityLine;
import model.EntityModel;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;


public class MainController {

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane tpLine;

    @FXML
    private ComboBox<EntityLine> cbbLine;

    @FXML
    private TitledPane tpModel;

    @FXML
    private TreeView<?> tvModel;

    public Session session = HibernateUtil.getSessionFactory().openSession();

    @FXML
    public void initialize() {
        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);

        List<EntityLine> listLine = session.createQuery("FROM EntityLine").list();
        cbbLine.setItems(FXCollections.observableArrayList(listLine));
        cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> openTv()));
    }

    public void openTv() {
        tpModel.setDisable(false);
        tpModel.setExpanded(true);

        fillTv();
    }

    public void fillTv() {
        TreeItem root = new TreeItem();

        EntityLine cbbLineSelected = cbbLine.getValue();

        List<EntityCategory> listCategory = session.createQuery(String.format("FROM EntityCategory WHERE id_line = '%s'", cbbLineSelected.getId())).list();
        listCategory.forEach(entityCategory -> {
            TreeItem newItemCategory = new TreeItem<>(entityCategory);
            root.getChildren().add(newItemCategory);

            List<EntityModel> listModel = session.createQuery(String.format("FROM EntityModel WHERE id_category = '%s'", entityCategory.getId())).list();
            listModel.forEach(entityModel -> {
                TreeItem itemModel = new TreeItem<>(entityModel);
                newItemCategory.getChildren().add(itemModel);
            });

        });
        root.setValue(cbbLineSelected);
        root.setExpanded(true);
        tvModel.setRoot(root);

    }
}