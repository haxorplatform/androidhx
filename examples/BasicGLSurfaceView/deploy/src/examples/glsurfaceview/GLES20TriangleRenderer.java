package examples.glsurfaceview;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class GLES20TriangleRenderer extends haxe.lang.HxObject implements android.opengl.GLSurfaceView.Renderer
{
	static 
	{
		examples.glsurfaceview.GLES20TriangleRenderer.TAG = "TRACE";
		examples.glsurfaceview.GLES20TriangleRenderer.FLOAT_SIZE_BYTES = 4;
		examples.glsurfaceview.GLES20TriangleRenderer.TRIANGLE_VERTICES_DATA_STRIDE_BYTES = ( 5 * examples.glsurfaceview.GLES20TriangleRenderer.FLOAT_SIZE_BYTES );
		examples.glsurfaceview.GLES20TriangleRenderer.TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;
		examples.glsurfaceview.GLES20TriangleRenderer.TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;
		examples.glsurfaceview.GLES20TriangleRenderer.mVertexShader = "\r\n\tuniform mat4 uMVPMatrix;\r\n\tattribute vec4 aPosition;\r\n\tattribute vec2 aTextureCoord;\r\n\tvarying vec2 vTextureCoord;\r\n\tvoid main() \r\n\t{\r\n\t  gl_Position = uMVPMatrix * aPosition;\r\n\t  vTextureCoord = aTextureCoord;\r\n\t}\r\n\t";
		examples.glsurfaceview.GLES20TriangleRenderer.mFragmentShader = "\r\n\tprecision mediump float;\r\n\tvarying vec2 vTextureCoord;\r\n\tuniform sampler2D sTexture;\r\n\tvoid main() \r\n\t{\r\n\t  gl_FragColor = texture2D(sTexture, vTextureCoord);\r\n\t}\r\n\t";
	}
	public    GLES20TriangleRenderer(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    GLES20TriangleRenderer(android.content.Context p_context)
	{
		examples.glsurfaceview.GLES20TriangleRenderer.__hx_ctor_examples_glsurfaceview_GLES20TriangleRenderer(this, p_context);
	}
	
	
	public static   void __hx_ctor_examples_glsurfaceview_GLES20TriangleRenderer(examples.glsurfaceview.GLES20TriangleRenderer __temp_me3259, android.content.Context p_context)
	{
		__temp_me3259.mContext = p_context;
		__temp_me3259.mMVPMatrix = new float[((int) (16) )];
		__temp_me3259.mProjMatrix = new float[((int) (16) )];
		__temp_me3259.mMMatrix = new float[((int) (16) )];
		__temp_me3259.mVMatrix = new float[((int) (16) )];
		
		float[] tvd =
		{ 
            // X, Y, Z, U, V
           -1.0f, -0.5f, 0f, -0.5f, 0.0f,
            1.0f, -0.5f, 0f, 1.5f, -0.0f,
			0.0f,  1.11803399f, 0f, 0.5f,  1.61803399f
		};
		mTriangleVerticesData = tvd;		
		;
		__temp_me3259.mTriangleVertices = java.nio.ByteBuffer.allocateDirect(((int) (( examples.glsurfaceview.GLES20TriangleRenderer.mTriangleVerticesData.length * examples.glsurfaceview.GLES20TriangleRenderer.FLOAT_SIZE_BYTES )) )).order(((java.nio.ByteOrder) (java.nio.ByteOrder.nativeOrder()) )).asFloatBuffer();
		__temp_me3259.mTriangleVertices.put(((float[]) (examples.glsurfaceview.GLES20TriangleRenderer.mTriangleVerticesData) )).position(((int) (0) ));
	}
	
	
	public static  java.lang.String TAG;
	
	public static  int FLOAT_SIZE_BYTES;
	
	public static  int TRIANGLE_VERTICES_DATA_STRIDE_BYTES;
	
	public static  int TRIANGLE_VERTICES_DATA_POS_OFFSET;
	
	public static  int TRIANGLE_VERTICES_DATA_UV_OFFSET;
	
	public static  float[] mTriangleVerticesData;
	
	public static  java.lang.String mVertexShader;
	
	public static  java.lang.String mFragmentShader;
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new examples.glsurfaceview.GLES20TriangleRenderer(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new examples.glsurfaceview.GLES20TriangleRenderer(((android.content.Context) (arr.__get(0)) ));
	}
	
	
	public  java.nio.FloatBuffer mTriangleVertices;
	
	public  int mProgram;
	
	public  int mTextureID;
	
	public  int muMVPMatrixHandle;
	
	public  int maPositionHandle;
	
	public  int maTextureHandle;
	
	public  android.content.Context mContext;
	
	public  float[] mMVPMatrix;
	
	public  float[] mProjMatrix;
	
	public  float[] mMMatrix;
	
	public  float[] mVMatrix;
	
	public   void onDrawFrame(javax.microedition.khronos.opengles.GL10 p_gl)
	{
		android.opengl.GLES20.glClearColor(((float) (0.0) ), ((float) (0.0) ), ((float) (1.0) ), ((float) (1.0) ));
		android.opengl.GLES20.glClear(((int) (( android.opengl.GLES20.GL_DEPTH_BUFFER_BIT | android.opengl.GLES20.GL_COLOR_BUFFER_BIT )) ));
		android.opengl.GLES20.glUseProgram(((int) (this.mProgram) ));
		this.checkGlError("glUseProgram");
		android.opengl.GLES20.glActiveTexture(((int) (android.opengl.GLES20.GL_TEXTURE0) ));
		android.opengl.GLES20.glBindTexture(((int) (android.opengl.GLES20.GL_TEXTURE_2D) ), ((int) (this.mTextureID) ));
		this.mTriangleVertices.position(((int) (examples.glsurfaceview.GLES20TriangleRenderer.TRIANGLE_VERTICES_DATA_POS_OFFSET) ));
		android.opengl.GLES20.glVertexAttribPointer(((int) (this.maPositionHandle) ), ((int) (3) ), ((int) (android.opengl.GLES20.GL_FLOAT) ), haxe.lang.Runtime.toBool(false), ((int) (examples.glsurfaceview.GLES20TriangleRenderer.TRIANGLE_VERTICES_DATA_STRIDE_BYTES) ), ((java.nio.Buffer) (this.mTriangleVertices) ));
		this.checkGlError("glVertexAttribPointer maPosition");
		this.mTriangleVertices.position(((int) (examples.glsurfaceview.GLES20TriangleRenderer.TRIANGLE_VERTICES_DATA_UV_OFFSET) ));
		android.opengl.GLES20.glEnableVertexAttribArray(((int) (this.maPositionHandle) ));
		this.checkGlError("glEnableVertexAttribArray maPositionHandle");
		android.opengl.GLES20.glVertexAttribPointer(((int) (this.maTextureHandle) ), ((int) (2) ), ((int) (android.opengl.GLES20.GL_FLOAT) ), haxe.lang.Runtime.toBool(false), ((int) (examples.glsurfaceview.GLES20TriangleRenderer.TRIANGLE_VERTICES_DATA_STRIDE_BYTES) ), ((java.nio.Buffer) (this.mTriangleVertices) ));
		this.checkGlError("glVertexAttribPointer maTextureHandle");
		android.opengl.GLES20.glEnableVertexAttribArray(((int) (this.maTextureHandle) ));
		this.checkGlError("glEnableVertexAttribArray maTextureHandle");
		double time = ((double) (( ((int) (android.os.SystemClock.uptimeMillis()) ) % 4000 )) );
		float angle = ((float) (( 0.090 * ((int) (java.lang.Math.floor(time)) ) )) );
		android.opengl.Matrix.setRotateM(((float[]) (this.mMMatrix) ), ((int) (0) ), ((float) (angle) ), ((float) (0) ), ((float) (0) ), ((float) (1.0) ));
		android.opengl.Matrix.multiplyMM(((float[]) (this.mMVPMatrix) ), ((int) (0) ), ((float[]) (this.mVMatrix) ), ((int) (0) ), ((float[]) (this.mMMatrix) ), ((int) (0) ));
		android.opengl.Matrix.multiplyMM(((float[]) (this.mMVPMatrix) ), ((int) (0) ), ((float[]) (this.mProjMatrix) ), ((int) (0) ), ((float[]) (this.mMVPMatrix) ), ((int) (0) ));
		android.opengl.GLES20.glUniformMatrix4fv(((int) (this.muMVPMatrixHandle) ), ((int) (1) ), haxe.lang.Runtime.toBool(false), ((float[]) (this.mMVPMatrix) ), ((int) (0) ));
		android.opengl.GLES20.glDrawArrays(((int) (android.opengl.GLES20.GL_TRIANGLES) ), ((int) (0) ), ((int) (3) ));
		this.checkGlError("glDrawArrays");
	}
	
	
	public   void onSurfaceChanged(javax.microedition.khronos.opengles.GL10 p_gl, int width, int height)
	{
		android.opengl.GLES20.glViewport(((int) (0) ), ((int) (0) ), ((int) (width) ), ((int) (height) ));
		android.util.Log.v(haxe.lang.Runtime.toString(examples.glsurfaceview.GLES20TriangleRenderer.TAG), haxe.lang.Runtime.toString(( ( width + " " ) + height )));
		float ratio = ((float) (( ((double) (width) ) / height )) );
		android.opengl.Matrix.frustumM(((float[]) (this.mProjMatrix) ), ((int) (0) ), ((float) ( - (ratio) ) ), ((float) (ratio) ), ((float) (-1) ), ((float) (1) ), ((float) (3) ), ((float) (7) ));
	}
	
	
	public   void onSurfaceCreated(javax.microedition.khronos.opengles.GL10 p_gl, javax.microedition.khronos.egl.EGLConfig p_config)
	{
		this.mProgram = this.createProgram(examples.glsurfaceview.GLES20TriangleRenderer.mVertexShader, examples.glsurfaceview.GLES20TriangleRenderer.mFragmentShader);
		if (( this.mProgram == 0 )) 
		{
			return ;
		}
		
		this.maPositionHandle = android.opengl.GLES20.glGetAttribLocation(((int) (this.mProgram) ), haxe.lang.Runtime.toString("aPosition"));
		this.checkGlError("glGetAttribLocation aPosition");
		if (( this.maPositionHandle == -1 )) 
		{
			throw new android.util.AndroidRuntimeException(haxe.lang.Runtime.toString("Could not get attrib location for aPosition"));
		}
		
		this.maTextureHandle = android.opengl.GLES20.glGetAttribLocation(((int) (this.mProgram) ), haxe.lang.Runtime.toString("aTextureCoord"));
		this.checkGlError("glGetAttribLocation aTextureCoord");
		if (( this.maTextureHandle == -1 )) 
		{
			throw new android.util.AndroidRuntimeException(haxe.lang.Runtime.toString("Could not get attrib location for aTextureCoord"));
		}
		
		this.muMVPMatrixHandle = android.opengl.GLES20.glGetUniformLocation(((int) (this.mProgram) ), haxe.lang.Runtime.toString("uMVPMatrix"));
		this.checkGlError("glGetUniformLocation uMVPMatrix");
		if (( this.muMVPMatrixHandle == -1 )) 
		{
			throw new android.util.AndroidRuntimeException(haxe.lang.Runtime.toString("Could not get attrib location for uMVPMatrix"));
		}
		
		int[] textures = new int[((int) (1) )];
		android.opengl.GLES20.glGenTextures(((int) (1) ), ((int[]) (textures) ), ((int) (0) ));
		this.mTextureID = textures[0];
		android.opengl.GLES20.glBindTexture(((int) (android.opengl.GLES20.GL_TEXTURE_2D) ), ((int) (this.mTextureID) ));
		android.opengl.GLES20.glTexParameterf(((int) (android.opengl.GLES20.GL_TEXTURE_2D) ), ((int) (android.opengl.GLES20.GL_TEXTURE_MIN_FILTER) ), ((float) (android.opengl.GLES20.GL_NEAREST) ));
		android.opengl.GLES20.glTexParameterf(((int) (android.opengl.GLES20.GL_TEXTURE_2D) ), ((int) (android.opengl.GLES20.GL_TEXTURE_MAG_FILTER) ), ((float) (android.opengl.GLES20.GL_LINEAR) ));
		android.opengl.GLES20.glTexParameteri(((int) (android.opengl.GLES20.GL_TEXTURE_2D) ), ((int) (android.opengl.GLES20.GL_TEXTURE_WRAP_S) ), ((int) (android.opengl.GLES20.GL_REPEAT) ));
		android.opengl.GLES20.glTexParameteri(((int) (android.opengl.GLES20.GL_TEXTURE_2D) ), ((int) (android.opengl.GLES20.GL_TEXTURE_WRAP_T) ), ((int) (android.opengl.GLES20.GL_REPEAT) ));
		try 
		{
			java.io.InputStream istr = this.mContext.getResources().openRawResource(((int) (R.raw.robot) ));
			android.graphics.Bitmap bitmap = null;
			bitmap = android.graphics.BitmapFactory.decodeStream(((java.io.InputStream) (istr) ));
			android.opengl.GLUtils.texImage2D(((int) (android.opengl.GLES20.GL_TEXTURE_2D) ), ((int) (0) ), ((android.graphics.Bitmap) (bitmap) ), ((int) (0) ));
			bitmap.recycle();
			try 
			{
				istr.close();
			}
			catch (java.io.IOException err)
			{
				{
				}
				
			}
			
			
		}
		catch (java.lang.Exception err1)
		{
			java.lang.String s = ( err1.getMessage() + "\n" );
			{
				int _g1 = 0;
				int _g = err1.getStackTrace().length;
				while (( _g1 < _g ))
				{
					int i = _g1++;
					s += ( err1.getStackTrace()[i].toString() + "\n" );
				}
				
			}
			
			android.util.Log.e(haxe.lang.Runtime.toString(examples.glsurfaceview.GLES20TriangleRenderer.TAG), haxe.lang.Runtime.toString(s));
		}
		
		
		android.opengl.Matrix.setLookAtM(((float[]) (this.mVMatrix) ), ((int) (0) ), ((float) (0) ), ((float) (0) ), ((float) (-5) ), ((float) (0) ), ((float) (0) ), ((float) (0) ), ((float) (0) ), ((float) (1.0) ), ((float) (0.0) ));
	}
	
	
	public   int loadShader(int shaderType, java.lang.String source)
	{
		int shader = android.opengl.GLES20.glCreateShader(((int) (shaderType) ));
		if (( shader != 0 )) 
		{
			android.opengl.GLES20.glShaderSource(((int) (shader) ), haxe.lang.Runtime.toString(source));
			android.opengl.GLES20.glCompileShader(((int) (shader) ));
			int[] compiled = new int[((int) (1) )];
			android.opengl.GLES20.glGetShaderiv(((int) (shader) ), ((int) (android.opengl.GLES20.GL_COMPILE_STATUS) ), ((int[]) (compiled) ), ((int) (0) ));
			if (( compiled[0] == 0 )) 
			{
				android.util.Log.e(haxe.lang.Runtime.toString(examples.glsurfaceview.GLES20TriangleRenderer.TAG), haxe.lang.Runtime.toString(( ( "Could not compile shader " + shaderType ) + ":" )));
				android.util.Log.e(haxe.lang.Runtime.toString(examples.glsurfaceview.GLES20TriangleRenderer.TAG), haxe.lang.Runtime.toString(android.opengl.GLES20.glGetShaderInfoLog(((int) (shader) ))));
				android.opengl.GLES20.glDeleteShader(((int) (shader) ));
				shader = 0;
			}
			
		}
		
		return shader;
	}
	
	
	public   int createProgram(java.lang.String vertexSource, java.lang.String fragmentSource)
	{
		int vertexShader = this.loadShader(android.opengl.GLES20.GL_VERTEX_SHADER, vertexSource);
		if (( vertexShader == 0 )) 
		{
			return 0;
		}
		
		int pixelShader = this.loadShader(android.opengl.GLES20.GL_FRAGMENT_SHADER, fragmentSource);
		if (( pixelShader == 0 )) 
		{
			return 0;
		}
		
		int program = android.opengl.GLES20.glCreateProgram();
		if (( program != 0 )) 
		{
			android.opengl.GLES20.glAttachShader(((int) (program) ), ((int) (vertexShader) ));
			this.checkGlError("glAttachShader");
			android.opengl.GLES20.glAttachShader(((int) (program) ), ((int) (pixelShader) ));
			this.checkGlError("glAttachShader");
			android.opengl.GLES20.glLinkProgram(((int) (program) ));
			int[] linkStatus = new int[((int) (1) )];
			android.opengl.GLES20.glGetProgramiv(((int) (program) ), ((int) (android.opengl.GLES20.GL_LINK_STATUS) ), ((int[]) (linkStatus) ), ((int) (0) ));
			if (( linkStatus[0] != android.opengl.GLES20.GL_TRUE )) 
			{
				android.util.Log.e(haxe.lang.Runtime.toString(examples.glsurfaceview.GLES20TriangleRenderer.TAG), haxe.lang.Runtime.toString("Could not link program: "));
				android.util.Log.e(haxe.lang.Runtime.toString(examples.glsurfaceview.GLES20TriangleRenderer.TAG), haxe.lang.Runtime.toString(android.opengl.GLES20.glGetProgramInfoLog(((int) (program) ))));
				android.opengl.GLES20.glDeleteProgram(((int) (program) ));
				program = 0;
			}
			
		}
		
		return program;
	}
	
	
	public   void checkGlError(java.lang.String op)
	{
		int error = 0;
		while (( (error = android.opengl.GLES20.glGetError()) != android.opengl.GLES20.GL_NO_ERROR ))
		{
			android.util.Log.e(haxe.lang.Runtime.toString(examples.glsurfaceview.GLES20TriangleRenderer.TAG), haxe.lang.Runtime.toString(( ( op + ": glError " ) + error )));
			throw new android.util.AndroidRuntimeException(haxe.lang.Runtime.toString(( ( op + ": glError " ) + error )));
		}
		
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef3294 = true;
			switch (field.hashCode())
			{
				case 80642543:
				{
					if (field.equals("maTextureHandle")) 
					{
						__temp_executeDef3294 = false;
						this.maTextureHandle = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1951936169:
				{
					if (field.equals("mProgram")) 
					{
						__temp_executeDef3294 = false;
						this.mProgram = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1699231589:
				{
					if (field.equals("maPositionHandle")) 
					{
						__temp_executeDef3294 = false;
						this.maPositionHandle = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1041517961:
				{
					if (field.equals("mTextureID")) 
					{
						__temp_executeDef3294 = false;
						this.mTextureID = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 989222920:
				{
					if (field.equals("muMVPMatrixHandle")) 
					{
						__temp_executeDef3294 = false;
						this.muMVPMatrixHandle = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef3294) 
			{
				return super.__hx_setField_f(field, value, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_setField(java.lang.String field, java.lang.Object value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef3295 = true;
			switch (field.hashCode())
			{
				case -1993701718:
				{
					if (field.equals("mVMatrix")) 
					{
						__temp_executeDef3295 = false;
						this.mVMatrix = ((float[]) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1251075922:
				{
					if (field.equals("mTriangleVertices")) 
					{
						__temp_executeDef3295 = false;
						this.mTriangleVertices = ((java.nio.FloatBuffer) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1391300255:
				{
					if (field.equals("mMMatrix")) 
					{
						__temp_executeDef3295 = false;
						this.mMMatrix = ((float[]) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1951936169:
				{
					if (field.equals("mProgram")) 
					{
						__temp_executeDef3295 = false;
						this.mProgram = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 1035363787:
				{
					if (field.equals("mProjMatrix")) 
					{
						__temp_executeDef3295 = false;
						this.mProjMatrix = ((float[]) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1041517961:
				{
					if (field.equals("mTextureID")) 
					{
						__temp_executeDef3295 = false;
						this.mTextureID = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case -1838896997:
				{
					if (field.equals("mMVPMatrix")) 
					{
						__temp_executeDef3295 = false;
						this.mMVPMatrix = ((float[]) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 989222920:
				{
					if (field.equals("muMVPMatrixHandle")) 
					{
						__temp_executeDef3295 = false;
						this.muMVPMatrixHandle = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case -691017598:
				{
					if (field.equals("mContext")) 
					{
						__temp_executeDef3295 = false;
						this.mContext = ((android.content.Context) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1699231589:
				{
					if (field.equals("maPositionHandle")) 
					{
						__temp_executeDef3295 = false;
						this.maPositionHandle = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 80642543:
				{
					if (field.equals("maTextureHandle")) 
					{
						__temp_executeDef3295 = false;
						this.maTextureHandle = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef3295) 
			{
				return super.__hx_setField(field, value, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_getField(java.lang.String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
	{
		{
			boolean __temp_executeDef3296 = true;
			switch (field.hashCode())
			{
				case 22124027:
				{
					if (field.equals("checkGlError")) 
					{
						__temp_executeDef3296 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("checkGlError"))) );
					}
					
					break;
				}
				
				
				case -1251075922:
				{
					if (field.equals("mTriangleVertices")) 
					{
						__temp_executeDef3296 = false;
						return this.mTriangleVertices;
					}
					
					break;
				}
				
				
				case 1937129960:
				{
					if (field.equals("createProgram")) 
					{
						__temp_executeDef3296 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("createProgram"))) );
					}
					
					break;
				}
				
				
				case -1951936169:
				{
					if (field.equals("mProgram")) 
					{
						__temp_executeDef3296 = false;
						return this.mProgram;
					}
					
					break;
				}
				
				
				case -227384213:
				{
					if (field.equals("loadShader")) 
					{
						__temp_executeDef3296 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("loadShader"))) );
					}
					
					break;
				}
				
				
				case 1041517961:
				{
					if (field.equals("mTextureID")) 
					{
						__temp_executeDef3296 = false;
						return this.mTextureID;
					}
					
					break;
				}
				
				
				case -365648582:
				{
					if (field.equals("onSurfaceCreated")) 
					{
						__temp_executeDef3296 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("onSurfaceCreated"))) );
					}
					
					break;
				}
				
				
				case 989222920:
				{
					if (field.equals("muMVPMatrixHandle")) 
					{
						__temp_executeDef3296 = false;
						return this.muMVPMatrixHandle;
					}
					
					break;
				}
				
				
				case -655259386:
				{
					if (field.equals("onSurfaceChanged")) 
					{
						__temp_executeDef3296 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("onSurfaceChanged"))) );
					}
					
					break;
				}
				
				
				case 1699231589:
				{
					if (field.equals("maPositionHandle")) 
					{
						__temp_executeDef3296 = false;
						return this.maPositionHandle;
					}
					
					break;
				}
				
				
				case 92828522:
				{
					if (field.equals("onDrawFrame")) 
					{
						__temp_executeDef3296 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("onDrawFrame"))) );
					}
					
					break;
				}
				
				
				case 80642543:
				{
					if (field.equals("maTextureHandle")) 
					{
						__temp_executeDef3296 = false;
						return this.maTextureHandle;
					}
					
					break;
				}
				
				
				case -1993701718:
				{
					if (field.equals("mVMatrix")) 
					{
						__temp_executeDef3296 = false;
						return this.mVMatrix;
					}
					
					break;
				}
				
				
				case -691017598:
				{
					if (field.equals("mContext")) 
					{
						__temp_executeDef3296 = false;
						return this.mContext;
					}
					
					break;
				}
				
				
				case -1391300255:
				{
					if (field.equals("mMMatrix")) 
					{
						__temp_executeDef3296 = false;
						return this.mMMatrix;
					}
					
					break;
				}
				
				
				case -1838896997:
				{
					if (field.equals("mMVPMatrix")) 
					{
						__temp_executeDef3296 = false;
						return this.mMVPMatrix;
					}
					
					break;
				}
				
				
				case 1035363787:
				{
					if (field.equals("mProjMatrix")) 
					{
						__temp_executeDef3296 = false;
						return this.mProjMatrix;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef3296) 
			{
				return super.__hx_getField(field, throwErrors, isCheck, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   double __hx_getField_f(java.lang.String field, boolean throwErrors, boolean handleProperties)
	{
		{
			boolean __temp_executeDef3297 = true;
			switch (field.hashCode())
			{
				case 80642543:
				{
					if (field.equals("maTextureHandle")) 
					{
						__temp_executeDef3297 = false;
						return ((double) (this.maTextureHandle) );
					}
					
					break;
				}
				
				
				case -1951936169:
				{
					if (field.equals("mProgram")) 
					{
						__temp_executeDef3297 = false;
						return ((double) (this.mProgram) );
					}
					
					break;
				}
				
				
				case 1699231589:
				{
					if (field.equals("maPositionHandle")) 
					{
						__temp_executeDef3297 = false;
						return ((double) (this.maPositionHandle) );
					}
					
					break;
				}
				
				
				case 1041517961:
				{
					if (field.equals("mTextureID")) 
					{
						__temp_executeDef3297 = false;
						return ((double) (this.mTextureID) );
					}
					
					break;
				}
				
				
				case 989222920:
				{
					if (field.equals("muMVPMatrixHandle")) 
					{
						__temp_executeDef3297 = false;
						return ((double) (this.muMVPMatrixHandle) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef3297) 
			{
				return super.__hx_getField_f(field, throwErrors, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_invokeField(java.lang.String field, haxe.root.Array dynargs)
	{
		{
			boolean __temp_executeDef3298 = true;
			switch (field.hashCode())
			{
				case 22124027:
				{
					if (field.equals("checkGlError")) 
					{
						__temp_executeDef3298 = false;
						this.checkGlError(haxe.lang.Runtime.toString(dynargs.__get(0)));
					}
					
					break;
				}
				
				
				case 92828522:
				{
					if (field.equals("onDrawFrame")) 
					{
						__temp_executeDef3298 = false;
						this.onDrawFrame(((javax.microedition.khronos.opengles.GL10) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 1937129960:
				{
					if (field.equals("createProgram")) 
					{
						__temp_executeDef3298 = false;
						return this.createProgram(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)));
					}
					
					break;
				}
				
				
				case -655259386:
				{
					if (field.equals("onSurfaceChanged")) 
					{
						__temp_executeDef3298 = false;
						this.onSurfaceChanged(((javax.microedition.khronos.opengles.GL10) (dynargs.__get(0)) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(2))) ));
					}
					
					break;
				}
				
				
				case -227384213:
				{
					if (field.equals("loadShader")) 
					{
						__temp_executeDef3298 = false;
						return this.loadShader(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), haxe.lang.Runtime.toString(dynargs.__get(1)));
					}
					
					break;
				}
				
				
				case -365648582:
				{
					if (field.equals("onSurfaceCreated")) 
					{
						__temp_executeDef3298 = false;
						this.onSurfaceCreated(((javax.microedition.khronos.opengles.GL10) (dynargs.__get(0)) ), ((javax.microedition.khronos.egl.EGLConfig) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef3298) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("mVMatrix");
		baseArr.push("mMMatrix");
		baseArr.push("mProjMatrix");
		baseArr.push("mMVPMatrix");
		baseArr.push("mContext");
		baseArr.push("maTextureHandle");
		baseArr.push("maPositionHandle");
		baseArr.push("muMVPMatrixHandle");
		baseArr.push("mTextureID");
		baseArr.push("mProgram");
		baseArr.push("mTriangleVertices");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


