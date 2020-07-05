package test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import test.pojo.Bcategory;
import test.pojo.Product;
import test.pojo.Scategory;
import test.service.CategoryService;
import test.service.ProductService;
import test.utils.jsonvo.JsonBcategory;
import test.utils.jsonvo.JsonScategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/category",produces="application/json;charset=UTF-8")
public class CategoryController {

    @Autowired@Qualifier("CategoryService")
    private CategoryService categoryService;

    @Autowired@Qualifier("ProductService")
    private ProductService productService;

    @PostMapping(path = "/getCategories")
    private @ResponseBody Map getCategories(){
        Map map=new HashMap();
        List<Bcategory> bcategoryList = categoryService.getBcategoriesWithScategory(new HashMap());
        PageHelper.startPage(1,7).countColumn("1");
        PageInfo<Product> pageInfo=new PageInfo<Product>(productService.findProduct(new HashMap()));

        for (int i=0;i<bcategoryList.size();i++){
            List list1=new ArrayList();
            List list2=new ArrayList();

            for (Product product : pageInfo.getList()) {
                list2.add(product);
            }

            for (Scategory scategory : bcategoryList.get(i).getScategories()) {
                JsonScategory jsonScategory = new JsonScategory();
                jsonScategory.setScategory_id(scategory.getId());
                jsonScategory.setScname(scategory.getSname());
                jsonScategory.setChildren(list2);
                list1.add(jsonScategory);
            }

            JsonBcategory jsonBcategory = new JsonBcategory();
            jsonBcategory.setBcategory_id(bcategoryList.get(i).getId());
            jsonBcategory.setBcname(bcategoryList.get(i).getBname());
            jsonBcategory.setChildren(list1);

            map.put(i,jsonBcategory);
        }
        return map;
    }

}
