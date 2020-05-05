package jyaml;

import java.io.File;
import org.ho.yaml.Yaml;

public class Load {

    public static void main(String[] args)
        throws Exception {
        String filename = "test/object.yml";
        if (args.length > 0) filename = args[0];
        System.out.println(Yaml.load(new File(filename)));
    }

}
