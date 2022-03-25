package id.hikki.macapatjawa.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Model{

	@SerializedName("data")
	private List<DataItem> data;

	public List<DataItem> getData(){
		return data;
	}
}