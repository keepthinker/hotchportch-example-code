<%@page import="com.keepthinker.example.web.servlet.MyFilter"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Random"%>
<%@page import="com.keepthinker.example.web.servlet.Person"%>
<html>
<body>
<%!
	private int getRandonInt(){
		return new Random().nextInt();
	} 
%>
<%
	int i = getRandonInt();
	Person person1 = new Person();
	person1.setName("Shawn Ke!" + i);
 	pageContext.setAttribute("person", person1); 
%>
<h3>${person.name}</h3>
<jsp:useBean id="person" class="com.keepthinker.example.web.servlet.Person" />
<h2>Hello World! <%-- <jsp:setProperty name="person" property="name" value="Shawn Ke"/> --%></h2>
<h3><jsp:getProperty name="person" property="name"/></h3>
<jsp:setProperty name="person" property="name" value="Shawn"/>
<h3>${person.name}</h3>
<p>
<jsp:element name="ol">
	<jsp:attribute name="id">
		1
	</jsp:attribute>
	<jsp:body>
	<jsp:element name="li">
	  Ke Shengkai
	</jsp:element>
	<jsp:element name="li">
	  Ke Shengkai
	  <jsp:text>  
	    , Welcome to JSP Programming
		</jsp:text>
	</jsp:element>
	</jsp:body>
</jsp:element>
</p>
<p>
<% 
Object a = null;// = new Object(); 
request.setAttribute("a", a);
%>
${empty a}
<br/>
${a == null}
<br/>
${1 + 1}
</p>
<p>
${param.id}
</p>
<p>
</p>
</body>
<%
out.println("current caller class's class loader for Class.forName() : " + sun.reflect.Reflection.getCallerClass().getClassLoader() + "<br/><br/>");
out.println("Servlet.class.getClassLoader() --- <br/>" + recursiveParent(Servlet.class.getClassLoader()) + "<br/>");
out.println("javax.el.ELContext --- <br/>" + recursiveParent(Class.forName("javax.el.ELContext").getClassLoader()) + "<br/>");
out.println("org.apache.catalina.connector.Connector --- <br/>" + recursiveParent(Class.forName("org.apache.catalina.connector.Connector").getClassLoader()) + "<br/>");
ClassLoader cl = MyFilter.class.getClassLoader();//ClassLoader.getSystemClassLoader();
out.println("MyFilter.class.getClassLoader() --- <br/>" + recursiveParent(cl) + "<br/>");
out.println("MyFilter org.apache.catalina.connector.Connector --- <br/>" + recursiveParent(cl.loadClass("org.apache.catalina.connector.Connector").newInstance().getClass().getClassLoader()) + "<br/>");
out.println("Thread.currentThread().getContextClassLoader() --- <br/>" + recursiveParent(Thread.currentThread().getContextClassLoader()) + "<br/>");
out.println("ClassLoader.getSystemClassLoader() --- <br/>" + recursiveParent(ClassLoader.getSystemClassLoader()) + "<br/>");
%>
<%!
private String recursiveParent(ClassLoader cl){
	if(cl == null){
		return "";
	}
	String str = recursiveParent(cl.getParent());
	return str + cl + ":" + cl.getClass().getName() + "<br/>";
}%>
</html>
