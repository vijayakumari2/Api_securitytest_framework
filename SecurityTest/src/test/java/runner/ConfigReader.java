package runner;

import utils.PropertyManager;
import org.testng.annotations.Test;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigReader {

    @Test
    public void ConfigReader() {
        PropertyManager configReader = new PropertyManager();

        // Retrieve properties
        String applicationUrl = configReader.getProperty("ApplicationUrl");
        String consoleJsonUrl = configReader.getProperty("consoleJsonUrl");
        String consoleDocsUrl = configReader.getProperty("consoleDocsUrl");
        String agentBinaryJsonUrl = configReader.getProperty("agentBinaryJsonUrl");
        String agentBinaryDocsUrl = configReader.getProperty("agentBinaryDocsUrl");
        String keycloakUrl = configReader.getProperty("keycloakUrl");

        // Output file path
        String fileName = System.getProperty("user.dir")+"/src/assets/Zap_urls.txt";


        // Write properties to file
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(applicationUrl + "\n");
            writer.write(consoleJsonUrl + "\n");
            writer.write(consoleDocsUrl + "\n");
            writer.write(agentBinaryJsonUrl + "\n");
            writer.write(agentBinaryDocsUrl + "\n");
            writer.write(keycloakUrl + "\n");
            System.out.println("Zap_urls file successfully written to 'Zap_urls.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
