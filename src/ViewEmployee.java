import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ViewEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEmployee frame = new ViewEmployee();
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
	public ViewEmployee() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lblNewLabel = new JLabel("Employee Details");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(572, 87, 402, 51);
		contentPane.add(lblNewLabel);
		
		String[] columns = {"ID", "Name","Salary"};
		DefaultTableModel tableModel = new DefaultTableModel(columns,0);
		String path = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/julyjdbc?user=root&password=root";
        String sql = "SELECT id, name, salary FROM Employee";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
        	Class.forName(path);
        	con = DriverManager.getConnection(url);
        	ps = con.prepareStatement(sql);
        	rs = ps.executeQuery();
        	while(rs.next()) {
        		String id = rs.getString("id");
        		String name = rs.getString("name");
        		String salary = rs.getString("salary");
        		tableModel.addRow(new Object[] {id,name,salary});
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	try {
        		con.close();
        		ps.close();
        		rs.close();
        	}catch(Exception e1) {
        		e1.printStackTrace();
        	}
        }
        
        
        JTable table = new JTable(tableModel);
        table.setBackground(new Color(192, 192, 192));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        table.setRowHeight(30);
        
        
        int rowCount = tableModel.getRowCount();
        int tableHeight = rowCount > 0 ? rowCount * table.getRowHeight() : 1; // Minimum height for empty table
        table.setPreferredScrollableViewportSize(new Dimension(screenWidth - 200, tableHeight));
        
        
        TableColumn column;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(150); // Adjust width as needed
        }
        
      
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(452, 193, 821, 539); // Adjust bounds as needed
        
        if (tableModel.getRowCount() > 0) {
            scrollPane.getViewport().setBackground(Color.WHITE);
        } else {
            scrollPane.getViewport().setBackground(contentPane.getBackground());
        }
        
        contentPane.add(scrollPane);
	}
}
