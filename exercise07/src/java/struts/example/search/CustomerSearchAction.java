package struts.example.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.example.customer.delegate.CustomerListDelegate;
import struts.example.customer.list.CustomerSummaryObject;

/**
 *
 * @author Srikanth Shenoy
 * @version $Revision:   $ $Date:   $
 */
public class CustomerSearchAction extends Action 
{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionForward forward = null;
		CustomerSearchForm searchForm = (CustomerSearchForm) form;
		
		//Make search as the ONLY action, even if you click Search button or just press enter (at which there is no ssearch.x or search.y)
		//if (searchForm.getSearchButton().isSelected())
		//{
			CustomerListDelegate delegate = new CustomerListDelegate();
			CustomerSummaryObject[] customers = delegate.findCustomers(searchForm.getLastName());
			HttpSession session = request.getSession(true);
			session.setAttribute("CUSTOMER_SUMMARY_OBJECTS", customers);
			forward = mapping.findForward("success");
		//}
		
		return forward;
	}

}