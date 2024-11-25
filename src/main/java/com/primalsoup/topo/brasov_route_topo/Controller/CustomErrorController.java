package com.primalsoup.topo.brasov_route_topo.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
		Object status = response.getStatus();

		if (status != null) {
			int statusCode = Integer.parseInt(status.toString());
			model.addAttribute("errorCode", statusCode);

			String errorMessage = switch (statusCode) {
			case 404 -> "Pagina solicitata nu a fost gasita.";
			case 500 -> "A aparut o eroare interna pe server.";
			default -> "A aparut o eroare neprevazuta.";
			};
			model.addAttribute("errorMessage", errorMessage);
		}
		model.addAttribute("body", "page-error");

		return "main-layout";
	}

}
