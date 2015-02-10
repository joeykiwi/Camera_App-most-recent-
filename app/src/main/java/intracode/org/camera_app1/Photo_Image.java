package intracode.org.camera_app1;

/**
 * Created by jongwookim on 2/6/15.
 */
public class Photo_Image {
    public String _data;
    public String _pic;
    public Photo_Image(){
        super();
    }
    public  Photo_Image(String data, String pic) {
        super();
        _data = data;
        _pic = pic;
    }

    public String get_name(){
        return _data;
    }


    public String get_path() {
        return _pic;
    }

    public void set_name(String name) {
        _data = name;
    }
}
