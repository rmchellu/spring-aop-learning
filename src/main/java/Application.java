import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.controller.FileImportController;
import com.learning.controller.TestController;
import com.learning.templates.factory.JDBCTemplateService;



public class Application {
    
   public static void main(String[] args) {
       
      ApplicationContext context = 
             new ClassPathXmlApplicationContext("resources/spring-config.xml");
      
      
      FileImportController fileImportController = (FileImportController) context.getBean("fileImportController");

      fileImportController.process("test should be intercepted");
     
     TestController testController = (TestController) context.getBean("testController");

     testController.process("test should not be intercepted");
      System.out.println("done:");
   }
}