package ru.ncedu.java.tasks;

import org.xml.sax.SAXException;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Frolov Maksim.
 */

public class SimpleXMLImpl implements SimpleXML {
    private String root;

    @Override
    public String createXML(String tagName, String textNode) {
        String output = null;
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(tagName);
            doc.appendChild(rootElement);
            rootElement.appendChild(doc.createTextNode(textNode));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            output = writer.getBuffer().toString().replaceAll("\n|\r", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public String parseRootElement(InputStream xmlStream) throws SAXException {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean root_flg = false;

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {
                    if (!root_flg) {
                        root = qName;
                    }
                    root_flg = true;
                }
            };

            saxParser.parse(xmlStream, handler);

            return root;
        } catch (SAXException e) {
            throw new SAXException();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}