package soap;

import java.io.Serializable;

public class UpdateElementDto implements Serializable {
    private static final long serialVersionUID = -1987030297646831187L;

    public Integer getElementId() {
        return elementId;
    }

    public Integer getNewArrowCount() {
        return newArrowCount;
    }

    private Integer elementId;
    private Integer newArrowCount;

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public void setNewArrowCount(Integer newArrowCount) {
        this.newArrowCount = newArrowCount;
    }
}
