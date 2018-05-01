package securitymanager;

import java.io.*;

class PasswordSM extends SecurityManager {

    private final String password;

    PasswordSM(String password) {
        super();
        this.password = password;
    }

    private boolean accessOK() {
        DataInputStream dis = new DataInputStream(System.in);
        String response;

        System.out.println("What's the secret password?");
        try {
            //noinspection deprecation
            response = dis.readLine();
            return response.equals(password);
        } catch (IOException e) {
            return false;
        }
    }
    public void checkRead(FileDescriptor filedescriptor) {
        if (!accessOK())
            throw new SecurityException("No access!");
    }
    public void checkRead(String filename) {
        if (!accessOK())
            throw new SecurityException("No access!");
    }
    public void checkRead(String filename, Object executionContext) {
        if (!accessOK())
            throw new SecurityException("No access!");
    }
    public void checkWrite(FileDescriptor filedescriptor) {
        if (!accessOK())
            throw new SecurityException("No access!");
    }
    public void checkWrite(String filename) {
        if (!accessOK())
            throw new SecurityException("No access!");
    }
}
