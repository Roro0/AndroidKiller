package edu.feucui.seektreasure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BaiduMap.OnMapStatusChangeListener, BDLocationListener {
    MapView mMapView;
    BaiduMap mapMgr;
    Button mBtn;
    Button mBtnLocal;
    RelativeLayout relativeLayout;
    LocationClient locationClient;
    @BindView(R.id.btn_compass)
    Button btnCompass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());//在SDK各功能组件使用之前都需要调用，因此建议该方法放在Application的初始化方法中
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        //找到MapView
//        mMapView = (MapView) findViewById(R.id.bmapView);//找到MapView
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_main);
        mBtn = (Button) findViewById(R.id.btn_switch_state);//视图转换
        mBtnLocal = (Button) findViewById(R.id.btn_local);//定位
        //卫星视图和普通视图的切换
        mBtn.setOnClickListener(this);
        mBtnLocal.setOnClickListener(this);
        //地图的状态
        MapStatus status = new MapStatus.Builder()
                .overlook(0)
                .zoom(20)
                .build();
        BaiduMapOptions option = new BaiduMapOptions()
                .zoomControlsEnabled(true)
                .zoomGesturesEnabled(true)
                .rotateGesturesEnabled(true)
                .scaleControlEnabled(true)
                .scrollGesturesEnabled(true)
                .mapStatus(status);
        //动态添加mapView
        mMapView = new MapView(this, option);
        mMapView.showZoomControls(true);//显示地图缩放控件
        mMapView.showScaleControl(true);//显示地图比例尺
        relativeLayout.addView(mMapView, 0);//参数0表示该视图是第一视图（布局内含有Button,如果没有0表示该View会抢夺其他view的焦点）
        //获取操作地图的控制器
        mapMgr = mMapView.getMap();
        //地图状态改变监听
        mapMgr.setOnMapStatusChangeListener(this);
        //定位
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapMgr.setMyLocationEnabled(true);
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_switch_state:
                //切换卫星试图和普通试图
                switchMap();
                break;
            case R.id.btn_local:
                //定位
                setMyLocation();
                break;
            case R.id.btn_compass://指南
                setCompass();

        }
    }

    /**
     * 设置指南
     */
    private void setCompass() {

    }

    /**
     * 切换视图
     */
    private void switchMap() {
        //判断视图的类型
        if (mapMgr.getMapType() == BaiduMap.MAP_TYPE_SATELLITE) {
            mapMgr.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            mBtn.setText("卫星地图");
//            int type =  mapMgr.getMapType()==BaiduMap.MAP_TYPE_NORMAL?BaiduMap.MAP_TYPE_SATELLITE:BaiduMap.MAP_TYPE_NORMAL;
        } else {
            mapMgr.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
            mBtn.setText("普通地图");
        }
    }

    /**
     * 设置定位
     */
    private void setMyLocation() {

        /**
         * 开去定位图层
         *初始化LocalClient
         *设置一些定位相关的LocalClientOption
         *设置监听，定位的监听
         *开启定位
         */
        mapMgr.setMyLocationEnabled(true);        //开去定位图层
        locationClient = new LocationClient(getApplication()); //初始化LocalClient
        LocationClientOption locOption = locationClient.getLocOption();//设置一些定位相关的LocalClientOption
        locOption.setOpenGps(true);//默认打开GPS
        locOption.setCoorType("bd0911");//设置地图类型
        locOption.setIsNeedAddress(true);//
        locOption.setScanSpan(10000);//范围大小
//        locOption.setAddrType("all");//返回的定位结果包含地址信息
        locationClient.setLocOption(locOption);
        //设置监听
        locationClient.registerLocationListener(this);    //设置监听，定位的监听
        locationClient.start();//开启定位
        locationClient.requestLocation();//针对某些机型，开启请求定位失败
    }

    /**
     * 地图状态改变监听
     *
     * @param mapStatus
     */
    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {
    }

    @Override
    public void onMapStatusChange(MapStatus mapStatus) {
        Toast.makeText(this, "纬度1：" + mapStatus.target.latitude + ",经度2：" + mapStatus.target.longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {

    }

    LatLng myLocal;

    /**
     * 定位监听：获取定位的信息
     *
     * @param bdLocation
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //如果没有定位，重新请求有没有定位成功

        //获得定位信息
        //定位信息设置到地图上
        //移动到定位的位置上去
        //-----第一次进入：一进到项目需要定位到定位的地方去
        //-------点击按钮定位到定位到的地方上

        //判断
        if (bdLocation == null) {
            locationClient.requestLocation();
            return;
        }
        double lng = bdLocation.getLongitude();//经度
        double lat = bdLocation.getLatitude();//纬度
        Toast.makeText(this, "纬度：" + lng + ",经度：" + lat, Toast.LENGTH_SHORT).show();
        /**
         * 添加定位的标志
         *   1.拿到定位的信息
         *   2.给地图设置定位的数据
         */
        MyLocationData locationData = new MyLocationData.Builder()
                .longitude(lng)//定位的经度
                .latitude(lat)//定位的纬度
                .accuracy(100f)//定位的景区的大小
                .build();
        mapMgr.setMyLocationData(locationData);
        /**
         * 移动我们的位置
         * 1.有我们的位置
         * 2.移动的话，地图状态是否发生变化
         * 3.在地图状态设置位置
         * 4.位置变化，地图改变
         */
        myLocal = new LatLng(lat, lng);
        //移动到指定位置
        moveToMyLocatuon();
    }

    /**
     * 移动到我们定位的位置上去
     */
    private void moveToMyLocatuon() {
        MapStatus mapStatus = new MapStatus.Builder()
                .target(myLocal)
                .rotate(0)//作用是摆正地图
                .zoom(15).build();
        //更新地图状态
        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mapMgr.animateMapStatus(statusUpdate);
        addMarker();
    }

    /**
     * 定位标志
     */
    private void addMarker() {
        /**
         *
         * 1.定位位置
         * 2.定位图标
         */
        MarkerOptions markerOption = new MarkerOptions();
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_gcoding);
        markerOption.position(myLocal).icon(bitmap);
        mapMgr.addOverlay(markerOption);
    }

    @OnClick(R.id.btn_compass)
    public void onClick() {
    }
    //添加标注物和监听
}

