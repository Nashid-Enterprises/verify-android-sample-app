package com.example.sdksample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sdksample.ui.theme.SDKSampleTheme
import com.kyc.nashidmrz.NashidSDK
import com.kyc.nashidmrz.SDKIntListener
import com.kyc.nashidmrz.mrtd2.activity.CameraXLivePreviewActivity
import com.kyc.nashidmrz.mrtd2.activity.SelectDocumentActivity
import com.kyc.nashidmrz.mrtd2.resultcallback.ResultListener
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SDKSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Nashid SDK")
                }
            }
        }
        initNashidSDK(this@MainActivity)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = " $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SDKSampleTheme {
        Greeting("Android")
    }
}

private fun initNashidSDK(mainActivity: MainActivity) {

    val sdkInstance = NashidSDK.getInstance()
    val sdkIntListener= object : SDKIntListener {
        override fun onInitFail() {
            Log.d("MainActivity", "onInitFail: ")
        }

        override fun onInitSuccess() {
            Log.d("MainActivity", "onInitSuccess: ")
//            You can call this method anywhere you like. For instance,
//            if you wish to invoke it on a button click,
//            you need to include this line within the button's click listener.
//            However, it's crucial to ensure that the NashidSDK has been successfully initialized before calling this method.
//            Otherwise, the method won't function as intended.
            NashidSDK.getInstance().documentScan(mainActivity)
        }

    }
    sdkInstance.init(
        "MIO7NIJsfkJsE8HJJOB1Ff3xpysU7k1HE2NhslTCKA2qaIfIS9",
        "MD7ECJ0K48AJP6S",
        "https://dashboard.test.projectnid.com/api/",
        "sotopo5208@ikumaru.com",
        sdkIntListener,
    )

    val resultListener = object : ResultListener {

        override fun onResultData(jsonObject: JSONObject?, scannedDocType: String?) {
            Log.d("MainActivity", "onResultData: " + jsonObject)
        }

        override fun onFailure() {
            Log.d("MainActivity", "onFailure: ")

        }
    }
// Check if resultListener is not null
    sdkInstance.setResultListener(resultListener)
}