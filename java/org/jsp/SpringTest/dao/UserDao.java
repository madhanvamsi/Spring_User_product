package org.jsp.SpringTest.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.SpringTest.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	EntityManager m;
	
	public User SaveUser(User u) {
		EntityTransaction t = m.getTransaction();
		m.persist(u);
		t.begin();
		t.commit();
		return u;
	}

	
	public User UpdateUser(User u) {
		EntityTransaction t = m.getTransaction();
		m.merge(u);
		t.begin();
		t.commit();
		return u;
	}
	
	public User fetchUserById(int id) {
		return m.find(User.class, id);
	}
	
	public User verifyUserByPhoneAndPassword(long phone, String password) {
		String query = "select u from User u where u.phone=?1 and u.password=?2";
		Query q = m.createQuery(query);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		return (User)q.getSingleResult();
	}
	
	public User verifyUserByEmailAndPassword(String email, String password) {
		String query = "select u from User u where u.email=?1 and u.password=?2";
		Query q = m.createQuery(query);
		q.setParameter(1, email);
		q.setParameter(2, password);
		return (User)q.getSingleResult();
	}

}