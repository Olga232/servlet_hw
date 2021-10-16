package ua.itea.services.mapper;

import ua.itea.models.User;

public class UserMapper {
	
	public static User mapToUser(String login, String name, String password, String gender, String region, String comment) {
		User user = new User();
		user.setLogin(login);
		user.setName(name);
		user.setPassword(password);
		user.setGender(gender);
		user.setRegion(region);
		user.setComment(comment);
		return user;
	}

}
