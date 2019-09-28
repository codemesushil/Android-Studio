package shop.books.bookiesh;

// this class holds attributes of single books.....................................................
public class book {

    private String bkid;
    private String bkname ;
    private String bkoriginalprice;
    private String bkauthor;
    private String bkrent;
    private String bkurl;
    private String days;
    private String uid;
    private String bid;
    private String available;


    public book() {

    }

    public String getavailable() {return available;}

    public String getbid() {return bid;}

    public String getuid()   {return uid;}

    public String getdays() { return days;}

    public String getBkid() { return bkid;}

    public String getbkname() { return bkname; }

    public String getbkoriginalprice() {
        return bkoriginalprice;
    }

    public String getbkauthor() {
        return bkauthor;
    }

    public String getbkrent() {
        return bkrent;
    }

    public String getbkurl() {
        return bkurl;
    }


    public void setavailable(String available) {this.available = available;}

    public void setbid(String bid) {this.bid=bid;}

    public void setuid(String uid) {this.uid=uid;}

    public  void setdays(String days) {this.days = days;}

    public void setbkid(String bkid){ this.bkid= bkid;}

    public void setbkname(String bkname) { this.bkname = bkname; }

    public void setbkoriginalprice(String bkoriginalprice) {this.bkoriginalprice = bkoriginalprice; }

    public void setbkauthor(String bkauthor) {
        this.bkauthor = bkauthor;
    }

    public void setbkrent(String bkrent) {
        this.bkrent = bkrent;
    }

    public void setbkurl(String bkurl) {
        this.bkurl = bkurl;
    }


}
