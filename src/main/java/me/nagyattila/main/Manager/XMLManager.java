
package me.nagyattila.main.Manager;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLManager {
     public void saveToXML(String filePath, char[][] board, String currentPlayer) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element root = doc.createElement("GameState");
            doc.appendChild(root);

            Element boardElement = doc.createElement("Board");
            for (char[] row : board) {
                Element rowElement = doc.createElement("Row");
                rowElement.appendChild(doc.createTextNode(new String(row)));
                boardElement.appendChild(rowElement);
            }
            root.appendChild(boardElement);

            Element playerElement = doc.createElement("CurrentPlayer");
            playerElement.appendChild(doc.createTextNode(currentPlayer));
            root.appendChild(playerElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));

            transformer.transform(source, result);
            System.out.println("Játékállás mentve XML fájlba: " + filePath);
        } catch (Exception e) {
            System.err.println("Hiba az XML mentésekor: " + e.getMessage());
        }
    }

    public Object[] loadFromXML(String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList rowNodes = doc.getElementsByTagName("Row");
            char[][] board = new char[rowNodes.getLength()][];
            for (int i = 0; i < rowNodes.getLength(); i++) {
                board[i] = rowNodes.item(i).getTextContent().toCharArray();
            }

            String currentPlayer = doc.getElementsByTagName("CurrentPlayer").item(0).getTextContent();

            System.out.println("Játékállás betöltve XML fájlból: " + filePath);
            return new Object[]{board, currentPlayer};
        } catch (Exception e) {
            System.err.println("Hiba az XML betöltésekor: " + e.getMessage());
            return null;
        }
    }
}
