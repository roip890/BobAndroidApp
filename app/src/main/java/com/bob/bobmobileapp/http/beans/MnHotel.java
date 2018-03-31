package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;



/**
 * The persistent class for the MN_HOTELS database table.
 * 
 */



public class MnHotel implements Serializable {
	
	public static final String NAME_FIND_BY_NAME = "MnHotel.findByName";
	public static final String QUERY_FIND_BY_NAME = "SELECT h FROM MnHotel h WHERE h.hotelName=:name";
	
	private static final long serialVersionUID = 1L;

	private int hotelId;

	private String hotelGuid;

	private String hotelName;

	private Timestamp insertTs;

	private Timestamp lmTs;

	//bi-directional many-to-one association to MnGuest
	private List<MnGuest> mnGuests;

	//bi-directional many-to-one association to MnHotelInfo
	private List<MnHotelInfo> mnHotelInfos;

	//bi-directional many-to-one association to MnLeave
	private List<MnLeaf> mnLeaves;

	//bi-directional many-to-one association to MnNode
	private List<MnNode> mnNodes;

	//bi-directional many-to-one association to MnUser
	private List<MnUser> mnUsers;

	public MnHotel() {
	}

	public int getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelGuid() {
		return this.hotelGuid;
	}

	public void setHotelGuid(String hotelGuid) {
		this.hotelGuid = hotelGuid;
	}

	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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

	public List<MnGuest> getMnGuests() {
		return this.mnGuests;
	}

	public void setMnGuests(List<MnGuest> mnGuests) {
		this.mnGuests = mnGuests;
	}

	public MnGuest addMnGuest(MnGuest mnGuest) {
		getMnGuests().add(mnGuest);
		mnGuest.setMnHotel(this);

		return mnGuest;
	}

	public MnGuest removeMnGuest(MnGuest mnGuest) {
		getMnGuests().remove(mnGuest);
		mnGuest.setMnHotel(null);

		return mnGuest;
	}

	public List<MnHotelInfo> getMnHotelInfos() {
		return this.mnHotelInfos;
	}

	public void setMnHotelInfos(List<MnHotelInfo> mnHotelInfos) {
		this.mnHotelInfos = mnHotelInfos;
	}

	public MnHotelInfo addMnHotelInfo(MnHotelInfo mnHotelInfo) {
		getMnHotelInfos().add(mnHotelInfo);
		mnHotelInfo.setMnHotel(this);

		return mnHotelInfo;
	}

	public MnHotelInfo removeMnHotelInfo(MnHotelInfo mnHotelInfo) {
		getMnHotelInfos().remove(mnHotelInfo);
		mnHotelInfo.setMnHotel(null);

		return mnHotelInfo;
	}

	public List<MnLeaf> getMnLeaves() {
		return this.mnLeaves;
	}

	public void setMnLeaves(List<MnLeaf> mnLeaves) {
		this.mnLeaves = mnLeaves;
	}

	public MnLeaf addMnLeave(MnLeaf mnLeave) {
		getMnLeaves().add(mnLeave);
		mnLeave.setMnHotel(this);

		return mnLeave;
	}

	public MnLeaf removeMnLeave(MnLeaf mnLeave) {
		getMnLeaves().remove(mnLeave);
		mnLeave.setMnHotel(null);

		return mnLeave;
	}

	public List<MnNode> getMnNodes() {
		return this.mnNodes;
	}

	public void setMnNodes(List<MnNode> mnNodes) {
		this.mnNodes = mnNodes;
	}

	public MnNode addMnNode(MnNode mnNode) {
		getMnNodes().add(mnNode);
		mnNode.setMnHotel(this);

		return mnNode;
	}

	public MnNode removeMnNode(MnNode mnNode) {
		getMnNodes().remove(mnNode);
		mnNode.setMnHotel(null);

		return mnNode;
	}

	public List<MnUser> getMnUsers() {
		return this.mnUsers;
	}

	public void setMnUsers(List<MnUser> mnUsers) {
		this.mnUsers = mnUsers;
	}

	public MnUser addMnUser(MnUser mnUser) {
		getMnUsers().add(mnUser);
		mnUser.setMnHotel(this);

		return mnUser;
	}

	public MnUser removeMnUser(MnUser mnUser) {
		getMnUsers().remove(mnUser);
		mnUser.setMnHotel(null);

		return mnUser;
	}

}