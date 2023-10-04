package ru.job4j.serialization.jason;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Info {
    @XmlAttribute
    private int number;
    @XmlAttribute
    private String phone;

    public Info() {

    }

    public Info(int number, String phone) {
        this.number = number;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{"
                + "number=" + number
                + ", phone='" + phone + '\''
                + '}';
    }
}
