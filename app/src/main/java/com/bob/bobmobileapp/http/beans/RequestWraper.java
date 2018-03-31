package com.bob.bobmobileapp.http.beans;

public class RequestWraper {

	private int command;
	
	private CustomResponse response = new CustomResponse();
	
	private MnGuest guest;
	private MnHotel hotel;
	private MnHotelInfo hotelInfo;
	private MnLeaf leaf;
	private MnNode node;
	private MnWish wish;
	private MnRoot root;
	private MnUser user;


	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public CustomResponse getResponse() {
		return response;
	}

	public void setResponse(CustomResponse response) {
		this.response = response;
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

	public MnHotelInfo getHotelInfo() {
		return hotelInfo;
	}

	public void setHotelInfo(MnHotelInfo hotelInfo) {
		this.hotelInfo = hotelInfo;
	}

	public void setWish(MnWish wish) {
		this.wish = wish;
	}
	
	
	
	
	
}
