package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.FachadaRestaurante;
import modelo.Conta;

public class TelaControleConta extends JFrame {

	private JPanel contentPane;
	private JTextField formmesa;
	private JLabel lblNome;
	private JButton btnCriar;
	private JButton btnFecharConta;
	private JLabel lblmsg;
	private JTextField formgarcom;
	private JButton btnTransferirConta;
	private JButton btnCancelarConta;
	private JButton btnSolicitarProduto;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroConta frame = new TelaCadastroConta();
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
	public TelaControleConta() {
		setTitle("Controle de Contas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 328, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		formmesa = new JTextField();
		formmesa.setBounds(112, 47, 86, 20);
		contentPane.add(formmesa);
		formmesa.setColumns(10);

		lblNome = new JLabel("Mesa");
		lblNome.setBounds(22, 49, 46, 14);
		contentPane.add(lblNome);

		btnCriar = new JButton("Abrir conta");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int mesa = Integer.parseInt(formmesa.getText());
					String apelido = formgarcom.getText();
					FachadaRestaurante.permissaoGarcom(apelido, mesa);
					Conta c = FachadaRestaurante.criarConta(mesa);
					lblmsg.setText("cadastrado conta na mesa "+mesa);
					
					formmesa.setText("");
					formgarcom.setText("");
					formmesa.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(12, 79, 115, 23);
		contentPane.add(btnCriar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 185, 273, 14);
		contentPane.add(lblmsg);
		
		btnFecharConta = new JButton("Fechar conta");
		btnFecharConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int mesa = Integer.parseInt(formmesa.getText());
					String apelido = formgarcom.getText();
					FachadaRestaurante.permissaoGarcom(apelido, mesa);
					FachadaRestaurante.fecharConta(mesa);
					lblmsg.setText("Conta da mesa "+mesa+" fechada com sucesso!");
					
					formmesa.setText("");
					formgarcom.setText("");
					formmesa.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnFecharConta.setBounds(147, 79, 136, 23);
		contentPane.add(btnFecharConta);
		
		JLabel lblGarom = new JLabel("Garçom");
		lblGarom.setBounds(21, 12, 63, 14);
		contentPane.add(lblGarom);
		
		formgarcom = new JTextField();
		formgarcom.setColumns(10);
		formgarcom.setBounds(112, 10, 86, 20);
		contentPane.add(formgarcom);
		
		btnTransferirConta = new JButton("Transferir Conta");
		btnTransferirConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int mesa1 = Integer.parseInt(formmesa.getText());
					String apelido = formgarcom.getText();
					FachadaRestaurante.permissaoGarcom(apelido, mesa1);
					int mesa2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Favor digite a mesa de DESTINO: "));
					FachadaRestaurante.permissaoGarcom(apelido, mesa2);
					FachadaRestaurante.transferirConta(mesa1, mesa2);
					lblmsg.setText ("Transferido com sucesso!");
					//lblmsg.setText("Conta da mesa "+mesa1+" transferida para "+mesa2+"!");
					
					formmesa.setText("");
					formgarcom.setText("");
					formmesa.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnTransferirConta.setBounds(0, 114, 150, 23);
		contentPane.add(btnTransferirConta);
		
		btnCancelarConta = new JButton("Cancelar Conta");
		btnCancelarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int mesa = Integer.parseInt(formmesa.getText());
					String apelido = formgarcom.getText();
					FachadaRestaurante.permissaoGarcom(apelido, mesa);
					FachadaRestaurante.cancelarConta(mesa);
					lblmsg.setText("cancelado conta da mesa"+mesa);
					
					formmesa.setText("");
					formgarcom.setText("");
					formmesa.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCancelarConta.setBounds(157, 114, 142, 23);
		contentPane.add(btnCancelarConta);
		
		btnSolicitarProduto = new JButton("Solicitar produto");
		btnSolicitarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					TelaInserirProdutoMesas j = new TelaInserirProdutoMesas();
					j.setVisible(true);
					
					formmesa.setText("");
					formgarcom.setText("");
					formmesa.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnSolicitarProduto.setBounds(75, 149, 167, 23);
		contentPane.add(btnSolicitarProduto);
	}
}
