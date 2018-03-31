package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;

import java.sql.Timestamp;


/**
 * The persistent class for the MN_LEAF_PROP database table.
 * 
 */
public class MnNodeProp implements Serializable {
	
	public static final String NAME_FIND_BY_HOTEL_ID = "MnNodeProp.findByHotelId";
	public static final String QUERY_FIND_BY_HOTEL_ID = "SELECT m FROM MnNodeProp m where m.mnNode.mnHotel.hotelId = :hotelId";
	
	
	private static final long serialVersionUID = 1L;

	private long nodeStyleId;

	private Timestamp insertTs;

	private String keyProp;

	private String nodePropGuid;

	private Timestamp lmTs;

	private String valueProp;

	//bi-directional many-to-one association to MnLeave
	private MnNode mnNode;
	
	private long nodeStyleParentId;//The node

	private long hotelId;
	
	public MnNodeProp() {
	}

	public void prepareToSend() {
		if(this.mnNode!=null) {
			this.nodeStyleParentId = this.mnNode.getNodeId();
			if(this.mnNode.getMnHotel() !=null) {
				this.hotelId = this.mnNode.getMnHotel().getHotelId();
			}
		this.mnNode = null;
		}
	}
	
	public long getNodeStyleId() {
		return this.nodeStyleId;
	}

	public void setNodeStyleId(long nodeStyleId) {
		this.nodeStyleId = nodeStyleId;
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

	public String getNodePropGuid() {
		return this.nodePropGuid;
	}

	public void setNodePropGuid(String nodePropGuid) {
		this.nodePropGuid = nodePropGuid;
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

	public MnNode getMnNode() {
		return this.mnNode;
	}

	public void setMnNode(MnNode mnNode) {
		this.mnNode = mnNode;
	}

	public long getNodeStyleParentId() {
		return nodeStyleParentId;
	}

	public void setNodeStyleParentId(long nodeStyleParentId) {
		this.nodeStyleParentId = nodeStyleParentId;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
}