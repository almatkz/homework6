package kz.jusan.homework6

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

const val CORRECT_PIN = "1567"
const val PIN_LENGTH = 4
const val KEY_PIN = "pin"

class MainActivity : AppCompatActivity() {

    private var pinText = ""
    private var pinTextOnSave = ""
    lateinit var tvPin: TextView
    lateinit var animShake: Animation
    lateinit var vibrator: Vibrator

    var pinTextColor: Int = Color.BLACK
    var errorColor: Int = Color.BLACK
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVibrationAndShake()
        initColors()
        initTvPin()
        initNumButtons()
        initBackspaceButton()
        initOkButton()
    }

    private fun initVibrationAndShake() {
        animShake = AnimationUtils.loadAnimation(this, R.anim.shake)
        val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibrator = vibratorManager.defaultVibrator
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        pinText = savedInstanceState.getString(KEY_PIN) ?: ""
        updatePinTextView()
        checkIfPinIsCorrect()
    }

    private fun initColors() {
        pinTextColor = ContextCompat.getColor(this, R.color.color_primary)
        errorColor = ContextCompat.getColor(this, R.color.error)
    }

    private fun initTvPin() {
        tvPin = findViewById(R.id.tv_pin)
    }

    private fun initBackspaceButton() {
        val btnBackspace: Button = findViewById(R.id.btn_backspace)
        btnBackspace.setOnClickListener {
            pinText = pinText.dropLast(1)
            updatePinTextView()
        }
        btnBackspace.setOnLongClickListener() {
            pinText = ""
            updatePinTextView()
            true
        }
    }

    private fun initOkButton() {
        val btnOk: Button = findViewById(R.id.btn_ok)
        btnOk.setOnClickListener {
            checkIfPinIsCorrect()
            pinTextOnSave = pinText
        }
    }

    private fun checkIfPinIsCorrect() {
        if (pinText.equals(CORRECT_PIN)) {
            Toast.makeText(this, R.string.pin_is_correct, Toast.LENGTH_LONG).show()
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        } else {
            tvPin.setTextColor(errorColor)
            tvPin.startAnimation(animShake)
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    500, VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        }
    }

    private fun initNumButtons() {
        val btnOne: Button = findViewById(R.id.btn_one)
        btnOne.setOnClickListener(this::onNumButtonClick)

        val btnTwo: Button = findViewById(R.id.btn_two)
        btnTwo.setOnClickListener(this::onNumButtonClick)

        val btnThree: Button = findViewById(R.id.btn_three)
        btnThree.setOnClickListener(this::onNumButtonClick)

        val btnFour: Button = findViewById(R.id.btn_four)
        btnFour.setOnClickListener(this::onNumButtonClick)

        val btnFive: Button = findViewById(R.id.btn_five)
        btnFive.setOnClickListener(this::onNumButtonClick)

        val btnSix: Button = findViewById(R.id.btn_six)
        btnSix.setOnClickListener(this::onNumButtonClick)

        val btnSeven: Button = findViewById(R.id.btn_seven)
        btnSeven.setOnClickListener(this::onNumButtonClick)

        val btnEight: Button = findViewById(R.id.btn_eight)
        btnEight.setOnClickListener(this::onNumButtonClick)

        val btnNine: Button = findViewById(R.id.btn_nine)
        btnNine.setOnClickListener(this::onNumButtonClick)

        val btnZero: Button = findViewById(R.id.btn_zero)
        btnZero.setOnClickListener(this::onNumButtonClick)
    }

    private fun onNumButtonClick(view: View) {
        if (view !is Button) {
            return
        }
        val clickedNum = view.text
        pinText += clickedNum

        updatePinTextView()
    }

    private fun updatePinTextView() {
        if (pinText.length > PIN_LENGTH) {
            pinText = pinText.substring(0, PIN_LENGTH)
        }
        tvPin.text = pinText
        tvPin.setTextColor(pinTextColor)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(KEY_PIN, pinTextOnSave)
        Log.e("Main Activity", "OnSaveInstance #1")
    }
}