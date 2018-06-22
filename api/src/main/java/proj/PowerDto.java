package proj;

import java.io.Serializable;

public class PowerDto implements Serializable {
    private static final long serialVersionUID = -7803698440778859460L;

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int powerLevel;
    int id;
}
