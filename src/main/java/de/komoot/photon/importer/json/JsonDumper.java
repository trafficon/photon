package de.komoot.photon.importer.json;

import de.komoot.photon.importer.Importer;
import de.komoot.photon.importer.Utils;
import de.komoot.photon.importer.model.PhotonDoc;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * useful to create json files that can be used for fast re imports
 *
 * @author christoph
 */
@Slf4j
public class JsonDumper implements Importer {
	private PrintWriter writer = null;

	public JsonDumper(String filename) throws FileNotFoundException {
		this.writer = new PrintWriter(filename);
	}

	@Override
	public void add(PhotonDoc doc) {
		try {
			writer.println("{\"index\": {}}");
			writer.println(Utils.convert(doc).string());
		} catch(IOException e) {
			log.error("error writing json file", e);
		}
	}

	@Override
	public void finish() {
		if(writer != null) {
			writer.close();
		}
	}
}
