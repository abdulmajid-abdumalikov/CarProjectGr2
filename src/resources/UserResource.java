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
        return !ok ? new APIResponse(400, "User updated failed!", null) : new APIResponse(200, "User successfully updated", newBean);
    }

    @Override
    public APIResponse delete(Integer ID) {
        boolean ok = DB.deleteUser(ID);
        return !ok ? new APIResponse(400, "User delete failed!", false) : new APIResponse(200, "User has been successfully deleted!", true);
    }
    public APIResponse login(UserBean user) {
        UserBean newUser = DB.getUser(user.getUsername(), user.getPassword());

        return newUser == null ? new APIResponse(400, "User not found!", null) :
                new APIResponse(200, "Welcome user!", newUser);
    }
}
