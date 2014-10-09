package speedpay.action.client;

import java.util.List;

import speedpay.dao.Qrcode;
import speedpay.dao.QrcodeDAO;

public class SetQrCodeClientAction {

	private String qrCode_content;
	QrcodeDAO qrcodeDao;
	
	public void doAction() {
		
		List<Qrcode> list=qrcodeDao.findByQrCodeContent(qrCode_content);
		list.get(0).setQrCodeStatus(1);
		qrcodeDao.getHibernateTemplate().update(list.get(0));
	}

	public String getQrCode_content() {
		return qrCode_content;
	}

	public void setQrCode_content(String qrCode_content) {
		this.qrCode_content = qrCode_content;
	}

	public QrcodeDAO getQrcodeDao() {
		return qrcodeDao;
	}

	public void setQrcodeDao(QrcodeDAO qrcodeDao) {
		this.qrcodeDao = qrcodeDao;
	}
	
	
}
