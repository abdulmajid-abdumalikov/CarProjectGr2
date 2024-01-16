import beans.APIResponse;
import beans.CarBean;
import beans.UserBean;
import db.DB;
import resources.CarResource;
import resources.UserResource;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static Scanner scanInt = new Scanner(System.in);
    static Scanner scanStr = new Scanner(System.in);


    public static void main(String[] args) {

        showMenu();

    }

    private static void showMenu() {
        System.out.println("0. Exit");
        if (DB.session == null) {
            System.out.println("1. Register");
            System.out.println("2. Login");

        } else {
            System.out.println("3. Add car");
            System.out.println("4. Show my cars");
            System.out.println("5. Put my car to store");
            System.out.println("6. Buy car");
            System.out.println("7. Check balancee");
            System.out.println("8. Log out");
        }


        int choice = scanInt.nextInt();
        switch (choice) {
            case 0 -> {
                return;
            }
            case 1 -> {
                register();
            }
            case 2 -> {
                login();
            }
            case 3 -> {
                addCar();
            }
            case 4 -> {
                showMyCars();
            }
            case 5 -> {
                putMyCarToStore();
            }
            case 6 -> {
                buyCar();
            }
            case 7 -> {
                checkBalance();
            }
            case 8 -> {
                logout();
            }
            default -> System.out.println("Invalid choice");
        }
        showMenu();
    }

    private static void register() {

        UserResource resource = new UserResource();

        System.out.print("Enter username: ");
        String username = scanStr.next();

        System.out.print("Enter password: ");
        String p1 = scanStr.next();
        System.out.print("Confirm password: ");
        String p2 = scanStr.next();

        if (!p1.equals(p2)) {
            System.out.println("Passwords did not match!");
            register();
        }

        UserBean bean = new UserBean(username, p1);

        APIResponse resp = resource.add(bean);

        System.out.println(resp.getMessage());

        if (resp.getCode().equals(200)) {
            DB.session = (UserBean) resp.getData();
        } else if (resp.getCode().equals(400)) {
            register();
        }

    }

    private static void login() {

        UserResource resource = new UserResource();

        System.out.print("Enter username: ");
        String username = scanStr.next();

        System.out.print("Enter password: ");
        String p1 = scanStr.next();
        APIResponse login = resource.login(new UserBean(username, p1));
        System.out.println(login.getMessage());
        if (login.getCode().equals(200)) {
            DB.session = (UserBean) login.getData();

        } else {
            login();
        }

    }

    private static void addCar() {

        System.out.print("Enter car name: ");
        scanStr = new Scanner(System.in);
        String carName = scanStr.nextLine();
        System.out.print("Enter car color: ");
        String carColor = scanStr.next();
        System.out.print("Enter car price: ");
        Integer price = scanInt.nextInt();
        if (price < DB.session.getBalance()) {
            CarBean car = new CarBean(carName, carColor, price, DB.session.getID());
            CarResource carResource = new CarResource();
            APIResponse add = carResource.add(car);
            DB.session.setBalance(DB.session.getBalance() - price);

            System.out.println(add.getMessage());
            if (add.getCode().equals(400)) {
                addCar();
            }
        } else {
            System.out.println("You do not have enough money!");
        }


    }

    private static void showMyCars() {

        CarResource resource = new CarResource();
        APIResponse cars = resource.getCars(DB.session.getID());
        System.out.println("*******************");
        if (cars.getCode().equals(400)) {
            showMyCars();
        } else if (cars.getCode().equals(200)) {
            if (cars.getData() != null) {
                List<CarBean> list = (List) cars.getData();
                for (CarBean car : list) {
                    if (car.getInStore()) {
                        System.out.println(car + " (In store)");
                    } else {
                        System.out.println(car + (" (In garage)"));
                    }
                }
            }
        }
        System.out.println("*******************");

    }

    private static void putMyCarToStore() {

        CarResource resource = new CarResource();
        APIResponse cars = resource.getCars(DB.session.getID());
        System.out.println("*******************");
        if (cars.getCode().equals(400)) {
            showMyCars();
        } else if (cars.getCode().equals(200)) {
            if (cars.getData() != null) {
                List<CarBean> list = (List) cars.getData();
                for (CarBean car : list) {
                    if (!car.getInStore() && Objects.equals(car.getUserID(), DB.session.getID())) {
                        System.out.println(car);
                    }
                }
                System.out.println("*******************");
                System.out.print("Choose car ID: ");
                int choice = scanInt.nextInt();
                System.out.print("Enter price: ");
                int price = scanInt.nextInt();
                if (choice > list.size()) {
                    System.out.println("Invalid ID!");
                } else {
                    for (CarBean carBean : list) {
                        if (choice == carBean.getID()) {
                            carBean.setInStore(true);
                            carBean.setPrice(price);
                        }
                    }
                }
            }
        }
    }

    private static void buyCar() {

//        CarResource resource = new CarResource();

        for (CarBean car : DB.getCars()) {
            if (car.getInStore() && !Objects.equals(car.getUserID(), DB.session.getID())) {
                System.out.println(car);
            }
        }
        System.out.println("*******************");
        System.out.print("Choose car ID: ");
        int choice = scanInt.nextInt();
        if (choice > DB.getCars().size()) {
            System.out.println("Invalid ID!");
        } else {
            for (CarBean carBean : DB.getCars()) {
                if (choice == carBean.getID()) {
                    if (carBean.getPrice() < DB.session.getBalance()) {
                        carBean.setInStore(false);
                        carBean.setUserID(DB.session.getID());
                        System.out.println("Success!");
                        DB.session.setBalance(DB.session.getBalance() - carBean.getPrice());
                        for (CarBean car : DB.getCars()) {
                            if (Objects.equals(car.getID(), carBean.getID())) {
                                for (UserBean user : DB.getUsers()) {
                                    if (Objects.equals(car.getUserID(), user.getID())) {
                                        user.setBalance(user.getBalance() + car.getPrice());
                                    }
                                }
                                car.setUserID(DB.session.getID());
                            }
                        }
                    } else {
                        System.out.println("You dont have enough money!");
                    }
                }
            }
        }


    }

    private static void checkBalance() {

        System.out.println(DB.session.getBalance());

    }

    private static void logout() {

        System.out.println("Successfully logged out!");
        DB.session = null;

    }
}