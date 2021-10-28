package mz.co.witchallenge.app.ws.exception;

import lombok.Getter;

@Getter
public class DividendoInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String location = "mz.co.witchallenge.app.ws.exception.DividendoInvalidException";

}
