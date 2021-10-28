package mz.co.witchallenge.app.ws.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class RequestResourceEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private String resourceId;
	
	public RequestResourceEvent(Object source, HttpServletResponse response, 
			String resourceId) {
		super(source);
		this.response = response;
		this.resourceId = resourceId;
		
	}

}


















