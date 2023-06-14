package controller;

import dao.*;
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
    private TreeView tvModel;

    public Session session = HibernateUtil.getSessionFactory().openSession();

    private EntityLineDao lineDao;
    private EntityCategoryDao categoryDao;
    private EntityModelDao modelDao;

    public MainController(){
        lineDao = new EntityLineDaoImpl();
        categoryDao = new EntityCategoryDaoImpl();
        modelDao = new EntityModelDaoImpl();
    }

    @FXML
    public void initialize() {
        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);
    
        List<EntityLine> listLine = lineDao.getListLine();
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

        List<EntityCategory> listCategory = categoryDao.getListCategory(cbbLineSelected);
        listCategory.forEach(entityCategory -> {
            TreeItem newItemCategory = new TreeItem(entityCategory);
            root.getChildren().add(newItemCategory);

            List<EntityModel> listModel = modelDao.getListModel(entityCategory);
            listModel.forEach(entityModel -> {
                TreeItem itemModel = new TreeItem(entityModel);
                newItemCategory.getChildren().add(itemModel);
            });

        });
        root.setValue(cbbLineSelected);
        root.setExpanded(true);
        tvModel.setRoot(root);

    }
}
