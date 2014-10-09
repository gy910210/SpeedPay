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
 * Transferproof entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see speedpay.dao.Transferproof
 * @author MyEclipse Persistence Tools
 */

public class TransferproofDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TransferproofDAO.class);
	// property constants
	public static final String TRANSFER_PROOF_SUM = "transferProofSum";
	public static final String TRANSFER_PROOF_CAUSE = "transferProofCause";

	protected void initDao() {
		// do nothing
	}

	public void save(Transferproof transientInstance) {
		log.debug("saving Transferproof instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Transferproof persistentInstance) {
		log.debug("deleting Transferproof instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Transferproof findById(java.lang.Integer id) {
		log.debug("getting Transferproof instance with id: " + id);
		try {
			Transferproof instance = (Transferproof) getHibernateTemplate()
					.get("speedpay.dao.Transferproof", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Transferproof instance) {
		log.debug("finding Transferproof instance by example");
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
		log.debug("finding Transferproof instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Transferproof as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTransferProofSum(Object transferProofSum) {
		return findByProperty(TRANSFER_PROOF_SUM, transferProofSum);
	}

	public List findByTransferProofCause(Object transferProofCause) {
		return findByProperty(TRANSFER_PROOF_CAUSE, transferProofCause);
	}

	public List findAll() {
		log.debug("finding all Transferproof instances");
		try {
			String queryString = "from Transferproof";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Transferproof merge(Transferproof detachedInstance) {
		log.debug("merging Transferproof instance");
		try {
			Transferproof result = (Transferproof) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Transferproof instance) {
		log.debug("attaching dirty Transferproof instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Transferproof instance) {
		log.debug("attaching clean Transferproof instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TransferproofDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TransferproofDAO) ctx.getBean("TransferproofDAO");
	}
}