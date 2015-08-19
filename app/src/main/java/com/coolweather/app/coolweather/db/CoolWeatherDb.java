package com.coolweather.app.coolweather.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.coolweather.model.City;
import com.coolweather.app.coolweather.model.County;
import com.coolweather.app.coolweather.model.Province;
import com.coolweather.app.coolweather.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baosisi on 2015/8/19.
 * 封装对数据库的操作
 */
public class CoolWeatherDb {
    //数据库版本
    private static int VERSION = 1;
    private static CoolWeatherDb coolWeatherDb;
    private static String DBNAME = "CWdb";
    SQLiteDatabase db;

    //私有化构造方法
    private CoolWeatherDb() {
        OpenDataHelper helper = new OpenDataHelper(MyApplication.getContext(), DBNAME, null, VERSION);
        db = helper.getWritableDatabase();
    }

    //获取实例
    public synchronized static CoolWeatherDb getInstance() {
        if (coolWeatherDb == null) {
            coolWeatherDb = new CoolWeatherDb();
        }
        return coolWeatherDb;
    }
    /* 保存province数据*/
    public void saveProvince(Province province) {
        ContentValues values = new ContentValues();
        values.put("provincename", province.getProvinceName());
        values.put("provincecode", province.getProvinceCode());
        db.insert("Province", null, values);
    }
    public List<Province> queryAllProvince(){
        List<Province> list=new ArrayList<Province>();
        Cursor cursor=db.query("province",null,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            Province province=new Province();
            province.setId(cursor.getInt(cursor.getColumnIndex("Id")));
            province.setProvinceCode(cursor.getString(cursor.getColumnIndex("provincecode")));
            province.setProvinceName(cursor.getString(cursor.getColumnIndex("provincename")));
            list.add(province);
        }
        return list;
    }
    public void  saveCity(City city){
        ContentValues values=new ContentValues();
        values.put("cityName",city.getCityName());
        values.put("cityCode",city.getCityCode());
        values.put("provinceId",city.getProvinceId());
        db.insert("city",null,values);
    }
    public List<City> queryAllCity(){
        List<City> list=new ArrayList<>();
        Cursor cursor=db.query("city",null,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            City city=new City();
            city.setId(cursor.getInt(cursor.getColumnIndex("Id")));
            city.setCityCode(cursor.getString(cursor.getColumnIndex("cityCode")));
            city.setCityName(cursor.getString(cursor.getColumnIndex("cityName")));
            city.setProvinceId(cursor.getInt(cursor.getColumnIndex("provinceId")));
            list.add(city);
        }
        return list;
    }
    public void saveCounty(County county){
        ContentValues values=new ContentValues();
        values.put("cityId",county.getCityId());
        values.put("provinceCode",county.getCountyCode());
        values.put("countyName",county.getCountyName());
        db.insert("county",null,values);
    }
    public List<County> queryAllCounty(){
        List<County> list=new ArrayList<>();
        Cursor cursor=db.query("county",null,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            County county=new County();
            county.setId(cursor.getInt(cursor.getColumnIndex("Id")));
            county.setCityId(cursor.getInt(cursor.getColumnIndex("CityId")));
            county.setCountyCode(cursor.getString(cursor.getColumnIndex("countyCode")));
            county.setCountyName(cursor.getString(cursor.getColumnIndex("countyName")));
            list.add(county);
        }
        return list;
    }
}
