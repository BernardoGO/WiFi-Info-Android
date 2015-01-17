package com.rane.wifi.info;

import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView view;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        view = (TextView)findViewById(R.id.textView1);
        String simNum = "";
        
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        
        
        simNum += "IP Address: " + getIpAddr() + "\n";
        simNum += "MAC Address: " + wifiInfo.getMacAddress() + "\n";
        simNum += "SSID: " + wifiInfo.getSSID() + "\n";
        simNum += "BSSID: " + wifiInfo.getBSSID() + "\n";
        simNum += "SSID Status: " + wifiInfo.getHiddenSSID() + "\n";
        simNum += "Network ID: " + wifiInfo.getNetworkId() + "\n";
        simNum += "Link Speed: " + wifiInfo.getLinkSpeed() + " Mbps"+ "\n";
        simNum += "Signal: " + wifiInfo.getRssi() + "\n";
        
        
        
       
        
        view.setText(simNum);
    }

    public String getIpAddr() {
    	   WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
    	   WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    	   int ip = wifiInfo.getIpAddress();

    	   String ipString = String.format(
    	   "%d.%d.%d.%d",
    	   (ip & 0xff),
    	   (ip >> 8 & 0xff),
    	   (ip >> 16 & 0xff),
    	   (ip >> 24 & 0xff));

    	   return ipString;
    	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
private void onMarketLaunch(String url) {
		
		Intent donate = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(
				"market://details?id=%s", url)));
		startActivity(donate);
	}
	
private void onDevLaunch(String url) {
		
		Intent donate = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(
				"market://search?q=pub:%s", url)));
		startActivity(donate);
	}
	
	public void share()
	{
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=");
		startActivity(Intent.createChooser(intent, "Share with"));
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_rate:    onMarketLaunch("");
	                            break;
	                            
            
	        case R.id.menu_moreapps:	onDevLaunch("");
	        break; 
            
	        case R.id.menu_share:	share();
	        break;

	    }
	    return true;
	}
}
