package com.csc.kt_wanandroid.http;

import com.csc.core.base.BaseConstant;
import com.orhanobut.logger.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class ApiRequest {
    private static ApiRequest instance;

    private Retrofit retrofit;

    private ApiRequest() {
        OkHttpClient client;
        NetworkInterceptor mNetworkInterceptor = new NetworkInterceptor();
        Dispatcher dispatcher = new Dispatcher(Executors.newFixedThreadPool(20));
        dispatcher.setMaxRequests(20);
        dispatcher.setMaxRequestsPerHost(1);
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .addInterceptor(mNetworkInterceptor)
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new SaveCookiesInterceptor())
                .addInterceptor(logInterceptor)
                .retryOnConnectionFailure(true)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseConstant.HOST)
                .addConverterFactory(NobodyConverterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .client(client)
                .build();
    }

    private static ApiRequest getInstance() {
        if (instance == null) {
            instance = new ApiRequest();
        }
        return instance;
    }

    public static <T> T create(Class<T> service) {
        return getInstance().retrofit.create(service);
    }

    private class HttpLogger implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            // 请求或者响应开始s
            if (message.startsWith("--> POST") || message.startsWith("--> GET") || message.startsWith("--> DELETE") || message.startsWith("--> PUT")) {
                mMessage.setLength(0);
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if ((message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))) {
                message = JsonUtil.formatJson(message);
            }
            mMessage.append(message.concat("\n"));
            // 请求或者响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                Logger.e(mMessage.toString());
            }
        }
    }
}
