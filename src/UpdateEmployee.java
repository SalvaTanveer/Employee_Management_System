import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class UpdateEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTextField salary;
	private JTextField email;
	private JTextField phone;
	private JTextField address;
	String fieldId,fieldName,fieldEmail,fieldPhone,fieldAddress, path,url;
	int fieldSalary;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEmployee frame = new UpdateEmployee();
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
	public UpdateEmployee() {
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
		
		JLabel lblNewLabel = new JLabel("Update Employee Detail");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(463, 62, 521, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Emp_ID to update");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(388, 132, 236, 55);
		contentPane.add(lblNewLabel_1);
		
		id = new JTextField();
		id.setBounds(792, 143, 258, 38);
		contentPane.add(id);
		id.setColumns(10);
		
		JCheckBox chkName = new JCheckBox("Name");
		chkName.setBackground(new Color(64, 128, 128));
		chkName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chkName.setBounds(428, 244, 118, 47);
		contentPane.add(chkName);
		
		JCheckBox chkSalary = new JCheckBox("Salary");
		chkSalary.setBackground(new Color(64, 128, 128));
		chkSalary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chkSalary.setBounds(428, 325, 118, 47);
		contentPane.add(chkSalary);
		
		JCheckBox chkEmail = new JCheckBox("Email");
		chkEmail.setBackground(new Color(64, 128, 128));
		chkEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chkEmail.setBounds(428, 407, 118, 47);
		contentPane.add(chkEmail);
		
		JCheckBox chkPhone = new JCheckBox("Phone");
		chkPhone.setBackground(new Color(64, 128, 128));
		chkPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chkPhone.setBounds(428, 497, 118, 47);
		contentPane.add(chkPhone);
		
		JCheckBox chkAddress = new JCheckBox("Address");
		chkAddress.setBackground(new Color(64, 128, 128));
		chkAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chkAddress.setBounds(428, 590, 118, 47);
		contentPane.add(chkAddress);
		
		name = new JTextField();
		name.setBounds(749, 252, 317, 35);
		contentPane.add(name);
		name.setColumns(10);
		
		salary = new JTextField();
		salary.setColumns(10);
		salary.setBounds(749, 325, 317, 35);
		contentPane.add(salary);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(749, 415, 317, 35);
		contentPane.add(email);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(749, 497, 317, 35);
		contentPane.add(phone);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(749, 589, 317, 35);
		contentPane.add(address);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.setBackground(new Color(64, 128, 128));
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		updateBtn.setBounds(585, 667, 205, 47);
		contentPane.add(updateBtn);
		
		
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		submit.setBackground(new Color(64, 128, 128));
		submit.setBounds(1188, 143, 110, 35);
		contentPane.add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fieldId = id.getText();
				
				path = "com.mysql.cj.jdbc.Driver";
				url = "jdbc:mysql://localhost:3306/julyjdbc?user=root&password=root";
				String sql = "select name,salary,email,phone,address from Employee where id = ?";
				
				try {
					Class.forName(path);
					con = DriverManager.getConnection(url);
					ps = con.prepareStatement(sql);
					ps.setString(1,fieldId);
					rs = ps.executeQuery();
					if(rs.next()) {
						fieldName = rs.getString("name");
						 fieldSalary = rs.getInt("salary");
						fieldEmail = rs.getString("email");
						fieldPhone = rs.getString("phone");
						fieldAddress = rs.getString("address");
						
						name.setText(fieldName);
						
						salary.setText(fieldSalary+"");
						email.setText(fieldEmail);
						phone.setText(fieldPhone);
						address.setText(fieldAddress);
					}
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(chkName.isEnabled()) {
						fieldName = name.getText();
					}
					if(chkSalary.isEnabled()) {
						fieldSalary = Integer.parseInt(salary.getText());
					}
					if(chkEmail.isEnabled()) {
						fieldEmail = email.getText();
					}
					if(chkPhone.isEnabled()) {
						fieldPhone = phone.getText();
					}
					if(chkAddress.isEnabled()) {
						fieldAddress = address.getText();
					}
					String query = "update Employee set name=?, salary=?, email=?,phone=?,address=? where id = ?";
					ps = con.prepareStatement(query);
					ps.setString(1, fieldName);
					ps.setInt(2, fieldSalary);
					ps.setString(3, fieldEmail);
					ps.setString(4, fieldPhone);
					ps.setString(5, fieldAddress);
					ps.setString(6, fieldId);
					
					int numOfRowsUpdated = ps.executeUpdate();
					if(numOfRowsUpdated > 0) {
						JOptionPane.showMessageDialog(contentPane,"Details updated successfully.");
						id.setText("");
						name.setText("");
						salary.setText("");
						email.setText("");
						phone.setText("");
						address.setText("");
						if(chkName.isSelected()) {
							chkName.setSelected(false);
						}
						if(chkSalary.isSelected()) {
							chkSalary.setSelected(false);
						}
						if(chkEmail.isSelected()) {
							chkEmail.setSelected(false);
						}
						if(chkPhone.isSelected()) {
							chkPhone.setSelected(false);
						}
						if(chkAddress.isSelected()) {
							chkAddress.setSelected(false);
						
						}
					}
					
				
				}catch(Exception e2){
					e2.printStackTrace();
				}finally {
					try {
						con.close();
						ps.close();
						rs.close();
					}catch(Exception e3) {
						e3.printStackTrace();
					}
				}
			}
		});
	}
}
