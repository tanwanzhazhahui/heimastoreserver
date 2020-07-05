package test.utils.jsonvo;

import lombok.Data;

@Data
public class JsonOrder {
    public int id;
    public int user_id;
    public String oid;
    public double order_price;
    public String time;
}
