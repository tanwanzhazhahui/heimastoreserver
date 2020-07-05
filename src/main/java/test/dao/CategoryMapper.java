package test.dao;

import test.pojo.Bcategory;
import test.pojo.Scategory;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {
    List<Bcategory> getBcategories(Map map);
    List<Bcategory> getBcategoriesWithScategory(Map map);
    void addBcategory(Bcategory bcategory);

    List<Scategory> getScategories();
    void addScategory(Scategory scategory);
}
