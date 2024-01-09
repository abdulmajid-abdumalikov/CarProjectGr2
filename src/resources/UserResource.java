package resources;

import beans.APIResponse;
import beans.UserBean;
import db.DB;

public class UserResource implements BaseCRUDResource<UserBean> {
    @Override
    public APIResponse add(UserBean bean) {
        UserBean newUser = DB.addUser(bean);
        return newUser == null ? new APIResponse(400, "User already exists", null) : new APIResponse(200, "Successfully added", newUser);
    }

    @Override
    public APIResponse get(Integer ID) {
        UserBean user = DB.getUser(ID);
        return user == null ? new APIResponse(400, "User does not exist!", null) : new APIResponse(200, "Successfully", user);
    }

    @Override
    public APIResponse update(UserBean newBean) {
        boolean ok = DB.updateUser(newBean);
        if (!ok) {
            System.out.println("User update failed!");
        } else {
            System.out.println(newBean);
        }
        return !ok ? new APIResponse(400, "User updated failed!", null) : new APIResponse(200, "User successfully updated", newBean);
    }

    @Override
    public APIResponse delete(Integer ID) {
        boolean ok = DB.deleteUser(ID);
        return !ok ? new APIResponse(400, "User delete failed!", false) : new APIResponse(200, "User has been successfully deleted!", true);
    }
}
