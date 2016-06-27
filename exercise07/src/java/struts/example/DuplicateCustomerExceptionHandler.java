/*
 * Created on Jul 7, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package struts.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import struts.example.customer.dao.DuplicateCustomerException;

/**
 * @author vnszs
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DuplicateCustomerExceptionHandler extends ExceptionHandler
{
	
	public ActionForward execute(Exception ex, ExceptionConfig ae,
								 ActionMapping mapping, ActionForm formInstance,
								 HttpServletRequest request, HttpServletResponse response)
									throws ServletException 
	{
		ActionForward forward = mapping.getInputForward();
		ActionErrors errors = new ActionErrors();
		
		DuplicateCustomerException dup = (DuplicateCustomerException) ex;
		
		ActionError error = new ActionError(dup.getUserMessageKey(), dup.getValueReplacementArray());
		errors.add("emailAddress", error);
		
		//Store the ActionsErrors in request
		request.setAttribute(Globals.ERROR_KEY, errors);
		
		//Store the Exception in request
		request.setAttribute(Globals.EXCEPTION_KEY, ex);
		
		return forward;
	}

}
