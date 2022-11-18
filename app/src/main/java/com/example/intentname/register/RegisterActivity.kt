package com.example.intentname.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.intentname.MainActivity
import com.example.intentname.R
import com.example.intentname.register.address.SearchActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register_check.*

class RegisterActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        et_address?.isFocusable = false

        mAuth = FirebaseAuth.getInstance()
        et_address?.setOnClickListener {
            val intent = Intent(this@RegisterActivity, SearchActivity::class.java)
            getSearchResult.launch(intent)
        }

        btn_register_check!!.setOnClickListener {
            if (et_email?.text.toString() != "" && et_password?.text.toString() != "") {
                createUser(et_email?.text.toString(), et_password?.text.toString())
            }
        }

        tv_intent.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
        })
    }

    private fun createUser(email: String, password: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
            this@RegisterActivity
        ) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@RegisterActivity, "회원가입 정상 처리되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@RegisterActivity, "회원가입 이상 에러", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val getSearchResult = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            if (result.data != null) {
                val data = result.data!!.getStringExtra("data")
                et_address!!.setText(data)
            }
        }
    }
}