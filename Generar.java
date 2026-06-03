import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

/**
 *
 * @author López Cruz Luis Eduardo
 * @version 2/06/26
 */
public abstract class Generar
{
	protected boolean guardarObjeto(String archivoNombre, Object objeto){
		try{
			File archivo = new File(archivoNombre);
			boolean existe = archivo.exists() && archivo.length() > 0;
			FileOutputStream fos = new FileOutputStream(archivo, true);
			ObjectOutputStream oos;
			if(existe){
				oos = new AppendableObjectOutputStream(fos);
			}else{
				oos = new ObjectOutputStream(fos);
			}
			oos.writeObject(objeto);
			oos.close();
			fos.close();
			return true;
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
			return false;
		}
	}

	protected static class AppendableObjectOutputStream extends ObjectOutputStream
	{
		public AppendableObjectOutputStream(FileOutputStream fos) throws IOException{
			super(fos);
		}

		protected void writeStreamHeader() throws IOException{
		}
	}
}
