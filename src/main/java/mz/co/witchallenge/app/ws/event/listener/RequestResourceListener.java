package mz.co.witchallenge.app.ws.event.listener;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import mz.co.witchallenge.app.ws.event.RequestResourceEvent;

@Component
public class RequestResourceListener implements ApplicationListener<RequestResourceEvent>{

	@Override
	public void onApplicationEvent(RequestResourceEvent event) {
		HttpServletResponse response = event.getResponse();
		String resourceId = event.getResourceId();
		
		
		addHeaderToResponse(response, resourceId);
	}

	private void addHeaderToResponse(HttpServletResponse response, String resourceId) {
		UUID.randomUUID();
		/*
		 * URI uri = ServletUriComponentsBuilder .fromCurrentRequestUri()
		 * .path("/{requestId}") .buildAndExpand(resourceId).toUri();
		 */
		response.addHeader("UUIDUniqueId", UUID.randomUUID().toString());
		response.addHeader("requestId", resourceId);
	}

}



















