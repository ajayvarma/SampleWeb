package com.javacodegeeks.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyJSPConfiguration {

	public static void main(String[] args) {
		
		Server server = new Server(8580);
		WebAppContext ctx = new WebAppContext();
		ctx.setResourceBase("src/main/webapp");
		ctx.setContextPath("/jettyjspconfiguration-example");
		
		ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*jstl.*\\.jar$");
		
		org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");
        
		server.setHandler(ctx);
		try {
			server.start();
			server.join();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

}
