package io.httpdoc.jestful.server;

import io.httpdoc.core.Document;
import io.httpdoc.core.conversion.Converter;
import io.httpdoc.core.conversion.DefaultFormat;
import io.httpdoc.core.conversion.Format;
import io.httpdoc.core.conversion.StandardConverter;
import io.httpdoc.core.exception.DocumentTranslationException;
import io.httpdoc.core.interpretation.DefaultInterpreter;
import io.httpdoc.core.interpretation.Interpreter;
import io.httpdoc.core.kit.LoadKit;
import io.httpdoc.core.serialization.Serializer;
import io.httpdoc.core.supplier.Supplier;
import io.httpdoc.core.supplier.SystemSupplier;
import io.httpdoc.core.translation.Container;
import io.httpdoc.core.translation.Translation;
import io.httpdoc.core.translation.Translator;
import org.qfox.jestful.core.http.GET;
import org.qfox.jestful.core.http.HTTP;
import org.qfox.jestful.core.http.Path;
import org.qfox.jestful.core.http.Query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Jestful Server 服务
 *
 * @author 杨昌沛 646742615@qq.com
 * @date 2018-04-24 16:23
 **/
@HTTP("/jestful")
public class JestfulHttpdocController {
    private String httpdoc;
    private String protocol;
    private String hostname;
    private Integer port;
    private String context;
    private String version;
    private Translator translator = new JestfulTranslator();
    private Supplier supplier = new SystemSupplier();
    private Interpreter interpreter = new DefaultInterpreter();
    private Converter converter = new StandardConverter();
    private Map<String, Serializer> serializers = LoadKit.load(this.getClass().getClassLoader(), Serializer.class);
    private Format format = new DefaultFormat();

    @GET("/httpdoc")
    public void render(
            @Query("charset") String charset,
            @Query("contentType") String contentType,
            HttpServletRequest req,
            HttpServletResponse res
    ) throws IOException, DocumentTranslationException {
        if (serializers == null) {
            res.sendError(404);
            return;
        }

        Container container = new JestfulHttpdocContainer(req.getServletContext());
        Translation translation = new Translation(container, supplier, interpreter);

        translation.setHttpdoc(httpdoc != null ? httpdoc : Module.getInstance().getVersion());
        translation.setProtocol(protocol != null ? protocol : req.getProtocol().split("/")[0].toLowerCase());
        translation.setHostname(hostname != null ? hostname : req.getServerName());
        translation.setPort(port != null ? port : req.getServerPort());
        translation.setContext(context != null ? context : req.getContextPath());
        translation.setVersion(version);

        Document document = translator.translate(translation);

        Serializer serializer = serializers.values().iterator().next();
        charset = charset != null && charset.trim().length() > 0 ? charset : "UTF-8";
        contentType = contentType != null && charset.trim().length() > 0 ? contentType : serializer.getType();
        res.setCharacterEncoding(charset);
        res.setContentType(contentType + "; charset=" + charset);
        Map<String, Object> doc = converter.convert(document, format);
        serializer.serialize(doc, res.getOutputStream());
    }

    @GET("/httpdoc.{suffix:.*}")
    public void render(
            @Path("suffix") String suffix,
            @Query("charset") String charset,
            @Query("contentType") String contentType,
            HttpServletRequest req,
            HttpServletResponse res
    ) throws IOException, DocumentTranslationException {
        Serializer serializer = serializers.get(suffix);
        if (serializer == null) {
            res.sendError(404);
            return;
        }

        Container container = new JestfulHttpdocContainer(req.getServletContext());
        Translation translation = new Translation(container, supplier, interpreter);

        translation.setHttpdoc(httpdoc != null ? httpdoc : Module.getInstance().getVersion());
        translation.setProtocol(protocol != null ? protocol : req.getProtocol().split("/")[0].toLowerCase());
        translation.setHostname(hostname != null ? hostname : req.getServerName());
        translation.setPort(port != null ? port : req.getServerPort());
        translation.setContext(context != null ? context : req.getContextPath());
        translation.setVersion(version);

        Document document = translator.translate(translation);

        charset = charset != null && charset.trim().length() > 0 ? charset : "UTF-8";
        contentType = contentType != null && charset.trim().length() > 0 ? contentType : serializer.getType();
        res.setCharacterEncoding(charset);
        res.setContentType(contentType + "; charset=" + charset);
        Map<String, Object> doc = converter.convert(document, format);
        serializer.serialize(doc, res.getOutputStream());
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

    public Translator getTranslator() {
        return translator;
    }

    public void setTranslator(Translator translator) {
        this.translator = translator;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Interpreter getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public Map<String, Serializer> getSerializers() {
        return serializers;
    }

    public void setSerializers(Map<String, Serializer> serializers) {
        this.serializers = serializers;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }
}
