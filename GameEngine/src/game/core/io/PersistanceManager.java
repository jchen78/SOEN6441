package game.core.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PersistanceManager {
	public static final String
		ROW_SEPARATOR = "~",
		TABLE_SEPARATOR = "\r\n";
	
	public void persist(String identifier, String... lines) throws IOException {
		File outFile = new File(identifier);
		FileWriter out = new FileWriter(outFile);
		
		for (String line : lines)
			out.write(line + TABLE_SEPARATOR);
		
		out.close();
	}
	
	public String[] retrieve(String identifier) throws IOException {
		Path path = Paths.get(identifier);
		List<String> lines = Files.readAllLines(path);
		return lines.toArray(new String[lines.size()]);
	}
}
