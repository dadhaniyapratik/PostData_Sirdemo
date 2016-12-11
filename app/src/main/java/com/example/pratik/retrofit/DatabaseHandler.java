package com.example.pratik.retrofit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pratik.retrofit.Pojo.Address;
import com.example.pratik.retrofit.Pojo.Company;
import com.example.pratik.retrofit.Pojo.Geo;
import com.example.pratik.retrofit.Pojo.UserDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pratik on 29-Nov-16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_STREET = "street";
    private static final String KEY_SUITE = "suite";
    private static final String KEY_CITY = "city";
    private static final String KEY_ZIPCODE = "zipcode";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LNG = "lng";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_WEBSITE = "website";
    private static final String KEY_COMPANYNAME = "companyname";
    private static final String KEY_CATCHPHRASE = "catchPhrase";
    private static final String KEY_BS = "bs";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER,"
                + KEY_NAME + " TEXT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_STREET + " TEXT,"
                + KEY_SUITE + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_ZIPCODE + " TEXT,"
                + KEY_LAT + " TEXT,"
                + KEY_LNG + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_WEBSITE + " TEXT,"
                + KEY_COMPANYNAME + " TEXT,"
                + KEY_CATCHPHRASE + " TEXT,"
                + KEY_BS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addContact(List<UserDetail> userDetail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        for (int i = 0; i <userDetail.size() ; i++) {
            values.put(KEY_ID, userDetail.get(i).getId()); // Contact Name
            values.put(KEY_NAME, userDetail.get(i).getName()); // Contact Phone
            values.put(KEY_USERNAME, userDetail.get(i).getUsername()); // Contact Phone
            values.put(KEY_EMAIL, userDetail.get(i).getEmail()); // Contact Phone
            values.put(KEY_STREET, userDetail.get(i).getAddress().getStreet()); // Contact Phone
            values.put(KEY_SUITE, userDetail.get(i).getAddress().getSuite()); // Contact Phone
            values.put(KEY_CITY, userDetail.get(i).getAddress().getCity()); // Contact Phone
            values.put(KEY_ZIPCODE, userDetail.get(i).getAddress().getZipcode()); // Contact Phone
            values.put(KEY_LAT, userDetail.get(i).getAddress().getGeo().getLat()); // Contact Phone
            values.put(KEY_LNG, userDetail.get(i).getAddress().getGeo().getLng()); // Contact Phone
            values.put(KEY_PHONE, userDetail.get(i).getPhone()); // Contact Phone
            values.put(KEY_WEBSITE, userDetail.get(i).getWebsite()); // Contact Phone
            values.put(KEY_COMPANYNAME, userDetail.get(i).getCompany().getName()); // Contact Phone
            values.put(KEY_CATCHPHRASE, userDetail.get(i).getCompany().getCatchPhrase()); // Contact Phone
            values.put(KEY_BS, userDetail.get(i).getCompany().getBs()); // Contact Phone




            // Inserting Row
            db.insert(TABLE_CONTACTS, null, values);
        }

        db.close(); // Closing database connection
    }



    // Getting All Contacts
    public List<UserDetail> getAllContacts() {
        List<UserDetail> userDetails = new ArrayList<UserDetail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        UserDetail userDetail = new UserDetail();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                userDetail.setId(Integer.parseInt(cursor.getString(0)));
                userDetail.setName(cursor.getString(1));
                userDetail.setUsername(cursor.getString(2));
                userDetail.setEmail(cursor.getString(3));


                Address address = new Address();
                address.setStreet(cursor.getString(4));
                address.setSuite(cursor.getString(5));
                address.setCity(cursor.getString(6));
                address.setZipcode(cursor.getString(7));

                Geo geo = new Geo();
                geo.setLat(cursor.getString(8));
                geo.setLng(cursor.getString(9));

                address.setGeo(geo);

                userDetail.setPhone(cursor.getString(10));
                userDetail.setWebsite(cursor.getString(11));

                Company company = new Company();
                company.setName(cursor.getString(12));
                company.setCatchPhrase(cursor.getString(13));
                company.setBs(cursor.getString(14));
                userDetail.setAddress(address);
                userDetail.setCompany(company);
                // Adding contact to list



//                ArrayList<Geo> geos = new ArrayList<Geo>();
//                geos.add(geo);
//
//                ArrayList<Address> addresses = new ArrayList<Address>();
//
//                addresses.add(address);
//                address.setGeo(geo);
//                userDetail.setAddress(address);
//
//                List<Company> companies = new ArrayList<Company>();
//                companies.add(company);
//
//                userDetail.setAddress(address);
//                userDetail.setCompany(company);
                userDetails.add(userDetail);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userDetails;
    }
}
