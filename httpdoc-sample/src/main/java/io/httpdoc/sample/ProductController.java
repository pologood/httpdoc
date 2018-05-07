package io.httpdoc.sample;

import io.httpdoc.core.Document;
import io.httpdoc.core.Generation;
import io.httpdoc.core.Generator;
import io.httpdoc.core.conversion.Converter;
import io.httpdoc.core.conversion.StandardConverter;
import io.httpdoc.core.deserialization.Deserializer;
import io.httpdoc.core.provider.SystemProvider;
import io.httpdoc.jackson.deserialization.YamlDeserializer;
import io.httpdoc.jestful.JestfulClientGenerator;
import org.qfox.jestful.core.http.GET;
import org.qfox.jestful.core.http.HTTP;
import org.springframework.stereotype.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * 产品管理器
 *
 * @author 杨昌沛 646742615@qq.com
 * @date 2018-04-20 12:18
 **/
@HTTP("/products")
@Controller
public class ProductController {

    public static void main(String... args) throws IOException {
        Deserializer deserializer = new YamlDeserializer();
        Converter converter = new StandardConverter();
        Map<String, Object> doc = deserializer.deserialize(new FileInputStream("D:\\用户目录\\下载\\httpdoc (21).yaml"));
        Document document = converter.convert(doc);
        Generation generation = new Generation();
        generation.setDocument(document);
        generation.setPkg("io.httpdoc.gen");
        generation.setDirectory("D:\\用户目录\\下载");
        generation.setProvider(new SystemProvider());
        Generator generator = new JestfulClientGenerator();
        generator.generate(generation);
    }

    @GET("/")
    public ProductListResult list() {
        return null;
    }

}
