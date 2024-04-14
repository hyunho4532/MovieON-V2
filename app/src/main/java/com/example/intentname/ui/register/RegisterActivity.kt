package com.example.intentname.ui.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.intentname.ui.MainActivity
import com.example.intentname.R
import com.example.intentname.ui.login.LoginActivity
import com.example.intentname.ui.register.address.SearchActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

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
            if (et_check_email?.text.toString() != "" || et_check_password?.text.toString() != "") {
                createUser(et_check_email?.text.toString(), et_check_password?.text.toString())
            } else {
                Toast.makeText(this, "이메일 또는 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        tv_login_click.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createUser(email: String, password: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
            this@RegisterActivity
        ) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@RegisterActivity, "회원가입 정상 처리되었습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                intent.putExtra("email", email);

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

    override fun onStart() {
        super.onStart()

        mainMovePage(mAuth?.currentUser)
    }

    private fun mainMovePage(firebaseUser: FirebaseUser?) {
        if (firebaseUser != null) {
            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
            finish()
        }
    }
}