package test.utils.jsonvo;

import lombok.Data;

import java.util.List;

@Data
public class JsonBcategory {
    public int bcategory_id;
    public String bcname;
    public List children;
}
