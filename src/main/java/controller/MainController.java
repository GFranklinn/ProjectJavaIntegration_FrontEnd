package controller;

import helper.DataBaseHelper;
import model.EntityCategory;
import model.EntityLine;
import model.EntityModel;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    @FXML
    public void initialize() {
        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);

        cbbSetItems();
    }

    @FXML
    public void cbbSetItems() {

        List<EntityLine> listLine = DataBaseHelper.getListLine();
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

        List<EntityCategory> listCategory = DataBaseHelper.getListCategory(cbbLineSelected);

        listCategory.forEach(entityCategory -> {
            TreeItem newItemCategory = new TreeItem<>(entityCategory);
            root.getChildren().add(newItemCategory);

            List<EntityModel> listModel = DataBaseHelper.getListModel(entityCategory);
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