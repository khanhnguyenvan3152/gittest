package xmltest;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentTable extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmNewMenuItem_1;
	private JTextField StudentID;
	private JTextField NameTT;
	private JTable tableStudent;
	private JTextField NameStudent;
	private final ButtonGroup GroupSex = new ButtonGroup();

	public static ArrayList<Student> List = new ArrayList<Student>();
	public static DefaultTableModel model = new DefaultTableModel();
	public static IOcontrol io = new IOcontrol();
	public static XMLIo xmlio = new XMLIo();
	private JTextField NameTinh;
	private JTextField Math;
	private JTextField Physical;
	private JTextField Chemistry;
	private JTextField BirthDay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentTable frame = new StudentTable();
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
	public StudentTable() {
		setTitle("Project : StudentTable");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 926, 519);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		mntmNewMenuItem_1 = new JMenuItem("Open");
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Close");
		mnNewMenu.add(mntmNewMenuItem_2);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_1 = new JMenu("About");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("About Me");
		mnNewMenu_1.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Student Filtre", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(8, 10, 896, 74);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Student ID");
		lblNewLabel.setBounds(26, 25, 100, 24);
		panel.add(lblNewLabel);

		StudentID = new JTextField();
		StudentID.setBounds(134, 28, 86, 19);
		panel.add(StudentID);
		StudentID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("NameTT");
		lblNewLabel_1.setBounds(361, 25, 100, 24);
		panel.add(lblNewLabel_1);

		NameTT = new JTextField();
		NameTT.setBounds(469, 28, 86, 19);
		panel.add(NameTT);
		NameTT.setColumns(10);

		JButton btnNewButton = new JButton("Filtre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Student> a = new ArrayList<Student>();
				String studentID = StudentID.getText();
				String nameTT = NameTT.getText();
				if (studentID.equalsIgnoreCase("") && nameTT.equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "chua dien doi tuong can tim!");
					reload(List);
				} else {
					for (Student student : List) {
						int ID = student.getStudentID();
						// tim theo StudentID va NameTT
						if (!studentID.equalsIgnoreCase("") && !nameTT.equalsIgnoreCase("")) {
							if (String.valueOf(ID).equalsIgnoreCase(studentID)
									&& nameTT.equalsIgnoreCase(student.getNameTT())) {
								a.add(student);
							}
						}
						// tim theo StudentID
						else if (!studentID.equalsIgnoreCase("") && nameTT.equalsIgnoreCase("")) {
							if (String.valueOf(ID).equalsIgnoreCase(studentID)) {
								a.add(student);
							}
						}
						// tim theo NameTinhThanh
						else if (studentID.equalsIgnoreCase("") && !nameTT.equalsIgnoreCase("")) {
							if (nameTT.equalsIgnoreCase(student.getNameTT())) {
								a.add(student);
							}
						}

					}
					reload(a);
				}
			}
		});
		btnNewButton.setBounds(748, 27, 83, 21);
		panel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Student Table", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		scrollPane.setBounds(8, 94, 896, 152);
		contentPane.add(scrollPane);

		tableStudent = new JTable();
		tableStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableStudent.getSelectedRow();
				Student student = List.get(index);
				NameStudent.setText(student.getStudentName());
				NameTinh.setText(student.getNameTT());
				BirthDay.setText(student.getDate());
				Math.setText(String.valueOf(student.getMath()));
				Physical.setText(String.valueOf(student.getPhysical()));
				Chemistry.setText(String.valueOf(student.getChemistry()));

			}
		});
		scrollPane.setViewportView(tableStudent);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Student Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(8, 256, 896, 157);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("NameStudent");
		lblNewLabel_2.setBounds(8, 24, 88, 22);
		panel_1.add(lblNewLabel_2);

		NameStudent = new JTextField();
		NameStudent.setBounds(104, 26, 144, 19);
		panel_1.add(NameStudent);
		NameStudent.setColumns(10);

		JRadioButton RadioButtonMale = new JRadioButton("Male");
		GroupSex.add(RadioButtonMale);
		RadioButtonMale.setBounds(300, 119, 99, 21);
		panel_1.add(RadioButtonMale);

		JRadioButton RadioButtonFemale = new JRadioButton("Female");
		GroupSex.add(RadioButtonFemale);
		RadioButtonFemale.setBounds(405, 119, 99, 21);
		panel_1.add(RadioButtonFemale);

		JLabel lblNewLabel_3 = new JLabel("NameTT");
		lblNewLabel_3.setBounds(273, 27, 88, 17);
		panel_1.add(lblNewLabel_3);

		NameTinh = new JTextField();
		NameTinh.setBounds(405, 26, 144, 19);
		panel_1.add(NameTinh);
		NameTinh.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Math");
		lblNewLabel_4.setBounds(8, 71, 88, 22);
		panel_1.add(lblNewLabel_4);

		Math = new JTextField();
		Math.setBounds(104, 73, 144, 19);
		panel_1.add(Math);
		Math.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Physical");
		lblNewLabel_5.setBounds(273, 71, 88, 22);
		panel_1.add(lblNewLabel_5);

		Physical = new JTextField();
		Physical.setBounds(405, 73, 144, 19);
		panel_1.add(Physical);
		Physical.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Chemistry");
		lblNewLabel_6.setBounds(607, 71, 88, 22);
		panel_1.add(lblNewLabel_6);

		Chemistry = new JTextField();
		Chemistry.setBounds(699, 73, 144, 19);
		panel_1.add(Chemistry);
		Chemistry.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("BirthDay");
		lblNewLabel_7.setBounds(607, 24, 88, 22);
		panel_1.add(lblNewLabel_7);

		BirthDay = new JTextField();
		BirthDay.setBounds(699, 26, 144, 19);
		panel_1.add(BirthDay);
		BirthDay.setColumns(10);

		JButton btnNewButton_1 = new JButton("Th\u00EAm");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestException test = new TestException();
				// kiem tra diem co dung khong
				int ma = 1;
				ma = test.Exception(ma, Math.getText());
				int phy = 1;
				phy = test.Exception(phy, Physical.getText());
				int che = 1;
				che = test.Exception(che, Chemistry.getText());
				if (ma != 0 && phy != 0 && che != 0) {
					boolean Sex = sex();
					String studentName = NameStudent.getText();
					String NameT = NameTinh.getText();
					String BD = BirthDay.getText();
					float math = Float.parseFloat(Math.getText());
					float physical = Float.parseFloat(Physical.getText());
					float chemistry = Float.parseFloat(Chemistry.getText());
					Student student = new Student(NameT, studentName, Sex, BD, math, physical, chemistry);
					List.add(student);
					model.addRow(new Object[] { student.getStudentID(), student.getStudentName(), student.getNameTT(),
							student.getID(), student.getDate(), student.isSex(), student.getMath(),
							student.getPhysical(), student.getChemistry() });
					tableStudent.setModel(model);
				} else {
					JOptionPane.showMessageDialog(null, "Diem phai trong [0,10]");
				}
				deleteTextField();

			}
		});
		btnNewButton_1.setBounds(8, 429, 83, 21);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("S\u1EEDa");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestException test = new TestException();
				int index = tableStudent.getSelectedRow();
				if (List.size() == 0) {
					JOptionPane.showMessageDialog(null, "bang chua co doi tuong!");
				} else if (index == -1) {
					JOptionPane.showMessageDialog(null, "chon mot doi tuong trong bang");
				} else {

					int math = 1;
					math = test.Exception(math, Math.getText());
					int phy = 1;
					phy = test.Exception(phy, Physical.getText());
					int che = 1;
					che = test.Exception(che, Chemistry.getText());

					// Kiem tra boolean sex
					if (math != 0 && phy != 0 && che != 0) {
						Student student = List.get(index);// lay doi tuong co vi tri index trong list
						boolean Sex = sex();
						student.setSex(Sex);
						student.setNameTT(NameTinh.getText());
						student.setStudentName(NameStudent.getText());
						student.setDate(BirthDay.getText());
						student.setMath(Float.parseFloat(Math.getText()));
						student.setPhysical(Float.parseFloat(Physical.getText()));
						student.setChemistry(Float.parseFloat(Chemistry.getText()));
						List.remove(index);
						List.add(index, student);
						// lam moi table va dua du lieu vao bang
						reload(List);
					} else {
						JOptionPane.showMessageDialog(null, "Diem phai trong [0,10]");
					}
				}
				deleteTextField();

			}
		});
		btnNewButton_2.setBounds(300, 429, 83, 21);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("X\u00F3a");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = tableStudent.getSelectedRow();
				if (List.size() == 0) {
					JOptionPane.showMessageDialog(null, "bang chua co doi tuong!");
				} else if (index == -1) {
					JOptionPane.showMessageDialog(null, "chon mot doi tuong trong bang");
				} else {
					List.remove(index);// xoa doi tuong co vi tri index trong list
				}
				reload(List);

			}
		});
		btnNewButton_3.setBounds(572, 429, 83, 21);
		contentPane.add(btnNewButton_3);
		JButton btnNewButton_6 = new JButton("L\u01B0u File");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				io.outputFileStudent(List);
//				io.outputFileTinhThanh(List);
				xmlio.outputFileStudent(List);
				xmlio.outputFileTinhThanh(List);
			}
		});
		btnNewButton_6.setBounds(821, 429, 83, 21);
		contentPane.add(btnNewButton_6);
		getColumn();
//		inputFile();
		inputXML();
	}

	private void getColumn() {
		model.addColumn("StudentID");
		model.addColumn("StudentName");
		model.addColumn("BirthPlace");
		model.addColumn("PlaceID");
		model.addColumn("BirthDay");
		model.addColumn("Sex");
		model.addColumn("Math");
		model.addColumn("Physical");
		model.addColumn("Chemistry");
	}

	private void reload(ArrayList<Student> List) {
		model.setRowCount(0);
		for (Student x : List) {
			model.addRow(new Object[] { x.getStudentID(), x.getStudentName(), x.getID(), x.getNameTT(), x.getDate(),
					x.isSex(), x.getMath(), x.getPhysical(), x.getChemistry() });
		}
		tableStudent.setModel(model);

	}

	private void inputFile() {
		List = io.inputFile();
		for (Student student : List) {
			model.addRow(new Object[] { student.getStudentID(), student.getStudentName(), student.getNameTT(),
					student.getID(), student.getDate(), student.isSex(), student.getMath(), student.getPhysical(),
					student.getChemistry() });
		}
		tableStudent.setModel(model);
	}
	private void inputXML()
	{
		List = xmlio.readxml();
		for(Student student : List)
		{
			model.addRow(new Object[] {student.getStudentID(),student.getStudentName(),student.getNameTT(),student.getID(),student.getDate(),
			student.isSex(),student.getMath(),student.getPhysical(),student.getChemistry()		
			});
		}
		tableStudent.setModel(model);
	}

	private boolean sex() {
		String sex = "";
		Enumeration<AbstractButton> abs = GroupSex.getElements();
		while (abs.hasMoreElements()) {
			JRadioButton JR = (JRadioButton) abs.nextElement();
			if (JR.isSelected()) {
				if (JR.getText().equalsIgnoreCase("male")) {
					sex = "true";
				} else if (JR.getText().equalsIgnoreCase("female")) {
					sex = "false";
				}
			}
		}
		boolean Sex = Boolean.parseBoolean(sex);
		return Sex;
	}

	private void deleteTextField() {
		NameTinh.setText("");
		NameStudent.setText("");
		BirthDay.setText("");
		Math.setText("");
		Physical.setText("");
		Chemistry.setText("");
	}
}
