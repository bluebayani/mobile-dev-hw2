package org.csuf.cpsc411.simplehttpclient

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CustomActivity : AppCompatActivity() {
    lateinit var cList: MutableList<Claim>
    lateinit var cService: ClaimService
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val fldRowGenerator = ObjDetailScreenGenerator(this).generate()
        setContentView(fldRowGenerator)

        // finding button, claim field, date field, and status by id
        val bView: Button = findViewById(R.id.add_btn)
        val edit1 = findViewById<EditText>(R.id.title)
        val edit2 = findViewById<EditText>(R.id.date)
        val status = findViewById<TextView>(R.id.status)

        // when button is clicked
        bView.setOnClickListener {
            // assign variables to the text from the EditText fields
            val title = edit1.text
            val date = edit2.text
            //if there is text in both of the fields
            if (!title.isNullOrBlank() && !date.isNullOrBlank()) {
                //call add claim from ClaimService
                ClaimService(this).addClaim(Claim(title.toString(), date.toString()))

                //clear the textfields
                edit1.text.clear()
                edit2.text.clear()

                //change the status message to indicate success
                status.text = "Status: <SUCCESSFULLY ADDED CLAIM TO DATABASE>"
            } else {
                // when there is text missing from either of the text fields, display popup message
                Toast.makeText(this, "MISSING FIELD(S).", Toast.LENGTH_LONG).show()
                //change status to indicate failure
                status.text = "Status: <FAILURE TO ADD CLAIM TO DATABASE>"
            }
        }
    }
}