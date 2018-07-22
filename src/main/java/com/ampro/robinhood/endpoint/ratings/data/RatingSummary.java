package com.ampro.robinhood.endpoint.ratings.data;

import com.ampro.robinhood.endpoint.ApiElement;

/**
 * The Class RatingSummary.
 *
 * @author MainStringArgs
 */
public class RatingSummary implements ApiElement {

  /** The num buy ratings. */
  private int num_buy_ratings;

  /** The num hold ratings. */
  private int num_hold_ratings;

  /** The num sell ratings. */
  private int num_sell_ratings;

  /**
   * Gets the num buy ratings.
   *
   * @return the num buy ratings
   */
  public int getNumBuyRatings() {
    return num_buy_ratings;
  }

  /**
   * Gets the num hold ratings.
   *
   * @return the num hold ratings
   */
  public int getNumHoldRatings() {
    return num_hold_ratings;
  }

  /**
   * Gets the num sell ratings.
   *
   * @return the num sell ratings
   */
  public int getNumSellRatings() {
    return num_sell_ratings;
  }

  @Override
  public String toString() {
    return "RatingSummary [num_buy_ratings=" + num_buy_ratings + ", num_hold_ratings="
        + num_hold_ratings + ", num_sell_ratings=" + num_sell_ratings + "]";
  }

  @Override
  public boolean requiresAuth() {
    return false;
  }

}
