

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;

/*
 * Model data for the phone book application.  
 */

public class PhoneBookModel {

	private PhoneBookView phonebookview;

	// The following are various states captured by the model
	public static String ADD_NAME_STATE = "ADD_NAME";
	public static String ADD_NUMBER_STATE = "ADD_NUMBER";
	public static String SEARCH_STATE = "SEARCH";
	public static String IDLE_STATE = "IDLE";
	public static String SEARCH_RESULT_STATE = "SEARCH_RESULT";
	public static String ERROR_STATE = "ERROR";
	public static String EXIT_STATE = "EXIT";

	//更新資料
	public static String UPDATE_NAME_STATE = "UPDATE_NAME";
	public static String UPDATE_NUMBER_STATE = "UPDATE_NUMBER";
	public static String UPDATE_RESULT_STATE = "UPDATE_RESULT";
	//刪除資料
	public static String DELETE_STATE = "DELETE_NAME";
	public static String DELETE_RESULT_STATE = "DELETE_RESULT";

	
	// Private fields used to track various model data
	private String state = IDLE_STATE;
	private String searchResult = null;
	//更新
	private String updateResult = null;
	//刪除
	private String deleteResult = null;
	private DataBase d_base;
	//private Hashtable<String,String> phoneBook = null;

	/**
	 * set the state
	 * @param aState
	 */
	public void setState(String aState) {
		state = aState;
		phonebookview.stateHasChanged(this, state);
	}
	
	/**
	 * add a phone entry
	 * @param name
	 * @param number
	 */
	public void addAnEntry(String name, String number) {
		d_base.addAnEntry(name, number);
	}

	/**
	 * search the phone number and set the searchResult field
	 * @param name
	 */
	public void searchPhoneNumber(String name) {
		searchResult = d_base.searchPhoneNumber(name);
	}

	/**
	 * return the search result
	 */
	public String getSearchResult() {
		return searchResult;
	}

	/**
	 * update a phone entry
	 * @param name
	 * @param number
	 */
	public void updateData(String name,String number) {
		updateResult= d_base.updateData(name, number);
	}
	public String getUpdateResult() {
		return updateResult;
	}

	/**
	 * delete a phone entry
	 * @param name
	 * @param number
	 */
	public void deleteData(String name)
	{
		deleteResult= d_base.deleteData(name);
	}
	public String getDeleteResult() {
		return deleteResult;
	}
	/**
	 * get the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * constructor
	 * @param view
	 */
	public PhoneBookModel(PhoneBookView view) {
		phonebookview = view;
		
		d_base = new Text("Data.txt");
		
		//phoneBook = new Hashtable<String,String>();
	}
	
}
