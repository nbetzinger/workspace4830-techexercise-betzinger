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

@WebServlet("/RemoveTask")
public class RemoveTask extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public RemoveTask() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Remove Task";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<TaskList> listTasks = null;
      if (keyword != null && !keyword.isEmpty()) {
         UtilDBBetzinger.removeTasks(keyword);
      } else {
         listTasks = UtilDBBetzinger.listTasks();
      }
      display(out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + removeWebName + ">Return to Removing Tasks</a> <br>");
      out.println("</body></html>");
   }

   void display(PrintWriter out) {
//      for (TaskList task : listTasks) {
//         System.out.println("[DBG] " + task.getId() + ", " //
//               + task.getTaskName() + ", " //
//               + task.getPriority() + ", " //
//         	   + task.getDue());
//
//         out.println("<li>" + task.getId() + ", " //
//               + task.getTaskName() + ", " //
//               + task.getPriority() + ", " // 
//         		+ task.getDue() + "</li>");
//      }
	   out.println("<li>Task Removed!</li>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
