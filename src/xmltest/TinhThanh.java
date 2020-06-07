package xmltest;

public class TinhThanh {
	private int IDTT;
	private String NameTT;
	
	public static int ID=1;
	
	public TinhThanh(String Name) {
		this.NameTT = Name;
		this.IDTT = ID;
		ID++;
	}
	public TinhThanh(int id, String Name)
	{
		this.IDTT = id;
		this.NameTT = Name;
	}

	public int getIDTT() {
		return IDTT;
	}

	public void setIDTT(int iDTT) {
		IDTT = iDTT;
	}

	public String getNameTT() {
		return NameTT;
	}

	public void setNameTT(String nameTT) {
		NameTT = nameTT;
	}

}
