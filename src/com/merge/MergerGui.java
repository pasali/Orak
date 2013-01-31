package com.merge;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.itextpdf.text.DocumentException;

public class MergerGui {

	
	private JFrame frmLorduV;
	private JTextField textField_1;
	private  String fileout;
	private DefaultListModel model = new DefaultListModel();
	private JList list = new JList(model);
	private Merger merge = new Merger();
	
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MergerGui window = new MergerGui();
					window.frmLorduV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MergerGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmLorduV = new JFrame();
		frmLorduV.setResizable(false);
		frmLorduV.setTitle("Lordu v0.1");
		frmLorduV.setBounds(100, 100, 322, 261);
		frmLorduV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Kaldır");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel dlm = (DefaultListModel) list.getModel();
				int count = list.getSelectedIndices().length;
				for (int i = 0; i < count; i++){
				     dlm.removeElementAt(list.getSelectedIndex());
				}
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		JLabel lblYeniDosyaIsmi = new JLabel("Yeni Dosya ismi:");
		final JButton btnGzat = new JButton("Gözat");
		btnGzat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(btnGzat);
				fc.setMultiSelectionEnabled(true);
				File file  = fc.getSelectedFile();
				int index = list.getModel().getSize();
				model.add(index,file.getName());
				merge.addPDFs(file.getAbsolutePath());				

			}
		});
		
		JButton btnBirletir = new JButton("Birleştir");
		btnBirletir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileout = textField_1.getText();
				if(fileout == null | fileout.equals(""))
				{
					JOptionPane.showMessageDialog(frmLorduV, "Yeni dosya adını girmediniz !!!");
				}
				else
				{
					if(merge.NumberOfPDFs() >= 2)
					{
							try {
								merge.mergePdfs(fileout);
							} catch (DocumentException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						
					}
					else
					{
						JOptionPane.showMessageDialog(frmLorduV, "En az iki dosya eklemelisiniz !!!");

					}
				}
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmLorduV.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnGzat)
								.addComponent(btnNewButton)
								.addComponent(btnBirletir)))
						.addComponent(lblYeniDosyaIsmi))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(btnGzat)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblYeniDosyaIsmi)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(btnBirletir))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		frmLorduV.getContentPane().setLayout(groupLayout);
	}
	
	
	
}
