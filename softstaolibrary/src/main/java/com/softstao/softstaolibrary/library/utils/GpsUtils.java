package com.softstao.softstaolibrary.library.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by xuhon on 2016/7/27.
 */
public class GpsUtils {
    private static GpsUtils _GpsUtils;
    private LocationManager locationManager = null;
    // Flag for GPS status
    boolean isGPSEnabled = false;

    // Flag for network status
    boolean isNetworkEnabled = false;

    // Flag for GPS status
    boolean canGetLocation = false;
    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    private static final int EXPIRE_TIME = 1000 * 60 * 1;//这个可是根据软件的需要调整
    private Location curLocation = null;
    private Geocoder geocoder = null;
    private Context context;
    private String TAG = "tag";
    private String curAddr="";
    private getAddressName listener;
    private LocationListeners locationListeners;
    Location location; // Location
    private GpsUtils(Context context) {
        this.context = context;
        initLocationInfo();
    }

    public synchronized static GpsUtils getInstance(Context context) {
        if (_GpsUtils == null) {
            _GpsUtils = new GpsUtils(context);
        }
        return _GpsUtils;
    }

    private void initLocationInfo() {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        isGPSEnabled = locationManager
//                .isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//        // Getting network status
//        isNetworkEnabled = locationManager
//                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//        if (!isGPSEnabled && !isNetworkEnabled) {
//            // No network provider is enabled
//        } else {
//            this.canGetLocation = true;
//            if (isNetworkEnabled) {
//                locationManager.requestLocationUpdates(
//                        LocationManager.NETWORK_PROVIDER,
//                        MIN_TIME_BW_UPDATES,
//                        MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
//                Log.d("Network", "Network");
//                if (locationManager != null) {
//                    location = locationManager
//                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//                }
//            }
//            // If GPS enabled, get latitude/longitude using GPS Services
//            if (isGPSEnabled) {
//                if (location == null) {
//                    locationManager.requestLocationUpdates(
//                            LocationManager.GPS_PROVIDER,
//                            MIN_TIME_BW_UPDATES,
//                            MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
//                    Log.d("GPS Enabled", "GPS Enabled");
//                    if (locationManager != null) {
//                        location = locationManager
//                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    }
//                }
//            }
//        }
        geocoder = new Geocoder(context, Locale.getDefault());
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
                0, locationListener);
        // Define a listener that responds to location updates
        // Register the listener with the Location Manager to receive location updates
    }

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            //makeUseOfNewLocation(location);
            if(isBetterLocation(location,curLocation)){
                if(locationListeners!=null){
                    locationListeners.returnLocation(location);
                }
                curLocation=location;
//                getAddrByhandler(curLocation);
            }
            Log.e(getClass().getName(), "location lat:"+location.getLatitude()+",lon:"+location.getLongitude());
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(getClass().getName(), "onStatusChanged");
        }

        public void onProviderEnabled(String provider) {
            Log.e(getClass().getName(), "onProviderEnabled");
        }

        public void onProviderDisabled(String provider) {
            Log.e(getClass().getName(), "onProviderDisabled");
        }
    };

    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > EXPIRE_TIME;
        boolean isSignificantlyOlder = timeDelta < -EXPIRE_TIME;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    private void getAddrByhandler(Location location){
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(),1);
        } catch (IOException ioException) {
            Log.e(TAG, "服务不可用！", ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            Log.e(TAG, "无效的经纬度" + ". " +"Latitude = " + location.getLatitude() +", Longitude = " +
                    location.getLongitude(), illegalArgumentException);
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            Log.e(TAG, "没有找到相关地址!");
        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();
            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            curAddr="";
            for(int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
                curAddr=curAddr+address.getAddressLine(i);
            }
            if(!TextUtils.isEmpty(address.getFeatureName())
                    && !addressFragments.isEmpty()
                    && !addressFragments.get(addressFragments.size()-1).equals(address.getFeatureName())){
                addressFragments.add(address.getFeatureName());
                curAddr=curAddr+address.getFeatureName();
            }
            if(listener!=null){
                listener.getAddress(curAddr);
            }
            Log.i(TAG, "详情地址已经找到,地址:"+curAddr);
        }
    }


    public interface getAddressName{
        void getAddress(String name);
    }

    public interface LocationListeners{
        void returnLocation(Location location);
    }

    public getAddressName getListener() {
        return listener;
    }

    public void setListener(getAddressName listener) {
        this.listener = listener;
    }

    private String getTimeStrByLong(long aTime){
        Date currentTime = new Date(aTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public void removeListener(){
        locationManager.removeUpdates(locationListener);
    }

    public LocationListeners getLocationListeners() {
        return locationListeners;
    }

    public void setLocationListeners(LocationListeners locationListeners) {
        this.locationListeners = locationListeners;
    }
}
