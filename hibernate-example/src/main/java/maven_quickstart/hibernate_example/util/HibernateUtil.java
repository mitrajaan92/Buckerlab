package maven_quickstart.hibernate_example.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import maven_quickstart.hibernate_example.entity.Employee;

public class HibernateUtil {

	private static StandardServiceRegistry registry; //configures hibernate services

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {

			try {
				
				/*Builder Class , example of builder pattern*/
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder(); 

				Map<String, Object> settings = new HashMap<>(); // Hibernate Configurations , can be xml or can be java file
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/ntrace?useSSL=false");

				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "root");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				settings.put(Environment.SHOW_SQL, "true");

				registryBuilder.applySettings(settings);

				registry = registryBuilder.build();

				MetadataSources sources = new MetadataSources(registry); //It gathers meta data about entity & mapping classes

				sources.addAnnotatedClass(Employee.class); // adding entity to metadata sources

				Metadata metadata = sources.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception e) {
				System.out.println(e);

				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}

			return sessionFactory;
		}
		return sessionFactory;

	}
	
	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}
