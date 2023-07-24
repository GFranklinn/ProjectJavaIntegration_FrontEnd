package model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EntityLineDtoTest {

        @Test
        public void equalsTest01(){
            EntityLineDto line1 = new EntityLineDto(1, "Ares");
            EntityLineDto line2 = new EntityLineDto(1, "Ares");

            assertEquals(line1, line2);
        }

        @Test
        public void equalsTest02(){
            EntityLineDto line1 = new EntityLineDto(2, "Ares");
            EntityLineDto line2 = new EntityLineDto(1, "Ares");

            assertNotEquals(line1, line2);
        }

        @Test
        public void equalsTest04(){
            EntityLineDto line1 = new EntityLineDto(1, "Ares");
            EntityCategoryDto category1 = new EntityCategoryDto(1, "Cronos Old", new EntityLineDto(1, "Cronos"));

            assertNotEquals(line1, category1);
        }

}
