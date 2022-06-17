import java.util.Random;
import javax.swing.JOptionPane;

public class Customer extends Restaurant {

  private int orderNumber;
  private int mealQuantity;

  private String customerName;
  private String contactNumber;
  private String address;
  private String cityLocation;
  private String email;
  private String selectedRestaurant;
  private String restaurantLocation;
  private String restaurantContact;
  private String selectedMeal;
  private String selectedMealPrice;
  private String specialInstruction;

  // constructor method to set the customer details
  public void setCustomerDetails() {

    String customerNameInput = JOptionPane.showInputDialog("Enter your name");
    String addressInput = JOptionPane.showInputDialog("Enter your address");
    String cityInput = JOptionPane.showInputDialog("Enter your city");
    String emailInput = JOptionPane.showInputDialog("Enter your email address");
    String contactNumberInput = JOptionPane.showInputDialog("Enter your cell number");

    this.contactNumber = contactNumberInput;
    this.customerName = customerNameInput;
    this.address = addressInput;
    this.cityLocation = cityInput;
    this.email = emailInput;
    this.orderNumber = generateOrderNumber();
  }

  // method to get the customer name
  public String getCustomerName() {
    return this.customerName;
  }

  // method to get the customer email
  public String getEmail() {
    return this.email;
  }

  // method to get the customer contact number
  public String getContactNumber() {
    return this.contactNumber;
  }

  // method to get the customer address
  public String getAddress() {
    return this.address;
  }

  // method to get the customer location
  public String getCustomerLocation() {
    return this.cityLocation;
  }

  // method to get the customer order number
  public int getOrderNumber() {
    return this.orderNumber;
  }

  // method to get the special order instructions
  public String getSpecialInstruction() {
    return this.specialInstruction;
  }

  // method to get the customers selected meal
  public String getSelectedMeal() {
    return this.selectedMeal;
  }

  // method to get the meal price of the selected meal
  public String getSelectedMealPrice() {
    return this.selectedMealPrice;
  }

  // method to get the meal quantity
  public int getMealQuantity() {
    return this.mealQuantity;
  }

  private int generateOrderNumber() {
    // It will generate 6 digit random Number.
    // from 0 to 999999
    Random rnd = new Random();
    int number = rnd.nextInt(999999);

    // format the number
    int orderNumber1 = Integer.valueOf(String.format("%06d", number));

    return orderNumber1;
  }

  // method to set the customers order
  public void selectOrder() {

    // invoke the restaurant object
    Restaurant restaurant = new Restaurant();

    //set the restaurant.
    selectedRestaurant = setRestaurant();

    // get restaurant location and contact
    this.restaurantLocation = restaurant.getRestaurantDetails(this.selectedRestaurant)[0];
    this.restaurantContact = restaurant.getRestaurantDetails(this.selectedRestaurant)[1];

    // get the meal from the input
    String meal = JOptionPane.showInputDialog("Select a meal");

    // set the meal quantity
    this.mealQuantity = Integer.parseInt(JOptionPane.showInputDialog("Select quantity"));

    // set the selected meal and its price
    String[] mealSelected = selectMeal(this.selectedRestaurant, meal).split(",");

    this.selectedMeal = mealSelected[0];
    this.selectedMealPrice = mealSelected[1];

    // set the special instruction
    String specialInstruction = JOptionPane.showInputDialog("Any Special instructions? n to continue");

    // ensure special instruction does not equal to null
    if (!specialInstruction.equals("n")) {
      this.specialInstruction = specialInstruction;
    }
  }

  // method to get the selected restaurant
  public String getRestaurant() {

    return this.selectedRestaurant;
  }

  // method to get the selected restaurants location
  public String getRestaurantLocation() {

    return this.restaurantLocation;
  }

  // method to get the selected restaurants contact number
  public String getRestaurantContact() {

    return this.restaurantContact;
  }
}