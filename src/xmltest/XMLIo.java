package xmltest;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class XMLIo{
	private static ArrayList<Student> list = new ArrayList<Student>();
	private ArrayList<TinhThanh> plist = readProvince();
	public ArrayList<Student> readxml() 
	{
		File file = new File("thisinh.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc;
			try {
				doc = db.parse(file);
				doc.getDocumentElement().normalize();
				System.out.println("Root element:" +doc.getDocumentElement().getNodeName());
				NodeList nodelist = doc.getElementsByTagName("thisinh");
				for(int itr = 0; itr < nodelist.getLength(); itr++)
				{
					Node node = nodelist.item(itr);
					System.out.println("\nNode Name: " + node.getNodeName());
					if(node.getNodeType() == Node.ELEMENT_NODE)
					{
						Element eElement = (Element) node;
						int ma = Integer.parseInt(eElement.getElementsByTagName("mathisinh").item(0).getTextContent());
						String name = eElement.getElementsByTagName("ten").item(0).getTextContent();
						int pid = Integer.parseInt(eElement.getElementsByTagName("matinh").item(0).getTextContent());
						String date = eElement.getElementsByTagName("ngaysinh").item(0).getTextContent();
						String namett="";
						for(TinhThanh p:plist)
						{
							if(p.getIDTT()==pid) namett=p.getNameTT(); 
						}
						boolean gender = Boolean.parseBoolean(eElement.getElementsByTagName("gioitinh").item(0).getTextContent());
						float math = Float.parseFloat(eElement.getElementsByTagName("toan").item(0).getTextContent());
						float chemistry = Float.parseFloat(eElement.getElementsByTagName("hoa").item(0).getTextContent());
						float physics = Float.parseFloat(eElement.getElementsByTagName("ly").item(0).getTextContent());
						Student s = new Student(namett,name,gender,date,math,physics,chemistry);
						s.setStudentID(ma);;
						list.add(s);
					}
				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public ArrayList<TinhThanh> readProvince() 
	{
		ArrayList<TinhThanh> plist = new ArrayList<TinhThanh>();
		File file = new File("quequan.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc;
			try {
				doc = db.parse(file);
				doc.getDocumentElement().normalize();
				System.out.println("Root element:" +doc.getDocumentElement().getNodeName());
				NodeList nodelist = doc.getElementsByTagName("quequan");
				for(int itr = 0; itr < nodelist.getLength(); itr++)
				{
					Node node = nodelist.item(itr);
					System.out.println("\nNode Name: " + node.getNodeName());
					if(node.getNodeType() == Node.ELEMENT_NODE)
					{
						Element eElement = (Element) node;
						String name = eElement.getElementsByTagName("tentinh").item(0).getTextContent();
						TinhThanh e = new TinhThanh(plist.size()+1,name);
						plist.add(e);
					}
				}
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return plist ;
	}
	public void outputFileStudent(ArrayList<Student> list)
	{
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			//Tao the danh sach
			Element danhsach = doc.createElement("danhsach");
			doc.appendChild(danhsach);
			for(Student x: list)
			{
				//Tao the thi sinh
				Element thisinh = doc.createElement("thisinh");
				danhsach.appendChild(thisinh);
				//Tao the con ma thi sinh
				Element mathisinh = doc.createElement("mathisinh");
				mathisinh.appendChild(doc.createTextNode(String.valueOf(x.getStudentID())));
				thisinh.appendChild(mathisinh);
				//Tao the con ten
				Element ten = doc.createElement("ten");
				ten.appendChild(doc.createTextNode(x.getStudentName()));
				thisinh.appendChild(ten);
				//Tao the ma tinh
				Element matinh = doc.createElement("matinh");
				matinh.appendChild(doc.createTextNode(String.valueOf(x.getIDTT())));
				thisinh.appendChild(matinh);
				//Tao the ngaysinh
				Element ngaysinh = doc.createElement("ngaysinh");
				ngaysinh.appendChild(doc.createTextNode(x.getDate()));
				thisinh.appendChild(ngaysinh);
				//Tao the gioi tinh
				Element gioitinh = doc.createElement("gioitinh");
				gioitinh.appendChild(doc.createTextNode(String.valueOf(x.isSex())));
				thisinh.appendChild(gioitinh);
				//Tao the toan
				Element toan = doc.createElement("toan");
				toan.appendChild(doc.createTextNode(String.valueOf(x.getMath())));
				thisinh.appendChild(toan);
				//Tao the ly
				Element ly = doc.createElement("ly");
				ly.appendChild(doc.createTextNode(String.valueOf(x.getPhysical())));
				thisinh.appendChild(ly);
				//Tao the hoa
				Element hoa = doc.createElement("hoa");
				hoa.appendChild(doc.createTextNode(String.valueOf(x.getChemistry())));
				thisinh.appendChild(hoa);
			}
			 TransformerFactory transformerFactory = TransformerFactory.newInstance();
		     Transformer transformer;
			try {
				transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				 
			     File f = new File("Student.xml");
			     StreamResult result = new StreamResult(f);
			     try {
					transformer.transform(source, result);
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void outputFileTinhThanh(ArrayList<Student> list)
	{
		for(Student x:list)
		{
			TinhThanh tmp = new TinhThanh(x.getIDTT(),x.getNameTT());
			if(plist.contains(tmp)==false)
			{
				TinhThanh e = new TinhThanh(plist.size()+1,x.getNameTT());
				plist.add(e);
			}
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			//Tao the danh sach
			Element danhsach = doc.createElement("danhsach");
			doc.appendChild(danhsach);
			for(TinhThanh p: plist)
			{
				Element quequan = doc.createElement("quequan");
				danhsach.appendChild(quequan);
				
				Element matinh = doc.createElement("quequan");
				matinh.appendChild(doc.createTextNode(String.valueOf(p.getIDTT())));
				quequan.appendChild(matinh);
				
				Element tentinh = doc.createElement("tentinh");
				tentinh.appendChild(doc.createTextNode(p.getNameTT()));
				quequan.appendChild(tentinh);
			}
			 TransformerFactory transformerFactory = TransformerFactory.newInstance();
		     Transformer transformer;
			try {
				transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				 
			     File f = new File("tinhthanh.xml");
			     StreamResult result = new StreamResult(f);
			     try {
					transformer.transform(source, result);
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
