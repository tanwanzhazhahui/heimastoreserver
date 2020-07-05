package test.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FloorImage implements Serializable {
    private int id;
    private String finame;
    private String url;
}
