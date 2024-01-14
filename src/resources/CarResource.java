package resources;

import beans.APIResponse;
import beans.CarBean;
import beans.UserBean;
import db.DB;

import java.util.List;

public class CarResource implements BaseCRUDResource<CarBean> {
    @Override
    public APIResponse add(CarBean bean) {
        Integer newCar = DB.addCar(bean);
        return newCar == null ? new APIResponse(400, "Car already exists", null) : new APIResponse(200, "Successfully added", newCar);
    }
    @Override
    public APIResponse get(Integer ID) {
        CarBean car = DB.getCar(ID);
        return car == null ? new APIResponse(400, "Car does not exist!", null) : new APIResponse(200, "Successfully", car);
    }

    @Override
    public APIResponse update(CarBean newBean) {
        boolean ok = DB.updateCar(newBean);
        return !ok ? new APIResponse(400, "Car updated failed!", null) : new APIResponse(200, "Car successfully updated", newBean);
    }

    @Override
    public APIResponse delete(Integer ID) {
        boolean ok = DB.deleteCar(ID);
        return !ok ? new APIResponse(400, "Car delete failed!", false) : new APIResponse(200, "Car has been successfully deleted!", true);
    }
    public APIResponse getCars(Integer userID){
        List<CarBean> cars = DB.getCars(userID);
        return cars == null ? new APIResponse(400, "Error", null) :
                cars.isEmpty() ? new APIResponse(200, "Empty List", null) :
                        new APIResponse(200, "Successfully", cars);
    }
}
