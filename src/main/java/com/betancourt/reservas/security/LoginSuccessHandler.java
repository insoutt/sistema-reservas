package com.betancourt.reservas.security;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;


@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler  {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		SessionFlashMapManager sessionFlashMapManager = new SessionFlashMapManager();
		FlashMap flashMap = new FlashMap();
		flashMap.put("success", "Bienvenido/a " + authentication.getName());
		sessionFlashMapManager.saveOutputFlashMap(flashMap, request, response);
		if(authentication !=  null) {
			logger.info("Has iniciado la sesión con éxito " + Calendar.getInstance().get(Calendar.SHORT_FORMAT));
		}		
		super.onAuthenticationSuccess(request, response, authentication);
	}	
	
}
