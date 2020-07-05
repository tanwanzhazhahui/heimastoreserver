package test.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SwiperImage implements Serializable {
    private int id;
    private String url;
}
