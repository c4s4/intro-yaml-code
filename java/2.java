package jyaml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.ho.yaml.Yaml;

public class Dump {

    public static void main(String[] args)
        throws Exception {
        List<Object> object = new ArrayList<Object>();
        object.add("One");
        object.add(2);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("three", 3.0);
        map.put("four", true);
        object.add(map);
        Yaml.dump(object, new File("test/dump.yml"));
    }

}
