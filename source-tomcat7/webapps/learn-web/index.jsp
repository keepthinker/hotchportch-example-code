<%@page import="java.util.Date"%>
<%@page import="java.util.Random"%>
<%@page import="com.keepthinker.learn.web.servlet.Person"%>
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
<jsp:useBean id="person" class="com.keepthinker.learn.web.servlet.Person" />
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
</html>
