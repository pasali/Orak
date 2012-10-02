import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JList;

import com.itextpdf.text.DocumentException;


public class merger_gui {

	private JFrame frmLorduV;
	JTextField textField_1;
	static String fileout;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					merger_gui window = new merger_gui();
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
	public merger_gui() {
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
		final DefaultListModel model = new DefaultListModel();
		final JList list = new JList(model);
		
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
				merger dizi = new merger();
				dizi.pdfs.add(file.getAbsolutePath());
				

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
					merger birlestir = new merger();
					
					if(birlestir.pdfs.size() >= 2)
					{
							try {
								birlestir.main();
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
