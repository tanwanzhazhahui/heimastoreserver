package test.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dao.UserMapper;
import test.pojo.User;
import test.service.UserService;

import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUser(Map map) {
        return userMapper.findUser(map);
    }

    @Override
    public User findUserWithOrderByWx_id(String wx_id) {
        return userMapper.findUserWithOrderByWx_id(wx_id);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
}
