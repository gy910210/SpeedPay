package speedpay.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Qrcode entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see speedpay.dao.Qrcode
 * @author MyEclipse Persistence Tools
 */

public class QrcodeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(QrcodeDAO.class);
	// property constants
	public static final String QR_CODE_CONTENT = "qrCodeContent";
	public static final String QR_CODE_STATUS = "qrCodeStatus";

	protected void initDao() {
		// do nothing
	}

	public void save(Qrcode transientInstance) {
		log.debug("saving Qrcode instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Qrcode persistentInstance) {
		log.debug("deleting Qrcode instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Qrcode findById(java.lang.Integer id) {
		log.debug("getting Qrcode instance with id: " + id);
		try {
			Qrcode instance = (Qrcode) getHibernateTemplate().get(
					"speedpay.dao.Qrcode", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Qrcode instance) {
		log.debug("finding Qrcode instance by example");
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
		log.debug("finding Qrcode instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Qrcode as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByQrCodeContent(Object qrCodeContent) {
		return findByProperty(QR_CODE_CONTENT, qrCodeContent);
	}

	public List findByQrCodeStatus(Object qrCodeStatus) {
		return findByProperty(QR_CODE_STATUS, qrCodeStatus);
	}

	public List findAll() {
		log.debug("finding all Qrcode instances");
		try {
			String queryString = "from Qrcode";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Qrcode merge(Qrcode detachedInstance) {
		log.debug("merging Qrcode instance");
		try {
			Qrcode result = (Qrcode) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Qrcode instance) {
		log.debug("attaching dirty Qrcode instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Qrcode instance) {
		log.debug("attaching clean Qrcode instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static QrcodeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (QrcodeDAO) ctx.getBean("QrcodeDAO");
	}
}