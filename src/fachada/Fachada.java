package fachada;	
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Pagamento;
import modelo.PagamentoCartao;
import modelo.PagamentoDinheiro;
import modelo.Produto;
import repositorio.Restaurante;

public class Fachada {

	private static Restaurante domani = new Restaurante ();
	private static Comparator<Produto> compareProduto = new Comparator<Produto> (){
		public int compare (Produto p1, Produto p2) {
			return p1.getNome().compareTo(p2.getNome());
		}
	};

	public static List<Produto> listarProdutos () {
		List<Produto> produtos = domani.getProdutos();
		Collections.sort(produtos,compareProduto);
		return produtos;
	}

	public static ArrayList<Produto> listarProdutos (String nome) {
		ArrayList<Produto> produtosFiltro = new ArrayList<Produto>();

		Collections.sort(produtosFiltro,compareProduto);
		for (Produto produto : listarProdutos()) {
			if(produto.getNome().contains(nome.toUpperCase())) {
				produtosFiltro.add(produto);
			}
		}
		return produtosFiltro;
	}

	public static ArrayList<Garcom> listarGarcons () {
		return domani.getGarcons();
	}

	public static ArrayList<Mesa> listarMesas () {
		return domani.getMesas();
	}

	public static ArrayList<Conta> listarContas () {
		return domani.getContas();
	}

	public static List<Conta> listarContas (String garcom) throws Exception {
		List<Conta> lista = new ArrayList<Conta>();

		domani.localizarGarcom(garcom);

		for (Conta conta : domani.getContas()) {
			if (conta.getDtfechamento() != null && conta.getMesa().getGarcom().getApelido().equalsIgnoreCase(garcom)) {
				lista.add(conta);
			}
		}

		return lista;
	}

	public static void criarMesas(int n) throws Exception {
		for (int i = 0; i < n; i++) {
			domani.adicionarMesa(new Mesa (domani.primaryKeyMesas(),false));
		}
	}

	public static Produto cadastrarProduto (String nome, double preco) throws Exception {
		Produto produtoAdicionado = new Produto (nome.toUpperCase(), preco);
		Produto p = domani.localizarProduto(produtoAdicionado.getNome());
		if (p != null) {
			throw new Exception("Produto já cadastrado");
		}
		domani.adicionarProduto(produtoAdicionado);
		return produtoAdicionado;
	}

	public static Produto apagarProduto (String nome) throws Exception {
		Produto produtoApagar = domani.localizarProduto(nome);

		for (Conta c : domani.getContas()) {
			if (c.getProdutos().contains(produtoApagar)) {
				new Exception ("Produto não pode ser apagado, possui contas vinculadas!");
			}
		}
		domani.getProdutos().remove(produtoApagar);
		return produtoApagar;
	}

	public static ArrayList<Mesa> definirGarcomMesa (String apelido, int mesainicial, int mesafinal) throws Exception {
		ArrayList<Mesa> mesasGarcom = new ArrayList<Mesa>();
		Garcom g = domani.localizarGarcom(apelido);

		for (int i = mesainicial; i <= mesafinal; i++) {
			Mesa mesalocalizada = domani.localizarMesa(i);
			if (mesalocalizada != null) {
				Garcom g1 = mesalocalizada.getGarcom();
				mesalocalizada.setGarcom(null);
				if (g1 != null) {
					g1.getMesas().remove(mesalocalizada);
				}
				mesasGarcom.add(mesalocalizada);
			}else {
				throw new Exception ("Mesa(s) não existente!");
			}
		}

		if (g != null) {
			if (mesafinal-mesainicial+g.getMesas().size()>=5) {
				throw new Exception ("Menos que 5 mesas por garcom!");
			}
			g.getMesas().addAll(mesasGarcom);
			for (Mesa mesa : mesasGarcom) {
				mesa.setGarcom(g);
			}
			return null;
		}
		return mesasGarcom;
	}

	public static Garcom cadastrarGarcom (String apelido, int mesainicial, int mesafinal) throws Exception {
		ArrayList<Mesa> mesasGarcom = new ArrayList<Mesa>();

		mesasGarcom = definirGarcomMesa(apelido, mesainicial, mesafinal);
		Garcom garcomAdicionado = new Garcom (apelido.toUpperCase(), mesasGarcom);

		Garcom g = domani.localizarGarcom(garcomAdicionado.getApelido());
		if (g != null) {
			throw new Exception("Garcom já cadastrado");
		}

		for (Mesa mesa : mesasGarcom) {
			mesa.setGarcom(garcomAdicionado);
		}

		domani.adicionarGarcom(garcomAdicionado);
		return garcomAdicionado;
	}

	public static Garcom excluirGarcom(String nome) throws Exception {
			Garcom g = domani.localizarGarcom(nome.toUpperCase());

		if (g!=null) {
			for (Mesa m : g.getMesas()) {
				if (m.isOcupada()) {
					throw new Exception("Falhas: contas em aberto!");
				}
			}
			domani.getGarcons().remove(nome.toUpperCase());
			for (Mesa m : g.getMesas()) {
				m.setGarcom(null);
			}
			//g.setMesas(null);
			return g;
		} else {
			throw new Exception ("Garcom não cadastrado!");
		}
	}

	public static Mesa adicionarMesa (int num) throws Exception {
		Mesa mesaAdd = new Mesa (domani.primaryKeyMesas(),false);
		if (domani.adicionarMesa(mesaAdd)) {
			return mesaAdd;
		};
		return null;
	}

	public static boolean permissaoGarcom (String apelido, int idmesa) throws Exception {
		Mesa mesa = domani.localizarMesa(idmesa);
		Garcom g = mesa.getGarcom();

		if (g != null && g.getApelido().equalsIgnoreCase(apelido)) {
			return true;
		}
		throw new Exception ("Garçom sem permissão para mesa "+idmesa);
	}

	public static Conta criarConta (int idmesa) throws Exception {

		Mesa mesaAtiva = domani.localizarMesa(idmesa);

		if (mesaAtiva == null) {
			throw new Exception ("Mesa inexistente!");
		}
		
		if (mesaAtiva.getGarcom() == null) {
			throw new Exception ("Mesa sem garcom!");
		}

		if(!mesaAtiva.isOcupada()) {
			Conta contaAdd = new Conta (domani.primaryKeyContas(), mesaAtiva);
			domani.adicionarConta(contaAdd);
			mesaAtiva.getContas().add(contaAdd);
			mesaAtiva.setOcupada(true);
			return contaAdd;
		}else {
			throw new Exception("Falha: mesa com conta aberta!");
		}
	}

	public static Conta consultarConta(int idmesa) throws Exception {
		if (domani.localizarMesa(idmesa)==null) throw new Exception ("Mesa não localizada!");
		int qntContas =  domani.localizarMesa(idmesa).getContas().size();
		if (qntContas == 0) {
			//throw new Exception ("Mesa sem nenhuma conta registrada na base!");
			return null;
		}

		/*
		for (Conta i: domani.localizarMesa(idmesa).getContas()) {
			if (i.getDtfechamento() == null) {
				return i;
			}
		}
		*/

		return domani.localizarMesa(idmesa).getContas().get(qntContas-1);
	}

	public static Produto solicitarProduto (int idmesa,String nomeproduto) throws Exception {
		Produto produtoSolicitado = domani.localizarProduto(nomeproduto);
		
		if (produtoSolicitado == null) throw new Exception ("Produto n�o cadastrado!");
		
		Mesa mesa = domani.localizarMesa(idmesa);
		
		if (!mesa.isOcupada()) throw new Exception ("Nenhuma conta em aberto!"); 
		
		Conta contaAberta = consultarConta(idmesa);

		contaAberta.getProdutos().add(produtoSolicitado);
		contaAberta.setTotal(contaAberta.getTotal()+produtoSolicitado.getPreco());
		return produtoSolicitado;
	}

	public static void cancelarConta (int idmesa) throws Exception {
		Conta contaCancelar = consultarConta(idmesa);

		domani.removerConta(contaCancelar);
		domani.localizarMesa(idmesa).getContas().remove(contaCancelar);
		domani.localizarMesa(idmesa).setOcupada(false);
	}

	public static void transferirConta(int idmesaorigem,int idmesadestino) throws Exception {
		Conta contaOrigem = consultarConta(idmesaorigem);
		Conta contaDestino = consultarConta(idmesadestino);
		
		if (contaDestino == null) {
			criarConta(idmesadestino);
		}
		
		if (contaOrigem == null) {
			throw new Exception ("Não é possível transferir produtos de uma mesa sem conta!");
		}

		if (contaOrigem == contaDestino) throw new Exception ("N�o � poss�vel transferir pra mesma mesa!"); 
		
		if (contaOrigem.getMesa().getGarcom().getApelido() != contaDestino.getMesa().getGarcom().getApelido())
			throw new Exception ("Mesas de gar�ons diferentes!");
			
		contaDestino.getProdutos().addAll(contaOrigem.getProdutos());
		contaDestino.setTotal(0);
		for (Produto produto : contaDestino.getProdutos()) {
			contaDestino.setTotal(contaDestino.getTotal()+produto.getPreco());
		}
		cancelarConta(idmesaorigem);
	}

	public static void fecharConta(int idmesa) throws Exception {
		Conta contaFechamento = consultarConta(idmesa);
		
		if (contaFechamento.getDtfechamento() != null ) 
			throw new Exception ("Nenhuma conta em aberto!"); 

		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		contaFechamento.setDtfechamento(agora.format(f));

		contaFechamento.getMesa().setOcupada(false);
	}

	public static Pagamento pagarConta (int idmesa, String tipo, int percentual, String cartao, int quantidade) throws Exception  {
		Conta contaFechamento = consultarConta(idmesa);
		Pagamento pgConta = null;

		if (contaFechamento.getDtfechamento() == null) {
			throw new Exception ("Conta não fechada ainda!");
		}
		
		if (contaFechamento.getPagamento() != null) {
			throw new Exception ("Conta j� paga!");
		}

		if (tipo.equalsIgnoreCase("dinheiro")) {
			pgConta = new PagamentoDinheiro (percentual);
		}

		if (tipo.equalsIgnoreCase("cartao")) {
			if (quantidade>4 || quantidade < 1) throw new Exception ("Verificar parcelas! (Máx=4)");
			if (quantidade > 1 && contaFechamento.getTotal()/quantidade < 100) throw new Exception ("Valor mínimo da parcela = 100!");
			pgConta = new PagamentoCartao(cartao, quantidade);
		}

		pgConta.calcularPagamento(contaFechamento.getTotal());
		contaFechamento.setPagamento(pgConta);
		return pgConta;
	}

	public static double calcularGorjeta (String apelido) {
		return Pagamento.calcularGorjeta(apelido);
	}

	public static double calcularPercentualMedio (String apelido) throws Exception {
		int somaDesc=0;
		int i=0;

		for (Conta c : listarContas(apelido)) {
			if (c.getPagamento() != null && c.getPagamento() instanceof PagamentoDinheiro) {
				PagamentoDinheiro p = (PagamentoDinheiro) c.getPagamento();
				i++;
				somaDesc = somaDesc + p.getPercentualdesconto();
			}
		}
		return i>0 ? somaDesc/(double) i : 0.0;
	}
}
