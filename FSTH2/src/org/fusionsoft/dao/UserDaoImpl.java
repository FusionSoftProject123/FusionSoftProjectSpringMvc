package org.fusionsoft.dao;

import java.util.ArrayList;
import java.util.List;

import org.fusionsoft.model.Profile;
import org.fusionsoft.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unused")
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public User  findByUsername(String username) throws UserNotFound {
		// TODO Auto-generated method stub

		List<User> users = new ArrayList<User>();

		users =  getSessionFactory().openSession().createQuery("from User where username=?")
				.setParameter(0, username).list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			throw new UserNotFound("user not found in the table");
		}

	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Profile> findprofilebyuserid(int userid) {
		List<Profile> employees = new ArrayList<Profile>();

		employees =  getSessionFactory().openSession().createQuery("from Profile where userid=?")
				.setParameter(0, userid).list();
		
		return employees;
	}

	public User finduserbyuserid(int userid) {
		User user;
		user =  getSessionFactory().openSession().get(User.class, userid);
		return user;
	}

	public int SaveUser(User user) {
		// TODO Auto-generated method stub
		int userid;
		userid = (int) getSessionFactory().openSession().save(user);
		return userid;
	}
}
