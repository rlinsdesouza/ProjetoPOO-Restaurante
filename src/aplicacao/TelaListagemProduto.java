package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Produto;

public class TelaListagemProduto extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnRefinaBusca;
	private JTextField formRefinaBusca;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaListagemProduto frame = new TelaListagemProduto();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaListagemProduto() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try{
					String texto;
					List<Produto> lista1 = Fachada.listarProdutos();
					texto = "Listagem de produtos: \n";
					if (lista1.isEmpty())
						texto += "n�o tem produto cadastrado\n";
					else
						for(Produto p: lista1) 
							texto +=  p + "\n"; 

					textArea.setText(texto);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		setTitle("Listar Produto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnRefinaBusca = new JButton("Refinar por palavra");
		btnRefinaBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String texto;
					String palavra = formRefinaBusca.getText();
					if (!palavra.isEmpty()) {
						ArrayList<Produto> lista1 = Fachada.listarProdutos(palavra);
						texto = "Listagem de produtos: \n";
						if (lista1.isEmpty())
							texto += "n�o tem produto cadastrado\n";
						else 	
							for(Produto p: lista1) 
								texto +=  p + "\n"; 

						textArea.setText(texto);	
					}
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnRefinaBusca.setBounds(260, 215, 191, 23);
		contentPane.add(btnRefinaBusca);
		
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 29, 510, 174);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
		formRefinaBusca = new JTextField();
		formRefinaBusca.setText("");
		formRefinaBusca.setBounds(112, 217, 114, 19);
		contentPane.add(formRefinaBusca);
		formRefinaBusca.setColumns(10);
	}
}
