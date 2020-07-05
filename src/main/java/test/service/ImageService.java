package test.service;

import test.pojo.CategoryImage;
import test.pojo.FloorImage;
import test.pojo.SwiperImage;

import java.util.List;
import java.util.Map;

public interface ImageService {
    List<SwiperImage> findSwiperImage(Map map);
    void addSwiperImage(SwiperImage swiperImage);

    List<FloorImage> findFloorImage(Map map);
    void addFloorImage(FloorImage floorImage);

    List<CategoryImage> findCategoryImage(Map map);
    void addCategoryImage(CategoryImage categoryImage);
}
