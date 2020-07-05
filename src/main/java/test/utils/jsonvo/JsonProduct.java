package test.utils.jsonvo;

import lombok.Data;

import java.util.List;

@Data
public class JsonProduct {
    public int productimage_id;
    public String productname;
    public String url;
    public double price;
    public String detail;
    public List pics;
}
