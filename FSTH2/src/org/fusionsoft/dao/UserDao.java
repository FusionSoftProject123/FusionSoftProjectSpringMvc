package org.fusionsoft.dao;
import java.util.List;

import org.fusionsoft.model.Profile;
import org.fusionsoft.model.User;

public interface UserDao {
	
	public User findByUsername(String username) throws UserNotFound;
	public List<Profile> findprofilebyuserid(int userid);
	public User finduserbyuserid(int userid);
	public int SaveUser(User user);
	
}
