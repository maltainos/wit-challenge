package mz.co.witchallenge.app.ws.service;

import mz.co.witchallenge.app.ws.ui.request.CalculadoraRequestDetails;
import mz.co.witchallenge.app.ws.ui.response.CalculadoraRest;

public interface CalculadoraService {
	
	public CalculadoraRest somar(CalculadoraRequestDetails requestDetails);
	public CalculadoraRest subtracao(CalculadoraRequestDetails requestDetails);
	public CalculadoraRest multiplicacao(CalculadoraRequestDetails requestDetails);
	public CalculadoraRest divisao(CalculadoraRequestDetails requestDetails);
	
}
