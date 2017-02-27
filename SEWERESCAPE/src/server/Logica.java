package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Logica implements Observer, Runnable {

	private final int PORT = 5000;

	private ServerSocket ss;

	private boolean conectado;

	private ArrayList<Comunicacion> clientes = new ArrayList<>();

	public Logica() {
		try {
			ss = new ServerSocket(PORT);
			conectado = true;
			System.out.println(
					"[SERVER]: atendiendo en: " + InetAddress.getLocalHost().getHostAddress().toString() + ":" + PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (conectado) {

			try {
				Socket s = ss.accept();

				if (clientes.size() < 2) {
					Comunicacion com = new Comunicacion(s, clientes.size());

					com.addObserver(this);
					new Thread(com).start();
					clientes.add(com);
					System.out.println("[SERVER]: Tenemos " + clientes.size() + " clientes");

					if (clientes.size() == 2) {
						System.out.println("No mas clientes D:");
					}
				} else {
					System.out.println("No se aceptan mas clientes panita");
					rechazar(s);
				}

				Thread.sleep(100);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		Comunicacion com = (Comunicacion) o;

		if (arg instanceof Mensaje) {
			Mensaje mensaje = (Mensaje) arg;
		}
	}

	private void rechazar(Socket s) {
		try ()
	}
}
