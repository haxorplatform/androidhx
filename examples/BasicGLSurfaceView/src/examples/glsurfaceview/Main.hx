package examples.glsurfaceview;
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
	
	private var mView : BasicGLSurfaceView;
	
	@:overload 
	override function onCreate(savedInstanceState:Bundle):Void 
	{
		super.onCreate(savedInstanceState);	
		mView = new BasicGLSurfaceView(getApplication());
        setContentView(mView);		
	}
	
	@:overload
	override function onPause():Void 
	{
		super.onPause();
		mView.onPause();
	}
	
	@:overload
	override function onResume():Void 
	{
		super.onResume();
		mView.onResume();
	}
	
}