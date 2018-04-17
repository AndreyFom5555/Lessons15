import java.awt.*;
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Server implements Runnable{
	static private ServerSocket server; 
	static private Socket connection; 
	static private ObjectOutputStream output;
	static private ObjectInputStream input;
	@SuppressWarnings("null")
	public void run() {
		try {
			server = new ServerSocket(5786, 10);
			while(true) {
				connection = server.accept();
				output = new ObjectOutputStream(connection.getOutputStream());
				input = new ObjectInputStream(connection.getInputStream());
				output.writeObject("Вы выслали серверу '" + input.readObject() + "'");
				Thread.sleep(3000);
				JOptionPane.showMessageDialog(null, "Ждите ответа сервера...\nНажмите 'ОК' для включения Server-приёмника");
				Thread.sleep(5000);
				JOptionPane.showMessageDialog(null, "Сервер  -  Автоответ \nЗдравствуйте, время ожидания вышло. Cервер был отключён или же удалён...");
			}
		}catch(UnknownHostException e) {
		}catch(IOException e) {
		}catch(HeadlessException e) {
		}catch(ClassNotFoundException e) {
		}catch(InterruptedException e) {}
	}
}
