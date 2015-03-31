package platform;

import gui.ExecPanel;
import gui.StatusPanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public interface IPlugin {
	void execute(ExecPanel execPanel);
	void status(StatusPanel statusPanel);
}
