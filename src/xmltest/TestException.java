package xmltest;

public class TestException {
	public void Test(float x) {
		if(x<0 || x>10) {
			throw new ArithmeticException("Diem trong [0,10]");
		}
	}
	public int Exception(int x, String a) {
		try {
			Float.parseFloat(a);
			Test(Float.parseFloat(a));
		} catch (Exception e) {
			x = 0;
		}
		return x;
	}
}
