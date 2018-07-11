package io.httpdoc.objective.c.core;

import io.httpdoc.core.Operation;
import io.httpdoc.core.Parameter;
import io.httpdoc.core.Result;

import java.util.ArrayList;
import java.util.List;

public class ObjCOperation extends Operation {
    private final Operation operation;

    public ObjCOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public String getName() {
        return operation.getName();
    }

    @Override
    public void setName(String name) {
        operation.setName(name);
    }

    @Override
    public String getMethod() {
        return operation.getMethod();
    }

    @Override
    public void setMethod(String method) {
        operation.setMethod(method);
    }

    @Override
    public String getPath() {
        return operation.getPath();
    }

    @Override
    public void setPath(String path) {
        operation.setPath(path);
    }

    @Override
    public List<String> getProduces() {
        return operation.getProduces();
    }

    @Override
    public void setProduces(List<String> produces) {
        operation.setProduces(produces);
    }

    @Override
    public List<String> getConsumes() {
        return operation.getConsumes();
    }

    @Override
    public void setConsumes(List<String> consumes) {
        operation.setConsumes(consumes);
    }

    @Override
    public List<Parameter> getParameters() {
        List<Parameter> parameters = operation.getParameters();
        if (parameters == null) return null;
        List<Parameter> list = new ArrayList<>();
        for (Parameter parameter : parameters) list.add(new ObjCParameter(parameter));
        return list;
    }

    @Override
    public void setParameters(List<Parameter> parameters) {
        operation.setParameters(parameters);
    }

    @Override
    public Result getResult() {
        Result result = operation.getResult();
        if (result == null) return null;
        return new ObjCResult(result);
    }

    @Override
    public void setResult(Result result) {
        operation.setResult(result);
    }

    @Override
    public String getDescription() {
        return operation.getDescription();
    }

    @Override
    public void setDescription(String description) {
        operation.setDescription(description);
    }
}