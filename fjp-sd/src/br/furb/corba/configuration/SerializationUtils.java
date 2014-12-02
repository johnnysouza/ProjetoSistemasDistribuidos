package br.furb.corba.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationUtils {

	/**
	 * Serializa um objeto num arquivo.
	 * 
	 * @param object o objeto a ser serializado.
	 * @param file o arquivo onde onde a serialização será percistida.
	 */
	public static void serializeObject(Object object, File file) {
		if (object instanceof Serializable) {
			FileOutputStream fileWriter = null;
			ObjectOutputStream objectWriter = null;
			try {
				fileWriter = new FileOutputStream(file);
				objectWriter = new ObjectOutputStream(fileWriter);
				objectWriter.writeObject(object);
			} catch (FileNotFoundException e) {
				System.out.println("Arquivo não encontrado");
			} catch (IOException e) {
				System.out.println("Erro na leitura do arquivo");
			} finally {
				try {
					if (fileWriter != null) {
						fileWriter.close();
					}
					if (objectWriter != null) {
						objectWriter.close();
					}
				} catch (IOException ex) {
					System.out.println("Erro na liberação dos leitores do arquivo");
				}
			}
		} else {
			System.out.println("Objeto não é serializável");
		}
	}

	/**
	 * Deserealiza um arquivo para um objeto.
	 * 
	 * @param file o arquivo a ser deserealizado.
	 * @return o objecto deserealizado ou <code>null</code> se ocorrer algum problema.
	 */
	public static Object deserealizeFile(File file) {
		Object object = null;

		FileInputStream fileReader = null;
		ObjectInputStream objectReader = null;
		try {
			fileReader = new FileInputStream(file);
			objectReader = new ObjectInputStream(fileReader);
			object = objectReader.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		} catch (ClassNotFoundException e) {
			System.out.println("Erro no carregamento do job");
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				if (objectReader != null) {
					objectReader.close();
				}
			} catch (IOException ex) {
				System.out.println("Erro na liberação dos leitores do arquivo");
			}
		}

		return object;
	}

}
