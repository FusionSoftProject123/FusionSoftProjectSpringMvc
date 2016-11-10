package org.fusionsoft.services;
import java.util.List;

import org.fusionsoft.dao.UserNotFound;
import org.fusionsoft.model.Profile;
import org.fusionsoft.model.User;

public interface UserService {

	public User finduserbyusername(String username) throws UserNotFound, UserNotFoundService;
	public List<Profile> findprofilesbyuserid(int userid);
	public User finduserbyuserid(int userid);
	public int SaveUser(User user);

}