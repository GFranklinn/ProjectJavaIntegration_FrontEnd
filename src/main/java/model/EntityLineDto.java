package model;

import java.util.Objects;

public class EntityLineDto extends ObjectAbstractDto {

    public EntityLineDto() {}

    public EntityLineDto(int id, String name) {
        super(name, id);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EntityLineDto)) {
            return false;
        }

        EntityLineDto differentLine = (EntityLineDto) object;
        return super.equals(object);
    }

    @Override
    public String toString() {
        return getName();
    }
}