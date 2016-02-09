package com.imtanan.crud_recipe;

/**
 * Created by Mani on 08/12/2015.
 */
public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://iaraja.mohammedsohaib.com/CRUD_Recipe/Create.php";
    public static final String URL_GET_ALL = "http://iaraja.mohammedsohaib.com/CRU_Recipe/ReadAll.php";
    public static final String URL_GET_REC = "http://iaraja.mohammedsohaib.com/CRUD_Recipe/Read.php?id=";
    public static final String URL_UPDATE_REC = "http://iaraja.mohammedsohaib.com/CRUD_Recipe/Update.php";
    public static final String URL_DELETE_REC = "http://iaraja.mohammedsohaib.com/CRUD_Recipe/Delete.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_REC_ID = "id";
    public static final String KEY_REC_NAME = "recipe_name";
    public static final String KEY_REC_ORIGIN = "origin";
    public static final String KEY_REC_INGRE = "ingredients";
    public static final String KEY_REC_CM = "cookmethod";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "recipe_name";
    public static final String TAG_ORIGIN = "origin";
    public static final String TAG_INGRE = "ingredients";
    public static final String TAG_CM = "cookmethod";

    //employee id to pass with intent
    public static final String REC_ID = "rec_id";
}
