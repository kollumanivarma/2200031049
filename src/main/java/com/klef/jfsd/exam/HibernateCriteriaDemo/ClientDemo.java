package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Step 1: Get the SessionFactory
        SessionFactory factory = HibernateUtil.getSessionFactory();

        // Step 2: Open a Session
        Session session = factory.openSession();

        // Step 3: Begin Transaction
        Transaction transaction = session.beginTransaction();

        // Step 4: Insert Records
        Customer customer1 = new Customer();
        customer1.setName("Alice");
        customer1.setEmail("alice@example.com");
        customer1.setAge(25);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Bob");
        customer2.setEmail("bob@example.com");
        customer2.setAge(30);
        customer2.setLocation("Los Angeles");

        // Save Customers
        session.save(customer1);
        session.save(customer2);

        // Commit the transaction
        transaction.commit();

        System.out.println("Customers saved successfully!");

        // Step 5: Run Criteria Queries
        System.out.println("\nCustomers with age > 20:");
        Criteria criteria1 = session.createCriteria(Customer.class);
        criteria1.add(Restrictions.gt("age", 20));
        List<Customer> customers1 = criteria1.list();
        customers1.forEach(c -> System.out.println(c.getName()));

        // Step 6: Close the Session and Factory
        session.close();
        factory.close();
    }
}
