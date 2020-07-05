package test.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryImage implements Serializable {
    private int id;
    private String ciname;
    private String url;
}
