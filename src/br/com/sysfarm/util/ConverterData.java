/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sysfarm.util;

import com.toedter.calendar.JCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author eltho
 */
public class ConverterData {
    public static String CALENDAR_PARA_SIMPLE_FORMAT(Calendar calendar){
        Date date= new Date(calendar.getTimeInMillis());
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }
}
