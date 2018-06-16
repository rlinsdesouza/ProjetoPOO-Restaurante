package repositorio;
import java.util.ArrayList;

import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Produto;

public class Restaurante {
	
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	private ArrayList<Mesa> mesas = new ArrayList<>();
	private int primaryKeyMesas = 0;
	private ArrayList<Conta> contas = new ArrayList<>();
	private int primaryKeyContas = 0;
	private ArrayList<Garcom> garcons = new ArrayList<Garcom>();
	
	public ArrayList<Garcom> getGarcons() {
		return garcons;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public ArrayList<Mesa> getMesas() {
		return mesas;
	}
	
	public ArrayList<Conta> getContas() {
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
	
	public boolean adicionarProduto (Produto produtoAdicionado) throws Exception {
		Produto p = localizarProduto(produtoAdicionado.getNome());
		if (p != null) {
			throw new Exception("Produto já cadastrado");
		}
		return produtos.add(produtoAdicionado);
	}
	
	public boolean adicionarGarcom (Garcom garcomAdicionado) throws Exception {
		Garcom g = localizarGarcom(garcomAdicionado.getApelido());
		if (g != null) {
			throw new Exception("Garcom já cadastrado");
		}
		
		return garcons.add(garcomAdicionado);
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
		for (Garcom g : garcons) {
			if (g.getApelido().equalsIgnoreCase(garcom)) {
				return g;
			}
		}
		return null;
	}
	
	public boolean removerConta (Conta c) {
		return this.contas.remove(c);
	}
	
}
