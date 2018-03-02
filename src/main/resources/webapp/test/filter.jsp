<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>
<%!
	//过滤注入信息

	String sql = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";  
    String script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";                                                                                                
    String style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
    String html = "<[^>]+>";
	public String validateSql(String keyword){
		String validatedata="";
		String word=keyword;
		try {
		Pattern pattern = Pattern.compile(sql, Pattern.CASE_INSENSITIVE);  
		validatedata = pattern.matcher(word).replaceAll(""); 
        } catch (Exception e) {
          e.getMessage();
        }
		return validatedata;
	}
	public String validateJavaScipt(String keyword){
		String validatedata="";
		String word=keyword;
		try {
		Pattern pattern = Pattern.compile(script, Pattern.CASE_INSENSITIVE);  
		validatedata = pattern.matcher(word).replaceAll(""); 
        } catch (Exception e) {
          e.getMessage();
        }
		return validatedata;
	}
	public String validateHtml(String keyword){
		String validatedata="";
		String word=keyword;
		try {
		Pattern pattern = Pattern.compile(html, Pattern.CASE_INSENSITIVE);  
		validatedata = pattern.matcher(word).replaceAll(""); 
        } catch (Exception e) {
          e.getMessage();
        }
		return validatedata;
	}
	public String validateCss(String keyword){
		String validatedata="";
		String word=keyword;
		try {
		Pattern pattern = Pattern.compile(style, Pattern.CASE_INSENSITIVE);  
		validatedata = pattern.matcher(word).replaceAll(""); 
        } catch (Exception e) {
          e.getMessage();
        }
		return validatedata;
	}
	public String validateAll(String keyword){
		return validateSql(validateJavaScipt(validateHtml(validateCss(keyword))));
		
	}
%>