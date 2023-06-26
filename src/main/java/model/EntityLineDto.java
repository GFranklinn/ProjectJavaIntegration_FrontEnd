package model;


public class EntityLineDto extends ObjectAbstractDto {

    public EntityLineDto(){}
    public EntityLineDto(int id, String name){
        super(name, id);
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EntityLineDto)) {
            return false;
        }

        EntityLineDto diferentLine = (EntityLineDto) object;
        return super.equals(object);
    }
}
