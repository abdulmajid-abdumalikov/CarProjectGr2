package db;

import beans.CarBean;
import beans.UserBean;

import java.util.ArrayList;
import java.util.List;

public class DB {

    public static UserBean session = null;
    private static final List<UserBean> users = new ArrayList<>();
    private static final List<CarBean> cars = new ArrayList<>();


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

// Login users
    public static UserBean getLogin(String login, String password) {
        for (UserBean user : users) {
            if (user.getUsername().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static UserBean getUser(Integer id) {
        if (id < 0 || id > users.size()) {
            return null;
        }
        return users.get(id);
    }

    public static boolean updateUser(UserBean newBean) {
        if (newBean.getID() < 0 || newBean.getID() > users.size()) {
            return false;
        }
        users.set(newBean.getID(), newBean);
        return true;
    }

    public static boolean deleteUser(Integer id) {
        if (id < 0 || id >= users.size()) {
            return false;
        }
        return users.removeIf(user -> user.getID().equals(id));
    }

    public static Integer addCar(CarBean bean) {
        bean.setID(cars.size());
        cars.add(bean);
        return bean.getID();
    }

    public static CarBean getCar(Integer id) {
        if (id < 0 || id > cars.size()) {
            return null;
        }
        return cars.get(id);
    }

    public static boolean updateCar(CarBean newBean) {
        if (newBean.getID() < 0 || newBean.getID() > cars.size()) {
            return false;
        }
        cars.set(newBean.getID(), newBean);
        return true;
    }

    public static boolean deleteCar(Integer id) {
        if (id < 0 || id >= cars.size()) {
            return false;
        }
        return cars.removeIf(car -> car.getID().equals(id));
    }
}
