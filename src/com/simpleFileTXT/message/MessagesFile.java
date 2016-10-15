package com.simpleFileTXT.message;

import java.util.ArrayList;

/**
 *
 * @author André Luiz Alexandrini -
 * This class is a collection of MessageFile.
 *
 */

public class MessagesFile {

	private ArrayList<MessageFile> messagesFile = new ArrayList<>();

	/**
	 *
	 * @param messageFile - This method you can to set a MessageFile class
	 */
	public void addMessageFile(MessageFile messageFile){
		messagesFile.add(messageFile);
	}

	/**
	 *
	 * @return - This method you can to get the MessageFile's ArrayList
	 */
	public ArrayList<MessageFile> getMessagesFile(){
		return messagesFile;
	}

}
