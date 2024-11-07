package maven_quickstart.hibernate_example.entity.manytomany;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import maven_quickstart.hibernate_example.util.HibernatePropertiesUtil;

public class MappingDemo {
	public static void main(String[] args) {
		
		Session session3 = HibernatePropertiesUtil.getSessionFactory().openSession();

		Emp e1 = new Emp();
		Emp e2 = new Emp();

		e1.setEid(34);
		e1.setName("Ram");

		e2.setEid(35);
		e2.setName("Shyam");

		Project p1 = new Project();
		Project p2 = new Project();

		p1.setPid(12121);
		p1.setProjectName("Library management system");
		p2.setPid(14214);
		p2.setProjectName("CHATBOT");

		List<Emp> list1 = new ArrayList<Emp>();
		List<Project> list2 = new ArrayList<Project>();

		list1.add(e1);
		list1.add(e2);

		list2.add(p1);
		list2.add(p2);

		//
		e1.setProjects(list2);
		p2.setEmps(list1);

		
		Transaction tx = session3.beginTransaction();

		session3.save(e1);
		session3.save(e2);
		session3.save(p1);
		session3.save(p2);

		tx.commit();
		session3.close();

		
	}
}
