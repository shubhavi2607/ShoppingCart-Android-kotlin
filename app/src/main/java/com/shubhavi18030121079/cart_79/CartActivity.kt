package com.shubhavi18030121079.cart_79

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.indexOf
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class CartActivity : AppCompatActivity() {

    lateinit var spCategory: Spinner
    lateinit var c1: CheckBox
    lateinit var c2: CheckBox
    lateinit var c3: CheckBox
    lateinit var txtp1 : TextView
    lateinit var txtp2 : TextView
    lateinit var txtp3 : TextView
    lateinit var txtusername : TextView
    lateinit var et1 : EditText
    lateinit var et2 : EditText
    lateinit var et3 : EditText
    lateinit var btnAdd : Button
    var pos = ""
    var item1 = ""
    var item2 = ""
    var item3 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        spCategory = findViewById(R.id.spCategory)
        c1 = findViewById(R.id.c1)
        c2 = findViewById(R.id.c2)
        c3 = findViewById(R.id.c3)
        txtp1 = findViewById(R.id.txtp1)
        txtp2 = findViewById(R.id.txtp2)
        txtp3 = findViewById(R.id.txtp3)
        txtusername = findViewById(R.id.txtusername)
        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        et3 = findViewById(R.id.et3)
        btnAdd = findViewById(R.id.btnAdd)

        //intents

        val username = intent.getStringExtra("username")
        val loginMsg = intent.getStringExtra("loginMsg")

        /*if (loginMsg.isNullOrEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
            Toast.makeText(this, "Login First", Toast.LENGTH_SHORT).show()
        }*/


        //heading
        txtusername.text = username

        // Category - DropDown
        val arrayListCate = arrayListOf("Utensils", "Stationary", "Phones")
        val arrayListCount = arrayListOf(0, 1, 2, 3, 4, 5)
        val arrayAdapterCate = ArrayAdapter(applicationContext, android.R.layout.simple_expandable_list_item_1, arrayListCate)
        spCategory.adapter = arrayAdapterCate


        spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                pos = p0?.getItemAtPosition(p2).toString()
                if (p2 == 0) {
                    c1.text = "Spoon"
                    c2.text = "Plate"
                    c3.text = "Bowl"
                    txtp1.text = "25"
                    txtp2.text = "50"
                    txtp3.text = "30"
                }

                if (p2 == 1) {
                    c1.text = "Pen"
                    c2.text = "Pencil"
                    c3.text = "Notebook"
                    txtp1.text = "10"
                    txtp2.text = "5"
                    txtp3.text = "20"
                }


                if (p2 == 2) {
                    c1.text = "Poco"
                    c2.text = "Iphone"
                    c3.text = "Oneplus"
                    txtp1.text = "25000"
                    txtp2.text = "50000"
                    txtp3.text = "30000"
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        btnAdd.setOnClickListener(){
            if(c1.isChecked){
                spCategory.isEnabled = false
                if(et1.text.toString() == ""){
                    et1.setText("1")
                }
                item1 = c1.text.toString()+","+txtp1.text.toString()+","+et1.text.toString()
            }else{
                removeItem()
            }
            if(c2.isChecked){
                spCategory.isEnabled = false
                if(et2.text.toString() == ""){
                    et2.setText("1")
                }
                item2 = c2.text.toString()+","+txtp2.text.toString()+","+et2.text.toString()
            }else{
                removeItem()
            }
            if(c3.isChecked){
                spCategory.isEnabled = false
                if(et3.text.toString() == ""){
                    et3.setText("1")
                }
                item3 = c3.text.toString()+","+txtp3.text.toString()+","+et3.text.toString()
            }else{
                removeItem()
            }

            if(!c1.isChecked && !c2.isChecked && !c3.isChecked){
                spCategory.isEnabled = true
            }

            Toast.makeText(this, " $item1\n$item2\n$item3", Toast.LENGTH_SHORT).show()
        }
    }

    fun removeItem(){
        if(!c1.isChecked){
            item1 = ""
        }
        if(!c2.isChecked){
            item2 = ""
        }
        if(!c3.isChecked){
            item3 = ""
        }
    }

    fun btnClickProceed(v: View){
        val i = Intent(this, DashboardActivity::class.java)
        i.putExtra("ITEM1",item1)
        i.putExtra("ITEM2",item2)
        i.putExtra("ITEM3",item3)
        startActivity(i)
        }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.home -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.login -> {
                startActivity(Intent(this, LoginActivity::class.java))
                true
            }
            R.id.cart -> true
            R.id.dashboard -> {
                startActivity(Intent(this, DashboardActivity::class.java))
                true
            }
            R.id.exit -> {
                moveTaskToBack(true)
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(1)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}