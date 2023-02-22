package airport.cli;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import airport.entities.Traveler;
import airport.service.TravelerService;

@Controller
public class SecurityController {
	@Autowired
	private TravelerService service;
	
	@RequestMapping("/")
	public String showLoginForm(Model model, HttpServletRequest request) {
		Traveler user = new Traveler();

		// Add the user object as a model attribute
		model.addAttribute("user", user);

		String param = request.getParameter("incorrect");
		if(param != null)
			model.addAttribute("message", "incorrect username, please try again");

		return "login-page";
	}

	@RequestMapping("/processLogin")
	public String processLogin(@ModelAttribute("user")Traveler user, HttpServletRequest request) {
		System.out.println(user);
		// minimum 3 characters long
		if (user.getFullName().length() >= 3)
		{
			try { // check if user exist already
				Traveler t = service.findTraveler(user.getFullName());
				service.setTraveler(t);
				return "redirect:/showMainScreen";
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return "redirect:/?incorrect=true";
			}
		}		
		return "redirect:/?incorrect=true";
	}
	
	@RequestMapping("/register")
	public String showRegisterForm(Model model, HttpServletRequest request) {
		Traveler user = new Traveler();

		// Add the user object as a model attribute
		model.addAttribute("user", user);

		String param = request.getParameter("incorrect");
		if(param != null)
			model.addAttribute("message", "username already exist, please try again");

		return "register";
	}

	@RequestMapping("/processRegister")
	public String processRegister(@ModelAttribute("user")Traveler user, HttpServletRequest request) {
		System.out.println(user);
		// minimum 3 characters long
		if (user.getFullName().length() >= 3)
		{
			try { // check if user exist already
				service.setTraveler(service.findTraveler(user.getFullName()));
				return "redirect:/register?incorrect=true";
			}catch(Exception e)
			{
				try {
					service.saveTraveler(user);
					service.setTraveler(user);	
					return "redirect:/showMainScreen";
				}catch(Exception e2)
				{
					e2.printStackTrace();
				}
			}
		}		
		return "redirect:/register?incorrect=true";
	}
}