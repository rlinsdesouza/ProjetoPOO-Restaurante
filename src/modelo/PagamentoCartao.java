package modelo;

public class PagamentoCartao extends Pagamento {
	private String cartao;
	private int quantidadeparcelas;
		
	public PagamentoCartao(String ct, int qntparc) {
		super(0.0);
		this.cartao = ct;
		this.quantidadeparcelas = qntparc;
		
	}
	
	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public int getQuantidadeparcelas() {
		return quantidadeparcelas;
	}

	public void setQuantidadeparcelas(int quantidadeparcelas) {
		this.quantidadeparcelas = quantidadeparcelas;
	}

	@Override
	public void calcularPagamento(double totalconta) {
		
		if (this.quantidadeparcelas < 3) {
			super.setValorpago(totalconta);
		}else {
			super.setValorpago(totalconta-((double)(this.quantidadeparcelas*10-20)/100*totalconta));
		}
		
	}
	
	public String toString () {
		return "Tipo pagamento: Cartao  - Valor do pagamento: "+super.getValorpago()+"- Parcelas: "+this.quantidadeparcelas+"- Cartao:"+this.cartao;
	}

}
