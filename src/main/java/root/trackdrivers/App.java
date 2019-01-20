package root.trackdrivers;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    		if(args.length != 0) {
    			new HistoryTracker().trackHistroy(args[0]);
    		}else {
    			ClassLoader classLoader = App.class.getClassLoader();
            new HistoryTracker().trackHistroy(classLoader.getResource("input.txt").getFile());
    		}
    }
}
