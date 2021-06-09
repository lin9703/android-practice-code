package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    // 초기화 블록
    // 클래스 인스턴스 시 자동 생성
    init {
        Log.d(TAG, "ViewModel instance created")
    }

    // ViewModel 인스턴스 소멸 전 호출
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destryoed")
    }
}