package server;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipo;
	private String dir;

	private int x;
	private int y;

	public Mensaje(String tipo, int x, int y) {
		this.tipo = tipo;
		this.x = x;
		this.y = y;
	}

	public Mensaje(String tipo, String dir) {
		this.tipo = tipo;
		this.dir = dir;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
