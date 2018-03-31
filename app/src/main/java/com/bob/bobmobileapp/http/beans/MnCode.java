package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;



/**
 * The persistent class for the MN_CODES database table.
 * 
 */
public class MnCode implements Serializable {
	private static final long serialVersionUID = 1L;

	private int seq;

	private String actCode;

	//bi-directional many-to-one association to MnUser
	private MnUser mnUser;

	public MnCode() {
	}

	public int getSeq() {
		return this.seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getActCode() {
		return this.actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public MnUser getMnUser() {
		return this.mnUser;
	}

	public void setMnUser(MnUser mnUser) {
		this.mnUser = mnUser;
	}

}