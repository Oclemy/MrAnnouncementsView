package info.camposha.mrannouncementview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AnnouncementView announcementView;

    /**
     * Let's add and return our announcements
     * @return
     */
    private List<String> getAnnouncements(){
        List<String> announcements = new ArrayList<>();
        announcements.add("First Years come with your ID cards please.");
        announcements.add("Law Students meet me at Dr Sagini Hall 8pm ");
        announcements.add("Good Doctor SN2 on in ABC.Don't miss it.");
        announcements.add("Free snacks for all at the Sadhguru event.");
        announcements.add("College site under maintenance till evening.");
        announcements.add("Betelguese goes supernova. Hey, joking");
        announcements.add("Free dental checkup sponsored by College.");
        announcements.add("One who tried hacking college site.Please STOP.");
        announcements.add("Python wins TIOBE language of Year. Java still top");

        return announcements;
    }

    /**
     * Let's now initialize our AnnouncementView
     */
    private void initializeAnnouncementView() {
        announcementView = findViewById(R.id.announcemnet_view);
        announcementView.addAnnouncement(getAnnouncements());
        announcementView.startFlipping();
    }

    private void handleAnnouncementClick(){
        announcementView.setOnAnnouncementClickListener(new AnnouncementView.OnAnnouncementClickListener() {
            @Override
            public void onAnnouncementClick(int position, String announcement) {
                Toast.makeText(MainActivity.this, announcement, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeAnnouncementView();
        this.handleAnnouncementClick();
    }
}
//end