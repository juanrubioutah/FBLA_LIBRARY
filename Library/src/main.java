import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class main {
	public static Date today;
	public static Calendar calendar = new GregorianCalendar(2017,8,10);
	public static void main(String args[]){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Date date2 = (calendar.add(Calendar.WEEK_OF_MONTH, 2).getTime());
		System.out.println(sdf.format(date));
		System.out.println(date.before(date));
	}
}
