package com.tool.bluetoothlauncher;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by jw362j on 12/22/2016.
 */
public class BluetoothManager {
    /**
     * 11
     * 当前 Android 设备是否支持 Bluetooth
     * 12
     * <p/>
     * 13
     *
     * @return true：支持 Bluetooth false：不支持 Bluetooth
     * 14
     */

    public static boolean isBluetoothSupported() {
        return BluetoothAdapter.getDefaultAdapter() != null ? true : false;
    }

    /**
     * 21
     * 当前 Android 设备的 bluetooth 是否已经开启
     * 22
     * <p/>
     * 23
     *
     * @return true：Bluetooth 已经开启 false：Bluetooth 未开启
     * 24
     */
    public static boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();

        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    /**
     * 39
     * 强制开启当前 Android 设备的 Bluetooth
     * 40
     * <p/>
     * 41
     *
     * @return true：强制打开 Bluetooth　成功　false：强制打开 Bluetooth 失败
     * 42
     */
    public static boolean turnOnBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();

        if (bluetoothAdapter != null) {
            return bluetoothAdapter.enable();
        }
        return false;
    }


}
