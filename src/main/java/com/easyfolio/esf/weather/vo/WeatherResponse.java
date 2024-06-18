package com.easyfolio.esf.weather.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "response")
public class WeatherResponse {
    private Header header;
    private Body body;

    @XmlElement(name = "header")
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlElement(name = "body")
    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Header {
        private String resultCode;
        private String resultMsg;

        @XmlElement(name = "resultCode")
        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        @XmlElement(name = "resultMsg")
        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }

    public static class Body {
        private Items items;

        @XmlElement(name = "items")
        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }
    }

    public static class Items {
        private List<Item> itemList;

        @XmlElement(name = "item")
        public List<Item> getItemList() {
            return itemList;
        }

        public void setItemList(List<Item> itemList) {
            this.itemList = itemList;
        }
    }

    public static class Item {
        private String category;
        private String fcstValue;

        @XmlElement(name = "category")
        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        @XmlElement(name = "fcstValue")
        public String getFcstValue() {
            return fcstValue;
        }

        public void setFcstValue(String fcstValue) {
            this.fcstValue = fcstValue;
        }
    }
}
