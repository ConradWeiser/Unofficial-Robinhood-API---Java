package com.ampro.robinhood;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogHandler extends Handler {

	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private final boolean writeToFile;
	private final PrintWriter fileWriter;

	public LogHandler() {
		//Force it to run the constructor without the write flag once created
		this(false);
	}

	public LogHandler(boolean writeFlag) {
		PrintWriter out = null;

		try {
			File log = new File("robinhood-api.log");
			if(!log.exists() || !log.createNewFile()) {
				out = new PrintWriter(new BufferedWriter(new FileWriter(log, true)), true);
			} else {
				throw new IOException();
			}
		} catch (IOException ex) {
			System.err.println("Could not use the Log Handler. Using Java System.out instead");
			ex.printStackTrace();
			writeFlag = false;
		}

		writeToFile = writeFlag;
		fileWriter = out;
	}

	@Override
	public void close() {
		if (writeToFile) {
			fileWriter.flush();
			fileWriter.close();
		}
	}

	@Override
	public void flush() {
		if(writeToFile) {
			fileWriter.flush();
		}
	}

	@Override
	public void publish(LogRecord record) {
		StringBuilder builder = new StringBuilder();
		DateFormat format = new SimpleDateFormat(DATE_PATTERN);

		//Timestamp
		builder.append("[").append(format.format(new Date())).append("]");
		//Severity
		builder.append("[").append(record.getLevel()).append("] ");
		//Message
		builder.append(record.getMessage()).append("\n");
		//Stack Trace - Error debugging is important
		Throwable trace = record.getThrown();

		//As long as the throwable exists, append it. Sometimes it is null
		if(trace != null) {

			StringWriter stackTrace = new StringWriter();
			trace.printStackTrace(new PrintWriter(stackTrace));
			builder.append(stackTrace.getBuffer());
		}

		System.out.println(builder.toString());
		if (writeToFile) {
			fileWriter.println(builder.toString());
		}

	}
}
