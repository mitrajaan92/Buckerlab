package maven_quickstart.hibernate_example.nativesql;

import java.util.Arrays;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.NativeQuery;



import maven_quickstart.hibernate_example.util.HibernatePropertiesUtil;

public class SQLExample {
	public static void main(String[] args) {

		

		
		  Session s = HibernatePropertiesUtil.getSessionFactory().openSession();
		  
		  String  q = "select * from student";
		  
		  NativeQuery nq = s.createSQLQuery(q);
		  
		  List<Object[]> list = nq.list();
		  
		  for (Object[] student : list) {
		  System.out.println(student[4]+" : "+student[3]); }
		  
		  s.close(); 
		  
		 

	}
}
