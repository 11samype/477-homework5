package platform;

import gui.ExecPanel;
import gui.StatusPanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public interface IPlugin {
	JPanel executeMainGUI();
	void statusSetup(StatusPanel statusPanel);
}
