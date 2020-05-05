package jyaml;

import java.io.File;
import org.ho.yaml.Yaml;

public class Alias {

    public static void main(String[] args)
        throws Exception {
        Person[] persons = Yaml.loadType(new File("test/alias.yml"),
                                         Person[].class);
        for(int i=0; i<persons.length; i++) {
            Person person = persons[i];
            System.out.println(person);
        }
        // we test that references are the same
        System.out.println("Anchor OK: "+(persons[2].getParents()[0]==persons[0]));
    }

}
