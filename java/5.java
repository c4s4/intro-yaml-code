package jyaml;

import java.io.File;
import org.ho.yaml.Yaml;

public class Load2 {

    public static void main(String[] args)
        throws Exception {
        System.out.println(Yaml.loadType(new File("test/order.yml"),
                                         Order.class));
    }

}
