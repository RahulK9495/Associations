package Associations_onetoone_Unidirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class TestOneToOneBidiection {

	public static void main(String[] args) {

		SessionFactory sessionfcatory = HibernateUtil.getSessionFactory();

		Session session = sessionfcatory.openSession();

		Transaction transaction = session.beginTransaction();
		
		Employee employee =new Employee();
	
		employee.setName("Ram");
		employee.setSalary(252526);
		
		
		Account account = new Account();
		account.setAccountName("Saving");
		account.setAccountNumber(232424);
		
		employee.setAccount(account);
		account.setEmployee(employee);
		
		session.save(employee);
		session.save(account);
		
		transaction.commit();
		session.close();
		
		sessionfcatory.close();
		
		int empid = employee.getId();
		int accid = account.getId();
		
		
		
		Session session2 = sessionfcatory.openSession();
		Transaction transaction2 = session2.beginTransaction();
		
		Employee retrivedEmp = session2.get(Employee.class, empid);
		
		System.out.println(retrivedEmp.getName());
		System.out.println(retrivedEmp.getSalary());
		System.out.println(retrivedEmp.getAccount().getId());
		
		Account retrivedAcc =  session2.get(Account.class, accid);
		
		System.out.println(retrivedAcc.getAccountName());
		System.out.println(retrivedAcc .getAccountNumber());
		System.out.println(retrivedAcc .getEmployee().getId());

		transaction2.commit();
		session2.close();
		
		sessionfcatory.close();

		
		
	}

}
