package com.ITAcademy.M14DausMongo.services;

import java.util.List;

import com.ITAcademy.M14DausMongo.dto.User;

public interface IUserServices {
	
	public User creaUsuario(User user);
	public User selectUserById(Long id);
	public void eliminaUserById(Long id);
	public User jugadorMenorExito();
	public User jugadorMayorExito();
	public User updateUser(User user);
	public Boolean existeUser(String nomUser);
	public Boolean existeUserById(Long id);
	public List<User>	listUsers();
	public Boolean userAnonimo();
	public Long totalJugadores();
	public Double sumaPorcentajesExito();
	

}
