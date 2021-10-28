package mz.co.witchallenge.app.ws.ui.response;

import java.time.LocalDateTime;

import org.springframework.web.context.request.WebRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessagemErro {

	private String mensagem;
	private int statusCode;
	private String statusName;
	private LocalDateTime timestamp;
	private String exception;
	private WebRequest request;
	
}
