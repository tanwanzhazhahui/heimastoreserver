package test.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Data
public class Product implements Serializable {
    private int id;
    private String productname;
    private String url;
    private double price;
    private String detail;
    private List<Order> orders;
    private int number;
}
