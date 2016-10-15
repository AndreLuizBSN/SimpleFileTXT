package com.simpleFileTXT.message;

/**
 *
 * @author André Luiz Alexandrini
 *
 * This class set a message in your SimpleFileTXT class transaction.
 *
 */

public class MessageFile {

	private KindMessage kindMessage;
	private String message;
	private String stackTrace;

	/**
	 * @param kingMessage - This parameter is a enum to define a kind of message
	 * @param message - This parameter is a simple text about the response in the transaction
	 * @param stackTrace - This parameter is a exception getMessage.
	 */
	public MessageFile(KindMessage kingMessage, String message, String stackTrace){
		this.kindMessage = kingMessage;
		this.message = message;
		this.stackTrace = stackTrace;
	}

	/**
	 *
	 * @return - This method return a kind of message
	 */
	public KindMessage getKindMessage() {
		return kindMessage;
	}

	/**
	 *
	 * @return - This method return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 *
	 * @return - This method return the stackTrace about the message
	 */
	public String getStackTrace() {
		return stackTrace;
	}

}
