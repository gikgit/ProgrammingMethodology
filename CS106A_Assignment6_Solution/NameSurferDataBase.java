import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
	ArrayList<NameSurferEntry> entries = new ArrayList<NameSurferEntry>();
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try {
			BufferedReader br = 
				new BufferedReader(new FileReader(filename));
				
			while (true) {
				String line = br.readLine();
				if (line == null) break;	
				NameSurferEntry entry = new NameSurferEntry(line);
				entries.add(entry);
			}
		
			br.close();
			
		} catch (IOException e) {
			System.out.println("Could not open that file.");
		}		
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		for(NameSurferEntry entry: entries){
			if(entry.name.equals(name)) return entry;
		}
		return null;
	}
}

