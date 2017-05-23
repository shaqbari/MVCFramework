<%@page import="Common.file.FileManager"%>
<%@page import="gallery.model.Gallery"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@ page contentType="text/html;charset=utf-8"%>

<%
	
	List<Gallery> list=(List)request.getAttribute("list");

%>

<%
	int currentPage=1; //현재 페이지
	//클라이언트가 전송한 currentPage파라미터 값을 현재 페이지에 이용하자!
	if(request.getParameter("currentPage")!=null){//null이 아닐때만 입력한다.
		currentPage=Integer.parseInt(request.getParameter("currentPage"));//최초에는 null이므로 유효성검사 없으면 에러!
	}
	
	int totalRecord=list.size(); //총 레코드 수	
	int pageSize=10; //페이지당 보여질 레코드 수 sqlplus에서도 이 변수명 사용한다.
	int totalPage=(int)Math.ceil((float)totalRecord/pageSize); //총페이지수
	//연산할때 양쪽중에 하나만 달라도 손실이 발생하지 않는것을 기준으로 한다.
	//Math.ceil():올림 floor():버림 round():반올림 //스태틱 메소드라 그냥 써도된다.
	
	int totalBlock=1;
		
	
	int blockSize=10;//블럭당 보여질 페이지 수
	/* int firstPage=(int)Math.floor(
		Math.floor(
			Math.floor( (float)currentPage/pageSize)*blockSize
		) 
	)+1; */ //블럭당 시작 페이지
	int firstPage=currentPage-(currentPage-1)%blockSize;
	int lastPage=firstPage+blockSize-1; //블럭당 마지막 페이지
	
	int curPos=(currentPage-1)*pageSize; //페이지당 시작 index(ArrayList에서)
	int num=totalRecord-curPos; //페이지당 시작 번호//기존 변수를 이용하는 것이 유지보수상 좋다.
	//int num=totalRecord-(currentPage-1)*pageSize; //페이지당 시작 번호
	
%>

<%="currentPage="+currentPage %>

<%="totalRecord="+totalRecord %>
<%="pageSize="+pageSize %>
<%="totalPage="+totalPage %>
<%="blockSize="+blockSize %>
<%="totalBlock="+totalBlock %>
<%="firstPage="+firstPage %>
<%="lastPage="+lastPage %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
#box{border:1px solid #CCCCCC}
#title{font-size:9pt;font-weight:bold;color:#7F7F7F;돋움}
#category{font-size:9pt;color:#7F7F7F;돋움}
#keyword{
	width:80px;
	height:17px;
	font-size:9pt;
	border-left:1px solid #333333;
	border-top:1px solid #333333;
	border-right:1px solid #333333;
	border-bottom:1px solid #333333;
	color:#7F7F7F;돋움
}
#paging{font-size:9pt;color:#7F7F7F;돋움}
#list td{font-size:9pt;}
#copyright{font-size:9pt;}
a{text-decoration:none}
img{border:0px}

.pageStyle{
	font-weight:bold;
	font-size:14pt;
	color:red
}

</style>
</head>
<body>
<table id="box" align="center" width="603" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="6"><img src="/board/images/ceil.gif" width="603" height="25"></td>
  </tr>
  <tr>
    <td height="2" colspan="6" bgcolor="#6395FA"><img src="/board/images/line_01.gif"></td>
  </tr>
  <tr id="title" align="center">
    <td width="50" height="20">번호</td>
    <td width="50" height="20">이미지</td>
    <td width="203" height="20">제목</td>
    <td width="100" height="20">글쓴이</td>
    <td width="100" height="20">날짜</td>
    <td width="50" height="20">조회수</td>
  </tr>
  <tr>
    <td height="1" colspan="6" bgcolor="#CCCCCC"></td>
  </tr>
	<tr>	
		<td colspan="6" id="list">
		  <table width="100%" border="0" cellpadding="0" cellspacing="0">
		  
		  <% for(int i=0; i<pageSize; i++){ %>
			<%if(num<1)break; %>
			<%-- Board dto=list.get(curPos++); 좀 위험한 위치 num이 멤버변수?라 여기서는 괜찮다. 
			멤버 변수가 아니라 연산 다 끝난뒤 더한다.--%>
		    <tr align="center" height="20px" onMouseOver="this.style.background='#FFFF99'" onMouseOut="this.style.background=''">
			  
				
				<td width="50"> <%=num-- %> </td>
			<% Gallery dto=list.get(curPos++); %>
				<td width="50" height="50"><img width="50" height="50" src="/data/<%=dto.getGallery_id()+"."+FileManager.getExt(dto.getUserFile()) %>"/></td>
				<td width="203"><a href="detail.do?gallery_id=<%=dto.getGallery_id()%>"><%=dto.getTitle() %></a></td>
				<td width="100"><%=dto.getWriter() %></td>
				<td width="100"><%=dto.getRegdate().substring(0, 10)%></td>
				<td width="50"><%=dto.getHit() %></td>
			
			    
		    </tr>
			<tr>
				<td height="1" colspan="6" background="/board/images/line_dot.gif"></td>
			</tr>
		 <%} %>
		  	
		  	
		  </table>		</td>
	</tr>
  <tr>
    <td id="paging" height="20" colspan="6" align="center">
    	<%if(firstPage-1>0){ //이전페이지가 있다면 %>
    	<a href="/board/list.do?currentPage=<%=firstPage-1%>">◀</a>
		<%}else{ %>
		<a href="javascript:alert('처음페이지 입니다.');">◀</a> 
		<%} %>
		
		<%for(int i=firstPage; i<=lastPage; i++){ %>		
			<%if(i>totalPage)break;%><!-- 내가 가진 페이지보다 크면 그만 찍어라 -->
   			<a <%if(i==currentPage){ %>class="pageStyle"<%} %> href="/gallery/list.do?currentPage=<%=i%>">[<%=i%>]</a> <!--get방식으로 현재의 페이지를 요청한다. -->
    	<%} %>
    	
    	<%if(lastPage+1<totalPage){ //다음페이지가 있다면 %>
    		<a href="/gallery/list.do?currentPage=<%=lastPage+1%>">▶</a>
    	<%}else{ %>
    		<a href="javascript:alert('마지막페이지 입니다.');">▶</a> 
    	<%} %>
    </td>
  </tr>
  <tr>
    <td height="20" colspan="6" align="right" style="padding-right:2px;">
	<table width="160" border="0" cellpadding="0" cellspacing="0">      
      <tr>
        <td width="70">
          <select name="select" id="category">
            <option>제목</option>
            <option>내용</option>
            <option>글쓴이</option>
          </select>        </td>
        <td width="80">
          <input name="textfield" id="keyword" type="text" size="15">        </td>
        <td><img src="/board/images/search_btn.gif" width="32" height="17"></td>
      </tr>      
    </table></td>
  </tr>
  <tr>
    <td height="30" colspan="6" align="right" style="padding-right:2px;"><a href="write.jsp"><img src="/board/images/write_btin.gif" width="61" height="20" border="0"></a></td>
  </tr>
  <tr>
    <td height="1" colspan="6" bgcolor="#CCCCCC"></td>
  </tr>
  <tr>
    <td height="20" colspan="6" align="center" id="copyright">Copyright zino All Rights Reserved </td>
  </tr>
</table>
</body>
</html>
