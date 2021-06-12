package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

// 최상위 수준 속성
// 1. 특정 클래스의 인스턴스를 생성하지 않고 바로 사용하므로 앱이 실행되는 동안 속성값을 계속 보존해야 할 때 사용
// 2. 앱 전체에서 사용하는 상수를 정의할 대 유용
private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    // 장점 1. by lazy 키워드를 사용하여 val 속성으로 선언
    // -> 액티비티 인스턴스가 생성될 때 ViewModel 인스턴스 참조를 quizViewModel에 한 번만 저장
    // 장점 2. by lazy 키워드를 사용하면 최초로 quizViewModel이 사용될 때까지 초기화 늦출 수 있다.
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")

        // layout inflate해서 화면에 표출
        setContentView(R.layout.activity_main)


//        // QuizViewModel 인스턴스 연결
//        // ViewModelProvider는 ViewModel의 레즈스트리처럼 작동
//        // 1. 액티비티 인스턴스가 처음으로 QuizViewModel을 요청하면 새로운 QuizViewModel 인스터스 생성하고 반환
//        // 2. 장치 구성이 변경되어 새로 생성된 액티비티 인스턴스가 QuizViewModel을 요청하면 최초 생성되었던 인스턴스 반환
//        // 3. 액티비티 인스턴스 소멸 시 QuizViewModel 인스턴스도 메모리에서 제거
//        // 현재 액티비티와 연관된 ViewModelProvider 인스턴스 생성 및 반환
//        val provider: ViewModelProvider = ViewModelProvider(this)
//        // QuizViewModel 인스턴스 반환
//        val quizViewModel = provider.get(QuizViewModel::class.java)
//        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        // Bundle 객체에 저장된 값 있는지 확인 및 ViewModel의 변수에 값 입력
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        // View 객체로 inflate된 위젯 참조 얻기
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)


        // 리스너(이벤트에 응답하기 위해 생성하는 객체) 설정
        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener { view: View ->
            quizViewModel.moveToNext()
            updateQuestion()
        }


        // Question 설정
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")

        // currentIndex를 Bundle 객체에 저장
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(
            this,
            messageResId,
            Toast.LENGTH_SHORT
        ).show()
    }
}