package com.easyfolio.esf.weather.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherResponse {
    @XmlElement(name = "header")
    private Header header;

    @XmlElement(name = "body")
    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @XmlRootElement(name = "header")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Header {
        @XmlElement(name = "resultCode")
        private String resultCode;

        @XmlElement(name = "resultMsg")
        private String resultMsg;

        // Getter, Setter 추가
    }

    @XmlRootElement(name = "body")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body {
        @XmlElement(name = "items")
        private Items items;

        // Getter, Setter 추가
    }

    @XmlRootElement(name = "items")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Items {
        @XmlElement(name = "item")
        private Item item;

        // Getter, Setter 추가
    }

    @XmlRootElement(name = "item")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Item {
        @XmlElement(name = "baseDate")
        private String baseDate;

        @XmlElement(name = "baseTime")
        private String baseTime;

        @XmlElement(name = "category")
        private String category;

        @XmlElement(name = "fcstDate")
        private String fcstDate;

        @XmlElement(name = "fcstTime")
        private String fcstTime;

        @XmlElement(name = "fcstValue")
        private String fcstValue;

        @XmlElement(name = "nx")
        private int nx;

        @XmlElement(name = "ny")
        private int ny;

        // Getter, Setter 추가
    }
}
