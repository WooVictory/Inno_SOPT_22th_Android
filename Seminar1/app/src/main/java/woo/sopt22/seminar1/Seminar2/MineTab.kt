package woo.sopt22.seminar1.Seminar2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import woo.sopt22.seminar1.R

class MineTab : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_mine,container,false);
        return v;
    }
}

/*
a : Int?의 의미 : a라는 변수는 Null 값을 가질 수 있다.
? [Nullable] 타입에서 에러가 난다면?
1. ? 를 지워주는 방법
2. 인자로 들어온 변수에 !! 를 붙이는 방법 (!! : Null을 허용하지 않겠다.)
* */