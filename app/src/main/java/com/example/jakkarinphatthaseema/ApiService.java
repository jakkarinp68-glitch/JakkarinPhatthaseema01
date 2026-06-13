package com.example.jakkarinphatthaseema;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * ApiService คือ Interface สำหรับกำหนด HTTP Operations ที่ใช้ติดต่อกับ Network API (Backend)
 * โดยใช้ไลบรารี Retrofit
 */
public interface ApiService {
    
    /**
     * @GET("posts") คือการระบุว่าเราจะส่ง HTTP GET Request ไปยัง Endpoint ชื่อ "posts"
     * Call<List<TextNote>> คือการบอกว่าผลลัพธ์ที่ได้จาก Server จะเป็น List ของวัตถุ TextNote
     * ซึ่ง Retrofit จะแปลง JSON ที่ได้จาก Server ให้เป็น Java Object อัตโนมัติ (โดยใช้ Gson)
     */
    @GET("posts")
    Call<List<TextNote>> getTextNote();
}
