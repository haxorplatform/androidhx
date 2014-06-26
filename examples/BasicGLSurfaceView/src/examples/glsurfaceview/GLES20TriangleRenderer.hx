package examples.glsurfaceview;
import android.content.Context;
import android.opengl.GLES20;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.opengl.GLUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.NativeArray;
import java.javax.microedition.khronos.opengles.GL10;
import java.javax.microedition.khronos.egl.EGLConfig;
import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.lang.RuntimeException;
import java.lang.Exception;
import android.os.SystemClock;

/**
 * ...
 * @author Eduardo Pons - eduardo@thelaborat.org
 */
class GLES20TriangleRenderer implements GLSurfaceView_Renderer
{
	static private var TAG : String = "TRACE";
	
	static private var FLOAT_SIZE_BYTES 					: Int = 4;
    static private var TRIANGLE_VERTICES_DATA_STRIDE_BYTES  : Int = 5 * FLOAT_SIZE_BYTES;
    static private var TRIANGLE_VERTICES_DATA_POS_OFFSET  	: Int = 0;
    static private var TRIANGLE_VERTICES_DATA_UV_OFFSET 	: Int = 3;
	
    static private var mTriangleVerticesData : NativeArray<Single>;

    private var mTriangleVertices : FloatBuffer;
	
	static private var mVertexShader : String = 
	'
	uniform mat4 uMVPMatrix;
	attribute vec4 aPosition;
	attribute vec2 aTextureCoord;
	varying vec2 vTextureCoord;
	void main() 
	{
	  gl_Position = uMVPMatrix * aPosition;
	  vTextureCoord = aTextureCoord;
	}
	';
		
	static private var mFragmentShader:String = 
	'
	precision mediump float;
	varying vec2 vTextureCoord;
	uniform sampler2D sTexture;
	void main() 
	{
	  gl_FragColor = texture2D(sTexture, vTextureCoord);
	}
	';
	
    private var mProgram : Int;
    
	private var mTextureID : Int;
    
	private var muMVPMatrixHandle : Int;
    
	private var maPositionHandle : Int;

    private var maTextureHandle : Int;
	
	private var mContext : Context;
	
	private var mMVPMatrix 	: NativeArray<Single>;
	private var mProjMatrix : NativeArray<Single>;
	private var mMMatrix 	: NativeArray<Single>;
	private var mVMatrix 	: NativeArray<Single>;
	
	
	
	public function new(p_context : Context) 
	{
		mContext    = p_context;
		mMVPMatrix 	= new NativeArray(16);
		mProjMatrix = new NativeArray(16);
		mMMatrix 	= new NativeArray(16);
		mVMatrix 	= new NativeArray(16);
		
		untyped __java__('
		float[] tvd =
		{ 
            // X, Y, Z, U, V
           -1.0f, -0.5f, 0f, -0.5f, 0.0f,
            1.0f, -0.5f, 0f, 1.5f, -0.0f,
			0.0f,  1.11803399f, 0f, 0.5f,  1.61803399f
		};
		mTriangleVerticesData = tvd;		
		');
		
		mTriangleVertices = ByteBuffer.allocateDirect(mTriangleVerticesData.length * FLOAT_SIZE_BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer();
		mTriangleVertices.put(mTriangleVerticesData).position(0);
		
	}
	
	public function onDrawFrame(p_gl : GL10):Void
	{
		// Ignore the passed-in GL10 interface, and use the GLES20
        // class's static methods instead.
        GLES20.glClearColor(0.0, 0.0, 1.0, 1.0);
        GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glUseProgram(mProgram);
        checkGlError("glUseProgram");

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureID);

        mTriangleVertices.position(TRIANGLE_VERTICES_DATA_POS_OFFSET);
        GLES20.glVertexAttribPointer(maPositionHandle, 3, GLES20.GL_FLOAT, false,TRIANGLE_VERTICES_DATA_STRIDE_BYTES, mTriangleVertices);
        
		checkGlError("glVertexAttribPointer maPosition");
		
        mTriangleVertices.position(TRIANGLE_VERTICES_DATA_UV_OFFSET);
        GLES20.glEnableVertexAttribArray(maPositionHandle);
		
        checkGlError("glEnableVertexAttribArray maPositionHandle");
		
        GLES20.glVertexAttribPointer(maTextureHandle, 2, GLES20.GL_FLOAT, false, TRIANGLE_VERTICES_DATA_STRIDE_BYTES, mTriangleVertices);
		
        checkGlError("glVertexAttribPointer maTextureHandle");
		
        GLES20.glEnableVertexAttribArray(maTextureHandle);
        
		checkGlError("glEnableVertexAttribArray maTextureHandle");

        var time : Float  = (cast SystemClock.uptimeMillis()) % 4000;
        var angle : Single = 0.090 * Math.floor(time);
        Matrix.setRotateM(mMMatrix, 0, angle, 0, 0, 1.0);
        Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, mMMatrix, 0);
        Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);

        GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, mMVPMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
        checkGlError("glDrawArrays");
	}
	
	public  function onSurfaceChanged(p_gl:GL10,width : Int,height : Int)
	{
		// Ignore the passed-in GL10 interface, and use the GLES20
        // class's static methods instead.
        GLES20.glViewport(0, 0, width, height);
		Log.v(TAG, width + " " + height);
        var ratio : Single = cast (width / height);
        Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
	}
	
	public  function onSurfaceCreated(p_gl:GL10,p_config:EGLConfig)
	{
		// Ignore the passed-in GL10 interface, and use the GLES20
        // class's static methods instead.
        mProgram = createProgram(mVertexShader, mFragmentShader);
        if (mProgram == 0)
		{
            return;
        }
        
		maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");		
        checkGlError("glGetAttribLocation aPosition");
		
        if (maPositionHandle == -1)
		{
            throw new AndroidRuntimeException("Could not get attrib location for aPosition");
        }
        
		maTextureHandle = GLES20.glGetAttribLocation(mProgram, "aTextureCoord");
        checkGlError("glGetAttribLocation aTextureCoord");
        
		if (maTextureHandle == -1) 
		{
            throw new AndroidRuntimeException("Could not get attrib location for aTextureCoord");
        }

        muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        checkGlError("glGetUniformLocation uMVPMatrix");
        
		if (muMVPMatrixHandle == -1) 
		{
            throw new AndroidRuntimeException("Could not get attrib location for uMVPMatrix");
        }

        /*
         * Create our texture. This has to be done each time the
         * surface is created.
         */

        var textures : NativeArray<Int> = new NativeArray<Int>(1);
        GLES20.glGenTextures(1, textures, 0);

        mTextureID = textures[0];
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureID);

        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_NEAREST);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR);

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,
                GLES20.GL_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,
                GLES20.GL_REPEAT);

		try
		{				
			var istr : java.io.InputStream = mContext.getResources().openRawResource(untyped __java__ ("R.raw.robot"));
			var bitmap : Bitmap; 
		
			bitmap = BitmapFactory.decodeStream(istr);
			
			GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
			bitmap.recycle();
			
			try {  istr.close(); } 
			catch (err : java.io.IOException) 
			{
				
				//var s : String = err.getMessage()+"\n";
				//for (i in 0...err.getStackTrace().length) s += err.getStackTrace()[i].toString() + "\n";			
				//Log.e(TAG,s);
			}
			
		}
		catch (err : Exception)
		{			
			var s : String = err.getMessage()+"\n";
			for (i in 0...err.getStackTrace().length) s += err.getStackTrace()[i].toString() + "\n";			
			Log.e(TAG,s);
		}
		
		

        Matrix.setLookAtM(mVMatrix, 0, 0, 0, -5, 0, 0, 0, 0, 1.0, 0.0);
	}
	
	private function loadShader(shaderType : Int, source : String) : Int
	{
        var shader : Int = GLES20.glCreateShader(shaderType);
        if (shader != 0) 
		{
            GLES20.glShaderSource(shader, source);
            GLES20.glCompileShader(shader);
			
            var compiled : NativeArray<Int> = new NativeArray<Int>(1);
            
			GLES20.glGetShaderiv(shader, android.opengl.GLES20.GL_COMPILE_STATUS, compiled, 0);			
			
            if (compiled[0] == 0) 
			{
                Log.e(TAG, "Could not compile shader " + shaderType + ":");
                Log.e(TAG, GLES20.glGetShaderInfoLog(shader));
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }
	
	private function createProgram(vertexSource : String, fragmentSource : String):Int
	{
        var vertexShader :Int = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        if (vertexShader == 0) 
		{
            return 0;
        }

        var pixelShader : Int = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
        if (pixelShader == 0) 
		{
            return 0;
        }

        var program :Int = GLES20.glCreateProgram();
        if (program != 0) 
		{
            GLES20.glAttachShader(program, vertexShader);
            checkGlError("glAttachShader");
            GLES20.glAttachShader(program, pixelShader);
            checkGlError("glAttachShader");
            GLES20.glLinkProgram(program);
            var linkStatus : NativeArray<Int> = new NativeArray<Int>(1);			
			GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] != GLES20.GL_TRUE)
			{
                Log.e(TAG, "Could not link program: ");
                Log.e(TAG, GLES20.glGetProgramInfoLog(program));
                GLES20.glDeleteProgram(program);
                program = 0;
            }
        }
        return program;
    }
	
	private function checkGlError(op:String):Void
	{
        var error : Int=0;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) 
		{
            Log.e(TAG, op + ": glError " + error);
			throw new AndroidRuntimeException(op + ": glError " + error);            
        }
    }
	
}