

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {




    public static void main(String[] args) {


//        Property.savePropertyToFile();
//        Tenant.saveTenantToFile();






        Tenant.setTenants(Tenant.loadTenantFromFile());
//
        Property.loadPropertyFromFile();
        Tenant.loadTenantFromFile();

        Tenant t = new Tenant("Angus", null, null, 2);


        System.out.println("Amount of tenants in system is: " + Tenant.getTenants().size());
        System.out.println("Amount of properties in system is: " + Property.getProperties().size());




        System.out.println("What would you like to do? Press 1 to create a new property and calculate its rent.");
        System.out.println("Press 2 if you would like to find a property by searching the address");
        System.out.println("Press 3 if you would like to find a property by searching the amount of rooms");
        System.out.println("Press 4 if you would like to enter a new tenant into the system");
        System.out.println("Press 5 if you would like to search for a tenant");



        try {

            Scanner sc = new Scanner(System.in);

            int input1 = sc.nextInt();
            if (input1 == 1) {

                Property.createProperty();


            }

            else if(input1 == 2) {

                System.out.println("Please enter the address");
                sc.nextLine();
                String inputAddress = sc.nextLine();


                System.out.println(Property.findPropertyByAddress(inputAddress));

            }

            else if(input1 == 3) {
                System.out.println("Please enter the number of rooms");

                int input2 = sc.nextInt();

               Property.findPropertyByNumRooms(input2);
            }
            else if(input1 == 4) {
                System.out.println("Please enter the name of the tenant");

                sc.nextLine();
                String name = sc.nextLine();


                System.out.println("Please enter the address of the property");
                String address = sc.nextLine();

                System.out.println("Enter the contract start date year");

                int year = sc.nextInt();

                System.out.println("Enter the contract start date month");
                int month = sc.nextInt();
                System.out.println("Enter the contract start day");
                int day = sc.nextInt();

                LocalDate contractStartDate = Tenant.createDate(day, month, year);

                System.out.println("Enter the contract length in months e.g. 12");

                int length = sc.nextInt();




                System.out.println("Creating tenant...");

                Tenant.createTenant(name, address, contractStartDate, length);
            }

            else if(input1 == 5) {
                System.out.println("Please enter the tenant name");


                sc.nextLine();
                String name  = sc.nextLine();


                System.out.println("Finding tenant...");
                System.out.println(Tenant.findTenant(name));

            }




        }catch(InputMismatchException e) {

            System.out.println("INVALID INPUT");

        }


//
//         List<Property> properties = Property.getProperties();
//
//
//        for(Property p: properties) {
//
//            System.out.println(Property.propertyDetails(p));
//
//        }
//
//










    }

}