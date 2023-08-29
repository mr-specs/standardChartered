package com.standardDemo.standardChartered.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.standardDemo.standardChartered.entities.User;
import com.standardDemo.standardChartered.service.TransactionService;
import com.standardDemo.standardChartered.service.UserAccountService;
import com.standardDemo.standardChartered.service.UserService;

@Controller
public class WebController {
	@Autowired
	UserService userService;

	@Autowired
	UserAccountService userAccountService;

	@Autowired
	TransactionService transactionService;

	// working
	@GetMapping(value = "/login")
	public String index(Model model) {
		return "index";
	}
	// @RequestMapping(value = "/dashboard")
	// public String dashboard(Model model) {
	// return "dashboard";
	// }

	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model) {
		String username = user.getUsername();
		String password = user.getPassword();

		if (!username.isEmpty()) {
			User user1 = userService.findByUsername(username);
			// Check if the provided credentials are valid
			if (user1 != null && user1.getPassword().equals(password)) {
				// Redirect to the new page after successful login
				String redashboard = "redirect:/dashboard/" + user1.getUserId();
				return redashboard; // Replace "dashboard" with the URL of your desired page
			} else {
				// Display an error message for invalid credentials
				model.addAttribute("error", "Invalid username or password.");
				return "index"; // Return the login page view name
			}
		}
		model.addAttribute("error", null);
		return "index";
	}

	@GetMapping(value = "/dashboard/{id}")
	public ModelAndView dashboard(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("/dashboard");
		mav.addObject("userAccount", userAccountService.getUsersById(id));
		return mav;
	}

	/*//
	 * @GetMapping(value = "/gettransaction/{accountId}/{userId}") public String
	 * index(@PathVariable("accountId") int accountId,@PathVariable("userId") int
	 * userId,Model model) { try { String a =
	 * "redirect:/transactions/"+accountId+"/"+userId; return a; }catch (Exception
	 * e) { e.printStackTrace(); } return null; }
	 * 
	 * @GetMapping(value = "/transactions/{accountId}/{userId}") public ModelAndView
	 * transactions(@PathVariable("accountId") int accountId,@PathVariable("userId")
	 * int userId) { try { ModelAndView mav = new ModelAndView("/transactions");
	 * mav.addObject("transactions",
	 * transactionService.getTransactionDetById(accountId,userId)); return mav;
	 * }catch (Exception e) { e.printStackTrace(); } return null; }
	 */

	@PostMapping("/submitAction")
	public String handleFormSubmission(@ModelAttribute("selectedAccountNumber") @RequestParam String selectedValue) {
		// Process the selectedValue on the server-side as needed
		String a = "redirect:/gettransaction/" + selectedValue;
		return a;
	}

	@GetMapping(value = "/gettransaction/{accountNumber}")
	public String index(@PathVariable("accountNumber") Long accountNumber, Model model) {
		String a = "redirect:/transactions/" + accountNumber;
		return a;
	}

	@GetMapping(value = "/transactions/{accountNumber}")
	public ModelAndView transactions(@PathVariable("accountNumber") Long accountNumber) {
		ModelAndView mav = new ModelAndView("/transactions");
		mav.addObject("transactions", transactionService.getTransactionDetByAccountNumber(accountNumber));
		mav.addObject("selectedDropDownValue", accountNumber);
		return mav;
	}

}