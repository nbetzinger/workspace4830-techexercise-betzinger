/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.TaskList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
//general UtilDB
public class UtilDBBetzinger {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<TaskList> listTasks() {
      List<TaskList> resultList = new ArrayList<TaskList>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> tasks = session.createQuery("FROM TaskList").list();
         for (Iterator<?> iterator = tasks.iterator(); iterator.hasNext();) {
            TaskList task = (TaskList) iterator.next();
            resultList.add(task);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<TaskList> listTasks(String keyword) {
      List<TaskList> resultList = new ArrayList<TaskList>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> tasks = session.createQuery("FROM TaskList").list();
         for (Iterator<?> iterator = tasks.iterator(); iterator.hasNext();) {
            TaskList task = (TaskList) iterator.next();
            if (task.getDue().startsWith(keyword)) {
               resultList.add(task);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static void createTasks(String taskName, String priority, String due) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new TaskList(taskName, Integer.valueOf(priority), due));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   
   public static void removeTasks(String taskName) {
	   Session session = getSessionFactory().openSession();
	   Transaction tx = null;
	   try {
		   tx = session.beginTransaction();
		   List<?> tasks = session.createQuery("FROM TaskList").list();
		   for(Iterator<?> iterator = tasks.iterator(); iterator.hasNext();) {
			   TaskList task = (TaskList) iterator.next();
			   if(task.getTaskName().startsWith(taskName))
				   session.delete(task);
		   }
		   tx.commit();
	   } catch (HibernateException e) {
		   if(tx!= null)
			   tx.rollback();
		   e.printStackTrace();
	   } finally {
		   session.close();
	   }
   }
   
}
