package com.ampro.robinhood.endpoint.ratings.method;

import java.util.Arrays;
import com.ampro.robinhood.endpoint.fundamentals.methods.GetFundamental;
import com.ampro.robinhood.endpoint.ratings.data.RatingElementList;
import com.ampro.robinhood.net.request.RequestMethod;

/**
 * The Class GetRatingsData that allows querying for ratings data of an instrument.
 *
 * @author MainStringArgs
 */
public class GetRatingsData extends GetFundamental {

  /**
   * Instantiates GetRatingsData.
   *
   * @param ids the id
   */
  public GetRatingsData(String... ids) {

    this.setUrlBase(RH_URL + "/midlands/ratings/");

    // convert the ids to a String a strip off the [ and ].
    final String queryParam = Arrays.toString(ids).replaceAll("\\[|\\]|\\s", "");

    this.addQueryParameter("ids", queryParam);
  
    // This method is ran as GET
    this.setMethodType(RequestMethod.GET);

    // Declare what the response should look like
    this.setReturnType(RatingElementList.class);

  }
}
