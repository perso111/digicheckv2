package com.sterling.digicheck.bank.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.sterling.digicheck.state.entity.StateEntity;
import com.sterling.digicheck.state.service.IStateService;

@ManagedBean(name="statesBean")
@RequestScoped
public class StateManagedBean {

	@ManagedProperty("#{stateService}")
	private IStateService stateService;
		
	public void showStates(){
		for (StateEntity state : stateService.getStates()) {
			System.out.println("ID: " + state.getCode());
			System.out.println("Name: " + state.getName());
		}
	}
	
	public void setStateService(IStateService stateService) {
		this.stateService = stateService;
	}			
}
