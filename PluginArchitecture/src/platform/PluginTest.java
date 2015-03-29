package platform;

public class PluginTest implements IPlugin {

	@Override
	public void execute() {
		System.out.println("Execute: status'd");

	}

	@Override
	public void status() {
		System.out.println("Status: execut'd");

	}

}
