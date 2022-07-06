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

        makeCMScale(rulerPicker, rulerPickerHolder)

        toggleBTNS.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if(isChecked) {
                when(checkedId) {
                    R.id.btn1 -> makeCMScale(rulerPicker, rulerPickerHolder)
                    R.id.btn2 -> makeFTScale(rulerPicker, rulerPickerHolder)
                }
            }
        }

        val nextPage = findViewById<Button>(R.id.nextPage)
        nextPage.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

    }

    private fun makeFTScale(rulerPicker: RulerValuePicker, rulerPickerHolder: TextView) {
                        rulerPicker.setMinMaxValue(40,90)
                        rulerPicker.setValuePickerListener(object : RulerValuePickerListener {
                            override fun onValueChange(selectedValue: Int) {
                                val ft = getFt(selectedValue)
                                val inch = getInch(selectedValue)
                                rulerPickerHolder.text = "$ft' $inch''"
                            }
                            override fun onIntermediateValueChange(selectedValue: Int) {
                                val ft = getFt(selectedValue)
                                val inch = getInch(selectedValue)
                                rulerPickerHolder.text = "$ft' $inch''"
                            }

                        })
    }

    private fun getFt(selectedValue: Int): Int {
        return selectedValue / 10
    }

    private fun getInch(selectedValue: Int): Int {
        return selectedValue % 10
    }

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