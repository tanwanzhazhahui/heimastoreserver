package test.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import test.pojo.CategoryImage;
import test.pojo.FloorImage;
import test.pojo.Product;
import test.pojo.SwiperImage;
import test.service.ImageService;
import test.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/image",produces="application/json;charset=UTF-8")
public class ImageController {

    @Autowired@Qualifier("ImageService")
    private ImageService imageService;

    @Autowired@Qualifier("ProductService")
    private ProductService productService;

    @PostMapping(path = "/getSwiperImage")
    private @ResponseBody Map getSwiperImage(){
        Map map=new HashMap();
        for (SwiperImage swiperImage : imageService.findSwiperImage(new HashMap())) {
            map.put(swiperImage.getId(),swiperImage);
        }
        return map;
    }

    @PostMapping(path = "/getCategoryImage")
    private @ResponseBody Map getCategoryImage(){
        Map map=new HashMap();
        for (CategoryImage categoryImage : imageService.findCategoryImage(new HashMap())) {
            map.put(categoryImage.getId(),categoryImage);
        }
        return map;
    }

    @PostMapping(path = "/getFloorImageAndProduct")
    private @ResponseBody Map getFloorImageAndProduct(){
        HashMap map = new HashMap();
        List<FloorImage> floorImageList = imageService.findFloorImage(new HashMap());
        for(int i=0;i<floorImageList.size();i++){
            Map insidemap=new HashMap<String,Object>();
            List plist=new ArrayList();
            PageHelper.startPage(i+1,5).countColumn("1");
            List<Product> productList = productService.findProduct(new HashMap());
            PageInfo<Product> pageInfo=new PageInfo<Product>(productList);
            for (Product product : pageInfo.getList()) {
                plist.add(product);
            }
            insidemap.put("floor_title",floorImageList.get(i));
            insidemap.put("product_list",plist);

            map.put(i+1,insidemap);
        }
        return map;
    }



}
