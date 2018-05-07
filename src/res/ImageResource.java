package res;

import javax.swing.*;

public enum ImageResource {
	
	DROP_IMAGE("DR.jpg"),
	WP_720p("720p_wp.jpg"),
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
