package com.goldentime.syon.goldentime.base;

import com.goldentime.syon.goldentime.classpackage.ClassModel;
import com.goldentime.syon.goldentime.lesson.LessonModel;
import com.goldentime.syon.goldentime.question.QuestionModel;
import com.goldentime.syon.goldentime.series.SeriesModel;
import com.goldentime.syon.goldentime.welcome.BooksDetails;
import com.goldentime.syon.goldentime.login.LoginResponseModel;
import com.goldentime.syon.goldentime.registration.RegistrationModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

/*
    @GET("TchrTeacherPersonalInfo")
    Call<Example> Login(@Query("lEmpId") String lEmpId, @Query("sSchCode") String sSchCode);

    @FormUrlEncoded
    @POST("TchrTeacherLogin")
    Call<LoginBean> getLoginData(@Field("user") String user, @Field("pwd") String pwd, @Field("logintype") String logintype, @Field("schoolcode") String schoolcode, @Field("deviceid") String deviceid, @Field("tokenno") String tokenno);


    @FormUrlEncoded
    @POST("TchrStudMarksEntry")
    Call<MarkBean> getMarksData(@Field("empid") String empid, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);
*/

    @FormUrlEncoded
    @POST("RegisterUser")
    Call<RegistrationModel> getRegistration(@Field("userid") String userid, @Field("userpwd") String userpwd, @Field("tokenno") String tokenno ,@Field("username") String username,@Field("email") String email,@Field("mobile") String mobile,@Field("usertype") String usertype,@Field("gender") String gender,@Field("dob") String dob,@Field("country") String country,@Field("state") String state,@Field("city") String city,@Field("board") String board,@Field("school") String school);

    @FormUrlEncoded
    @POST("UserLogin")
    Call<LoginResponseModel> getLoginDetails(@Field("user") String user, @Field("pwd") String pwd, @Field("tokenno") String tokenno);

    @GET("SubjectList")
    Call<BooksDetails> getSubjectList();
    @GET("SeriesList")
    Call<SeriesModel> getSeriesList(@Query("lSubId") int lSubId);
    @GET("ClassList")
    Call<ClassModel> getClassList(@Query("lSeriesId") int lSeriesId);
    @GET("ChapterList")
    Call<LessonModel> getLessonList(@Query("lClassId") int lClassId);
    @GET("SearchSeries")
    Call<ClassModel> getSearchList(@Query("sSearchText") String sSearchText);
    @GET("QuestionAnswer")
    Call<QuestionModel> getQuestionList(@Query("lChapterId") int lChapterId,@Query("nQuesType") int nQuesType);

    /*@GET("QuestionChoice")
    Call<AnswerModel> getAnswerList(@Query("QId") int QId);
*/
}
