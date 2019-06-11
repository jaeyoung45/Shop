package com.shop.domain;

import java.util.Date;

public class GoodsVO {
	private int gdsNum;
	private String gdsName;
	private String cateCode;
	private int gdsPrice;
	private int gdsStock;
	private String gdsDes;
	private String gdsImg;
	private String gdsThumbImg;
	private Date gdsDate;

	public GoodsVO() {
	}

	public GoodsVO(String gdsName, String cateCode, int gdsPrice, int gdsStock, String gdsDes) {
		this.gdsName = gdsName;
		this.cateCode = cateCode;
		this.gdsPrice = gdsPrice;
		this.gdsStock = gdsStock;
		this.gdsDes = gdsDes;
	}

	public GoodsVO(String gdsName, String cateCode, int gdsPrice, int gdsStock, String gdsDes, String gdsImg) {
		this.gdsName = gdsName;
		this.cateCode = cateCode;
		this.gdsPrice = gdsPrice;
		this.gdsStock = gdsStock;
		this.gdsDes = gdsDes;
		this.gdsImg = gdsImg;
	}
	
	public GoodsVO(int gdsNum, String gdsName, String cateCode, int gdsPrice, int gdsStock, String gdsDes) {
		this.gdsNum = gdsNum;
		this.gdsName = gdsName;
		this.cateCode = cateCode;
		this.gdsPrice = gdsPrice;
		this.gdsStock = gdsStock;
		this.gdsDes = gdsDes;
	}

	public GoodsVO(int gdsNum, String gdsName, String cateCode, int gdsPrice, int gdsStock, String gdsDes, String gdsImg) {
		this.gdsNum = gdsNum;
		this.gdsName = gdsName;
		this.cateCode = cateCode;
		this.gdsPrice = gdsPrice;
		this.gdsStock = gdsStock;
		this.gdsDes = gdsDes;
		this.gdsImg = gdsImg;
	}

	public int getGdsNum() {
		return gdsNum;
	}

	public void setGdsNum(int gdsNum) {
		this.gdsNum = gdsNum;
	}

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public int getGdsPrice() {
		return gdsPrice;
	}

	public void setGdsPrice(int gdsPrice) {
		this.gdsPrice = gdsPrice;
	}

	public int getGdsStock() {
		return gdsStock;
	}

	public void setGdsStock(int gdsStock) {
		this.gdsStock = gdsStock;
	}

	public String getGdsDes() {
		return gdsDes;
	}

	public void setGdsDes(String gdsDes) {
		this.gdsDes = gdsDes;
	}

	public String getGdsImg() {
		return gdsImg;
	}

	public void setGdsImg(String gdsImg) {
		this.gdsImg = gdsImg;
	}

	public String getGdsThumbImg() {
		return gdsThumbImg;
	}

	public void setGdsThumbImg(String gdsThumbImg) {
		this.gdsThumbImg = gdsThumbImg;
	}

	public Date getGdsDate() {
		return gdsDate;
	}

	public void setGdsDate(Date gdsDate) {
		this.gdsDate = gdsDate;
	}

	@Override
	public String toString() {
		return "GoodsVO [gdsNum=" + gdsNum + ", gdsName=" + gdsName + ", cateCode=" + cateCode + ", gdsPrice="
				+ gdsPrice + ", gdsStock=" + gdsStock + ", gdsDes=" + gdsDes + ", gdsImg=" + gdsImg + ", gdsThumbImg="
				+ gdsThumbImg + ", gdsDate=" + gdsDate + "]";
	}

}
