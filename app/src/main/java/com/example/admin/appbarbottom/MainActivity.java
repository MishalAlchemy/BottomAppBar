package com.example.admin.appbarbottom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    BottomAppBar bottomAppBar;
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        addFragment(new ProfileFragment(), false, ProfileFragment.class.getName(), false, false, null, false, false, null);
        bottomAppBar = findViewById(R.id.bottom_appbar);

        bottomAppBar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheet(BottomSheetFragment.class.getName());


            }
        });
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha_anim);
////
////                // Use bounce interpolator with amplitude 0.2 and frequency 20
//                //Use for bounce fab button
//                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
//                myAnim.setInterpolator(interpolator);
//                floatingActionButton.startAnimation(myAnim);
                showDiag();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        bottomAppBar.replaceMenu(R.menu.secondary_menu);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void addFragment(Fragment fragment, boolean addToBackStack, String tag, boolean isAnimate, boolean isArguments, Bundle bundle, boolean isAdd, boolean isShared, View view) {

//        fragment.setEnterTransition(new Fade());
//        fragment.setExitTransition(new Fade());

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (isAnimate) {
//            ft.setCustomAnimations(R.anim.slide_in_right,
//                    R.anim.slide_out_left, R.anim.slide_in_left,
//                    R.anim.slide_out_right);
            ft.setCustomAnimations(R.animator.slide_up, R.animator.slide_down);
        }
        if (isShared) {
            ft.addSharedElement(view, ViewCompat.getTransitionName(view));
        }
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }

        if (isArguments) {
            fragment.setArguments(bundle);
        }

        if (!isAdd) {
            ft.replace(R.id.frame_container, fragment, tag);

        } else {
            ft.add(R.id.frame_container, fragment, tag);
        }
        ft.commitAllowingStateLoss();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() >= 0) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
            if (fragment instanceof ProfileFragment) {
                setToolbar("Animals", false);
                toggleFabButton();
            }

        }
    }

    public void toggleFabButton() {
        if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            floatingActionButton.setImageResource(R.drawable.ic_reply_black_24dp);
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            floatingActionButton.setImageResource(R.drawable.ic_add_black_24dp);
        }
    }

    public void setToolbar(String title, boolean isBack) {
        if (isBack) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        toolbar.setTitle(title);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
                if (fragment instanceof ProfileFragment) {

                } else {
                    onBackPressed();
                }
            }
        });


    }

    public void openBottomSheet(String tag) {
        BottomSheetDialogFragment bottomSheet = null;
        if (tag.equals(BottomSheetFragment.class.getName())) {
            bottomSheet = new BottomSheetFragment();
            bottomSheet.setAllowEnterTransitionOverlap(true);
        }
        if (bottomSheet != null) {
            bottomSheet.show(getSupportFragmentManager(), tag);
        }
    }

    class MyBounceInterpolator implements android.view.animation.Interpolator {
        private double mAmplitude = 1;
        private double mFrequency = 10;

        MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }
    }

    private void showDiag() {

        final View dialogView = View.inflate(this, R.layout.dialog, null);
//
        final Dialog dialog = new Dialog(this, R.style.MyAlertDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.closeDialogImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                revealShow(dialogView, false, dialog);
            }
        });

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                revealShow(dialogView, true, null);
            }
        });

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {

                    revealShow(dialogView, false, dialog);
                    return true;
                }

                return false;
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();
    }

    private void revealShow(View dialogView, boolean b, final Dialog dialog) {

        final View view = dialogView.findViewById(R.id.dialog);

        int w = view.getWidth();
        int h = view.getHeight();

        int endRadius = (int) Math.hypot(w, h);

        int cx = (int) (floatingActionButton.getX() + (floatingActionButton.getWidth() / 2));
        int cy = (int) (floatingActionButton.getY()) + floatingActionButton.getHeight() / 2;


        if (b) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, endRadius);

            view.setVisibility(View.VISIBLE);
            revealAnimator.setDuration(700);
            revealAnimator.start();

        } else {

            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);

                }
            });
            anim.setDuration(700);
            anim.start();
        }

    }
}
