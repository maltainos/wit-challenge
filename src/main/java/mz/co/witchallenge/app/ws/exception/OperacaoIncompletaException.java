package mz.co.witchallenge.app.ws.exception;

import lombok.Getter;

@Getter
public class OperacaoIncompletaException extends RuntimeException {

	private final String location = "mz.co.witchallenge.app.ws.exception.OperacaoIncompletaException";
	private static final long serialVersionUID = 1L;

}
