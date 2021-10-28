package mz.co.witchallenge.app.ws.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import mz.co.witchallenge.app.ws.exception.DividendoInvalidException;
import mz.co.witchallenge.app.ws.exception.OperacaoIncompletaException;
import mz.co.witchallenge.app.ws.service.CalculadoraService;
import mz.co.witchallenge.app.ws.ui.request.CalculadoraRequestDetails;
import mz.co.witchallenge.app.ws.ui.response.CalculadoraRest;
import mz.co.witchallenge.app.ws.ui.response.TipoOperacao;

@Service
public class CalculadoraServiceImpl implements CalculadoraService{

	private CalculadoraRest returnValue;
	
	@Override
	public CalculadoraRest somar(CalculadoraRequestDetails request) {

		if(request.getNumero1() == null || request.getNumero2() == null)
			throw new OperacaoIncompletaException();
		returnValue = new CalculadoraRest();
		BeanUtils.copyProperties(request, returnValue);
		returnValue.setResultado(request.getNumero1().add(request.getNumero2()));
		returnValue.setOperacao(TipoOperacao.ADICAO);
		return returnValue;
	}
	
	@Override
	public CalculadoraRest subtracao(CalculadoraRequestDetails request) {
		if(request.getNumero1() == null || request.getNumero2() == null)
			throw new OperacaoIncompletaException();
		returnValue = new CalculadoraRest();
		BeanUtils.copyProperties(request, returnValue);
		returnValue.setResultado(request.getNumero1().subtract(request.getNumero2()));
		returnValue.setOperacao(TipoOperacao.SUBTRACAO);
		return returnValue;
	}
	
	@Override
	public CalculadoraRest multiplicacao(CalculadoraRequestDetails request) {
		if(request.getNumero1() == null || request.getNumero2() == null)
			throw new OperacaoIncompletaException();
		returnValue = new CalculadoraRest();
		BeanUtils.copyProperties(request, returnValue);
		returnValue.setResultado(request.getNumero1().multiply(request.getNumero2()));
		returnValue.setOperacao(TipoOperacao.MULTIPLICACAO);
		return returnValue;
	}
	
	@Override
	public CalculadoraRest divisao(CalculadoraRequestDetails request) {
		if(request.getNumero1() == null || request.getNumero2() == null)
			throw new OperacaoIncompletaException();
		
		if(request.getNumero2().equals(new BigDecimal(0)) )
			throw new DividendoInvalidException();
		
		returnValue = new CalculadoraRest();
		BeanUtils.copyProperties(request, returnValue);
		returnValue.setResultado(request.getNumero1().divide(request.getNumero2()));
		returnValue.setOperacao(TipoOperacao.DIVISAO);
		return returnValue;
	}
}




