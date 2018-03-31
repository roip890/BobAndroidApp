package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;

import java.sql.Timestamp;


/**
 * The persistent class for the MN_ROOTS database table.
 * 
 */
public class MnRoot implements Serializable {
	private static final long serialVersionUID = 1L;

	private int rootId;

	private Timestamp insertTs;

	private Timestamp lmTs;

	private String rootGuid;

	private String rootName;

	public MnRoot() {
	}

	public int getRootId() {
		return this.rootId;
	}

	public void setRootId(int rootId) {
		this.rootId = rootId;
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

	public String getRootGuid() {
		return this.rootGuid;
	}

	public void setRootGuid(String rootGuid) {
		this.rootGuid = rootGuid;
	}

	public String getRootName() {
		return this.rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

}