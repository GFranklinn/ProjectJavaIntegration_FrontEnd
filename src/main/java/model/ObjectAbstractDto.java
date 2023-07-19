package model;

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
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof ObjectAbstractDto)) {
            return false;
        }

        ObjectAbstractDto other = (ObjectAbstractDto) object;

        return id == other.id && name.equals(other.name);
    }
}
