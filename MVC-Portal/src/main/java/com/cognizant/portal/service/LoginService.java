package com.cognizant.portal.service;
import com.cognizant.portal.model.Login;


public interface LoginService {

	String validateUserNameAndPassword(Login login);

	String getUsername(Login login);

}
