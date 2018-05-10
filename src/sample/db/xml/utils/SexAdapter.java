package sample.db.xml.utils;

import model.Sex;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SexAdapter extends XmlAdapter<String, Sex> {
	
	@Override
	public String marshal(Sex s) throws Exception {
		return s.toString().toLowerCase();
	}

	@Override
	public Sex unmarshal(String string) throws Exception {
		return Sex.fromValue(string);
	}

}
