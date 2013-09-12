package database;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EconDataComp {

	static JFrame frame;
	static JPanel panel = new JPanel();
	static JPanel panel_1 = new JPanel();
	static JPanel panel_2 = new JPanel();
	static JPanel q1q3ResultPanel = new JPanel();
	static JPanel q2ResultPanel = new JPanel();
	static JPanel modPanel = new JPanel();
	static String dateChosen;
	static JLabel projectTitle;
	static JLabel projectAuthors;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EconDataComp window = new EconDataComp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EconDataComp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Title and authors JLabels
		projectTitle = new JLabel("Economic Data Comparison System");
		projectTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		projectTitle.setBounds(75, 72, 600, 30);
		frame.add(projectTitle);
		projectAuthors = new JLabel("<html>Joseph Acosta <BR> Timothy Cohen <BR> Joseph Hause</html>");
		projectAuthors.setFont(new Font("Tahoma", Font.PLAIN, 15));
		projectAuthors.setBounds(250, 80, 600, 100);
		frame.add(projectAuthors);
		
		//final JPanel panel = new JPanel();
		panel.setBounds(144, 162, 294, 238);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		
		
		
		panel_1.setBounds(10, 11, 564, 540);
		panel_1.add(econMain.panelCreator());
		frame.getContentPane().add(panel_1);
		panel_1.setVisible(false);
		
		
		panel_2.setBounds(10, 11, 564, 540);
		panel_2.add(Modify.panelCreator());
		frame.getContentPane().add(panel_2);
		panel_2.setVisible(false);
		
		q1q3ResultPanel.setBounds(10, 11, 564, 540);
		frame.getContentPane().add(q1q3ResultPanel);
		q1q3ResultPanel.setVisible(false);
		
		frame.getContentPane().add(q2ResultPanel);
		q2ResultPanel.setVisible(false);
		
		frame.getContentPane().add(modPanel);
		modPanel.setVisible(false);
		
		
		
		
		
		JButton btnQueryDatabase = new JButton("Query Database");
		btnQueryDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				projectTitle.setVisible(false);
				projectAuthors.setVisible(false);
				panel_1.setVisible(true);
				//econMain.run();
				//frame.repaint();
			}
		});
		btnQueryDatabase.setBounds(10, 53, 111, 48);
		panel.add(btnQueryDatabase);
		
		JButton btnNewButton = new JButton("Modify Database");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				projectTitle.setVisible(false);
				projectAuthors.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		btnNewButton.setBounds(163, 53, 111, 48);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(89, 139, 111, 48);
		panel.add(btnNewButton_1);
		
		
	}
	
	public static void resultPanelCreator(String date, String data){
		
		q1q3ResultPanel.setBounds(0, 42, 582, 564);
		q1q3ResultPanel.setLayout(null);
		
		JLabel label = new JLabel("Date Chosen:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label.setBounds(30, 72, 150, 39);
		q1q3ResultPanel.add(label);
		
		JLabel label_1 = new JLabel("Results:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_1.setBounds(30, 164, 133, 39);
		q1q3ResultPanel.add(label_1);
		
		final JTextArea txtDataWillGo = new JTextArea();
		System.out.println(" after creation " + txtDataWillGo.getText().toString());
		System.out.println("data: " + data);
		txtDataWillGo.setText(data);
		txtDataWillGo.setBounds(106, 265, 600, 300);
		System.out.println(" after setting text " + txtDataWillGo.getText().toString());
		q1q3ResultPanel.add(txtDataWillGo);
		txtDataWillGo.setColumns(10);
		
		
		JTextField txtDateSelectedGoes = new JTextField();
		txtDateSelectedGoes.setText(date);
		txtDateSelectedGoes.setBounds(197, 85, 325, 22);
		q1q3ResultPanel.add(txtDateSelectedGoes);
		txtDateSelectedGoes.setColumns(10);
		
		JButton home = new JButton("Back to Home");
		home.setBounds(221, 13, 130, 25);
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				q1q3ResultPanel.setVisible(false);
				q1q3ResultPanel.removeAll();
				System.out.println("bef " + txtDataWillGo.getText().toString());
				txtDataWillGo.setText("");
				System.out.println("af "+ txtDataWillGo.getText().toString());
				panel.setVisible(true);
				projectTitle.setVisible(true);
				projectAuthors.setVisible(true);
			}
		});
		q1q3ResultPanel.add(home);
		
		q2ResultPanel.setBounds(0, 42, 582, 564);
		q2ResultPanel.setLayout(null);
		
		JLabel label2 = new JLabel("Data Chosen:");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label2.setBounds(30, 72, 150, 39);
		q2ResultPanel.add(label2);
		
		JLabel label_2 = new JLabel("Results:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_2.setBounds(30, 164, 133, 39);
		q2ResultPanel.add(label_2);
		
		final JTextField txtDataWillGo2 = new JTextField();
		txtDataWillGo2.setText(date);
		txtDataWillGo2.setBounds(106, 265, 400, 200);
		q2ResultPanel.add(txtDataWillGo2);
		txtDataWillGo2.setColumns(10);
		
		JTextArea txtDateSelectedGoes2 = new JTextArea();
		txtDateSelectedGoes2.setText(data);
		txtDateSelectedGoes2.setBounds(197, 85, 325, 22);
		q2ResultPanel.add(txtDateSelectedGoes2);
		txtDateSelectedGoes2.setColumns(10);
		
		JButton home2 = new JButton("Back to Home");
		home2.setBounds(221, 13, 130, 25);
		home2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				q2ResultPanel.setVisible(false);
				q2ResultPanel.removeAll();
				txtDataWillGo2.setText("");
				panel.setVisible(true);
				projectTitle.setVisible(true);
				projectAuthors.setVisible(true);
			}
		});
		
		q2ResultPanel.add(home2);
		
		modPanel.setBounds(0, 42, 582, 564);
		modPanel.setLayout(null);
		
		JButton btnBackToHome = new JButton("Back to Home");
		btnBackToHome.setBounds(256, 13, 130, 25);
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modPanel.setVisible(false);
				panel.setVisible(true);
				projectTitle.setVisible(true);
				projectAuthors.setVisible(true);
			}
		});
		modPanel.add(btnBackToHome);
		
		JLabel lblModificationsMade = new JLabel("Modifications Made");
		lblModificationsMade.setFont(new Font("Tahoma", Font.PLAIN, 48));
		lblModificationsMade.setBounds(88, 187, 423, 127);
		modPanel.add(lblModificationsMade);
		
		
	}
}
