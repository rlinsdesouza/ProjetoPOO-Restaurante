package fachada;	
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Produto;
import repositorio.Restaurante;

public class FachadaRestaurante {
	
	private static Restaurante domani = new Restaurante ();
	
	public static ArrayList<Produto> listarProdutos () {
		return domani.getProdutos();
	}
	
	public static ArrayList<Produto> listarProdutos (String nome) {
		ArrayList<Produto> produtosFiltro = new ArrayList<Produto>();	
		
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
	
	public static ArrayList<Conta> listarContas (String garcom) throws Exception {
		ArrayList<Conta> lista = new ArrayList<Conta>();
		
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
	
	public static Garcom removerGarcom(String nome) throws Exception {
			Garcom g = domani.localizarGarcom(nome);
			
		if (g!=null) {
			for (Mesa m : g.getMesas()) {
				if (m.isOcupada()) {
					throw new Exception("Falhas: contas em aberto!");
				}
			}
			domani.getGarcons().remove(g);
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
		int qntContas =  domani.localizarMesa(idmesa).getContas().size();
		if (qntContas == 0 ) {
			throw new Exception ("Mesa sem nenhuma conta registrada na base!");
		}
		
		for (Conta i: domani.localizarMesa(idmesa).getContas()) {
			if (i.getDtfechamento() == null) {
				return i;
			}
		}

		return domani.localizarMesa(idmesa).getContas().get(qntContas-1);
	}
	
	public static Produto solicitarProduto (int idmesa,String nomeproduto) throws Exception {
		Conta contaAberta = consultarConta(idmesa);
		Produto produtoSolicitado = domani.localizarProduto(nomeproduto);
		if (contaAberta != null) {
			contaAberta.getProdutos().add(produtoSolicitado);	
		}else {
			criarConta(idmesa).getProdutos().add(produtoSolicitado);
			contaAberta =  consultarConta(idmesa);
		}
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
		
		if (contaOrigem == null) {
			throw new Exception ("Não é possível transferir produtos de uma mesa sem conta!");
		}
		
		if (contaDestino == null) {
			contaDestino = criarConta(idmesadestino);
		}
		
		contaDestino.getProdutos().addAll(contaOrigem.getProdutos());
		contaDestino.setTotal(0);
		for (Produto produto : contaDestino.getProdutos()) {
			contaDestino.setTotal(contaDestino.getTotal()+produto.getPreco());
		}
		cancelarConta(idmesaorigem);
	}
	
	public static void fecharConta(int idmesa) throws Exception {
		Conta contaFechamento = consultarConta(idmesa);
		
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
		contaFechamento.setDtfechamento(agora.format(f));
		
		contaFechamento.getMesa().setOcupada(false);
	}
	
	public static double calcularGorjeta (String apelido) {
		double gorjeta=0;
		
		for (Conta conta : FachadaRestaurante.listarContas()) {
			if (conta.getMesa().getGarcom().getApelido().equalsIgnoreCase(apelido) && conta.getDtfechamento()!=null) {
				gorjeta =  gorjeta+(0.10*conta.getTotal());
			}
		}
		
		return gorjeta;
	}


}
