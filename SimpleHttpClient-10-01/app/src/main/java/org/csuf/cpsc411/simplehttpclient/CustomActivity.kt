package org.csuf.cpsc411.simplehttpclient

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CustomActivity : AppCompatActivity() {
    lateinit var cList : MutableList<Claim>
    lateinit var cService : ClaimService
    lateinit var claim: Claim
    fun refreshScreen(cObj : Claim) {
        Log.d("Claim service", "Refreshing the screen")
        val titleView : EditText = findViewById(R.id.title)
        val dateView : EditText = findViewById(R.id.date)
        titleView.setText(cObj.title)
        dateView.setText(cObj.date)
        val addBtn : Button = findViewById(R.id.add_btn)
        Log.d("ADDED", "${cObj.title},${cObj.date}")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fldRowGenerator = ObjDetailScreenGenerator(this).generate()
        setContentView(fldRowGenerator)

        val bView : Button = findViewById(R.id.add_btn)
        bView.setOnClickListener {
            //TODO: need to add the text in the text fields to a claim object
            refreshScreen(claim)
        }
        //PersonService(this).addPerson(Person("Amie", "Lam", "874847489"))
        //ClaimService(this).addClaim(Claim("from claim title text field", "from date text field"))
    }
}