package modelo;

public final class PagamentoDinheiro extends Pagamento {
	private int percentualdesconto;

	public PagamentoDinheiro(int percent) {
		super(0.0);
		this.percentualdesconto = percent;
	}
	
	public int getPercentualdesconto() {
		return percentualdesconto;
	}



	public void setPercentualdesconto(int percentualdesconto) {
		this.percentualdesconto = percentualdesconto;
	}



	@Override
	public void calcularPagamento(double totalconta) {
		super.setValorpago(totalconta-((double) this.percentualdesconto/100*totalconta));
	}
	
	public String toString () {
		return "Tipo pagamento: Dinheiro  - Valor do pagamento: "+super.getValorpago()+" Desconto: "+this.percentualdesconto;
	}
}
