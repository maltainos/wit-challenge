package mz.co.witchallenge.app.ws.ui.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculadoraRequestDetails {

	private BigDecimal numero1;
	private BigDecimal numero2;
}
