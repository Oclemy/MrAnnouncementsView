package info.camposha.mrannouncementview;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * Let's extend the ViewFlipper class. This class normally animates between two
 * or more views that have been added to it.
 */
public class AnnouncementView extends ViewFlipper implements View.OnClickListener {

    private Context mContext;
    private List<String> mAnnouncements;

    /**
     * Let's supply two overrides of our constructor
     * @param context
     */
    public AnnouncementView(Context context) { super(context);}
    public AnnouncementView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Let's receive context, set flip interval,padding then set in and
     * out animation
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        setFlipInterval(3000);
        setPadding(dp2px(5f), dp2px(5f), dp2px(5f), dp2px(5f));
        setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in));
        setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out));
    }

    /**
     * Next thing is for us to create a method that will first receive a list
     * of announcements then flip through them via our AnnouncementView.
     * @param announcements
     */
    public void addAnnouncement(List<String> announcements) {
        //let's remove all child views from the ViewGroup.
        removeAllViews();
        mAnnouncements = announcements;
        for (int i = 0; i < announcements.size(); i++) {
            String currentAnnouncement = announcements.get(i);
            // Let's Build a Tetview based on the content of the announcement
            TextView textView = new TextView(mContext);
            textView.setMaxLines(3);
            textView.setText(currentAnnouncement);
            textView.setTextSize(20f);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(Color.parseColor("#ffffff"));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            // Let's set the location of the announcement to the textView tag.
            textView.setTag(i);
            textView.setOnClickListener(this);
            // Let's add the announcement to the ViewFlipper
            AnnouncementView.this.addView(textView, new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        String announcement = mAnnouncements.get(position);
        if (mOnAnnouncementClickListener != null) {
            mOnAnnouncementClickListener.onAnnouncementClick(position, announcement);
        }
    }

    /**
     * Notification click monitor interface
     */
    public interface OnAnnouncementClickListener {
        void onAnnouncementClick(int position, String announcement);
    }

    private OnAnnouncementClickListener mOnAnnouncementClickListener;

    /**
     * Set notification click listener
     *
     * @param onAnnouncementClickListener Notification click listener
     */
    public void setOnAnnouncementClickListener(OnAnnouncementClickListener
    onAnnouncementClickListener) {
        mOnAnnouncementClickListener = onAnnouncementClickListener;
    }

    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                mContext.getResources().getDisplayMetrics());
    }
}
