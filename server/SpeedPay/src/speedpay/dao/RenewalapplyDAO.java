package speedpay.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Renewalapply entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see speedpay.dao.Renewalapply
 * @author MyEclipse Persistence Tools
 */

public class RenewalapplyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(RenewalapplyDAO.class);
	// property constants
	public static final String RENEWAL_APPLY_IS_CHECKED = "renewalApplyIsChecked";

	protected void initDao() {
		// do nothing
	}

	public void save(Renewalapply transientInstance) {
		log.debug("saving Renewalapply instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Renewalapply persistentInstance) {
		log.debug("deleting Renewalapply instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Renewalapply findById(java.lang.Integer id) {
		log.debug("getting Renewalapply instance with id: " + id);
		try {
			Renewalapply instance = (Renewalapply) getHibernateTemplate().get(
					"speedpay.dao.Renewalapply", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Renewalapply instance) {
		log.debug("finding Renewalapply instance by example");
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
		log.debug("finding Renewalapply instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Renewalapply as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRenewalApplyIsChecked(Object renewalApplyIsChecked) {
		return findByProperty(RENEWAL_APPLY_IS_CHECKED, renewalApplyIsChecked);
	}

	public List findAll() {
		log.debug("finding all Renewalapply instances");
		try {
			String queryString = "from Renewalapply";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Renewalapply merge(Renewalapply detachedInstance) {
		log.debug("merging Renewalapply instance");
		try {
			Renewalapply result = (Renewalapply) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Renewalapply instance) {
		log.debug("attaching dirty Renewalapply instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Renewalapply instance) {
		log.debug("attaching clean Renewalapply instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RenewalapplyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RenewalapplyDAO) ctx.getBean("RenewalapplyDAO");
	}
}