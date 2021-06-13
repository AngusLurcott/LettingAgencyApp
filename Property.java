
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    private String address;
    private String city;
    private String postcode;
    private String status;
    private String dateInMarket;
    private List<Room> rooms;
    private static List<Property> properties = new ArrayList<Property>();
    private Tenant tenant;
    private Facility facilities;

    public Property(String address, String city, String postcode, String status, String dateInMarket, List<Room> rooms, Facility facilities, Tenant tenant) {
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.status = status;
        this.dateInMarket = dateInMarket;
        this.rooms = rooms;
        this.facilities = facilities;
        this.tenant = tenant;


    }

    public Property(String address, String city, String postcode, String status, String dateInMarket, Facility facilities, Tenant tenant, List<Room> rooms) {

        this(address, city, postcode, status, dateInMarket, new ArrayList<Room>(), facilities, tenant);
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Facility getFacilities() {
        return facilities;
    }


    public static List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        Property.properties = properties;
    }

    public void setFacilities(Facility facilities) {
        this.facilities = facilities;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }


    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    public String getDateInMarket() {
        return dateInMarket;
    }

    public void setDateInMarket(String dateInMarket) {
        this.dateInMarket = dateInMarket;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }


    public int propertyRent(Property p) {


        int rent = 125;

        if (this instanceof House) {
            rent *= 2;
        }

        String city = p.getCity();


        if (facilities.isPool()) {
            rent += 50;
        }

        if (facilities.isGarage()) {
            rent += 50;
        }

        if (facilities.isLift()) {
            rent += 50;
        }


        switch (city) {
            case "London":
                rent -= 50;

                break;
            case "Cardiff":
                rent += 30;

                break;

            case "Bristol":
                rent += 45;
                break;

            case "Manchester":
                rent += 40;

                break;

        }
        List<Room> rooms = p.getRooms();


        for (Room r : rooms) {

            int area = r.getArea();
            int windows = r.getNumOfWindows();

            rent += windows + 2;  // For each window add amount times by 2

            if (area <= 10) {

                rent += 20;

            } else if (area <= 20) {
                rent += 30;
            } else {
                rent += 35;
            }


        }

        return rent;
    }


    public void addRoom(Room r) {

        this.rooms.add(r);


    }
    public void addProperty(Property p) {

        properties.add(p);
    }


    public static String propertyDetails(Property p) {

        String propertyType;
        String propertyDetails;
        List<Room> propertyRooms;
        propertyRooms = p.getRooms();


        if (p instanceof Flat) {
            propertyType = "Flat";
        } else {
            propertyType = "House";
        }


        propertyDetails = "Property type = " + propertyType + ", Address = " + p.getAddress() + ", City = " + p.getCity() +  ", Postcode = " + p.getPostcode() + ", rooms = [";
        for (Room r : propertyRooms) {
            propertyDetails += (r.getName() + " " + r.getArea() + " m², Number of windows = " + " " + r.getNumOfWindows() + " ");

        }
        propertyDetails += "] Facilities = [";

        if (p.facilities.isLift()) {
            propertyDetails += "Lift,";
        }
        if (p.facilities.isGarage()) {
            propertyDetails += "Garage,";
        }

        if (p.facilities.isPool()) {
            propertyDetails += "Pool";
        }

        propertyDetails += "], rent = " + p.propertyRent(p) + ", status = " + p.getStatus() + ", Date in market = " + p.getDateInMarket();


        return propertyDetails;
    }




    public static void findPropertyByNumRooms(int num) {

        String propertyDetails = "Not found";

        for(Property p: properties) {

            if (p.getRooms().size() == num) {
                System.out.println(propertyDetails(p));
            }
        }



    }

    public static Property propByAddress(String address) {



        Property p = properties.stream()
                .filter
                        (property -> property.getAddress().equals(address))
                .findAny()
                .orElse(null);


        return p;
    }







    public static String findPropertyByAddress(String address) {
        String propertyDetails = "";

        for (Property p : properties) {

            List<Room> propertyRooms;
            propertyRooms = p.getRooms();


            String propertyType;


            if (p instanceof Flat) {
                propertyType = "Flat";
            } else {
                propertyType = "House";
            }


            if (address.equals(p.getAddress())) {

                propertyDetails = "Property type = " + propertyType + ", Address = " + p.getAddress() + ", Postcode = " + p.getPostcode() + ", rooms = [";
                for (Room r : propertyRooms) {
                    propertyDetails += (r.getName() + " " + r.getArea() + " m², Number of windows = " + " " + r.getNumOfWindows() + " ");

                }
                propertyDetails += "] Facilities = [";

                if (p.facilities.isLift()) {
                    propertyDetails += "Lift,";
                }
                if (p.facilities.isGarage()) {
                    propertyDetails += "Garage,";
                }

                if (p.facilities.isPool()) {
                    propertyDetails += "Pool";
                }

                propertyDetails += "], rent = " + p.propertyRent(p) + ", status = " + p.getStatus() + ", Date in market = " + p.getDateInMarket();


            } else {

            }


        }


        return propertyDetails;
    }


    public static void createProperty() {


        Facility facilities = new Facility(false, false, false);


            int counter = 1;
            int a = 1;
        String propertyType = "";
        String status = "";


            try {
                Scanner scanner = new Scanner(System.in);

                int m = 1;
                while(m==1) {
                    System.out.println("Enter 1 if the property is a house and enter 2 if the property is a flat");

                    int prop = scanner.nextInt();
                    if (prop == 1) {
                        propertyType = "House";
                        m=2;
                    } else if(prop == 2) {
                        propertyType = "Flat";
                        m=2;
                    }
                    else {

                    }
                }




                System.out.println("Please enter property address");
                scanner.nextLine();

                String address = scanner.nextLine();

                System.out.println("Please enter city");
                String city = scanner.nextLine();
                System.out.println("Please enter postcode");
                String postcode = scanner.nextLine();

                while (a == 1) {
                    System.out.println("Is the property available (ENTER 1), offer (ENTER 2(, rented (ENTER 3), withdrawn from the market (ENTER 4). Please enter the appropriate number and press enter");




                    int availbility = scanner.nextInt();

                    if (availbility == 1) {
                        status = "available";
                        a = 2;
                    } else if (availbility == 2) {
                        status = "offer";
                        a = 2;
                    } else if (availbility == 3) {
                        status = "rented";
                        a = 2;
                    } else if (availbility == 4) {
                        status = "withdrawn";
                        a = 2;
                    } else {

                        status = "invalid";

                    }

                }

                System.out.println("Enter date in market");
                scanner.nextLine();
                String dateInMarket = scanner.nextLine();


                System.out.println("Enter the amount of rooms. You will then be able to enter the room details");
                int numRooms = scanner.nextInt();

                List<Room> roomsList = new ArrayList<>();

                for (int i = 0; i < numRooms; i++) {

                    System.out.println("Enter name of room " + (i+1));
                    scanner.nextLine();

                    String roomName = scanner.nextLine();
                    System.out.println("Enter length of room");
                    int length = scanner.nextInt();
                    System.out.println("Enter width of room");
                    int width = scanner.nextInt();

                    System.out.println("Ener number of windows");



                    int numWindows = scanner.nextInt();

                    Room room = new Room(roomName, length, width, numWindows);

                    roomsList.add(room);
                }

                int d = 1;
                while (d == 1) {
                    System.out.println("Does the property have a pool? Press 1 for yes and 2 for no");

                    int pool = scanner.nextInt();

                    if (pool == 1) {

                        facilities.setPool(true);
                        d = 2;

                    } else if (pool == 2) {
                        facilities.setPool(false);
                        d = 2;
                    } else {

                        System.out.println("Invalid number");
                    }
                }


                int e = 1;
                while (e == 1) {
                    System.out.println("Does the property have a garage? Press 1 for yes and 2 for no");

                    int garage = scanner.nextInt();
                    if (garage == 1) {

                        facilities.setGarage(true);
                        e = 2;

                    } else if (garage == 2) {
                        facilities.setGarage(false);
                        e = 2;
                    } else {

                        System.out.println("Invalid number");
                    }
                }

                int f = 1;
                while (f == 1) {
                    System.out.println("Does the property have a lift? Press 1 for yes and 2 for no");


                    int lift = scanner.nextInt();
                    if (lift == 1) {

                        facilities.setLift(true);
                        f = 2;

                    } else if (lift == 2) {
                        facilities.setLift(false);
                        f = 2;
                    } else {

                        System.out.println("Invalid number");
                    }
                }


                if (propertyType.equals("House")) {


                    House h1 = new House(address, city, postcode, roomsList, status, dateInMarket, facilities, null);
                    h1.addProperty(h1);

                    System.out.println(h1.propertyRent(h1));
                    System.out.println(House.propertyDetails(h1));

                } else {

                    Flat f1 = new Flat(address, city, postcode, roomsList, status, dateInMarket, facilities, null);
                    f1.addProperty(f1);


                    System.out.println(f1.propertyRent(f1));
                    System.out.println(House.propertyDetails(f1));

                }

                Property.savePropertyToFile();




            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("INVALID INPUT");
                counter = 0;


            }




        }









        public static void savePropertyToFile() {

            try {

                FileOutputStream fileOut = new FileOutputStream("properties.csv");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(properties);
                objectOut.close();
                System.out.println("The property  was succesfully written to the file");



            } catch (IOException ex) {
                ex.printStackTrace();
            }



        }



        public static List<Property> loadPropertyFromFile() {


            try
            {
                FileInputStream fis = new FileInputStream("properties.csv");
                ObjectInputStream ois = new ObjectInputStream(fis);
               Object obj = ois.readObject();

             properties = (ArrayList<Property>) obj;


            }catch(IOException ioe){
                ioe.printStackTrace();

            }catch(ClassNotFoundException c){
                System.out.println("File is Empty");
                c.printStackTrace();

            }

            return properties;

        }








    }

