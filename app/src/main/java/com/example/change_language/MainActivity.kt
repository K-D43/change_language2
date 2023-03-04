package com.example.change_language

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import androidx.appcompat.app.AlertDialog
import com.example.change_language.databinding.ActivityMainBinding
import org.intellij.lang.annotations.Language
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LoadLocale()

        binding.tvchangeLanguage.setOnClickListener({
            openDialogLanguageChange()
        })




    }

    private fun LoadLocale() {
        sharedPreferences=getSharedPreferences("Settings",Context.MODE_PRIVATE)
    }

    private fun openDialogLanguageChange() {
        val list = arrayOf("English", "Hindi")
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Select Language")
        alertDialog.setSingleChoiceItems(list, -1, DialogInterface.OnClickListener { dialog, i ->
            if (i == 0) {
                setLocale("en")
                dialog.dismiss()
                recreate()
            }else if (i == 1) {
                setLocale("hi")
                dialog.dismiss()
                recreate()
            }
        })
        alertDialog.setNegativeButton("Cancel",DialogInterface.OnClickListener{ dialog, which ->
            dialog.cancel()
        })
//        val mDialog=alertDialog.create()
            alertDialog.show()
    }

    private fun setLocale(language: String) {

        val local = Locale(language)
        Locale.setDefault(local)
        val config=Configuration()
        config.locale=local;
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        editor=getSharedPreferences("",Context.MODE_PRIVATE).edit()
        editor.putString("selected_language",language)
        editor.apply()

    }
}
