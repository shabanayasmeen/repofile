<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<html:html xhtml="true">

<head>
	<title><bean:message key="formpage.title"/></title>
	<html:base/>
	<link rel="stylesheet" href="css/struts-training.css" charset="ISO-8859-1" type="text/css"/>
</head>

<body background="images/blueAndWhiteBackground.gif">
	<div align="center">
		<h2><bean:message key="searchpage.title"/></h2>
		
		<html:errors/>
	
		<html:form action="/submitCustomerSearchForm" focus="lastName" method="GET">
	  
		  	<table width="60%">
		  		<tr>
					<td align="right"><bean:message key="prompt.customer.lastname"/>:</td>
					<td align="left"><html:text property="lastName" size="16" maxlength="25"/></td>
					<td align="left"><html:image property="searchButton" srcKey="image.search" altKey="image.search.alttext" bundle="bundle.image"/></td>
				</tr>
			</table>
	  	</html:form>
	</div>

	<c:if test='${empty CUSTOMER_SUMMARY_OBJECTS}'>
		<div align="center"><bean:message key="prompt.noresults"/> "<c:out value='${CustomerSearchForm.lastName}'/>"</div>
	</c:if>

	<html:form action="/manageCustomerList" method="GET">
	
		<c:if test='${not empty CUSTOMER_SUMMARY_OBJECTS}'>
		<pg:pager url="customerlist.do" maxIndexPages="10" maxPageItems="5">
			<div align="center">
				<table class="list">
					<thead class="list">
						<tr class="list">
							<td class="list">&nbsp;</td>
							<td class="list"><bean:message key="prompt.customer.firstname" /></td>
							<td class="list"><bean:message key="prompt.customer.lastname" /></td>
							<td class="list"><bean:message key="prompt.customer.email"/></td>
						</tr>
					</thead>
			
					<%
						int i = 1;
						String claz = null;
					%>
					
					<c:forEach var='customer' items='${CUSTOMER_SUMMARY_OBJECTS}'>
						<% claz = (i%2 != 0) ? "odd" : "even"; %>
						<pg:item>						
							<tr class="<%= claz %>">
							
								<td>
									<html-el:multibox property="idSelections">
										<c:out value='${customer.id}'/>
									</html-el:multibox>
								</td>
							
								<td><c:out value='${customer.firstName}' /> </td>
							
								<td><c:out value='${customer.lastName}' /> </td>
							
								<td>
									<html-el:link page="/showCustomerForm.do?action=Edit&amp;email=${customer.emailAddress}">
										<c:out value='${customer.emailAddress}'/>
									</html-el:link>
								</td>
							
							</tr>
						</pg:item>
						
						<% i++; %>
					</c:forEach>
				</table>
				
				<TABLE width="80%" border="0">
					<TR><TD>&nbsp;</TD></TR>
					<TR align="center">
						<TD>
							<pg:index>
							
								<pg:prev><a href="<%= pageUrl%>">[&lt;&lt;Prev]</html-el></pg:prev>
								
								<pg:pages><a href="<%= pageUrl %>"><%= pageNumber %></a></pg:pages>
								
								<pg:next><a href="<%= pageUrl%>">[Next&gt;&gt;]</a></pg:next>
								
							</pg:index>
						</TD>
					</TR>
					<TR><TD>&nbsp;</TD></TR>
				</TABLE>

			</div>
		</pg:pager>
		</c:if>

		<div align="center">
			<table>
				<tr>
					<td>
						<html:image property="newButton" 
									srcKey="image.new" 
									altKey="image.new.alttext" 
									bundle="bundle.image"/>
					</td>
					
					<c:if test='${not empty CUSTOMER_SUMMARY_OBJECTS}'>
						<td>
							<html:image property="deleteButton" 
										srcKey="image.delete" 
										altKey="image.delete.alttext" 
										bundle="bundle.image"/>
						</td>
					</c:if>					
				</tr>
			</table>
		</div>
	</html:form>
</body>

</html:html>