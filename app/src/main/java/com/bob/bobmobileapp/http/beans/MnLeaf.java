package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MN_LEAVES database table.
 * 
 */
public class MnLeaf implements Serializable {
	public static final String NAME_FIND_BY_HOTEL_ID = "MnLeaf.findByHotelId";
	public static final String QUERY_FIND_BY_HOTEL_ID = "SELECT m FROM MnLeaf m where m.mnHotel.hotelId = :hotelId";
	
	
	private static final long serialVersionUID = 1L;

	private long leafId;

	private Timestamp insertTs;

	private String leafFkey;

	private String leafFvalue;

	private String leafGuid;

	private String leafType;

	private int leafX1;

	private int leafX2;

	private int leafY1;

	private int leafY2;

	private Timestamp lmTs;

	//bi-directional many-to-one association to MnLeafProp
	private List<MnLeafProp> mnLeafProps;

	//bi-directional many-to-one association to MnHotel
	private MnHotel mnHotel;

	//bi-directional many-to-one association to MnNode
	private MnNode mnNode;

	private long leafParentId;
	
	private long hotelId;
	
	public MnLeaf() {
	}

	public void prepareToSend() {
		if(this.mnHotel != null) {
			this.hotelId = this.mnHotel.getHotelId();
			this.mnHotel = null;
		}
		
		if(this.mnNode != null) {
			this.leafParentId = this.mnNode.getNodeId();
			this.mnNode = null;
		}
		this.mnLeafProps = null;
	}
	
	public long getLeafId() {
		return this.leafId;
	}

	public void setLeafId(long leafId) {
		this.leafId = leafId;
	}

	public Timestamp getInsertTs() {
		return this.insertTs;
	}

	public void setInsertTs(Timestamp insertTs) {
		this.insertTs = insertTs;
	}

	public String getLeafFkey() {
		return this.leafFkey;
	}

	public void setLeafFkey(String leafFkey) {
		this.leafFkey = leafFkey;
	}

	public String getLeafFvalue() {
		return this.leafFvalue;
	}

	public void setLeafFvalue(String leafFvalue) {
		this.leafFvalue = leafFvalue;
	}

	public String getLeafGuid() {
		return this.leafGuid;
	}

	public void setLeafGuid(String leafGuid) {
		this.leafGuid = leafGuid;
	}

	public String getLeafType() {
		return this.leafType;
	}

	public void setLeafType(String leafType) {
		this.leafType = leafType;
	}

	public int getLeafX1() {
		return this.leafX1;
	}

	public void setLeafX1(int leafX1) {
		this.leafX1 = leafX1;
	}

	public int getLeafX2() {
		return this.leafX2;
	}

	public void setLeafX2(int leafX2) {
		this.leafX2 = leafX2;
	}

	public int getLeafY1() {
		return this.leafY1;
	}

	public void setLeafY1(int leafY1) {
		this.leafY1 = leafY1;
	}

	public int getLeafY2() {
		return this.leafY2;
	}

	public void setLeafY2(int leafY2) {
		this.leafY2 = leafY2;
	}

	public Timestamp getLmTs() {
		return this.lmTs;
	}

	public void setLmTs(Timestamp lmTs) {
		this.lmTs = lmTs;
	}

	public List<MnLeafProp> getMnLeafProps() {
		return this.mnLeafProps;
	}

	public void setMnLeafProps(List<MnLeafProp> mnLeafProps) {
		this.mnLeafProps = mnLeafProps;
	}

	public MnLeafProp addMnLeafProp(MnLeafProp mnLeafProp) {
		getMnLeafProps().add(mnLeafProp);
		mnLeafProp.setMnLeave(this);

		return mnLeafProp;
	}

	public MnLeafProp removeMnLeafProp(MnLeafProp mnLeafProp) {
		getMnLeafProps().remove(mnLeafProp);
		mnLeafProp.setMnLeave(null);

		return mnLeafProp;
	}

	public MnHotel getMnHotel() {
		return this.mnHotel;
	}

	public void setMnHotel(MnHotel mnHotel) {
		this.mnHotel = mnHotel;
	}

	public MnNode getMnNode() {
		return this.mnNode;
	}

	public void setMnNode(MnNode mnNode) {
		this.mnNode = mnNode;
	}

	public long getLeafParentId() {
		return leafParentId;
	}

	public void setLeafParentId(long leafParentId) {
		this.leafParentId = leafParentId;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
}