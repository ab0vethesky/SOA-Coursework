package ws;
/**
 * 
 * @author Oladipo Oyekanmi
 *
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.text.MessageFormat;

import javax.ws.rs.client.Client; 
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget; 
import javax.ws.rs.core.MediaType;


public class Test 
{

	
	private static final String BASE_URI ="https://campus.cs.le.ac.uk/tyche/CO7214Rest/rest/soa";

	public static final String INIT_PATH = "initialise";
    @SuppressWarnings("unused")
	private static final String SHOW_PATH = "showReport";
    @SuppressWarnings("unused")
	private static final String SUB_PATH = "submit";
    @SuppressWarnings("unused")
	private static final String REV_PATH = "revise";
   
	public static String passCode="oo1640";
	//Chages the values here to initialize database for users and supervisors
	
	public static String users = "Abdullah_Reiko,Ahmad_Thomas,Mohammed_Reiko,Bello_Reiko,Hao_Yi,Dipo_Reiko,Ade_Reiko,Ola_Reiko,Bill_Reiko,Dipo_Yi,Dipo_Thomas" ;
	
	//This string stores the user for the current session whose report is to be marked
	public static String sessionUser="Abdullah";
	
	//This string stores the Supervisor for the current session whose report is to be marked
	public static String sessionSpr="Thomas";
	
	//This stores the mark to be submitted
	public static double sessionMrk = 80.05;

	public static void main(String[] args) 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		Calendar cal = Calendar.getInstance(); 
			 
		//Print Access time 
		System.out.println("Accessed: " + dateFormat.format(cal.getTime()));
		System.out.println("passCode: " + passCode);
		System.out.println("Student for this session is: " +sessionUser);

        // TODO Auto-generated method stub
		
		Boolean ini_ = initialize(passCode,users);
		//System.out.println("initialise return : " + ini_);
	   
		String rpt=submit(passCode,sessionUser);
		String com = comment(passCode, rpt);	
		//String com2 = comment(passCode, rpt);
		String rev1 =revise(passCode,rpt);    
		String rev2 =revise(passCode,rpt); 		
		String fin = finalise(passCode, rpt);
		Double mrk = mark(passCode, com,rpt);


		System.out.println("Submit return :" + rpt);   
		System.out.println("Comment 1 return :" + com);
		//  System.out.println("Comment 1 return :" + com);
		System.out.println("revise 1 return :" + rev1);    
		System.out.println("revise 2 return :" + rev2);   
		System.out.println("Finalise return :" + fin);
		System.out.println("Mark return :" + mrk);
		System.out.println("Show Report : "+showReport(passCode,rpt));	   
		System.out.println("Feedback : "+showFeedback(passCode,com));
		//System.out.println("Feedback : "+showFeedback(passCode,com2));
		System.out.println("Student Report : "+showStudentReports(passCode,rpt));
		System.out.println("showSupervisorFeedbacks : "+showSupervisorFeedbacks(passCode,sessionSpr));		
		 
		 
	}
	
	public static boolean initialize(String passCode, String users) 
	{
		//System.out.println("I got here domain");
		
		
		try
		{
			Client client = ClientBuilder.newClient(); 
			WebTarget target =	client.target(BASE_URI+MessageFormat.format("/initialise/"+passCode+"/"+users, new Object[] {}));
		
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(boolean.class);
			
			//return true;
		}
		catch(Exception e)
		{
			//System.out.println("Initialize Error " + e);
			//return target.request(MediaType.APPLICATION_JSON_TYPE).get(Boolean.class);
			return false;
			
		}
	}
	public static String submit(String passCode, String sessionUser)
	{     
		try{
		Client client = ClientBuilder.newClient(); 
		
		WebTarget target =	client.target(BASE_URI+MessageFormat.format("/submit/"+passCode+"/"+sessionUser, new Object[] {}));
		//System.out.println("Submit URI: " +target);
		
		return target.request(MediaType.APPLICATION_JSON).get(String.class);
		}
		catch(Exception e)
		{
			System.out.println("Submit Error: " + e);
			return null;
		}
	}
	private static String comment(String passCode, String rpt) {
	// TODO Auto-generated method stub
		try{
			Client client = ClientBuilder.newClient(); 
			//WebTarget target =	client.target(BASE_URI+MessageFormat.format("/submit/"+passCode+"/Ahmad", new Object[] {passCode, "Ahmad"}));
			WebTarget target =	client.target(BASE_URI+MessageFormat.format("/comment/"+passCode+"/"+sessionSpr+"/"+rpt, new Object[] {}));
			
			return target.request(MediaType.APPLICATION_JSON).get(String.class);
			}
			catch(Exception e)
			{
				System.out.println("Comment Error: " + e);
				return null;
			}
}
	
	private static String revise(String passCode, String rpt) {
		try{
			Client client = ClientBuilder.newClient(); 
			
			WebTarget target =	client.target(BASE_URI+MessageFormat.format("/revise/"+passCode+"/"+rpt, new Object[] {}));
			//WebTarget target =	client.target(BASE_URI+MessageFormat.format("/revise/"+passCode+"/doc63", new Object[] {}));
			//System.out.println("Revise URI: " +target);
			
			return target.request(MediaType.APPLICATION_JSON).get(String.class);
			}
			catch(Exception e)
			{
				System.out.println("Revise Error: " + e);
				return null;
			}
	}
	private static String finalise(String passCode, String rpt) {
	// TODO Auto-generated method stub
		try{
			Client client = ClientBuilder.newClient(); 
			//WebTarget target =	client.target(BASE_URI+MessageFormat.format("/submit/"+passCode+"/Ahmad", new Object[] {passCode, "Ahmad"}));
			WebTarget target =	client.target(BASE_URI+MessageFormat.format("/finalise/"+passCode+"/"+rpt, new Object[] {}));
			//System.out.println("finalise URI: " +target);
			
			return target.request(MediaType.APPLICATION_JSON).get(String.class);
			}
			catch(Exception e)
			{
				System.out.println("Finalise Error: " + e);
				return null;
			}
}
	private static double mark(String passCode, String rpt, String com) {
		// TODO Auto-generated method stub
				try{
					Client client = ClientBuilder.newClient(); 
					//WebTarget target =	client.target(BASE_URI+MessageFormat.format("/submit/"+passCode+"/Ahmad", new Object[] {passCode, "Ahmad"}));
					//WebTarget target =	client.target(BASE_URI+MessageFormat.format("/mark/"+passCode+"/"+rpt+"/"+com+"/"+sessionMrk, new Object[] {}));
					WebTarget target =	client.target(BASE_URI+MessageFormat.format("/mark/"+passCode+"/"+rpt+"/"+sessionMrk, new Object[] {}));
					//System.out.println("mark URI: " +target);
					
					//return target.request(MediaType.TEXT_PLAIN).get(Double.class);
					return target.request(MediaType.APPLICATION_JSON_TYPE).get(double.class);
					
					}
					catch(Exception e)
					{
						System.out.println("");
						return (0.00) ;
					}
	}
	private static String showStudentReports(String passCode, String rpt) {
		// TODO Auto-generated method stub
		try{
			Client client = ClientBuilder.newClient(); 
			//WebTarget target =	client.target(BASE_URI+MessageFormat.format("/submit/"+passCode+"/Ahmad", new Object[] {passCode, "Ahmad"}));
			WebTarget target =	client.target(BASE_URI+MessageFormat.format("/showStudentReports/"+passCode+"/"+sessionUser, new Object[] {}));
			//System.out.println("showSupervisorFeedbacks URI: " +target);
			//return target.request(MediaType.TEXT_PLAIN).get(String.class);
			return target.request(MediaType.APPLICATION_JSON).get(String.class);
			}
			catch(Exception e)
			{
				System.out.println("showStudentReports Error: " + e);
				return null;
			}
	}
	private static String showFeedback(String passCode, String rpt) {
		// TODO Auto-generated method stub
		try{
			Client client = ClientBuilder.newClient(); 
			//WebTarget target =	client.target(BASE_URI+MessageFormat.format("/submit/"+passCode+"/Ahmad", new Object[] {passCode, "Ahmad"}));
			WebTarget target =	client.target(BASE_URI+MessageFormat.format("/showFeedback/"+passCode+"/"+rpt, new Object[] {}));
			//System.out.println("showSupervisorFeedbacks URI: " +target);
			//return target.request(MediaType.TEXT_PLAIN).get(String.class);
			return target.request(MediaType.APPLICATION_JSON).get(String.class);
			}
			catch(Exception e)
			{
				System.out.println("showFeedback Error: " + e);
				return null;
			}
	}
	

	public static String showSupervisorFeedbacks(String passCode, String rpt) {
		// TODO Auto-generated method stub
		try{
			Client client = ClientBuilder.newClient(); 
			//WebTarget target =	client.target(BASE_URI+MessageFormat.format("/submit/"+passCode+"/Ahmad", new Object[] {passCode, "Ahmad"}));
			WebTarget target =	client.target(BASE_URI+MessageFormat.format("/showSupervisorFeedbacks/"+passCode+"/"+sessionSpr, new Object[] {}));
			//System.out.println("showSupervisorFeedbacks URI: " +target);
			//return target.request(MediaType.TEXT_PLAIN).get(String.class);
			return target.request(MediaType.APPLICATION_JSON).get(String.class);
			}
			catch(Exception e)
			{
				System.out.println("showSupervisorFeedbacks Error: " + e);
				return null;
			}
	}


	

	private static String showReport(String passCode, String rpt) 
	{
		// TODO Auto-generated method stub
		try{
			Client client = ClientBuilder.newClient(); 
			//WebTarget target =	client.target(BASE_URI+MessageFormat.format("/showReport/"+passCode+"/"+rpt, new Object[] {passCode, rpt}));
			WebTarget target =	client.target(BASE_URI+MessageFormat.format("/showReport/"+passCode+"/"+rpt, new Object[] {}));
			//System.out.println("showReport URI: " +target);
			//return target.request(MediaType.TEXT_PLAIN).get(String.class);
			return target.request(MediaType.APPLICATION_JSON).get(String.class);
			}
			catch(Exception e)
			{
				System.out.println("showReport Error: " + e);
				return null;
			}
	}
     
}
