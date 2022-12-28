package de.hechler.patrick.zeugs.check.objects;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogHandler extends Handler {
	
	private static final String LOG_LEVEL_PROP   = "de.hechler.patrick.check.log.level";
	private static final String LOG_HANDLER_PROP = "de.hechler.patrick.check.log.handler";
	
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
		default -> LOG.setLevel(defLevel());
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
	
	private static Level defLevel() {
		LOG.warning(() -> "log level property is invalid '" + System.getProperty(LOG_LEVEL_PROP) + "' (use 'de.hechler.patrick.check.log=<VALUE>')");
		return Level.ALL;
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
