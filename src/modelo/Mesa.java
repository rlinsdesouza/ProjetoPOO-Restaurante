package modelo;
import java.util.ArrayList;

public class Mesa {

	private int id;
	private boolean ocupada;
	private ArrayList<Conta>contas = new ArrayList<Conta>();
	private Garcom garcom;
	
	public Mesa(int id, boolean ocupada) {
		this.id = id;
		this.ocupada = ocupada;
		this.garcom = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}

	public Garcom getGarcom() {
		return garcom;
	}

	public void setGarcom(Garcom garcom) {
		this.garcom = garcom;
	}
	
	public String toString () {
		if (this.ocupada) {
			return "Mesa: "+id+" ---- Status: Ocupada";	
		}
		return "Mesa: "+id+" ---- Status: Livre";
	}
	
}
