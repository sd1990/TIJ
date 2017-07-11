package org.songdan.tij.jaxb.demo;

import org.songdan.tij.jaxb.JaxbUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Songdan
 * @date 2016/12/8 11:53
 */
@XmlRootElement(name = "DEMO")
public class DemoModel {

    private String firstProp;

    private String secondProp;

    public String getFirstProp() {
        return firstProp;
    }

    @XmlElement(name = "FIRST")
    public void setFirstProp(String firstProp) {
        this.firstProp = firstProp;
    }

    public String getSecondProp() {
        return secondProp;
    }

    @XmlElement(name = "SECOND")
    public void setSecondProp(String secondProp) {
        this.secondProp = secondProp;
    }

    public static void main(String[] args) {
        DemoModel demo = new DemoModel();
        demo.setFirstProp("China");
        demo.setSecondProp("Beijing");
        String xml = JaxbUtil.convertToXml(demo);
        System.out.println(xml);
        String converXML = "<DEMO><SECOND>Beijing</SECOND><THIRD>Beijing</THIRD></DEMO>";
//        String converXML = "<DEMO><FIRST>China</FIRST><SECOND>Beijing</SECOND></DEMO>";
        DemoModel demoModel = JaxbUtil.convertToJavaBean(converXML, DemoModel.class);
        System.out.println(demoModel.getFirstProp());
        System.out.println(demoModel.getSecondProp());
    }
}
