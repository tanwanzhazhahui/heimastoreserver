package test.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dao.CategoryMapper;
import test.pojo.Bcategory;
import test.pojo.Scategory;
import test.service.CategoryService;

import java.util.List;
import java.util.Map;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Bcategory> getBcategories(Map map) {
        return categoryMapper.getBcategories(map);
    }

    @Override
    public List<Bcategory> getBcategoriesWithScategory(Map map) {
        return categoryMapper.getBcategoriesWithScategory(map);
    }

    @Override
    public void addBcategory(Bcategory bcategory) {
        categoryMapper.addBcategory(bcategory);
    }

    @Override
    public List<Scategory> getScategories() {
        return categoryMapper.getScategories();
    }

    @Override
    public void addScategory(Scategory scategory) {
        categoryMapper.addScategory(scategory);
    }
}
