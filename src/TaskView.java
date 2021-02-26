import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.TaskList;
import util.Info;
import util.UtilDBBetzinger;

@WebServlet("/TaskView")
public class TaskView extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public TaskView() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Task List";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\" style=\"font-family:arial;\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<TaskList> listTasks = null;
      if (keyword != null && !keyword.isEmpty()) {
         listTasks = UtilDBBetzinger.listTasks(keyword);
      } else {
         listTasks = UtilDBBetzinger.listTasks();
      }
      display(listTasks, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + viewWebName + ">Return to Viewing Tasks</a> <br>");
      out.println("</body></html>");
   }

   void display(List<TaskList> listTasks, PrintWriter out) {
      for (TaskList task : listTasks) {
         System.out.println("[DBG] " + task.getId() + ", " //
               + task.getTaskName() + ", " //
               + task.getPriority() + ", " //
         	   + task.getDue());

         out.println("<li>" + task.getTaskName() + ", " //
               + task.getPriority() + ", " // 
         		+ task.getDue() + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
