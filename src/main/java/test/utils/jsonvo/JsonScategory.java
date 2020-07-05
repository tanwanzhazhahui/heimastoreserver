package test.utils.jsonvo;

import lombok.Data;

import java.util.List;

@Data
public class JsonScategory {
    public int scategory_id;
    public String scname;
    public List children;
}
