package model;

import java.io.Serializable;

public class CreditCard implements Serializable {

	private int id;
	private String cardNumber;
	private String validThru;
	private int CVC;
	private String cardOwnerName;
	private String cardOwnerLastName;

	public CreditCard(String cardNumber, String validThru, int cVC, String cardOwnerName, String cardOwnerLastName) {
		super();
		this.cardNumber = cardNumber;
		this.validThru = validThru;
		CVC = cVC;
		this.cardOwnerName = cardOwnerName;
		this.cardOwnerLastName = cardOwnerLastName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getValidThru() {
		return validThru;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setValidThru(String validThru) {
		this.validThru = validThru;
	}

	public int getCVC() {
		return CVC;
	}

	public void setCVC(int cVC) {
		CVC = cVC;
	}

	public String getCardOwnerName() {
		return cardOwnerName;
	}

	public void setCardOwnerName(String cardOwnerName) {
		this.cardOwnerName = cardOwnerName;
	}

	public String getCardOwnerLastName() {
		return cardOwnerLastName;
	}

	public void setCardOwnerLastName(String cardOwnerLastName) {
		this.cardOwnerLastName = cardOwnerLastName;
	}

	@Override
	public String toString() {
		return "CreditCard [cardNumber=" + cardNumber + ", validThru=" + validThru + ", CVC=" + CVC + ", cardOwnerName="
				+ cardOwnerName + ", cardOwnerLastName=" + cardOwnerLastName + "]";
	}

}
