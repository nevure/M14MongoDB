/**
 * 
 */
package com.ITAcademy.M14DausMongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITAcademy.M14DausMongo.dao.UserDao;
import com.ITAcademy.M14DausMongo.dto.User;

/**
 * Clase Servicio del repositorio de User
 * @author ru
 *
 */
@Service
public class UserService implements IUserServices {

	//inyectamos el repositorio
	@Autowired
	UserDao userDao;
	
	@Override
	public User creaUsuario(User user) {
		return userDao.save(user);
	}

	@Override
	public User selectUserById(Long id) {
		return userDao.findByCodigo(id);
	}

	@Override
	public void eliminaUserById(Long id) {
		userDao.deleteByCodigo(id);
	}

	@Override
	public User jugadorMenorExito() {
		return userDao.findTopByOrderByPercentExitAsc();
	}

	@Override
	public User jugadorMayorExito() {
		return userDao.findTopByOrderByPercentExitDesc();
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existeUser(String nomUsuari) {
		return userDao.existsByNomUsuari(nomUsuari);
	}

	@Override
	public Boolean existeUserById(Long id) {
		return userDao.existsByCodigo(id) ;
	}

	@Override
	public List<User> listUsers() {
		return userDao.findAll();
	}

	@Override
	public Boolean userAnonimo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long totalJugadores() {
		return userDao.count();
	}


	@Override
	public Double sumaPorcentajesExito() {
		return userDao.findAll().stream().mapToDouble(user -> user.getPercentExit())
				.sum();
	}


}
