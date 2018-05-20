package io.httpdoc.gen;

import io.httpdoc.core.Document;
import io.httpdoc.core.generation.Generation;
import io.httpdoc.core.generation.Generator;
import io.httpdoc.jackson.deserialization.YamlDeserializer;
import io.httpdoc.jestful.*;

import java.io.IOException;
import java.net.URL;

/**
 * @author 杨昌沛 646742615@qq.com
 * @date 2018-05-16 13:18
 **/
public class Generate {

    public static void main(String... args) throws IOException {
        Document document = Document.from(new URL("http://localhost:8080/httpdoc-sample/httpdoc.yaml"), new YamlDeserializer());
        Generation generation = new Generation(document);
        generation.setPkg("io.httpdoc.gen");
        generation.setDirectory(System.getProperty("user.dir") + "\\httpdoc-sample\\src\\main\\java\\io\\httpdoc\\gen");
//        generation.setProvider(new RetrofitProvider());
//        Generator generator = new RetrofitMergedGenerator()
//                .include(RetrofitCallGenerator.class)
//                .include(RetrofitRxJavaGenerator.class)
//                .include(RetrofitJava8Generator.class)
//                .include(RetrofitGuavaGenerator.class);

        generation.setProvider(new JestfulProvider());
        Generator generator = new JestfulClientMergedGenerator()
                .include(JestfulClientLambdaGenerator.class)
                .include(JestfulClientFutureGenerator.class)
                .include(JestfulClientGuavaGenerator.class)
                .include(JestfulClientJava8Generator.class)
                .include(JestfulClientMessageGenerator.class)
                .include(JestfulClientEntityGenerator.class)
                .include(JestfulClientHeaderGenerator.class)
                .include(JestfulClientObservableGenerator.class);

        generator.generate(generation);
    }

}
