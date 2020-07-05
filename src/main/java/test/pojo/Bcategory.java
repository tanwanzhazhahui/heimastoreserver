package test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bcategory implements Serializable {
    public int id;
    public String bname;
    private List<Scategory> scategories;

    public Bcategory(int id, String bname) {
        this.id = id;
        this.bname = bname;
    }
}
