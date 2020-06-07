package org.gseii.sftp;
//////////////////////////////////////////////////////////////////////////
//object that will contains parameters received by SFTP JSON file 
//////////////////////////////////////////////////////////////////////////
public class Json {
	private short  id ;
	private double value ;
	
	public Json(short id, double value) {
		super();
		this.id = id;
		this.value = value;
	}
	public Json() {
		super();
	}
	@Override
	public String toString() {
		return "Json [id=" + id + ", value=" + value + "]";
	}
	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

}
