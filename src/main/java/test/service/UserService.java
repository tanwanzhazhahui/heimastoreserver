package test.service;

import test.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findUser(Map map);
    User findUserWithOrderByWx_id(String wx_id);
    void addUser(User user);
}
