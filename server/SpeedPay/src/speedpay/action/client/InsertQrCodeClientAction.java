package speedpay.action.client;

import speedpay.dao.Qrcode;
import speedpay.dao.QrcodeDAO;

public class InsertQrCodeClientAction {

	private String qrCode_content;
	QrcodeDAO qrcodeDao;
	
	public void doAction() {
		Qrcode qrcode=new Qrcode(qrCode_content, 0);
		qrcodeDao.save(qrcode);
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
