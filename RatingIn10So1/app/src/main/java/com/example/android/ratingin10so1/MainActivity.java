package com.example.android.ratingin10so1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.ratingin10so1.R.id.radioGender;


public class MainActivity extends AppCompatActivity {

    String DataParseUrl = "http://forandroid3000.esy.es/rating_q_services1.php" ;
    TextView q_reception_employee,q_game_operator,q_cafe_employee,s_reception_employee,s_game_operator,s_cafe_employee,ac,center_cleanliness,toilets,reception_area,games_area,img_sound_q,name,city,email,age,mobile,comments;
    CheckBox karting,laser_tag,bowling,billiards,climbing,archery,video_games,net_cafe;
    RadioGroup gender;
    String GetQReception,GetQGame,GetQCafe,Get_s_reception_employee,Get_s_game_operator,Get_s_cafe_employee,Get_ac,Get_center_cleanliness,Get_toilets,Get_reception_area,Get_games_area,Get_img_sound_q,Get_karting,Get_laser_tag,Get_bowling,Get_billiards,Get_climbing,Get_archery,Get_video_games,Get_net_cafe,Get_gender,Get_name,Get_city,Get_email,Get_age,Get_mobile,Get_comments;
    Button save ;
    Boolean CheckEditText ;
    private RatingBar ratingBar,ratingBar2,ratingBar3,ratingBar4,ratingBar5,ratingBar6,ratingBar7,ratingBar8,ratingBar9,ratingBar10,ratingBar11,ratingBar12;
    private TextView txtRatingValue,txtRatingValue2,txtRatingValue3,txtRatingValue4,txtRatingValue5,txtRatingValue6,txtRatingValue7,txtRatingValue8,txtRatingValue9,txtRatingValue10,txtRatingValue11,txtRatingValue12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnRatingBar();

        q_reception_employee=(TextView) findViewById(R.id.txtRatingValue);
        q_game_operator=(TextView) findViewById(R.id.txtRatingValue2);
        q_cafe_employee=(TextView) findViewById(R.id.txtRatingValue3);

        s_reception_employee=(TextView) findViewById(R.id.txtRatingValue4);
        s_game_operator=(TextView) findViewById(R.id.txtRatingValue5);
        s_cafe_employee=(TextView) findViewById(R.id.txtRatingValue6);
        ac=(TextView) findViewById(R.id.txtRatingValue7);
        center_cleanliness=(TextView) findViewById(R.id.txtRatingValue8);
        toilets=(TextView) findViewById(R.id.txtRatingValue9);
        reception_area=(TextView) findViewById(R.id.txtRatingValue10);
        games_area=(TextView) findViewById(R.id.txtRatingValue11);
        img_sound_q=(TextView) findViewById(R.id.txtRatingValue12);

        karting=(CheckBox) findViewById(R.id.karting);
        laser_tag=(CheckBox) findViewById(R.id.lasertag);
        bowling=(CheckBox) findViewById(R.id.bowling);
        billiards=(CheckBox) findViewById(R.id.billiards);
        climbing=(CheckBox) findViewById(R.id.climbing);
        archery=(CheckBox) findViewById(R.id.archery);
        video_games=(CheckBox) findViewById(R.id.videogames);
        net_cafe=(CheckBox) findViewById(R.id.netcafe);
        gender=(RadioGroup) findViewById(radioGender);

        name=(EditText) findViewById(R.id.editText_name);
        city=(EditText) findViewById(R.id.editText_city);
        email=(EditText) findViewById(R.id.editText_email);
        age=(EditText) findViewById(R.id.editText_age);
        mobile=(EditText) findViewById(R.id.editText_mobile);
        comments=(EditText) findViewById(R.id.editText_comment);


        save = (Button) findViewById(R.id.submit);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                GetCheckEditTextIsEmptyOrNot();
/*                GetQReception = q_reception_employee.getText().toString();
                GetQGame = q_game_operator.getText().toString();
                GetQCafe = q_cafe_employee.getText().toString();*/
                if (CheckEditText) {
                    SendDataToServer(GetQReception, GetQGame,GetQCafe,Get_s_reception_employee,Get_s_game_operator,Get_s_cafe_employee,Get_ac,Get_center_cleanliness,Get_toilets,Get_reception_area,Get_games_area,Get_img_sound_q,Get_karting,Get_laser_tag,Get_bowling,Get_billiards,Get_climbing,Get_archery,Get_video_games,Get_net_cafe,Get_gender,Get_name,Get_city,Get_email,Get_age,Get_mobile,Get_comments);
                    Intent intent = new Intent(MainActivity.this, EndSurvey.class);
                    startActivity(intent);
                }
                else {

                    Toast.makeText(MainActivity.this, "Please fill name and mobile form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    public void GetCheckEditTextIsEmptyOrNot(){

        GetQReception = q_reception_employee.getText().toString();
        GetQGame = q_game_operator.getText().toString();
        GetQCafe = q_cafe_employee.getText().toString();
        Get_s_reception_employee=s_reception_employee.getText().toString();
        Get_s_game_operator=s_game_operator.getText().toString();
        Get_s_cafe_employee=s_cafe_employee.getText().toString();
        Get_ac=ac.getText().toString();
        Get_center_cleanliness=center_cleanliness.getText().toString();
        Get_toilets=toilets.getText().toString();
        Get_reception_area=reception_area.getText().toString();
        Get_games_area=games_area.getText().toString();
        Get_img_sound_q=img_sound_q.getText().toString();
        Get_name=name.getText().toString();
        Get_city=city.getText().toString();
        Get_email=email.getText().toString();
        Get_age=age.getText().toString();
        Get_mobile=mobile.getText().toString();
        Get_comments=comments.getText().toString();
        Get_karting=Boolean.toString(karting.isChecked());
        Get_laser_tag=Boolean.toString(laser_tag.isChecked());
        Get_bowling=Boolean.toString(bowling.isChecked());
        Get_billiards=Boolean.toString(billiards.isChecked());
        Get_climbing=Boolean.toString(climbing.isChecked());
        Get_archery=Boolean.toString(archery.isChecked());
        Get_video_games=Boolean.toString(video_games.isChecked());
        Get_net_cafe=Boolean.toString(net_cafe.isChecked());

        int selectedId = gender .getCheckedRadioButtonId();
        // find the radio button by returned id
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        Get_gender=radioButton.getText().toString();

        if(TextUtils.isEmpty(Get_name) || TextUtils.isEmpty(Get_mobile))
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }
    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        txtRatingValue2 = (TextView) findViewById(R.id.txtRatingValue2);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);
        txtRatingValue3 = (TextView) findViewById(R.id.txtRatingValue3);
        ratingBar4 = (RatingBar) findViewById(R.id.ratingBar4);
        txtRatingValue4 = (TextView) findViewById(R.id.txtRatingValue4);
        ratingBar5 = (RatingBar) findViewById(R.id.ratingBar5);
        txtRatingValue5 = (TextView) findViewById(R.id.txtRatingValue5);
        ratingBar6 = (RatingBar) findViewById(R.id.ratingBar6);
        txtRatingValue6 = (TextView) findViewById(R.id.txtRatingValue6);
        ratingBar7 = (RatingBar) findViewById(R.id.ratingBar7);
        txtRatingValue7 = (TextView) findViewById(R.id.txtRatingValue7);
        ratingBar8 = (RatingBar) findViewById(R.id.ratingBar8);
        txtRatingValue8 = (TextView) findViewById(R.id.txtRatingValue8);
        ratingBar9 = (RatingBar) findViewById(R.id.ratingBar9);
        txtRatingValue9 = (TextView) findViewById(R.id.txtRatingValue9);
        ratingBar10 = (RatingBar) findViewById(R.id.ratingBar10);
        txtRatingValue10 = (TextView) findViewById(R.id.txtRatingValue10);
        ratingBar11 = (RatingBar) findViewById(R.id.ratingBar11);
        txtRatingValue11 = (TextView) findViewById(R.id.txtRatingValue11);
        ratingBar12 = (RatingBar) findViewById(R.id.ratingBar12);
        txtRatingValue12 = (TextView) findViewById(R.id.txtRatingValue12);
        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });
        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue2.setText(String.valueOf(rating));

            }
        });
        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue3.setText(String.valueOf(rating));

            }
        });
        ratingBar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue4.setText(String.valueOf(rating));

            }
        });
        ratingBar5.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue5.setText(String.valueOf(rating));

            }
        });
        ratingBar6.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue6.setText(String.valueOf(rating));

            }
        });
        ratingBar7.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue7.setText(String.valueOf(rating));

            }
        });
        ratingBar8.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue8.setText(String.valueOf(rating));

            }
        });
        ratingBar9.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue9.setText(String.valueOf(rating));

            }
        });
        ratingBar10.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue10.setText(String.valueOf(rating));

            }
        });
        ratingBar11.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue11.setText(String.valueOf(rating));

            }
        });
        ratingBar12.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue12.setText(String.valueOf(rating));

            }
        });
        // displaying yellow when the button pressed
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(0).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        LayerDrawable stars2 = (LayerDrawable) ratingBar2.getProgressDrawable();
        stars2.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);
        LayerDrawable stars3 = (LayerDrawable) ratingBar3.getProgressDrawable();
        stars3.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar4 = (RatingBar) findViewById(R.id.ratingBar4);
        LayerDrawable stars4 = (LayerDrawable) ratingBar4.getProgressDrawable();
        stars4.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar5 = (RatingBar) findViewById(R.id.ratingBar5);
        LayerDrawable stars5 = (LayerDrawable) ratingBar5.getProgressDrawable();
        stars5.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar6 = (RatingBar) findViewById(R.id.ratingBar6);
        LayerDrawable stars6 = (LayerDrawable) ratingBar6.getProgressDrawable();
        stars6.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar7 = (RatingBar) findViewById(R.id.ratingBar7);
        LayerDrawable stars7 = (LayerDrawable) ratingBar7.getProgressDrawable();
        stars7.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar8 = (RatingBar) findViewById(R.id.ratingBar8);
        LayerDrawable stars8 = (LayerDrawable) ratingBar8.getProgressDrawable();
        stars8.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar9 = (RatingBar) findViewById(R.id.ratingBar9);
        LayerDrawable stars9 = (LayerDrawable) ratingBar9.getProgressDrawable();
        stars9.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar10 = (RatingBar) findViewById(R.id.ratingBar10);
        LayerDrawable stars10 = (LayerDrawable) ratingBar10.getProgressDrawable();
        stars10.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar11 = (RatingBar) findViewById(R.id.ratingBar11);
        LayerDrawable stars11 = (LayerDrawable) ratingBar11.getProgressDrawable();
        stars11.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar12 = (RatingBar) findViewById(R.id.ratingBar12);
        LayerDrawable stars12 = (LayerDrawable) ratingBar12.getProgressDrawable();
        stars12.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
    }
    public void SendDataToServer(final String qReception, final String qGame, final String qCafe,final String qSReception, final String qSGame, final String qSCafe,final String qac, final String qcenter_cleanliness, final String qtoilets,final String qReceptionArea, final String qGameArea, final String qImg_Sound,final String qKarting, final String qLaserTag, final String qBowling,final String qBilliards, final String qClimbing, final String qArchery,final String qVideoGames, final String qNetCafe, final String qGender,final String qName,final String qCity, final String qEmail, final String qAge,final String qMobile,final String qComments){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String QReception = qReception ;
                String QGame = qGame ;
                String QCafe = qCafe;
                String QSReception = qSReception ;
                String QSGame = qSGame ;
                String QSCafe = qSCafe;
                String Qac = qac ;
                String QCenterCleanliness = qcenter_cleanliness ;
                String QToilets = qtoilets;
                String QReceptionArea = qReceptionArea ;
                String QGameArea = qGameArea ;
                String QImages_Sound = qImg_Sound;
                String QKarting = qKarting ;
                String QLaserTag = qLaserTag ;
                String QBowling = qBowling;
                String QBilliards = qBilliards ;
                String QClimbing = qClimbing ;
                String QArchery = qArchery;
                String QVideoGames = qVideoGames ;
                String QNetCafe = qNetCafe ;
                String QGender = qGender;
                String QName = qName ;
                String QCity = qCity ;
                String QEmail = qEmail;
                String QAge = qAge ;
                String QMobile = qMobile ;
                String QComments = qComments;



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("qReception", QReception));
                nameValuePairs.add(new BasicNameValuePair("qGame", QGame));
                nameValuePairs.add(new BasicNameValuePair("qCafe", QCafe));
                nameValuePairs.add(new BasicNameValuePair("qSReception", QSReception));
                nameValuePairs.add(new BasicNameValuePair("qSGame", QSGame));
                nameValuePairs.add(new BasicNameValuePair("qSCafe", QSCafe));
                nameValuePairs.add(new BasicNameValuePair("qac", Qac));
                nameValuePairs.add(new BasicNameValuePair("qcenter_cleanliness", QCenterCleanliness));
                nameValuePairs.add(new BasicNameValuePair("qtoilets", QToilets));
                nameValuePairs.add(new BasicNameValuePair("qReceptionArea", QReceptionArea));
                nameValuePairs.add(new BasicNameValuePair("qGameArea", QGameArea));
                nameValuePairs.add(new BasicNameValuePair("qImg_Sound", QImages_Sound));
                nameValuePairs.add(new BasicNameValuePair("qKarting", QKarting));
                nameValuePairs.add(new BasicNameValuePair("qLaserTag", QLaserTag));
                nameValuePairs.add(new BasicNameValuePair("qBowling", QBowling));
                nameValuePairs.add(new BasicNameValuePair("qBilliards", QBilliards));
                nameValuePairs.add(new BasicNameValuePair("qClimbing", QClimbing));
                nameValuePairs.add(new BasicNameValuePair("qArchery", QArchery));
                nameValuePairs.add(new BasicNameValuePair("qVideoGames", QVideoGames));
                nameValuePairs.add(new BasicNameValuePair("qNetCafe", QNetCafe));
                nameValuePairs.add(new BasicNameValuePair("qGender", QGender));
                nameValuePairs.add(new BasicNameValuePair("qName", QName));
                nameValuePairs.add(new BasicNameValuePair("qCity", QCity));
                nameValuePairs.add(new BasicNameValuePair("qEmail", QEmail));
                nameValuePairs.add(new BasicNameValuePair("qAge", QAge));
                nameValuePairs.add(new BasicNameValuePair("qMobile", QMobile));
                nameValuePairs.add(new BasicNameValuePair("qComments", QComments));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(DataParseUrl);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Submit Successfully";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(MainActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(qReception, qGame, qCafe,qSReception,qSGame, qSCafe,qac,qcenter_cleanliness,qtoilets,qReceptionArea,qGameArea,qImg_Sound,qKarting,qLaserTag,qBowling,qBilliards,qClimbing,qArchery,qVideoGames,qNetCafe,qGender,qName,qCity,qEmail,qAge,qMobile,qComments);
    }

}


