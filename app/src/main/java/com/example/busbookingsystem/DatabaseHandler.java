package com.example.busbookingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private Context context;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BusBookingSystem";
    private static final String REGISTRATION_TABLE= "UserRegistration";
    private static final String REGISTRATION_ID= "userID";
    private static final String REGISTRATION_NAME= "userName";
    private static final String REGISTRATION_CONTACT= "userContact";
    private static final String REGISTRATION_EMAIL= "userEmail";
    private static final String REGISTRATION_PASSWORD= "userPassword";
    private static final String BUS_DETAILS_TABLE= "BusDetails";
    private static final String BUS_DETAILS_ID= "busID";
    private static final String BUS_DETAILS_TRAVELS_NAME= "busTravelsName";
    private static final String BUS_DETAILS_TO= "busDestination";
    private static final String BUS_DETAILS_FROM= "busFrom";
    private static final String BUS_DETAILS_DATE= "busDate";
    private static final String BUS_DETAILS_TIME_START= "busTimeStart";
    private static final String BUS_DETAILS_TIME_END= "busTimeEnd";
    private static final String BUS_DETAILS_TYPE= "busType";
    private static final String BUS_DETAILS_RATE= "busRate";
    private static final String BOOKED_BUS_TABLE = "BookedBuses";
    private static final String BOOKED_ID = "bookedBusID";
    private static final String BOOKED_BUS_ID = "BusID";
    private static final String BOOKED_BUS_SEATNO = "bookedBusSeatNo";
    private static final String PASSENGER_DETAILS_TABLE="PassengersDetails";
    private static final String PASSENGER_DETAILS_ID="passengersDetailsId";
    private static final String PASSENGER_USER_ID="passengersUserId";
    private static final String PASSENGER_BUS_ID="passengersBusId";
    private static final String PASSENGER_NAME="passengersName";
    private static final String PASSENGER_CONTACT="passengersContact";

    private static final String PASSENGER_SEATNO="passengersSeatNo";

    //    private static final String PASSENGER_NAME="passengersName";
//    private static final String PASSENGER_EMAIL="passengersEmail";
//    private static final String PASSENGER_CONTACT="passengersContact";
    // private static final String PASSENGER_RATE="passengersRate";

    public static String getBusDetailsTable() {
        return BUS_DETAILS_TABLE;
    }
    public static String getBusDetailsFrom() {
        return BUS_DETAILS_FROM;
    }
    public static String getBusDetailsTo() {
        return BUS_DETAILS_TO;
    }
    public static String getBusDetailsDate() {
        return BUS_DETAILS_DATE;
    }
    public static String getBusDetailsTimeStart() {
        return BUS_DETAILS_TIME_START;
    }
    public static String getBusDetailsTimeEnd() {
        return BUS_DETAILS_TIME_END;
    }
    public static String getBusDetailsTravelsName() {
        return BUS_DETAILS_TRAVELS_NAME;
    }
    public static String getBusDetailsRate() {
        return BUS_DETAILS_RATE;
    }
    public static String getBusDetailsType() {
        return BUS_DETAILS_TYPE;
    }
    public static String getBusDetailsId() {
        return BUS_DETAILS_ID;
    }


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REGISTRATION_TABLE = "CREATE TABLE " + REGISTRATION_TABLE + "(" + REGISTRATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                REGISTRATION_NAME + " TEXT, " +
                REGISTRATION_CONTACT + " TEXT, " +
                REGISTRATION_EMAIL + " TEXT, " +
                REGISTRATION_PASSWORD + " TEXT " + ")";
        db.execSQL(CREATE_REGISTRATION_TABLE);

        String CREATE_BUS_DETAILS_TABLE = "CREATE TABLE " + BUS_DETAILS_TABLE + "(" + BUS_DETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                BUS_DETAILS_TRAVELS_NAME + " TEXT, " +
                BUS_DETAILS_FROM + " TEXT, " +
                BUS_DETAILS_TO + " TEXT, " +
                BUS_DETAILS_DATE + " TEXT, " +
                BUS_DETAILS_TIME_START + " TEXT ," +
                BUS_DETAILS_TIME_END + " TEXT ," +
                BUS_DETAILS_TYPE + " TEXT, "+
                BUS_DETAILS_RATE + " INTEGER"+")";
        db.execSQL(CREATE_BUS_DETAILS_TABLE);


        String BUS_RECORDS="INSERT INTO "+BUS_DETAILS_TABLE+ "("+ BUS_DETAILS_ID + "," +  BUS_DETAILS_TRAVELS_NAME+ "," +BUS_DETAILS_FROM+","+ BUS_DETAILS_TO+"," +BUS_DETAILS_DATE+","+ BUS_DETAILS_TIME_START +","+BUS_DETAILS_TIME_END+","+ BUS_DETAILS_TYPE+","+ BUS_DETAILS_RATE
                +") VALUES(1, 'Suguna Travels', 'Udupi', 'Chikkamagaluru', '20-12-2023','9:00','14:00','AC', 980), " +
                "(2, 'Ashoka Travels', 'Bengaluru', 'Mandya', '20-12-2023','7:00','10:00', 'AC', 1050), " +
                "(3, 'Kalpaka Travels', 'Mumbai', 'Bihar', '20-12-2023','5:00','15:00', 'NON AC', 750), " +
                "(4, 'Durgamba Travels', 'Pune', 'Agra', '20-12-2023','8:00','13:00', 'NON AC', 980), " +
                "(5, 'Reshma Travels', 'Chennai', 'Kolkata', '20-12-2023','4:00','16:00', 'AC', 1050), " +
                "(6, 'Suguna Travels', 'Kannur', 'Hosur', '20-12-2023','22:00','4:00', 'AC', 750), " +
                "(7, 'Ashoka Travels', 'Kochi', 'Ahmedabad', '20-12-2023','20:00','12:00', 'NON AC', 980), " +
                "(8, 'Kalpaka Travels', 'Mangalore', 'Suratkal', '20-12-2023','14:00','15:00', 'NON AC', 1050), " +
                "(9, 'Durgamba Travels', 'Delhi', 'Chandigarh', '20-12-2023','20:00','3:00', 'AC', 750), " +
                "(10, 'Reshma Travels', 'Hyderabad', 'Indore', '20-12-2023','9:00','21:00', 'AC', 980), " +
                "(11, 'Suguna Travels', 'Selam', 'Jaipur', '20-12-2023','6:00','23:00','NON AC', 1050), " +
                "(12, 'Ashoka Travels', 'Mysore', 'Patna', '20-12-2023','23:00','12:00', 'NON AC', 750), " +
                "(13, 'Kalpaka Travels', 'Ooty', 'Jalandar', '20-12-2023','20:00','4:00', 'AC', 980), " +
                "(14, 'Durgamba Travels', 'Goa', 'Trivandrum', '20-12-2023','21:00','8:00', 'AC', 1050), " +
                "(15, 'Reshma Travels', 'Coorg', 'Dehradun', '20-12-2023','4:00','23:00', 'NON AC', 750), " +
                "(16, 'Suguna Travels', 'Mumbai', 'Bihar', '20-12-2023','11:00','19:00', 'NON AC', 980), " +
                "(17, 'Ashoka Travels', 'Udupi', 'Bengaluru', '20-12-2023','21:00','6:00', 'AC', 1050), " +
                "(18, 'Kalpaka Travels', 'Udupi', 'Bengaluru', '20-12-2023','22:00','5:00', 'AC', 750), " +
                "(19, 'Durgamba Travels', 'Udupi', 'Bengaluru', '20-12-2023','7:00','12:00', 'NON AC', 980), " +
                "(20, 'Reshma Travels', 'Udupi', 'Bengaluru', '20-12-2023','20:00','6:00', 'NON AC', 1050)";
        db.execSQL(BUS_RECORDS);

        String CREATE_PASSENGER_DETAILS_TABLE = "CREATE TABLE "+ PASSENGER_DETAILS_TABLE + "(" +
                PASSENGER_DETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PASSENGER_BUS_ID + " INTEGER, " +
                PASSENGER_USER_ID + " INTEGER, " +
                PASSENGER_NAME + " TEXT, " +
                PASSENGER_CONTACT + " TEXT, " +
                PASSENGER_SEATNO + " TEXT, " +
                "FOREIGN KEY (" + PASSENGER_BUS_ID + ") REFERENCES " + BUS_DETAILS_TABLE + "(" + BUS_DETAILS_ID + "), " +
                "FOREIGN KEY (" + PASSENGER_USER_ID + ") REFERENCES " + REGISTRATION_TABLE + "(" + REGISTRATION_ID + "))";
        db.execSQL(CREATE_PASSENGER_DETAILS_TABLE);

        String PASSENGER_RECORDS="INSERT INTO "+PASSENGER_DETAILS_TABLE+ "("+ PASSENGER_BUS_ID+ "," +PASSENGER_USER_ID+","+ PASSENGER_NAME+","+PASSENGER_CONTACT+"," +PASSENGER_SEATNO
                +") VALUES(18, 1, 'Rashmi Kumar','9875465254', 6), " +
                "(18, 1, 'John Doe','5555555555', 8), " +
                "(16, 1, 'Reema','4559875462', 1), " +
                "(2, 1, 'Kumar','4444885577', 5)";
        db.execSQL(PASSENGER_RECORDS);

        String CREATE_BUS_BOOKED_TABLE = "CREATE TABLE " + BOOKED_BUS_TABLE + "(" +
                BOOKED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BOOKED_BUS_ID + " INTEGER, " +
                BOOKED_BUS_SEATNO + " TEXT,"+
                "FOREIGN KEY (" + BOOKED_BUS_ID + ") REFERENCES " + BUS_DETAILS_TABLE + "(" + BUS_DETAILS_ID + ")) " ;
        db.execSQL(CREATE_BUS_BOOKED_TABLE);

        String BOOKED_BUS_RECORDS="INSERT INTO "+BOOKED_BUS_TABLE+ "("+ BOOKED_BUS_ID+ "," +BOOKED_BUS_SEATNO
                +") VALUES(18, 1), " +
                "(18, 15), " +
                "(18, 21), " +
                "(18, 10)";
        db.execSQL(BOOKED_BUS_RECORDS);

        SharedPreferences sharedPreferences = context.getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", -1);
        editor.putString("username", "");
        editor.putString("useremail", "");
        editor.putString("contact", "");
        editor.apply();
    }

    public Boolean insertuserdata(BusBookingConstructor bookingCustructor)
    {
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(REGISTRATION_NAME,bookingCustructor.get_name());
        contentValues.put(REGISTRATION_CONTACT,bookingCustructor.get_contact());
        contentValues.put(REGISTRATION_EMAIL,bookingCustructor.get_email());
        contentValues.put(REGISTRATION_PASSWORD,bookingCustructor.get_password());
        long result=DB.insert(REGISTRATION_TABLE,"null",contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return  true;
        }
    }

    //Sanjana modified
//    public int insertuserdata(BusBookingConstructor bookingCustructor)
//    {
//        SQLiteDatabase DB= this.getWritableDatabase();
//        ContentValues contentValues= new ContentValues();
//        contentValues.put(REGISTRATION_NAME,bookingCustructor.get_name());
//        contentValues.put(REGISTRATION_CONTACT,bookingCustructor.get_contact());
//        contentValues.put(REGISTRATION_EMAIL,bookingCustructor.get_email());
//        contentValues.put(REGISTRATION_PASSWORD,bookingCustructor.get_password());
//        long result=DB.insert(REGISTRATION_TABLE,"null",contentValues);
//        if(result==-1)
//        {
//            return -1;
//        }
//        else
//        {
//            return  (int)result;
//        }
//    }

    public Boolean checkusername(String email){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+REGISTRATION_TABLE+" where "+REGISTRATION_EMAIL+"=?",new String[]{email});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }

    public BusBookingConstructor checkUsernamePassword(String username,String password)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+REGISTRATION_TABLE+" where "+REGISTRATION_EMAIL+"=? and "+REGISTRATION_PASSWORD+"=?",new String[]{username,password});
        int userId=0;
        String uname="",useremail="",contact="";
        BusBookingConstructor b;
        if(cursor.getCount()>0 && cursor.moveToFirst())
        { // Move to the first row in the cursor (if not already at the first row)

                // Fetch data from the cursor for the first row
                int userIdIndex = cursor.getColumnIndex(REGISTRATION_ID);
                int usernameIndex = cursor.getColumnIndex(REGISTRATION_NAME);
                int emailIndex = cursor.getColumnIndex(REGISTRATION_EMAIL);
                int contactIndex = cursor.getColumnIndex(REGISTRATION_CONTACT);
                if (userIdIndex != -1) {
                    userId = cursor.getInt(userIdIndex);

                }
                if (usernameIndex != -1) {
                    uname = cursor.getString(usernameIndex);
                }
                if (emailIndex != -1) {
                    useremail = cursor.getString(emailIndex);
                }
                if (contactIndex != -1) {
                    contact = cursor.getString(contactIndex);
                }
//                Toast.makeText(context.getApplicationContext(), "Login Successfull"+String.valueOf(userId) , Toast.LENGTH_SHORT).show();
                b=new BusBookingConstructor(userId,uname, contact, useremail);

                // Store the fetched data in SharedPreferences
                SharedPreferences sharedPreferences = context.getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("userId", userId);
                editor.putString("username", uname);
                editor.putString("useremail", useremail);
                editor.putString("contact", contact);
                editor.apply();
                // Close the cursor
                cursor.close();

                return b;
        }

        return  null;

//        if(cursor.getCount()>0)
//        {
//            return true ;
//        }
//        else
//        {
//            return  false;
//        }

    }

    public String[] getUserData(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] userData = new String[3];

        Cursor cursor = db.query(REGISTRATION_TABLE, new String[]{REGISTRATION_NAME, REGISTRATION_CONTACT,REGISTRATION_PASSWORD},
                REGISTRATION_ID + "=?", new String[]{String.valueOf(userId)}, null, null, null, null);
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(REGISTRATION_NAME);
            int contactIndex = cursor.getColumnIndex(REGISTRATION_CONTACT);
            int passwordIndex = cursor.getColumnIndex(REGISTRATION_PASSWORD);

            if (nameIndex >= 0) {
                userData[0] = cursor.getString(nameIndex);
            }

            if (contactIndex >= 0) {
                userData[1] = cursor.getString(contactIndex);
            }

            if (passwordIndex >= 0) {
                userData[2] = cursor.getString(passwordIndex);
            }
        }


        return userData;
    }

    //for user update page
    public Boolean updateUserdata(int userid,String name,String contact,String email,String password)
    {
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(REGISTRATION_NAME,name);
        contentValues.put(REGISTRATION_CONTACT,contact);
        contentValues.put(REGISTRATION_EMAIL,email);
        contentValues.put(REGISTRATION_PASSWORD,password);
        int result=DB.update(REGISTRATION_TABLE,contentValues,REGISTRATION_ID+" = ?",new String[]{String.valueOf(userid)} );
        DB.close();
        if(result==0)
        {
            return false;
        }
        else
        {
            return  true;
        }

    }

    //Fetching the booked seat number of a given busId
    public ArrayList<Integer> getBookedBuses(int busId){
        ArrayList<Integer> bookedBusSeatNoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + BOOKED_BUS_SEATNO + " FROM " + BOOKED_BUS_TABLE+" where "+BOOKED_BUS_ID+"=?",new String[]{String.valueOf(busId)});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int seatNoIndex = cursor.getColumnIndex(BOOKED_BUS_SEATNO);
                    if (seatNoIndex >= 0) {
                        int seatNo = Integer.parseInt(cursor.getString(seatNoIndex));
                        bookedBusSeatNoList.add(seatNo);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        else{
            return null;
        }
        db.close();
        return bookedBusSeatNoList;
    }

    public ArrayList<String> getSourcesFromDatabase() {
        ArrayList<String> sourceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + BUS_DETAILS_FROM + " FROM " + BUS_DETAILS_TABLE, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int columnIndex = cursor.getColumnIndex(BUS_DETAILS_FROM);
                    if (columnIndex >= 0) {
                        String source = cursor.getString(columnIndex);
                        sourceList.add(source);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return sourceList;
    }

    // Method to get unique destination values from the database
    public ArrayList<String> getDestinationsFromDatabase() {
        ArrayList<String> destinationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + BUS_DETAILS_TO + " FROM " + BUS_DETAILS_TABLE, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int columnIndex = cursor.getColumnIndex(BUS_DETAILS_TO);
                    if (columnIndex >= 0) {
                        String destination = cursor.getString(columnIndex);
                        destinationList.add(destination);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return destinationList;
    }

    public ArrayList<busdetails> getAvailableBuses(String source,String destination,String date) {
        ArrayList<busdetails> availableBuses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + BUS_DETAILS_TABLE + " WHERE " +
                BUS_DETAILS_FROM + " = ? AND " +
                BUS_DETAILS_TO + " = ? AND " +
                BUS_DETAILS_DATE + " = ?", new String[]{source, destination,date});
        int numberOfBuses = cursor.getCount();


        while (cursor.moveToNext()) {
            busdetails bus = new busdetails();

            int idIndex=cursor.getColumnIndex(DatabaseHandler.getBusDetailsId());
            int timeIndexStart = cursor.getColumnIndex(DatabaseHandler.getBusDetailsTimeStart());
            int timeIndexEnd = cursor.getColumnIndex(DatabaseHandler.getBusDetailsTimeEnd());
            int travelsNameIndex = cursor.getColumnIndex(DatabaseHandler.getBusDetailsTravelsName());
            int rateIndex = cursor.getColumnIndex(DatabaseHandler.getBusDetailsRate());
            int typeIndex = cursor.getColumnIndex(DatabaseHandler.getBusDetailsType());

            if (idIndex >= 0) {
                bus.setBusId(cursor.getInt(idIndex));
            }

            if (timeIndexStart >= 0) {
                bus.setTime(cursor.getString(timeIndexStart)+'-'+cursor.getString(timeIndexEnd));
            }

            if (travelsNameIndex >= 0) {
                bus.setTravelName(cursor.getString(travelsNameIndex));
            }

            if (rateIndex >= 0) {
                bus.setPrice("₹" + cursor.getInt(rateIndex));
            }
            String startTimeBefore=cursor.getString(timeIndexStart).split(":")[0];
            String endTimeBefore=cursor.getString(timeIndexEnd).split(":")[0];
            if(Integer.parseInt(startTimeBefore)>Integer.parseInt(endTimeBefore)){
                int duration = (24 - Integer.parseInt(startTimeBefore)) + Integer.parseInt(endTimeBefore);
                bus.setDuration(String.valueOf(duration)+" hours");
            }else{
                int duration = Integer.parseInt(endTimeBefore) - Integer.parseInt(startTimeBefore);
                bus.setDuration(String.valueOf(duration)+" hours");
            }

            if (typeIndex >= 0) {
                bus.setType(cursor.getString(typeIndex));
            }
            availableBuses.add(bus);
        }
        cursor.close();
        db.close();
        return availableBuses;
    }

    public void insertPassengerDetails(int busId,int userId,String name,String contact,int seatNo){

        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(PASSENGER_BUS_ID,busId);
        contentValues.put(PASSENGER_USER_ID,userId);
        contentValues.put(PASSENGER_NAME,name);
        contentValues.put(PASSENGER_CONTACT,contact);
        contentValues.put(PASSENGER_SEATNO,String.valueOf(seatNo));
        long result=DB.insert(PASSENGER_DETAILS_TABLE,"null",contentValues);

    }

     public void bookedBusDetails(int busId,int seatNo){

        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(BOOKED_BUS_ID,busId);
        contentValues.put(BOOKED_BUS_SEATNO,seatNo);
        long result=DB.insert(BOOKED_BUS_TABLE,"null",contentValues);

    }

    public ArrayList<PassengersDetailsClass> getPassengersDetails(int userId) {
        ArrayList<PassengersDetailsClass> passengersDetailsArraylist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " +
                "pd." + PASSENGER_NAME + ", " +
                "pd." + PASSENGER_SEATNO + ", " +
                "bd." + BUS_DETAILS_TRAVELS_NAME + ", " +
                "bd." + BUS_DETAILS_FROM + ", " +
                "bd." + BUS_DETAILS_TO + ", " +
                "bd." + BUS_DETAILS_DATE + ", " +
                "bd." + BUS_DETAILS_TIME_START + ", " +
                "bd." + BUS_DETAILS_TIME_END + ", " +
                "bd." + BUS_DETAILS_TYPE + ", " +
                "bd." + BUS_DETAILS_RATE +
                " FROM " + PASSENGER_DETAILS_TABLE + " as pd " +
                " INNER JOIN " + BUS_DETAILS_TABLE + " as bd ON pd." + PASSENGER_BUS_ID + " = bd." + BUS_DETAILS_ID +
                " WHERE pd." + PASSENGER_USER_ID + " = ?"+" ORDER BY pd." + PASSENGER_DETAILS_ID + " DESC", new String[]{String.valueOf(userId)});



//        Toast.makeText(context.getApplicationContext(), "Fetched details" +String.valueOf(cursor.getCount()) ,Toast.LENGTH_LONG).show();


        while (cursor.moveToNext()) {
            int duration;
            int nameIndex = cursor.getColumnIndex(PASSENGER_NAME);
            int seatNoIndex = cursor.getColumnIndex(PASSENGER_SEATNO);
            int travelsNameIndex = cursor.getColumnIndex(BUS_DETAILS_TRAVELS_NAME);
            int fromIndex = cursor.getColumnIndex(BUS_DETAILS_FROM);
            int toIndex = cursor.getColumnIndex(BUS_DETAILS_TO);
            int dateIndex = cursor.getColumnIndex(BUS_DETAILS_DATE);
            int timeStartIndex = cursor.getColumnIndex(BUS_DETAILS_TIME_START);
            int timeEndIndex = cursor.getColumnIndex(BUS_DETAILS_TIME_END);
            int typeIndex = cursor.getColumnIndex(BUS_DETAILS_TYPE);
            int rateIndex = cursor.getColumnIndex(BUS_DETAILS_RATE);


            String passengerName = cursor.getString(nameIndex);
            String seatNo = cursor.getString(seatNoIndex);
            String travelsName = cursor.getString(travelsNameIndex);
            String from = cursor.getString(fromIndex);
            String to = cursor.getString(toIndex);
            String date = cursor.getString(dateIndex);
            String timeStart = cursor.getString(timeStartIndex);
            String timeEnd = cursor.getString(timeEndIndex);
            String type = cursor.getString(typeIndex);
            int rate = cursor.getInt(rateIndex);

            String startTimeBefore=timeStart.split(":")[0];
            String endTimeBefore=timeEnd.split(":")[0];
            if(Integer.parseInt(startTimeBefore)>Integer.parseInt(endTimeBefore)){
                duration = (24 - Integer.parseInt(startTimeBefore)) + Integer.parseInt(endTimeBefore);
            }else{
                duration = Integer.parseInt(endTimeBefore) - Integer.parseInt(startTimeBefore);
            }
            String timeComplete = timeStart + '-' + timeEnd;
            PassengersDetailsClass passengerDetails = new PassengersDetailsClass(
                    passengerName, seatNo, travelsName, from, to, date, String.valueOf(duration),timeComplete,  type, rate
            );

            passengersDetailsArraylist.add(passengerDetails);
        }

//        Toast.makeText(context.getApplicationContext(), "Arraylist size " +String.valueOf(passengersDetailsArraylist.size()) ,Toast.LENGTH_LONG).show();


        cursor.close();
        db.close();
        return passengersDetailsArraylist;
    }

    public void deletePassengerHistory(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PASSENGER_DETAILS_TABLE, null, null);
        db.close();
    }

    public ArrayList<PassengersDetailsClass> getHistory15(int userId){
        ArrayList<PassengersDetailsClass> passengerHistory = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " +
                "pd." + PASSENGER_NAME + ", " +
                "pd." + PASSENGER_SEATNO + ", " +
                "bd." + BUS_DETAILS_TRAVELS_NAME + ", " +
                "bd." + BUS_DETAILS_FROM + ", " +
                "bd." + BUS_DETAILS_TO + ", " +
                "bd." + BUS_DETAILS_DATE + ", " +
                "bd." + BUS_DETAILS_TIME_START + ", " +
                "bd." + BUS_DETAILS_TIME_END + ", " +
                "bd." + BUS_DETAILS_TYPE + ", " +
                "bd." + BUS_DETAILS_RATE +
                " FROM " + PASSENGER_DETAILS_TABLE + " as pd " +
                " INNER JOIN " + BUS_DETAILS_TABLE + " as bd ON pd." + PASSENGER_BUS_ID + " = bd." + BUS_DETAILS_ID +
                " WHERE pd." + PASSENGER_USER_ID + " = ?" +
                " ORDER BY pd." + PASSENGER_DETAILS_ID + " DESC LIMIT 15", new String[]{String.valueOf(userId)});

        if(cursor.moveToFirst()){
            do {
                int duration;
                int nameIndex = cursor.getColumnIndex(PASSENGER_NAME);
                int seatNoIndex = cursor.getColumnIndex(PASSENGER_SEATNO);
                int travelsNameIndex = cursor.getColumnIndex(BUS_DETAILS_TRAVELS_NAME);
                int fromIndex = cursor.getColumnIndex(BUS_DETAILS_FROM);
                int toIndex = cursor.getColumnIndex(BUS_DETAILS_TO);
                int dateIndex = cursor.getColumnIndex(BUS_DETAILS_DATE);
                int timeStartIndex = cursor.getColumnIndex(BUS_DETAILS_TIME_START);
                int timeEndIndex = cursor.getColumnIndex(BUS_DETAILS_TIME_END);
                int typeIndex = cursor.getColumnIndex(BUS_DETAILS_TYPE);
                int rateIndex = cursor.getColumnIndex(BUS_DETAILS_RATE);

                String passengerName = cursor.getString(nameIndex);
                String seatNo = cursor.getString(seatNoIndex);
                String travelsName = cursor.getString(travelsNameIndex);
                String from = cursor.getString(fromIndex);
                String to = cursor.getString(toIndex);
                String date = cursor.getString(dateIndex);
                String timeStart = cursor.getString(timeStartIndex);
                String timeEnd = cursor.getString(timeEndIndex);
                String type = cursor.getString(typeIndex);
                int rate = cursor.getInt(rateIndex);

                String startTimeBefore=timeStart.split(":")[0];
                String endTimeBefore=timeEnd.split(":")[0];
                if(Integer.parseInt(startTimeBefore)>Integer.parseInt(endTimeBefore)){
                    duration = (24 - Integer.parseInt(startTimeBefore)) + Integer.parseInt(endTimeBefore);
                }else{
                    duration = Integer.parseInt(endTimeBefore) - Integer.parseInt(startTimeBefore);
                }
                String timeComplete = timeStart + '-' + timeEnd;
                PassengersDetailsClass pDetails = new PassengersDetailsClass(
                        passengerName, seatNo, travelsName, from, to, date, String.valueOf(duration),timeComplete,  type, rate
                );
                passengerHistory.add(pDetails);
            }while (cursor.moveToNext());
        }
        db.close();
        return passengerHistory;
    }

    public ArrayList<PassengersDetailsClass> getHistory30(int userId){
        ArrayList<PassengersDetailsClass> passengerHistory = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " +
                "pd." + PASSENGER_NAME + ", " +
                "pd." + PASSENGER_SEATNO + ", " +
                "bd." + BUS_DETAILS_TRAVELS_NAME + ", " +
                "bd." + BUS_DETAILS_FROM + ", " +
                "bd." + BUS_DETAILS_TO + ", " +
                "bd." + BUS_DETAILS_DATE + ", " +
                "bd." + BUS_DETAILS_TIME_START + ", " +
                "bd." + BUS_DETAILS_TIME_END + ", " +
                "bd." + BUS_DETAILS_TYPE + ", " +
                "bd." + BUS_DETAILS_RATE +
                " FROM " + PASSENGER_DETAILS_TABLE + " as pd " +
                " INNER JOIN " + BUS_DETAILS_TABLE + " as bd ON pd." + PASSENGER_BUS_ID + " = bd." + BUS_DETAILS_ID +
                " WHERE pd." + PASSENGER_USER_ID + " = ?" +
                " ORDER BY pd." + PASSENGER_DETAILS_ID + " DESC LIMIT 30", new String[]{String.valueOf(userId)});

        if(cursor.moveToFirst()){
            do {
                int duration;
                int nameIndex = cursor.getColumnIndex(PASSENGER_NAME);
                int seatNoIndex = cursor.getColumnIndex(PASSENGER_SEATNO);
                int travelsNameIndex = cursor.getColumnIndex(BUS_DETAILS_TRAVELS_NAME);
                int fromIndex = cursor.getColumnIndex(BUS_DETAILS_FROM);
                int toIndex = cursor.getColumnIndex(BUS_DETAILS_TO);
                int dateIndex = cursor.getColumnIndex(BUS_DETAILS_DATE);
                int timeStartIndex = cursor.getColumnIndex(BUS_DETAILS_TIME_START);
                int timeEndIndex = cursor.getColumnIndex(BUS_DETAILS_TIME_END);
                int typeIndex = cursor.getColumnIndex(BUS_DETAILS_TYPE);
                int rateIndex = cursor.getColumnIndex(BUS_DETAILS_RATE);

                String passengerName = cursor.getString(nameIndex);
                String seatNo = cursor.getString(seatNoIndex);
                String travelsName = cursor.getString(travelsNameIndex);
                String from = cursor.getString(fromIndex);
                String to = cursor.getString(toIndex);
                String date = cursor.getString(dateIndex);
                String timeStart = cursor.getString(timeStartIndex);
                String timeEnd = cursor.getString(timeEndIndex);
                String type = cursor.getString(typeIndex);
                int rate = cursor.getInt(rateIndex);

                String startTimeBefore=timeStart.split(":")[0];
                String endTimeBefore=timeEnd.split(":")[0];
                if(Integer.parseInt(startTimeBefore)>Integer.parseInt(endTimeBefore)){
                    duration = (24 - Integer.parseInt(startTimeBefore)) + Integer.parseInt(endTimeBefore);
                }else{
                    duration = Integer.parseInt(endTimeBefore) - Integer.parseInt(startTimeBefore);
                }
                String timeComplete = timeStart + '-' + timeEnd;
                PassengersDetailsClass pDetails = new PassengersDetailsClass(
                        passengerName, seatNo, travelsName, from, to, date, String.valueOf(duration),timeComplete,  type, rate
                );
                passengerHistory.add(pDetails);
            }while (cursor.moveToNext());
        }
        db.close();
        return passengerHistory;
    }




//    public Boolean insertUserData(String name,String phone,String email,String password)
//    {
//        SQLiteDatabase DB= this.getWritableDatabase();
//        ContentValues contentValues= new ContentValues();
//        contentValues.put("name",name);
//        contentValues.put("phone",phone);
//        contentValues.put("email",email);
//        contentValues.put("password",password);
//        long result=DB.insert(REGISTRATION_TABLE,"null",contentValues);
//        if(result==-1)
//        {
//            return false;
//        }
//        else
//        {
//            return  true;
//        }
//    }
//
//    //checking username and password for login page
//
//
//    //for user update page
//    public Boolean updateUserdata(String email,String name,String contact,String password)
//    {
//        SQLiteDatabase DB= this.getWritableDatabase();
//        ContentValues contentValues= new ContentValues();
//        contentValues.put("name",name);
//        contentValues.put("phone",contact);
//        contentValues.put("password",password);
//        long result=DB.update(REGISTRATION_TABLE,contentValues,REGISTRATION_EMAIL+"=?",new String[]{email});
//        if(result==-1)
//        {
//            return false;
//        }
//        else
//        {
//            return  true;
//        }
//
//    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
