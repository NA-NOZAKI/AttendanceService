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

	// 全てのユーザー情報を取得する
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// 指定されたIDのユーザー情報を取得する
	public User getUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.orElse(null);
	}

	// 新しいユーザーを作成する
	public User createUser(User user) {
		return userRepository.save(user);
	}

	// 指定されたIDのユーザー情報を更新する
	public User updateUser(Long id, User userDetails) {
		User user = userRepository.findById(id)
				.orElse(null);
		if (user != null) {
			user.setUsername(userDetails.getUsername());
			user.setPassword(userDetails.getPassword());
			user.setDepartment(userDetails.getDepartment());
			user.setUserType(userDetails.getUserType());
			return userRepository.save(user);
		} else {
			return null;
		}
	}

	// 指定されたIDのユーザー情報を削除する
	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElse(null);
		if (user != null) {
			userRepository.delete(user);
		} else {
			// ユーザーが見つからない場合の処理
		}
	}
}
