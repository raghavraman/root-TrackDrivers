package root.trackdrivers;

public class App {
	public static void main(String[] args) throws Exception {
		ClassLoader classLoader = App.class.getClassLoader();
		new HistoryTracker().trackHistroy(classLoader.getResource("input.txt").getFile());
	}
}
