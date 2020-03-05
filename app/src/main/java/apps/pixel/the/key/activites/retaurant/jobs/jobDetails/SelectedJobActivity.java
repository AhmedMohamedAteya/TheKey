package apps.pixel.the.key.activites.retaurant.jobs.jobDetails;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NavUtils;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import apps.pixel.the.key.R;
import apps.pixel.the.key.activites.retaurant.jobs.applicationJob.ApplicationJobActivity;
import apps.pixel.the.key.models.jobDetails.JobDetailsModel;
import apps.pixel.the.key.utilities.CairoBoldButton;
import apps.pixel.the.key.utilities.CairoBoldTextView;
import apps.pixel.the.key.utilities.CairoRegularTextView;
import apps.pixel.the.key.utilities.Constant;

public class SelectedJobActivity extends AppCompatActivity implements JobDetailsInterface {

    //Constant.SELECTED_JOB_ID
    public static SwipeRefreshLayout swipeContainer;

    private CairoBoldButton mBtnApply;
    private String selectedJobId;

    private JobDetailsPresenter jobDetailsPresenter;

    private CairoBoldTextView txtJobTitle;
    private CairoRegularTextView txtExperience;
    private CairoRegularTextView txtEducation;
    private CairoRegularTextView txtEmploymentType;
    private CairoRegularTextView txtSalary;
    private CairoRegularTextView txtDesc;
    private AppCompatImageView mImg;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_job);


        setUpUi();
    }

    private void setUpUi() {
        swipeContainer = findViewById(R.id.swipeContainer);

        mImg = findViewById(R.id.img);

        txtJobTitle = findViewById(R.id.txt_job_title);
        txtExperience = findViewById(R.id.txt_experience);
        txtEducation = findViewById(R.id.txt_education);
        txtEmploymentType = findViewById(R.id.txt_employment_type);
        txtSalary = findViewById(R.id.txt_salary);
        txtDesc = findViewById(R.id.txt_desc);


        selectedJobId = getIntent().getStringExtra(Constant.SELECTED_JOB_ID);

        jobDetailsPresenter = new JobDetailsPresenter(this, this);
        jobDetailsPresenter.getSeletedJobDetails(selectedJobId);

        swipeContainer.setOnRefreshListener(() -> jobDetailsPresenter.getSeletedJobDetails(selectedJobId));
        Constant.setSwipeLayourColor(this , swipeContainer);


        mBtnApply = findViewById(R.id.btn_apply);
        mBtnApply.setOnClickListener(v -> {
            Intent openApplication = new Intent(this, ApplicationJobActivity.class);
            startActivity(openApplication);
            Animatoo.animateSlideUp(this);
        });
    }

    @Override
    public void getJobDetails(JobDetailsModel jobDetailsModel) {
        txtJobTitle.setText(jobDetailsModel.getTitle());
        txtExperience.setText(jobDetailsModel.getExperience_Level());
        txtEducation.setText(jobDetailsModel.getEducation_Level());
        txtEmploymentType.setText(jobDetailsModel.getEmployment_Type());
        txtSalary.setText((int) jobDetailsModel.getSalary() + "  " + getString(R.string.pound));
        txtDesc.setText(jobDetailsModel.getDescription());

        Picasso.get()
                .load(Constant.BASE_URL_HTTP + jobDetailsModel.getImage())
                .fit()
                .centerCrop()
                .into(mImg);

    }
}
