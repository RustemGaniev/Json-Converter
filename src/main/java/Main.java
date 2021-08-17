import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Main {


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        String xmlFileNAME = "data.xml";

        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);

        try (FileWriter file = new FileWriter("data.json")) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Employee> listEmployees = parseXML(xmlFileNAME);
        String json2 = listToJson(listEmployees);

        try (FileWriter file = new FileWriter("data2.json")) {
            file.write(json2);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static List<Employee> parseCSV(String[] mapping, String file) throws FileNotFoundException {

        List<Employee> stuff = null;
        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(mapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();

            stuff = csv.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stuff);
        return stuff;
    }
    
    public static String listToJson(List<Employee> listStuff) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        String json = gson.toJson(listStuff, listType);
        return json;

    }

    public static List<Employee> parseXML(String xmlFile1) throws ParserConfigurationException, IOException, SAXException {

        List<Employee> stuff2 = null;

        File xmlFile = new File(xmlFile1);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("employee");
          stuff2 = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                stuff2.add(getEmployee(nodeList.item(i)));
            }

            for (Employee empl : stuff2) {
                System.out.println(empl.toString());
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
return  stuff2;
    }

    private static Employee getEmployee(Node node) {
        Long id = null;
        String firstName = null;
        String lastName = null;
        String country = null;
        int age = 0;

        Employee empl2 = new Employee();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            empl2.setIde(Long.parseLong(getTagValue("id", element)));
            empl2.setFirstName(getTagValue("firstName", element));
            empl2.setLastName(getTagValue("lastName", element));
            empl2.setCountry(getTagValue("country", element));
            empl2.setAge(Integer.parseInt(getTagValue("age", element)));
        }

        return empl2;
    }
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
    }






