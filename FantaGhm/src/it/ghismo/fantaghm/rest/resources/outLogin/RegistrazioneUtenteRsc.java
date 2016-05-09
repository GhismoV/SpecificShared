package it.ghismo.fantaghm.rest.resources.outLogin;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import it.ghismo.common.rest.jaxb.msg.BeanType;
import it.ghismo.common.rest.resources.CommonRsc;
import it.ghismo.common.rest.support.Language;
import it.ghismo.common.rest.utils.MsgBeanUtil;
import it.ghismo.common.rest.utils.MsgBeanUtil.MsgBeanUtilCachingMode;

@Path("outlogin/userreg")

public class RegistrazioneUtenteRsc extends CommonRsc {
    public static final String RSC_NAME = "RegistrazioneUtenteRsc";
    private static final String RSC_BASE_TEMPLATE_NAME = "userreg";

	@Override
	public String getDescrizione() { return RSC_NAME; }

	/************ Template Names *************/
    private String getFormPageTemplateName() {
    	return RSC_BASE_TEMPLATE_NAME + "_form";
    }
    private String getReportPageTemplateName() {
    	return "generic_report";
    }
	/*****************************************/

    
	/************ Servizi Raggiungibili ************/
    @GET
    @Path("jget")
    @Produces(MediaType.APPLICATION_JSON)
    public BeanType json_getPage() {
    	preProcessReq(null, Language.LANGUAGE_DEFAULT);
    	return mainserv_json_getPage();
    }
    @GET
    @Path("jget/{lang}")
    @Produces(MediaType.APPLICATION_JSON)
    public BeanType json_getPage(@PathParam("lang") String langStr) {
    	preProcessReq(null, langStr);
    	return mainserv_json_getPage();
    }
	
    @GET
    @Path("wget")
    @Produces(MediaType.TEXT_HTML)
    public String web_getPage() {
    	preProcessReq(null, Language.LANGUAGE_DEFAULT);
    	return mainserv_web_getPage();
    }
    @GET
    @Path("wget/{lang}")
    @Produces(MediaType.TEXT_HTML)
    public String web_getPage(@PathParam("lang") String langStr) {
    	preProcessReq(null, langStr);
    	return mainserv_web_getPage();
    }
    
    @PUT
    @Path("jsend")
    @Produces(MediaType.APPLICATION_JSON)
    public BeanType json_send(BeanType beanRq) {
    	preProcessReq(beanRq);
    	return mainserv_json_send(beanRq);
    }
    
    @POST
    @Path("wsend")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED /* "application/x-www-form-urlencoded" */)
    @Produces(MediaType.TEXT_HTML)
    public String web_send(MultivaluedMap<String, String> formParams) {
    	preProcessReq(formParams);
    	return mainserv_web_send(getBeanInputFromFormParams(formParams));
    }    
	/*****************************************/
    
    
	/********** Servizi Principali non raggiungibili **********/
    public BeanType mainserv_json_getPage() {
    	BeanType beanRs = getTemplateJson(getFormPageTemplateName());
    	completeProcessReq(beanRs);
    	return beanRs;
    }
    public String mainserv_web_getPage() {
    	return getFreemarkerTemplateAndProcess(getFormPageTemplateName(), mainserv_json_getPage());
    }
    
    public BeanType mainserv_json_send(BeanType beanRq) {
    	BeanType beanRs = beanRq;
    	
    	MsgBeanUtil mbuRq = new MsgBeanUtil(beanRq, MsgBeanUtilCachingMode.Complete);
    	if(mbuRq.stdCheckFields()) {
    		// TODO : recupero dei campi 
    		// TODO : applicazione model con scritture DB
    		
        	beanRs =  getTemplateJson(getReportPageTemplateName());
    	}
    	
    	completeProcessReq(beanRs);
    	return beanRs;
    }

    
    public String mainserv_web_send(BeanType beanInCreated) {
    	return getFreemarkerTemplateAndProcess(getReportPageTemplateName(), mainserv_json_send(beanInCreated));
    }

    
	/******* Metodi per flusso funzione ******/
    @Override
    protected BeanType getLastReturnedJson() {
    	return getTemplateJson(getFormPageTemplateName());
    }
	/*****************************************/
    
}



