package test.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import test.dao.UserMapper;
import test.pojo.User;

import java.util.List;
import java.util.Map;


public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public List<User> findUser(Map map) {
        return getSqlSession().getMapper(UserMapper.class).findUser(map);
    }

    @Override
    public User findUserWithOrderByWx_id(String wx_id) {
        return getSqlSession().getMapper(UserMapper.class).findUserWithOrderByWx_id(wx_id);
    }

    @Override
    public void addUser(User user) {
        getSqlSession().getMapper(UserMapper.class).addUser(user);
    }
}
