package com.moyu.lyqdhgo.librarytestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.moyu.lyqdhgo.librarytestdemo.bean.Dog;
import com.orhanobut.logger.Logger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmResults;
import io.realm.internal.Table;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView textView;
    private TextView catRealm;
    private TextView updRealm;

    Dog dog;
    Dog theDog;
    Realm realm;
    RealmResults<Dog> findDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        catRealm = (TextView) findViewById(R.id.cat_realm);
        textView = (TextView) findViewById(R.id.textview);
        updRealm = (TextView) findViewById(R.id.upd_realm);
        catRealm.setOnClickListener(this);
        updRealm.setOnClickListener(this);
    }

    public void catRealm() {
        dog = new Dog();
        dog.setName("Friger");
        dog.setAge(2);

        RealmMigration migration = new RealmMigration() {
            @Override
            public long execute(Realm realm, long l) {
                Table table = realm.getTable(Dog.class);
                table.convertColumnToNotNullable(table.getColumnIndex("name"));
                return 1;
            }
        };
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .schemaVersion(1)
                .migration(migration)
                .build();
        realm = Realm.getInstance(config);
        findDog = realm.where(Dog.class).lessThan("age", 5).findAll();
        realm.beginTransaction();
        realm.copyToRealm(dog);
        realm.commitTransaction();
        Log.i(TAG, "FindDogs->" + findDog.size());

    }

    // Query and Update
    public void upDateRealm() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                theDog = realm.where(Dog.class).equalTo("age", 2).findFirst();
                theDog.setName("GeHAo");
            }
        }, new Realm.Transaction.Callback() {
            @Override
            public void onSuccess() {
                super.onSuccess();
                Logger.i("Dog Size->" + findDog.size());
                Logger.i("Dog Name->" + dog.getName());
                Logger.i("TheDog Name->" + theDog.getName());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cat_realm:
                catRealm();
                break;
            case R.id.upd_realm:
                upDateRealm();
                break;
            default:
                break;
        }
    }
}
