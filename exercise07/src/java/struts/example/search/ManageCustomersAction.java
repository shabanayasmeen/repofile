package struts.example.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.example.customer.delegate.CustomerDelegate;
import struts.example.util.BaseAction;
import struts.example.util.BaseActionForm;

public class ManageCustomersAction extends BaseAction 
{

	public ActionForward process(ActionMapping mapping, BaseActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionForward forward = null;
		ManageCustomersForm custForm = (ManageCustomersForm) form;
		if (custForm.getNewButton().isSelected())
		{
			forward = mapping.findForward("add");	
		}
		else if (custForm.getDeleteButton().isSelected())
		{
			CustomerDelegate delegate = new CustomerDelegate();
			String[] idsToDelete = custForm.getIdSelections();
			if (idsToDelete != null && idsToDelete.length > 0 )
			{
				for (int i=0;i<idsToDelete.length;i++)
				{
					int x = Integer.parseInt(idsToDelete[i]);
					delegate.deleteCustomer(x);
				}
			}
			forward = mapping.findForward("deleteSuccess");
		}
		
		return forward;
	}

}