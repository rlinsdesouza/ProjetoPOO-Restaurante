//package aplicacao;
///**********************************
// * IFPB - Curso Superior de Tec. em Sist. para Internet
// * Programa��o Orientada a Objetos
// * Prof. Fausto Maranh�o Ayres
// **********************************/
//
//import java.util.ArrayList;
//
//import fachada.FachadaRestaurante;
//import modelo.Conta;
//import modelo.Garcom;
//import modelo.Mesa;
//import modelo.Produto;
//
//public class TesteRapido {
//	
//	public static void main (String[] args) {  
//		parte1();
//		parte2();
//		System.out.println("fim do teste");
//	}
//
//	public static void parte1(){
//		try {	
//			Produto p;
//			p = FachadaRestaurante.cadastrarProduto("feijoada", 25.0);
//			p = FachadaRestaurante.cadastrarProduto("bode guisado", 20.0);
//			p = FachadaRestaurante.cadastrarProduto("galhinhada", 15.0);
//			p = FachadaRestaurante.cadastrarProduto("cerveja", 6.0);
//			p = FachadaRestaurante.cadastrarProduto("refrigerante", 5.0);
//			p = FachadaRestaurante.cadastrarProduto("agua", 2.0);
//			ArrayList<Produto> produtos = FachadaRestaurante.listarProdutos();
//			System.out.println("produtos cadastrados:");
//			System.out.println(produtos);
//
//			FachadaRestaurante.criarMesas(20);		// 20 mesas
//			ArrayList<Mesa> mesas = FachadaRestaurante.listarMesas();
//			System.out.println("mesas criadas:");
//			System.out.println(mesas);
//
//			Garcom g;
//			g = FachadaRestaurante.cadastrarGarcom("baixinho", 1,5);
//			g = FachadaRestaurante.cadastrarGarcom("esperto", 6,10);
//			g = FachadaRestaurante.cadastrarGarcom("zezinho", 10,15);
//			g = FachadaRestaurante.cadastrarGarcom("zezinho", 16,20);
//			ArrayList<Garcom> garcons = FachadaRestaurante.listarGarcons();
//			System.out.println("garcons cadastrados:");
//			for (Garcom garcom : garcons) {
//				System.out.println(garcons);	
//			}
//			
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//
//	public static void parte2() {
//		try {
//			FachadaRestaurante.criarConta(1);	//mesa 1
//			FachadaRestaurante.solicitarProduto(1, "galinhada");
//			FachadaRestaurante.solicitarProduto(1, "cerveja");
//			FachadaRestaurante.solicitarProduto(1, "refrigerante");
//			System.out.println("conta da mesa 1: \n"+ FachadaRestaurante.consultarConta(1)); 
//			FachadaRestaurante.solicitarProduto(1, "cerveja");
//			FachadaRestaurante.solicitarProduto(1, "cerveja");
//			FachadaRestaurante.fecharConta(1);
//			System.out.println("conta da mesa 1: \n"+ FachadaRestaurante.consultarConta(1)); 
//
//
//			FachadaRestaurante.criarConta(5);	//mesa 5
//			FachadaRestaurante.solicitarProduto(5, "feijoada");
//			FachadaRestaurante.solicitarProduto(5, "cerveja");
//			FachadaRestaurante.fecharConta(5);
//			System.out.println("conta da mesa 5: \n"+ FachadaRestaurante.consultarConta(5)); 
//
//
//			double gorjeta = FachadaRestaurante.calcularGorjeta("baixinho");
//			System.out.println("gorjeta do baixinho="+gorjeta);
//
//			ArrayList<Conta> contas = FachadaRestaurante.listarContas();
//			System.out.println("contas existentes:");
//			System.out.println(contas);
//			
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	
//
//
//}
