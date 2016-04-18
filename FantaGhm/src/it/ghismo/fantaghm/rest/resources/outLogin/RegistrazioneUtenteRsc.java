package it.ghismo.fantaghm.rest.resources.outLogin;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import freemarker.template.Template;
import it.ghismo.common.rest.jaxb.msg.BeanType;
import it.ghismo.common.rest.jaxb.msg.FieldType;
import it.ghismo.common.rest.resources.CommonRsc;
import it.ghismo.common.rest.utils.MsgBeanUtil;
import it.ghismo.common.rest.utils.MsgBeanUtil.MsgBeanUtilCachingMode;

@Path("outlogin/userreg")

public class RegistrazioneUtenteRsc extends CommonRsc {
    public static final String RSC_NAME = "RegistrazioneUtenteRsc";

	@Override
	public String getDescrizione() { return RSC_NAME; }

	
	/************ Servizi Mappati ************/
    @GET
    @Path("jget")
    @Produces(MediaType.APPLICATION_JSON)
    public BeanType json_getPage() {
    	preProcessReq();
    	return mainserv_json_getPage();
    }
	
    @GET
    @Path("wget")
    @Produces(MediaType.TEXT_HTML)
    public String web_getPage() {
    	preProcessReq();
    	return mainserv_web_getPage();
    }
    
    @PUT
    @Path("jsend")
    @Produces(MediaType.APPLICATION_JSON)
    public BeanType json_send(BeanType beanRq) {
    	preProcessReq();
    	return mainserv_json_send(beanRq);
    }
    
    @POST
    @Path("wsend")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED /* "application/x-www-form-urlencoded" */)
    @Produces(MediaType.TEXT_HTML)
    public String web_send(MultivaluedMap<String, String> formParams) {
        // Store the message
    	
    	BeanType beanInCreated = getTemplateJson("userreg_form");
    	MsgBeanUtil mbuCreated = new MsgBeanUtil(beanInCreated, MsgBeanUtilCachingMode.Complete);
    	
    	Set<Entry<String, List<String>>> entries = formParams.entrySet();
    	Iterator<Entry<String, List<String>>> entriesIter = entries.iterator();
    	Entry<String, List<String>> currEntry = null;
    	FieldType _currField = null;
    	while(entriesIter.hasNext()) {
    		currEntry = entriesIter.next();
    		_currField = mbuCreated.getField(currEntry.getKey());
    		if(_currField != null) {
    			if(currEntry.getValue() != null && !currEntry.getValue().isEmpty()) {
    				if(currEntry.getValue().size() == 1) {
    					_currField.setValue(currEntry.getValue().get(0));
    				} else {
    					for(String postValue : currEntry.getValue()) {
    						_currField.getValues().add(postValue);
    					}
    				}
    			}
    		}
    			
    	}
    	return mainserv_web_send(beanInCreated);
    }    
	/*****************************************/
    
    
	/********** Servizi non Mappati **********/
    public BeanType mainserv_json_getPage() {
    	BeanType ris = getTemplateJson("userreg_form");
    	return ris;
    }
    public String mainserv_web_getPage() {
    	preProcessReq();
    	String ris = null;
    	BeanType bean = mainserv_json_getPage();
    	
    	Template t;
		try {
			t = getFreemarkerTemplate("userreg_form");
	    	StringWriter sw = new StringWriter();
	    	
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("bean", bean);
	    	
	    	t.process(map, sw);
	    	ris = sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ris;
    }
    
    public BeanType mainserv_json_send(BeanType beanRq) {
    	BeanType beanRs = getTemplateJson("userreg_report");
    	
    	MsgBeanUtil mbuRq = new MsgBeanUtil(beanRq, MsgBeanUtilCachingMode.Complete);
    	FieldType _username = mbuRq.getField("username");
    	
    	System.out.println("gggv - username = " + _username.getValue());
    	
    	MsgBeanUtil mbuRs = new MsgBeanUtil(beanRs, MsgBeanUtilCachingMode.Complete);
    	FieldType _pippero = mbuRs.getField("pippero");
    	_pippero.setValue("pippero_" + _username.getValue());
    	
    	return beanRs;
    }

    public String mainserv_web_send(BeanType beanRq) {
    	preProcessReq();
    	String ris = null;
    	BeanType bean = mainserv_json_send(beanRq);
    	
    	Template t;
		try {
			t = getFreemarkerTemplate("userreg_report");
	    	StringWriter sw = new StringWriter();
	    	
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	map.put("bean", bean);
	    	
	    	t.process(map, sw);
	    	ris = sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ris;
    }
    
	/*****************************************/
    
}



