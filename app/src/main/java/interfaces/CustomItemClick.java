package interfaces;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Profile;

/**
 * Created by ptblr-1162 on 23/3/17.
 */

public interface CustomItemClick {

    public void onClick(int position, Profile profile, ImageView view);
}
