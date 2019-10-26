package com.ramoncosta.cursomc.service;

import org.springframework.security.core.context.SecurityContextHolder;
import com.ramoncosta.cursomc.security.UserSS;

public class UserService {
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception erro) {
			return null;
		}		
	}
}
