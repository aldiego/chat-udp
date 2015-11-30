/**
 * 
 */
package br.com.fatec.chat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.com.fatec.chat.model.User;

/**
 * @author Diego
 *
 */
public class Chat extends JFrame implements Runnable {

	private static Chat INSTANCE;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6225979871909173016L;

	private JPanel contentPane;
	private final JButton btnSay = new JButton("Say");
	private final JButton btnWhisper = new JButton("Whisper");
	private final JButton btnLeave = new JButton("Leave");

	private JTextArea textConversa = new JTextArea();
	private JTextArea textEnviar = new JTextArea();
	private JComboBox<User> comboBoxUsuarios = new JComboBox<User>();

	/**
	 * Create the frame.
	 */
	private Chat() {
		addWindowListener(configuraJanela());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(textEnviar, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
								.addComponent(textConversa, GroupLayout.PREFERRED_SIZE, 627, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnLeave).addGap(18)
										.addComponent(comboBoxUsuarios, 0, 390, Short.MAX_VALUE).addGap(18)
										.addComponent(btnWhisper).addGap(18).addComponent(btnSay)))
						.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(textConversa, GroupLayout.PREFERRED_SIZE, 242,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(textEnviar, GroupLayout.PREFERRED_SIZE, 121,
												GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnSay)
								.addComponent(btnWhisper).addComponent(comboBoxUsuarios, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLeave)).addGap(14)));
		montaUI(gl_contentPane);

		btnSay.setEnabled(true);
		btnSay.addActionListener(enviarSay());

		btnWhisper.setEnabled(true);
		btnWhisper.addActionListener(enviaWhisper());

		btnLeave.addActionListener(enviaListener());

		textConversa.setForeground(Color.BLACK);
		textConversa.setEditable(false);
		contentPane.setLayout(gl_contentPane);

		System.out.println("UI iniciado...");
	}

	public static Chat getInsance() {
		if (INSTANCE == null) {
			return new Chat();
		}
		return INSTANCE;
	}

	/**
	 * @return
	 */
	private ActionListener enviaListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent clique) {

				EntryPoint.getWriter().enviaLeave();
			}
		};
	}

	/**
	 * @return
	 */
	private ActionListener enviaWhisper() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((User) comboBoxUsuarios.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(contentPane, "Selecione alguém para sussurar", "Selecione um usuario",
							JOptionPane.ERROR_MESSAGE);
				} else {
					EntryPoint.getWriter().enviaWhisper((User) comboBoxUsuarios.getSelectedItem(),
							textConversa.getText());
				}
			}
		};
	}

	/**
	 * @return
	 */
	private ActionListener enviarSay() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent clique) {

				EntryPoint.getWriter().enviaSay((User) comboBoxUsuarios.getSelectedItem(), textConversa.getText());

				textConversa.setText("");
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			Chat frame = new Chat();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JButton getBtnSay() {
		return btnSay;
	}

	public JButton getBtnWhisper() {
		return btnWhisper;
	}

	public JTextArea getTextConversa() {
		return textConversa;
	}

	public JTextArea getTextEnviar() {
		return textEnviar;
	}

	public JComboBox<User> getComboBoxUsuarios() {
		return comboBoxUsuarios;
	}

	/**
	 * @return
	 */
	private WindowAdapter configuraJanela() {
		return new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				EntryPoint.SAIR = true;
			}
		};
	}

	/**
	 * @param gl_contentPane
	 */
	private void montaUI(GroupLayout gl_contentPane) {
	}
}
