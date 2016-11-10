package org.fusionsoft.services;

import java.util.List;

import org.fusionsoft.dao.UserDao;
import org.fusionsoft.dao.UserDaoImpl;
import org.fusionsoft.dao.UserNotFound;
import org.fusionsoft.model.Profile;
import org.fusionsoft.model.User;
import org.springframework.stereotype.Component;

@Component
public class UsersServiceImpl implements UserService {

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	private UserDaoImpl userDao;

	@Override
	public User finduserbyusername(String username)  throws UserNotFoundService {
		try {
			return userDao.findByUsername(username);
		} catch (UserNotFound e) {
			throw new UserNotFoundService("User not available");
		}
	}

	@Override
	public List<Profile> findprofilesbyuserid(int userid) {
		// TODO Auto-generated method stub
		return userDao.findprofilebyuserid(userid);
	}

	public User finduserbyuserid(int userid) {
		// TODO Auto-generated method stub
		return userDao.finduserbyuserid(userid);
	}

	public int  SaveUser(User user) {
		// TODO Auto-generated method stub
		return userDao.SaveUser(user);
	}

}