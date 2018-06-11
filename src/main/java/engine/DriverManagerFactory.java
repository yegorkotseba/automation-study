package engine;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverTypes type) {

            DriverManager driverManager;

            switch (type) {
                default:
                    driverManager = new ChromeDriverManager();
                    break;
                /*case FIREFOX:
                    driverManager = new FirefoxDriverManager();
                    break;
                default:
                    driverManager = new SafariDriverManager();
                    break;*/
            }
            return driverManager;

        }
}
