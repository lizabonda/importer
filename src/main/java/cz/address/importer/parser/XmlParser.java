package cz.address.importer.parser;

import cz.address.importer.dto.MunicipalityData;
import cz.address.importer.dto.MunicipalityPartData;
import cz.address.importer.dto.ParsedData;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlParser {

    public ParsedData parse() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File("downloads/kopidlno.xml"));

        Element root = doc.getDocumentElement();
        NodeList municipalities = root.getElementsByTagName("vf:Obec");

        Element municipality = (Element) municipalities.item(0);

        Long municipalityCode = Long.valueOf(municipality
                .getElementsByTagName("obi:Kod")
                .item(0)
                .getTextContent());

        String municipalityName = municipality
                .getElementsByTagName("obi:Nazev")
                .item(0)
                .getTextContent();

        MunicipalityData municipalityData = new MunicipalityData(municipalityCode, municipalityName);

        NodeList municipalityParts =
                root.getElementsByTagName("vf:CastObce");

        List<MunicipalityPartData> municipalityPartsData = new ArrayList<>();
        for (int i = 0; i < municipalityParts.getLength(); i++) {

            Element municipalityPart = (Element) municipalityParts.item(i);

            Long municipalityPartCode = Long.valueOf(municipalityPart
                    .getElementsByTagName("coi:Kod")
                    .item(0)
                    .getTextContent());

            String municipalityPartName = municipalityPart
                    .getElementsByTagName("coi:Nazev")
                    .item(0)
                    .getTextContent();

            Long parentMunicipalityCode = Long.valueOf(municipalityPart
                    .getElementsByTagName("obi:Kod")
                    .item(0)
                    .getTextContent());

            MunicipalityPartData municipalityPartData = new MunicipalityPartData(municipalityPartCode, municipalityPartName, parentMunicipalityCode);
            municipalityPartsData.add(municipalityPartData);

        }
        return new ParsedData(municipalityData, municipalityPartsData);
    }
}
