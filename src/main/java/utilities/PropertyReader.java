package utilities;

import com.google.common.io.Files;
import org.slf4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static consts.PropertiesNames.BROWSER_NAME;
import static org.slf4j.LoggerFactory.getLogger;
import static org.testng.Assert.assertNotNull;


public class PropertyReader {

    private static PropertyReader instance;
    private static final String GLOBAL_CONFIG = "config.properties";

    private static final Logger LOG = getLogger(PropertyReader.class);

    private Map<String, String> properties = new HashMap<>();
    private static Map<String, String> globalProperties = new HashMap<>();

    private PropertyReader(String resource) {
        loadResource(resource);
    }

    private PropertyReader() {
        loadResource(null);
    }

    static {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(PropertyReader.class.getClassLoader()
                .getResourceAsStream(GLOBAL_CONFIG), Charset.forName("UTF-8")))) {
            LOG.info("LOAD GLOBAL CONFIG - " + GLOBAL_CONFIG);
            Properties newProp = new Properties();
            newProp.load(reader);
            for (String name : newProp.stringPropertyNames()) {
                globalProperties.put(name.contains("browser") ? "browserName" : name, newProp.getProperty(name));
            }
            System.out.println(globalProperties);
        } catch (IOException e) {
            LOG.error("Failed to load global properties: " + GLOBAL_CONFIG + "\n" + e);
            LOG.error(e.getMessage());
        }
    }

    public static synchronized PropertyReader getInstance(String resource) {
        if (instance == null) {
            instance = new PropertyReader(resource);
        }
        return instance;
    }

    public static synchronized PropertyReader getInstance() {
        if (instance == null) {
            instance = new PropertyReader();
        }
        return instance;
    }

    private void loadResource(String resource) {
        try (InputStream resourceAsStream = PropertyReader.class.getClassLoader().getResourceAsStream(resource)) {
            load(resourceAsStream);
        } catch (IOException e) {
            LOG.error("Failed to load default properties: " + resource + "\n" + e);
            LOG.error(e.getMessage());
        }
    }

    public void load(InputStream inputStream) throws IOException {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        Properties newProp = new Properties();
        newProp.load(reader);
        for (String name : newProp.stringPropertyNames()) {
            properties.put(name, newProp.getProperty(name));
        }
    }

    public static String getBrowserName() {
        return getGlobalProperty(BROWSER_NAME);
    }

    public static String getGlobalProperty(String property) {
        String prop = globalProperties.get(property);
        String sep = File.separator;
        String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep
                + "config.properties";
        String file = "";
        try {
            file = Files.asCharSource(new File(path), Charset.defaultCharset()).read();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        assertNotNull(property + " is null! " + file, prop);
        return prop;
    }
}
