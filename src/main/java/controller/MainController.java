package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.EntityCategoryDto;
import model.EntityLineDto;
import model.EntityModelDto;


import java.util.List;


import static services.JsonMapDto.*;

public class MainController{

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane tpLine;

    @FXML
    private ComboBox<EntityLineDto> cbbLine;

    @FXML
    private TitledPane tpModel;

    @FXML
    private TreeView tvModel;

    @FXML
    void initialize(){

        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);

        List<EntityLineDto> lineList = getListDb(EntityLineDto[].class, "linha");

        cbbLine.setItems(FXCollections.observableArrayList(lineList));
        cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> tvOpen()));

    }

    void tvOpen() {
        tpModel.setDisable(false);
        tpModel.setExpanded(true);

        tvFill();
    }

    void tvFill() {
        EntityLineDto selectedLine = cbbLine.getValue();
        String cbbLineSelected = String.valueOf(selectedLine.getId());
        TreeItem<String> root = new TreeItem<>(selectedLine.getName());

        List<EntityCategoryDto> listCategory = getListDb(EntityCategoryDto[].class, "categorias", String.valueOf(cbbLineSelected));
        listCategory.forEach(listItemCategory -> {
            TreeItem<String> itemCategory = new TreeItem<>(listItemCategory.getName());
            root.getChildren().add(itemCategory);

            List<EntityModelDto> listModel = getListDb(EntityModelDto[].class, "modelos", String.valueOf(listItemCategory.getId()));

            listModel.forEach(model -> {
                TreeItem<String> itemModel = new TreeItem<>(model.getName());
                itemModel.setValue(String.valueOf(model));  // Definindo o objeto EntityModelDto como valor do TreeItem
                itemCategory.getChildren().add(itemModel);
            });
        });

        root.setExpanded(true);
        tvModel.setRoot(root);
    }
}
