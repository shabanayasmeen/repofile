package struts.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import struts.example.customer.CustomerDetailObject;
import struts.example.customer.delegate.CustomerDelegate;

public class ShowCustomerAction extends DispatchAction
{

//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//				HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//		ActionForward nextPage = null;
//
//		if ("Create".equals(request.getParameter("action")))
//		{
//			nextPage = mapping.findForward("customerFormPage");
//		}
//		else if ("Edit".equals(request.getParameter("action")))
//		{
//			CustomerDelegate delegate = new CustomerDelegate();
//			CustomerDetailObject customer = delegate.getCustomerDetail(request.getParameter("email"));
//			CustomerForm custForm = CustomerFormAssembler.createCustomerForm(customer);
//			request.setAttribute("CustomerForm", custForm);
//			nextPage = mapping.findForward("customerFormPage");
//		}
//		
//		return nextPage;
//	}
//	
	
	public ActionForward Create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("customerFormPage");
	}
	

	public ActionForward Edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CustomerDelegate delegate = new CustomerDelegate();
		CustomerDetailObject customer = delegate.getCustomerDetail(request.getParameter("email"));
		CustomerForm custForm = CustomerFormAssembler.createCustomerForm(customer);
		request.setAttribute("CustomerForm", custForm);
		return mapping.findForward("customerFormPage");
	}
		
}