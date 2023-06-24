package com.Pk.Udemy.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
 private static List<User> users = new ArrayList<>();
 
 private static Integer usersCount = 0;
 	static {
 		users.add(new User(++usersCount,"Adam",LocalDate.now().minusYears(30)));
 		users.add(new User(++usersCount,"Josh",LocalDate.now().minusYears(20)));
 		users.add(new User(++usersCount,"Kaine",LocalDate.now().minusYears(40)));
 		users.add(new User(++usersCount,"Horchfueant",LocalDate.now().minusYears(10)));
 	}
 	public List<User> findAll(){
 		return users;
 	}
 	public User findOne(Integer id) {
 		Predicate<? super User> predicate = user -> 
 		user.getId().equals(id);
 		return users.stream().filter(predicate).findFirst().orElse(null);
 	}
 	public void deleteOne(Integer id) {
 		Predicate<? super User> predicate = user -> 
 		user.getId().equals(id);
 		users.removeIf(predicate);
 	}
 	public User save(User user) {
 		user.setId(++usersCount);
 		users.add(user);
 		return user;
 	}
}
