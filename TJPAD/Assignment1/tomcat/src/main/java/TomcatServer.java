import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import view.*;

import java.io.File;

public class TomcatServer {
    public static void main(String[] args) throws Exception{
        Tomcat server = new Tomcat();
        Context context = server.addContext("", (new File(".")).getAbsolutePath());
        Tomcat.addServlet(context, "cars", new carsHTML());
        Tomcat.addServlet(context, "add", new addHTML());
        Tomcat.addServlet(context, "dispatcher", new dispatcher());
        Tomcat.addServlet(context, "update", new updateHTML());
        Tomcat.addServlet(context, "delete", new deleteHTML());
        context.addServletMappingDecoded("", "dispatcher");
        context.addServletMappingDecoded("/dispatcher", "dispatcher");
        context.addServletMappingDecoded("/cars", "cars");
        context.addServletMappingDecoded("/add", "add");
        context.addServletMappingDecoded("/update", "update");
        context.addServletMappingDecoded("/delete", "delete");
        System.out.println("Start Tomcat embedded");
        server.start();
        server.getServer().await();
    }
}
