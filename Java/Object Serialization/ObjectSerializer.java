import java.io.*;

public class ObjectSerializer {

    private static FileOutputStream fos;
    private static ObjectOutputStream oos;
    private static FileInputStream fis;
    private static ObjectInputStream ois;

    public static void serializeToFile( String filename, Object obj ) {
        try {
            fos = new FileOutputStream( new File( filename ) );
            oos = new ObjectOutputStream( fos );
            oos.writeObject( obj );
            closeOutput();
        } catch( IOException ioe ) {
            System.out.println("Could not serialize object to file");
        }
    }

    public static Object readObjectFromFile( String filename ) {
        try {
            fis = new FileInputStream( new File( filename ) );
            ois = new ObjectInputStream( fis );
            Object obj = ois.readObject();
            closeInput();
            return obj;
        } catch( Exception e ) {
            System.out.println("Could not read object from file");
        }
        return null;
    }

    public static void closeOutput() {
        try {
            fos.close();
            oos.close();
        } catch( IOException ioe ) {
            System.out.println("Failed to close Object Serializer outputs");
        }
    }

    public static void closeInput() {
        try {
            fis.close();
            ois.close();
        } catch( IOException ioe ) {
            System.out.println("Failed to close Object Serializer inputs");
        }
    }
}
