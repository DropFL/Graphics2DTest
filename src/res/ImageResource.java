package res;

import javax.swing.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

public enum ImageResource {
	
	DROP_IMAGE("DR.jpg"),
	WIN_IMAGE("wp.jpg");
	
	private ImageIcon imageIcon;
	
	ImageResource (String name) {
		try {
			imageIcon = new ImageIcon(getClass().getResource("images/" + name));
		} catch (Exception e) {
			e.printStackTrace();
			imageIcon = null;
		}
	}
	
	public ImageIcon getImageIcon () {
		return imageIcon;
	}
}
