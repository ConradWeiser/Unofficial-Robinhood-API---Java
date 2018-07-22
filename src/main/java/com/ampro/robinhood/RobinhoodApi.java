package com.ampro.robinhood;

import com.ampro.robinhood.endpoint.ApiElement;
import com.ampro.robinhood.endpoint.ApiElementList;
import com.ampro.robinhood.endpoint.account.data.Account;
import com.ampro.robinhood.endpoint.account.data.*;
import com.ampro.robinhood.endpoint.account.methods.*;
import com.ampro.robinhood.endpoint.authorize.data.AuthorizationData;
import com.ampro.robinhood.endpoint.authorize.data.AuthorizationData.Token;
import com.ampro.robinhood.endpoint.authorize.methods.AuthorizeWithMultifactor;
import com.ampro.robinhood.endpoint.authorize.methods.GetAuthorizationData;
import com.ampro.robinhood.endpoint.authorize.methods.LogoutFromRobinhood;
import com.ampro.robinhood.endpoint.collection.data.InstrumentCollectionList;
import com.ampro.robinhood.endpoint.collection.methods.GetCollectionData;
import com.ampro.robinhood.endpoint.fundamentals.data.TickerFundamental;
import com.ampro.robinhood.endpoint.fundamentals.data.TickerFundamentalList;
import com.ampro.robinhood.endpoint.fundamentals.methods.GetTickerFundamental;
import com.ampro.robinhood.endpoint.fundamentals.methods.GetTickerFundamentalList;
import com.ampro.robinhood.endpoint.instrument.data.Instrument;
import com.ampro.robinhood.endpoint.instrument.data.InstrumentList;
import com.ampro.robinhood.endpoint.instrument.methods.GetAllInstruments;
import com.ampro.robinhood.endpoint.instrument.methods.GetInstrumentByTicker;
import com.ampro.robinhood.endpoint.instrument.methods.SearchInstrumentsByKeyword;
import com.ampro.robinhood.endpoint.option.data.Option;
import com.ampro.robinhood.endpoint.option.data.OptionList;
import com.ampro.robinhood.endpoint.option.methods.GetOptionsMethod;
import com.ampro.robinhood.endpoint.orders.data.SecurityOrder;
import com.ampro.robinhood.endpoint.orders.data.SecurityOrderList;
import com.ampro.robinhood.endpoint.orders.enums.OrderTransactionType;
import com.ampro.robinhood.endpoint.orders.enums.TimeInForce;
import com.ampro.robinhood.endpoint.orders.methods.*;
import com.ampro.robinhood.endpoint.quote.data.TickerQuote;
import com.ampro.robinhood.endpoint.quote.data.TickerQuoteList;
import com.ampro.robinhood.endpoint.quote.methods.GetTickerQuote;
import com.ampro.robinhood.endpoint.quote.methods.GetTickerQuoteList;
import com.ampro.robinhood.endpoint.ratings.data.RatingList;
import com.ampro.robinhood.endpoint.ratings.method.GetRatingsData;
import com.ampro.robinhood.net.ApiMethod;
import com.ampro.robinhood.net.pagination.PaginatedIterator;
import com.ampro.robinhood.net.request.LoginStatus;
import com.ampro.robinhood.throwables.NotLoggedInException;
import com.ampro.robinhood.throwables.RequestTooLargeException;
import com.ampro.robinhood.throwables.RobinhoodApiException;
import com.ampro.robinhood.throwables.TickerNotFoundException;
import io.github.openunirest.http.exceptions.UnirestException;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ampro.robinhood.net.request.LoginStatus.*;

/**
 * <p><h2>
 * A {@link RobinhoodApi} instance is used as an intermediary between the
 * Robinhood servers and your java/kotlin/whatever application.
 * </h2>
 * <p>
 *     <h3>{@link Configuration}</h3>
 *     Each API instance contains it's own {@link Configuration} which contains
 *     information about account tokens and urls (if the API has been logged
 *     in).
 *
 * @author Conrad Weisse, Jonathan Augustine
 */
public class RobinhoodApi {

	/** The Logger object used for the custom error handling */
	public static final Logger log = Logger.getLogger(RobinhoodApi.class.getName());

	/**
	 * The active instance of the Configuration Manager.
     * The Auth-token is stored in this instance.
	 */
	private final Configuration config;

	/**
	 * Constructor which creates all of the access points to use the API.
	 * This constructor does not require the Username and Password, thus giving limited
	 * access to the API. See Robinhood Unofficial Documentation at following link
	 * to see what can and cannot be used if you do not authorize a user
	 */
	public RobinhoodApi() {
		this.config = new Configuration();
	}

	/**
	 * Constructor which creates all of the access points to use the API.
	 * This constructor requires both a Username and Password and attempts to
     * authorize the user.
	 * On success, the {@link AuthorizationData} will be stored in the Configuration
	 * instance to be retrieved elsewhere. On failure, an error will be thrown.
     * <br>
	 * <b><em>NOTE: This cannot be used with multifactor authorization.</em></b>
	 *
	 * @param username The user's email (that they use with robinhood)
	 * @param password The user's password
	 * @throws RobinhoodApiException If the login failed
	 */
	public RobinhoodApi(String username, String password)
    throws RobinhoodApiException {
        this();
		//Log the user in and store the auth token
        LoginStatus status = this.login(username, password);
        if (status != SUCCESS) {
            throw new RobinhoodApiException("Failed to log user in: "
                                                    + status.getValue());
        }
	}

    /**
     * Request {@link AuthorizationData} from Robinhood and adds it to the
     * {@link Configuration}.
     * @param email The email to login with
     * @param password The password
     * @return The {@link AuthorizationData} returned or null if failed.
     *          This will wither contain a token or multifactor details.
     */
	public AuthorizationData requestAuthData(String email, String password) {
        AuthorizationData data = new GetAuthorizationData(email, password).execute();
        this.config.setAuthData(data);
        return data;
    }

	/**
	 * Method which logs a user in given a email and password. This method
	 * automatically stores the authorization token in with the instance,
	 * allowing any method which requires the token to have immediate access to
	 * it.
	 *
	 * This method is ran if you created the RobinhoodApi class using the
	 * constructor with both a email and password, but is available if you
	 * wish to get the authorization token again. Usually ran after the user
	 * is logged out to refresh the token
	 *
	 * @param email The user's email
	 * @param password The user's password
	 * @return {@link LoginStatus#FAILURE} if the user could not be logged in.
	 *                  {@link LoginStatus#SUCCESS} otherwise
	 *
	 */
	public LoginStatus login(String email, String password) {
        //TODO: Implement multifactor authorization
        try {
            //Save the token into the configuration to be used with other methods
            AuthorizationData authData = this.requestAuthData(email, password);
            if (authData == null) {
                return FAILURE.setValue("no token");
            } else if (authData.mfaRequired()) {
                return REQ_MFA.setValue("requires mfa " + authData.getMfaType());
            } else if (authData.getToken() == null) {
                return FAILURE.setValue("no token");
            }

            //Save the account number into the config to be used with other methods
            //TODO: Clean up the following line, it should not have to use
            //the array wrapper. Tuck that code elsewhere
            AccountArrayWrapper requestData = new GetAccounts(this.config).execute();
            if (requestData.getResult() == null) {
                return FAILURE.setValue("no account wrapper");
            }
            Account data = requestData.getResult();

            //If there is no account number, something went wrong.
            if (data.getAccountNumber() == null) {
                RobinhoodApi.log.log(Level.SEVERE, "Failed to get account Number.");
                RobinhoodApi.log.log(Level.SEVERE, "Unable to login!");
                return FAILURE.setValue("Failed to get account Number.");
            }

            this.config.setAccountNumber(data.getAccountNumber());

        } catch (UnirestException | NullPointerException e) {
            RobinhoodApi.log.throwing(RobinhoodApi.class.getName(), "login", e);
            return FAILURE;
        }

        return SUCCESS;
    }

    /**
     * Login with a Multifactor/Two-factor authorization code.
     * This requires the {@link Configuration Configuration's} {@link AuthorizationData}
     * to be set with {@link RobinhoodApi#requestAuthData(String, String)}
     *
     * @param email The user's email
     * @param password The user's password
     * @param code The mfa code
     * @return {@link LoginStatus#FAILURE} if the user could not be logged in.
     *                  {@link LoginStatus#SUCCESS} otherwise
     */
    public LoginStatus loginMultifactor(String email, String password, String code) {
	    AuthorizationData authData = this.config.getAuthData();
	    if (authData == null) {
	        return FAILURE.setValue("authorization data not set");
        } else  if (!authData.mfaRequired()) {
            return this.login(email, password);
        }
        authData.setMfaCode(code);
        Token token = new AuthorizeWithMultifactor(email, password, code).execute();
        if (token == null) {
            return FAILURE;
        }
        authData.setToken(token);
        return SUCCESS;
    }

	/**
	 * Method which forces the authorization token to expire, logging the user
	 * out if the user is currently logged in.
	 *
     * @return an enum containing either "SUCCESS"or "NOT_LOGGED_IN"
	 */
	public LoginStatus logUserOut() {
		if(!this.isLoggedIn()) {
			return LoginStatus.NOT_LOGGED_IN;
		}

		//Create the APIMethod which attempts to log the user out, and run it
        //note: Logout has no return
	    new LogoutFromRobinhood(this.config).execute();

        //Just to be safe, wipe the token from memory
        this.config.clear();

		//If we made it to this point without throwing something, it worked!
		return SUCCESS;
	}

	//ACCOUNT DATA

	/**
	 * Method returning a {@link Account} using the currently logged in
     * user
     * @return The requested {@link Account}
	 * @throws NotLoggedInException if the user is not logged in
	 */
	public Account getAccountData() {
		//TODO: This is a temporary fix, as the Robinhood API seems
		//to have some features implemented, but are not used yet
		AccountArrayWrapper data = new GetAccounts(this.config).execute();
		return data.getResult();
	}

	/**
	 * Method returning a {@link BasicUserInfo} for the currently logged in user
     *
     * @return Basic information about the user
	 * @throws NotLoggedInException if the user is not logged in
	 */
	public BasicUserInfo getBasicUserInfo()  {
		return new GetBasicUserInfo(this.config).execute();
	}

	/**
	 * Method returning a {@link BasicAccountHolderInfo} for the currently logged in user
	 * @throws NotLoggedInException if the user is not logged in
	 */
	public BasicAccountHolderInfo getAccountHolderInfo() {
		return new GetBasicAccountHolderInfo(this.config).execute();
	}

	/**
	 * Method returning a {@link AccountHolderAffiliation} for the currently logged in user
	 * @throws NotLoggedInException if the user is not logged in
	 */
	public AccountHolderAffiliation getAccountHolderAffiliation() {
		return new GetAccountHolderAffiliationInfo(this.config).execute();
	}

	/**
	 * Method returning a {@link AccountHolderEmployment} for the currently logged in user
	 * @throws NotLoggedInException if the user is not logged in
	 */
	public AccountHolderEmployment getAccountHolderEmployment() {
		return new GetAccountHolderEmploymentInfo(this.config).execute();
	}

	/**
	 * Method returning a {@link AccountHolderInvestmentProfile} for the
     * currently logged in user
     * @return AccountHolderInvestmentProfile
	 * @throws NotLoggedInException if the user is not logged in
	 */
	public AccountHolderInvestmentProfile getAccountInvestmentProfile() {
		return new GetAccountHolderInvestmentProfile(this.config).execute();
	}

	//ORDERS

    /**
     * Returns a list of {@link Position} for each entry on the account's
     * watchlist. If the quantity of the {@link Position} is above 0,
     * that means that you have an active position in that stock. All of the
     * other information which can be retrieved from this can be found in the
     * Position page itself
     * @return A list of {@link Position}, both held and only watched entities
     * @throws NotLoggedInException If not logged in
     */
    public List<Position> getAccountWatchlist() {
        //Return the current account positions
        PositionList response = new GetAccountPositions(this.config).execute();
        List<Position> out = new ArrayList<>();
        buildIterable(response).forEach(out::add);
        return out;
    }

    /**
     * Method which gets all of the account positions a user actually has shares in.
     * @return List containing all of the stocks an account has shares in.
     * @throws NotLoggedInException If not logged in
     */
    public List<Position> getAccountPositions() {
        //Get the entire watchlist for the account
        List<Position> accountWatchlist = this.getAccountWatchlist();

        //Parse the watchlist for things which have a quantity
        // >= 1 and return it
        Vector<Position> accountPositions = new Vector<>();

        accountWatchlist.forEach( position -> {
            if(position.getQuantity() >= 1) {
                accountPositions.add(position);
            }
        });
        return accountPositions;
    }

    /**
     * @return Closed and open orders.
     * @throws NotLoggedInException If not logged in
     */
    public List<SecurityOrder> getOrders() {
        SecurityOrderList orders = new GetOrdersMethod(this.config).execute();
        List<SecurityOrder> out = new ArrayList<>();
        //Load all the pages into one list
        buildIterable(orders).forEach(out::add);
        return out;
    }

    /**
     * Method which returns a {@link SecurityOrder} after running a LIMIT order
     * given the supplied parameters.
     * @param ticker The ticker which the buy or sell order should be performed on
     * @param timeInForce The Enum representation for when this order should be made
     * @param limitPrice The price you're willing to accept in a sell, or pay in a buy
     * @param quantity The number of shares you would like to buy or sell
     * @param orderType Which type of order is being made. A buy, or sell.

     * @return The {@link SecurityOrder} that was made
     *
     * @throws TickerNotFoundException Thrown when the ticker supplied to the
     *                                   method is invalid.
     * @throws NotLoggedInException  Thrown when this Robinhood Api instance is
     *                      not logged into an account. Run the login method first.
     */
    public SecurityOrder makeLimitOrder(String ticker, TimeInForce timeInForce,
                                        float limitPrice, int quantity,
                                        OrderTransactionType orderType)
    throws TickerNotFoundException {
        return new MakeLimitOrder(ticker, timeInForce, limitPrice, quantity,
                                  orderType, this.config).execute();
    }

    /**
     * @param ticker The ticker which the buy or sell order should be performed on
     * @param timeInForce The Enum representation for when this order should be made
     * @param limitPrice The price you're willing to accept in a sell, or pay in a buy
     * @param quantity The number of shares you would like to buy or sell
     * @param orderType Which type of order is being made. A buy, or a sell
     * @param stopPrice The price at which the stop trigger converts the order
     *                      into a market order
     * @return The created {@link SecurityOrder}
     * @throws TickerNotFoundException The ticker supplied is not valid.
     */
    public SecurityOrder makeLimitStopOrder(String ticker, TimeInForce timeInForce,
                                            float limitPrice, int quantity,
                                            OrderTransactionType orderType,
                                            float stopPrice)
    throws TickerNotFoundException {
        return new MakeLimitStopOrder(ticker, timeInForce, limitPrice,
                quantity, orderType, stopPrice, this.config).execute();
    }

    /**
     * @param ticker What ticker you are performing this order on
     * @param quantity How many shares should be transacted
     * @param orderType Which type of order is being made. A buy, or a sell.
     * @param time The Enum representation of when this order should be made.
     * @return The SecurityOrder object with the API response.
     * @throws TickerNotFoundException if the ticker supplied was invalid
     * @throws NotLoggedInException if instance not logged in
     */
    public SecurityOrder makeMarketOrder(String ticker, int quantity,
                                         OrderTransactionType orderType,
                                         TimeInForce time)
    throws TickerNotFoundException {
        return new MakeMarketOrder(ticker, quantity, orderType, time, this.config)
                .execute();
    }

    /**
     * @param ticker The stock ticker
     * @param quantity The number of elements to order
     * @param orderType {@link OrderTransactionType#BUY} or {@link OrderTransactionType#SELL}
     * @param time The time and/or duration an order will be active.
     * @param stopPrice The stop (activation) price
     * @return The {@link SecurityOrder} created, this order can fail
     *              ({@link SecurityOrder#reject_reason}
     * @throws TickerNotFoundException If the ticker is not tracked by RH
     * @throws NotLoggedInException If instance is not logged in
     */
    public SecurityOrder makeMarketStopOrder(String ticker, int quantity,
                                             OrderTransactionType orderType,
                                             TimeInForce time, float stopPrice)
    throws TickerNotFoundException {
        return new MakeMarketStopOrder(ticker, quantity, orderType, time, stopPrice,
                                       this.config).execute();
    }

    /**
     * Cancel an order. The order must be open and not completed.
     *
     * @param order The {@link SecurityOrder} to cancel
     * @return The cancelled order as a {@link SecurityOrder}
     * @throws RobinhoodApiException If the order could not be cancelled
     */
    @Deprecated
    public SecurityOrder cancelOrder(SecurityOrder order)
    throws RobinhoodApiException {
        return new CancelOrderMethod(order, this.config).execute();
    }

    /**
     * TODO DOCS
     * @return
     *
     * @throws NotLoggedInException
     */
    public List<Option> getOptions() {
        OptionList options = new GetOptionsMethod(this.config).execute();
        return options.getResults();
    }

	//PUBLIC DATA

	/**
	 * Method returning a {@link TickerFundamental} for the supplied ticker name
     *
	 * @param ticker The Stock's ticker
	 */
	public TickerFundamental getFundamental(String ticker) {
		//Create the API method
		return new GetTickerFundamental(ticker).execute();
	}

	/**
	 * Get a {@link List} of {@link TickerFundamental}.
     *
	 * @param tickers A collection of stock tickers
	 * @return a {@link List} of {@link TickerFundamental}.
	 * @throws RequestTooLargeException If the Collection is longer than 10
	 * @author Jonathan Augustine
	 */
	public List<TickerFundamental> getFundamentalList(Collection<String> tickers)
    throws RequestTooLargeException {
		TickerFundamentalList list = new GetTickerFundamentalList(tickers).execute();
		List<TickerFundamental> out = new ArrayList<>();
		buildIterable(list).forEach(out::add);
		return out;
	}

	/**
	 * Method returning a {@link TickerQuote} for the supplied ticker.
     * Contains general information, such as the current asking price and the
     * last trading price. Does not require the API to be logged on.
	 * @param ticker Which symbol you are retrieving a quote for
	 * @return {@link TickerQuote}
     * @throws TickerNotFoundException If the quote is not found
	 */
	public TickerQuote getQuoteByTicker(String ticker)
    throws TickerNotFoundException {
        TickerQuote quote = new GetTickerQuote(ticker).execute();
        if (quote == null || quote.getSymbol() == null) {
            throw new TickerNotFoundException();
        }
		return quote;
	}

    /**
     * Get a list of security quotes by their tickers. The result is
     * SemiPaginated, which is why this can return a normal List
     * @param tickers The tickers to get quotes of (e.g. MSFT, FIT)
     * @return A list of {@link TickerQuote TickerQuoteElements}.
     *          A value in the list may be null if the ticker was not found
     *          on Robinhood.
     * @throws RequestTooLargeException if the collection is longer than 1,630
     */
	public List<TickerQuote> getQuoteListByTickers(Collection<String> tickers)
    throws RequestTooLargeException {
        TickerQuoteList list = new GetTickerQuoteList(tickers).execute();
        return list.getQuotes();
   }

    /**
     * @param ticker The stock ticker
     * @return The {@link Instrument} requested
     * @throws TickerNotFoundException If the ticker is not tracked by Robinhood
     */
    public Instrument getInstrumentByTicker(String ticker)
    throws TickerNotFoundException {
        InstrumentList list = new GetInstrumentByTicker(ticker).execute();
        if (list.isEmpty()) {
            throw new TickerNotFoundException(ticker);
        }
        return list.getResults().get(0);
    }

    /**
     * Gets a list of instruments by searching with the given keyword.
     * As of July 2018, it seems as this will not return a list greater than 10
     * elements.
     *
     * @param keyword The keyword to search with
     * @return A {@link List} of {@link Instrument InstrumentElements}
     *                  returned by Robinhood's search
     */
    public List<Instrument> getInstrumentsByKeyword(String keyword) {
        InstrumentList list = new SearchInstrumentsByKeyword(keyword).execute();
        return list.getResults();
    }

    /**
     * Get's every {@link Instrument} tracked by Robinhood.
     * This method performs several calls to the Robinhood servers and is
     * therefore rather expensive to use. Try to use it sparingly (it's not
     * like it's going to be changing all the time)
     *
     * @return Every {@link Instrument} tracked by Robinhood
     */
    public List<Instrument> getAllInstruments() {
        InstrumentList list = GetAllInstruments.get();
        ArrayList<Instrument> out = new ArrayList<>();
        buildIterable(list).forEach(out::add);
        return out;
    }

	/**
	 * Gets the collection data from Robinhood based on the given Collection
	 * Name. This method does not require a security token.
	 *
	 * Examples of collections include 'manufacturing', 'consumer-product', and
	 * '100-most-popular'
	 *
	 * @param collectionName
	 *            the collection name
	 * @return the collection data as a list of {@link Instrument}.
	 *
	 * @author MainStringArgs
	 */
	public InstrumentCollectionList getCollectionData(String collectionName) {
		return new GetCollectionData(collectionName).execute();
	}

    /**
	 * Gets the ratings by tickers.
	 *
	 * @author MainStringArgs
	 * @param tickers the tickers
	 * @return the ratings by tickers
	 * @throws RequestTooLargeException if request is greater than
     *                          {@link ApiMethod#MAX_TICKERS}
	 */
    public RatingList getRatingsByTickers(String... tickers)
    throws RequestTooLargeException {
        List<String> tkList = Arrays.asList(tickers);
        List<String> instrumentIds = new ArrayList<>();

        TickerQuoteList tqeList = new GetTickerQuoteList(tkList).execute();

        for (TickerQuote quote : tqeList.getQuotes()) {
            if (quote.getInstrumentId() != null) {
                instrumentIds.add(quote.getInstrumentId());
            }
        }
        return new GetRatingsData(instrumentIds).execute();
    }

	/**
	 * Gets the ratings by instrument ids.
	 *
	 * @author MainStringArgs
	 * @param ids the tickers
	 * @return the ratings by instrument ids
	 */
	public RatingList getRatingsByInstrumentIds(String... ids) {
		return new GetRatingsData(ids).execute();
	}

    /**
     * Build an {@link Iterable} based off a {@link PaginatedIterator}.
     * @param elementList The {@link ApiElementList} build from
     * @param <E> The ApiElement of the List
     * @return a "Paginated" Iterable
     */
    public <E extends ApiElement> Iterable<E> buildIterable(ApiElementList<E> elementList) {
    	return () -> new PaginatedIterator<>(elementList, RobinhoodApi.this.config);
	}

	/** @return {@code true} if the API has been logged in */
	public boolean isLoggedIn() {
	    return this.config.hasToken();
    }

    /** @return The API instance's {@link Configuration} */
	public Configuration getConfig() {
		return config;
	}

    /**
     * Method which returns the authentication for the logged in user, if one exists.
     * <br> To check whether an API instance is logged in, use
     * {@link RobinhoodApi#isLoggedIn()}.
     * @throws NotLoggedInException if not login information is available.
     */
    public String getAccountToken() {
        return this.config.getToken();
    }

    /**
     * Method allowing a user to input a token without logging in.
     * It is not suggested you use this unless you have a specific reason where
     * you need to inject a auth token into the instance, generally allowing the
     * system to resolve this token with a username and password is more 'secure'.
     */
    @Deprecated
    public void setAuthToken(String token) {
        this.config.setAuthToken(token);
    }

}
