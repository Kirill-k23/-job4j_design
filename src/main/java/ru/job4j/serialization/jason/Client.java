package ru.job4j.serialization.jason;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String surname;
    @XmlAttribute
    private boolean sex;
    @XmlAttribute
    private int age;
    private Info info;
    @XmlAttribute
    private String[] children;

    public Client() {

    }

    public Client(String name, String surname, boolean sex,
                  int age, Info info, String[] children) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        this.info = info;
        this.children = children;
    }

    @Override
    public String toString() {
        return "Client{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", sex=" + sex
                + ", age=" + age
                + ", info=" + info
                + ", children=" + Arrays.toString(children)
                + '}';
    }
}
