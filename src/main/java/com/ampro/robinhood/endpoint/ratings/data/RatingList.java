package com.ampro.robinhood.endpoint.ratings.data;

import com.ampro.robinhood.endpoint.ApiElementList;

/**
 * The Class RatingList.
 *
 * @author MainStringArgs
 */
public class RatingList extends ApiElementList<Rating> {

  @Override
  public boolean requiresAuth() {
    return false;
  }

  @Override
  public String toString() {
    return "RatingList [results=" + results + "]";
  }


}
