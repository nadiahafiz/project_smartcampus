package com.smartcampus.library;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;

@Endpoint
public class LibrarySoapEndpoint {

    private static final String NAMESPACE_URI = "http://com.smartcampus/library/soap";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRoomStatusRequest")
    @ResponsePayload
    public Element getRoomStatus(@RequestPayload Element request) throws Exception {
        
        // Ambil data roomNumber daripada input XML
        String roomNumber = request.getElementsByTagNameNS("*", "roomNumber").item(0).getTextContent();
        
        // Cipta XML Respons secara manual (Simulasi Sistem Legacy)
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element response = document.createElementNS(NAMESPACE_URI, "tns:getRoomStatusResponse");
        
        Element roomNumNode = document.createElementNS(NAMESPACE_URI, "tns:roomNumber");
        roomNumNode.setTextContent(roomNumber);
        
        Element statusNode = document.createElementNS(NAMESPACE_URI, "tns:status");
        statusNode.setTextContent("AVAILABLE"); // Status simulasi asal
        
        Element messageNode = document.createElementNS(NAMESPACE_URI, "tns:message");
        messageNode.setTextContent("Bilik " + roomNumber + " sedia untuk digunakan melalui Legacy Integration.");

        response.appendChild(roomNumNode);
        response.appendChild(statusNode);
        response.appendChild(messageNode);

        return response;
    }
}