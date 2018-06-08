package engine;

public class DriverManagerFactory {

    public static DriverManager1 getManager(DriverTypes type) {

            DriverManager1 driverManager1;

            switch (type) {
                default:
                    driverManager1 = new ChromeDriverManager1();
                    break;
                /*case FIREFOX:
                    driverManager1 = new FirefoxDriverManager();
                    break;
                default:
                    driverManager1 = new SafariDriverManager();
                    break;*/
            }
            return driverManager1;

        }
}
