package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CakeController;
import controller.UserController;
import model.Cake;
import model.User;

public class UserView extends JFrame implements ActionListener{
	JTable table;
	DefaultTableModel data;
	JButton buyButton;
	JTextField idField;
	JLabel idLabel;
	User user;
	
	Vector<Object> header = new Vector<>();
	
	public UserView(User user) {
		this.user = user;
		buyButton = new JButton("Buy Cake");
		buyButton.addActionListener(this);
		idField = new JTextField();
		idLabel = new JLabel("ID to buy: ");
		
		JPanel inputPanel = new JPanel(new GridLayout(1,2));
		inputPanel.add(idLabel);
		inputPanel.add(idField);
		
		JPanel mainPanel = new JPanel(new GridLayout(3,1));
		mainPanel.add(inputPanel, BorderLayout.NORTH);
		mainPanel.add(buyButton, BorderLayout.SOUTH);

		Vector<Object> header = new Vector<>();
		header.add("Cake ID");
		header.add("Cake Name");
		header.add("Cake Price");
		header.add("Cake Status");
		header.add("Cake Buyer");
		
		data = new DefaultTableModel(header, 0);
		setData();
		
		table = new JTable(data) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane, BorderLayout.CENTER);
		add(mainPanel, BorderLayout.SOUTH);
		
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public void setData() {
		while(data.getRowCount() > 0) {
			data.removeRow(0);
		}
		Vector<Cake> cakeList = CakeController.getAllCake();
		for (Cake cake : cakeList) {
			Vector<Object> rowData = new Vector<>();
			rowData.add(cake.getId());
			rowData.add(cake.getName());
			rowData.add(cake.getPrice());
			rowData.add(cake.getStatus());
			rowData.add(cake.getBuyer());
			data.addRow(rowData);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buyButton) {
			int id = Integer.parseInt(idField.getText());
			String username = user.getUsername();
		
			if(!CakeController.setStatus(id, username)){
				System.err.println("Cannot buy cake!");
			}
			idField.setText("");
			setData();
		}
	}
	
}