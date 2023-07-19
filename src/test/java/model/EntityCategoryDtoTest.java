package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntityCategoryDtoTest {

    @Test
    public void equalsTest01(){
        EntityCategoryDto category1 = new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos"));
        EntityCategoryDto category2 = new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos"));

        assertEquals(category1, category2);
    }

    @Test
    public void equalsTest02(){
        EntityCategoryDto category1 = new EntityCategoryDto(1, "Cronos L", new EntityLineDto(1, "Cronos"));
        EntityCategoryDto category2 = new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos"));

        assertNotEquals(category1, category2);
    }

    @Test
    public void equalsTest03(){
        EntityCategoryDto category1 = new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(2, "Cronos"));
        EntityCategoryDto category2 = new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos"));

        assertNotEquals(category1, category2);
    }

    @Test
    public void equalsTest04(){
        EntityCategoryDto category1 = new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos"));
        EntityLineDto line1 = new EntityLineDto(1, "Ares");

        assertNotEquals(category1, line1);
    }

}

