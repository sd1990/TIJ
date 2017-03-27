package org.songdan.tij.reflect.annotation;

import javax.xml.bind.annotation.*;

/**
 * @author Songdan
 * @date 2016/6/16
 */
@XmlRootElement(name = "REQUEST_FPCX_PTCX_PARAM")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RequestParam<T> {

    /**
     * 根标签中的class属性
     */
    @XmlAttribute(name = "class")
    private String className;

    @XmlElement(name = "REQUEST_FPCX_PTCXS")
    private RequestContent<T> requestContent;

    public void setClassName(String className) {
        this.className = className;
    }

    public void setRequestContent(RequestContent<T> requestContent) {
        this.requestContent = requestContent;
    }
}
