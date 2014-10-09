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
 * Withdrawproof entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see speedpay.dao.Withdrawproof
 * @author MyEclipse Persistence Tools
 */

public class WithdrawproofDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(WithdrawproofDAO.class);
	// property constants
	public static final String WITHDRAW_PROOF_ATM_ID = "withdrawProofAtmId";
	public static final String WITHDRAW_PROOF_SUM = "withdrawProofSum";

	protected void initDao() {
		// do nothing
	}

	public void save(Withdrawproof transientInstance) {
		log.debug("saving Withdrawproof instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Withdrawproof persistentInstance) {
		log.debug("deleting Withdrawproof instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Withdrawproof findById(java.lang.Integer id) {
		log.debug("getting Withdrawproof instance with id: " + id);
		try {
			Withdrawproof instance = (Withdrawproof) getHibernateTemplate()
					.get("speedpay.dao.Withdrawproof", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Withdrawproof instance) {
		log.debug("finding Withdrawproof instance by example");
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
		log.debug("finding Withdrawproof instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Withdrawproof as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByWithdrawProofAtmId(Object withdrawProofAtmId) {
		return findByProperty(WITHDRAW_PROOF_ATM_ID, withdrawProofAtmId);
	}

	public List findByWithdrawProofSum(Object withdrawProofSum) {
		return findByProperty(WITHDRAW_PROOF_SUM, withdrawProofSum);
	}

	public List findAll() {
		log.debug("finding all Withdrawproof instances");
		try {
			String queryString = "from Withdrawproof";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Withdrawproof merge(Withdrawproof detachedInstance) {
		log.debug("merging Withdrawproof instance");
		try {
			Withdrawproof result = (Withdrawproof) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Withdrawproof instance) {
		log.debug("attaching dirty Withdrawproof instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Withdrawproof instance) {
		log.debug("attaching clean Withdrawproof instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static WithdrawproofDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (WithdrawproofDAO) ctx.getBean("WithdrawproofDAO");
	}
}