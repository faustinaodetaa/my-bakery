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

public class AdminView extends JFrame implements ActionListener{

	JTable table;
	DefaultTableModel data;
	JButton insertButton, deleteButton;
	JTextField nameField, priceField, idField;
	JLabel nameLabel, priceLabel, idLabel;
	User user;
	
	public AdminView(User user) {
		this.user = user;
		insertButton = new JButton("Insert");
		deleteButton = new JButton("Delete");
		insertButton.addActionListener(this);
		deleteButton.addActionListener(this);
		nameField = new JTextField();
		priceField = new JTextField();
		idField = new JTextField();
		nameLabel = new JLabel("Insert Cake Name: ");
		priceLabel = new JLabel("Insert Cake Price: ");
		idLabel = new JLabel("ID to delete: ");
		
		JPanel inputPanel = new JPanel(new GridLayout(3,2));
		inputPanel.add(nameLabel);
		inputPanel.add(nameField);
		inputPanel.add(priceLabel);
		inputPanel.add(priceField);
		inputPanel.add(idLabel);
		inputPanel.add(idField);
		
		JPanel mainPanel = new JPanel(new GridLayout(3,1));
		mainPanel.add(inputPanel, BorderLayout.NORTH);
		mainPanel.add(insertButton, BorderLayout.SOUTH);
		mainPanel.add(deleteButton, BorderLayout.SOUTH);

		Vector<Object> header = new Vector<>();
		header.add("Cake ID");
		header.add("Cake Name");
		header.add("Cake Price");
		header.add("Cake Status");
		
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
			data.addRow(rowData);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertButton) {
			String name = nameField.getText();
			int price = Integer.parseInt(priceField.getText());
		
			if(!CakeController.insertCake(user.getId(), name, price)){
				System.err.println("Cannot insert cake!");
			}
			nameField.setText("");
			priceField.setText("");
			setData();
		}else if(e.getSource() == deleteButton) {
			int id = Integer.parseInt(idField.getText());
			if(!CakeController.deleteCake(id)) {
				System.err.println("Cannot delete cake!");
			}
			idField.setText("");
			setData();
		}
	}
}

