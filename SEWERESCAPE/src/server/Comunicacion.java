package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;

public class Comunicacion extends Observable implements Runnable {

	private Socket s;

	private int id;

	private boolean conectado;

	public Comunicacion(Socket s, int id) {
		this.s = s;
		this.id = id;
	}

	@Override
	public void run() {
		while (conectado) {
			recibirObjeto();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void recibirObjeto() {
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(s.getInputStream());
			Mensaje mensaje = (Mensaje) ois.readObject();

			setChanged();
			notifyObservers(mensaje);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (ois != null)
					ois.close();
				s.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			s = null;
			conectado = false;
			setChanged();
			notifyObservers("cliente desconectado");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void enviar(String mensaje) {
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(mensaje);
			System.out.println("[CONTROL CLIENTE" + id + "]: Se ha enviado el mensje a " + mensaje);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				if (dos != null) {
					dos.close();
				}
				s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			s = null;
			conectado = false;
			setChanged();
			notifyObservers("cliente desconectado");
		}
	}

	public void enviarObjeto(Mensaje m) {
		ObjectOutputStream ous = null;

		try {
			ous = new ObjectOutputStream(s.getOutputStream());
			ous.writeObject(m);
			System.out.println("[CONTROL CLIENTE " + id + "]: Se envio el mensaje: " + m);
		} catch (IOException e) {
			try {
				if (ous != null)
					ous.close();
				s.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			s=null;
			conectado = false;
			setChanged();
			notifyObservers("cliente desconectado");
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
