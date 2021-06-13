import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Flat extends Property implements Serializable {

    private static final long serialVersionUID = 1L;

    List<Room> rooms = new ArrayList<Room>();






    public Flat(String address, String city, String postcode, List<Room> rooms, String status, String dateInMarket, Facility facilities, Tenant tenant) {
        super(address, city, postcode, status,dateInMarket, rooms, facilities, tenant);



    }

    public Flat(String address, String city, String postcode, String status, String dateInMarket, Facility facilities, Tenant tenant, List<Room> rooms) {
        super(address, city, postcode, status,dateInMarket,facilities, tenant, rooms);
    }
























}
