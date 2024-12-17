import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1920, 1080);
		 // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Set frame size to match screen size
        setBounds(0, 0, screenWidth, screenHeight);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel homeLabel = new JLabel("Welcome to Employee Management System");
		homeLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		homeLabel.setBounds(564, 124, 627, 54);
		contentPane.add(homeLabel);
		
		JButton viewEmployeeBtn = new JButton("View Employees");
		viewEmployeeBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		viewEmployeeBtn.setBackground(new Color(64, 128, 128));
		viewEmployeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEmployee viewEmp = new ViewEmployee();
				viewEmp.setVisible(true);
			}
		});
		viewEmployeeBtn.setBounds(438, 254, 182, 83);
		contentPane.add(viewEmployeeBtn);
		
		JButton viewByIdBtn = new JButton("View Employee By Id");
		viewByIdBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		viewByIdBtn.setBackground(new Color(64, 128, 128));
		viewByIdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewById viewEmp = new ViewById();
				viewEmp.setVisible(true);
			}
		});
		viewByIdBtn.setBounds(1012, 254, 166, 83);
		contentPane.add(viewByIdBtn);
		
		JButton addEmployeeBtn = new JButton("Add Employee");
		addEmployeeBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addEmployeeBtn.setBackground(new Color(64, 128, 128));
		addEmployeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployee addEmployee = new AddEmployee();
				addEmployee.setVisible(true);
			}
		});
		addEmployeeBtn.setBounds(438, 403, 182, 76);
		contentPane.add(addEmployeeBtn);
		
		JButton btnNewButton_1 = new JButton("Remove Employee");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBackground(new Color(64, 128, 128));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveEmployee removeEmployee = new RemoveEmployee();
				removeEmployee.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(1012, 403, 166, 78);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update Employee");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateEmployee updateEmp = new UpdateEmployee();
				updateEmp.setVisible(true);
			}
		});
		btnNewButton_2.setBackground(new Color(64, 128, 128));
		btnNewButton_2.setBounds(735, 545, 158, 68);
		contentPane.add(btnNewButton_2);
	}
}
