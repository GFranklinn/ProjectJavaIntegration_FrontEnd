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
    public void testGetDatabaseList01() {

        MockedStatic<HttpRest> mockConnection = mockStatic(HttpRest.class);

        String endURL = "linha";
        String json = "[{\"id\":1,\"name\":\"Ares\"},{\"id\":2,\"name\":\"Cronos\"}]";


        mockConnection.when(() -> HttpRest.getJsonDb(endURL)).thenReturn(json);

        List<EntityLineDto> expected = new ArrayList<>();
        expected.add(new EntityLineDto(1, "Ares"));
        expected.add(new EntityLineDto(2, "Cronos"));

        EntityLineDto[] actual = new EntityLineDto[2];
        actual[0] = new EntityLineDto(1, "Ares");
        actual[1] = new EntityLineDto(2, "Cronos");

        assertEquals(actual, expected.toArray());

        mockConnection.close();
    }

    @Test
    public void testGetDatabaseList02() {

        MockedStatic<HttpRest> mockConnection = mockStatic(HttpRest.class);

        String endURL = "categorias";
        String json = "[{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}," +
                "{\"id\":2,\"name\":\"Cronos L\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}]";

        mockConnection.when(() -> HttpRest.getJsonDb(endURL)).thenReturn(json);

        List<EntityCategoryDto> expected = new ArrayList<>();
        expected.add(new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos")));
        expected.add(new EntityCategoryDto(2, "Cronos L", new EntityLineDto(2, "Cronos")));

        EntityCategoryDto[] actual = new EntityCategoryDto[2];
        actual[0] = new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"));
        actual[1] = new EntityCategoryDto(2, "Cronos L", new EntityLineDto(2, "Cronos"));

        assertEquals(actual, expected.toArray());

        mockConnection.close();
    }

    @Test
    public void testGetDatabaseList03() {

        MockedStatic<HttpRest> mockConnection = mockStatic(HttpRest.class);

        String endURL = "modelos";
        String json = "[{\"id\":1,\"name\":\"Cronos 6001-A\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}," +
                "{\"id\":2,\"name\":\"Cronos 6003\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}]";


        mockConnection.when(() -> HttpRest.getJsonDb(endURL)).thenReturn(json);

        List<EntityModelDto> expected = new ArrayList<>();
        expected.add(new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"))));
        expected.add(new EntityModelDto(2, "Cronos 6003", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"))));

        EntityModelDto[] actual = new EntityModelDto[2];
        actual[0] = new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos")));
        actual[1] = new EntityModelDto(2, "Cronos 6003", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos")));

        assertEquals(actual, expected.toArray());

        mockConnection.close();
    }

    @Test
    public void testGetDatabaseList04() {

        MockedStatic<HttpRest> mockConnection = mockStatic(HttpRest.class);

        String endURL = "categorias";
        String json = "[{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}," +
                "{\"id\":2,\"name\":\"Cronos L\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}]";
        String filter = "Cronos";


        mockConnection.when(() -> HttpRest.getJsonDb(endURL, filter)).thenReturn(json);

        List<EntityCategoryDto> expected = new ArrayList<>();
        expected.add(new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos")));
        expected.add(new EntityCategoryDto(2, "Cronos L", new EntityLineDto(2, "Cronos")));

        EntityCategoryDto[] actual = new EntityCategoryDto[2];
        actual[0] = new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"));
        actual[1] =new EntityCategoryDto(2, "Cronos L", new EntityLineDto(2, "Cronos"));


        assertEquals(actual, expected.toArray());

        mockConnection.close();
    }

    @Test
    public void testGetDatabaseList05() {

        MockedStatic<HttpRest> mockConnection = mockStatic(HttpRest.class);

        String endURL = "modelos";
        String json = "[{\"id\":1,\"name\":\"Cronos 6001-A\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}," +
                "{\"id\":2,\"name\":\"Cronos 6003\",\"category\":{\"id\":1,\"name\":\"Cronos Old\",\"line\":{\"id\":2,\"name\":\"Cronos\"}}}]";
        String filter = "Cronos Old";

        mockConnection.when(() -> HttpRest.getJsonDb(endURL, filter)).thenReturn(json);

        List<EntityModelDto> expected = new ArrayList<>();
        expected.add(new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"))));
        expected.add(new EntityModelDto(2, "Cronos 6003", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"))));

        EntityModelDto[] actual = new EntityModelDto[2];
        actual[0] = new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos")));
        actual[1] = new EntityModelDto(2, "Cronos 6003", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos")));

        assertEquals(actual, expected.toArray());

        mockConnection.close();
    }
}