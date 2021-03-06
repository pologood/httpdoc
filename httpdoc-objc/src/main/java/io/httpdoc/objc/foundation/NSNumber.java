package io.httpdoc.objc.foundation;

import io.httpdoc.objc.ObjC;

/**
 * NSNumber
 *
 * @author 杨昌沛 646742615@qq.com
 * @date 2018-07-25 16:10
 **/
public class NSNumber implements ObjC {

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Kind getKind() {
        return Kind.CLASS;
    }

    @Override
    public Reference getReference() {
        return Reference.STRONG;
    }

    @Override
    public String getLocation() {
        return FOUNDATION;
    }

    @Override
    public boolean isExternal() {
        return true;
    }
}
