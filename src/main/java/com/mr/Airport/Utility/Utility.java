package com.mr.Airport.Utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	
	public static String getCurrentDateAsString() {
        // Prendo la data attuale
        Date currentDate = new Date();
        
        // Formatta la data come stringa
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentDate);
        
        return dateString;
    }
	
	public static String getCurrentTimeAsString() {
        // Ottieni l'ora attuale
        Date currentTime = new Date();
        
        // Formatta l'ora come stringa
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String timeString = formatter.format(currentTime);
        
        return timeString;
    }
	
	
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	
	public static String getCurrentDateTimeAsString() {
		// Ottieni la data attuale
        Date currentDate = new Date();
        
        // Formatta la data come stringa
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = formatter.format(currentDate);
        
        return dateString;
	}
	

 }
