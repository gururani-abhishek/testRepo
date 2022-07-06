package org.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.kevalpatel2106.rulerpicker.RulerValuePicker
import com.kevalpatel2106.rulerpicker.RulerValuePickerListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggleBTNS = findViewById<MaterialButtonToggleGroup>(R.id.toggleBTNS)
        val rulerPickerHolder = findViewById<TextView>(R.id.ruler_picker_holder)
        val rulerPicker = findViewById<RulerValuePicker>(R.id.ruler_picker)


        //by default it's a cm scale
        //calling makeCMScale function
        makeCMScale(rulerPicker, rulerPickerHolder)

        //toggle group for cm/ft buttons
        toggleBTNS.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if(isChecked) {
                when(checkedId) {
                    R.id.btn1 -> makeCMScale(rulerPicker, rulerPickerHolder)
                    R.id.btn2 -> makeFTScale(rulerPicker, rulerPickerHolder)
                }
            }
        }

        //using bottom sheet fragment,
        // that displays the bottom sheet dialog box, when "Next" is pressed.
        val nextPage = findViewById<Button>(R.id.nextPage)
        nextPage.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

    }

    //I'm using a library that provided rulerView.
    //makeFTScale func, sets the scale to 'ft' measure
    private fun makeFTScale(rulerPicker: RulerValuePicker, rulerPickerHolder: TextView) {
        //setMinMaxValue() fun is the library function that sets
        //min and max of the scale, here (40, 90)
                        rulerPicker.setMinMaxValue(40,90)

        //setValuePickerListener() : listener to get notified when the selected value changes.
                        rulerPicker.setValuePickerListener(object : RulerValuePickerListener {
                            //Value changed and the user stopped scrolling the ruler.
                            //Application can consider this value as final selected value.
                            override fun onValueChange(selectedValue: Int) {
                                val ft = getFt(selectedValue)
                                val inch = getInch(selectedValue)
                                rulerPickerHolder.text = "$ft' $inch''"
                            }

                            //Value changed but the user is still scrolling the ruler.
                            //This value is not final value. Application can utilize
                            // this value to display the current selected value.
                            override fun onIntermediateValueChange(selectedValue: Int) {
                                val ft = getFt(selectedValue)
                                val inch = getInch(selectedValue)
                                rulerPickerHolder.text = "$ft' $inch''"
                            }

                        })
    }

    //extracts feets from the total value
    private fun getFt(selectedValue: Int): Int {
        return selectedValue / 10
    }
    //extract inches fromt he total value
    private fun getInch(selectedValue: Int): Int {
        return selectedValue % 10
    }

    //makeFTScale func, sets the scale to 'cm' measure
    private fun makeCMScale(rulerPicker: RulerValuePicker, rulerPickerHolder: TextView) {
                        rulerPicker.setMinMaxValue(150, 200)
        rulerPicker.setValuePickerListener(object : RulerValuePickerListener {
                            override fun onValueChange(selectedValue: Int) {

                                rulerPickerHolder.text = "$selectedValue cm"
                            }

                            override fun onIntermediateValueChange(selectedValue: Int) {
                                rulerPickerHolder.text = "$selectedValue cm"
                            }

                        })
    }
}