package test.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Data
public class User implements Serializable {
    private int id;
    private String wx_id;
    private List<Order> orders;
}
