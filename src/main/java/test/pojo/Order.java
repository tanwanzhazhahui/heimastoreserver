package test.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Data
public class Order implements Serializable {
    private int id;
    private String oid;
    private String address;
    private double price;
    private String status;
    private String time;
    private List<Product> products;
    private int userid;
}
