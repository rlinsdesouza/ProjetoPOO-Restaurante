package aplicacao;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.FachadaRestaurante;
import modelo.Garcom;

public class TelaCadastroGarcom extends JFrame {

	private JPanel contentPane;
	private JTextField apelido;
	private JTextField mesaInit;
	private JTextField mesaEnd;
	private JLabel lblNome;
	private JLabel lblPreco;
	private JLabel lblMesafinal;
	private JButton btnCriar;
	private JLabel lblmsg;
	private JButton btnDefinirMesa;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroGarcom frame = new TelaCadastroGarcom();
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
	public TelaCadastroGarcom() {
		setTitle("Cadastrar Garçom");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		apelido = new JTextField();
		apelido.setBounds(112, 12, 86, 20);
		contentPane.add(apelido);
		apelido.setColumns(10);

		lblNome = new JLabel("Apelido");
		lblNome.setBounds(10, 14, 84, 14);
		contentPane.add(lblNome);

		lblPreco = new JLabel("Mesa inicial");
		lblPreco.setBounds(10, 52, 95, 18);
		contentPane.add(lblPreco);

		mesaInit = new JTextField();
		mesaInit.setBounds(112, 52, 86, 20);
		contentPane.add(mesaInit);
		mesaInit.setColumns(10);
		
		lblMesafinal = new JLabel("Mesa final");
		lblMesafinal.setBounds(10, 88, 84, 14);
		contentPane.add(lblMesafinal);

		mesaEnd = new JTextField();
		mesaEnd.setBounds(112, 82, 86, 20);
		contentPane.add(mesaEnd);
		mesaEnd.setColumns(10);


		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String apelid = apelido.getText();
					int mI = Integer.parseInt(mesaInit.getText());
					int mE = Integer.parseInt(mesaEnd.getText());
					Garcom g = FachadaRestaurante.cadastrarGarcom(apelid, mI, mE);
					lblmsg.setText("cadastrado "+g.getApelido());
					
					apelido.setText("");
					mesaInit.setText("");
					mesaEnd.setText("");
					apelido.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(20, 114, 115, 23);
		contentPane.add(btnCriar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(12, 142, 273, 14);
		contentPane.add(lblmsg);
		
		btnDefinirMesa = new JButton("Definir mesas");
		btnDefinirMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String apelid = apelido.getText();
					int mI = Integer.parseInt(mesaInit.getText());
					int mE = Integer.parseInt(mesaEnd.getText());
					FachadaRestaurante.definirGarcomMesa(apelid, mI, mE);
					lblmsg.setText("Mesas definidas com sucesso!");
					
					apelido.setText("");
					mesaInit.setText("");
					mesaEnd.setText("");
					apelido.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnDefinirMesa.setBounds(147, 114, 140, 23);
		contentPane.add(btnDefinirMesa);
	}
}
