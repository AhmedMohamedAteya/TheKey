package apps.pixel.the.key.activites.retaurant.selectedRestaurant

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import apps.pixel.the.key.activites.relatedToBasket.OrderDetailsActivity
import apps.pixel.the.key.fragments.selectedCatHome.coupon.FragmentCoupon
import apps.pixel.the.key.fragments.menu.MenuFragment
import apps.pixel.the.key.fragments.selectedCatHome.offers.OffersFramgent
import apps.pixel.the.key.fragments.selectedCatHome.home.HomeRestFragment
import apps.pixel.the.key.fragments.selectedCatHome.jobs.JobsFragment
import apps.pixel.the.key.utilities.CairoBoldTextView
import apps.pixel.the.key.utilities.Constant
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.selected_restaurant_kotlin.*


class SelectedItemKotlinActivity : AppCompatActivity() {

    private var selectedResId: String? = null
    private var selectedCat: String? = null
    private var selectedEnName: String? = null
    private var selectedArName: String? = null
    private var imgUrl: String? = null
    private var mImgBack: AppCompatImageView? = null
    private var mImgBasket: AppCompatImageView? = null
    private var mImgForCoupon: AppCompatImageView? = null
    private var mTxtRestarauntName: CairoBoldTextView? = null

    internal var fragment: Fragment? = null

    companion object {
        private const val ID_JOBS = 1
        private const val ID_COUPON = 2
        private const val ID_HOME = 3
        private const val ID_EGY_KEY = 4
        private const val ID_OFFERS = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apps.pixel.the.key.R.layout.selected_restaurant_kotlin)

        selectedResId = intent.getStringExtra(Constant.ITEM_SELECTED_ID)
        selectedCat = intent.getStringExtra(Constant.CAT_THAT_SELECTED)
        selectedEnName = intent.getStringExtra(Constant.ITEM_SELECTED_NAME_EN)
        selectedArName = intent.getStringExtra(Constant.ITEM_SELECTED_NAME_AR)
        imgUrl = intent.getStringExtra(Constant.ITEM_SELECTED_REST_LOGO)

        mImgBasket = findViewById(apps.pixel.the.key.R.id.ic_basket)
        mImgBasket?.setOnClickListener {
            val openBasket = Intent(this, OrderDetailsActivity::class.java)
            openBasket.putExtra(Constant.SELECTED_RESTAURANT_DETAILS_FOR_MENU, selectedResId)
            startActivity(openBasket)
        }

        mImgForCoupon = findViewById(apps.pixel.the.key.R.id.img_for_coupon_only)
        mImgForCoupon?.setVisibility(View.GONE)

        mTxtRestarauntName = findViewById(apps.pixel.the.key.R.id.restaurant_name)
        //mTxtRestarauntName?.setVisibility(View.GONE)

        if (Constant.getLng(this).equals("ar"))
            mTxtRestarauntName?.setText(selectedArName)
        else
            mTxtRestarauntName?.setText(selectedEnName)
//        tv_selected.typeface = Typeface.createFromAsset(assets, "fonts/SourceSansPro-Regular.ttf")

        mImgBack = findViewById(apps.pixel.the.key.R.id.arrow_back_page_two)
        mImgBack?.setOnClickListener({ onBackPressed() })

        bottomNavigation.add(MeowBottomNavigation.Model(ID_HOME, apps.pixel.the.key.R.drawable.ic_home))
       // bottomNavigation.add(MeowBottomNavigation.Model(ID_OFFERS, apps.pixel.the.key.R.drawable.ic_menu))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_JOBS, apps.pixel.the.key.R.drawable.ic_nav_jobs))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_EGY_KEY, apps.pixel.the.key.R.drawable.ic_nav_egy_key))
        bottomNavigation.add(MeowBottomNavigation.Model(ID_COUPON, apps.pixel.the.key.R.drawable.ic_nav_copoun))


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
                ID_OFFERS -> handleMenuClick()
                else -> handleHomeClick()
            }
//            Toast.makeText(this, "$name is clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleHomeClick() {
        mImgForCoupon?.setVisibility(View.GONE)
        //mTxtRestarauntName?.setVisibility(View.GONE)
        val homeRestFragment = HomeRestFragment()
        val bundle = Bundle()
        bundle.putString(Constant.ITEM_SELECTED_ID, selectedResId)
        bundle.putString(Constant.CAT_THAT_SELECTED, selectedCat)

        homeRestFragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(apps.pixel.the.key.R.id.fragment_container, homeRestFragment)
                .setCustomAnimations(apps.pixel.the.key.R.anim.enter_from_right, apps.pixel.the.key.R.anim.exit_from_right).commit()
    }

    private fun handleJobsClick() {
        mImgForCoupon?.setVisibility(View.GONE)
        // mTxtRestarauntName?.setVisibility(View.GONE)
        val homeRestFragment = JobsFragment()
        val bundle = Bundle()
        bundle.putString(Constant.ITEM_SELECTED_ID, selectedResId)
        bundle.putString(Constant.CAT_THAT_SELECTED, selectedCat)

        homeRestFragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(apps.pixel.the.key.R.id.fragment_container, homeRestFragment)
                .setCustomAnimations(apps.pixel.the.key.R.anim.enter_from_right, apps.pixel.the.key.R.anim.exit_from_right).commit()
    }

    private fun handleCopounClick() {
        mImgForCoupon?.setVisibility(View.GONE)
        // mTxtRestarauntName?.setVisibility(View.GONE)
        supportFragmentManager.beginTransaction().replace(apps.pixel.the.key.R.id.fragment_container, FragmentCoupon())
                .setCustomAnimations(apps.pixel.the.key.R.anim.enter_from_right, apps.pixel.the.key.R.anim.exit_from_right).commit()
    }

    private fun handleMenuClick() {
        mImgForCoupon?.setVisibility(View.GONE)
        // mTxtRestarauntName?.setVisibility(View.VISIBLE)


        val menuFragment = MenuFragment()

        val bundle = Bundle()
        bundle.putString(Constant.ITEM_SELECTED_ID, selectedResId)
        bundle.putString(Constant.CAT_THAT_SELECTED, selectedCat)
        bundle.putString(Constant.ITEM_SELECTED_REST_LOGO, imgUrl)
        bundle.putString(Constant.ITEM_SELECTED_NAME_AR, selectedArName)
        bundle.putString(Constant.ITEM_SELECTED_NAME_EN, selectedEnName)



        menuFragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(apps.pixel.the.key.R.id.fragment_container, menuFragment)
                .setCustomAnimations(apps.pixel.the.key.R.anim.enter_from_right, apps.pixel.the.key.R.anim.exit_from_right).commit()
    }

    private fun handleEgyKeyClick() {
        //Toast.makeText(this,"ss",Toast.LENGTH_LONG).show();
        mImgForCoupon?.setVisibility(View.GONE)

        //mTxtRestarauntName?.setVisibility(View.GONE)
        val offersFragment = OffersFramgent()
        val bundle = Bundle()
        bundle.putString(Constant.ITEM_SELECTED_ID, selectedResId)
        bundle.putString(Constant.CAT_THAT_SELECTED, selectedCat)

        offersFragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(apps.pixel.the.key.R.id.fragment_container, offersFragment)
                .setCustomAnimations(apps.pixel.the.key.R.anim.enter_from_right, apps.pixel.the.key.R.anim.exit_from_right)
                .commit()
    }

    override fun onResume() {
        super.onResume()

        //bottomNavigation.show(ID_HOME, true)
        //handleHomeClick()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fm = getSupportFragmentManager()
        fm.popBackStack()
        finish()
    }

}
