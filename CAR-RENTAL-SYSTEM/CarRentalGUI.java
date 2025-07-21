import java.awt.*;
import java.util.*;
import javax.swing.*;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double pricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() { return carId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public String getDisplayInfo() {
        return carId + " - " + brand + " " + model + " ($" + pricePerDay + "/day) - " +
                (isAvailable ? "Available" : "Rented");
    }
}

class Customer {
    private String customerId;
    private String name;
    private String licenseNumber;

    public Customer(String customerId, String name, String licenseNumber) {
        this.customerId = customerId;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getLicenseNumber() { return licenseNumber; }
}

class Rental {
    private Customer customer;
    private Car car;
    private int rentalDays;

    public Rental(Customer customer, Car car, int rentalDays) {
        this.customer = customer;
        this.car = car;
        this.rentalDays = rentalDays;
    }

    public double calculateTotal() {
        return car.getPricePerDay() * rentalDays;
    }

    public String getReceipt() {
        return "Rental Summary:\n"
                + "Customer: " + customer.getName() + "\n"
                + "Car: " + car.getBrand() + " " + car.getModel() + "\n"
                + "Days: " + rentalDays + "\n"
                + "Total Cost: $" + calculateTotal();
    }
}

public class CarRentalGUI extends JFrame {
    private java.util.List<Car> cars = new ArrayList<>();
    private java.util.List<Customer> customers = new ArrayList<>();
    private JTextArea outputArea;

    public CarRentalGUI() {
        setTitle("\uD83D\uDE97 Car Rental System");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        JButton showCarsBtn = new JButton("Show Available Cars");
        JButton rentCarBtn = new JButton("Rent a Car");
        JButton returnCarBtn = new JButton("Return a Car");
        JButton addCustomerBtn = new JButton("Add Customer");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(showCarsBtn);
        buttonPanel.add(rentCarBtn);
        buttonPanel.add(returnCarBtn);
        buttonPanel.add(addCustomerBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Sample Data
        cars.add(new Car("C101", "Toyota", "Camry", 55.0));
        cars.add(new Car("C102", "Honda", "Civic", 50.0));
        cars.add(new Car("C103", "Ford", "Focus", 45.0));

        customers.add(new Customer("U001", "Aryan", "DL12345"));
        customers.add(new Customer("U002", "Priya", "MH99887"));

        showCarsBtn.addActionListener(e -> showAvailableCars());
        rentCarBtn.addActionListener(e -> rentCar());
        returnCarBtn.addActionListener(e -> returnCar());
        addCustomerBtn.addActionListener(e -> addCustomer());
        exitBtn.addActionListener(e -> System.exit(0));
    }
    private void showAvailableCars() {
        outputArea.setText("\uD83D\uDCCB Available Cars:\n\n");
        for (Car car : cars) {
            outputArea.append(car.getDisplayInfo() + "\n");
        }
    }

    private void rentCar() {
        String custId = JOptionPane.showInputDialog(this, "Enter Customer ID:");
        if (custId == null) return;
        Customer customer = findCustomerById(custId);
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "Customer not found!");
            return;
        }

        String carId = JOptionPane.showInputDialog(this, "Enter Car ID to Rent:");
        if (carId == null) return;
        Car car = findCarById(carId);
        if (car == null || !car.isAvailable()) {
            JOptionPane.showMessageDialog(this, "Car not available!");
            return;
        }

        String daysStr = JOptionPane.showInputDialog(this, "Enter number of rental days:");
        if (daysStr == null) return;
        int days;
        try {
            days = Integer.parseInt(daysStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid number of days!");
            return;
        }

        Rental rental = new Rental(customer, car, days);
        car.setAvailable(false);

        JOptionPane.showMessageDialog(this, rental.getReceipt(), "Rental Receipt", JOptionPane.INFORMATION_MESSAGE);
        showAvailableCars();
    }

    private void returnCar() {
        String carId = JOptionPane.showInputDialog(this, "Enter Car ID to return:");
        if (carId == null) return;

        Car car = findCarById(carId);
        if (car == null) {
            JOptionPane.showMessageDialog(this, "Car not found!");
            return;
        }

        if (car.isAvailable()) {
            JOptionPane.showMessageDialog(this, "Car is already available!");
        } else {
            car.setAvailable(true);
            JOptionPane.showMessageDialog(this, "Car returned successfully!");
            showAvailableCars();
        }
    }

    private void addCustomer() {
        String id = JOptionPane.showInputDialog(this, "Enter Customer ID:");
        if (id == null) return;
        String name = JOptionPane.showInputDialog(this, "Enter Name:");
        if (name == null) return;
        String license = JOptionPane.showInputDialog(this, "Enter License Number:");
        if (license == null) return;

        customers.add(new Customer(id, name, license));
        JOptionPane.showMessageDialog(this, "Customer added successfully!");
    }

    private Car findCarById(String carId) {
        for (Car car : cars) {
            if (car.getCarId().equalsIgnoreCase(carId)) {
                return car;
            }
        }
        return null;
    }

    private Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarRentalGUI gui = new CarRentalGUI();
            gui.setVisible(true);
        });
    }
}
