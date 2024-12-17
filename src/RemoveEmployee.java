import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class RemoveEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveEmployee frame = new RemoveEmployee();
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
	public RemoveEmployee() {
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
		
		JLabel lblNewLabel = new JLabel("REMOVE EMPLOYEE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(593, 81, 462, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Emp_ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(548, 200, 182, 42);
		contentPane.add(lblNewLabel_1);
		
		id = new JTextField();
		id.setBounds(767, 200, 274, 33);
		contentPane.add(id);
		id.setColumns(10);
		
		JButton btnNewButton = new JButton("Remove Employee");
		btnNewButton.setBackground(new Color(64, 128, 128));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(660, 308, 190, 42);
		contentPane.add(btnNewButton);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(192, 192, 192));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(563, 422, 406, 159);
		contentPane.add(textArea);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String path = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/julyjdbc?user=root&password=root";
				String sql = "select name from Employee where id = ?";
				String fieldId = id.getText();
				try {
					Class.forName(path);
					con = DriverManager.getConnection(url);
					ps = con.prepareStatement(sql);
					ps.setString(1, fieldId);
					rs = ps.executeQuery();
					if(rs.next()) {
						String fieldName = rs.getString("name");
						textArea.setText("Employee ID: "+fieldId+" \nEmployee Name: "+fieldName+" \n");
						id.setText("");
					}
					String query = "delete from Employee where id = ?";
					ps = con.prepareStatement(query);
					ps.setString(1, fieldId);
					int rowsEffected = ps.executeUpdate();
					if(rowsEffected > 0) {
						textArea.append("Employee Removed Successfully from Database.");
					}
					

					
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {
					try {
						con.close();
						ps.close();
						rs.close();
						
					}catch(Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
	}
}
