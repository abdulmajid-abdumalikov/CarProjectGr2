package beans;

public class CarBean extends BaseIDBean {
    String name;
    String color;
    Integer price;
    Boolean isInStore;
    Integer userID;

    public CarBean() {
    }

    public CarBean(String name, String color, Integer price, Integer userID) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.isInStore = false;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getInStore() {
        return isInStore;
    }

    public void setInStore(Boolean inStore) {
        isInStore = inStore;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return STR."CarBean{ID= \{getID()}, name='\{name}\{'\''}, color='\{color}\{'\''}, price=\{price}, isInStore=\{isInStore}, userID=\{userID}\{'}'}";
    }
}
