package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MN_USERS database table.
 * 
 */
public class MnUser implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String NAME_FIND_BY_NAME = "MnUser.findByUsername";
	public static final String QUERY_FIND_BY_NAME = "SELECT u FROM MnUser u WHERE u.userUsername=:name";
	
	private int userId;

	private Timestamp insertTs;

	private Timestamp lmTs;

	private String userEmail;

	private String userGuid;

	private String userPassword;

	private int userPermissionLvl;

	private String userPhone;

	private String userUsername;

	//bi-directional many-to-one association to MnHotel
	private MnHotel mnHotel;
	
	//bi-directional many-to-one association to MnCode
	private List<MnCode> mnCodes;

	public MnUser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getInsertTs() {
		return this.insertTs;
	}

	public void setInsertTs(Timestamp insertTs) {
		this.insertTs = insertTs;
	}

	public Timestamp getLmTs() {
		return this.lmTs;
	}

	public void setLmTs(Timestamp lmTs) {
		this.lmTs = lmTs;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserGuid() {
		return this.userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserPermissionLvl() {
		return this.userPermissionLvl;
	}

	public void setUserPermissionLvl(int userPermissionLvl) {
		this.userPermissionLvl = userPermissionLvl;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public MnHotel getMnHotel() {
		return this.mnHotel;
	}

	public void setMnHotel(MnHotel mnHotel) {
		this.mnHotel = mnHotel;
	}

	public List<MnCode> getMnCodes() {
		return this.mnCodes;
	}

	public void setMnCodes(List<MnCode> mnCodes) {
		this.mnCodes = mnCodes;
	}

	public MnCode addMnCode(MnCode mnCode) {
		getMnCodes().add(mnCode);
		mnCode.setMnUser(this);

		return mnCode;
	}

	public MnCode removeMnCode(MnCode mnCode) {
		getMnCodes().remove(mnCode);
		mnCode.setMnUser(null);

		return mnCode;
	}
}