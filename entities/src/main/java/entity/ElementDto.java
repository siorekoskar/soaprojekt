package entity;

import java.io.Serializable;

public class ElementDto implements Serializable {
    private static final long serialVersionUID = -574075082743912950L;

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public Integer getIntType1() {
        return intType1;
    }

    public void setIntType1(Integer intType1) {
        this.intType1 = intType1;
    }

    public Integer getIntType2() {
        return intType2;
    }

    public void setIntType2(Integer intType2) {
        this.intType2 = intType2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    private Integer elementId;
    private Integer intType1;
    private Integer intType2;
    private String name;
    private Integer power;
    private Integer elementTypeId;

    public static ElementDto builder(Elf element){
        ElementDto elementDto = new ElementDto();
        elementDto.setElementId(element.getElfId());
        elementDto.setIntType1(element.getArrowType());
        elementDto.setIntType2(element.getArrowsCount());
        elementDto.setPower(element.getPower());
        elementDto.setName(element.getName());
        return elementDto;
    }

    public static Elf builder(ElementDto elementDto){
        Elf element = new Elf();
        element.setArrowType(elementDto.getIntType1());
        element.setArrowsCount(elementDto.getIntType2());
        element.setPower(elementDto.getPower());
        element.setName(elementDto.getName());
        return element;
    }

    public Integer getElementTypeId() {
        return elementTypeId;
    }

    public void setElementTypeId(Integer elementTypeId) {
        this.elementTypeId = elementTypeId;
    }
}
