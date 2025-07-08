package PDS.UT6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que permite medir el tamaño en memoria de un objeto serializable.
 * @author jechague
 */
public class ObjectSizeFetcher {

    /**
     * Método que calcula el tamaño en memoria de un objeto serializable.
     * Utiliza la serialización para determinar el tamaño del objeto.
     * 
     * La serialización convierte el objeto en un flujo de bytes, y el tamaño del flujo resultante
     * se utiliza como una aproximación del tamaño del objeto en memoria.
     *
     * @param o El objeto cuyo tamaño se desea medir.
     * @return El tamaño del objeto en bytes.
     */
    public static long getObjectSize(Object o) {
        ObjectOutputStream oos = null;
        try {
            // Se crea un ByteArrayOutputStream para almacenar los datos serializados
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Se crea un ObjectOutputStream para escribir el objeto en el ByteArrayOutputStream
            oos = new ObjectOutputStream(baos);

            // Se escribe el objeto en el ObjectOutputStream, lo que lo serializa
            oos.writeObject(o);

            // Se cierra el ObjectOutputStream para finalizar la serialización
            oos.close();

            // Se obtiene el tamaño del ByteArrayOutputStream, que representa el tamaño del objeto serializado
            // y, por ende, el tamaño del objeto en memoria
            return baos.size();
        } catch (IOException ex) {
            Logger.getLogger(ObjectSizeFetcher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(ObjectSizeFetcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
}
