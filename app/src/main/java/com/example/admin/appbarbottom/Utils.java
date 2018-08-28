package com.example.admin.appbarbottom;

import android.content.Context;

import java.util.ArrayList;

import model.Profile;

public class Utils {

    public static ArrayList<Profile> generateAnimalItems(Context context) {
        ArrayList<Profile> profile = new ArrayList<>();
        profile.add(new Profile("Dog", context.getString(R.string.dog_blurb), "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1"));
        profile.add(new Profile("Penguin", context.getString(R.string.penguin_blurb), "https://c1.staticflickr.com/9/8616/16237154608_c5489cae31_z.jpg"));
        profile.add(new Profile("Eagle", context.getString(R.string.eagle_blurb), "https://c1.staticflickr.com/5/4010/4210875342_7cb06a9b62_z.jpg?zz=1"));
        profile.add(new Profile("Rabbit", context.getString(R.string.rabbit_blurb), "https://c2.staticflickr.com/4/3285/2819978026_175072995a_z.jpg?zz=1"));
        profile.add(new Profile("Dolphin", context.getString(R.string.dolphin_blurb), "https://c1.staticflickr.com/8/7619/16124006043_60bc4d8ca5_z.jpg"));
        profile.add(new Profile("Snek", context.getString(R.string.snek_blurb), "https://c1.staticflickr.com/9/8796/17158681740_a6caa5099f_z.jpg"));
        profile.add(new Profile("Seal", context.getString(R.string.seal_blurb), "https://c1.staticflickr.com/4/3852/14729534910_62b338dd72_z.jpg"));
        profile.add(new Profile("Rhino", context.getString(R.string.rhino_blurb), "https://c1.staticflickr.com/1/335/18040640224_f56f05f8dc_z.jpg"));
        profile.add(new Profile("Leopard", context.getString(R.string.leopard_blurb), "https://c1.staticflickr.com/9/8678/16645189230_b0e96e7af9_z.jpg"));
        profile.add(new Profile("Hippo", context.getString(R.string.hippo_blurb), "https://c2.staticflickr.com/4/3774/9377370000_6a57d1cfec_z.jpg"));
        return profile;
    }
}
