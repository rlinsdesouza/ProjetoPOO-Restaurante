package modelo;
import java.util.ArrayList;

public class Conta {

	private int numero;
	private String dtfechamento;
	private double total;
	private Mesa mesa;
	private ArrayList<Produto> produtos;
	private Pagamento pagamento;


	public Conta(int numero, Mesa mesa) {
		super();
		this.numero = numero;
		this.dtfechamento = null;
		this.total = 0.0;
		this.mesa = mesa;
		this.produtos = new ArrayList<Produto>();
		this.pagamento = null;
	}


	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDtfechamento() {
		return dtfechamento;
	}
	public void setDtfechamento(String dtfechamento) {
		this.dtfechamento = dtfechamento;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}


	public String toString () {
		return "Numero da conta: "+numero+"\n"+"Data de fechamento: "+ (dtfechamento != null ? dtfechamento : "Em aberto") +"\n"+"Numero da mesa: "+mesa.getId()+"\n"+"Garcom: "+mesa.getGarcom().getApelido()+"\n"+"Produtos: "+produtos+"\n"+"Total: "+total+"\n"+"Pagamento: "+"\n"+(pagamento != null ? pagamento : "Em aberto");
	}

}
