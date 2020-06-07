package xmltest;

public class Student extends TinhThanh {
	private int StudentID;
	private String StudentName;
	private int ID;
	private boolean Sex;
	private String Date;
	private float Math;
	private float Physical;
	private float Chemistry;
	
	public static int SID=10000;
	
	
	public Student(String NameTT,String StudentName,boolean Sex,String Date, float Math,float Physical,float Chemistry) {
		super(NameTT);
		this.StudentName =StudentName;
		this.StudentID = SID;
		SID++;
		this.ID = this.getIDTT();
		this.Sex = Sex;
		this.Date=Date;
		this.Math = Math;
		this.Physical = Physical;
		this.Chemistry = Chemistry;
	}

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public int getID() {
		return this.getIDTT();
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isSex() {
		return Sex;
	}

	public void setSex(boolean sex) {
		Sex = sex;
	}

	public float getMath() {
		return Math;
	}

	public void setMath(float math) {
		Math = math;
	}

	public float getPhysical() {
		return Physical;
	}

	public void setPhysical(float physical) {
		Physical = physical;
	}

	public float getChemistry() {
		return Chemistry;
	}

	public void setChemistry(float chemistry) {
		Chemistry = chemistry;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
	
	
	

}
