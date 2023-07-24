package controller;

import javafx.scene.control.*;
import model.EntityCategoryDto;
import model.EntityLineDto;
import model.EntityModelDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runners.Parameterized;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.testfx.framework.junit.ApplicationTest;
import services.JsonMapDto;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static services.JsonMapDto.getListDb;

public class MainControllerTest extends ApplicationTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    private MainController mainController;

    @Before
    public void setUp() {
        mainController = spy(MainController.class);
        mainController.cbbLine = new ComboBox();
        mainController.tvModel = new TreeView();
        mainController.tpLine = new TitledPane();
        mainController.tpModel = new TitledPane();
        mainController.accordion = new Accordion();
    }

    @After
    public void finished() {
        mainController = null;
    }

    @Test
    public void initializeTest01() {
        doNothing().when(mainController).fillCbbLineSelected();
        mainController.accordion.setExpandedPane(null);
        // esse cara era pra realmente esta null em localtion e resources ? pois não está carregando o estado ainda do
        //setUp
        mainController.initialize(null, null);

        assertEquals("Check if initialize sets TitledPaneLines to expanded",
                mainController.tpLine,
                mainController.accordion.getExpandedPane());
    }

    @Test
    public void initializeTest02() {
        doNothing().when(mainController).fillCbbLineSelected();
        mainController.tpModel.setDisable(false);

        mainController.initialize(null, null);

        verify(mainController, times(1)).fillCbbLineSelected();
        assertTrue("Check if initialize sets titledPaneModels to disabled",
                mainController.tpModel.isDisabled());
    }

    @Test
    public void fillLineSelectorComboBoxTest01() {
        MockedStatic<JsonMapDto> mockConnection = mockStatic(JsonMapDto.class);

        EntityLineDto ares = new EntityLineDto(1, "Ares");
        EntityLineDto cronos = new EntityLineDto(2, "Cronos");

        EntityLineDto[] dtos = new EntityLineDto[]{ares, cronos};
        List<EntityLineDto> expected = Arrays.asList(dtos);

        mockConnection.when(() -> JsonMapDto.getListDb(EntityLineDto[].class, "linha")).thenReturn(expected);
        mainController.cbbLine.getItems().clear();
        mainController.fillCbbLineSelected();
        List<EntityLineDto> actual = mainController.cbbLine.getItems();

        error.checkThat("Check if lineSelector is being filled when LineDto List is called",
                actual.isEmpty(), is(false));
        assertArrayEquals(expected.toArray(), actual.toArray());

        mockConnection.close();
    }

    @Test
    public void fillLineSelectorComboBoxTest02() {
        MockedStatic<JsonMapDto> mockConnection = mockStatic(JsonMapDto.class);

        EntityLineDto ares = new EntityLineDto(1, "Ares");
        EntityLineDto cronos = new EntityLineDto(2, "Cronos");

        EntityLineDto[] dtos = new EntityLineDto[]{ares, cronos};
        List<EntityLineDto> expected = Arrays.asList(dtos);

        mockConnection.when(() -> getListDb(EntityLineDto[].class, "linha")).thenReturn(expected);
        mainController.cbbLine.getItems().clear();
        mainController.fillCbbLineSelected();

        mainController.cbbLine.setValue(ares);

        verify(mainController, times(1)).tvOpen();

        mockConnection.close();
    }

    @Test
    public void openTvTest01() {
        doNothing().when(mainController).tvFill();

        mainController.tpModel.setExpanded(false);
        mainController.tvOpen();

        error.checkThat(mainController.tpModel.isDisabled(), is(false));
        error.checkThat(mainController.tpModel.isExpanded(), is(true));
        verify(mainController, times(1)).tvFill();
    }

    @Test
    public void fillTreeViewTest01() {
        mainController.cbbLine.setValue(new EntityLineDto(1, "Ares"));
        doNothing().when(mainController).tiFill(any());

        ArgumentCaptor<TreeItem> captor = ArgumentCaptor.forClass(TreeItem.class);

        mainController.tvFill();

        verify(mainController).tiFill(captor.capture());

        error.checkThat("Check if fillTreeView sets TreeItem to expanded",
                captor.getValue().isExpanded(), is(true));
        assertEquals("Check if fillTreeView sets TreeItem to the correct value",
                mainController.tvModel.getRoot(), captor.getValue());
    }

    @Test
    public void fillTreeViewTest02() {
        mainController.cbbLine.setValue(new EntityLineDto(1, "Ares"));
        doNothing().when(mainController).tiFill (any());

        mainController.tvFill();

        verify(mainController, times(1)).tvFill();
    }

    @Test
    public void tiFillTest01() {
        MockedStatic<JsonMapDto> mockConnection = mockStatic(JsonMapDto.class);
        EntityLineDto cronos = new EntityLineDto(2, "Cronos");

        EntityCategoryDto cronosOld = new EntityCategoryDto(1, "Cronos Old", cronos);
        EntityCategoryDto cronosL = new EntityCategoryDto(1, "Cronos L", cronos);

        EntityModelDto cronos6001A = new EntityModelDto(1, "Cronos 6001-A", cronosOld);
        EntityModelDto cronos6003 = new EntityModelDto(2, "Cronos 6003", cronosOld);
        EntityModelDto cronos6021 = new EntityModelDto(4, "Cronos 6021", cronosL);
        EntityModelDto cronos7023L = new EntityModelDto(5, "Cronos 7023L", cronosL);

        List<EntityCategoryDto> categoryDtosList = new ArrayList<>();
        categoryDtosList.add(cronosOld);
        categoryDtosList.add(cronosL);

        List<EntityModelDto> cronosOldDtoModelsList = new ArrayList<>();
        cronosOldDtoModelsList.add(cronos6001A);
        cronosOldDtoModelsList.add(cronos6003);

        List<EntityModelDto> cronosLDtoModelsList = new ArrayList<>();
        cronosLDtoModelsList.add(cronos6021);
        cronosLDtoModelsList.add(cronos7023L);

        mockConnection.when(() -> getListDb(EntityCategoryDto[].class, "categorias", "Cronos"))
                .thenReturn(categoryDtosList);
        mockConnection.when(() -> getListDb(EntityModelDto[].class, "modelos", "Cronos Old"))
                .thenReturn(cronosOldDtoModelsList);
        mockConnection.when(() -> getListDb(EntityModelDto[].class, "modelos", "Cronos L"))
                .thenReturn(cronosLDtoModelsList);

        TreeItem<EntityLineDto> root = new TreeItem<>(cronos);
        mainController.tiFill(root);

        TreeItem cronosOldTreeItem = new TreeItem("Cronos Old");
        TreeItem cronosLTreeItem = new TreeItem("Cronos L");
        cronosOldDtoModelsList.forEach(e -> cronosOldTreeItem.getChildren().add(new TreeItem<>(e)));
        cronosLDtoModelsList.forEach(e -> cronosLTreeItem.getChildren().add(new TreeItem<>(e)));

        List<TreeItem<EntityLineDto>> expectedCategoryTreeItems = new ArrayList<>();
        expectedCategoryTreeItems.add(cronosOldTreeItem);
        expectedCategoryTreeItems.add(cronosLTreeItem);

        List<TreeItem<EntityLineDto>> actualCategoryTreeItems = new ArrayList<>(root.getChildren());

        StringBuilder expectedModelTreeItems = new StringBuilder();

        StringBuilder actualModelTreeItems = new StringBuilder();
        actualModelTreeItems.append("TreeItem [ value: Cronos Old ]TreeItem [ value: Cronos 6001-A ]TreeItem [ value: Cronos 6003 ]TreeItem [ value: Cronos L ]TreeItem [ value: Cronos 6021 ]TreeItem [ value: Cronos 7023L ]");
        expectedCategoryTreeItems.forEach(categoryTreeItem -> {
            expectedModelTreeItems.append(categoryTreeItem.toString());
            categoryTreeItem.getChildren()
                    .forEach(modelTreeItem -> expectedModelTreeItems.append(modelTreeItem.toString()));
        });
        actualCategoryTreeItems.forEach(categoryTreeItem -> {
            actualModelTreeItems.append(categoryTreeItem.toString());
            categoryTreeItem.getChildren()
                    .forEach(modelTreeItem -> actualModelTreeItems.append(modelTreeItem.toString()));
        });
        assertEquals("Check if model items are set correctly",
                expectedModelTreeItems.toString(), actualModelTreeItems.toString());

        mockConnection.close();
    }

}
