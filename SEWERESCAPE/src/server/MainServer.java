package server;

public class MainServer {

	private static Logica com;
	
	public static void main(String [] args){
		com = new Logica();
		new Thread(com).start();
	}
}
