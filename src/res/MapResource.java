package res;

import javax.swing.*;

public enum MapResource {

	TestMap("TestMap.jmap");

	private String Mapdata;

	MapResource(String name) {
		try {
			//will read string data from jmap file
			//Mapdata = new getClass().getResource("maps/" + name);
		} catch (Exception e) {
			e.printStackTrace();
			Mapdata = null;
		}
	}
	
	public String getMapdata () {
		return Mapdata;
	}
}
