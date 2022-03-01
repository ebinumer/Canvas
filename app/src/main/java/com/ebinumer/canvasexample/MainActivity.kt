package com.ebinumer.canvasexample


import android.graphics.Color
import android.media.AudioManager
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import vadiole.colorpicker.ColorModel
import vadiole.colorpicker.ColorPickerDialog


class MainActivity : AppCompatActivity() {
    var mDefaultColor = Color.BLACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myView = MyView(this)
        linearLayout.addView(myView)

        btn_clear.setOnClickListener {
            myView.clearCanvas()
        }
        seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
//                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0)
                    Log.e("deek","$b")
                    Log.e("deek","$seekBar")
                    Log.e("deek","$i")
                    myView.PaintSettings(mDefaultColor, i.toFloat())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        btn_color.setOnClickListener {

            val colorPicker: ColorPickerDialog = ColorPickerDialog.Builder()
                //  set initial (default) color
                .setInitialColor(mDefaultColor)
                .setColorModel(ColorModel.HSV)
                .setColorModelSwitchEnabled(true)
                .setButtonOkText(android.R.string.ok)
                .setButtonCancelText(android.R.string.cancel)
                .onColorSelected { color: Int ->
                    mDefaultColor=color
                    myView.PaintSettings(mDefaultColor,10f)
                    btn_color.setBackgroundColor(mDefaultColor)
                }

                //  create dialog
                .create()

            colorPicker.show(supportFragmentManager, "color_picker")
        }
    }

}