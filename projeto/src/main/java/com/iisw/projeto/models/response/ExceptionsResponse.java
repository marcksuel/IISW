package com.iisw.projeto.models.response;

public class ExceptionsResponse {
	public class ErrosCode{
		public static String USERNAME = "0001 - INVALID USERNAME";
		public static String PASSWORD = "0002 - INVALID PASSWORD";
		public static String EMAIL = "0003 - INVALID EMAIL";
		public static String TOKEN = "0004 - TOKEN EXPIRED";
		public static String DELETE = "0005 - DELETE USER FAIL";
		public static String POST = "0006 - INVALID POST CONTENT";
		public static String POST_PERMISSION = "0007 - USER DON'T HAVE PERMISSION IN THIS POST";

	}
	
	
	
	public class NotFound{
		public static String USER ="1001 -  User ID: ";
		public static String POST ="1002 -  Post ID: ";
		public static String COMMENT ="1003 -  Comment ID: ";

	}
	
	public class NotAvaible{
		public static String POST = "2001 - Post service not avaible";
		public static String COMMENT = "2002 - Comment service not avaible";

	}
	
}
