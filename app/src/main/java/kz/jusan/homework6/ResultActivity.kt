package kz.jusan.homework6

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initButtons()
    }

    private fun initButtons() {
        initShareButton()
        initSendViaEmailButton()
        initCallButton()
        initCameraButton()
    }

    private fun initShareButton() {
        val btnShare: Button = findViewById(R.id.btn_share)
        btnShare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Here is my profile!"
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }

    private fun initSendViaEmailButton() {
        val btnSendViaEmail: Button = findViewById(R.id.btn_send_via_email)
        btnSendViaEmail.setOnClickListener {
            val sendEmailSelectorIntent = Intent(Intent.ACTION_SENDTO)
            sendEmailSelectorIntent.data = Uri.parse("mailto:")

            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Here is my profile!")
            emailIntent.selector = sendEmailSelectorIntent
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
    }

    private fun initCallButton() {
        val btnCall: Button = findViewById(R.id.btn_call)
        btnCall.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+77010010101"))
            startActivity(callIntent)
        }
    }

    private fun initCameraButton() {
        val btnCamera: Button = findViewById(R.id.btn_camera)
        btnCamera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(cameraIntent)
        }
    }
}