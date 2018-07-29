package modelo;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import fachada.Fachada;

public abstract class Pagamento {
	
	private double valorpago;

	public Pagamento(double valorpago) {
		this.valorpago = valorpago;
	}
	
	public double getValorpago() {
		return valorpago;
	}

	public void setValorpago(double valorpago) {
		this.valorpago = valorpago;
	}

	public abstract void calcularPagamento (double totalconta);
	
	public static double calcularGorjeta (String apelido) {
		double gorjeta=0;
		LocalDateTime hoje = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
		
		for (Conta conta : Fachada.listarContas()) {
			if (conta.getMesa().getGarcom().getApelido().equalsIgnoreCase(apelido) 
				&& conta.getPagamento()!=null
				&& conta.getDtfechamento().substring(0,9).equalsIgnoreCase(hoje.format(f).substring(0,9))
				){
				gorjeta =  gorjeta+(0.10*conta.getPagamento().getValorpago());
			}
		}
		
		return gorjeta;
	}
}
