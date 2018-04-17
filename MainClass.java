import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainClass extends JFrame implements Runnable{
	static private Socket connection; 
	static private ObjectOutputStream output;
	static private ObjectInputStream input;
	public static void main(String[] args) {
		new Thread(new MainClass("Server (ИИ братан!!!)")).start();
		new Thread(new Server()).start();
	}
	public MainClass(String name) {
		super(name);
		setLayout(new FlowLayout());
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		final JTextField t1 = new JTextField(10);
		final JButton b1 = new JButton("Отправить");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==b1) {
					sendData(t1.getText());
				}
			}
		});
		add(t1);
		add(b1);
	}
	public void run() {
		try {
			while(true) {
				connection = new Socket(InetAddress.getByName("127.0.0.1"), 5786);
				output = new ObjectOutputStream(connection.getOutputStream());
				input = new ObjectInputStream(connection.getInputStream());
				JOptionPane.showMessageDialog(null, (String)input.readObject());
			}
		}catch(UnknownHostException e) {
		}catch(IOException e) {
		}catch (HeadlessException e) {
		}catch (ClassNotFoundException e) {}
	}
	private static void sendData(Object obj) {
		try {
			output.flush();
			output.writeObject(obj);
		}catch(IOException e) {}
	}
}
