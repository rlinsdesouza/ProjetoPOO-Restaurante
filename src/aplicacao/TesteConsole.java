//package aplicacao;
///**********************************
// * IFPB - Curso Superior de Tec. em Sist. para Internet
// * Programa��o Orientada a Objetos
// * Prof. Fausto Maranh�o Ayres
// **********************************/
//
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import fachada.Fachada;
//import modelo.Prateleira;
//import modelo.Produto;
//
//public class TesteConsole {
//	private Scanner teclado = new Scanner(System.in);
//
//	public TesteConsole() {
//		try{
//			Produto p;
//			p = Fachada.cadastrarProduto("arroz", 3.0);
//			p = Fachada.cadastrarProduto("feijao", 5.0);
//			p = Fachada.cadastrarProduto("leite", 2.0);
//			p = Fachada.cadastrarProduto("carne", 30.0);
//			p = Fachada.cadastrarProduto("oleo", 10.0);
//			Prateleira pt;
//			pt = Fachada.cadastrarPrateleira(200);
//			pt = Fachada.cadastrarPrateleira(300);
//			pt = Fachada.cadastrarPrateleira(400);	
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//		}
//		processarMenu();
//	}
//
//	public  void processarMenu() {
//		String entrada;
//		int opcao;
//		System.out.println("\n\n <-- Bem vindo � loja Bem Muito Mais --> ");
//		do {
//			exibirMenu();
//			try{
//				entrada = teclado.nextLine();
//				opcao = Integer.parseInt(entrada);
//				switch (opcao) {
//				case 0:	break;
//				case 1:	tarefa_cadastrarProduto();				break;
//				case 2:	tarefa_cadastrarPrateleira();			break;
//				case 3:	tarefa_inserirProduto();				break;
//				case 4: tarefa_listarProdutos();				break;
//				case 5: tarefa_listarPrateleiras();				break;
//				default: System.out.println("Op��o Invalida !! \n");
//				}
//			}
//			catch(NumberFormatException e)	{
//				System.out.println("Digite somente n�mero! \n");
//				opcao=-1;
//			}
//			catch(Exception e)	{
//				System.out.println("erro:" + e.getMessage());
//				//e.printStackTrace();
//				opcao=-1;
//			}		
//		}while (opcao != 0);
//		System.out.println("\n <-- At� Breve -->");
//	}
//
//	public void exibirMenu() {
//		System.out.println("\n\n| - - - - - - - - -  Menu  - - - - - - - - - - - |");
//		System.out.println("|  [0]- Sair                                     |");
//		System.out.println("|  [1]- Cadastrar produto                        |");
//		System.out.println("|  [2]- Cadastrar prateleira                     |");
//		System.out.println("|  [3]- Incluir Produto na prateleira            |");
//		System.out.println("|  [4]- Listar produtos                          |");
//		System.out.println("|  [5]- Listar prateleiras                       |");
//		System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - -|");
//		System.out.print("  Op��o :");
//	}
//
//
//	//	---------------------------------------	
//	public void tarefa_cadastrarProduto(){
//		//	---------------------------------------			
//		Produto p;
//		String nome;
//		String preco;
//		System.out.println("\n---CADASTRO DE PRODUTO---");
//		System.out.print("Nome do Produto(ou ENTER para voltar):");
//		nome = teclado.nextLine();		
//		while (!nome.equals("")) {
//			try{
//				System.out.print("preco:");
//				preco = teclado.nextLine();
//
//				p = Fachada.cadastrarProduto(nome,	Double.parseDouble(preco)	) ;
//				System.out.println("--> cadastrado com sucesso ! --> " + p.getNome() +"\n");
//			}
//			catch(Exception e){
//				System.out.println("-->" + e.getMessage());
//			}
//			System.out.print("Nome do Produto(ou ENTER para voltar):");
//			nome = teclado.nextLine();			
//		}
//	}
//
//
//	//	---------------------------------------	
//	public void tarefa_cadastrarPrateleira(){
//		//	---------------------------------------			
//		System.out.println("\n---CADASTRO DE PRATELEIRA---");
//		Prateleira pt;
//		String tamanho;
//		System.out.print("tamanho da prateleira(ou ENTER para voltar):");
//		tamanho = teclado.nextLine();		
//		while (!tamanho.equals("")) {
//			try{				
//				pt = Fachada.cadastrarPrateleira(Double.parseDouble(tamanho)) ;
//				System.out.println("--> cadastrado com sucesso ! --> " + pt.getId() +"\n");
//			}
//			catch(Exception e){
//				System.out.println("-->" + e.getMessage());
//			}
//			System.out.print("tamanho da prateleira(ou ENTER para voltar):");
//			tamanho = teclado.nextLine();		
//		}
//	}
//
//	
//	//	---------------------------------------	
//	public void tarefa_inserirProduto(){
//		//	---------------------------------------			
//		System.out.println("\n---ALOCA��O DE PRODUTO NA PRATELEIRA---");
//		String nome,idprat;
//		System.out.print("Nome do Produto:");
//		nome = teclado.nextLine();	
//		System.out.print("id da prateleira:");
//		idprat = teclado.nextLine();		
//		try{	
//			Fachada.inserirProdutoPrateleira(Integer.parseInt(idprat), nome) ;
//			System.out.println("--> inserido com sucesso !   \n");
//		}
//		catch(Exception e){
//			System.out.println("-->" + e.getMessage());
//		}		
//	}
//
//
//	//	---------------------------------------	
//	public void tarefa_listarProdutos()
//	//	---------------------------------------	
//	{
//		System.out.println("\n---------inicio--------");
//		String texto;
//		ArrayList<Produto> lista1 = Fachada.listarProdutos();
//		texto = "Listagem de produtos: \n";
//		if (lista1.isEmpty())
//			texto += "n�o tem produto cadastrado\n";
//		else 	
//			for(Produto p: lista1) 
//				texto +=  p + "\n"; 
//
//		System.out.println(texto);
//		System.out.println("-----------fim-----------");
//	}
//
//	//	---------------------------------------	
//	public void tarefa_listarPrateleiras()
//	//	---------------------------------------	
//	{
//		System.out.println("\n---------inicio--------");
//		ArrayList<Prateleira> lista2 = Fachada.listarPrateleiras();
//		String texto = "Listagem de prateleiras: \n";
//		if (lista2.isEmpty())
//			texto += "n�o tem prateleira cadastrada\n";
//		else 
//			for(Prateleira p: lista2) 
//				texto +=  p + "\n"; 
//
//		System.out.println(texto);
//		System.out.println("-----------fim-----------");
//	}
//
//	//  ***********************************************
//	public static void main (String[] args)   
//	//  ***********************************************
//	{
//		new TesteConsole();
//	}
//
//}
