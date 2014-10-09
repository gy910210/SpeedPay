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
 * Consumptionproof entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see speedpay.dao.Consumptionproof
 * @author MyEclipse Persistence Tools
 */

public class ConsumptionproofDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ConsumptionproofDAO.class);
	// property constants
	public static final String CONSUMPTION_PROOF_SUM = "consumptionProofSum";
	public static final String CONSUMPTION_PROOF_CAUSE = "consumptionProofCause";

	protected void initDao() {
		// do nothing
	}

	public void save(Consumptionproof transientInstance) {
		log.debug("saving Consumptionproof instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Consumptionproof persistentInstance) {
		log.debug("deleting Consumptionproof instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Consumptionproof findById(java.lang.Integer id) {
		log.debug("getting Consumptionproof instance with id: " + id);
		try {
			Consumptionproof instance = (Consumptionproof) getHibernateTemplate()
					.get("speedpay.dao.Consumptionproof", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Consumptionproof instance) {
		log.debug("finding Consumptionproof instance by example");
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
		log.debug("finding Consumptionproof instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Consumptionproof as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByConsumptionProofSum(Object consumptionProofSum) {
		return findByProperty(CONSUMPTION_PROOF_SUM, consumptionProofSum);
	}

	public List findByConsumptionProofCause(Object consumptionProofCause) {
		return findByProperty(CONSUMPTION_PROOF_CAUSE, consumptionProofCause);
	}

	public List findAll() {
		log.debug("finding all Consumptionproof instances");
		try {
			String queryString = "from Consumptionproof";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Consumptionproof merge(Consumptionproof detachedInstance) {
		log.debug("merging Consumptionproof instance");
		try {
			Consumptionproof result = (Consumptionproof) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Consumptionproof instance) {
		log.debug("attaching dirty Consumptionproof instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Consumptionproof instance) {
		log.debug("attaching clean Consumptionproof instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ConsumptionproofDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ConsumptionproofDAO) ctx.getBean("ConsumptionproofDAO");
	}
}