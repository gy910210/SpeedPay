package speedpay.dao;

/**
 * AbstractQrcode entity provides the base persistence definition of the Qrcode
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractQrcode implements java.io.Serializable {

	// Fields

	private Integer qrCodeId;
	private String qrCodeContent;
	private Integer qrCodeStatus;

	// Constructors

	/** default constructor */
	public AbstractQrcode() {
	}

	/** full constructor */
	public AbstractQrcode(String qrCodeContent, Integer qrCodeStatus) {
		this.qrCodeContent = qrCodeContent;
		this.qrCodeStatus = qrCodeStatus;
	}

	// Property accessors

	public Integer getQrCodeId() {
		return this.qrCodeId;
	}

	public void setQrCodeId(Integer qrCodeId) {
		this.qrCodeId = qrCodeId;
	}

	public String getQrCodeContent() {
		return this.qrCodeContent;
	}

	public void setQrCodeContent(String qrCodeContent) {
		this.qrCodeContent = qrCodeContent;
	}

	public Integer getQrCodeStatus() {
		return this.qrCodeStatus;
	}

	public void setQrCodeStatus(Integer qrCodeStatus) {
		this.qrCodeStatus = qrCodeStatus;
	}

}