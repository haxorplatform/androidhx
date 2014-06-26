package examples.glsurfaceview;
import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * ...
 * @author Eduardo Pons - eduardo@thelaborat.org
 */
class BasicGLSurfaceView extends GLSurfaceView
{

	public function new(p_context : Context):Void
	{
		super(p_context);
		setEGLContextClientVersion(2);
        setRenderer(new GLES20TriangleRenderer(p_context));
	}
	
}