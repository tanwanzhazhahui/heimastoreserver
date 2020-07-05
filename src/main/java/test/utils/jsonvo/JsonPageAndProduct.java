package test.utils.jsonvo;

import lombok.Data;

import java.util.List;

@Data
public class JsonPageAndProduct {
    public int total;
    public int pagenum;
    public List productlist;
}
