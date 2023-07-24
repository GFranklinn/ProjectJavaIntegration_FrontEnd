package model;

import java.util.Objects;

public class EntityLineDto extends ObjectAbstractDto {

    public EntityLineDto() {}

    public EntityLineDto(int id, String name) {
        super(name, id);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof EntityLineDto)) {
            return false;
        }
        EntityLineDto other = (EntityLineDto) object;
        return this.getId() == other.getId() && Objects.equals(this.getName(), other.getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}