package apps.pixel.al.egykey.activites.retaurant.selectedRestaurant

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import apps.pixel.al.egykey.R
import apps.pixel.al.egykey.fragments.FragmentCoupon
import apps.pixel.al.egykey.fragments.retaurant.OffersFramgent
import apps.pixel.al.egykey.fragments.retaurant.home.HomeRestFragment
import apps.pixel.al.egykey.fragments.retaurant.jobs.JobsFragment
import apps.pixel.al.egykey.utilities.Constant
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.selected_restaurant_kotlin.*


class SelectedRestaurantKotlinActivity : AppCompatActivity() {

    private var selectedResId: String? = null
    private var mImgBack: AppCompatImageView? = null
    private var mImgForCoupon: AppCompatImageView? = null


    companion object {
        private const val ID_JOBS = 1
        private const val ID_COUPON = 2
        private const val ID_HOME = 3
        private const val ID_EGY_KEY = 4
        private const val ID_OFFERS = 5
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selected_restaurant_kotlin)

        selectedResId = intent.getStringExtra(Constant.RESTAURANT_SELECTED_ID)

        mImgForCoupon = findViewById(R.id.img_for_coupon_only)
        mImgForCoupon?.setVisibility(View.GONE)
//        tv_selected.typeface = Typeface.createFromAsset(assets, "fonts/SourceSansPro-Regular.ttf")

        mImgBack = findViewById(R.id.arrow_back_page_two)
        mImgBack?.setOnClickListener({ onBackPressed() })


        bottomNavigation.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_COUPON, R.drawable.ic_nav_copoun))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_JOBS, R.drawable.ic_nav_jobs))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_EGY_KEY, R.drawable.ic_nav_egy_key))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_OFFERS, R.drawable.ic_nav_offer))


        //bottomNavigation.setCount(ID_EGY_KEY, "115")
        bottomNavigation.show(ID_HOME, true)
        handleHomeClick()

        bottomNavigation.setOnShowListener {
            //            val name = when (it.id) {
//                ID_HOME -> R.string.home
//                ID_COUPON -> R.string.get_copoun
//                ID_JOBS -> R.string.jobs
//                ID_EGY_KEY -> R.string.egy_key
//                ID_OFFERS -> R.string.offers
//                else -> R.string.home
//            }
//            tv_selected.text = "$name page is selected"
        }


        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                ID_HOME -> handleHomeClick();
                ID_COUPON -> handleCopounClick()
                ID_JOBS -> handleJobsClick()
                ID_EGY_KEY -> handleEgyKeyClick()
                ID_OFFERS -> handleOffersClick()
                else -> handleHomeClick()
            }
//            Toast.makeText(this, "$name is clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleHomeClick() {
        mImgForCoupon?.setVisibility(View.GONE)
        val homeRestFragment = HomeRestFragment()
        val bundle = Bundle()
        bundle.putString(Constant.RESTAURANT_SELECTED_ID, selectedResId)
        homeRestFragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, homeRestFragment).addToBackStack(null)
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right).commit()
    }

    private fun handleJobsClick() {
        mImgForCoupon?.setVisibility(View.GONE)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, JobsFragment())
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right).addToBackStack(null).commit()
    }

    private fun handleCopounClick() {
        mImgForCoupon?.setVisibility(View.GONE)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FragmentCoupon())
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right).addToBackStack(null).commit()
    }

    private fun handleOffersClick() {
        mImgForCoupon?.setVisibility(View.GONE)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, OffersFramgent())
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right).addToBackStack(null).commit()
    }

    private fun handleEgyKeyClick() {
        mImgForCoupon?.setVisibility(View.GONE)

    }


    override fun onResume() {
        super.onResume()

        bottomNavigation.show(ID_HOME, true)
        handleHomeClick()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finish()
    }
}
