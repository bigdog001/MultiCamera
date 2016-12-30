package apps.bigdog.com.multicamera.activity;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tool.mytool.lib.util.LogUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import apps.bigdog.com.multicamera.R;
import apps.bigdog.com.multicamera.activity.base.BaseActivity;
import apps.bigdog.com.multicamera.config.InterfaceGenerator;
import apps.bigdog.com.multicamera.fragment.BaseFragment;
import apps.bigdog.com.multicamera.fragment.HomeFourFragment;
import apps.bigdog.com.multicamera.fragment.HomeOneFragment;
import apps.bigdog.com.multicamera.fragment.HomeThreeFragment;
import apps.bigdog.com.multicamera.fragment.HomeTwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jw362j on 6/1/2016.
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    @ViewInject(R.id.main_botton_btns)
    private RadioGroup main_botton_btns;
    @ViewInject(R.id.radioBtn_home1)
    private RadioButton radioBtn_home1;
    @ViewInject(R.id.radioBtn_home2)
    private RadioButton radioBtn_home2;
    @ViewInject(R.id.radioBtn_home3)
    private RadioButton radioBtn_home3;
    @ViewInject(R.id.radioBtn_home4)
    private RadioButton radioBtn_home4;
    @ViewInject(R.id.main_content_fgt)
    private ViewPager main_content_fgt;
    @ViewInject(R.id.home_menu_main_menu)
    private ImageView home_menu_main_menu;

    private List<BaseFragment> fragments;

    private HomeOneFragment one;
    private HomeTwoFragment two;
    private HomeThreeFragment three;
    private HomeFourFragment four;

    // 选中的索引
    public static int chooseIndex = -1;
    private boolean isRecycled ;

    @Event(value = {R.id.home_menu_main_menu, R.id.radioBtn_home2, R.id.radioBtn_home3, R.id.radioBtn_home4}, type = View.OnClickListener.class)
    private void ItemOnclick(View v) {
        switch (v.getId()){
            case R.id.home_menu_main_menu:
                showPopupWindow(v);
                break;

            default:
                break;
        }
    }

    private void showPopupWindow(View parent) {

    }


        protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initParams() {
        fragments = new ArrayList<BaseFragment>();
        radioBtn_home1.setChecked(true);
        main_botton_btns.setOnCheckedChangeListener(this);
        initFragments();
        initViewPager();
    }

    private void initViewPager() {
        main_content_fgt.setOffscreenPageLimit(fragments.size()-1);
        main_content_fgt.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
//                return fragments.get(position%(fragments.size()));
                return fragments.get(position);
            }

            @Override
            public int getCount() {
//                return Integer.MAX_VALUE;
                return fragments.size();
            }
        });
        main_content_fgt.setCurrentItem(0);
        main_content_fgt.addOnPageChangeListener(this);
    }

    private void initFragments() {
        one = new HomeOneFragment();
        two = new HomeTwoFragment();
        three = new HomeThreeFragment();
        four = new HomeFourFragment();
        fragments.add(one);
        fragments.add(two);
        fragments.add(three);
        fragments.add(four);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isRecycled){
            main_content_fgt.setCurrentItem(chooseIndex);
            switch (chooseIndex){
                case 0:
                    radioBtn_home1.setChecked(true);
                    break;
                case 1:
                    radioBtn_home2.setChecked(true);
                    break;
                case 2:
                    radioBtn_home3.setChecked(true);
                    break;
                case 3:
                    radioBtn_home4.setChecked(true);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isRecycled",true);
        outState.putInt("chooseIndex",chooseIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isRecycled = savedInstanceState.getBoolean("isRecycled");
        chooseIndex = savedInstanceState.getInt("chooseIndex");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            onCheckedChanged(main_botton_btns, R.id.radioBtn_home1);
        }
    }

    @Override
    public void OnStop() {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        LogUtil.log("the selected is :" + i);
        switch (i) {
            case R.id.radioBtn_home1:
                if (chooseIndex != 0) {
                    chooseIndex = 0;
                    main_content_fgt.setCurrentItem(chooseIndex);
                }

                break;
            case R.id.radioBtn_home2:
                if (chooseIndex != 1) {
                    chooseIndex = 1;
                    main_content_fgt.setCurrentItem(chooseIndex);
                }
                break;
            case R.id.radioBtn_home3:
                if (chooseIndex != 2) {
                    chooseIndex = 2;
                    main_content_fgt.setCurrentItem(chooseIndex);
                }
                break;
            case R.id.radioBtn_home4:
                if (chooseIndex != 3) {
                    chooseIndex = 3;
                    main_content_fgt.setCurrentItem(chooseIndex);
                }
                break;

            default:
                break;
        }
        scanFragmentsCommunicatable();
    }

    @Override
    public void onPageSelected(int position) {
        LogUtil.log("onPageSelected:"+position);
//        int position_t = position%(fragments.size());
        switch (position){
            case 0:
                if(!radioBtn_home1.isChecked())radioBtn_home1.setChecked(true);
                chooseIndex = 0;
                break;
            case 1:
                if(!radioBtn_home2.isChecked())radioBtn_home2.setChecked(true);
                chooseIndex = 1;
                break;
            case 2:
                if(!radioBtn_home3.isChecked())radioBtn_home3.setChecked(true);
                chooseIndex = 2;
                break;
            case 3:
                if(!radioBtn_home4.isChecked())radioBtn_home4.setChecked(true);
                chooseIndex = 3;
                break;

            default:
                break;
        }
        scanFragmentsCommunicatable();
    }

    private void scanFragmentsCommunicatable(){
        if (fragments == null) {
            return;
        }
        for (InterfaceGenerator.ICommunicatable comunicate: fragments) {
            if(chooseIndex == comunicate.myIndex()){
                comunicate.setCommunicatable(true);
            }else {
                comunicate.setCommunicatable(false);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
