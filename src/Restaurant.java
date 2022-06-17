import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Restaurant {

  /*
   * name of restaurant
   * location of restaurant
   * contact number restaurant
   * how many each meal is being ordered
   * list of meals being ordered and prices
   * special preparation instructions by customer
   * total amount to be paid
   **/

  private HashMap < String, String > restaurantLocation = new HashMap < String, String > ();
  private HashMap < String, String > restaurantContact = new HashMap < String, String > ();

  private HashMap < String, String > availableRestaurants = new HashMap < String, String > ();

  private ArrayList < String > burgerKingMealPrices = new ArrayList < String > ();
  private ArrayList < String > kFCMealPrices = new ArrayList < String > ();
  private ArrayList < String > pizzaPerfectMealPrices = new ArrayList < String > ();

  // method that sets all the details pertaining to each restaurant
  public Restaurant() {

    this.restaurantLocation.put("Burger King", "Witbank");
    this.restaurantLocation.put("KFC", "Johannesburg");
    this.restaurantLocation.put("Pizza perfect", "Durban");

    this.restaurantContact.put("Burger King", "011 111 1111");
    this.restaurantContact.put("KFC", "022 222 2222");
    this.restaurantContact.put("Pizza perfect", "033 333 3333");

    this.availableRestaurants.put("Burger King", "Whopper,double Whopper,tripple Whopper");
    this.availableRestaurants.put("KFC", "9 Piece Bucket + FREE Large Chips,Wrapsta Box with Small Drink,LARGE FULLY LOADED BOX MEAL WITH COLONEL BURGER AND NO-SUGAR SODA FOUNTAIN");
    this.availableRestaurants.put("Pizza perfect", "Perfect,Maestro,Mystic");

    this.burgerKingMealPrices.add("Whopper,double Whopper,tripple Whopper");
    this.burgerKingMealPrices.add("30.50,60.10,90.30");

    this.kFCMealPrices.add("9 Piece Bucket + FREE Large Chips,Wrapsta Box with Small Drink,LARGE FULLY LOADED BOX MEAL WITH COLONEL BURGER AND NO-SUGAR SODA FOUNTAIN");
    this.kFCMealPrices.add("56.90,67.50,101.30");

    this.pizzaPerfectMealPrices.add("Perfect,Maestro,Mince Nachos");
    this.pizzaPerfectMealPrices.add("43.60,50.21,74.55");
  }

  // method to get the meal and the associated price
  public String selectMeal(String restaurant, String meal) {
    if (!restaurant.equals("")) {

      switch (restaurant) {
      case "Burger King":
        String[] burgerKingMeals = burgerKingMealPrices.get(0).split(",");
        String[] burgerKingPrices = burgerKingMealPrices.get(1).split(",");

        for (int i = 0; i < burgerKingMeals.length; i++) {
          if (burgerKingMeals[i].equals(meal)) {
            return burgerKingMeals[i] + "," + burgerKingPrices[i];
          }
        }

        break;

      case "KFC":

        String[] kfcMeals = kFCMealPrices.get(0).split(",");
        String[] kfcPrices = kFCMealPrices.get(1).split(",");

        for (int i = 0; i < kfcMeals.length; i++) {
          if (kfcMeals[i].equals(meal)) {
            return kfcMeals[i] + "," + kfcPrices[i];
          }
        }

        break;

      case "Pizza perfect":

        String[] ppMeals = pizzaPerfectMealPrices.get(0).split(",");
        String[] ppPrices = pizzaPerfectMealPrices.get(1).split(",");

        for (int i = 0; i < ppMeals.length; i++) {
          if (ppMeals[i].equals(meal)) {
            return ppMeals[i] + "," + ppPrices[i];
          }
        }

        break;
      }
    }
    return meal;
  }

  // method to get the meal and the price together
  public void getMealPrice(String restaurantName) {

    // ensure value is not empty
    if (!restaurantName.equals("")) {

      switch (restaurantName) {
      case "Burger King":
        String[] burgerKingMeals = burgerKingMealPrices.get(0).split(",");
        String[] burgerKingPrices = burgerKingMealPrices.get(1).split(",");

        for (int i = 0; i < burgerKingMeals.length; i++) {
          System.out.println(i + 1 + ". " + burgerKingMeals[i] + "   R" + burgerKingPrices[i]);
        }

        break;

      case "KFC":

        String[] kFCMeals = kFCMealPrices.get(0).split(",");
        String[] kFCPrices = kFCMealPrices.get(1).split(",");

        for (int i = 0; i < kFCMeals.length; i++) {
          System.out.println(i + 1 + ".  " + kFCMeals[i] + "   R" + kFCPrices[i]);
        }

        break;

      case "Pizza perfect":

        String[] pizzaPerfectMeals = pizzaPerfectMealPrices.get(0).split(",");
        String[] pizzaPerfectPrices = pizzaPerfectMealPrices.get(1).split(",");

        for (int i = 0; i < pizzaPerfectMeals.length; i++) {
          System.out.println(i + 1 + ". " + pizzaPerfectMeals[i] + "   R" + pizzaPerfectPrices[i]);
        }

        break;
      }
    }
  }

  // method that lists the available restaurants
  public String setRestaurant() {

    int i = 0;

    System.out.println("Available Restaurants: \n");

    // iterate through each restaurant
    for (String restaurantName: availableRestaurants.keySet()) {

      i++;

      System.out.println(i + ". " + restaurantName);
      System.out.println("Meals: \n" + availableRestaurants.get(restaurantName).replace(',', '\n') + "\n");
    }

    // user input for the selected meal
    String chosenRestaurant = JOptionPane.showInputDialog("Select a Restaurant:");

    for (String restaurantName: availableRestaurants.keySet()) {

      if (restaurantName.equals(chosenRestaurant)) {
        return restaurantName;
      }
    }

    return "Restaurant not found";
  }

  //  get the restaurant details
  public String[] getRestaurantDetails(String restaurantName) {

    String[] restaurantDetails = {
      restaurantLocation.get(restaurantName),
      restaurantContact.get(restaurantName)
    };

    return restaurantDetails;

  }

  // get the nearest driver to the location
  public String[] getNearestDriver(String location) {

    try {

      File driverFile = new File("./drivers.txt"); //creates a new file instance

      FileReader fileReader = new FileReader(driverFile); //reads the file

      try (BufferedReader bufferReader = new BufferedReader(fileReader)) {

        StringBuffer stringBuffer = new StringBuffer(); //constructs a string buffer with no characters

        String line;

        while ((line = bufferReader.readLine()) != null) {

          stringBuffer.append(line); //appends line to string buffer

          String[] split = line.split(",");

          String driverLocation = split[1].trim();

          if (driverLocation.equals(location)) {

            return split;

          }

          stringBuffer.append("\n"); //line feed   
        }
      }

      fileReader.close(); //closes the stream and release the resources			
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}