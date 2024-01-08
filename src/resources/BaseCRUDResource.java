package resources;

import beans.APIResponse;
import beans.BaseIDBean;

public interface BaseCRUDResource<T extends BaseIDBean> {

    APIResponse add(T bean);

    APIResponse get(Integer ID);

    APIResponse update(T newBean);

    APIResponse delete(Integer ID);
}
