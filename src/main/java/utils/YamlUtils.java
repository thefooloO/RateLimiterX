package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class YamlUtils {

    public static <T> T parse(InputStream in, Class<T> clazz) {
        return new Yaml().loadAs(in, clazz);
    }
}