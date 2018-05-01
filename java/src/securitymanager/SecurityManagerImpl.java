package securitymanager;

import java.io.*;

public class SecurityManagerImpl {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/java/src/securitymanager/input.txt";
        try {
            System.setSecurityManager(new PasswordSM("password"));
        } catch (SecurityException se) {
            System.err.println("SecurityManager already set!");
        }
        try {
            DataInputStream fis = new DataInputStream(new FileInputStream(path));
            String inputString;
            while ((inputString = fis.readLine()) != null) {
                System.out.println(inputString);
            }
            fis.close();
        } catch (IOException ioe) {
            System.err.println("I/O failed for SecurityManagerTest.");
        }
    }
}

