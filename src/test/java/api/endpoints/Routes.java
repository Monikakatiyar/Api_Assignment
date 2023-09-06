package api.endpoints;

 

public class Routes {

	public static String base_url= "https://petstore.swagger.io/v2";
	
	// USER Module
	
	public static String create_user= base_url+"/user";
	public static String get_user= base_url+"/user/{username}";
	public static String update_user= base_url+"/user/{username}";
	public static String delete_user= base_url+"/user/{username}";

	// Store module
	
	public static String place_order= base_url+"/store/order";
	public static String get_order_details= base_url+"/store/order/{orderId}";
	public static String delete_order= base_url+"/store/order/{orderId}";
	public static String get_inventory= base_url+"/store/inventory";


	// Pet module

	public static String add_pet= base_url+"/pet";
	public static String update_pet= base_url+"/pet";
	public static String get_pet= base_url+"/pet/{petId}";
	public static String delete_pet= base_url+"/pet/{petId}";
	
}
