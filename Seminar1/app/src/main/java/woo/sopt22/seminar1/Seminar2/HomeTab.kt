package woo.sopt22.seminar1.Seminar2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import woo.sopt22.seminar1.R

class HomeTab : Fragment() {

    // 함수 키워드 : fun
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { // 현재 반환형 : view 타입
        // 변수에 들어가는 값의 타입이 무엇인지에 따라서 변수의 타입이 결정된다.
        // 일단 선언하고 타입을 따로 지정하지 않아도 들어간 값에 따라서 타입이 자동으로 결정된다.
        val v = inflater.inflate(R.layout.fragment_home,container,false);
        return v;
    }
}

/* 자바
View view = inflater.inflate()....
자바의 경우 Type을 지정한다.
val a = 10 ->a는 정수
* */