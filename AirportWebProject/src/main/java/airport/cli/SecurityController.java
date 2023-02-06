package airport.cli;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/")
	public String showLoginForm(Model model, HttpServletRequest request) {
		User user = new User();

		// Add the user object as a model attribute
		model.addAttribute("user", user);

		String param = request.getParameter("incorrect");
		if(param != null)
			model.addAttribute("message", "Username and password are empty or password contains characters, please try again");

		return "login-page";
	}

	@RequestMapping("/processLogin")
	public String processLogin(@ModelAttribute("user") User user, HttpServletRequest request) {
		System.out.println(user);

		if (user.getPassword().length() > 0 && user.getUsername().length() > 0 && checkPassword(user.getPassword())) {
			// create session and put user object on session
			request.getSession().setAttribute("user", user);
			return "redirect:/showMainScreen";
		}		
		return "redirect:/?incorrect=true";
	}
	private boolean checkPassword(String password)
	{
		try {
			int check = Integer.parseInt(password);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	/*@RequestMapping("/showMainScreen")
	public String showMainScreen(HttpServletRequest request) {
		
		if(request.getSession().getAttribute("user") == null)
			// user has no data on session
			return "redirect:/";
		
		return "main-screen"; // change to our main page
	}*/

}
