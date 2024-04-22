package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// 特定の部署に所属するユーザーを取得するメソッド
	List<User> findByDepartment(String department);

	// 特定のユーザータイプのユーザーを取得するメソッド
	List<User> findByUserType(String userType);

}
