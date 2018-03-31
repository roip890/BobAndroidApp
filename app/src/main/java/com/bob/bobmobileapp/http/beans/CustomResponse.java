
package com.bob.bobmobileapp.http.beans;

public class CustomResponse {
	
	private ApplicativeResponse statusResponse;
	
	private MnGuest guest;
	private MnHotel hotel;
	private MnLeaf leaf;
	private MnNode node;
	private MnWish wish;
	private MnRoot root;
	private MnUser user;
	
	public CustomResponse(){
		this.statusResponse = new ApplicativeResponse();
	}

	public CustomResponse(ApplicativeResponse statusResponse){
		this.statusResponse = statusResponse;
	}
	
	public void setSuccess() {
		this.statusResponse.setSuccess();
	}
	
	public void setFailure(String message , String status) {
		this.statusResponse.setFailure(message, status);
	}
	
	public MnGuest getGuest() {
		return guest;
	}

	public void setGuest(MnGuest guest) {
		this.guest = guest;
	}

	public MnHotel getHotel() {
		return hotel;
	}

	public void setHotel(MnHotel hotel) {
		this.hotel = hotel;
	}

	public MnLeaf getLeaf() {
		return leaf;
	}

	public void setLeaf(MnLeaf leaf) {
		this.leaf = leaf;
	}

	public MnNode getNode() {
		return node;
	}

	public void setNode(MnNode node) {
		this.node = node;
	}

	public MnWish getWish() {
		return wish;
	}

	public void setRequest(MnWish wish) {
		this.wish = wish;
	}

	public MnRoot getRoot() {
		return root;
	}

	public void setRoot(MnRoot root) {
		this.root = root;
	}

	public MnUser getUser() {
		return user;
	}

	public void setUser(MnUser user) {
		this.user = user;
	}


	public ApplicativeResponse getStatusResponse() {
		return statusResponse;
	}


	public void setStatusResponse(ApplicativeResponse statusResponse) {
		this.statusResponse = statusResponse;
	}


	public void setWish(MnWish wish) {
		this.wish = wish;
	}
	
	
	
}
