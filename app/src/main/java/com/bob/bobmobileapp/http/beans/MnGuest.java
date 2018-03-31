package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MN_GUESTS database table.
 * 
 */
public class MnGuest implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_EMAIL_QUERY_NAME = "MnGuest.findByEmail";
	public static final String NAME_GET_BY_EMAIL_AND_PASSWORD = "MnGuest.getGuidByEmail";
	public static final String QUERY_GET_BY_EMAIL_AND_PASSWORD = "SELECT m FROM MnGuest m where m.guestEmail = :email AND m.guestPassword = :password";
	public static final String NAME_SET_GUID_BY_EMAIL = "MnGuest.setGuidByEmail";
	public static final String QUERY_SET_GUID_BY_EMAIL = "UPDATE MnGuest m SET m.guestGuid = :guid , m.guestGuidValid = :valid"
			+ " WHERE m.guestEmail = :email";
	
	private int guestId;

	private String guestEmail;

	private String guestGuid;

	private String guestName;

	private String guestPassword;

	private String guestPhone;

	private int guestRoom;

	private String guestStatus;
	
	private String guestGuidValid;

	private Timestamp insertTs;

	private Timestamp lmTs;

	//bi-directional many-to-one association to MnHotel
	private MnHotel mnHotel;

	//bi-directional many-to-one association to MnWishe
	private List<MnWish> mnWishes;

	public MnGuest() {
	}

	public int getGuestId() {
		return this.guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public String getGuestEmail() {
		return this.guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public String getGuestGuid() {
		return this.guestGuid;
	}

	public void setGuestGuid(String guestGuid) {
		this.guestGuid = guestGuid;
	}

	public String getGuestName() {
		return this.guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestPassword() {
		return this.guestPassword;
	}

	public void setGuestPassword(String guestPassword) {
		this.guestPassword = guestPassword;
	}

	public String getGuestPhone() {
		return this.guestPhone;
	}

	public void setGuestPhone(String guestPhone) {
		this.guestPhone = guestPhone;
	}

	public int getGuestRoom() {
		return this.guestRoom;
	}

	public void setGuestRoom(int guestRoom) {
		this.guestRoom = guestRoom;
	}

	public String getGuestStatus() {
		return this.guestStatus;
	}

	public void setGuestStatus(String guestStatus) {
		this.guestStatus = guestStatus;
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

	public MnHotel getMnHotel() {
		return this.mnHotel;
	}

	public void setMnHotel(MnHotel mnHotel) {
		this.mnHotel = mnHotel;
	}
	
	public String getGuestGuidValid() {
		return guestGuidValid;
	}

	public void setGuestGuidValid(String guestGuidValid) {
		this.guestGuidValid = guestGuidValid;
	}

	public List<MnWish> getMnWishes() {
		return this.mnWishes;
	}

	public void setMnWishes(List<MnWish> mnWishes) {
		this.mnWishes = mnWishes;
	}

	public MnWish addMnWishe(MnWish mnWishe) {
		getMnWishes().add(mnWishe);
		mnWishe.setMnGuest(this);

		return mnWishe;
	}

	public MnWish removeMnWishe(MnWish mnWishe) {
		getMnWishes().remove(mnWishe);
		mnWishe.setMnGuest(null);

		return mnWishe;
	}

}