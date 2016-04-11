package it.ghismo.fantaghm.rest.resources;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import freemarker.template.Template;
import it.ghismo.common.rest.jaxb.msg.BeanType;
import it.ghismo.common.rest.resources.CommonRsc;
import it.ghismo.common.rest.servlets.CommonRestServlet;
import it.ghismo.common.rest.support.Log;

@Path("prova")

public class ProvaRsc extends CommonRsc {
    public static final String RSC_NAME = "ProvaRsc";

    
	@Override
	public String getDescrizione() {
		return RSC_NAME;
	}

 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("pippo")
    public BeanType getPippo() {
    	Log.info("Prova log pippo");
    	BeanType ris = getTemplateJson("prova");
    	
    	return ris;
    }
	
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("free")
    public String getFreemarker() {
    	String ris = null;
    	Log.info("Prova log getFreemarker");
    	
    	Template t;
		try {
			//CommonRestServlet.getFreemarkerConfiguration().setSharedVariable("ghismo", "strappattero"); // oppure map
			//t = CommonRestServlet.getFreemarkerConfiguration().getTemplate("default/prova.ftl");
			t = CommonRestServlet.getFreemarkerConfiguration().getTemplate("prova");
	    	StringWriter sw = new StringWriter();
	    	
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("ghismo", "strappattero2");
	    	
	    	t.process(map, sw);
	    	ris = sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	return ris;
    }
	
    
}



