package menu2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.w3c.dom.Text;

public class interfaz {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaz window = new interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar_1 = new JMenuBar();
		frame.setJMenuBar(menuBar_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 10, 416, 210);
		frame.getContentPane().add(textArea);


		JMenuItem File, Edit, Help, Copy, Cut, Paste, Selectall, Open, Close, Message;
		JMenu menuBar = new JMenu();
		

		
		File = new JMenu("File");
		Edit = new JMenu("Edit");
		Help = new JMenu("Help");
		Copy = new JMenuItem("Copy");
		Cut = new JMenuItem("Cut");
		Paste = new JMenuItem("Paste");
		Close = new JMenuItem("Close");
		Message = new JMenuItem("Message");
		
		ImageIcon icono = new ImageIcon("C:\\Users\\ALUMNOS_FP\\Downloads\\rerer.png");
		Message.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] opciones = {"Si", "No"};
				
				int option = JOptionPane.showOptionDialog(null, "¿Quieres una Moster?", 
						"Moster.exe🤙🤙", 
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.WARNING_MESSAGE,
						icono,
						opciones, 
						opciones[1]);
			}
		});
		Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		Selectall = new JMenuItem("Select all");
		Open = new JMenuItem("Open");
		Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", new String[] { "txt" });
				fc.addChoosableFileFilter(filter);
				int result = fc.showOpenDialog(null);
				fc.setAcceptAllFileFilterUsed(false);

				if (result == JFileChooser.APPROVE_OPTION) {
					BufferedReader buffer = null;
					try {
						File f = fc.getSelectedFile();
						buffer = new BufferedReader(new FileReader(f));
						String line;
						String text = "";

						while ((line = buffer.readLine()) != null) {
							text += line + "\n";
						}
						textArea.setText(text);
					} catch (IOException r) {
						r.getMessage();
					}
				}
			}
		});
		
		ActionListener editorAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuItem item = (JMenuItem)e.getSource();
				if(item.getText().equals("Cut"))
				textArea.cut();
			else if (item.getText().equals("Paste"))
				textArea.paste();
			else if (item.getText().equals("Copy"))
				textArea.copy();
			else if (item.getText().equals("Select all"))
				textArea.selectAll();
			}
		};
		
		Copy.addActionListener(editorAction);
		Paste.addActionListener(editorAction);
		Cut.addActionListener(editorAction);
		Selectall .addActionListener(editorAction);


		menuBar_1.add(File);
		menuBar_1.add(Edit);
		menuBar_1.add(Help);
		File.add(Open);
		Edit.add(Copy);
		Edit.add(Paste);
		Edit.add(Cut);
		Edit.add(Selectall);
		Help.add(Close);
		Help.add(Message);

		frame.setJMenuBar(menuBar_1);
		frame.getContentPane().setLayout(null);

	}
}
