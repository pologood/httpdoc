package io.httpdoc.core;

import io.httpdoc.core.conversion.Converter;
import io.httpdoc.core.conversion.Format;
import io.httpdoc.core.conversion.StandardConverter;
import io.httpdoc.core.deserialization.Deserializer;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.*;

/**
 * 文档
 *
 * @author 杨昌沛 646742615@qq.com
 * @date 2018-04-12 19:06
 **/
public class Document extends Definition {
    private static final long serialVersionUID = 4240537886514527060L;

    private String httpdoc;
    private String protocol;
    private String hostname;
    private Integer port;
    private String context;
    private String version;
    private String refPrefix = Format.REF_PREFIX;
    private String refSuffix = Format.REF_SUFFIX;
    private String mapPrefix = Format.MAP_PREFIX;
    private String mapSuffix = Format.MAP_SUFFIX;
    private String arrPrefix = Format.ARR_PREFIX;
    private String arrSuffix = Format.ARR_SUFFIX;
    private Set<Controller> controllers = new LinkedHashSet<>();
    private Map<String, Schema> schemas = new LinkedHashMap<>();

    public static Document from(File file, Deserializer deserializer) throws IOException {
        return from(file.toURI(), deserializer);
    }

    public static Document from(URI uri, Deserializer deserializer) throws IOException {
        return from(uri.toURL(), deserializer);
    }

    public static Document from(URL url, Deserializer deserializer) throws IOException {
        try (InputStream in = url.openStream()) {
            return from(in, deserializer);
        }
    }

    public static Document from(InputStream in, Deserializer deserializer) throws IOException {
        try (Reader reader = new InputStreamReader(in)) {
            return from(reader, deserializer);
        }
    }

    public static Document from(Reader reader, Deserializer deserializer) throws IOException {
        return from(reader, deserializer, new StandardConverter());
    }

    public static Document from(File file, Deserializer deserializer, Converter converter) throws IOException {
        return from(file.toURI(), deserializer, converter);
    }

    public static Document from(URI uri, Deserializer deserializer, Converter converter) throws IOException {
        return from(uri.toURL(), deserializer, converter);
    }

    public static Document from(URL url, Deserializer deserializer, Converter converter) throws IOException {
        try (InputStream in = url.openStream()) {
            return from(in, deserializer, converter);
        }
    }

    public static Document from(InputStream in, Deserializer deserializer, Converter converter) throws IOException {
        try (Reader reader = new InputStreamReader(in)) {
            return from(reader, deserializer, converter);
        }
    }

    public static Document from(Reader reader, Deserializer deserializer, Converter converter) throws IOException {
        Map<String, Object> map = deserializer.deserialize(reader);
        return converter.convert(map);
    }

    public String getHttpdoc() {
        return httpdoc;
    }

    public void setHttpdoc(String httpdoc) {
        this.httpdoc = httpdoc;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRefPrefix() {
        return refPrefix;
    }

    public void setRefPrefix(String refPrefix) {
        this.refPrefix = refPrefix;
    }

    public String getRefSuffix() {
        return refSuffix;
    }

    public void setRefSuffix(String refSuffix) {
        this.refSuffix = refSuffix;
    }

    public String getMapPrefix() {
        return mapPrefix;
    }

    public void setMapPrefix(String mapPrefix) {
        this.mapPrefix = mapPrefix;
    }

    public String getMapSuffix() {
        return mapSuffix;
    }

    public void setMapSuffix(String mapSuffix) {
        this.mapSuffix = mapSuffix;
    }

    public String getArrPrefix() {
        return arrPrefix;
    }

    public void setArrPrefix(String arrPrefix) {
        this.arrPrefix = arrPrefix;
    }

    public String getArrSuffix() {
        return arrSuffix;
    }

    public void setArrSuffix(String arrSuffix) {
        this.arrSuffix = arrSuffix;
    }

    public Set<Controller> getControllers() {
        return controllers;
    }

    public void setControllers(Set<Controller> controllers) {
        this.controllers = controllers;
    }

    public Map<String, Schema> getSchemas() {
        return schemas;
    }

    public void setSchemas(Map<String, Schema> schemas) {
        this.schemas = schemas;
    }

    /**
     * 本方法会根据controllers构建Schemas, 注意每次调用本方法都是重新构建
     */
    public void buildSchemas() {
        if (controllers == null || controllers.size() <= 0) {
            return;
        }
        this.schemas = new LinkedHashMap<>();
        for (Controller controller : controllers) {
            for (Operation operation : controller.getOperations()) {
                for (Parameter parameter : operation.getParameters()) {
                    Schema type = parameter.getType();
                    Collection<Schema> dependencies = type.getDependencies();
                    for (Schema schema : dependencies) this.getSchemas().put(schema.getName(), schema);
                }
                Schema type = operation.getResult().getType();
                Collection<Schema> dependencies = type.getDependencies();
                for (Schema schema : dependencies) this.getSchemas().put(schema.getName(), schema);
            }
        }
    }

}
