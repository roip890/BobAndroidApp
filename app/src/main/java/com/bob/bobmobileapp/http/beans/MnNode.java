package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MN_NODES database table.
 * 
 */
public class MnNode implements Serializable {
	
	public static final String NAME_FIND_BY_HOTEL_ID = "MnNode.findByHotelId";
	public static final String QUERY_FIND_BY_HOTEL_ID = "SELECT m FROM MnNode m where m.mnHotel.hotelId = :hotelId";
	
	
	private static final long serialVersionUID = 1L;

	private long nodeId;

	private Timestamp insertTs;

	private Timestamp lmTs;

	private String nodeGuid;

	private int nodeLvl;

	private String nodeName;

	private String nodeType;

	//bi-directional many-to-one association to MnLeave
	private List<MnLeaf> mnLeaves;

	//bi-directional many-to-one association to MnHotel
	private MnHotel mnHotel;

	//bi-directional many-to-one association to MnNode
	private MnNode mnNode;

	//bi-directional many-to-one association to MnNode
	private List<MnNode> mnNodes;

	private long nodeParentId;
	
	private long hotelId;
	
	public MnNode() {
	}

	public void prepareToSend() {
		if(this.mnHotel !=null) {
			this.hotelId = this.mnHotel.getHotelId();
			this.mnHotel = null;
		}
		if(this.mnNode !=null) {
			this.nodeParentId = this.mnNode.getNodeId();
			this.mnNode = null;
		}
		this.mnLeaves = null;
		this.mnNodes = null;
	}
	
	public long getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(long nodeId) {
		this.nodeId = nodeId;
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

	public String getNodeGuid() {
		return this.nodeGuid;
	}

	public void setNodeGuid(String nodeGuid) {
		this.nodeGuid = nodeGuid;
	}

	public int getNodeLvl() {
		return this.nodeLvl;
	}

	public void setNodeLvl(int nodeLvl) {
		this.nodeLvl = nodeLvl;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeType() {
		return this.nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public List<MnLeaf> getMnLeaves() {
		return this.mnLeaves;
	}

	public void setMnLeaves(List<MnLeaf> mnLeaves) {
		this.mnLeaves = mnLeaves;
	}

	public MnLeaf addMnLeave(MnLeaf mnLeave) {
		getMnLeaves().add(mnLeave);
		mnLeave.setMnNode(this);

		return mnLeave;
	}

	public MnLeaf removeMnLeave(MnLeaf mnLeave) {
		getMnLeaves().remove(mnLeave);
		mnLeave.setMnNode(null);

		return mnLeave;
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

	public List<MnNode> getMnNodes() {
		return this.mnNodes;
	}

	public void setMnNodes(List<MnNode> mnNodes) {
		this.mnNodes = mnNodes;
	}

	public MnNode addMnNode(MnNode mnNode) {
		getMnNodes().add(mnNode);
		mnNode.setMnNode(this);

		return mnNode;
	}

	public MnNode removeMnNode(MnNode mnNode) {
		getMnNodes().remove(mnNode);
		mnNode.setMnNode(null);

		return mnNode;
	}

	public long getNodeParentId() {
		return nodeParentId;
	}

	public void setNodeParentId(long nodeParentId) {
		this.nodeParentId = nodeParentId;
	}
	
	public void setFlatParentId() {
		if(this.mnNode == null) {
			this.nodeParentId = -1;
		}else {
			this.nodeParentId = this.mnNode.getNodeId();
		}
	}


	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
}