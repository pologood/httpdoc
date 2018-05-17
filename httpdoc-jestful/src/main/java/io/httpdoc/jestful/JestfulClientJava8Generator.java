package io.httpdoc.jestful;

import io.httpdoc.core.Operation;
import io.httpdoc.core.Parameter;
import io.httpdoc.core.Result;
import io.httpdoc.core.exception.HttpdocRuntimeException;
import io.httpdoc.core.fragment.ClassFragment;
import io.httpdoc.core.fragment.MethodFragment;
import io.httpdoc.core.provider.Provider;
import io.httpdoc.core.type.HDParameterizedType;
import io.httpdoc.core.type.HDType;

import java.util.List;

/**
 * Retrofit Completable Future 生成器
 *
 * @author 杨昌沛 646742615@qq.com
 * @date 2018-05-17 17:40
 **/
public class JestfulClientJava8Generator extends JestfulClientAbstractGenerator {

    public JestfulClientJava8Generator() {
        this("", "ForJava8");
    }

    public JestfulClientJava8Generator(String prefix, String suffix) {
        super(prefix, suffix);
    }

    @Override
    protected void generate(String pkg, Provider provider, ClassFragment interfase, Operation operation) {
        Class<?> clazz;
        try {
            clazz = Class.forName("java.util.concurrent.CompletableFuture");
        } catch (ClassNotFoundException e) {
            throw new HttpdocRuntimeException("this generator can only used in jdk 8 or greater versions");
        }
        MethodFragment method = new MethodFragment(0);
        annotate(operation, method);
        Result result = operation.getResult();
        HDType type = result != null && result.getType() != null ? result.getType().toType(pkg, provider) : null;
        method.setType(new HDParameterizedType(HDType.valueOf(clazz), null, type != null ? type : HDType.valueOf(Void.class)));
        method.setName(name(operation.getName()));
        List<Parameter> parameters = operation.getParameters();
        if (parameters != null) generate(pkg, provider, method, parameters);

        describe(operation, method, parameters);

        interfase.getMethodFragments().add(method);
    }

}
