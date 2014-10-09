package speedpay.dao;

/**
 * Qrcode entity. @author MyEclipse Persistence Tools
 */
public class Qrcode extends AbstractQrcode implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Qrcode() {
	}

	/** full constructor */
	public Qrcode(String qrCodeContent, Integer qrCodeStatus) {
		super(qrCodeContent, qrCodeStatus);
	}

}
