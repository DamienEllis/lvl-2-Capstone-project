import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

  public static void main(String[] args) {

    // invoke new restaurant object
    Restaurant restaurant = new Restaurant();

    System.out.println();

    // set the array list which will hold the order details and restaurant details.
    ArrayList < String[] > orderedMeals = new ArrayList < String[] > ();
    ArrayList < String[] > restaurantDetails = new ArrayList < String[] > ();

    Customer customer = new Customer();

    // sets the customer details
    customer.setCustomerDetails();

    // create string array which will hold the details of the customer object
    String[] customerDetails = {
      String.valueOf(customer.getOrderNumber()),
      customer.getCustomerName(),
      customer.getContactNumber(),
      customer.getEmail(),
      customer.getCustomerLocation(),
      customer.getAddress(),
    };

    // add customer details to ordered meals
    orderedMeals.add(customerDetails);

    String controlInput;

    // continue asking the customer for their meal until n is chosen
    do {

      Customer customerObject = new Customer();

      // select order of the customer
      customerObject.selectOrder();

      // set the restaurant details
      String[] restDetails = {
        customerObject.getRestaurant(),
        customerObject.getRestaurantContact(),
        customerObject.getRestaurantLocation()
      };

      // set the order details
      String[] orderDetails = {
        customerObject.getSelectedMeal(),
        customerObject.getSelectedMealPrice(),
        customerObject.getSpecialInstruction(),
        String.valueOf(customerObject.getMealQuantity())
      };

      restaurantDetails.add(restDetails);
      orderedMeals.add(orderDetails);

      // get the user input
      controlInput = JOptionPane.showInputDialog("Do you want to continue (Type y or n)");

    } while (controlInput.equals("Y") || controlInput.equals("y"));

    // get the restaurant details from the array
    String[] restaurantObject = restaurantDetails.get(0);

    // get the restaurants location
    String resLocation = restaurant.getRestaurantDetails(restaurantObject[0])[0];

    // ensure that driver and restaurant are in same location
    if (!(restaurant.getNearestDriver(resLocation) == null)) {

      // set the drivers details
      String[] driverDetails = restaurant.getNearestDriver(resLocation);

      BufferedWriter bufferWriter = null;

      try {

        double total = 0;

        // get the customers details
        String[] customerDetail = orderedMeals.get(0);

        // set the output string for the file
        String fileContentOutput = "Order Number " + customerDetail[0] +
          "\nCustomer: " + customerDetail[1] +
          "\nEmail: " + customerDetail[3] +
          "\nPhone Number: " + customerDetail[2] +
          "\nLocation: " + customerDetail[4] +
          "\n\n";

        fileContentOutput = fileContentOutput + "You have ordered the following from " + restaurantObject[0] + " in " + restaurantObject[2];

        fileContentOutput = fileContentOutput + "\n\n";

        // iterate through orderedMeals as append to the file contents output
        for (int i = 1; i < orderedMeals.size(); i++) {

          int quantity = 0;

          String[] orderDetails = orderedMeals.get(i);

          // convert the quantity to int
          quantity = Integer.parseInt(orderDetails[3]);

          // calculate the total of all the meals ordered
          total = total + (Double.parseDouble(orderDetails[1]) * quantity);

          // append to the file content output
          fileContentOutput = fileContentOutput + orderDetails[3] + " X " + orderDetails[0] + "(R" + orderDetails[1] + ")\n Special Instructions: " + orderDetails[2] + "\n";

        }

        fileContentOutput = fileContentOutput + "\ntotal: R" + total + "\n";

        fileContentOutput = fileContentOutput + "\n" + driverDetails[0] + " is nearest to the restaurant and so he will be delivering your order to you at \n\n" + customerDetail[4] + "\n" + customerDetail[5];

        fileContentOutput = fileContentOutput + "\n\nif you need to contact the restaurant, their number is " + restaurantObject[1];

        //Specify the file name and path here
        File invoiceFile = new File("./invoice.txt");

        FileWriter fileWriter = new FileWriter(invoiceFile);

        bufferWriter = new BufferedWriter(fileWriter);

        // write output to the file
        bufferWriter.write(fileContentOutput);

        System.out.println("\n\nInvoice created");

      } catch (IOException ioError) {

        // catch io exception
        ioError.printStackTrace();

      } finally {
        try {
          // close file resources
          if (bufferWriter != null) {
            bufferWriter.close();
          }
        } catch (Exception exception) {
          System.out.println("Error in closing the BufferedWriter " + exception);
        }
      }
    } else {
      System.out.print("Sorry! Our drivers are too far away from you to be able to deliver to your location.");
    }
  }
}