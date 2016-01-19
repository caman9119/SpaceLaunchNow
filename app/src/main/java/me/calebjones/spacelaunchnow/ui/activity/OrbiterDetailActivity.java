package me.calebjones.spacelaunchnow.ui.activity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.calebjones.spacelaunchnow.R;
import me.calebjones.spacelaunchnow.content.adapter.VehicleListAdapter;
import me.calebjones.spacelaunchnow.content.database.SharedPreference;
import me.calebjones.spacelaunchnow.content.models.Rocket;
import me.calebjones.spacelaunchnow.ui.fragment.vehicles.LaunchVehicleFragment;
import me.calebjones.spacelaunchnow.utils.CustomAnimatorListener;
import me.calebjones.spacelaunchnow.utils.CustomTransitionListener;
import me.calebjones.spacelaunchnow.utils.Utils;
import timber.log.Timber;

public class OrbiterDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;

    private SharedPreference sharedPreference;
    private android.content.SharedPreferences SharedPreferences;
    private View view, title_container, gridview;
    private Context context;
    private TextView toolbarTitle, detail_rocket, detail_vehicle_agency,
            orbiter_title, orbiter_description, wikiButton, orbiter_history, orbiter_history_description;
    private View orbiter_vehicle_card;
    private ImageView detail_profile_backdrop;
    private CircleImageView detail_profile_image;
    private AppBarLayout appBarLayout;
    private int mMaxScrollSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int m_theme;
        final int statusColor;
        this.context = getApplicationContext();

        sharedPreference = SharedPreference.getInstance(this.context);

        if (sharedPreference.getNightMode()) {
            m_theme = R.style.DarkTheme_Transparent;
            statusColor = ContextCompat.getColor(context, R.color.darkPrimary_dark);
        } else {
            m_theme = R.style.LightTheme_Transparent;
            statusColor = ContextCompat.getColor(context, R.color.colorPrimaryDark);
        }
        setTheme(m_theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orbiter_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.title_text);
        detail_rocket = (TextView) findViewById(R.id.detail_rocket);
        detail_vehicle_agency = (TextView) findViewById(R.id.detail_vehicle_agency);
        detail_profile_image = (CircleImageView) findViewById(R.id.detail_profile_image);
        detail_profile_backdrop = (ImageView) findViewById(R.id.detail_profile_backdrop);
        orbiter_title = (TextView) findViewById(R.id.orbiter_title);
        orbiter_description = (TextView) findViewById(R.id.orbiter_description);
        wikiButton = (TextView) findViewById(R.id.wikiButton);
        appBarLayout = (AppBarLayout) findViewById(R.id.detail_appbar);
        orbiter_history = (TextView) findViewById(R.id.orbiter_history);
        orbiter_history_description = (TextView) findViewById(R.id.orbiter_history_description);
        orbiter_vehicle_card = findViewById(R.id.orbiter_vehicle_card);
        title_container = findViewById(R.id.detail_title_container);
        gridview = findViewById(R.id.gridview);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            // Fab button
            detail_profile_image.setScaleX(0);
            detail_profile_image.setScaleY(0);
//            setupWindowAnimations();

            // Recover items from the intent
            final int position = getIntent().getIntExtra("position", 0);
            Timber.d("Position %s", position);

            // Recover book cover from BooksFragment cache
            Bitmap bookCoverBitmap = LaunchVehicleFragment.photoCache.get(position);
            ImageView toolbarBookCover = (ImageView) findViewById(R.id.detail_profile_backdrop);
            toolbarBookCover.setImageBitmap(bookCoverBitmap);

            // Define toolbar as the shared element
            detail_profile_backdrop.setBackground(new BitmapDrawable(getResources(), bookCoverBitmap));
            detail_profile_backdrop.setTransitionName("cover" + position);

            // Add a listener to get noticed when the transition ends to animate the fab button
            getWindow().getSharedElementEnterTransition().addListener(sharedTransitionListener);
        }


        appBarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appBarLayout.getTotalScrollRange();
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScroll = appBarLayout.getTotalScrollRange();
                int currentScroll = totalScroll + verticalOffset;

                Timber.v("AppBar totalScroll: %s currentScroll: %s verticalOffset: %s",
                        totalScroll, currentScroll, verticalOffset);
                int color = statusColor;
                int r = (color >> 16) & 0xFF;
                int g = (color >> 8) & 0xFF;
                int b = (color >> 0) & 0xFF;

                if ((currentScroll) < 255) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Timber.v("ColorNew: %s ColorPrimary: %s R: %s G: %s B: %s",
                                reverseNumber(currentScroll, 0, 255), R.color.colorPrimary, r, g, b);
                        Window window = getWindow();
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(Color.argb(reverseNumber(currentScroll, 0, 255), r, g, b));
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    }
                }
            }
        });

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
        displayOrbiterDetails();
    }

    public int reverseNumber(int num, int min, int max) {
        int number = (max + min) - num;
        Timber.v("Number: %s", number);
        return number;
    }

    public void displayOrbiterDetails() {
        Intent intent = getIntent();
        String orbiter = intent.getStringExtra("family");
        detail_rocket.setText(String.format("%s Spacecraft", orbiter));
        detail_vehicle_agency.setText(intent.getStringExtra("agency"));


        if (orbiter.equals("Soyuz")) {
            Glide.with(this)
                    .load(R.drawable.soyuz_spacecraft)
                    .into(detail_profile_backdrop);

            Glide.with(this)
                    .load(R.string.rus_logo)
                    .into(detail_profile_image);

            orbiter_vehicle_card.setVisibility(View.VISIBLE);

            //Set up history information
            orbiter_history.setText("Soyuz Spacecraft History");
            orbiter_history_description.setText(R.string.soyuz_history_description);
            wikiButton.setVisibility(View.VISIBLE);
            wikiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            //Set up vehicle card Information
            orbiter_title.setText("Soyuz TMA-M Details");
            orbiter_description.setText(R.string.soyuz_description);

        } else if (orbiter.equals("Shenzhou")) {
            Glide.with(this)
                    .load(R.drawable.shenzhou)
                    .into(detail_profile_backdrop);

            Glide.with(this)
                    .load(R.string.chn_logo)
                    .crossFade()
                    .error(R.drawable.icon_international)
                    .into(detail_profile_image);

            orbiter_vehicle_card.setVisibility(View.VISIBLE);

            //Set up history information
            orbiter_history.setText("Shenzhou Spacecraft History");
            orbiter_history_description.setText(R.string.shenzhou_history);
            wikiButton.setVisibility(View.VISIBLE);
            wikiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            //Set up vehicle card Information
            orbiter_title.setText("Shenzhou Details");
            orbiter_description.setText(R.string.shenzhou_description);

        } else if (orbiter.equals("Dragon")) {
            Glide.with(this)
                    .load(R.drawable.dragon)
                    .into(detail_profile_backdrop);

            Glide.with(this)
                    .load(R.string.spacex_logo)
                    .error(R.drawable.icon_international)
                    .into(detail_profile_image);

            orbiter_vehicle_card.setVisibility(View.VISIBLE);

            //Set up history information
            orbiter_history.setText("Dragon Spacecraft History");
            orbiter_history_description.setText(R.string.dragon_history);
            wikiButton.setVisibility(View.VISIBLE);
            wikiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            //Set up vehicle card Information
            orbiter_title.setText("Dragon V2 Details");
            orbiter_description.setText(R.string.dragon_description);

        } else if (orbiter.equals("Orion")) {
            Glide.with(this)
                    .load(R.drawable.orion)
                    .into(detail_profile_backdrop);

            Glide.with(this)
                    .load(R.drawable.usa_flag)
                    .error(R.drawable.icon_international)
                    .into(detail_profile_image);

            orbiter_vehicle_card.setVisibility(View.VISIBLE);

            //Set up history information
            orbiter_history.setText("Orion Spacecraft History");
            orbiter_history_description.setText(R.string.orion_history);
            wikiButton.setVisibility(View.VISIBLE);
            wikiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            //Set up vehicle card Information
            orbiter_title.setText("Orion Details");
            orbiter_description.setText(R.string.orion_description);
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
        getWindow().setEnterTransition(slide);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;
            detail_profile_image.animate().scaleY(0).scaleX(0).setDuration(200).start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            detail_profile_image.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }

    /**
     * I use a listener to get notified when the enter transition ends, and with that notifications
     * build my own coreography built with the elements of the UI
     * <p/>
     * Animations order:
     * <p/>
     * 1. The image is animated automatically by the SharedElementTransition
     * 2. The layout that contains the titles
     * 3. An alpha transition to show the text of the titles
     * 3. A scale animation to show the book info
     */
    private CustomTransitionListener sharedTransitionListener = new CustomTransitionListener() {

        @Override
        public void onTransitionEnd(Transition transition) {

            super.onTransitionEnd(transition);

            ViewPropertyAnimator showTitleAnimator = Utils.showViewByScale(detail_profile_image);
            showTitleAnimator.setListener(new CustomAnimatorListener() {

                @Override
                public void onAnimationEnd(Animator animation) {

                    super.onAnimationEnd(animation);
                    gridview.startAnimation(AnimationUtils.loadAnimation(OrbiterDetailActivity.this, R.anim.alpha_on));
                    gridview.setVisibility(View.VISIBLE);
                }
            });

            showTitleAnimator.start();
        }
    };

    @Override
    public void onBackPressed() {

        ViewPropertyAnimator hideTitleAnimator = Utils.hideViewByScaleXY(detail_profile_image);

        hideTitleAnimator.setListener(new CustomAnimatorListener() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                gridview.startAnimation(AnimationUtils.loadAnimation(OrbiterDetailActivity.this, R.anim.alpha_off));
                gridview.setVisibility(View.INVISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        coolBack();
                    }
                }, 600);

            }
        });

        hideTitleAnimator.start();
    }

    private void coolBack() {

        try {
            super.onBackPressed();

        } catch (NullPointerException e) {

            // TODO: workaround
        }

    }
}