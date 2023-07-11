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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EntityCategoryDto)) {
            return false;
        }

        if (!super.equals(object)) {
            return false;
        }

        EntityCategoryDto differentCategory = (EntityCategoryDto) object;

        return Objects.equals(entityLineDto, differentCategory.entityLineDto);
    }
    @Override
    public String toString() {
        return getName();
    }
}
