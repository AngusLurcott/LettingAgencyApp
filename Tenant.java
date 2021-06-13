
import static java.lang.Math.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tenant implements Serializable {
    private static final long serialVersionUID = 1L;

    private static List<Tenant> tenants = new ArrayList<>();





    private String name;
    private Property property;
    private LocalDate contractStartDate;
    private int contractLength;




    public Tenant(String name) {
        this.name = name;
    }


    public Tenant(String name, Property property, LocalDate contractStartDate, int contractLength) {

        this.name = name;
        this.property = property;
        this.contractStartDate = contractStartDate;
        this.contractLength = contractLength;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public static List<Tenant> getTenants() {
        return tenants;
    }

    public static void setTenants(List<Tenant> tenants) {
        tenants = tenants;
    }

    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(int day, int month, int year) {

        this.contractStartDate = LocalDate.of(year, month, day);



    }




    public static LocalDate createDate(int day, int month, int year) {

        LocalDate date = LocalDate.of(year, month, day);

        return date;
    }



    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(int contractLength) {
        this.contractLength = contractLength;
    }




    public static String findTenant(String name) {


        String tenantDetails = "Not found";






        for(Tenant t : tenants) {



            if(t.getName().equals(name)) {




                Property p = t.getProperty();

                tenantDetails = "Tenant name = " + t.getName() + " " +  Property.propertyDetails(p) + " Contract Start date  " + t.getContractStartDate() + " Months left of tenancy agreement = " + t.lengthTenancyAgreementValid(t) ;
            }


        }

        return tenantDetails;

    }


    public long lengthTenancyAgreementValid(Tenant t) {

        LocalDate contractStartDate = t.getContractStartDate();
        int contractLength = t.getContractLength();
        LocalDate currentDate = LocalDate.now();

        LocalDate contractEndDate = contractStartDate.plusMonths(contractLength);


        Period period = Period.between(currentDate, contractEndDate);

        long diff = Math.abs(period.getMonths());

        return diff;
    }



    public static void createTenant(String name, String address, LocalDate contractStartDate, int contractLength) {






        List<String> tenantNames = new ArrayList<String>();

        for(Tenant t : tenants) {

            tenantNames.add(t.getName());

        }

        Property p = Property.propByAddress(address);

        boolean found = false;

        for (String element:tenantNames ) {
            if ( element.equals( name)) {
                 found = false;

            }
        }







        if(p.getStatus().equals("available") && !found == true) {

            p.setStatus("rented");
            Tenant tenant = new Tenant(name, p, contractStartDate, contractLength);

            System.out.println(tenant.getName());



            addTenant(tenant);


            saveTenantToFile();
            Property.savePropertyToFile();

            System.out.println("Tenant created successfully");
        }

        else {
            System.out.println("Either the house is not available or this tenant already exists");
        }
    }



    public static void addTenant(Tenant t) {

       tenants.add(t);


    }


    public static void saveTenantToFile() {

        try {

            FileOutputStream fileOut = new FileOutputStream("tenants.csv");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(tenants);
            objectOut.close();
            System.out.println("The tenant  was succesfully written to the file");



        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }


    public static List<Tenant> loadTenantFromFile() {


        try
        {
            FileInputStream fis = new FileInputStream("tenants.csv");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();

            tenants = (ArrayList<Tenant>) obj;


        }catch(IOException ioe){
            ioe.printStackTrace();

        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();

        }

        return tenants;

    }




}
