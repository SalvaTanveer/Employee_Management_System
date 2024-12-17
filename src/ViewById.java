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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class ViewById extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String displayName,displayEmail,displayPhone,displayAddress;
	int displaySalary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewById frame = new ViewById();
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
	public ViewById() {
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
		
		JLabel lblNewLabel = new JLabel("EMPLOYEES DETAILS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(625, 42, 322, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Employee ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBackground(new Color(64, 128, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(453, 143, 166, 40);
		contentPane.add(lblNewLabel_1);
		
		JTextField textFieldId = new JTextField();
		textFieldId.setBounds(758, 153, 300, 32);
		contentPane.add(textFieldId);
		
		JCheckBox id = new JCheckBox("ID");
		id.setBackground(new Color(64, 128, 128));
		id.setFont(new Font("Tahoma", Font.PLAIN, 15));
		id.setBounds(494, 277, 96, 32);
		contentPane.add(id);
		
		JCheckBox name = new JCheckBox("Name");
		name.setBackground(new Color(64, 128, 128));
		name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		name.setBounds(743, 277, 101, 32);
		contentPane.add(name);
		
		JCheckBox salary = new JCheckBox("Salary");
		salary.setBackground(new Color(64, 128, 128));
		salary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		salary.setBounds(996, 277, 118, 32);
		contentPane.add(salary);
		
		JCheckBox email = new JCheckBox("Email");
		email.setBackground(new Color(64, 128, 128));
		email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		email.setBounds(494, 365, 96, 32);
		contentPane.add(email);
		
		JCheckBox phone = new JCheckBox("Phone");
		phone.setBackground(new Color(64, 128, 128));
		phone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		phone.setBounds(743, 365, 101, 32);
		contentPane.add(phone);
		
		JCheckBox address = new JCheckBox("Address");
		address.setBackground(new Color(64, 128, 128));
		address.setFont(new Font("Tahoma", Font.PLAIN, 15));
		address.setBounds(996, 353, 118, 32);
		contentPane.add(address);
		
		JTextArea area = new JTextArea();
		area.setBackground(new Color(192, 192, 192));
		area.setFont(new Font("Tahoma", Font.PLAIN, 20));
		area.setBounds(568, 538, 516, 253);
		contentPane.add(area);
		
		JButton details = new JButton("Get Details");
		details.setBackground(new Color(64, 128, 128));
		details.setFont(new Font("Tahoma", Font.PLAIN, 15));
		details.setBounds(716, 451, 160, 32);
		contentPane.add(details);
		
		details.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 area.setText("");
				String fieldId = textFieldId.getText();
				
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String path = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/julyjdbc?user=root&password=root";
				String sql = "select name,salary,email,phone,address from Employee where id = ?";
				
				try {
					Class.forName(path);
					con = DriverManager.getConnection(url);
					ps = con.prepareStatement(sql);
					ps.setString(1,fieldId);
					
					rs = ps.executeQuery();
					textFieldId.setText("");
					if(rs.next()) {
						displayName = rs.getString("name");
						displaySalary = rs.getInt("salary");
						displayEmail = rs.getString("email");
						displayPhone = rs.getString("phone");
						displayAddress = rs.getString("address");
					}
					if(id.isSelected()) {
						area.setText("Employee ID: "+fieldId+" \n");
						id.setSelected(false);
					}
					if(name.isSelected()) {
						area.append("Employee Name: "+displayName+" \n");
						name.setSelected(false);
					}
					if(salary.isSelected()) {
						area.append("Employee Salary: "+displaySalary+" \n");
						salary.setSelected(false);
					}
					if(email.isSelected()) {
						area.append("Employee Email: "+displayEmail+" \n");
						email.setSelected(false);
					}
					if(phone.isSelected()) {
						area.append("Employee Phone: "+displayPhone+" \n");
						phone.setSelected(false);
					}
					if(address.isSelected()) {
						area.append("Employee Address: "+displayAddress);
						address.setSelected(false);
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
		
		
	}
}
