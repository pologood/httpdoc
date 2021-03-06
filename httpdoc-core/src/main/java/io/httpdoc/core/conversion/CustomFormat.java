package io.httpdoc.core.conversion;

/**
 * 自定义的转换格式
 *
 * @author 杨昌沛 646742615@qq.com
 * @date 2018-04-19 11:03
 **/
public class CustomFormat implements Format {
    private static final long serialVersionUID = 6084957981476869704L;

    private String refPrefix = Format.REF_PREFIX;
    private String refSuffix = Format.REF_SUFFIX;
    private String mapPrefix = Format.MAP_PREFIX;
    private String mapSuffix = Format.MAP_SUFFIX;
    private String arrPrefix = Format.ARR_PREFIX;
    private String arrSuffix = Format.ARR_SUFFIX;
    private boolean pkgIncluded = true;
    private boolean canonical = true;

    @Override
    public String getRefPrefix() {
        return refPrefix;
    }

    public void setRefPrefix(String refPrefix) {
        this.refPrefix = refPrefix;
    }

    @Override
    public String getRefSuffix() {
        return refSuffix;
    }

    public void setRefSuffix(String refSuffix) {
        this.refSuffix = refSuffix;
    }

    @Override
    public String getMapPrefix() {
        return mapPrefix;
    }

    public void setMapPrefix(String mapPrefix) {
        this.mapPrefix = mapPrefix;
    }

    @Override
    public String getMapSuffix() {
        return mapSuffix;
    }

    public void setMapSuffix(String mapSuffix) {
        this.mapSuffix = mapSuffix;
    }

    @Override
    public String getArrPrefix() {
        return arrPrefix;
    }

    public void setArrPrefix(String arrPrefix) {
        this.arrPrefix = arrPrefix;
    }

    @Override
    public String getArrSuffix() {
        return arrSuffix;
    }

    public void setArrSuffix(String arrSuffix) {
        this.arrSuffix = arrSuffix;
    }

    @Override
    public boolean isPkgIncluded() {
        return pkgIncluded;
    }

    public void setPkgIncluded(boolean pkgIncluded) {
        this.pkgIncluded = pkgIncluded;
    }

    @Override
    public boolean isCanonical() {
        return canonical;
    }

    public void setCanonical(boolean canonical) {
        this.canonical = canonical;
    }
}
