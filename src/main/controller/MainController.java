package main.controller;

import main.model.CategoryEnum;
import main.model.LineEnum;
import main.model.ModelEnum;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class MainController implements Initializable {

	@FXML
	private Accordion accordion;

	@FXML
	private TitledPane tpLine;

	@FXML
	private ComboBox<LineEnum> cbbLine;

	@FXML
	private TitledPane tpModel;

	@FXML
	private TreeView<?> tvModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accordion.setExpandedPane(tpLine);
		tpModel.setDisable(true);

		cbbLine.setItems(FXCollections.observableArrayList(LineEnum.values()));
		cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> openTv()));
	}

	public void openTv() {
		tpModel.setDisable(false);
		tpModel.setExpanded(true);

		fillTv();
	}

	public void fillTv() {
		TreeItem root = new TreeItem();

		LineEnum cbbLineSelected = cbbLine.getValue();
		for (CategoryEnum categoryEnum : CategoryEnum.values()) {
			if (categoryEnum.getLineEnum().equals(cbbLineSelected)) {
				TreeItem newItem = new TreeItem<>(categoryEnum);
				root.getChildren().add(newItem);
				for (ModelEnum modelEnum : ModelEnum.values()) {
					if (modelEnum.getCategoryEnum().getLineEnum().equals(cbbLineSelected)
							&& modelEnum.getCategoryEnum().equals(categoryEnum)) {
						TreeItem model = new TreeItem(modelEnum);
						newItem.getChildren().add(model);
					}
				}
			}
		}

		root.setValue(cbbLineSelected);
		root.setExpanded(true);
		tvModel.setRoot(root);

	}

}