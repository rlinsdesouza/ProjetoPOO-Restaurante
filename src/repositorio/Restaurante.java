package repositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Produto;

public class Restaurante {

	private List<Produto> produtos = new ArrayList<Produto>();
	private List<Mesa> mesas = new ArrayList<>();
	private int primaryKeyMesas = 0;
	private List<Conta> contas = new ArrayList<>();
	private int primaryKeyContas = 0;
	private Map<String,Garcom> garcons = new TreeMap<>();

	public Map<String,Garcom> getGarcons() {
		return garcons;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public int primaryKeyMesas () {
		return ++primaryKeyMesas;
	}

	public int primaryKeyContas () {
		return ++primaryKeyContas;
	}

	public boolean adicionarMesa (Mesa mesa) throws Exception {
		return mesas.add(mesa);
	}

	public boolean adicionarConta (Conta conta) {
		return contas.add(conta);
	}

	public boolean adicionarProduto (Produto produtoAdicionado){
		return produtos.add(produtoAdicionado);
	}

	public Garcom adicionarGarcom (Garcom garcomAdicionado) {
		return garcons.put(garcomAdicionado.getApelido().toUpperCase(),garcomAdicionado);
	}

	public Mesa localizarMesa (int id) {
		for (int i = 0; i < this.mesas.size(); i++) {
			if (id == mesas.get(i).getId()) {
				return mesas.get(i);
			};
		}
		return null;
	}


	public Produto localizarProduto (String nome) {
		for (Produto p : produtos) {
			if (p.getNome().equalsIgnoreCase(nome)) {
				return p;
			}
		}
		return null;
	}

	public Garcom localizarGarcom (String garcom) {
		return garcons.get(garcom.toUpperCase());
	}

	public boolean removerConta (Conta c) {
		return this.contas.remove(c);
	}

}
