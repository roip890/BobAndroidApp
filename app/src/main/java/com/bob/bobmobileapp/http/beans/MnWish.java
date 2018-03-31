package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;

import java.sql.Timestamp;


/**
 * The persistent class for the MN_WISHES database table.
 * 
 */
public class MnWish implements Serializable {
	private static final long serialVersionUID = 1L;

	private int wishId;

	private Timestamp insertTs;

	private Timestamp lmTs;

	private String wishDetails;

	private String wishGuid;

	private String wishStatus;

	private String wishType;

	//bi-directional many-to-one association to MnGuest
	private MnGuest mnGuest;

	public MnWish() {
	}

	public int getWishId() {
		return this.wishId;
	}

	public void setWishId(int wishId) {
		this.wishId = wishId;
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

	public String getWishDetails() {
		return this.wishDetails;
	}

	public void setWishDetails(String wishDetails) {
		this.wishDetails = wishDetails;
	}

	public String getWishGuid() {
		return this.wishGuid;
	}

	public void setWishGuid(String wishGuid) {
		this.wishGuid = wishGuid;
	}

	public String getWishStatus() {
		return this.wishStatus;
	}

	public void setWishStatus(String wishStatus) {
		this.wishStatus = wishStatus;
	}

	public String getWishType() {
		return this.wishType;
	}

	public void setWishType(String wishType) {
		this.wishType = wishType;
	}

	public MnGuest getMnGuest() {
		return this.mnGuest;
	}

	public void setMnGuest(MnGuest mnGuest) {
		this.mnGuest = mnGuest;
	}

}