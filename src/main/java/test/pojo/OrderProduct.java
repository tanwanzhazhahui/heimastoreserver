package test.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class OrderProduct implements Serializable {
    public int id;
    public int orderid;
    public int productid;
    public int number;
}
