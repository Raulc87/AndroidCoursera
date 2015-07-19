package labs.course.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;


public class MainActivity extends Activity {

    private static final int MAX_TINT = 300;
    private static final int MAX_COLOR_COMP = 255;
    private static final String VISIT_MOMA_URL = "http://www.moma.org";

    private ImageView mView1, mView2, mView3, mView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView1 = (ImageView) findViewById(R.id.left_one);
        mView2 = (ImageView) findViewById(R.id.left_two);
        mView3 = (ImageView) findViewById(R.id.right_one);
        mView4 = (ImageView) findViewById(R.id.right_three);

        setupTintControl();
    }


    // Set up the SeekBar the manages the tint level
    private void setupTintControl() {

        SeekBar tintControl = (SeekBar) findViewById(R.id.slider);
        tintControl.setMax(MAX_TINT);
        tintControl.setProgress(0);

        // Set an OnSeekBarChangeListener on the
        tintControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {



            public void onProgressChanged(SeekBar seekBar, final int progress,
                                          boolean fromUser) {
                updateAllViews(progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    void updateAllViews(int nTint) {
        updateView(mView1, nTint);
        updateView(mView2, nTint);
        updateView(mView3, nTint);
        updateView(mView4, nTint);
    }

    private void updateView(final ImageView image, final int newAlpha) {
                image.setColorFilter(Color.argb(newAlpha, MAX_COLOR_COMP, MAX_COLOR_COMP, 0), PorterDuff.Mode.ADD);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.more_info) {
            DialogFragment newFragment = AlertDialogFragment.newInstance();
            newFragment.show(getFragmentManager(), "dialog");
        }

        return super.onOptionsItemSelected(item);
    }
    public static class AlertDialogFragment extends DialogFragment {

        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.more_info_text)

                            // User cannot dismiss dialog by hitting back button
                    .setCancelable(false)

                            // Set up No Button
                    .setNegativeButton(R.string.not_now,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                            dismiss();
                                }
                            })

                            // Set up Yes Button
                    .setPositiveButton(R.string.visit_moma,
                            new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, int id) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri
                                            .parse(VISIT_MOMA_URL)));
                                    dismiss();
                                }
                            }).create();
        }
    }
}
