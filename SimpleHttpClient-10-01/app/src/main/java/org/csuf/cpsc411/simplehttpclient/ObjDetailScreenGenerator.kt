package org.csuf.cpsc411.simplehttpclient

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ObjDetailScreenGenerator(val ctx: Context) {
    // Create a linearlayout object
    lateinit var layoutObj: LinearLayout
    fun generate(): LinearLayout {
        layoutObj = LinearLayout(ctx)
        val lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layoutObj.layoutParams = lParams
        layoutObj.orientation = LinearLayout.VERTICAL
        layoutObj.setBackgroundColor(Color.WHITE)


        // Add objdetail section
        val fldRowGenerator = ObjDetailSectionGenerator(ctx)
        val colView = fldRowGenerator.generate()
        layoutObj.addView(colView)

// to center button horizontally use weight
        // add next button Linear Layout
        val nLayout = LinearLayout(ctx)
        // BOTH NEED TO WRAP CONTENT
        val nParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        //sets the button to be on the right
        nParams.gravity = Gravity.RIGHT
        //nParams.weight = 1.0F
        nLayout.layoutParams = nParams
        nLayout.orientation = LinearLayout.HORIZONTAL
        nLayout.setBackgroundColor(Color.GRAY)

        val nButton = Button(ctx)
        nButton.text = "Add"
        nButton.setId(R.id.add_btn)
        nButton.setBackgroundColor(Color.GREEN)
        val nbParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        nbParams.weight = 1.0F

        //add button to the view
        nLayout.addView(nButton, nbParams)
        layoutObj.addView(nLayout)

        val lbl = TextView(ctx)
        val lbParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        lbl.text = "Status: <Status Message>"
//        lbl.setBackgroundColor(Color.YELLOW)
        lbParams.gravity = Gravity.CENTER
        layoutObj.addView(lbl, lbParams)


        return layoutObj
    }
}