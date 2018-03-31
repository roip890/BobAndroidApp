package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;

import java.sql.Timestamp;


/**
 * The persistent class for the MN_LEAF_PROP database table.
 * 
 */
public class MnLeafProp implements Serializable {
	
	public static final String NAME_FIND_BY_HOTEL_ID = "MnLeafProp.findByHotelId";
	public static final String QUERY_FIND_BY_HOTEL_ID = "SELECT m FROM MnLeafProp m where m.mnLeave.mnHotel.hotelId = :hotelId";
	
	
	private static final long serialVersionUID = 1L;

	private long leafStyleId;

	private Timestamp insertTs;

	private String keyProp;

	private String leafPropGuid;

	private Timestamp lmTs;

	private String valueProp;

	//bi-directional many-to-one association to MnLeave
	private MnLeaf mnLeave;

	private long leafParentId;
	
	private long hotelId;
	
	public MnLeafProp() {
	}

	public void prepareToSend() {
		if(this.mnLeave!=null) {
			this.leafParentId = this.mnLeave.getLeafId();
			this.mnLeave = null;
		}
	}
	
	public long getLeafStyleId() {
		return this.leafStyleId;
	}

	public void setLeafStyleId(long leafStyleId) {
		this.leafStyleId = leafStyleId;
	}

	public Timestamp getInsertTs() {
		return this.insertTs;
	}

	public void setInsertTs(Timestamp insertTs) {
		this.insertTs = insertTs;
	}

	public String getKeyProp() {
		return this.keyProp;
	}

	public void setKeyProp(String keyProp) {
		this.keyProp = keyProp;
	}

	public String getLeafPropGuid() {
		return this.leafPropGuid;
	}

	public void setLeafPropGuid(String leafPropGuid) {
		this.leafPropGuid = leafPropGuid;
	}

	public Timestamp getLmTs() {
		return this.lmTs;
	}

	public void setLmTs(Timestamp lmTs) {
		this.lmTs = lmTs;
	}

	public String getValueProp() {
		return this.valueProp;
	}

	public void setValueProp(String valueProp) {
		this.valueProp = valueProp;
	}

	public MnLeaf getMnLeave() {
		return this.mnLeave;
	}

	public void setMnLeave(MnLeaf mnLeave) {
		this.mnLeave = mnLeave;
	}


	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public long getLeafParentId() {
		return leafParentId;
	}

	public void setLeafParentId(long leafParentId) {
		this.leafParentId = leafParentId;
	}
}