package com.ampro.robinhood.endpoint.ratings.data;

import com.ampro.robinhood.endpoint.ApiElement;

/**
 * The Class RatingResult.
 *
 * @author MainStringArgs
 */
public class Rating implements ApiElement {

  /** The summary. */
  private RatingSummary summary;

  /** The instrument id. */
  private String instrument_id;

  /**
   * Gets the summary.
   *
   * @return the summary
   */
  public RatingSummary getSummary() {
    return summary;
  }

  /**
   * Gets the instrument id.
   *
   * @return the instrument id
   */
  public String getInstrumentId() {
    return instrument_id;
  }

  @Override
  public String toString() {
    return "RatingResult [summary=" + summary + ", instrument_id=" + instrument_id + "]";
  }

  @Override
  public boolean requiresAuth() {
    return false;
  }

}
