import java.io.Serializable;

public class Facility implements Serializable {
    private static final long serialVersionUID = 1L;




    private boolean garage;
    private boolean pool;
    private boolean lift;

    public Facility(boolean garage, boolean pool, boolean lift) {
        this.garage = garage;
        this.pool = pool;
        this.lift = lift;
    }

    public boolean isGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isLift() {
        return lift;
    }

    public void setLift(boolean lift) {
        this.lift = lift;
    }





}
