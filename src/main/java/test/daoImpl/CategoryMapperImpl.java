package test.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import test.dao.CategoryMapper;
import test.pojo.Bcategory;
import test.pojo.Scategory;

import java.util.List;
import java.util.Map;

public class CategoryMapperImpl extends SqlSessionDaoSupport implements CategoryMapper {
    @Override
    public List<Bcategory> getBcategories(Map map) {
        return getSqlSession().getMapper(CategoryMapper.class).getBcategories(map);
    }

    @Override
    public List<Bcategory> getBcategoriesWithScategory(Map map) {
        return getSqlSession().getMapper(CategoryMapper.class).getBcategoriesWithScategory(map);
    }

    @Override
    public void addBcategory(Bcategory bcategory) {
        getSqlSession().getMapper(CategoryMapper.class).addBcategory(bcategory);
    }

    @Override
    public List<Scategory> getScategories() {
        return getSqlSession().getMapper(CategoryMapper.class).getScategories();
    }

    @Override
    public void addScategory(Scategory scategory) {
        getSqlSession().getMapper(CategoryMapper.class).addScategory(scategory);
    }
}
