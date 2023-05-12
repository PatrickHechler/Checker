//This file is part of the Checker Project
//DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
//Copyright (C) 2023  Patrick Hechler
//
//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program.  If not, see <https://www.gnu.org/licenses/>.
package de.hechler.patrick.zeugs.check;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import java.util.function.Function;

import de.hechler.patrick.zeugs.check.anotations.ParamCreater;
import de.hechler.patrick.zeugs.check.anotations.ParamInfo;
import de.hechler.patrick.zeugs.check.anotations.ParamParam;
import de.hechler.patrick.zeugs.check.interfaces.TwoVals;
import de.hechler.patrick.zeugs.check.objects.ArrayIterator;
import de.hechler.patrick.zeugs.check.objects.TwoValues;

/**
 * this class provides some simple utility methods which can be used together
 * with the {@link ParamCreater} annotation
 * 
 * @author pat
 */
public class ParamCreaterHelp {
	
	private ParamCreaterHelp() {}
	
	public static final String LINES_OF_INFO = "linesOfInfo";
	
	public static Iterable<String> linesOfInfo(@ParamParam Parameter param) throws IOException {
		ParamInfo info  = info(param);
		String    value = info.value();
		return () -> value.lines().iterator();
	}
	
	public static final String SPLIT_COMMA_OF_INFO = "splitCommaOfInfo";
	
	public static Iterable<String> splitCommaOfInfo(@ParamParam Parameter param) throws IOException {
		ParamInfo info  = info(param);
		String[]  array = info.value().split(",");
		return () -> new ArrayIterator<>(array);
	}
	
	public static final String LINES_OF_FILE = "linesOfFile";
	
	public static Iterable<String> linesOfFile(@ParamParam Parameter param) throws IOException {
		Path path = pathOfInfo(param);
		return Files.readAllLines(path);
	}
	
	public static final String LINES_OF_RESOURCE = "linesOfResource";
	
	public static Iterable<String> linesOfResource(@ParamParam Parameter param) throws IOException {
		URL          res    = resourceOfInfo(param);
		List<String> result = new LinkedList<>();
		try (Scanner sc = new Scanner(res.openStream())) {
			while (sc.hasNextLine()) {
				result.add(sc.nextLine());
			}
		}
		return result;
	}
	
	public static final String ENTRIES_OF_PROPERTY_FILE = "entriesOfPropertyFile";
	
	public static Iterable<TwoVals<String, String>> entriesOfPropertyFile(@ParamParam Parameter param) throws IOException {
		Path path = pathOfInfo(param);
		try (Reader reader = Files.newBufferedReader(path)) {
			return readPropEntries(reader);
		}
	}
	
	public static final String ENTRIES_OF_PROPERTY_RESOURCE = "entriesOfPropertyResource";
	
	public static Iterable<TwoVals<String, String>> entriesOfPropertyResource(@ParamParam Parameter param) throws IOException {
		URL url = resourceOfInfo(param);
		try (Reader reader = new InputStreamReader(url.openStream())) {
			return readPropEntries(reader);
		}
	}
	
	private static Iterable<TwoVals<String, String>> readPropEntries(Reader reader) throws IOException {
		Properties props = new Properties();
		props.load(reader);
		return props.entrySet().stream()
				.map((Function<Entry<Object, Object>, TwoVals<String, String>>) e -> new TwoValues<>((String) e.getKey(), (String) e.getValue()))
				.toList();
	}
	
	private static Path pathOfInfo(Parameter param) throws AssertionError {
		ParamInfo info = info(param);
		return Paths.get(info.value());
	}
	
	private static URL resourceOfInfo(Parameter param) throws AssertionError, NoSuchFileException {
		ParamInfo info = info(param);
		URL       res  = param.getDeclaringExecutable().getDeclaringClass().getResource(info.value());
		if (res == null) { throw new NoSuchFileException("resource '" + info.value() + "' not found"); }
		return res;
	}
	
	private static ParamInfo info(Parameter param) throws AssertionError {
		ParamInfo info = param.getAnnotation(ParamInfo.class);
		if (info == null) { throw new AssertionError("info is null (" + param + ')'); }
		return info;
	}
	
}
