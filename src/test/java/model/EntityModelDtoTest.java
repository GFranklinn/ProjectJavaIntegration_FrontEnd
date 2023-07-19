package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntityModelDtoTest {

    @Test
    public void equalsTest01(){
        EntityModelDto model1 = new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos")));
        EntityModelDto model2 = new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos")));

        assertEquals(model1, model2);
    }

    @Test
    public void equalsTest02(){
        EntityModelDto model1 = new EntityModelDto(1, "Cronos 6001-B", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos")));
        EntityModelDto model2 = new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos")));

        assertNotEquals(model1,model2);
    }

    @Test
    public void equalsTest03(){
        EntityModelDto model1 = new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos L", new EntityLineDto(1, "Cronos")));
        EntityModelDto model2 = new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos")));

        assertNotEquals(model1, model2);
    }

    @Test
    public void equalsTest04(){
        EntityModelDto model1 = new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos")));
        EntityModelDto model2 = new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos")));

        assertNotEquals(model1,model2);
    }

    @Test
    public void equalsTest05(){
        EntityModelDto model1 = new EntityModelDto(1, "Cronos 6001-A", new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos")));
        EntityCategoryDto category1 = new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos"));

        assertNotEquals(model1, category1);
    }
}
