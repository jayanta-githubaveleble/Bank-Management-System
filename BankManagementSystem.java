package BankProject;

import java.util.ArrayList;
import java.util.Scanner;

class User {
    String id;
    String name;
    String password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}

class Customer extends User {
    double balance;
    double interestRate;

    public Customer(String id, String name, String password, double balance, double interestRate) {
        super(id, name, password);
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void calculateInterest() {
        balance += (balance * interestRate / 100);
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + "\n Name: " + name + "\n Balance: " + balance;
    }
}

class Employee extends User {
    String position;

    public Employee(String id, String name, String password, String position) {
        super(id, name, password);
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Position: " + position;
    }
}

class Admin extends User {
    public Admin(String id, String name, String password) {
        super(id, name, password);
    }
}

public class BankManagementSystem {
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static Admin admin = new Admin("jayanta1234", "jayanta1234", "jayanta1234");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Bank Management System");
            System.out.println("1. Customer Login");
            System.out.println("2. Employee Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Register as Customer");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    customerLogin(scanner);
                    break;
                case 2:
                    employeeLogin(scanner);
                    break;
                case 3:
                    adminLogin(scanner);
                    break;
                case 4:
                    registerCustomer(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void customerLogin(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.id.equals(id) && customer.password.equals(password)) {
                customerMenu(scanner, customer);
                return;
            }
        }
        System.out.println("Invalid ID or Password.");
    }

    public static void employeeLogin(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (Employee employee : employees) {
            if (employee.id.equals(id) && employee.password.equals(password)) {
                System.out.println("Welcome, " + employee.name);
                return;
            }
        }
        System.out.println("Invalid ID or Password.");
    }

    public static void adminLogin(Scanner scanner) {
        System.out.print("Enter Admin ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (admin.id.equals(id) && admin.password.equals(password)) {
            adminMenu(scanner);
        } else {
            System.out.println("Invalid ID or Password.");
        }
    }

    public static void registerCustomer(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();
        System.out.print("Enter Interest Rate: ");
        double interestRate = scanner.nextDouble();
        scanner.nextLine(); 

        customers.add(new Customer(id, name, password, balance, interestRate));
        System.out.println("Customer registered successfully.");
    }

    public static void customerMenu(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("Welcome, " + customer.name);
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Calculate Interest");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); 
                    customer.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); 
                    customer.withdraw(withdrawAmount);
                    break;
                case 3:
                    customer.calculateInterest();
                    System.out.println("Interest calculated and added to balance.");
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. View All Customers");
            System.out.println("2. View All Employees");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.println("Customers:");
                    for (Customer customer : customers) {
                    	System.out.println(customer);
                    }
                    break;
                case 2:
                    System.out.println("Employees:");
                    for (Employee employee : employees) {
                    	System.out.println(employee);
                    }
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}