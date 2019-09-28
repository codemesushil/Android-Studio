package shop.books.bookiesh;

//this class holds all the (final) strings which will not change........................................
public class Constants {

    private static final String URL = "http://13.235.86.115";

    private static final String ROOT_URL = URL+"/android/v1/";

    public static final String URL_REGISTER = ROOT_URL +"registerUser.php";

    public static final String URL_LOGIN = ROOT_URL +"userLogin.php";

    public static final String UPLOAD_URL_ROOT = URL +"/uploadimage/uploads/";

    public static final String UPLOAD_URL = UPLOAD_URL_ROOT +"upload.php";

    public static final String ADD_TO_CART = UPLOAD_URL_ROOT +"addtomycart.php";

    public static final String REMOVE_FROM_CART = UPLOAD_URL_ROOT +"rmfromcart.php";

    public static final String JSON_URL = UPLOAD_URL_ROOT +"database.json" ;

    public static final String ADD_ADDRESS = UPLOAD_URL_ROOT +"saveaddress.php";

    public static final String ADDRESS_JSON = UPLOAD_URL_ROOT +"address_data.json" ;

    public static final String ADD_TO_WISHLIST = UPLOAD_URL_ROOT + "wishlist.php";

   // public static final String WISHLIST_JSON = UPLOAD_URL_ROOT + "wishlist_database.json";

    public static final String UPDATE_CART = UPLOAD_URL_ROOT +"createcart_table.php";

    public static final String CART_JSON = UPLOAD_URL_ROOT +"cart_data.json";

    public static final String UPDATE_AVAILABLE = UPLOAD_URL_ROOT + "updateavailable.php";
}
