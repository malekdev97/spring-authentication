package com.spring.implementation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.implementation.DTO.UserDTO;
import com.spring.implementation.DTO.UserLoginDTO;
import com.spring.implementation.model.User;
import com.spring.implementation.repository.UserRepository;
import com.spring.implementation.service.EmailService;
import com.spring.implementation.service.UserDetailsServiceImpl;

@Controller
public class UserController {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	EmailService emailService;

	@Autowired
	UserRepository userRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@GetMapping("/register")
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping("/register")
	public String saveUser(@ModelAttribute UserDTO userDTO) {
		User user = userDetailsService.save(userDTO);
		if (user != null)
			return "redirect:/login";
		else
			return "redirect:/register";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	public void login(@ModelAttribute UserLoginDTO userLoginDTO, Model model) {
		userDetailsService.loadUserByUsername(userLoginDTO.getUsername());
	}

	@GetMapping("/userDashboard")
	public String showUserDashboardForm(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
			User user = userRepository.findByEmail(username);
			model.addAttribute("user", user);
		}

		return "userDashboard";
	}

}
