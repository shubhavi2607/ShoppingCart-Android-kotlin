package com.shubhavi18030121079.cart_79

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class DashboardActivity : AppCompatActivity() {

    lateinit var txtItem1 : TextView
    lateinit var txtItem2 : TextView
    lateinit var txtItem3 : TextView
    lateinit var txtItemTot1 : TextView
    lateinit var txtItemTot2 : TextView
    lateinit var txtItemTot3 : TextView
    lateinit var txtItemTot : TextView
    lateinit var btnPay : Button
    lateinit var txtMsg : TextView
    var first = ArrayList<String>()
    var second = ArrayList<String>()
    var third = ArrayList<String>()
    var itemtot = ArrayList<Int>()
    var total = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        txtItem1 = findViewById(R.id.txtItem1)
        txtItem2 = findViewById(R.id.txtItem2)
        txtItem3 = findViewById(R.id.txtItem3)
        txtItemTot1 = findViewById(R.id.txtItemTot1)
        txtItemTot2 = findViewById(R.id.txtItemTot2)
        txtItemTot3 = findViewById(R.id.txtItemTot3)
        txtItemTot = findViewById(R.id.txtItemTot)
        txtMsg = findViewById(R.id.txtMsg)
        btnPay = findViewById(R.id.btnPay)

        //intent
        val item1 = intent.getStringExtra("ITEM1")
        val item2 = intent.getStringExtra("ITEM2")
        val item3 = intent.getStringExtra("ITEM3")

        if(item1.isNullOrEmpty() && item2.isNullOrEmpty() && item3.isNullOrEmpty()){
            txtMsg.text = "Add Items to the Cart"
            txtItemTot.text = ""
            btnPay.isEnabled = false
        }else{
            txtItemTot.text = "Item Total"
            txtMsg.text = ""
            btnPay.isEnabled = true
        }

        if (item1.isNullOrEmpty()) {
            txtItem1.text = ""
            txtItemTot1.text = ""
        }else{
            first = item1.split(",") as ArrayList<String>
            itemtot.add(Integer.parseInt(first.get(1))*Integer.parseInt(first.get(2)))
            txtItem1.text = "I:\t\t"+first.get(0)+"\nP:\t\t"+first.get(1)+" Rs.\nQ:\t\t"+first.get(2)
            txtItemTot1.text = itemtot[0].toString()
        }
        if (item2.isNullOrEmpty()) {
            txtItem2.text = ""
            txtItemTot2.text = ""
        }else{
            second = item2.split(",") as ArrayList<String>
            itemtot.add(Integer.parseInt(second.get(1))*Integer.parseInt(second.get(2)))
            txtItem2.text = "I:\t\t"+second.get(0)+"\nP:\t\t"+second.get(1)+" Rs.\nQ:\t\t"+second.get(2)
            txtItemTot2.text = itemtot[1].toString()
        }
        if (item3.isNullOrEmpty()) {
            txtItem3.text = ""
            txtItemTot3.text = ""
        }else{
            third = item3.split(",") as ArrayList<String>
            itemtot.add(Integer.parseInt(third.get(1))*Integer.parseInt(third.get(2)))
            txtItem3.text = "I:\t\t"+third.get(0)+"\nP:\t\t"+third.get(1)+" Rs.\nQ:\t\t"+third.get(2)
            txtItemTot3.text = itemtot[2].toString()
        }

        total = itemtot.sum()
        btnPay.text = "Pay $total"

        btnPay.setOnClickListener(){
            Toast.makeText(this,"Paid $total", Toast.LENGTH_SHORT).show()
        }












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
            R.id.cart -> {
                startActivity(Intent(this, LoginActivity::class.java))
                true
            }
            R.id.dashboard -> true
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