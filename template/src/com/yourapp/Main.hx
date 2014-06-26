package com.yourapp;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


/**
 * ...
 * @author Eduardo Pons - eduardo@thelaborat.org
 */

class Main extends Activity
{
	
	static function main() { }
	
	override function onCreate(savedInstanceState:Bundle):Void 
	{
		super.onCreate(savedInstanceState);
		setContentView(untyped __java__("R.layout.main"));
		
		
		
		Log.v("TRACE", "Android Template> Hello World");
	}
}