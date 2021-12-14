package com.csc.kt_wanandroid.service

import com.csc.core.data.UserBean
import com.csc.core.model.BaseNetModel
import com.csc.kt_wanandroid.bean.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * @author chenshichun
 * 创建日期：2021/12/3
 * 描述：
 *
 */
interface ApiService {
    /*
    * 注册
    * */
    @POST("/user/register")
    fun register(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("repassword") repassword: String
    ): Call<BaseNetModel<Any>>

    /**
     * 登录
     */
    @POST("/user/login")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<BaseNetModel<UserBean>>

    /*
    * 首页banner
    * */
    @GET("/banner/json")
    fun getBannerData(): Call<BaseNetModel<ArrayList<BannerBean>>>

    /*
    * 首页置顶文章
    * */
    @GET("/article/top/json")
    fun getArticleTopListData(): Call<BaseNetModel<ArrayList<ArticleBean>>>

    /*
    * 首页文章
    * */
    @GET("/article/list/{pageNum}/json")
    fun getArticleListData(@Path("pageNum") pageNum: Int): Call<BaseNetModel<ArticleListBean>>

    /*
    * 收藏站内文章
    * */
    @POST("/lg/collect/{id}/json")
    fun collectArt(@Path("id") id: String): Call<BaseNetModel<Any>>

    /*
    * 取消收藏
    * */
    @POST("/lg/uncollect_originId/{id}/json")
    fun uncollectArt(@Path("id") id: String): Call<BaseNetModel<Any>>

    /*
    * 体系数据
    * */
    @GET("/tree/json")
    fun getSystemData(): Call<BaseNetModel<SystemBean>>

    /*
    * 体系列表
    * */
    @GET("/article/list/{pageNum}/json")
    fun getSystemListData(
        @Path("pageNum") pageNum: Int,
        @Query("cid") cid: String
    ): Call<BaseNetModel<SystemListBean>>

    /*
    * 项目分类
    * */
    @GET("/project/tree/json")
    fun getProjectTreeData(): Call<BaseNetModel<ProjectTreeBean>>

    /*
    * 项目分类列表
    * */
    @GET("/project/list/{pageNum}/json")
    fun getProjectListData(
        @Path("pageNum") pageNum: Int,
        @Query("cid") cid: String
    ): Call<BaseNetModel<ArticleListBean>>

    /*
    * 个人信息
    * */
    @GET("/user/lg/userinfo/json")
    fun getInformation(): Call<BaseNetModel<UserInfoBean>>

    /*
    * 收藏文章列表
    * */
    @GET("/lg/collect/list/{pageNum}/json")
    fun getCollectData(@Path("pageNum") pageNum: Int): Call<BaseNetModel<ArticleListBean>>

    /*
    * 退出登录
    * */
    @GET("/user/logout/json")
    fun loginOut(): Call<BaseNetModel<Any>>

}