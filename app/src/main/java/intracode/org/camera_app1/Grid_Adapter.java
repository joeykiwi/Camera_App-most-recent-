package intracode.org.camera_app1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by jongwookim on 2/6/15.
 */
public class Grid_Adapter extends ArrayAdapter<Photo_Image> {
    Context context;
    int resouceLayoutiId;
    ArrayList<Photo_Image> images = null;

    public Grid_Adapter(Context c, int r, ArrayList<Photo_Image> img) {
        super(c,r,img);
        this.resouceLayoutiId = r;
        this.context = c;
        this.images = img;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHolder holder = null;
        if (row == null) {
            Log.d("Joey", "row == null");
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resouceLayoutiId, parent, false);
            holder = new DataHolder();
            holder.image = (ImageView) row.findViewById(R.id.image_view);
            row.setTag(holder);
        } else {
            holder = (DataHolder) row.getTag();
        }
        Photo_Image img = images.get(position);
        Bitmap bitmap = decodeBitmapFromUri(images.get(position).get_path(), 100, 100);
        holder.image.setImageBitmap(bitmap);
        return row;

    }

    public Bitmap decodeBitmapFromUri(String path, int reqWidth, int reqHeight) {
        Bitmap bm = null;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth ) {
            if (width > height) {
                inSampleSize = Math.round((float)height/(float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width/(float)reqWidth);
            }
        }
        return inSampleSize;
    }

    static class DataHolder {
        ImageView image;
//        TextView txt;
    }

}
