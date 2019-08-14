package shop.books.bookiesh;

public class Delivery {

    private String pin;
    private String house ;
    private String area;
    private String city;
    private String state;
    private String name;
    private String totalbill;
    private String addrid;
    private String mobile;


    public Delivery() {

    }

    public String getaddr_id()  {return addrid;}

    public String getpin()   {return pin;}

    public String gethouse() { return house;}

    public String getarea() { return area;}

    public String getcity() { return city; }

    public String getstate() {
        return state;
    }

    public String getname() {
        return name;
    }

    public String gettotalbill() {
        return totalbill;
    }


    public void setaddr_id(String addr_id) {this.addrid=addr_id;}

    public void setpin(String pin) {this.pin=pin;}

    public  void sethouse(String house) {this.house = house;}

    public void setarea(String area){ this.area= area;}

    public void setcity(String city) { this.city = city; }

    public void setstate(String state) {this.state = state; }

    public void setname(String name) {
        this.name = name;
    }

    public void settotalbill(String totalbill) {
        this.totalbill = totalbill;
    }
}
