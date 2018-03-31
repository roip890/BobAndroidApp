package com.bob.bobmobileapp.http.beans;

import java.io.Serializable;

import java.sql.Timestamp;


/**
 * The persistent class for the MN_HOTEL_INFO database table.
 * 
 */
public class MnHotelInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int hotelInfoId;

	private int hotelInfoBuildingNumber;

	private String hotelInfoCity;

	private String hotelInfoCountry;

	private String hotelInfoGuid;

	private String hotelInfoPhone;

	private int hotelInfoRooms;

	private String hotelInfoStreet;

	private Timestamp insertTs;

	private Timestamp lmTs;

	//bi-directional many-to-one association to MnHotel
	private MnHotel mnHotel;

	public MnHotelInfo() {
	}

	public int getHotelInfoId() {
		return this.hotelInfoId;
	}

	public void setHotelInfoId(int hotelInfoId) {
		this.hotelInfoId = hotelInfoId;
	}

	public int getHotelInfoBuildingNumber() {
		return this.hotelInfoBuildingNumber;
	}

	public void setHotelInfoBuildingNumber(int hotelInfoBuildingNumber) {
		this.hotelInfoBuildingNumber = hotelInfoBuildingNumber;
	}

	public String getHotelInfoCity() {
		return this.hotelInfoCity;
	}

	public void setHotelInfoCity(String hotelInfoCity) {
		this.hotelInfoCity = hotelInfoCity;
	}

	public String getHotelInfoCountry() {
		return this.hotelInfoCountry;
	}

	public void setHotelInfoCountry(String hotelInfoCountry) {
		this.hotelInfoCountry = hotelInfoCountry;
	}

	public String getHotelInfoGuid() {
		return this.hotelInfoGuid;
	}

	public void setHotelInfoGuid(String hotelInfoGuid) {
		this.hotelInfoGuid = hotelInfoGuid;
	}

	public String getHotelInfoPhone() {
		return this.hotelInfoPhone;
	}

	public void setHotelInfoPhone(String hotelInfoPhone) {
		this.hotelInfoPhone = hotelInfoPhone;
	}

	public int getHotelInfoRooms() {
		return this.hotelInfoRooms;
	}

	public void setHotelInfoRooms(int hotelInfoRooms) {
		this.hotelInfoRooms = hotelInfoRooms;
	}

	public String getHotelInfoStreet() {
		return this.hotelInfoStreet;
	}

	public void setHotelInfoStreet(String hotelInfoStreet) {
		this.hotelInfoStreet = hotelInfoStreet;
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

	public MnHotel getMnHotel() {
		return this.mnHotel;
	}

	public void setMnHotel(MnHotel mnHotel) {
		this.mnHotel = mnHotel;
	}

}