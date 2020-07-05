package test.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dao.ImageMapper;
import test.pojo.CategoryImage;
import test.pojo.FloorImage;
import test.pojo.SwiperImage;
import test.service.ImageService;

import java.util.List;
import java.util.Map;

@Service("ImageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<SwiperImage> findSwiperImage(Map map) {
        return imageMapper.findSwiperImage(map);
    }

    @Override
    public void addSwiperImage(SwiperImage swiperImage) {
        imageMapper.addSwiperImage(swiperImage);
    }

    @Override
    public List<FloorImage> findFloorImage(Map map) {
        return imageMapper.findFloorImage(map);
    }

    @Override
    public void addFloorImage(FloorImage floorImage) {
        imageMapper.addFloorImage(floorImage);
    }

    @Override
    public List<CategoryImage> findCategoryImage(Map map) {
        return imageMapper.findCategoryImage(map);
    }

    @Override
    public void addCategoryImage(CategoryImage categoryImage) {
        imageMapper.addCategoryImage(categoryImage);
    }
}
