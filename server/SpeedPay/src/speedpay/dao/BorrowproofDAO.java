package speedpay.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Borrowproof entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see speedpay.dao.Borrowproof
 * @author MyEclipse Persistence Tools
 */

public class BorrowproofDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(BorrowproofDAO.class);
	// property constants
	public static final String BORROW_PROOF_SUM = "borrowProofSum";
	public static final String BORROW_PROOF_IS_REPAYED = "borrowProofIsRepayed";

	protected void initDao() {
		// do nothing
	}

	public void save(Borrowproof transientInstance) {
		log.debug("saving Borrowproof instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Borrowproof persistentInstance) {
		log.debug("deleting Borrowproof instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Borrowproof findById(java.lang.Integer id) {
		log.debug("getting Borrowproof instance with id: " + id);
		try {
			Borrowproof instance = (Borrowproof) getHibernateTemplate().get(
					"speedpay.dao.Borrowproof", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Borrowproof instance) {
		log.debug("finding Borrowproof instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Borrowproof instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Borrowproof as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBorrowProofSum(Object borrowProofSum) {
		return findByProperty(BORROW_PROOF_SUM, borrowProofSum);
	}

	public List findByBorrowProofIsRepayed(Object borrowProofIsRepayed) {
		return findByProperty(BORROW_PROOF_IS_REPAYED, borrowProofIsRepayed);
	}

	public List findAll() {
		log.debug("finding all Borrowproof instances");
		try {
			String queryString = "from Borrowproof";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Borrowproof merge(Borrowproof detachedInstance) {
		log.debug("merging Borrowproof instance");
		try {
			Borrowproof result = (Borrowproof) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Borrowproof instance) {
		log.debug("attaching dirty Borrowproof instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Borrowproof instance) {
		log.debug("attaching clean Borrowproof instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BorrowproofDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BorrowproofDAO) ctx.getBean("BorrowproofDAO");
	}
}