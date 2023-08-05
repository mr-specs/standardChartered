package com.standardDemo.standardChartered.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.standardDemo.standardChartered.entities.User;
import com.standardDemo.standardChartered.sevices.UserService;

@Controller
public class WebController {
	 @Autowired
	 UserService userService;
	 //working
	 @GetMapping(value = "/login")
   public String index(Model model) {
      return "index";
   }
//	 @RequestMapping(value = "/dashboard")
//	   public String dashboard(Model model) {
//	      return "dashboard";
//	   }

   @PostMapping("/login")
   public String login(@ModelAttribute("user") User user, Model model) {
       String username = user.getUsername();
       String password = user.getPassword();
       
       User user1 = userService.findByUsername(username);
       // Check if the provided credentials are valid
       System.out.println("sdds0000");
       if (user1 != null && user1.getPassword().equals(password)) {
           // Redirect to the new page after successful login
           System.out.println("sdds");
           String a ="redirect:/dashboard/"+user1.getUserId();
           
           return a; // Replace "dashboard" with the URL of your desired page
       } else {
           // Display an error message for invalid credentials
           System.out.println("sdd0000s");

           model.addAttribute("error", "Invalid username or password.");
           return "index"; // Return the login page view name
       }
   }
   
   @GetMapping(value = "/dashboard/{id}")
   public ModelAndView dashboard(@PathVariable("id") int id) {
       ModelAndView mav = new ModelAndView("/dashboard");
       mav.addObject("messages", userService.getUsersById(id));
       return mav;
   }
  
}