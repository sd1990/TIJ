package org.songdan.tij.reflect.annotation;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 主体
 *
 * @author Songdan
 * @date 2016/6/16
 */
@XmlRootElement(name = "content")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RequestContent<T> {

    @XmlAttribute(name = "class")
    private String className;

    @XmlAttribute(name = "size")
    private int size;

    @XmlElement(name = "REQUEST_FPCX_PTCX")
    private List<T> list = new ArrayList<T>();

    public void setClassName(String className) {
        this.className = className;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
