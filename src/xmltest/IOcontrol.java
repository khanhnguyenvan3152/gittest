package xmltest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IOcontrol {
	public void outputFileStudent(ArrayList<Student> List) {
		try(PrintWriter pr = new PrintWriter(new File("Student.txt"))){//luu vao file Student.txt
			for(Student x:List) {//chay qua tung doi tuong trong list
				pr.print(x.getStudentID()+",");
				pr.print(x.getStudentName()+",");
				pr.print(x.getID()+",");
				pr.print(x.getDate()+",");
				pr.print(x.isSex()+",");
				pr.print(x.getMath()+",");
				pr.print(x.getPhysical()+",");
				pr.print(x.getChemistry()+"\n");
				
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("loi luu file!");
		}
	}
	public void outputFileTinhThanh(ArrayList<Student> List) {
		try(PrintWriter pr = new PrintWriter(new File("TinhThanh.txt"))){//luu vao file Student.txt
			for(Student x:List) {//chay qua tung doi tuong trong list
				pr.print(x.getNameTT()+",");
				pr.print(x.getID()+"\n");
			}
		}
		catch(FileNotFoundException e) {
			System.err.println("loi luu file!");
		}
	}
	public ArrayList<Student> inputFile(){
		ArrayList<Student> list = new ArrayList<Student>();
		try(Scanner sc = new Scanner(new File("SV.txt"))){//quet den file SV.txt
			while(sc.hasNextLine()) {
				String name = sc.nextLine();
				String nameTT = sc.nextLine();
				String Date= sc.nextLine();
				boolean sex = Boolean.parseBoolean(sc.nextLine());
				float math = Float.parseFloat(sc.nextLine());
				float phy = Float.parseFloat(sc.nextLine());
				float che = Float.parseFloat(sc.nextLine());
			    Student student = new Student(nameTT,name,sex,Date,math,phy,che);
				list.add(student);
			}
		}
		catch(FileNotFoundException e) {
			System.err.println("loi doc file");
		}
		return list;
	}

}
