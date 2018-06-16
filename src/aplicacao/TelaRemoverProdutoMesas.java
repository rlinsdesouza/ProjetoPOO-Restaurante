
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.FachadaRestaurante;

public class TelaRemoverProdutoMesas extends JFrame {
	private JPanel contentPane;
	private JLabel lblNome;
	private JTextField textField;
	private JButton btnRemover;
	private DefaultListModel<String> model = new DefaultListModel<String>();	
	private JLabel lblMensagemDoUsuario;
	private JButton btnLimpar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaRemoverProdutoPrateleira window = new TelaRemoverProdutoPrateleira();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public TelaRemoverProdutoMesas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Remover Produto na Mesa");
		setBounds(100, 100, 345, 229);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNome = new JLabel("Nome do Produto");
		lblNome.setBounds(19, 28, 102, 14);
		contentPane.add(this.lblNome);
		textField = new JTextField();
		textField.setBounds(124, 25, 86, 20);
		contentPane.add(this.textField);
		textField.setColumns(10);
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String nome = textField.getText();			
					FachadaRestaurante.apagarProduto(nome);
					
					lblMensagemDoUsuario.setText("produto removido ");
					
				} catch (NumberFormatException e) {
					lblMensagemDoUsuario.setText("campo id deve ser numerico");
				} catch (Exception e) {
					lblMensagemDoUsuario.setText(e.getMessage());
				}
			}
		});
		btnRemover.setBounds(19, 101, 136, 23);
		contentPane.add(this.btnRemover);
		lblMensagemDoUsuario = new JLabel("Mensagem do usuario");
		lblMensagemDoUsuario.setBounds(19, 164, 294, 14);
		contentPane.add(this.lblMensagemDoUsuario);
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField.requestFocus();
			}
		});
		btnLimpar.setBounds(172, 101, 141, 23);
		contentPane.add(this.btnLimpar);

	}
}
