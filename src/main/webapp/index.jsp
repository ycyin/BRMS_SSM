<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.Enumeration"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% 
Enumeration names = request.getHeaderNames();   
while(names.hasMoreElements()) {  
    String name = names.nextElement().toString();  
    Enumeration headers = request.getHeaders(name);  
    while(headers.hasMoreElements()) {  
	    out.println("\n");  
    	out.println(headers.nextElement());  
    }  
    out.println("\n");  
}  
%>
</html>