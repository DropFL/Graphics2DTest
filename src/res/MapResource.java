package res;

import com.rshtiger.platformer.Block;
import com.rshtiger.platformer.Map;

import javax.swing.*;
import java.io.*;

public enum MapResource {

	TestMap("TestMap.jmap");

	private Map mapdata;

	MapResource(String name) {
		String tmpSplit[];
		try {
			try{
				File file = new File("maps/" + name);

				FileReader filereader = new FileReader(file);

				BufferedReader bufReader = new BufferedReader(filereader);
				String line = "";
				while((line = bufReader.readLine()) != null){
					if(line.charAt(0) == '/' || line.charAt(0) == '@') continue;
					tmpSplit = line.split("::");
					if(tmpSplit[0] == "Block"){
						mapdata.AddBlock(new Block(Integer.parseInt(tmpSplit[1]), Integer.parseInt(tmpSplit[2]), Integer.parseInt(tmpSplit[3]), Integer.parseInt(tmpSplit[4])));
					}
				}

				bufReader.close();
			}catch (FileNotFoundException e) {
				// TODO: handle exception
			}catch(IOException e){
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapdata = null;
		}
	}
	
	public Map getMapdata () {
		return mapdata;
	}
}
