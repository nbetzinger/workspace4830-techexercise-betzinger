package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//TaskList Outline
/**
 * @since J2SE-1.8
 CREATE TABLE task (
  id INT NOT NULL AUTO_INCREMENT,    
  task_name VARCHAR(30) NOT NULL,   
  priority INT NOT NULL,
  due varchar(30) NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "task")
public class TaskList {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "task_name")
   private String task_name;

   @Column(name = "priority")
   private Integer priority;
   
   @Column(name = "due")
   private String due;

   public TaskList() {
   }

   public TaskList(Integer id, String task_name, Integer priority, String due) {
      this.id = id;
      this.task_name = task_name;
      this.priority = priority;
      this.due = due;
   }

   public TaskList(String task_name, int priority, String due) {
      this.task_name = task_name;
      this.priority = priority;
      this.due = due;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getTaskName() {
      return task_name;
   }

   public void setName(String task_name) {
      this.task_name = task_name;
   }

   public Integer getPriority() {
      return priority;
   }

   public void setPriority(Integer priority) {
      this.priority = priority;
   }
   
   public String getDue() {
	   return due;
   }
   
   public void setDue(String due) {
	   this.due = due;
   }

   @Override
   public String toString() {
      return "Employee: " + this.id + ", " + this.task_name + ", " + this.priority + this.due;
   }
}