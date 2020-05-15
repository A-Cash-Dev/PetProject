package com.Spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllers {
	@Autowired
	UserRepository ur;

	@GetMapping("/User")
	public User getUser() {
		return new User();
	}

	@PostMapping("/Signup")
	public String signUp(@RequestBody User user) {
		int generatedUserId = ur.createUser(user);
		if (generatedUserId > 0) {
			return "Signed Up successfully with user id " + generatedUserId;
		}
		return null;
	}

	@PostMapping("/Signin")
	public String signIn(@RequestParam("id") int userId,
			@RequestParam("pwd") String password) {
		User user = new User();
		user.setUserid(userId);
		user.setPassword(password);
		String msg = ur.verifyCredentials(user);
		if ("Incorrect user id or password.".equals(msg)) {
			return "Incorrect user id or password.";
		}
		return msg;
	}
	
	@GetMapping("CountSignedInUsers")
	public String countNumberOfUsersLoggedIn() {
		Long countofUsersLoggedIn = ur.countLoggedInUsers();
		if (countofUsersLoggedIn == 0) {
			return "No user logged in right now";
		}
		return "Number of users logged in = "+countofUsersLoggedIn;
	}
	
	@GetMapping("SignedUpUsers")
	public String countNumberOfUsersSignedUp() {
		List<User> usersSignedUp = ur.signedUpUsers();
		if (usersSignedUp==null) {
			return "No user Signed up right now";
		}
		return usersSignedUp.toString();
	}
	
	
}
