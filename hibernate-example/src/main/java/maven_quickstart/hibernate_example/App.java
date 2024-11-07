package maven_quickstart.hibernate_example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import maven_quickstart.hibernate_example.entity.Customer;
import maven_quickstart.hibernate_example.entity.Department;
import maven_quickstart.hibernate_example.entity.Employee;
import maven_quickstart.hibernate_example.entity.manytomany.Emp;
import maven_quickstart.hibernate_example.entity.manytomany.Project;
import maven_quickstart.hibernate_example.entity.onetomany.Answer;
import maven_quickstart.hibernate_example.entity.onetomany.Question;
import maven_quickstart.hibernate_example.util.HibernatePropertiesUtil;

/**
 * Hello world!
 */
public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
    /**    
        //Session session = HibernateUtil.getSessionFactory().openSession(); //Opening Session
        //HibernatePropertiesUtil.buildSessionFactory();
        Session session = HibernatePropertiesUtil.getSessionFactory().openSession(); //Opening Session
        
        
        
     
        
        session.beginTransaction(); //Txn Management - ACID compliant
        
        Employee employee = new Employee();
        
        employee.setEmployeeId(10);
        employee.setEmployeeName("employee10");
        employee.setCreatedBy("Hibernate Program1");
        employee.setCreatedDate(new Date());
        
        
       // Customer customer  = new Customer();
      //  customer.setCustomerId(1235);
      //  customer.setCustomerName("mycustomer1");
        
      //  Department department = new Department();
       // department.setDeptId(4568);
       // department.setName("My department1");
        
		//customer.setDeparment(department);
		
		
		Customer customer = session.get(Customer.class, 2);
		System.out.println("Customer Value : ============" + customer.toString());
		//session.delete(customer);
        
        Department department = session.get(Department.class, 4568);
        System.out.println("Department Value : ==========" + department.toString());
		System.out.println(department.getCustomer());
        
		//session.save(customer);
       // session.persist(employee);
		
		 session.getTransaction().commit();
	     session.close();
		
		
		/**********************OneToMany*****************************************************************************/
	
       /**
		System.out.println("===========================Starting of One To Many=====================================");
		
		
		Session session1 = HibernatePropertiesUtil.getSessionFactory().openSession();
		
		session1.beginTransaction();
		
//		// creating question
		Question q1 = new Question();
		q1.setQuestionId(1212);
		q1.setQuestion("what is java ?");
//
//		// creating answer
		Answer answer = new Answer();
		answer.setAnswerId(343);
		answer.setAnswer("Java is programming langauge");
		answer.setQuestion(q1);
//
		Answer answer1 = new Answer();
		answer1.setAnswerId(33);
		answer1.setAnswer("With the help of java we can create softwares");
		answer1.setQuestion(q1);
//
		Answer answer2 = new Answer();
		answer2.setAnswerId(363);
		answer2.setAnswer("java has different type of frameworks.");
		answer2.setQuestion(q1);
//		
		List<Answer> list=new ArrayList<Answer>();
		list.add(answer);
		list.add(answer1);
		list.add(answer2);		
		q1.setAnswers(list);
		
		session1.persist(q1);
		//session1.save(q1);
		session1.getTransaction().commit();
		session1.close();
		
	
		**/
		
		System.out.println("======================Many To Many Mapping====================================");
		
		Session session3 = HibernatePropertiesUtil.getSessionFactory().openSession();

		Emp e1 = new Emp();
		Emp e2 = new Emp();

		e1.setEid(36);
		e1.setName("Ram");

		e2.setEid(37);
		e2.setName("Shyam");

		Project p1 = new Project();
		Project p2 = new Project();

		p1.setPid(12122);
		p1.setProjectName("Library management system");
		p2.setPid(14215);
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
