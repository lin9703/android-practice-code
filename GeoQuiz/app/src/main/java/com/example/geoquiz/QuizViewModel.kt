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

    var currentIndex = 0

    private val questionBank = listOf(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true)
    )

    // 문제의 정답 반환
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    // 문제의 텍스트 반환
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    // currentIndex 초기
    fun initCurrentIndex(index: Int) {
        if (index != currentIndex) {
            currentIndex = index
        }
    }

    // 다음 문제로 넘어가는 함수
    fun moveToNext() {
        currentIndex = ++currentIndex % questionBank.size
    }
}