package haxe.root;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Std
{
	public    Std()
	{
		{
		}
		
	}
	
	
	public static   boolean is(java.lang.Object v, java.lang.Object t)
	{
		if (( v == null )) 
		{
			return haxe.lang.Runtime.eq(t, java.lang.Object.class);
		}
		
		if (( t == null )) 
		{
			return false;
		}
		
		java.lang.Class clt = ((java.lang.Class) (t) );
		if (( ((java.lang.Object) (clt) ) == ((java.lang.Object) (null) ) )) 
		{
			return false;
		}
		
		java.lang.String name = clt.getName();
		{
			java.lang.String __temp_svar25 = (name);
			int __temp_hash27 = __temp_svar25.hashCode();
			switch (__temp_hash27)
			{
				case 761287205:case -1325958191:
				{
					if (( (( ( __temp_hash27 == 761287205 ) && __temp_svar25.equals("java.lang.Double") )) || __temp_svar25.equals("double") )) 
					{
						return haxe.lang.Runtime.isDouble(v);
					}
					
					break;
				}
				
				
				case 1063877011:
				{
					if (__temp_svar25.equals("java.lang.Object")) 
					{
						return true;
					}
					
					break;
				}
				
				
				case -2056817302:case 104431:
				{
					if (( (( ( __temp_hash27 == -2056817302 ) && __temp_svar25.equals("java.lang.Integer") )) || __temp_svar25.equals("int") )) 
					{
						return haxe.lang.Runtime.isInt(v);
					}
					
					break;
				}
				
				
				case 344809556:case 64711720:
				{
					if (( (( ( __temp_hash27 == 344809556 ) && __temp_svar25.equals("java.lang.Boolean") )) || __temp_svar25.equals("boolean") )) 
					{
						return v instanceof java.lang.Boolean;
					}
					
					break;
				}
				
				
			}
			
		}
		
		java.lang.Class clv = v.getClass();
		return clt.isAssignableFrom(((java.lang.Class) (clv) ));
	}
	
	
	public static   java.lang.String string(java.lang.Object s)
	{
		return ( haxe.lang.Runtime.toString(s) + "" );
	}
	
	
}


