package com.simpleFileTXT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.simpleFileTXT.enums.Status;
import com.simpleFileTXT.message.*;

/**
 *
 * @author André Luiz Alexandrini -
 *
 * SimpleFileTXT is a simple class to use in text document.
 * You can to write a new documents and read in a simple form.
 *
 */

public class SimpleFileTXT {

	private String fileName;
	private Status statusFile;
	private boolean createNewFileIfNotExists;
	private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader = null;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter = null;
    private ArrayList<String> data;

    //For messages
    private MessageFile msg;
    private MessagesFile msgs = new MessagesFile();

	private SimpleFileTXT(String fileName, Status statusFile, boolean createNewFileIfNotExists){
		this.fileName = fileName;
		this.statusFile = statusFile;
		this.createNewFileIfNotExists = createNewFileIfNotExists;
	}

	/**
	* Method Level 1
	* In this method, you set just a File Name(directory and file name).
	* The file to begin in a READ and WRITE mode, but this method don't create a new file case this file not exists.
	*/
	public static SimpleFileTXT setFile(String fileName){
		SimpleFileTXT simpleFileTXT = new SimpleFileTXT(fileName, Status.READWRITE, false);
		return simpleFileTXT;
	}

	/**
	 * Method Level 2
	* In this method, you set a File Name(directory and file name) and the Status(enum)
	* This method don't create a new file case this file not exists.
	 */
	public static SimpleFileTXT setFile(String fileName, Status statusFile){
		SimpleFileTXT simpleFileTXT = new SimpleFileTXT(fileName, statusFile, false);
		return simpleFileTXT;
	}

	/**
	 * Method Level 3
	 * In this method, you set a File Name(directory and file name), the Status(enum)
	 * and you have a choice for create or no the fila case not exists.
	 */
	public static SimpleFileTXT setFile(String fileName, Status statusFile, boolean createNewFileIfNotExists){
		SimpleFileTXT simpleFileTXT = new SimpleFileTXT(fileName, statusFile, createNewFileIfNotExists);
		return simpleFileTXT;
	}

	/**
	 * Internal method.
	 */
	private void openFile(){
		try{
			file = new File(this.fileName);
	        fileReader = new FileReader(file);
	        this.bufferedReader = new BufferedReader(fileReader);
		}catch(FileNotFoundException e){

			if(this.createNewFileIfNotExists){
				try{
					file.createNewFile();
					openFile();
				}catch(IOException ioE){
					this.msg = new MessageFile(KindMessage.ERROR, "Cannot create a new file!(openFile method)",ioE.getMessage());
					msgs.addMessageFile(msg);
				}
			}else{
				this.msg = new MessageFile(KindMessage.WARNING, "Cannot open the file!(openFile method)",e.getMessage());
				msgs.addMessageFile(msg);
			}
		}
	}

	/**
	 * Internal method.
	 */
	private void closeFileReader(){
		try{
			this.bufferedReader.close();
		}catch(IOException e){
			this.msg = new MessageFile(KindMessage.ERROR, "Cannot close the file for reader",e.getMessage());
			msgs.addMessageFile(msg);
		}
	}

	/**
	 * Internal method.
	 */
	private void closeFileWrite(){
		try{
			this.bufferedWriter.close();
		}catch(IOException e){
			this.msg = new MessageFile(KindMessage.ERROR, "Cannot close the file for writer", e.getMessage());
			msgs.addMessageFile(msg);
		}
	}

	/**
	 * This method receive of parameter a ArrayList of String.
	 * Each element of the ArrayList is a line in text archive.
	 */
	public void writeData(ArrayList<String> data){

		if(this.statusFile.equals(Status.READWRITE)){

			openFile();
			if(this.bufferedReader == null){
				this.msg = new MessageFile(KindMessage.WARNING, "The file not exists!(writeData method)",null);
				msgs.addMessageFile(msg);
			}else{

				this.data = data;

		        try{

		            //create a vector for text
		            ArrayList<String> text = new ArrayList<String>();

		            //add a value with the file content
		            while(this.bufferedReader.ready()){
		                text.add(this.bufferedReader.readLine());
		            }

		            this.fileWriter = new FileWriter(file);
		            this.bufferedWriter = new BufferedWriter(fileWriter);

		            //Add the information
		            for(int i=0;i<text.size();i++){
		                bufferedWriter.write(text.get(i));
		                bufferedWriter.newLine();
		            }
		            for(String s: this.data){
		                /*print the token.*/
		                bufferedWriter.write(s);
		                bufferedWriter.newLine();
		            }/*end for.*/

		            closeFileReader();
		            closeFileWrite();

		        }catch(IOException er){
					this.msg = new MessageFile(KindMessage.ERROR, "Error to read the file!(writeData method)",er.getMessage());
					msgs.addMessageFile(msg);
		        }
			}
		}else{
			this.msg = new MessageFile(KindMessage.INFO, "Cannot write this file! Your status is READ ONLY(writeData method)", null);
			msgs.addMessageFile(msg);
		}

    }

	/**
	 * This method return a ArrayList of String.
	 * Each element of the ArrayList is a line in text archive.
	 */
	public ArrayList<String> readData(){

        //create a vector for text
        ArrayList<String> text = new ArrayList<String>();

    	openFile();
		if(this.bufferedReader == null){
			this.msg = new MessageFile(KindMessage.WARNING, "The file not exists!(readData method)", null);
			msgs.addMessageFile(msg);
		}else{
			try{

	            //adiciona valor ao vetor com o que tem dentro do arquivo
	            while(this.bufferedReader.ready()){
	                text.add(this.bufferedReader.readLine());
	            }

	            closeFileReader();

	        }catch(FileNotFoundException e){
				this.msg = new MessageFile(KindMessage.ERROR, "Error to read the file(readData method)", e.getMessage());
				msgs.addMessageFile(msg);
	        }catch(IOException er){
				this.msg = new MessageFile(KindMessage.ERROR, "Error to read the file(readData method)", er.getMessage());
				msgs.addMessageFile(msg);
	        }

		}

		return text;

    }

	/**
	 * This method return a ArrayList of MessageFile.
	 */
	public ArrayList<MessageFile> getMessages(){
    	return this.msgs.getMessagesFile();
    }

}
