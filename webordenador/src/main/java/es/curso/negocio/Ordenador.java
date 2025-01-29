package es.curso.negocio;

public class Ordenador {

	private String marca;
	private int precio;
	
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Ordenador(String marca, int precio) {
		super();
		this.marca = marca;
		this.precio = precio;
	}
	

}
