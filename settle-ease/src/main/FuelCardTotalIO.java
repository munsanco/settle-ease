package main;
import java.io.*;

public class FuelCardTotalIO {
    public static void writeFuelReportToFile(FuelReport fuelReport, String filePath) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(fuelReport);
        }
    }

    public static FuelReport readFuelReportFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (FuelReport) objectInputStream.readObject();
        }
    }

    public static void writeBytesToFile(byte[] bytes, String filePath) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(bytes);
        }
    }

    public static byte[] readBytesFromFile(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteOutputStream.write(buffer, 0, bytesRead);
            }
            return byteOutputStream.toByteArray();
        }
    }

    public static void writePrimitiveTypesAndStringsToFile(String filePath, int intValue, double doubleValue, boolean booleanValue, String stringValue) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)) {
            dataOutputStream.writeInt(intValue);
            dataOutputStream.writeDouble(doubleValue);
            dataOutputStream.writeBoolean(booleanValue);
            dataOutputStream.writeUTF(stringValue);
        }
    }

    public static void readPrimitiveTypesAndStringsFromFile(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {
            int intValue = dataInputStream.readInt();
            double doubleValue = dataInputStream.readDouble();
            boolean booleanValue = dataInputStream.readBoolean();
            String stringValue = dataInputStream.readUTF();

            System.out.println("Int value: " + intValue);
            System.out.println("Double value: " + doubleValue);
            System.out.println("Boolean value: " + booleanValue);
            System.out.println("String value: " + stringValue);
        }
    }
}

