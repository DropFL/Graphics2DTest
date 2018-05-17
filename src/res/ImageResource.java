package res;

import javax.swing.*;

public enum ImageResource {
	
	DROP_IMAGE("DR.jpg"),
	UNIT_IMAGE("Unit.png"),
	WP_720p("720p_wp.jpg"),
	Map1("Map1_1280x720.jpg"),
	Block1("TmpBlock1.jpg"),
	Shield("Shield.png"),
	Ghost1("Ghost_type1.png"),
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
