package id.hikki.macapatjawa.Model;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("gatra")
	private String gatra;

	@SerializedName("lagu")
	private String lagu;

	@SerializedName("makna")
	private String makna;

	@SerializedName("arti")
	private String arti;

	@SerializedName("wilangan")
	private String wilangan;

	@SerializedName("watak")
	private String watak;

	@SerializedName("tembang")
	private String tembang;

	@SerializedName("jeneng")
	private String jeneng;

	public String getGatra(){
		return gatra;
	}

	public String getLagu(){
		return lagu;
	}

	public String getMakna(){
		return makna;
	}

	public String getArti(){
		return arti;
	}

	public String getWilangan(){
		return wilangan;
	}

	public String getWatak(){
		return watak;
	}

	public String getTembang(){
		return tembang;
	}

	public String getJeneng(){
		return jeneng;
	}
}