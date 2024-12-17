import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class AddEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee frame = new AddEmployee();
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
	public AddEmployee() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lblNewLabel = new JLabel("ADD NEW EMPLOYEE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(580, 104, 339, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Emp_ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(384, 203, 219, 44);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Emp_Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(384, 280, 219, 44);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("EMP_Salary");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(384, 351, 219, 44);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Emp_Email");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(384, 430, 219, 44);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Emp_Phone");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(384, 504, 219, 44);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Emp_Address");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_5.setBounds(384, 574, 219, 44);
		contentPane.add(lblNewLabel_1_5);
		
		JTextField id = new JTextField();
		id.setBounds(613, 203, 359, 34);
		contentPane.add(id);
		
		JTextField name = new JTextField();
		name.setBounds(613, 280, 359, 34);
		contentPane.add(name);
		
		JTextField salary = new JTextField();
		salary.setBounds(613, 363, 359, 34);
		contentPane.add(salary);
		
		JTextField email = new JTextField();
		email.setBounds(613, 442, 359, 34);
		contentPane.add(email);
		
		JTextField phone = new JTextField();
		phone.setBounds(613, 516, 359, 34);
		contentPane.add(phone);
		
		JTextField address = new JTextField();
		address.setBounds(613, 586, 359, 34);
		contentPane.add(address);
		
		JButton btnNewButton = new JButton("Add Employee");
		btnNewButton.setBackground(new Color(64, 128, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fieldId = id.getText();
				String fieldName = name.getText();
				int fieldSalary = Integer.parseInt(salary.getText());
				String fieldEmail = email.getText();
				String fieldPhone = phone.getText();
				String fieldAddress = address.getText();
				Connection con = null;
				PreparedStatement ps = null;
				String path = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/julyjdbc?user=root&password=root";
				String sql = "insert into Employee values(?,?,?,?,?,?)";
				Employee emp = new Employee(fieldId,fieldName,fieldSalary,fieldEmail,fieldPhone,fieldAddress);
				try {
					Class.forName(path);
					con = DriverManager.getConnection(url);
					ps = con.prepareStatement(sql);
					ps.setString(1,emp.id);
					ps.setString(2, emp.name);
					ps.setInt(3, emp.salary);
					ps.setString(4, emp.email);
					ps.setString(5, emp.phone);
					ps.setString(6, emp.address);
					int numOfRowsEffected = ps.executeUpdate();
					
					if(numOfRowsEffected > 0) {
						JOptionPane.showMessageDialog(contentPane,"Details inserted successfully.");
						id.setText("");
						name.setText("");
						salary.setText("");
						email.setText("");
						phone.setText("");
						address.setText("");
					}else {
						JOptionPane.showMessageDialog(contentPane,"Some error occurred.Try inserting again.");
					}
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {
					try {
						con.close();
						ps.close();
						
					}catch(Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(504, 656, 214, 44);
		contentPane.add(btnNewButton);
	}

}
