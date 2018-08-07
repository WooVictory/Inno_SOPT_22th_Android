package woo.sopt22.seminar3.Data

/*FIXME
* 데이터 클래스 정의*/
data class KaKaoData(
        var profile : Int, // 채팅방 이미지
        var name : String, // 채팅방 이름
        var preView : String, // 미리보기
        var date : String // 날짜
)

/*FIXME
* 비교
* - 자바 Code
* public class KaKaoData{
*   int profile;
*   String name;
*   String preView;
*   String data;
*
*   kaKaoData(int profile, String name, String preView, String data){
*       this.profile = profile;
*       ...
*       ...
*       ...
*       }
*
*       여기는 getter/setter 존재
*
*   }
* */