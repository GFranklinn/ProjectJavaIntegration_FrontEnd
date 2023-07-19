package services;

import model.EntityCategoryDto;
import model.EntityLineDto;
import model.EntityModelDto;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mockStatic;

public class JsonMapDtoTest {

    @Test
    public void testGetListDb01() {

        MockedStatic<HttpRest> mockConnection = mockStatic(HttpRest.class);

        String endURL = "linhas";
        String json = "[{\"id\":1,\"name\":\"Ares\"},{\"id\":2,\"name\":\"Cronos\"}]";


        mockConnection.when(() -> HttpRest.getJsonDb(endURL)).thenReturn(json);

        List<EntityLineDto> actual = JsonMapDto.getListDb(EntityLineDto[].class, endURL);

        List<EntityLineDto> expected = new ArrayList<>();
        expected.add(new EntityLineDto(1, "Ares"));
        expected.add(new EntityLineDto(2, "Cronos"));

        assertEquals(expected, actual);

        mockConnection.close();
    }

    @Test
    public void testGetListDb02() {

        MockedStatic<HttpRest> mockConnection = mockStatic(HttpRest.class);

        String endURL = "categorias";
        String json = "[{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}," +
                "{\"id\":2,\"name\":\"Cronos L\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}]";

        mockConnection.when(() -> HttpRest.getJsonDb(endURL)).thenReturn(json);

        List<EntityCategoryDto> actual = JsonMapDto.getListDb(EntityCategoryDto[].class, endURL);

        List<EntityCategoryDto> expected = new ArrayList<>();
        expected.add(new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos")));
        expected.add(new EntityCategoryDto(2, "Cronos L", new EntityLineDto(2, "Cronos")));


        assertNotEquals(actual, expected);

        mockConnection.close();
    }

    @Test
    public void testGetListDb03() {

        MockedStatic<HttpRest> mockConnection = mockStatic(HttpRest.class);

        String endURL = "modelos";
        String json = "[{\"id\":1,\"name\":\"Cronos 6001-A\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}," +
                "{\"id\":2,\"name\":\"Cronos 6003\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}]";


        mockConnection.when(() -> HttpRest.getJsonDb(endURL)).thenReturn(json);

        List<EntityModelDto> actual = JsonMapDto.getListDb(EntityModelDto[].class, endURL);

        List<EntityModelDto> expected = new ArrayList<>();
        expected.add(new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"))));
        expected.add(new EntityModelDto(2, "Cronos 6003", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"))));


        assertNotEquals(actual, expected);

        mockConnection.close();
    }

    @Test
    public void testGetListDb04() {

        MockedStatic<HttpRest> mockConnection = mockStatic(HttpRest.class);

        String endURL = "categorias";
        String json = "[{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}," +
                "{\"id\":2,\"name\":\"Cronos L\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}]";
        String filter = "Cronos";


        mockConnection.when(() -> HttpRest.getJsonDb(endURL, filter)).thenReturn(json);

        List<EntityCategoryDto> actual = JsonMapDto.getListDb(EntityCategoryDto[].class, endURL, filter);

        List<EntityCategoryDto> expected = new ArrayList<>();
        expected.add(new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos")));
        expected.add(new EntityCategoryDto(2, "Cronos L", new EntityLineDto(2, "Cronos")));


        assertNotEquals(actual, expected);

        mockConnection.close();
    }

    @Test
    public void testGetListDb05() {

        MockedStatic<HttpRest> mockConnection = mockStatic(HttpRest.class);

        String endURL = "modelos";
        String json = "[{\"id\":1,\"name\":\"Cronos 6001-A\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}," +
                "{\"id\":2,\"name\":\"Cronos 6003\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}]";
        String filter = "Cronos Old";


        mockConnection.when(() -> HttpRest.getJsonDb(endURL, filter)).thenReturn(json);

        List<EntityModelDto> actual = JsonMapDto.getListDb(EntityModelDto[].class, endURL, filter);

        List<EntityModelDto> expected = new ArrayList<>();
        expected.add(new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"))));
        expected.add(new EntityModelDto(2, "Cronos 6003", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"))));


        assertNotEquals(actual, expected);

        mockConnection.close();
    }
}
