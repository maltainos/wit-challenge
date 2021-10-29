package mz.co.witchallenge.app.ws.controller;

import java.net.URI;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mz.co.witchallenge.app.ws.event.RequestResourceEvent;
import mz.co.witchallenge.app.ws.exception.DividendoInvalidException;
import mz.co.witchallenge.app.ws.exception.OperacaoIncompletaException;
import mz.co.witchallenge.app.ws.mq.config.MQConfig;
import mz.co.witchallenge.app.ws.service.impl.CalculadoraServiceImpl;
import mz.co.witchallenge.app.ws.shared.MyUtils;
import mz.co.witchallenge.app.ws.ui.request.CalculadoraRequestDetails;
import mz.co.witchallenge.app.ws.ui.response.CalculadoraRest;
import mz.co.witchallenge.app.ws.ui.response.MessagemErro;

//http://localhost:8081/wit-challenge/api/calculadora
@RestController
@RequestMapping(path = "/calculadora")
public class CalculadoraRestController {
	
	@Autowired
	private MyUtils myUtils;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CalculadoraServiceImpl calculadoraService;
	
	@Autowired
	private RabbitTemplate template;
	
	Logger logger = LoggerFactory.getLogger(CalculadoraRestController.class);
	
	//http://localhost:8080/wit-challenge/api/calculadora/sum?numero1=VALOR&numero2=VALOR
	@GetMapping(path = "/sum", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CalculadoraRest> somar(CalculadoraRequestDetails request, HttpServletResponse response) {
		setUrlAndLoggerInfo(HttpMethod.GET);
		CalculadoraRest returnValue = calculadoraService.somar(request);
		publisher.publishEvent(new RequestResourceEvent(this, response, myUtils.generatedString(30)));
		template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, returnValue);
		return ResponseEntity.ok(returnValue);
	}
	
	//http://localhost:8080/wit-challenge/api/calculadora/sub?numero1=VALOR&numero2=VALOR
	@GetMapping(path = "/sub", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CalculadoraRest> subtracao(CalculadoraRequestDetails request, HttpServletResponse response) {
		setUrlAndLoggerInfo(HttpMethod.GET);
		CalculadoraRest returnValue = calculadoraService.subtracao(request);
		publisher.publishEvent(new RequestResourceEvent(this, response, myUtils.generatedString(30)));
		return ResponseEntity.ok(returnValue);
	}
	
	//http://localhost:8080/wit-challenge/api/calculadora/mult?numero1=VALOR&numero2=VALOR
	@GetMapping(path = "/mult", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CalculadoraRest> multiplicacao(CalculadoraRequestDetails request, HttpServletResponse response) {
		setUrlAndLoggerInfo(HttpMethod.GET);
		CalculadoraRest returnValue = calculadoraService.multiplicacao(request);
		publisher.publishEvent(new RequestResourceEvent(this, response, myUtils.generatedString(30)));
		return ResponseEntity.ok(returnValue);
	}
	
	//http://localhost:8080/wit-challenge/api/calculadora/div?numero1=VALOR&numero2=VALOR
	@GetMapping(path = "/div", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CalculadoraRest> divisao(CalculadoraRequestDetails request, HttpServletResponse response) {
		setUrlAndLoggerInfo(HttpMethod.GET);
		CalculadoraRest returnValue = calculadoraService.divisao(request);
		publisher.publishEvent(new RequestResourceEvent(this, response, myUtils.generatedString(30)));
		return ResponseEntity.ok(returnValue);
	}
	
	@ExceptionHandler({ DividendoInvalidException.class })
	public ResponseEntity<Object> handleDividendoInvalidException(DividendoInvalidException ex, 
			WebRequest request){
		
		MessagemErro error = new MessagemErro();
		error.setMensagem(messageSource.getMessage("dividendo-zero", null, LocaleContextHolder.getLocale()));
		error.setException(ex.getLocation());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusName(HttpStatus.BAD_REQUEST.toString());
		error.setTimestamp(LocalDateTime.now());
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler({ OperacaoIncompletaException.class })
	public ResponseEntity<Object> handleOperacaoIncompletaException(OperacaoIncompletaException ex, 
			WebRequest request){
		
		MessagemErro error = new MessagemErro();
		error.setMensagem(messageSource.getMessage("operacao-negada", null, LocaleContextHolder.getLocale()));
		error.setException(ex.getLocation());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusName(HttpStatus.BAD_GATEWAY.toString());
		error.setTimestamp(LocalDateTime.now());
		return ResponseEntity.badRequest().body(error);
	}
	
	
	@ExceptionHandler({ArithmeticException.class})
	public ResponseEntity<MessagemErro> handleArithmeticException(ArithmeticException ex, WebRequest request){
		MessagemErro error = new MessagemErro();
		error.setMensagem(messageSource.getMessage("non-terminating", null, LocaleContextHolder.getLocale()));
		error.setException(ex.getClass().toString());
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusName(HttpStatus.BAD_GATEWAY.toString());
		error.setTimestamp(LocalDateTime.now());
		return ResponseEntity.badRequest().body(error);
	}
	
	private void setUrlAndLoggerInfo(HttpMethod method) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.buildAndExpand("").toUri();
		logger.info(method +" "+uri.toASCIIString());
	}
	
}



























