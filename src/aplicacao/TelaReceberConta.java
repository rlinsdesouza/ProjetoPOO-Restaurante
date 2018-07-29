package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import repositorio.Restaurante;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TelaReceberConta extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMesa;
	private JTextField textFieldCartao;
	private JLabel lblMesa;
	private JLabel lblCartao;
	private JButton btnPagar;
	private JLabel lblmsg;
	private JTextField textFieldQntParc;
	private JComboBox comboBoxTipoPgmt;
	private JComboBox comboBoxDesconto;
	private JLabel lblQuantParcelas;
	private JLabel lblFormaPagamento;
	private JLabel labelDesconto;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroProduto frame = new TelaCadastroProduto();
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
	public TelaReceberConta() {
		setTitle("Pagar Conta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldMesa = new JTextField();
		textFieldMesa.setBounds(197, 12, 86, 20);
		contentPane.add(textFieldMesa);
		textFieldMesa.setColumns(10);

		lblMesa = new JLabel("Mesa");
		lblMesa.setBounds(10, 14, 46, 14);
		contentPane.add(lblMesa);

		lblCartao = new JLabel("Cartão");
		lblCartao.setBounds(10, 120, 63, 14);
		contentPane.add(lblCartao);
		lblCartao.setVisible(false);

		textFieldCartao = new JTextField();
		textFieldCartao.setBounds(109, 118, 174, 20);
		contentPane.add(textFieldCartao);
		textFieldCartao.setColumns(10);
		textFieldCartao.setVisible(false);

		btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(textFieldMesa.getText()==null || textFieldMesa.getText().trim().equals("")) 
						textFieldMesa.setText("0");
					int idmesa = Integer.parseInt(textFieldMesa.getText());
					String tipopag = comboBoxTipoPgmt.getSelectedItem().toString();
					int percent = Integer.parseInt(comboBoxDesconto.getSelectedItem().toString());
					String numcartao = textFieldCartao.getText();
					if(textFieldQntParc.getText()==null || textFieldQntParc.getText().trim().equals("")) 
						textFieldQntParc.setText("0");
					int quantparcelas = Integer.parseInt(textFieldQntParc.getText());

					
					Fachada.pagarConta(idmesa, tipopag, percent, numcartao, quantparcelas);
					lblmsg.setText("Conta paga! Valor final: "+Fachada.consultarConta(idmesa).getPagamento().getValorpago());
					
					textFieldMesa.setText("");
					textFieldCartao.setText("");
					textFieldMesa.requestFocus();
				}
				catch(Exception erro){
					//erro.printStackTrace();
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnPagar.setBounds(91, 177, 115, 23);
		contentPane.add(btnPagar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 206, 273, 14);
		contentPane.add(lblmsg);
		
		lblQuantParcelas = new JLabel("Quant Parcelas");
		lblQuantParcelas.setBounds(10, 144, 115, 14);
		contentPane.add(lblQuantParcelas);
		lblQuantParcelas.setVisible(false);
		
		textFieldQntParc = new JTextField();
		textFieldQntParc.setColumns(10);
		textFieldQntParc.setBounds(197, 142, 86, 20);
		contentPane.add(textFieldQntParc);
		textFieldQntParc.setVisible(false);
		
		lblFormaPagamento = new JLabel("Forma Pagamento");
		lblFormaPagamento.setBounds(10, 49, 148, 14);
		contentPane.add(lblFormaPagamento);

		
		labelDesconto = new JLabel("Desconto%");
		labelDesconto.setBounds(30, 87, 79, 14);
		contentPane.add(labelDesconto);
		
		comboBoxTipoPgmt = new JComboBox();
		comboBoxTipoPgmt.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxTipoPgmt.getSelectedItem().toString().equalsIgnoreCase("cartao")) {
					comboBoxDesconto.setEnabled(false);
					lblCartao.setVisible(true);
					lblQuantParcelas.setVisible(true);
					textFieldCartao.setVisible(true);
					textFieldQntParc.setVisible(true);
				}else {
					comboBoxDesconto.setEnabled(true);
					lblCartao.setVisible(false);
					lblQuantParcelas.setVisible(false);
					textFieldCartao.setVisible(false);
					textFieldQntParc.setVisible(false);
				}
			}
		});
		comboBoxTipoPgmt.setModel(new DefaultComboBoxModel(new String[] {"Dinheiro", "Cartao"}));
		comboBoxTipoPgmt.setBounds(174, 44, 109, 24);
		contentPane.add(comboBoxTipoPgmt);
		
		comboBoxDesconto = new JComboBox();
		comboBoxDesconto.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		comboBoxDesconto.setBounds(160, 82, 63, 24);
		contentPane.add(comboBoxDesconto);
	}
}
