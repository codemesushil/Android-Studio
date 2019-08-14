package shop.books.bookiesh;

//this class holds all the (final) strings which will not change........................................
public class Constants {

    private static final String URL = "http://192.168.137.1";

    private static final String ROOT_URL = URL+"/android/v1/";

    public static final String URL_REGISTER = ROOT_URL+"registerUser.php";

    public static final String URL_LOGIN = ROOT_URL+"userLogin.php";

    public static final String UPLOAD_URL_ROOT = URL+"/uploadimage/";

    public static final String UPLOAD_URL = UPLOAD_URL_ROOT+"upload.php";

    public static final String ADD_TO_CART = UPLOAD_URL_ROOT+"addtomycart.php";

    public static final String REMOVE_FROM_CART = UPLOAD_URL_ROOT+"rmfromcart.php";

    public static final String JSON_URL = UPLOAD_URL_ROOT+"database.json" ;

    public static final String ADD_DAYS = UPLOAD_URL_ROOT+"adddays.php";

    public static final String ADD_ADDRESS = UPLOAD_URL_ROOT+"saveaddress.php";

    public static final String ADDRESS_JSON = UPLOAD_URL_ROOT+"address_data.json" ;

    public static final String ADD_TO_WISHLIST = UPLOAD_URL_ROOT + "wishlist.php";

    public static final String WISHLIST_JSON = UPLOAD_URL + "wishlist_database.json";
}
