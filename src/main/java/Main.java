import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;


public class Main {
    private static String enabled;
    private static String fileName;
    private static String format;

    public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException {

        int[] newProducts = new int[]{100, 200, 300, 50};
        String[] newNameOfProducts = new String[]{"Pillow", "Mouse", "T-shirt", "Milk"};
        Basket basket = new Basket(newProducts, newNameOfProducts);
        File csvFile = new File("log.csv");
        File jsonFile = new File("basket.json");
        File xmlFile = new File("shop.xml");
        ClientLog log = new ClientLog();
        basket.addToCart(3, 10);
        basket.addToCart(1, 2);
        basket.printCart();

        GsonBuilder builder1 = new GsonBuilder();
        Gson gson = builder1.create();
        String obj = gson.toJson(basket);
        try (FileWriter file = new FileWriter(jsonFile)) {
            file.write(obj);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("basket.json");
        Basket basket1 = Basket.loadFromTxtFile(file);
        basket1.addToCart(2, 2);
        basket1.printCart();

        ClientLog allLog = new ClientLog();
        allLog.log(4, 5);
        allLog.log(3, 6);
        allLog.log(4, 3);
        allLog.exportAsCSV(new File("log.csv"));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlFile.toURI()));

        read(doc, "load");
        if (enabled.equals("true")) {
            if (jsonFile.exists()) {
                if (format.equals("json")) {
                    basket = Basket.loadFromTxtFile(new File("basket.json"));
                    basket.printCart();
                } else {
                    basket = Basket.loadFromTxtFile(new File("basket.json"));
                    basket.printCart();
                }
            }
        }


        basket.printCart();

        read(doc, "save");
        if (enabled.equals("true")) {
            if (format.equals("json")) {
                basket.saveTxt(new File("basket.json"));
            } else {
                basket.saveTxt(new File("basket.txt"));
            }
            log.exportAsCSV(csvFile);
        }
        read(doc, "log");
        if (enabled.equals("true")) {
            log.exportAsCSV(new File(fileName));
        }
    }


    private static void read(Document node, String name) {
        NodeList nodeList = node.getElementsByTagName(name);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                Element element = (Element) node_;
                enabled = element.getElementsByTagName("enabled").item(0).getTextContent();
                fileName = element.getElementsByTagName("fileName").item(0).getTextContent();
                if (!name.equals("log")) {
                    format = element.getElementsByTagName("format").item(0).getTextContent();
                }
            }
        }
    }
}


