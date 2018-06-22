package proj;

import entity.ElementType;

import java.io.Serializable;

public class ElementTypeDto implements Serializable {
    private static final long serialVersionUID = 5690458817898727513L;
    private String nameLabel;
    private String intLabel1;
    private String intLabel2;
    private String powerLabel;
    private int id;

    public ElementTypeDto(String nameLabel, String intLabel1, String intLabel2, String powerLabel) {
        this.nameLabel = nameLabel;
        this.intLabel1 = intLabel1;
        this.intLabel2 = intLabel2;
        this.powerLabel = powerLabel;
    }

    public ElementTypeDto() {
    }

    public String getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel = nameLabel;
    }

    public String getIntLabel1() {
        return intLabel1;
    }

    public void setIntLabel1(String intLabel1) {
        this.intLabel1 = intLabel1;
    }

    public String getIntLabel2() {
        return intLabel2;
    }

    public void setIntLabel2(String intLabel2) {
        this.intLabel2 = intLabel2;
    }

    public String getPowerLabel() {
        return powerLabel;
    }

    public void setPowerLabel(String powerLabel) {
        this.powerLabel = powerLabel;
    }

    public static ElementType ElementTypeBuilder(ElementTypeDto elementTypeDto){
        ElementType elementType = new ElementType();
        elementType.setIntLabel1(elementTypeDto.getIntLabel1());
        elementType.setIntLabel2(elementTypeDto.getIntLabel2());
        elementType.setNameLabel(elementTypeDto.getNameLabel());
        elementType.setPowerLabel(elementTypeDto.getPowerLabel());
        return elementType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
