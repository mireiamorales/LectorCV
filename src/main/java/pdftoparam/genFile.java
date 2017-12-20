package pdftoparam;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;
 
public class genFile {
 
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("C:/Wrk/inditex/EjemploCVPdf.pdf");
 
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
            Logger.getLogger(genFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Transformación de pdf a array de bytes
        byte[] bytes = bos.toByteArray();
 
        //El siguiente párrafo crea un documento nuevo.pdf a partir del array de bytes que contiene la misma información que el original
        File someFile = new File("C:/Wrk/inditex/nuevo.pdf");
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(bytes);
        fos.flush();
        fos.close();
        
        //Convertimos el Array de Bytes a String (JSON)
        String base64Encoded = DatatypeConverter.printBase64Binary(bytes);
        System.out.println(base64Encoded);

        //Volvemos a convertir de String(JSON) a array de bytes
        byte[] bytesfromJson =  DatatypeConverter.parseBase64Binary(base64Encoded);
        
        
        //Creamos documento nuevoFromJson.pdf a partir del último array de bytes obtenido del JSON
        someFile = new File("C:/Wrk/inditex/nuevoFromJson.pdf");
        fos = new FileOutputStream(someFile);
        fos.write(bytesfromJson);
        fos.flush();
        fos.close();
        
    }
}