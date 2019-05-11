package com.sinqupa.chofer.utility;
import android.Manifest;

public class Utility {
    public static String userID;
    //----------------- Variable para los Servicios de Localizacion -----------------//
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    public static final int REQUEST_PERMISSIONS_REQUEST_CODE = 101;
    public static final String[] PERMISSIONS = { Manifest.permission.INTERNET,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.RECEIVE_BOOT_COMPLETED};
    public static final String PERMISSION_TEXT="Permisos Requeridos para SinQupa";
    public static final String OK = "Ok";
}
