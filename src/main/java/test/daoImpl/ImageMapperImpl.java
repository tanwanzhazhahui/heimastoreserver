package test.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import test.dao.ImageMapper;
import test.pojo.CategoryImage;
import test.pojo.FloorImage;
import test.pojo.SwiperImage;

import java.util.List;
import java.util.Map;

public class ImageMapperImpl extends SqlSessionDaoSupport implements ImageMapper {
    @Override
    public List<SwiperImage> findSwiperImage(Map map) {
        return getSqlSession().getMapper(ImageMapper.class).findSwiperImage(map);
    }

    @Override
    public void addSwiperImage(SwiperImage swiperImage) {
        getSqlSession().getMapper(ImageMapper.class).addSwiperImage(swiperImage);
    }

    @Override
    public List<FloorImage> findFloorImage(Map map) {
        return getSqlSession().getMapper(ImageMapper.class).findFloorImage(map);
    }

    @Override
    public void addFloorImage(FloorImage floorImage) {
        getSqlSession().getMapper(ImageMapper.class).addFloorImage(floorImage);
    }

    @Override
    public List<CategoryImage> findCategoryImage(Map map) {
        return getSqlSession().getMapper(ImageMapper.class).findCategoryImage(map);
    }

    @Override
    public void addCategoryImage(CategoryImage categoryImage) {
        getSqlSession().getMapper(ImageMapper.class).addCategoryImage(categoryImage);
    }
}
