package server;

public class MainServer {

	private static Comunicacion com;
	
	public static void main(String [] args){
		com = new Comunicacion(0);
		new Thread(com).start();
	}
}
