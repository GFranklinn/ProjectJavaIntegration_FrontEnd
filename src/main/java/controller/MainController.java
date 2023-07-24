package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.EntityCategoryDto;
import model.EntityLineDto;
import model.EntityModelDto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static services.JsonMapDto.*;

public class MainController implements Initializable {

    @FXML
    protected Accordion accordion;

    @FXML
    protected TitledPane tpLine;

    @FXML
    protected ComboBox<EntityLineDto> cbbLine;

    @FXML
    protected TitledPane tpModel;

    @FXML
    protected TreeView tvModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);

        fillCbbLineSelected();
    }

    public void fillCbbLineSelected() {
        List<EntityLineDto> lineList = getListDb(EntityLineDto[].class, "linha");

        cbbLine.setItems(FXCollections.observableArrayList(lineList));
        cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> tvOpen()));
    }

    void tvOpen() {
        tpModel.setDisable(false);
        tpModel.setExpanded(true);

        tvFill();
    }

    public void tvFill() {
        EntityLineDto lineSelected = cbbLine.getValue();
        if (lineSelected != null) {
            String lineName = lineSelected.getName();
            TreeItem<String> root = new TreeItem<>(lineName);
            tiFill(root);
            root.setExpanded(true);
            tvModel.setRoot(root);
        }
    }


    public void tiFill(TreeItem root) {
        EntityLineDto cbbLineSelected = cbbLine.getValue();

        if (cbbLineSelected != null) {
            List<EntityCategoryDto> categoryList = getListDb(EntityCategoryDto[].class, "categorias", String.valueOf(cbbLineSelected.getId()));

            if (categoryList != null) {
                categoryList.forEach(categoryListItem -> {
                    TreeItem<String> categoryItem = new TreeItem<>(categoryListItem.getName());
                    root.getChildren().add(categoryItem);

                    List<EntityModelDto> modelList = getListDb(EntityModelDto[].class, "modelos", String.valueOf(categoryListItem.getId()));

                    if (modelList != null) {
                        modelList.forEach(model -> {
                            TreeItem<String> modelItem = new TreeItem<>(model.getName());
                            categoryItem.getChildren().add(modelItem);
                        });
                    }
                });
            }
        }
    }
}