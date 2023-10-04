package ru.job4j.serialization.jason;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class POJO {
    public static void main(String[] args) throws Exception {
        Client client = new Client("Ivan", "Ivanov",
                false, 35, new Info(123, "+7 923-213-12-32"),
                new String[]{"Petr", "Vera"});
        JAXBContext context = JAXBContext.newInstance(Client.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(client, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Client rsl = (Client) unmarshaller.unmarshal(reader);
            System.out.println(rsl);

        }
    }
}
