package test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import test.pojo.Product;
import test.pojo.SwiperImage;
import test.service.ImageService;
import test.service.ProductService;
import test.utils.jsonvo.JsonFuzzyProduct;
import test.utils.jsonvo.JsonPageAndProduct;
import test.utils.jsonvo.JsonProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/product",produces="application/json;charset=UTF-8")
public class ProductController {

    @Autowired@Qualifier("ProductService")
    private ProductService productService;

    @Autowired@Qualifier("ImageService")
    private ImageService imageService;

    @PostMapping(path = "/geProductByPageNum")
    private @ResponseBody Map geProductByPageNum(int pagenum){
        HashMap map = new HashMap();

        PageHelper.startPage(pagenum,10).countColumn("1");
        List<Product> productList = productService.findProduct(new HashMap());
        PageInfo<Product> pageInfo=new PageInfo<Product>(productList);

        JsonPageAndProduct jsonPageAndProduct = new JsonPageAndProduct();
        jsonPageAndProduct.setTotal(productService.findProduct(new HashMap()).size());
        jsonPageAndProduct.setPagenum(pagenum);
        jsonPageAndProduct.setProductlist(pageInfo.getList());

        map.put("message",jsonPageAndProduct);
        return map;
    }

    @PostMapping(path = "/getProductById")
    private @ResponseBody Map getProductById(int productimage_id){
        HashMap map = new HashMap();

        Map m=new HashMap();
        m.put("id",productimage_id);
        Product product = productService.findProduct(m).get(0);
        List<SwiperImage> pics = imageService.findSwiperImage(new HashMap());

        JsonProduct jsonProduct = new JsonProduct();
        jsonProduct.setProductimage_id(product.getId());
        jsonProduct.setProductname(product.getProductname());
        jsonProduct.setUrl(product.getUrl());
        jsonProduct.setPrice(product.getPrice());
        jsonProduct.setDetail(product.getDetail());
        jsonProduct.setPics(pics);

        map.put("message",jsonProduct);
        return map;
    }

    @PostMapping(path = "/getProductFuzzly")
    private @ResponseBody Map getProductFuzzly(String productname){
        HashMap map = new HashMap();

        List<JsonFuzzyProduct> jsonFuzzyProductList=new ArrayList<JsonFuzzyProduct>();
        for (Product product : productService.findProductLike(productname)) {
            JsonFuzzyProduct jsonFuzzyProduct = new JsonFuzzyProduct();
            jsonFuzzyProduct.setProductimage_id(product.getId());
            jsonFuzzyProduct.setProductname(product.getProductname());
            jsonFuzzyProductList.add(jsonFuzzyProduct);
        }

        map.put("result",jsonFuzzyProductList);
        return map;
    }
}
