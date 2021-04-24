package ru.examples.bilimdonuz.ui.splash

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import ru.examples.bilimdonuz.MainActivity
import ru.examples.bilimdonuz.R
import ru.examples.bilimdonuz.common.getString
import ru.examples.bilimdonuz.databinding.DialogViewBinding
import ru.examples.bilimdonuz.model.SaveModel

class SplashActivity : AppCompatActivity() {

    private val splashViewModel:ViewModelSplash by viewModels()
    private var dialog:AlertDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

         splashViewModel.isConrrent()
         splashViewModel.isCorrect.observe(this, Observer {
             if (it){
                 internetController()
             }else{
                 showDialog()
             }
         })

    }

    private fun showDialog() {
        val view= DialogViewBinding.inflate(layoutInflater)
         dialog=AlertDialog.Builder(this)
                .setView(view.root)
                .create()
                dialog!!.show()

        view.btSave.setOnClickListener {
            internetController()
            val surname=view.gbSurnameDialog.getString()
            val name=view.gbCodeDialog.getString()
            splashViewModel.onSavePreferense(name,surname)
            dialog!!.hide()
        }
    }

    private fun internetController() {
        splashViewModel.ControlInternetVM()
        splashViewModel.currentControlVm.observe(this, Observer {
            when(it){
                is InternetControl.offline->offlineInternet()
                is InternetControl.online->onlineInternet()
            }
        })
    }

    private fun onlineInternet() {
        Intent(this,MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }

    private fun offlineInternet() {
        Toast.makeText(this, "Internet bilan aloqa mavjud emas", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}