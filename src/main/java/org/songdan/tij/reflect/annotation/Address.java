package org.songdan.tij.reflect.annotation;

/**
 * @author Songdan
 * @date 2016/6/16
 */
public class Address {

    private String province;

    private String city;

    private String detail;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
