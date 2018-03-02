<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="../commons/init_include.jsp" %>
<%@ include file="../commons/init_config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../commons/head.jsp" %>
</head>

<body>	
    <%@ include file="../commons/page_header.jsp" %>
    <div class="inner_main_bg">
    	<div class="inner_main_bg_t">
        	<div class="inner_main_bg_b">
            	
                <div class="inner_main">
                	<%@ include file="../commons/page_sidebar.jsp" %>
                    <div class="inner_container">
                    	<!--ÄÚÈÝ s-->
                        <%
						try{
							String keyword = Request.getString("keyword");
							 String ILLEGAL_WORDS = "(\\||&amp;|&|;|\\^|\\$|%|@|'|\"|\\\\|&lt;|&gt;|<|>|\\(|\\)|\\+|\n|\r|,|/|#)";
								if(keyword != null){
									keyword = keyword.replaceAll(ILLEGAL_WORDS, "");
									}
						%>
						<div class="right_t">
                        	<span><img src="../images/img01_rightt_group.jpg" /><%=keyword%></span>
                        </div>
                        <div>
						<%	
							int pageno = Request.getInt("pageno",1);
							int pagesize = 10;
							
							String sql = "select count(*) from cms_archive where title like '%" + keyword + "%' or content like '%" + keyword + "%'";							
							int totalrows = dbi.getRecordNum(sql);	
							sql = "select * from cms_archive where title like '%" + keyword + "%' or content like '%" + keyword + "%'";						
							Vector vc = CmsApp.getArchiveList(dbi,sql,pageno,pagesize);
							
							for(int i = 0;i < vc.size();i++){								
								Archive ar = (Archive)vc.get(i);
								
								String title = ar.getTitle();
								
								if(title.length() > 40)
									title = title.substring(0,40) + "..";
						%>
                        	<dl class="archive_list">
                            	<dt><span>&middot;</span><a href="../public/archive.jsp?catid=<%=ar.getCatId()%>&id=<%=ar.getId()%>" title="<%=ar.getTitle()%>"><%=title%></a></dt>
                                <dd>[<%=ar.getCreateDate()%>]</dd>
                            </dl>
                        <%
							}
						%>    
                            
                            <div class="archive_border"></div>
                            <div class="inner_pages">
                            	<%@ include file="../commons/pages.jsp" %>                                
                            </div>
                        </div>
                        <%
						}catch(Exception ex){
							out.println(ex);
						}
						%>
                        <!--ÄÚÈÝ e-->
                    </div>
                    <div style="clear:both"></div>
                    <div style="position:absolute;right:-25px;bottom:70px"><a href="#"><img src="../images/gotop.jpg" alt="" /></a></div>
                </div>                
                               
            </div>
        </div>
    </div>    
    <%@ include file="../commons/page_footer.jsp" %> 
</body>
</html>
<%@ include file="../commons/init_free.jsp" %>