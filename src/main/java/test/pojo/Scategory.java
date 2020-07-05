package test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scategory implements Serializable {
    private int id;
    private String sname;
    private int bid;

    public Scategory(int id, String sname) {
        this.id = id;
        this.sname = sname;
    }
}
