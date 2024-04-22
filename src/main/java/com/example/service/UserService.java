package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.orElse(null);
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(Long id, User userDetails) {
		User user = userRepository.findById(id)
				.orElse(null);
		if (user != null) {
			// Update the user details
			// user.setUsername(userDetails.getUsername());
			// user.setEmail(userDetails.getEmail());
			// user.setEmploymentType(userDetails.getEmploymentType());
			// user.setDepartment(userDetails.getDepartment());
			// user.setPosition(userDetails.getPosition());
			// user.setUserType(userDetails.getUserType());
			// 他のフィールド...
			return userRepository.save(user);
		} else {
			return null; // or throw an exception
		}
	}

	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElse(null);
		if (user != null) {
			userRepository.delete(user);
		} else {
			// handle the case where user is not found
		}
	}
}
