# Job
作為Spring Boot一般測試用途

啟動專案後，可執行的測試如下：
一、瀏覽器輸入網址： "http://localhost:8080/" ；然後輸入帳號"user1"、密碼"111"；即可看到除了"RESTful API"外的成果

二、API測試：curl -i -X POST \
   -H "Authorization:Basic dXNlcjE6MTEx" \
   -H "Content-Type:application/json" \
   -d \
'{
  "v1": "v1-",
  "v2": 2
}' \
 'http://localhost:8080/search'

程式內容說明
1. Spring Data JPA
src\main\resources\application.properties=>設定H2資料庫
src\main\java\com\show\job\entity\Message.java=>假裝資料庫中，已經有一個資料表"ExchangeMessage"，記錄內部通訊往來
src\main\java\com\show\job\repository\MessageRepository.java=>查詢JPA"Message"的工具
src\main\java\com\show\job\StarterRunner.java=>因為是H2資料庫，所以每次啟動Java程式時，塞入一些假的測試資料
src\main\java\com\show\job\controller\FirstController.java=>利用JPA顯示測試結果頁面的Controller，主要看"index"、"getFullMsg"兩個方法
src\main\resources\templates\index.html=>顯示測試結果頁面，並用Ajax動態取得單筆的完整資料內容

2. RESTful API
src\main\java\com\show\job\rest\RestController.java=>測試簡單的RESTful API

3. Spring Bean
src\main\java\com\show\job\service\DirectService.java=>測試直接加上"@Component"，來產生Spring Been
src\main\java\com\show\job\service\ContextService.java=>準備測試在"ServiceConfig.java"，來產生Spring Been
src\main\java\com\show\job\ServiceConfig.java=>測試在"ServiceConfig.java"，來產生Spring Been

4. Spring AOP
src\main\java\com\show\job\entity\Count.java=>假裝資料庫中，已經有一個資料表"Request Count"，記錄收到多少次Request
src\main\java\com\show\job\repository\CountRepository.java=>查詢JPA"Count"的工具
src\main\java\com\show\job\aop\CountAop.java=>記錄總共收到多少次Request，並通知顯示測試結果頁面，更新資料
src\main\java\com\show\job\controller\FirstController.java=>利用JPA顯示測試結果頁面的Controller，主要看"getCount"方法
src\main\resources\templates\index.html=>顯示收到多少次Request結果頁面，並用Server-Sent Event動態更新內容
src\main\java\com\show\job\service\SseService.java=>用來處理所有Server-Sent Event，相關的事務

6. 其他
src\main\java\com\show\job\SecurityConfig.java=>基礎的安全設定，為了簡單通過Spring Security，所以設定極為簡單
