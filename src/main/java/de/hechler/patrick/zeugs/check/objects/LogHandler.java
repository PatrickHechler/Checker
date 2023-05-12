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
package de.hechler.patrick.zeugs.check.objects;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogHandler extends Handler {
	
	private static final String LOG_LEVEL_PROP   = Checker.LOG_LEVEL_PROP;
	private static final String LOG_HANDLER_PROP = Checker.LOG_HANDLER_PROP;
	
	/**
	 * this logger is used by the Checker module for logging
	 */
	public static final Logger LOG = Logger.getLogger("checker");
	
	static {
		switch (System.getProperty(LOG_LEVEL_PROP, "INFO").toUpperCase()) {
		case "OFF" -> LOG.setLevel(Level.OFF);
		case "SEVERE" -> LOG.setLevel(Level.SEVERE);
		case "WARNING" -> LOG.setLevel(Level.WARNING);
		case "INFO" -> LOG.setLevel(Level.INFO);
		case "CONFIG" -> LOG.setLevel(Level.CONFIG);
		case "FINE" -> LOG.setLevel(Level.FINE);
		case "FINER" -> LOG.setLevel(Level.FINER);
		case "FINEST" -> LOG.setLevel(Level.FINEST);
		case "ALL" -> LOG.setLevel(Level.ALL);
		default -> {
			LOG.warning(() -> "log level property is invalid '" + System.getProperty(LOG_LEVEL_PROP) + "' (use 'de.hechler.patrick.check.log=<VALUE>')");
			LOG.setLevel(Level.ALL);
		}
		}
		switch (System.getProperty(LOG_HANDLER_PROP, "LogHandler")) {
		case "default":
			break;
		case "LogHandler":
			LOG.setUseParentHandlers(false);
			LOG.addHandler(new LogHandler());
			break;
		default:
			try {
				Class<?> cls = Class.forName(System.getProperty(LOG_HANDLER_PROP));
				LOG.setUseParentHandlers(false);
				LOG.addHandler((Handler) cls.getConstructor().newInstance());
			} catch (Exception e) {
				LOG.warning(() -> "could not load logger handler '" + System.getProperty(LOG_HANDLER_PROP) + "' " + e);
			}
		}
	}
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private final PrintStream out;
	
	public LogHandler() { this(System.err); }
	
	public LogHandler(PrintStream out) { this.out = out; }
	
	
	@Override
	public void publish(LogRecord logRecord) {
		synchronized (out) {
			StringBuilder b = new StringBuilder();
			b.append('[').append(logRecord.getLoggerName()).append('|');
			if (Thread.currentThread().getId() == logRecord.getLongThreadID()) {
				b.append(Thread.currentThread().getName()).append('|');
			} else {
				for (Thread t : Thread.getAllStackTraces().keySet()) {
					if (t.getId() == logRecord.getLongThreadID()) {
						b.append(t.getName()).append('|');
						break;
					}
				}
			}
			b.append(logRecord.getLevel()).append("]: ");
			String emptyStart = " ".repeat(b.length());
			b.append(sdf.format(new Date(logRecord.getMillis())));
			b.append(" >> ").append(logRecord.getSourceClassName()).append(" in ").append(logRecord.getSourceMethodName());
			if (logRecord.getMessage() != null && !logRecord.getMessage().isEmpty()) {
				String msg = logRecord.getParameters() == null ? logRecord.getMessage()
						: String.format(logRecord.getMessage(), logRecord.getParameters());
				if (msg.lines().count() == 1L) {
					b.append("\tmsg: ").append(msg);
				} else {
					b.append('\n').append(emptyStart).append(" msg: ");
					msg.lines().limit(1L).forEach(b::append);
					msg.lines().skip(1L).forEach(l -> b.append('\n').append(emptyStart).append("      ").append(l));
				}
			}
			b.append('\n');
			out.print(b);
		}
	}
	
	@Override
	public void flush() { out.flush(); }
	
	@Override
	public void close() throws SecurityException { out.close(); }
	
}
