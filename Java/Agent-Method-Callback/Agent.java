import java.lang.instrument.Instrumentation;

public class Agent {
	public static void premain(String args, Instrumentation inst) {
		System.out.println("Starting the agent");
		inst.addTransformer(new SampleTransformer());
		Notifier n = new Notifier();
		n.methodEntry("test", "entry");
		n.methodEntry("test", "exit");
	}
}
