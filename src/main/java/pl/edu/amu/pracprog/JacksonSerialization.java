package pl.edu.amu.pracprog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import model.Employee;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JacksonSerialization {

    final static Logger logger = Logger.getLogger(JacksonSerialization.class);

    public static void serializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        //Set mapper to pretty-print
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //Create objects to serialize
        ModelObjectsCreator objectsCreator = new ModelObjectsCreator();
        Employee employee = objectsCreator.getEmp();


        ArrayList<Employee> workers = (ArrayList<Employee>) objectsCreator.getEmployees();

        mapper.writeValue(new File("workers." + fileSuffix), workers);


        //Serialize to file and string
        mapper.writeValue(new File("result." + fileSuffix), employee);
        String jsonString = mapper.writeValueAsString(employee);

        logger.info("Printing serialized original object " + fileSuffix);
        System.out.println(jsonString);

        //Deserialize from file
        Employee deserializedEmployee = mapper.readValue(
                new File("result." + fileSuffix), Employee.class);

        //Give a rise
        deserializedEmployee.setSalary(deserializedEmployee.getSalary() * 2);

        //Serialize back
        mapper.writeValue(new File("result-modified." + fileSuffix), deserializedEmployee);
        String modifiedJsonString = mapper.writeValueAsString(deserializedEmployee);
        logger.info("Printing serialized modified object " + fileSuffix);
        System.out.println(modifiedJsonString);
    }

    public static void deserializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        //Deserialized employee object from employees.* file in resources
//        InputStream employeeIs = JacksonSerialization.class.getClassLoader().
//                getResourceAsStream("result." + fileSuffix);

        System.out.println("Deserialization of Workers");
        ArrayList<Employee> workers = mapper.readValue(new File("workers." + fileSuffix), ArrayList.class);
        String desWorkers = mapper.writeValueAsString(workers);
        System.out.println(desWorkers);

        System.out.println();

        //Read value - set class type of serialization
        Employee deserializedEmployee = mapper.readValue(new File("result." + fileSuffix), Employee.class);

        //Give eployee big salary
        deserializedEmployee.setSalary(100000);

        String modifiedSerialzied = mapper.writeValueAsString(deserializedEmployee);
        logger.info("Printing modified re-serialized employee to" + fileSuffix);

        System.out.println(modifiedSerialzied);
    }

    public static void main(String[] args) throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JodaModule());
        //serializeDemo(jsonMapper, "json");
        deserializeDemo(jsonMapper, "json");

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JodaModule());
        //serializeDemo(xmlMapper, "xml");
        //deserializeDemo(xmlMapper, "xml ");


    }
}


