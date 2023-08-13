package org.jsp.SpringTest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.SpringTest.dto.Product;
import org.jsp.SpringTest.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	EntityManager m;
	
	public Product saveProduct(Product p , int uid) {
		User u = m.find(User.class, uid);
		if(u!=null) {
			u.getP().add(p);
			p.setUser(u);
			EntityTransaction t = m.getTransaction();
			m.merge(p);
			t.begin();
			t.commit();
			return p;
		}
		return null;
	}
	
	private void setUser(User u) {
		// TODO Auto-generated method stub
		
	}

	public List<Product> fetchProductByUserId(int uid){
		String qry = "select u.product from User u where u.id=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, uid);
		return q.getResultList();
	}
	
	public Product fetchProductById(int id) {
		return m.find(Product.class, id);
	}
	
	public List<Product> fetchProductByCategory(String category) {
		String qry = "select p from Product p where p.category=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, category);
		return q.getResultList();
	}
	

}