package db;

import beans.CarBean;
import beans.UserBean;

import java.util.ArrayList;
import java.util.List;

public class DB {

    private static List<UserBean> users = new ArrayList<>();
    private static List<CarBean> cars = new ArrayList<>();
    public static UserBean session = null;

    public static List<UserBean> getUsers() {
        return users;
    }

    public static void setUsers(List<UserBean> users) {
        DB.users = users;
    }

    public static List<CarBean> getCars() {
        return cars;
    }

    public static void setCars(List<CarBean> cars) {
        DB.cars = cars;
    }

    public static UserBean addUser(UserBean bean) {

        for (UserBean user : users) {
            if (user.getUsername().equals(bean.getUsername())) {
                return null;
            }
        }
        bean.setID(users.size());
        users.add(bean);
        return bean;

    }

    public static UserBean getUser(Integer ID) {

        if (ID < 0 || ID > users.size()) {
            return null;
        }

        return users.get(ID);
    }

    public static UserBean getUser(String username, String password) {

        for (UserBean user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;

    }

    public static boolean updateUser(UserBean newBean) {

        if (newBean.getID() < 0 || newBean.getID() > users.size()) {
            return false;
        }
        users.set(newBean.getID(), newBean);

        return true;
    }

    public static boolean deleteUser(Integer ID) {

        if (ID < 0 || ID > users.size()) {
            return false;
        }

        return users.remove(ID);
    }

    public static Integer addCar(CarBean bean) {

        bean.setID(cars.size());
        cars.add(bean);
        return bean.getID();

    }

    public static CarBean getCar(Integer ID) {

        if (ID < 0 || ID > cars.size()) {
            return null;
        }
        return cars.get(ID);

    }

    public static boolean updateCar(CarBean newBean) {

        if (newBean.getID() < 0 || newBean.getID() > cars.size()) {
            return false;
        }
        cars.set(newBean.getID(), newBean);

        return true;
    }

    public static boolean deleteCar(Integer ID) {

        if (ID < 0 || ID > cars.size()) {
            return false;
        }

        return cars.remove(ID);
    }

    public static List<CarBean> getCars(Integer userID) {

        List<CarBean> carBeans = new ArrayList<>();
        for (CarBean car : cars) {
            if (car.getUserID().equals(userID)) {
                carBeans.add(car);
            }
        }
        return carBeans;

    }
}