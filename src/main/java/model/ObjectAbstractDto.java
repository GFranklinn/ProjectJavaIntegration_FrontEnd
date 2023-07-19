package model;

import java.util.Objects;

public abstract class ObjectAbstractDto{
    private int id;
    private String name;
    public ObjectAbstractDto() {}

    public  ObjectAbstractDto(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ObjectAbstractDto)) {
            return false;
        }

        ObjectAbstractDto otherObject = (ObjectAbstractDto) obj;

        return Objects.equals(name, otherObject.name)
                && Objects.equals(id, otherObject.id);
    }

    @Override
    public String toString() {
        return name;
    }
}
