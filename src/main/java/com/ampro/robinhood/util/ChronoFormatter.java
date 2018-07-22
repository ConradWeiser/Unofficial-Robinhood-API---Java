package com.ampro.robinhood.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Class containing the format of ISO 8601 date returned by Robinhood
 *
 * @author Justin Albano
 */
public final class ChronoFormatter {

	public static final String RH_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSX";

	private ChronoFormatter() {}

	public static DateTimeFormatter getFormat() {
		return DateTimeFormatter.ofPattern(RH_ISO_8601);
	}

    /**
     * Parse a ZonedDateTime from a Robinhood ISO 8601 date format.
     *
     * @param isoDate the string to parse from.
     * @return The parsed {@link ZonedDateTime}
     */
	public static ZonedDateTime parseDefault(String isoDate) {
        return ZonedDateTime.parse(isoDate, ChronoFormatter.getFormat());
    }
}
