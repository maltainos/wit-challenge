package mz.co.witchallenge.app.ws.ui.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculadoraRest {

	private BigDecimal numero1;
	private BigDecimal numero2;
	private BigDecimal resultado;
	private TipoOperacao operacao;
}
