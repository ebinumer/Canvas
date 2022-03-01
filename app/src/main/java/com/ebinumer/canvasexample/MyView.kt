package com.ebinumer.canvasexample


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View
import java.util.*


class MyView(context: Context?) :
    View(context) {

    private val _rdmColor: Random = Random()


     val path: Path = Path()
    private val paint: Paint = Paint()
    var newColor=0
    protected override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, paint)


    }

    // Get x and y and follow user motion events
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN ->                 // Starts a new line in the path
                path.moveTo(pointX, pointY)
            MotionEvent.ACTION_MOVE ->                 // Draws line between last point and this point
                path.lineTo(pointX, pointY)
            else -> return false
        }
        invalidate()
        return true
    }

     fun PaintSettings(color: Int, width:Float) {

        paint.style = Paint.Style.STROKE
        paint.color = color
        paint.strokeWidth = width
    }
    fun newPaintSettings() {
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        paint.strokeWidth = 10f
    }
    //Constructor
    init {

        isFocusable = true
        isFocusableInTouchMode = true
        newPaintSettings()
    }



    fun clearCanvas(){
        path.reset()
        invalidate()
    }

    fun undoChange() {
//        path.undoLastChange()
    }

//    fun eraser() {
//        path.undoLastChange()
//    }
    fun onClickUndo() {

    }
}