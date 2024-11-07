package maven_quickstart.hibernate_example.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import maven_quickstart.hibernate_example.criteria.Student;
import maven_quickstart.hibernate_example.entity.Customer;
import maven_quickstart.hibernate_example.entity.Department;
import maven_quickstart.hibernate_example.entity.Employee;
import maven_quickstart.hibernate_example.entity.manytomany.Emp;
import maven_quickstart.hibernate_example.entity.manytomany.Project;
import maven_quickstart.hibernate_example.entity.onetomany.Answer;
import maven_quickstart.hibernate_example.entity.onetomany.Question;

public class HibernatePropertiesUtil {
	
	private static SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		
		try {
			
			StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
			
			StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder.build();
			
			MetadataSources sources = new MetadataSources(standardServiceRegistry);
			sources.addAnnotatedClass(Employee.class); // adding entity to metadata sources
			sources.addAnnotatedClass(Customer.class);
			sources.addAnnotatedClass(Department.class);
			sources.addAnnotatedClass(Question.class);
			sources.addAnnotatedClass(Answer.class);
			sources.addAnnotatedClass(Emp.class);
			sources.addAnnotatedClass(Project.class);
			sources.addAnnotatedClass(Student.class);
			
			
			
			//PersistenceProvider persistenceProvider  = new HibernatePersistenceProvider();
			
			//EntityManagerFactory emf = persistenceProvider.createContainerEntityManagerFactory(new AllEntitiesPersistenceUnitInfo(), null);
			
			Metadata metadata = sources.getMetadataBuilder().build();
			
			sessionFactory = metadata.getSessionFactoryBuilder().build();
			
			
			//return new Configuration().configure().buildSessionFactory();
			
			return sessionFactory;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return sessionFactory;
		
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
