package model;

import java.util.Objects;

public class EntityCategoryDto extends ObjectAbstractDto {

    private EntityLineDto entityLineDto;

    public EntityCategoryDto() {}

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
        EntityCategoryDto other = (EntityCategoryDto) object;
        return super.equals(object) && Objects.equals(this.getEntityLineDto(), other.getEntityLineDto());
    }

    @Override
    public String toString() {
        return getName();
    }
}
