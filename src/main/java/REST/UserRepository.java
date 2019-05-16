package REST;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class UserRepository {
	List<User> users=new ArrayList<User>();
	int usersCounter=0;
	void addUser() {
		User user=new User(usersCounter);
		users.add(user);
		usersCounter++;
	}
	void deleteUser(User user) {
		users.remove(user);
	}
	int getLastCreatedId() {
		return usersCounter-1;
	}

}
