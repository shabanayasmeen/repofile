package struts.example.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public abstract class BaseAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseActionForm baseForm = null;
		if (form != null)
		{
			baseForm = (BaseActionForm) form;
		}
		
		logEntry(mapping, baseForm, request, response);
		preprocess(mapping, baseForm, request, response);
		ActionForward forward = process(mapping, baseForm, request, response);
		postprocess(mapping, baseForm, request, response);
		logExit(mapping, baseForm, request, response, forward);
		return forward;
	}
	
	protected void logEntry(ActionMapping mapping, BaseActionForm baseForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Log log = LogFactory.getLog(baseForm.getClass().getName());
		if (log.isInfoEnabled())
		{
			log.info("Entering " + this.getClass().getName());
		}
		if (log.isDebugEnabled())
		{
			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements())
			{
				String s = (String) e.nextElement();
				String param = request.getParameter(s);
				log.debug("Request Parameter:" + s + ",Value:" + param);
			}
			if (baseForm != null)
			{
				//log.debug("Form properties set from request parameters are..");
				//More code goes here
				//This can be enabled to check if form properties are correctly set 
				//from request
			}
		}
	}
	
	protected void logExit(ActionMapping mapping, BaseActionForm baseForm,
			HttpServletRequest request, HttpServletResponse response, ActionForward forward)
	{
		Log log = LogFactory.getLog(baseForm.getClass().getName());
		try
		{
			if (log.isInfoEnabled())
			{
				log.info("Exiting " + this.getClass().getName());
				log.info("Dispatching to " + forward.getPath());
			}
		}
		catch (Exception e)
		{
			log.error(e.getClass().getName() + " occured. Ignoring since it occured in log");
		}
	}
	
	public void preprocess(ActionMapping mapping, BaseActionForm baseForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			
	}
	
	public abstract ActionForward process(ActionMapping mapping, BaseActionForm baseForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception;

	public void postprocess(ActionMapping mapping, BaseActionForm baseForm,
		HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			
	}

}