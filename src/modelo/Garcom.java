package modelo;
import java.util.ArrayList;

public class Garcom {

	private String apelido;
	private ArrayList<Mesa> mesas = new ArrayList<Mesa>();
	
	public Garcom(String apelido, ArrayList<Mesa> mesas) {
		super();
		this.apelido = apelido;
		this.mesas = mesas;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public ArrayList<Mesa> getMesas() {
		return this.mesas;
	}

	public void setMesas(ArrayList<Mesa> mesas) {
		this.mesas = mesas;
	}
	
	public String toString () {
		return "Garçom: "+apelido+" \n Mesas:"+mesas;
	}
}
