package maven_quickstart.hibernate_example.criteria;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import maven_quickstart.hibernate_example.util.HibernatePropertiesUtil;

public class CriteriaExample {
	public static void main(String[] args) {
		
		Session s = HibernatePropertiesUtil.getSessionFactory().openSession();
		
		
		Transaction tx = s.beginTransaction(); //Txn Management - ACID compliant
		
		CriteriaBuilder builder = s.getCriteriaBuilder();
		
		CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
		
		Root<Student> root = criteria.from(Student.class);
		
	
		
		criteria.select(root).where(builder.equal(root.get("name"), "John Doe"));
		
		
		
		List<Student> students= s.createQuery(criteria).getResultList();
		
		
		for(Student st:students)
		{
			System.out.println(st);
		}
		
		
		s.close();
	}
}
