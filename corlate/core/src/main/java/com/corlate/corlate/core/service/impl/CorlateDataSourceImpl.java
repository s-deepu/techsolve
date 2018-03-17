package com.corlate.corlate.core.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.PropertyUnbounded;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corlate.corlate.core.service.CorlateDataSource;

@Component(immediate = true, metatype = true, label = "csvrender")
@Service
@Property(label = "Add csv Path", name="csv_path", description = "The csv file path will be available here. Leaving it empty will return eempty results.")

public class CorlateDataSourceImpl implements CorlateDataSource {

	private static final String PROPERTY_CSV_FILE_PATH = "property.csv.file.path";
	private static String states[];
	private static String paths[];
	private static String[] fileData;
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public Map<Object, Object> datasource() {

		String line;
		Map<Object, Object> mapObj = new HashMap<Object, Object>();
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(new File(PROPERTY_CSV_FILE_PATH)));
			while ((line = br.readLine()) != null) {
				fileData = line.split(",");
				int seperate = fileData.length / 2;
				states = new String[seperate];
				paths = new String[seperate];
				for (int i = 0; i < fileData.length; i++) {
					if (0 == i % 2) {
						for (int j = 0; j < seperate; j++) {
							states[j] = fileData[i];
						}
					}
					if (0 != i % 2) {
						for (int j = 0; j < seperate; j++) {
							paths[j] = fileData[i];
						}
					}
				}
			}
			for (int j = 0; j < fileData.length; j++) {
				mapObj.put(states[j], paths[j]);
			}
		} catch (FileNotFoundException e) {
			log.debug("File Not Found");

		} catch (IOException ie) {
			log.debug("Input Not rendering Properly");
		}
		return mapObj;

	}

}
