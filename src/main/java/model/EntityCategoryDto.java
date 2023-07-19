package model;

import java.util.Objects;

public class EntityCategoryDto extends ObjectAbstractDto {

    private EntityLineDto entityLineDto;

    public EntityCategoryDto() {
    }

    public EntityCategoryDto(int id, String name, EntityLineDto entityLineDto) {
        super(name, id);
        this.entityLineDto = entityLineDto;
    }

    public EntityLineDto getEntityLineDto() {
        return entityLineDto;
    }

    public void setEntityLineDto(EntityLineDto entityLineDto) {
        this.entityLineDto = entityLineDto;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EntityCategoryDto)) {
            return false;
        }

        EntityCategoryDto otherCategory = (EntityCategoryDto) obj;
        return super.equals(obj) && Objects.equals(entityLineDto, otherCategory.entityLineDto);
    }
}