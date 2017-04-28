package com.example.administrator.myapplication.webview;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 网页读取手机联系人并显示出来
 */
public class WebviewContactActivity extends BaseActivity {

    private static final int REQUEST_CALL_PHONE = 3003;
    @BindView(R.id.web_contract)
    WebView web_contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebSettings webSettings = web_contract.getSettings();
        //①设置WebView允许调用js
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");

        web_contract.addJavascriptInterface(new SharpJs(), "sharp");
        web_contract.loadUrl("file:///android_asset/demo3.html");
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_webview_contact);
    }

    /**
     * 自定义一个Js的业务类,传递给JS的对象就是这个,调用时直接javascript:sharp.contactlist()
     */
    public class SharpJs {

        @JavascriptInterface
        public void contactlist() {
            try {
                System.out.println("contactlist()方法执行了！");
                String json = buildJson(getContacts());
                web_contract.loadUrl("javascript:show('" + json + "')");
            } catch (Exception e) {
                System.out.println("设置数据失败" + e);
            }
        }

        @JavascriptInterface
        public void call(String phone) {
            System.out.println("call()方法执行了！");
            Intent it = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int callPhonePermission = ContextCompat.checkSelfPermission(WebviewContactActivity.this
                        , Manifest.permission.CALL_PHONE);
                if (callPhonePermission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(WebviewContactActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                    return;
                } else {
                    startActivity(it);
                }
            } else {
                startActivity(it);
            }

        }

    }

    //将获取到的联系人集合写入到JsonObject对象中,再添加到JsonArray数组中
    public String buildJson(List<Contact> contacts) throws Exception {
        JSONArray array = new JSONArray();
        for (Contact contact : contacts) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", contact.getId());
            jsonObject.put("name", contact.getName());
            jsonObject.put("phone", contact.getPhone());
            array.put(jsonObject);
        }
        return array.toString();
    }

    //定义一个获取联系人的方法,返回的是List<Contact>的数据
    public List<Contact> getContacts() {
        List<Contact> Contacts = new ArrayList<Contact>();
        //①查询raw_contacts表获得联系人的id
        ContentResolver resolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //查询联系人数据
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            //获取联系人姓名,手机号码
            contact.setId(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            contact.setPhone(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            Contacts.add(contact);
        }
        cursor.close();
        return Contacts;
    }
}
